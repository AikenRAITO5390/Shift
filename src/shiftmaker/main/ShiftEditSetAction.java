package shiftmaker.main;

import java.time.LocalDate;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import bean.Store;
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
		int count = Integer.parseInt(countStr);
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


		// リクエストパラメータを取得
        String workerId = req.getParameter("workerId");
        // 確認用
        System.out.println("workerId: " + workerId);
        String shiftDateString = req.getParameter("date");
        // 確認用
        System.out.println("date: " + shiftDateString);
        String workTimeId = req.getParameter("workTimeId");
     	// 確認用
        System.out.println("workTimeId: " + workTimeId);
        String customStartTime = req.getParameter("shiftTimeStart");
        // 確認用
        System.out.println("customStartTime: " + customStartTime);
        String customEndTime = req.getParameter("shiftTimeEnd");
     	// 確認用
        System.out.println("customEndTime: " + customEndTime);


        // カスタム時間の検証（エラー処理）
        if (customStartTime != null && customEndTime != null) {
            int startHour = Integer.parseInt(customStartTime);
            int endHour = Integer.parseInt(customEndTime);

            if (startHour >= endHour) {
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

        // リクエストにデータをセット
     	req.setAttribute("workerId", workerId);
     	req.setAttribute("store_time_start", startHour);
     	req.setAttribute("store_time_end", endHour);
     	req.setAttribute("workTimes", workTimes);
     	req.setAttribute("date", shiftDateString);

     	count += 1;

		req.setAttribute("count", count);

        // JSPへフォワード
        req.getRequestDispatcher("shift_edit_set.jsp").forward(req, res);
    }

}
