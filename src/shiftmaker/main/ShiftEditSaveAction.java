package shiftmaker.main;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Date;
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

// 選択されたものを保存し、カレンダーに反映
public class ShiftEditSaveAction extends Action {

	@Override
	public void execute(HttpServletRequest req, HttpServletResponse res) throws Exception {
	    System.out.println("★★★★★★③★★★★★★");

	    // セッションを取得
	    HttpSession session = req.getSession();
	    Store manager = (Store) session.getAttribute("user");
	    if (manager == null) {
	        System.out.println("Error: storeがセッションにありません");
	        res.sendRedirect("login.jsp");
	        return;
	    }

	    // ログイン名をヘッダーに出す
	    StoreDao storeDao = new StoreDao();
		Store store_login = storeDao.get(manager.getStoreId());



	    // パラメータの取得
	    String workerId = req.getParameter("workerId");
	    String storeId = manager.getStoreId();
	    String shiftDateString = req.getParameter("date");
	    String workTimeId = req.getParameter("workTimeId");

	    // Worker と Store の情報を取得
	    WorkerDao workerDao = new WorkerDao();
	    ShiftDao shiftDao = new ShiftDao();

	    // insert用宣言
	 	String shift_hope_time_id = null;
	 	String work_time_id = null;
	 	String shift_time_start = null;
	 	String shift_time_end = null;

	 	LocalDate todaysDate = LocalDate.now();
		int year = todaysDate.getYear();
		int month = todaysDate.getMonthValue();

		int nextmonth = month +1;

		if(nextmonth == 13){
			nextmonth = 1;
			year = year+1;
		}

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

	    // Store オブジェクトを取得
    	Store store = storeDao.get(manager.getStoreId());

	    // 日付の変換
	    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
	    Date shiftDate = sdf.parse(shiftDateString);
	    java.sql.Date sqlShiftDate = new java.sql.Date(shiftDate.getTime());
	    LocalDate selectedDate = sqlShiftDate.toLocalDate();

	    // シフト情報を取得
	    Shift shift = shiftDao.getShiftScore(worker, sqlShiftDate, store);
	    String shiftScore = String.valueOf(shift.getShiftScore());

	    // カスタム時間の場合の処理
	    String customStartTime = req.getParameter("customStartTime");
	    String customEndTime = req.getParameter("customEndTime");
	    Timestamp timestampCustomStartTime = null;
	    Timestamp timestampCustomEndTime = null;

	    if ("E".equals(workTimeId)) {
	        // カスタム時間を Timestamp に変換
	        String customStartTimeStr = selectedDate + " " + customStartTime + ":00:00";
	        String customEndTimeStr = selectedDate + " " + customEndTime + ":00:00";
	        SimpleDateFormat dateTimeFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	        try {
	            timestampCustomStartTime = new Timestamp(dateTimeFormat.parse(customStartTimeStr).getTime());
	            timestampCustomEndTime = new Timestamp(dateTimeFormat.parse(customEndTimeStr).getTime());
	        } catch (Exception e) {
	            e.printStackTrace();
	            throw new Exception("Invalid custom time format.");
	        }

	        // SHIFTテーブルにカスタム時間を登録
	        shiftDao.insertCustomWorkTime_shiftEdit(sqlShiftDate, workerId, storeId, timestampCustomStartTime, timestampCustomEndTime, shiftScore, shift_hope_time_id, work_time_id, shift_time_start, shift_time_end);
	    } else if ("NONE".equals(workTimeId)) {
	        // なしの場合
	        shiftDao.updatenullShift(workerId, sqlShiftDate);
	    } else {
	        // 通常勤務時間IDを保存
	    	shiftDao.insertWorkTime_shiftEdit(sqlShiftDate, workerId, storeId, workTimeId, shiftScore, shift_hope_time_id, shift_time_start, shift_time_end, timestampCustomStartTime, timestampCustomEndTime);
	    }

	    // カレンダー情報を再取得
	    List<Shift> shifts = shiftDao.getShiftsByStoreId(storeId);
	    Map<String, Map<LocalDate, Shift>> shiftMap = new HashMap<>();
	    for (Shift shift2 : shifts) {
	    	String workerId2 = shift2.getWorker().getWorkerId();
	        LocalDate shiftDate2 = shift2.getShiftDate().toLocalDate();
	        shiftMap.computeIfAbsent(workerId2, k -> new HashMap<>()).put(shiftDate2, shift2);
	    }

	    // 従業員リスト
	    List<Worker> worker_list = workerDao.filter(manager);

	    // カレンダーの再取得
	    CalendeCreate calende = new CalendeCreate();
	    List<LocalDate> dates = calende.Calender(year, nextmonth);
	    dates.removeIf(Objects::isNull);

	    // 参考時間表示のため
    	List<Store> workTimeDetails = storeDao.getWorkTimes(manager.getStoreId());
    	req.setAttribute("workTimeDetails", workTimeDetails);


	    req.setAttribute("shiftMap", shiftMap);
	    req.setAttribute("shifts", shifts);
	    req.setAttribute("worker_list", worker_list);
	    req.setAttribute("dates", dates);
	    req.setAttribute("managerName", store_login.getManagerName());
	    String countStr = req.getParameter("count");
		int count = Integer.parseInt(countStr);
		System.out.println("count：" + count);
		req.setAttribute("count", count);

	    // JSPに転送
	    req.getRequestDispatcher("shift_edit_save.jsp").forward(req, res);
	}

}
