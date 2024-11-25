package shiftmaker.main;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import bean.Store;
import bean.Worker;
import dao.StoreDao;
import dao.WorkerDao;
import tool.Action;

public class WorkerUpdateExecuteAction extends Action {
	@Override
	public void execute(HttpServletRequest req, HttpServletResponse res) throws Exception {
		//ローカル変数の宣言 1
		WorkerDao wDao = new WorkerDao();
		StoreDao sDao = new StoreDao();


		HttpSession session = req.getSession();//セッション
		Store store = (Store)session.getAttribute("user");// ログインユーザーを取得object型で取り出されるためTeacher型にキャストする




		//リクエストパラメータ―の取得 2
		String WORKER_ID= req.getParameter("WORKER_ID");
		String WORKER_NAME = req.getParameter("WORKER_NAME");
		String WORKER_DATE = req.getParameter("WORKER_DATE");
		String WORKER_ADDRESS = req.getParameter("WORKER_ADDRESS");
		String WORKER_TEL = req.getParameter("WORKER_TEL");
		String WORKER_PASSWORD = req.getParameter("WORKER_PASSWORD");
		String STORE_NAME = req.getParameter("STORE_NAME");

		System.out.println(WORKER_ID);
	    System.out.println(WORKER_NAME);
	    System.out.println(WORKER_DATE);
	    System.out.println(WORKER_ADDRESS);
	    System.out.println(WORKER_TEL);



		//DBからデータ取得 3
		Worker worker = wDao.get(WORKER_ID);// 学生番号から学生インスタンスを取得
		List<String> list = sDao.filter(store.getStoreId());//ログインユーザーの学校コードをもとにクラス番号の一覧を取得

		worker.setWorkerName(WORKER_NAME);
        worker.setWorkerDate(WORKER_DATE);
        worker.setWorkerAddress(WORKER_ADDRESS);
        worker.setWorkerTel(WORKER_TEL);
        worker.setWorkerPassword(WORKER_PASSWORD);
        store.setStoreName(STORE_NAME);



        // データベースの更新
        wDao.save(worker);



		req.getRequestDispatcher("worker_updata_done.jsp").forward(req, res);
	}
}

