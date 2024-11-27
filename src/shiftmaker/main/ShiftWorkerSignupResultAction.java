package shiftmaker.main;

import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.ShiftDao;
import tool.Action;

public class ShiftWorkerSignupResultAction extends Action{

	@Override
	public void execute(HttpServletRequest req, HttpServletResponse res) throws Exception {

		// 初期化
		ShiftDao shiftDao = new ShiftDao();

	    // リクエストパラメータを取得
		String shiftDateString = req.getParameter("shiftDate");  // String型として取得
	    String workTimeId = req.getParameter("workTimeId");
	    String workerId = req.getParameter("workerId");
	    String storeId = req.getParameter("storeId");

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


	    if ("E".equals(workTimeId)) {
	        // カスタム時間を取得
	        String customStartTime = req.getParameter("customStartTime");
	        String customEndTime = req.getParameter("customEndTime");

	        // SHIFTテーブルにカスタム時間を登録
	        shiftDao.insertCustomWorkTime(sqlShiftDate, workerId, storeId, customStartTime, customEndTime);
	    } else {
	        // 通常の勤務時間IDをSHIFTテーブルに登録
	        shiftDao.insertWorkTime(sqlShiftDate, workerId, storeId, workTimeId);
	    }

	    // JSPへフォワード
	    req.getRequestDispatcher(".jsp").forward(req, res);
	}

}
