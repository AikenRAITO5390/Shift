package shiftmaker.main;
import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

public class DayPowerSettingResultAction extends Action{
	public void execute(HttpServletRequest req, HttpServletResponse res) throws Exception {
		//ローカル変数の宣言 1
		StoreDao sDao = new StoreDao();
		ShiftDao shiftDao = new ShiftDao();
		WorkerDao wDao = new WorkerDao();
		HttpSession session = req.getSession();//セッション
		Store store_login = (Store)session.getAttribute("user");// ログインユーザーを取得
		Map<String, String> errors = new HashMap<>();

		        // ArrayListを作成
		        ArrayList<String> dayScoreList = new ArrayList<>();
		        ArrayList<String> weekdayScoreList = new ArrayList<>();

		        ArrayList<Integer> dayScoreListInt = new ArrayList<>();
		        ArrayList<Date> weekdayScoreListDate = new ArrayList<>();

		        // 日付フォーマットを指定
		        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

		                // workDayScoreの数を取得
		        // 7つ分のデータを取得
		        for (int i = 0; i < 33; i++) {
		            // dayScoreを取得してリストに追加
		            String dayScore = req.getParameter("DayScore_" + i);
		            if (dayScore != null && !dayScore.isEmpty()) {
		                dayScoreList.add(dayScore);
		                dayScoreListInt.add(Integer.parseInt(dayScore));
		            }

		            // WorkdayScoreを取得してリストに追加
		            String workdayScore = req.getParameter("WorkDayScore_" + i);
		            if (workdayScore != null && !workdayScore.isEmpty()) {
		                weekdayScoreList.add(workdayScore);
		                java.util.Date workdayScoreDate = dateFormat.parse(workdayScore);
		                java.sql.Date sqlDate = new java.sql.Date(workdayScoreDate.getTime());
		                weekdayScoreListDate.add(sqlDate);
		            }
		        }
	            System.out.println("Day Scores (Int): " + dayScoreListInt);
	            System.out.println("Workday Scores (Date): " + weekdayScoreListDate);
		    // ここでworkTimeIdDates, workTimeEnds, workTimeStartsを使って処理を行う

	    List<Store> storelist = sDao.filterStore(store_login.getStoreId());
	    List<Worker> workerlist = wDao.getWorkersByStoreId(store_login.getStoreId());
	    System.out.println(workerlist);

	    Shift shift ;

	    if (dayScoreListInt != null) {
		    for (int i = 0; i < dayScoreListInt.size(); i++) {
		    	System.out.println(i);
		    	for (Worker worker : workerlist) {
		    		shift = new Shift();

		    		shift.setShiftDate(weekdayScoreListDate.get(i));
		    		shift.setWorker(worker);
		    		shift.setShiftScore(dayScoreListInt.get(i));
		    		shift.setShiftHopeTimeId(null);
		    		shift.setShiftHopeTimeStart(null);
		    		shift.setShiftHopeTimeEnd(null);
		    		shift.setWorkTimeId(null);
		    		shift.setShiftTimeStart(null);
		    		shift.setShiftTimeEnd(null);
		    		shift.setShiftId(store_login.getStoreId()+"_"+(i+1));
		    		shift.setStore(storelist.get(0));

		    		shiftDao.save_Score(shift);

		    	    // 各workerに対して処理を行う
		    	}
		    }

		    }
		//エラーがあったかどうかで手順6~7の内容が分岐

		if(!errors.isEmpty()){//エラーがあった場合、更新画面へ戻る
			// リクエスト属性をセット
			req.setAttribute("errors", errors);

			req.getRequestDispatcher("power_setting.jsp").forward(req, res);
			return;
		}

		req.getRequestDispatcher("power_setting_result.jsp").forward(req, res);
	}

}
