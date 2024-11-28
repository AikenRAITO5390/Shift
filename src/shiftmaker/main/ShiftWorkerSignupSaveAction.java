package shiftmaker.main;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;

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

public class ShiftWorkerSignupSaveAction extends Action {
    @Override
    public void execute(HttpServletRequest req, HttpServletResponse res) throws Exception {

    	System.out.println("★★★★★★③★★★★★★");

    	// セッションを取得
    	HttpSession session = req.getSession();

    	// ログインユーザーを取得
     	Worker loginuser = (Worker)session.getAttribute("user");
     	// 確認用
    	System.out.println("loginuser：" + loginuser);

    	// 初期化
    	ShiftDao shiftDao = new ShiftDao();
    	WorkerDao workerDao = new WorkerDao();
    	StoreDao storeDao = new StoreDao();

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

    	// カレンダーを初期化
    	CalendeCreate calende = new CalendeCreate();
    	// カレンダーを作成
    	List<LocalDate> dates = calende.Calender(year, nextmonth);



    	req.setAttribute("dates", dates);

    	// リクエストパラメータを取得
    	String shiftDateString = req.getParameter("shiftDate");  // String型として取得
    	// 確認用
    	System.out.println("shiftDateString：" + shiftDateString);

    	String workTimeId = req.getParameter("workTimeId");

    	// loginuserからworkerIdを取得
	    String workerId = loginuser.getWorkerId();
        // 確認用
    	System.out.println("workerId：" + workerId);

    	// loginuserからstoreIdを取得
    	String storeId = loginuser.getStoreId();
    	// 確認用
    	System.out.println("storeId：" + storeId);

    	// Worker オブジェクトを取得
        Worker worker = workerDao.get(workerId);
        if (worker == null) {
            throw new Exception("Worker not found for workerId: " + workerId);
        }

        // Store オブジェクトを取得
    	Store store = storeDao.get(loginuser.getStoreId());
    	// 確認用
    	System.out.println("store：" + store);

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

    		// SHIFTデータを取得
    		Shift shift = shiftDao.getShiftScore(worker, sqlShiftDate, store);
    		// 確認用
        	System.out.println("shift：" + shift);

    		// NULLチェックを追加
    		if (shift == null) {
    		    throw new Exception("Shift not found for workerId: " + workerId + ", date: " + sqlShiftDate + ", storeId: " + store.getStoreId());
    		}

    		String shiftScore = String.valueOf(shift.getShiftScore());

        	// 確認用
        	System.out.println("shiftScore：" + shiftScore);


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

        // 保存後にカレンダー画面を再度表示する
        req.getRequestDispatcher("shift_worker_singup_save.jsp").forward(req, res);
    }
}