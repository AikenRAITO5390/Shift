package shiftmaker.main;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
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
import tool.CalendeCreate;


// 選択されたものを保存しながら、カレンダーに反映させる
public class ShiftWorkerSignupSaveAction extends Action {
    @Override
    public void execute(HttpServletRequest req, HttpServletResponse res) throws Exception {

    	System.out.println("★★★★★★③★★★★★★");

    	/*** ---------- 取得、初期化、宣言 ---------- ***/
    	// セッションを取得
    	HttpSession session = req.getSession();

    	// ログインユーザーを取得
     	Worker loginuser = (Worker)session.getAttribute("user");
     	// 確認用
    	System.out.println("loginuser：" + loginuser);

    	// loginuserからworkerIdを取得
	    String workerId = loginuser.getWorkerId();
        // 確認用
    	System.out.println("workerId：" + workerId);

    	// loginuserからstoreIdを取得
    	String storeId = loginuser.getStoreId();
    	// 確認用
    	System.out.println("storeId：" + storeId);

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

		// insert用宣言
		String shift_hope_time_id = null;
		String work_time_id = null;
		String shift_time_start = null;
		String shift_time_end = null;
		String customStartTime = null;
        String customEndTime = null;
		// 日時フォーマットを指定（例: "yyyy-MM-dd HH:mm:ss"）
		SimpleDateFormat dateTimeFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Timestamp timestampCustomStartTime = null;
		Timestamp timestampCustomEndTime = null;

		// リクエストパラメータを取得
    	String shiftDateString = req.getParameter("shiftDate");  // String型として取得
    	// 確認用
    	System.out.println("shiftDateString：" + shiftDateString);

    	String workTimeId = req.getParameter("workTimeId");

    	// Worker オブジェクトを取得
        Worker worker = workerDao.get(workerId);
        if (worker == null) {
            throw new Exception("Worker not found for workerId: " + workerId);
        }

        // Store オブジェクトを取得
    	Store store = storeDao.get(loginuser.getStoreId());
    	// 確認用
    	System.out.println("store：" + store);

    	/*** ----------------------------------------------------------------------------- ***/

    	// カレンダーを初期化
    	CalendeCreate calende = new CalendeCreate();
    	// カレンダーを作成
    	Map<LocalDate, String> dates = calende.generateCalendarWithDBInfo(year, nextmonth, storeId, workerId);
    	// 確認用
    	System.out.println("dates：" + dates);

    	// LocalDateリストをString型に変換
    	List<String> stringDates = new ArrayList<>();
    	for (Map.Entry<LocalDate, String> entry : dates.entrySet()) {
    	    LocalDate date = entry.getKey();  // Mapのキー（LocalDate）
    	    String value = entry.getValue();  // Mapの値（String）
    	    if (date != null) {
    	        stringDates.add(date.toString()); // ISO形式で文字列化 (例: "2024-11-29")
    	    } else {
    	        stringDates.add(null); // null値を保持
    	    }
    	}
    	// 確認用
    	System.out.println("更新前のstringDates：" + stringDates);

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

    		// LocalDate型に変換
    		LocalDate selectedDate = sqlShiftDate.toLocalDate();

    		// SHIFTデータを取得
    		Shift shift = shiftDao.getShiftScore(worker, sqlShiftDate, store);
    		// 確認用
        	System.out.println("shift：" + shift);

        	String shiftScore = String.valueOf(shift.getShiftScore());
        	// 確認用
        	System.out.println("shiftScore：" + shiftScore);

//        	// カレンダー作成後、null を除外
//        	stringDates.removeIf(date -> date == null);
//        	// カレンダー作成後、null を除外
//        	dates.removeIf(date -> date == null);

        	// 選択された日付を文字列形式に変換
        	String selectedDateString = selectedDate.toString();

        	// stringDatesを更新：勤務時間情報のみ格納
        	for (int i = 0; i < stringDates.size(); i++) {
        	    if (stringDates.get(i) != null && stringDates.get(i).equals(selectedDateString)) {
        	        if ("E".equals(workTimeId)) {
        	            // カスタム時間を取得
        	            customStartTime = req.getParameter("customStartTime");
        	            customEndTime = req.getParameter("customEndTime");
        	            // 勤務時間としてカスタム時間を設定
        	            stringDates.set(i, customStartTime + " - " + customEndTime);
        	        } else {
        	            // 通常の勤務時間 ID を設定
        	            stringDates.set(i, workTimeId);
        	        }
        	    } else if (stringDates.get(i) == null) {
        	        // 勤務がない日を空白にする
        	        stringDates.set(i, "");
        	    }
        	}


        	// 確認用
        	System.out.println("更新後の stringDates：" + stringDates);



        	// Eに設定された時間をDBに保存するための変換など + 通常の保存（else側）
    		if ("E".equals(workTimeId)) {
    		    // カスタム時間を取得
    			customStartTime = req.getParameter("customStartTime");
    			// 確認用
	        	System.out.println("customStartTime：" + customStartTime);
    			customEndTime = req.getParameter("customEndTime");

    			String customStartTime2 = selectedDateString + " " + customStartTime + ":00:00";
    			// 確認用
	        	System.out.println("customStartTime2：" + customStartTime2);

	        	String customEndTime2 = selectedDateString + " " + customEndTime + ":00:00";
	        	// 確認用
	        	System.out.println("customEndTime2：" + customEndTime2);

    			try {
    				shift_hope_time_id = null;
    			    // リクエストから取得したカスタム時間をパースして Timestamp 型に変換
    			    timestampCustomStartTime = new Timestamp(dateTimeFormat.parse(customStartTime2).getTime());
    			    timestampCustomEndTime = new Timestamp(dateTimeFormat.parse(customEndTime2).getTime());
    			} catch (Exception e) {
    			    e.printStackTrace();
    			    throw new Exception("Invalid custom time format. Please provide time in 'yyyy-MM-dd HH:mm:ss' format.");
    			}

    		    // SHIFTテーブルにカスタム時間を登録
    		    shiftDao.insertCustomWorkTime(sqlShiftDate, workerId, storeId, timestampCustomStartTime, timestampCustomEndTime, shiftScore, shift_hope_time_id, work_time_id, shift_time_start, shift_time_end);
    		} else {
    		    // 通常の勤務時間IDをSHIFTテーブルに登録
    		    shiftDao.insertWorkTime(sqlShiftDate, workerId, storeId, workTimeId, shiftScore, shift_hope_time_id, shift_time_start, shift_time_end, timestampCustomStartTime, timestampCustomEndTime);
    		}


    	req.setAttribute("stringDates", stringDates);
    	req.setAttribute("dates", dates);

    	String countStr = req.getParameter("count");
		int count = Integer.parseInt(countStr);
		System.out.println("count：" + count);
		req.setAttribute("count", count);


        // 保存後にカレンダー画面を再度表示する
        req.getRequestDispatcher("shift_worker_singup_save.jsp").forward(req, res);
    }
}