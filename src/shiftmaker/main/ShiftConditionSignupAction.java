package shiftmaker.main;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bean.Worker;
import dao.WorkerDao;
import tool.Action;

public class ShiftConditionSignupAction extends Action {

	@Override
	public void execute(HttpServletRequest req, HttpServletResponse res) throws Exception {

		// WorkerDaoを初期化
	    WorkerDao workerDao = new WorkerDao();
	    //
	    List<Worker> workers = workerDao.getWorkersWithNullPositionOrScore();

	    if (!workers.isEmpty()) {
	        req.setAttribute("workers", workers);
	    } else {
	        req.setAttribute("message", "更新が必要な従業員が見つかりませんでした。");
	    }

	    // JSPにフォワード
	    req.getRequestDispatcher("shift_condition_signup.jsp").forward(req, res);
	}
}