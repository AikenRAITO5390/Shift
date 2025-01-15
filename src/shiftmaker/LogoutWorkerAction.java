package shiftmaker;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import bean.Worker;
import dao.WorkerDao;
import tool.Action;

public  class LogoutWorkerAction extends Action{


	@Override
	public void execute(HttpServletRequest req, HttpServletResponse res) throws Exception {
		HttpSession session=req.getSession();
        Worker worker = (Worker) session.getAttribute("user");
        Worker workers = new Worker();
        WorkerDao wDao = new WorkerDao();
        workers = wDao.get(worker.getWorkerId());
    	//ログイン情報から名前を取得して"managerName"として渡す
		req.setAttribute("WorkerName", workers.getWorkerName());
		//ローカル変数の宣言 1
		//リクエストパラメータ―の取得 2
		//なし
		//DBからデータ取得 3
		//なし
		//ビジネスロジック 4
		//なし
		//DBへデータ保存 5
		//なし
		//レスポンス値をセット 6
		//session.invalidate();
		System.out.println("b");
		//JSPへフォワード 7
		req.getRequestDispatcher("logout_ok2.jsp").forward(req, res);
	}

}
