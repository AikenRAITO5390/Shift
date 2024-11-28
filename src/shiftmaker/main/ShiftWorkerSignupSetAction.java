package shiftmaker.main;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Date;
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

public class ShiftWorkerSignupSetAction extends Action{

	@Override
    public void execute(HttpServletRequest req, HttpServletResponse res) throws Exception {

		System.out.println("★★★★★★②★★★★★★");

		// セッションを取得
		HttpSession session = req.getSession();

		// ログインユーザーを取得
     	Worker loginuser = (Worker)session.getAttribute("user");
     	// 確認用
    	System.out.println("loginuser：" + loginuser);

		// WorkerDaoを初期化
		WorkerDao workerDao = new WorkerDao();
		// ShiftDaoを初期化
		ShiftDao shiftDao = new ShiftDao();
		// StoreDaoを初期化
		StoreDao storeDao = new StoreDao();

		// loginuserからworkerIdを取得
	    String workerId = loginuser.getWorkerId();
        // 確認用
    	System.out.println("workerId：" + workerId);
        // データベースから該当の従業員情報を取得
        Worker worker = workerDao.get(workerId);
        // 確認用
    	System.out.println("worker：" + worker);

    	// loginuserからstoreIdを取得
    	String storeId = loginuser.getStoreId();
    	// 確認用
    	System.out.println("storeId：" + storeId);

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

     	// リクエストパラメータから日付を取得
		// "shiftDay" パラメータで日付を取得
        String shiftDateString = req.getParameter("shiftDay");

        String shiftDate = yearstr + "-" + nextmonthstr + "-" + "0" + shiftDateString;

        System.out.println("選択した日:" + shiftDateString);

        // 日付が選択されている場合は、Date型に変換してリクエストにセット
        if (shiftDateString != null && !shiftDateString.isEmpty()) {

        	// 日付のフォーマットを指定
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            // 文字列をjava.util.Date型に変換
            Date utilDate = sdf.parse(shiftDate);

            // java.sql.Dateに変換
            java.sql.Date sqlDate = new java.sql.Date(utilDate.getTime());

            // java.sql.Date型でリクエスト属性にセット
            req.setAttribute("shiftDate", sqlDate);
            System.out.println("取得した日:" + sqlDate);
        }


     	// 営業時間の取得（Eの選択肢のため）
     	String store_time_start = storeDao.TimeStartGet(loginuser.getStoreId());
		String store_time_end = storeDao.TimeEndGet(loginuser.getStoreId());

		// 時刻を整数値に変換 (時間部分のみを抽出)
	    int startHour = Integer.parseInt(store_time_start.split(":")[0]);
	    int endHour = Integer.parseInt(store_time_end.split(":")[0]);

        // 店舗の勤務時間を取得
        List<Store> workTimes = storeDao.getWorkTimes(loginuser.getStoreId());

        // 確認用
        System.out.println(workTimes);
        System.out.println("店舗ID" + loginuser.getStoreId());

		// リクエストにデータをセット
		req.setAttribute("worker", worker);
		req.setAttribute("workTimes", workTimes);
		req.setAttribute("store_time_start", startHour);
		req.setAttribute("store_time_end", endHour);
		req.setAttribute("year", year);
		req.setAttribute("month", month);
		req.setAttribute("nextmonth", nextmonth);

        // JSPへフォワード
        req.getRequestDispatcher("shift_worker_signup_set.jsp").forward(req, res);
    }


}
