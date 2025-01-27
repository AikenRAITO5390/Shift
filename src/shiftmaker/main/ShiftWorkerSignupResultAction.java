
package shiftmaker.main;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import bean.Worker;
import dao.WorkerDao;
import tool.Action;


// 希望シフト完了ページへ飛ばす
public class ShiftWorkerSignupResultAction extends Action{

	@Override
	public void execute(HttpServletRequest req, HttpServletResponse res) throws Exception {
		 HttpSession session = req.getSession();
		 Worker worker = (Worker) session.getAttribute("user");
		   WorkerDao wDao = new WorkerDao();
		 Worker worker_login = wDao.get(worker.getWorkerId());

		System.out.println("★★★★★★④★★★★★★");

		System.out.println("完了ページ");


	    // JSPへフォワード
		req.setAttribute("WorkerName", worker_login.getWorkerName());
	    req.getRequestDispatcher("shift_worker_signup_result.jsp").forward(req, res);
	}

}
