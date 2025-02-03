package shiftmaker.main;

import java.time.LocalDate;
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

		// WorkerDaoを初期化
		WorkerDao workerDao = new WorkerDao();
		// ShiftDaoを初期化
		ShiftDao shiftDao = new ShiftDao();
		// StoreDaoを初期化
		StoreDao storeDao = new StoreDao();

		//ログイン名をヘッダーに出す
		 Worker worker_login = workerDao.get(loginuser.getWorkerId());

		// loginuserからworkerIdを取得
	    String workerId = loginuser.getWorkerId();

        // データベースから該当の従業員情報を取得
        Worker worker = workerDao.get(workerId);


    	// loginuserからstoreIdを取得
    	Store store = storeDao.get(loginuser.getStoreId());


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


		// ShiftDBにデータが存在するか確認
        boolean shiftExists = shiftDao.checkIfShiftExists(workerId, year, nextmonth);

        try{
        	// シフトデータが存在しない場合、新規作成
            if (!shiftExists) {
                shiftDao.createShift(workerId, 1, null, null, null, null, null, null, loginuser.getStoreId(), dates);
            }
        } catch (Exception e){
	        e.printStackTrace();
        }



		// リクエストにデータをセット
		req.setAttribute("dates", dates);
		req.setAttribute("worker", worker);
		req.setAttribute("year", year);
		req.setAttribute("nextmonth", nextmonth);
		req.setAttribute("count", count);
		req.setAttribute("WorkerName", worker_login.getWorkerName());

        // JSPへフォワード
        req.getRequestDispatcher("shift_worker_signup.jsp").forward(req, res);
    }


}
