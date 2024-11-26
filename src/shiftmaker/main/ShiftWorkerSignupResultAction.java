package shiftmaker.main;

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
	    String workTimeId = req.getParameter("workTimeId");
	    String workerId = req.getParameter("workerId");
	    String storeId = req.getParameter("storeId");

	    if ("E".equals(workTimeId)) {
	        // カスタム時間を取得
	        String customStartTime = req.getParameter("customStartTime");
	        String customEndTime = req.getParameter("customEndTime");

	        // SHIFTテーブルにカスタム時間を登録
	        shiftDao.insertCustomWorkTime(workerId, storeId, customStartTime, customEndTime);
	    } else {
	        // 通常の勤務時間IDをSHIFTテーブルに登録
	        shiftDao.insertWorkTime(workerId, storeId, workTimeId);
	    }

	    // JSPへフォワード
	    req.getRequestDispatcher(".jsp").forward(req, res);
	}

}
