package shiftmaker.main;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.YearMonth;
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

    	/*** ---------- 取得、初期化、宣言 ---------- ***/
    	// セッションを取得
    	HttpSession session = req.getSession();

    	// ログインユーザーを取得
     	Worker loginuser = (Worker)session.getAttribute("user");

    	// loginuserからworkerIdを取得
	    String workerId = loginuser.getWorkerId();

    	// loginuserからstoreIdを取得
    	String storeId = loginuser.getStoreId();

    	// 初期化
    	ShiftDao shiftDao = new ShiftDao();
    	WorkerDao workerDao = new WorkerDao();
    	StoreDao storeDao = new StoreDao();

    	//ログイン名をヘッダーに出す
    	Worker worker_login = workerDao.get(loginuser.getWorkerId());

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

    	String workTimeId = req.getParameter("workTimeId");

    	// Worker オブジェクトを取得
        Worker worker = workerDao.get(workerId);
        if (worker == null) {
            throw new Exception("Worker not found for workerId: " + workerId);
        }

        // Store オブジェクトを取得
    	Store store = storeDao.get(loginuser.getStoreId());

    	/*** ----------------------------------------------------------------------------- ***/

    	// カレンダーを初期化
    	CalendeCreate calende = new CalendeCreate();
    	// カレンダーを作成
    	Map<LocalDate, String> dates = calende.generateCalendarWithDBInfo(year, nextmonth, storeId, workerId);

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
    	req.setAttribute("stringDates", stringDates);

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

        	String shiftScore = String.valueOf(shift.getShiftScore());

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

        	// Eに設定された時間をDBに保存するための変換など + 通常の保存（else側）
    		if ("E".equals(workTimeId)) {
    		    // カスタム時間を取得
    			customStartTime = req.getParameter("customStartTime");
    			// 確認用
	        	System.out.println("customStartTime：" + customStartTime);
    			customEndTime = req.getParameter("customEndTime");

    			String customStartTime2 = selectedDateString + " " + customStartTime + ":00:00";

	        	String customEndTime2 = selectedDateString + " " + customEndTime + ":00:00";

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
    		} else if ("NONE".equals(workTimeId)){
    			// なしが選ばれた場合、nullをDBに保存する
    			shiftDao.updatenullShifthope(workerId, new java.sql.Date(shiftDate.getTime()));
    		} else {
    		    // 通常の勤務時間IDをSHIFTテーブルに登録
    		    shiftDao.insertWorkTime(sqlShiftDate, workerId, storeId, workTimeId, shiftScore, shift_hope_time_id, shift_time_start, shift_time_end, timestampCustomStartTime, timestampCustomEndTime);
    		}

    		// nullとカスタム時間のみを格納するリスト
    		// nullAndTimeリストを初期化
    		List<String> nullAndTime = new ArrayList<>();

    		// カレンダーの日付ごとに勤務時間データを取得
    		for (Map.Entry<LocalDate, String> entry : dates.entrySet()) {
    		    LocalDate date = entry.getKey();

    		    if (date != null) {
    		        // DBから勤務時間を取得
    		        String workTime = shiftDao.getWorkTime(workerId, java.sql.Date.valueOf(date));

    		        // 勤務時間が存在する場合、その値をnullAndTimeにセット
    		        if (workTime != null) {
    		            nullAndTime.add(workTime);
    		        } else {
    		            nullAndTime.add(null); // 勤務時間がない場合はnull
    		        }
    		    } else {
    		        nullAndTime.add(null); // 日付がない場合はnull
    		    }
    		}

    		// カレンダーの日付ごとにshift_hope_time_idを取得
    		List<String> shiftHopeTimeIds = new ArrayList<>();

    		for (Map.Entry<LocalDate, String> entry : dates.entrySet()) {
    		    LocalDate date = entry.getKey();

    		    if (date != null) {
    		        // DBからshift_hope_time_idを取得
    		        String shiftHopeTimeId = shiftDao.getShiftHopeTimeId(workerId, java.sql.Date.valueOf(date));

    		        // shift_hope_time_idが存在する場合、その値をリストにセット
    		        if (shiftHopeTimeId != null) {
    		            shiftHopeTimeIds.add(shiftHopeTimeId);
    		        } else {
    		            shiftHopeTimeIds.add(null); // shift_hope_time_idが存在しない場合はnull
    		        }
    		    } else {
    		        shiftHopeTimeIds.add(null); // 日付がない場合はnull
    		    }
    		}

    	// datesマップのキー（LocalDate）をリストに格納
    	List<LocalDate> dateKeys = new ArrayList<>(dates.keySet());

    	// 参考時間表示のため
    	List<Store> workTimeDetails = storeDao.getWorkTimes(loginuser.getStoreId());
    	req.setAttribute("workTimeDetails", workTimeDetails);

    	// worker_judgeの値を取得
        boolean workerJudge = worker.isWorkerJudge();

        // worker_judgeがTrueの場合
        if (workerJudge) {
            req.setAttribute("isWorkerJudgeTrue", true);
        } else {
            // worker_judgeがFalseの場合
            req.setAttribute("isWorkerJudgeTrue", false);
        }

        // 月の最初の日と最後の日を取得
        YearMonth yearMonth = YearMonth.of(year, month);
        LocalDate firstDayOfMonth = yearMonth.atDay(1);
        int seru = firstDayOfMonth.getDayOfWeek().getValue();
        req.setAttribute("seru", seru);

    	req.setAttribute("dateKeys", dateKeys);
    	req.setAttribute("dates", dates);
    	req.setAttribute("nullAndTime", nullAndTime);
    	req.setAttribute("shiftHopeTimeIds", shiftHopeTimeIds);
    	req.setAttribute("WorkerName", worker_login.getWorkerName());

    	String countStr = req.getParameter("count");
		int count = Integer.parseInt(countStr);
		req.setAttribute("count", count);


        // 保存後にカレンダー画面を再度表示する
        req.getRequestDispatcher("shift_worker_singup_save.jsp").forward(req, res);
    }
}