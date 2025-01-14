package shiftmaker.main;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import bean.BBS;
import bean.Worker;
import dao.BBSDao;
import tool.Action;

public class BbsDeleteWorkerAction extends Action{

	 public void execute(HttpServletRequest req, HttpServletResponse res) throws Exception {
	    	System.out.println("①★★★★★★managerdelete★★★★★★");
	        // ローカル変数の宣言
	        BBSDao bDao = new BBSDao(); // BBS Dao
	        HttpSession session = req.getSession(); // セッション
	        Worker worker = (Worker) session.getAttribute("user"); // ログインユーザーを取得

	        // リクエストパラメータの取得
	        String BBS_ID = req.getParameter("BBS_ID");
	        String WORKER_ID = req.getParameter("Worker_ID");


	        BBS bbs = bDao.get(BBS_ID);
	        System.out.println("取得したbbs: " + bbs);

	        // コンソールで確認
	        System.out.println("取得したBBS_ID: " + BBS_ID);
	        System.out.println("取得したWORKER_ID: " + WORKER_ID);


	        req.setAttribute("BBS_ID", BBS_ID);
	        req.setAttribute("bbs", bbs);

	        // 削除完了後に一覧画面へリダイレクト
	       // res.sendRedirect("bbs_delete.jsp");

	        req.getRequestDispatcher("bbs_delete_worker.jsp").forward(req, res);
	    }

}
