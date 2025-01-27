package shiftmaker.main;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import bean.Worker;
import dao.WorkerDao;
import tool.Action;

public class ShiftChooseWorkAction extends Action {

	@Override
	public void execute(HttpServletRequest req,HttpServletResponse res)throws Exception{

		// セッションを取得
		HttpSession session = req.getSession();//セッション
		WorkerDao sDao = new WorkerDao();

		// ログインユーザーを取得
     	Worker loginuser = (Worker)session.getAttribute("user");
     	// 確認用
    	System.out.println("loginuser：" + loginuser);

		Worker worker = sDao.get(loginuser.getWorkerId());
		req.setAttribute("WorkerName", worker.getWorkerName());

		//JSPへフォワード 7
		req.getRequestDispatcher("shift_choose_work.jsp").forward(req, res);
	}

}
