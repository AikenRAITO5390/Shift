package shiftmaker.main;

import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import bean.Store;
import bean.Worker;
import dao.ShiftDao;
import dao.StoreDao;
import dao.WorkerDao;
import tool.Action;

public class ShiftWorkerSignupResultAction extends Action{

	@Override
	public void execute(HttpServletRequest req, HttpServletResponse res) throws Exception {

		System.out.println("★★★★★★④★★★★★★");

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
    	// loginuserからstoreIdを取得
    	Store store = storeDao.get(loginuser.getStoreId());
    	// 確認用
    	System.out.println("store：" + store);

	    // リクエストパラメータを取得
		String shiftDateString = req.getParameter("shiftDate");  // String型として取得
	    String workTimeId = req.getParameter("workTimeId");

	    // 文字列をDate型に変換
	    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");  // 日付のフォーマットを指定
	    Date shiftDate = null;
	    try {
	        shiftDate = sdf.parse(shiftDateString);  // 文字列をDate型に変換
	    } catch (Exception e) {
	        // 変換失敗時の処理（例: エラーメッセージを出すなど）
	        e.printStackTrace();
	    }

	    // java.util.Date を java.sql.Date に変換
	    java.sql.Date sqlShiftDate = new java.sql.Date(shiftDate.getTime());

	    String shiftScore = String.valueOf(shiftDao.get(worker, sqlShiftDate, store).getShiftScore());


	    if ("E".equals(workTimeId)) {
	        // カスタム時間を取得
	        String customStartTime = req.getParameter("customStartTime");
	        String customEndTime = req.getParameter("customEndTime");

	        // SHIFTテーブルにカスタム時間を登録
	        shiftDao.insertCustomWorkTime(sqlShiftDate, workerId, storeId, customStartTime, customEndTime, shiftScore);
	    } else {
	        // 通常の勤務時間IDをSHIFTテーブルに登録
	        shiftDao.insertWorkTime(sqlShiftDate, workerId, storeId, workTimeId, shiftScore);
	    }

	    // JSPへフォワード
	    req.getRequestDispatcher("shift_worker_signup_result.jsp").forward(req, res);
	}

}
