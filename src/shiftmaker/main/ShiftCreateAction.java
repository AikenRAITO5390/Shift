package shiftmaker.main;

//import java.util.Date;
import java.sql.Date;
import java.time.LocalDate;
import java.util.AbstractMap;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import bean.Shift;
import bean.Store;
import bean.Worker;
import dao.ShiftDao;
import dao.StoreDao;
import dao.WorkerDao;
import tool.Action;
import tool.CalendeCreate;
import tool.ShiftCreate;;

public class ShiftCreateAction extends Action{

	@Override
	public void execute(HttpServletRequest req, HttpServletResponse res) throws Exception {
		CalendeCreate calende = new CalendeCreate();
		ShiftDao  shDao = new ShiftDao();
		StoreDao  stDao = new StoreDao();
		WorkerDao wDao  = new WorkerDao();
		ShiftCreate shift_create = new ShiftCreate();


		Integer day = 14;
		String date = null;
		// カレンダーを生成
		List<LocalDate> dates = calende.Calender_side(2024, 11);
		//セッションの作成
		HttpSession session = req.getSession();
		//ログインユーザーの情報取得
		Store manager = (Store)session.getAttribute("user");
		//店舗の開店時間の取得
		String work_time_start = stDao.TimeStartGet(manager.getStoreId());
		//店舗の閉店時間の取得
		String work_time_end = stDao.TimeEndGet(manager.getStoreId());
		//従業員情報一覧を格納するリスト
		List<Worker> worker_list = new ArrayList<>();
		//従業員情報一覧を取得
		worker_list = wDao.filter(manager);
		//優先度付きキューの型指定
		Comparator<Map.Entry<String, Integer>> comparetor = Comparator.comparing(Map.Entry::getValue);
		PriorityQueue<Map.Entry<String, Integer>> priorityQueue = new PriorityQueue<>(comparetor);
		PriorityQueue<Map.Entry<String, Integer>> createShiftQueue = new PriorityQueue<>(comparetor);
		for(Worker worker:worker_list){
			priorityQueue.add(new AbstractMap.SimpleEntry<>(worker.getWorkerName(), 1));
			createShiftQueue.add(new AbstractMap.SimpleEntry<>(worker.getWorkerName(), 1));
		}

		//３０日分のシフト情報を入れるためのリスト
		List<Map<String, Object>> innerList = new ArrayList<>();
		//３０日分回す
		for(int i = 0; i<7 ;i++){
			date =day.toString();
			System.out.println("日時:"+date);
			//日にちを取得
			LocalDate localDates = LocalDate.of(2024, 11,day);
			System.out.println("年月日:"+localDates);
			//日にちをDate型に変換
			Date shift_date = Date.valueOf(localDates);
			//入った日にちのシフト希望を出している従業員情報一覧を格納するリスト
			List<Shift> shift_list = new ArrayList<>();
			//入った日にちのシフト希望を出している従業員情報一覧を取得
			shift_list = shDao.filter(stDao.get(manager.getStoreId()), shift_date);
			//開店時間、閉店時間、シフト作成者情報、従業員情報一覧、日時をいれシフトを作成する関数
			List<Map<String, Object>> workerShift = shift_create.Shiftmain(
					work_time_start, work_time_end,manager,shift_list,date,createShiftQueue,worker_list);

			innerList.addAll(workerShift);

			//優先度管理
			for(Map<String, Object> workerInfo :workerShift){
				List<String> priorityList = new ArrayList<>();
				for(int j =0; j< worker_list.size(); j++) {
					Map.Entry<String, Integer> entry = priorityQueue.poll();
					@SuppressWarnings("unchecked")
					List<String> mergedShifts = (List<String>) workerInfo.get("mergedShifts");
					System.out.println("名前:"+workerInfo.get("name")+"queue名前"+entry.getKey()+"優先度"+entry.getValue());
					if(workerInfo.get("name").equals(entry.getKey())){
						if(mergedShifts.isEmpty()){
							priorityQueue.add(new AbstractMap.SimpleEntry<>(entry.getKey(), entry.getValue()));
							createShiftQueue.removeIf(e -> e.getKey().equals(entry.getKey()));
							createShiftQueue.add(new AbstractMap.SimpleEntry<>(entry.getKey(), entry.getValue()));
							System.out.println("休み名前:"+workerInfo.get("name")+"queue名前"+entry.getKey()+"優先度"+entry.getValue());
							break;
						}else{
							priorityQueue.add(new AbstractMap.SimpleEntry<>(entry.getKey(), entry.getValue()+1));
							createShiftQueue.removeIf(e -> e.getKey().equals(entry.getKey()));
							createShiftQueue.add(new AbstractMap.SimpleEntry<>(entry.getKey(), entry.getValue()+1));
							System.out.println("シフト名前:"+workerInfo.get("name")+"queue名前"+entry.getKey()+"優先度"+entry.getValue());
							break;
						}
					}else{
						priorityList.add(entry.getKey());
						System.out.println("名無し。名前:"+workerInfo.get("name"));
					}
				}

				for(String add_name:priorityList){
					//ここの改良をする！例：Mapに名前と点数をもらって現状維持
					priorityQueue.add(new AbstractMap.SimpleEntry<>(add_name,1));
					createShiftQueue.removeIf(e -> e.getKey().equals(add_name));
					createShiftQueue.add(new AbstractMap.SimpleEntry<>(add_name,1));
				}
			}
			day = day + 1;
		}

		while (!priorityQueue.isEmpty()) {
			Map.Entry<String, Integer> entry = priorityQueue.poll();
		    System.out.println("Name"+entry.getKey()+"Priority"+ entry.getValue());
		}


		//リクエストにカレンダーをセット
		req.setAttribute("dates", dates);
        req.setAttribute("year", 2024);
        req.setAttribute("month", 11);
        req.setAttribute("innerList",innerList);
		req.setAttribute("worker_list", worker_list);
		//shift_select.jspに遷移
		req.getRequestDispatcher("shift_create.jsp").forward(req,res);
	}

}
