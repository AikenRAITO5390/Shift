package shiftmaker.main;

import java.sql.Date;
import java.time.LocalDate;
import java.util.List;
import java.util.Objects;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import bean.Store;
import bean.Worker;
import dao.ShiftDao;
import dao.StoreDao;
import dao.WorkerDao;
import tool.Action;
import tool.CalendeCreate;


// 最初のカレンダーを出す
public class ShiftWorkerSignupAction extends Action{

	@Override
    public void execute(HttpServletRequest req, HttpServletResponse res) throws Exception {

		System.out.println("★★★★★★①★★★★★★");

		// セッションを取得
		HttpSession session = req.getSession();

		int count = 1;

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
    	Store store = storeDao.get(loginuser.getStoreId());
    	// 確認用
    	System.out.println("store：" + store);

        LocalDate todaysDate = LocalDate.now();// LcalDateインスタンスを取得
		int year = todaysDate.getYear();// 現在の年を取得
		int month = todaysDate.getMonthValue();// 現在の月を取得

		// 次の月のもの
		int nextmonth = month + 1;

		// 次の年用
		if(nextmonth == 13){
			nextmonth = 1;
			year = year + 1;
		}

		// カレンダーを初期化
		CalendeCreate calende = new CalendeCreate();
		// カレンダーを作成
		List<LocalDate> dates = calende.Calender(year, nextmonth);

		dates.removeIf(Objects::isNull);

		System.out.println("dates: " + dates);

		// ShiftDBにデータが存在するか確認
        boolean shiftExists = shiftDao.checkIfShiftExists(workerId, year, nextmonth);

		// シフトデータが存在しない場合、新規作成
        if (!shiftExists) {
            System.out.println("シフトデータが存在しないため、新規作成します。");
            for (LocalDate localDate : dates) {
            	Date sqlDate = Date.valueOf(localDate); // 変換処理
                System.out.println("処理中の日付: " + sqlDate);
                shiftDao.createShift(workerId, sqlDate, 1, null, null, null, null, null, null, loginuser.getStoreId());
            }
        }


		// リクエストにデータをセット
		req.setAttribute("dates", dates);
		req.setAttribute("worker", worker);
		req.setAttribute("year", year);
		req.setAttribute("nextmonth", nextmonth);
		req.setAttribute("count", count);

        // JSPへフォワード
        req.getRequestDispatcher("shift_worker_signup.jsp").forward(req, res);
    }


}
