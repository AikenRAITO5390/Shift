package shiftmaker.main;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import bean.Store;
import bean.Worker;
import dao.ShiftDao;
import dao.StoreDao;
import dao.WorkerDao;
import tool.Action;

public class ShiftEditSetAction extends Action{

	@Override
    public void execute(HttpServletRequest req, HttpServletResponse res) throws Exception {

		System.out.println("★★★★★★②★★★★★★");

		// セッションを取得
		HttpSession session = req.getSession();

		String countStr = req.getParameter("count");
		int count = (countStr != null && !countStr.isEmpty()) ? Integer.parseInt(countStr) : 0;
		System.out.println("count：" + count);

		// ログインユーザーを取得
     	Store manager = (Store)session.getAttribute("user");
        if (manager == null) {
            System.out.println("Error: storeがセッションにありません");
            res.sendRedirect("login.jsp");
            return;
        }

        LocalDate todaysDate = LocalDate.now();// LcalDateインスタンスを取得
		Integer year = todaysDate.getYear();// 現在の年を取得
		Integer month = todaysDate.getMonthValue();// 現在の月を取得

		// 次の月のもの
		Integer nextmonth = month + 1;

		// 次の年用
		if(nextmonth == 13){
			nextmonth = 1;
			year = year + 1;
		}

		// String型にする
		String yearstr = year.toString();
		String nextmonthstr = nextmonth.toString();

		// WorkerDaoを初期化
		WorkerDao workerDao = new WorkerDao();
		// ShiftDaoを初期化
		ShiftDao shiftDao = new ShiftDao();
		// StoreDaoを初期化
		StoreDao storeDao = new StoreDao();

		//ログイン名をヘッダーに出す

		Store store_login = storeDao.get(manager.getStoreId());

		// リクエストパラメータを取得
        String workerId = req.getParameter("workerId");
        // 確認用
        System.out.println("workerId: " + workerId);
        String shiftDateString = req.getParameter("date");
        // 確認用
        System.out.println("date: " + shiftDateString);
        String workTimeId = req.getParameter("workTimeId");
        req.setAttribute("workTimeId", workTimeId);
     	// 確認用
        System.out.println("workTimeId: " + workTimeId);
        String customStartTime = req.getParameter("shiftTimeStart");
        req.setAttribute("customStartTime", customStartTime);
        // 確認用
        System.out.println("customStartTime: " + customStartTime);
        String customEndTime = req.getParameter("shiftTimeEnd");
        req.setAttribute("customEndTime", customEndTime);
     	// 確認用
        System.out.println("customEndTime: " + customEndTime);


     // カスタム時間の検証（エラー処理）
        if (customStartTime != null && !customStartTime.isEmpty() && customEndTime != null && !customEndTime.isEmpty()) {
            // 日付と時刻の形式を解析するためのフォーマットを作成
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.S");

            // 文字列をLocalDateTimeとしてパース
            LocalDateTime startDateTime = LocalDateTime.parse(customStartTime, formatter);
            LocalDateTime endDateTime = LocalDateTime.parse(customEndTime, formatter);

            // 時間部分（Hour）を抽出
            int startHour_c = startDateTime.getHour();
            int endHour_c = endDateTime.getHour();
            req.setAttribute("startHour_c", startHour_c);
         	req.setAttribute("endHour_c", endHour_c);

            // 開始時刻と終了時刻が正しいか確認
            if (startHour_c >= endHour_c) {
                req.setAttribute("errorMessage", "正確に時間を選択してください。");
                req.getRequestDispatcher("shift_worker_signup_set.jsp").forward(req, res);
                return; // 処理を中断
            }
        }

        // 営業時間の取得（Eの選択肢のため）
     	String store_time_start = storeDao.TimeStartGet(manager.getStoreId());
		String store_time_end = storeDao.TimeEndGet(manager.getStoreId());

		// 時刻を整数値に変換 (時間部分のみを抽出)
	    int startHour = Integer.parseInt(store_time_start.split(":")[0]);
	    int endHour = Integer.parseInt(store_time_end.split(":")[0]);

	    // 店舗の勤務時間を取得
        List<Store> workTimes = storeDao.getWorkTimes(manager.getStoreId());

        // 確認用
        System.out.println(workTimes);
        System.out.println("店舗ID: " + manager.getStoreId());

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

        // リクエストにデータをセット
     	req.setAttribute("workerId", workerId);
     	req.setAttribute("store_time_start", startHour);
     	req.setAttribute("store_time_end", endHour);
     	req.setAttribute("workTimes", workTimes);
     	req.setAttribute("date", shiftDateString);
     	req.setAttribute("managerName", store_login.getManagerName());

     	count += 1;

		req.setAttribute("count", count);

        // JSPへフォワード
        req.getRequestDispatcher("shift_edit_set.jsp").forward(req, res);
    }

}
