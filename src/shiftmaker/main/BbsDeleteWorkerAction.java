package shiftmaker.main;

import java.util.Optional;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import bean.BBS;
import bean.Worker;
import dao.BBSDao;
import dao.WorkerDao;
import tool.Action;

public class BbsDeleteWorkerAction extends Action{

	 public void execute(HttpServletRequest req, HttpServletResponse res) throws Exception {
	    	System.out.println("①★★★★★★managerdelete★★★★★★");
	        // ローカル変数の宣言
	        BBSDao bDao = new BBSDao(); // BBS Dao
	        HttpSession session = req.getSession(); // セッション
	        Worker worker = (Worker) session.getAttribute("user"); // ログインユーザーを取得
	        WorkerDao wDao = new WorkerDao();
	        Worker worker_login = wDao.get(worker.getWorkerId());

	        // リクエストパラメータの取得
	        String BBS_ID = req.getParameter("BBS_ID");
	        String WORKER_ID = req.getParameter("Worker_ID");


	        BBS bbs = bDao.get_BbsId(BBS_ID);
	        System.out.println("取得したbbs: " + bbs);

	        Worker worker_delete = bbs.getWorker();
	        String worker_name = Optional.ofNullable(worker_delete)
	                                     .map(Worker::getWorkerName)
	                                     .orElse(null);

	        String user = bbs.getManager();

	        String text = bbs.getBbsText();

	        // コンソールで確認
	        System.out.println("取得したBBS_ID: " + BBS_ID);
	        System.out.println("取得したWORKER_ID: " + WORKER_ID);

	    if (worker_name != null) {
	        req.setAttribute("BBS_ID", BBS_ID);
	        req.setAttribute("bbs", bbs);
	        req.setAttribute("text", text);
	        req.setAttribute("user", worker_name);
	        req.setAttribute("WorkerName", worker_login.getWorkerName());

	        // 削除完了後に一覧画面へリダイレクト
	       // res.sendRedirect("bbs_delete.jsp");

	        req.getRequestDispatcher("bbs_delete_worker.jsp").forward(req, res);
	    }else{
	    	 req.setAttribute("BBS_ID", BBS_ID);
	         req.setAttribute("bbs", bbs);
	         req.setAttribute("text", text);
	         req.setAttribute("user", user);
	         req.setAttribute("WorkerName", worker_login.getWorkerName());


	         System.out.println("取得したuser: " + user);
	         System.out.println("取得したtext: " + text);
	         req.getRequestDispatcher("bbs_delete_worker.jsp").forward(req, res);
	    }
	 }

}
