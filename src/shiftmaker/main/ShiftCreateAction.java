package shiftmaker.main;

//import java.util.Date;
import java.sql.Date;
import java.time.LocalDate;
import java.util.AbstractMap;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashSet;
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
		//優先度付きキューの作成：数字が小さいほど優先度が高くなる
		PriorityQueue<Map.Entry<String, Integer>> priorityQueue = new PriorityQueue<>(comparetor);
		//priorityqueueのコピーをとるためのキュー
		PriorityQueue<Map.Entry<String, Integer>> priorityQueuesub = new PriorityQueue<>(comparetor);
		//シフト作成に優先度を反映させるためのキュー
		PriorityQueue<Map.Entry<String, Integer>> createShiftQueue = new PriorityQueue<>(comparetor);
		//キューの初期値を入れる
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
			//その日のパワーバランスを取得
			String shift_score = shDao.shiftScoreGet(shift_date, manager.getStoreId());
			Integer shift_score_int = Integer.parseInt(shift_score);
			System.out.println("点数"+shift_score);
			//開店時間、閉店時間、シフト作成者情報、従業員情報一覧、日時をいれシフトを作成する関数
			List<Map<String, Object>> workerShift = shift_create.Shiftmain(
					work_time_start, work_time_end,manager,shift_list,date,createShiftQueue,worker_list,shift_score_int);
			//シフト情報を格納
			innerList.addAll(workerShift);

			//priorityqueueのコピーから値を戻す
			while (!priorityQueuesub.isEmpty()) {
				Map.Entry<String, Integer> entry = priorityQueuesub.poll();
				priorityQueue.add(new AbstractMap.SimpleEntry<>(entry.getKey(), entry.getValue()));
			}

			//優先度管理
			for(int j =0; j< worker_list.size(); j++) {
				//シフト希望を入れてない人を格納する
				List<String> priorityList1 = new ArrayList<>();
				//entryに優先度順に取り出す
				Map.Entry<String, Integer> entry = priorityQueue.poll();
				//シフト表に入っている人の数だけ回す
				for(Map<String, Object> workerInfo :workerShift){
					@SuppressWarnings("unchecked")
					//シフト時間表をリスト化
					List<String> mergedShifts = (List<String>) workerInfo.get("mergedShifts");
					System.out.println("名前:"+workerInfo.get("name")+"queue名前"+entry.getKey()+"優先度"+entry.getValue());
					//シフト希望に入っている名前とentryに入っている人が同じか判別
					if(workerInfo.get("name").equals(entry.getKey())){
						//entryに入っていた人にシフトが割り当てられているか判別
						if(mergedShifts.isEmpty()){
							//シフトが割り当てられていなかった場合休みだったとして優先度を上げる
							priorityQueuesub.add(new AbstractMap.SimpleEntry<>(entry.getKey(), 1));
							createShiftQueue.removeIf(e -> e.getKey().equals(entry.getKey()));
							createShiftQueue.add(new AbstractMap.SimpleEntry<>(entry.getKey(), 1));
							System.out.println("休み名前:"+workerInfo.get("name")+"queue名前"+entry.getKey()+"優先度"+entry.getValue());
							break;
						}else{
							//シフトが割り当てられていた場合優先度を下げる
							priorityQueuesub.add(new AbstractMap.SimpleEntry<>(entry.getKey(), entry.getValue()+1));
							createShiftQueue.removeIf(e -> e.getKey().equals(entry.getKey()));
							createShiftQueue.add(new AbstractMap.SimpleEntry<>(entry.getKey(), entry.getValue()+1));
							System.out.println("シフト名前:"+workerInfo.get("name")+"queue名前"+entry.getKey()+"優先度"+entry.getValue());
							break;
						}
					}else{
						//シフト希望が入っていない場合
						priorityList1.add(entry.getKey());
						System.out.println("名無し。名前:"+workerInfo.get("name"));
					}
				}
				//シフト希望が入っていなかった人をキューに格納する
				List<String> priorityListCheck = new ArrayList<String>(new HashSet<>(priorityList1));
				for(String add_name:priorityListCheck){
					// キー が存在するか確認
			        String targetKey = add_name;
			        //キューの中にキーと同じ名前が入っているか確認
			        boolean containsKey = priorityQueuesub.stream().anyMatch(e -> e.getKey().equals(targetKey));
			        //キューの中に値があるか判別
			        if(containsKey){
			        	break;
			        }else{
			        	//キューの中に値が入っていない場合格納
			        	priorityQueuesub.add(new AbstractMap.SimpleEntry<>(add_name,1));
			        	createShiftQueue.add(new AbstractMap.SimpleEntry<>(add_name,1));
			        }
				}
			}
			day = day + 1;
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
