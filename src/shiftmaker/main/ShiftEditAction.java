package shiftmaker.main;

import java.sql.Date;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

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

public class ShiftEditAction extends Action{

	@Override
    public void execute(HttpServletRequest req, HttpServletResponse res) throws Exception {

		// セッションを取得
        HttpSession session = req.getSession();

        int count = 1;

        // Daoを初期化
        ShiftDao shiftDao = new ShiftDao();
        WorkerDao workerDao  = new WorkerDao();
        StoreDao storeDao = new StoreDao();
        CalendeCreate calende = new CalendeCreate();

        LocalDate todaysDate = LocalDate.now();
		int year = todaysDate.getYear();
		int month = todaysDate.getMonthValue();

		int nextmonth = month +1;

		if(nextmonth == 13){
			nextmonth = 1;
			year = year+1;
		}

		// カレンダーを生成
		List<LocalDate> dates = calende.Calender(year, nextmonth);
		dates.removeIf(Objects::isNull);

        // ログインユーザーを取得
     	Store manager = (Store)session.getAttribute("user");
        if (manager == null) {
            res.sendRedirect("login.jsp");
            return;
        }

        Store store_login = storeDao.get(manager.getStoreId());

        // 店舗IDを取得
        String storeId = manager.getStoreId();

        //従業員情報一覧を格納するリスト
      	List<Worker> worker_list = new ArrayList<>();
      	//従業員情報一覧を取得
      	worker_list = workerDao.filter(manager);

        // 店舗IDに基づいてシフト情報を取得
        List<Shift> shifts = shiftDao.getShiftsByStoreId(storeId);

        // 従業員IDと日付をキーにシフト情報を格納するマップ
        Map<String, Map<LocalDate, Shift>> shiftMap = new HashMap<>();
        // 日付ごとの shift_score を格納
        Map<LocalDate, Map<String, Integer>> shiftScores = new HashMap<>();

        // シフトデータを整理
        for (Shift shift : shifts) {
        	// workeridのnullチェック
            if (shift.getWorker() == null) {
                continue;
            }
            String workerId = shift.getWorker().getWorkerId();

            // Worker オブジェクトを取得
            Worker worker = workerDao.get(workerId);
            if (worker == null) {
                throw new Exception("Worker not found for workerId: " + workerId);
            }

        	// worker_judgeの値を取得
            boolean workerJudge = worker.isWorkerJudge();

            // worker_judgeがTrueの場合
            if (workerJudge) {
                req.setAttribute("isWorkerJudgeTrue", true);
            } else {
                // worker_judgeがFalseの場合
                req.setAttribute("isWorkerJudgeTrue", false);
            }


            // 日付を取得
            LocalDate shiftDate = shift.getShiftDate().toLocalDate();
            String workTimeId = shift.getWorkTimeId();

            // LocalDate を java.sql.Date に変換
            Date sqlDate = Date.valueOf(shiftDate);

            // shift_score を取得
            String shiftScore = shiftDao.shiftScoreGet2(sqlDate, storeId, workerId);

            // String を int に変換
            int shiftScore2 = 0;  // 初期値を 0 に設定
            try {
                shiftScore2 = Integer.parseInt(shiftScore);  // String を int に変換
            } catch (NumberFormatException e) {
                System.out.println("shiftScore の変換に失敗しました: " + shiftScore);
            }

            shiftMap.computeIfAbsent(workerId, k -> new HashMap<>()).put(shiftDate, shift);

            // shiftScores に shift_score を格納
            shiftScores.computeIfAbsent(shiftDate, k -> new HashMap<>()).put(workerId, shiftScore2);


        }

        // 参考時間表示のため
    	List<Store> workTimeDetails = storeDao.getWorkTimes(manager.getStoreId());
    	req.setAttribute("workTimeDetails", workTimeDetails);

        // リクエストにデータをセット
        req.setAttribute("shiftMap", shiftMap);
        req.setAttribute("shiftScores", shiftScores);
        req.setAttribute("dates", dates);
        req.setAttribute("shifts", shifts);
        req.setAttribute("worker_list", worker_list);
        req.setAttribute("count", count);
        req.setAttribute("managerName", store_login.getManagerName());


        // JSPへフォワード
        req.getRequestDispatcher("shift_edit.jsp").forward(req, res);
    }

}
