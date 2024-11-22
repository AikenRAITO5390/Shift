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

public class ShiftConditionEditResultAction extends Action{

	@Override
	public void execute(HttpServletRequest req, HttpServletResponse res) throws Exception {

		//ローカル変数の宣言 1
		// Daoを初期化
		WorkerDao workerDao = new WorkerDao();
		StoreDao storeDao = new StoreDao();

		// セッションを取得
		HttpSession session = req.getSession();
		// ログインユーザーを取得
		Store store = (Store)session.getAttribute("user");


		// リクエストパラメータ―の取得 2
		String worker_id= req.getParameter("worker_id");
		String worker_position = req.getParameter("worker_position");
		String worker_score = req.getParameter("worker_score");


		// DBからデータ取得 3
		// 従業員IDから従業員インスタンスを取得
		Worker worker = workerDao.get(worker_id);
		List<String> list = storeDao.filter(store.getStoreId());

		worker.setWorkerPosition(worker_position);
        worker.setWorkerScore(worker_score);

        // データベースの更新
        workerDao.save(worker);



		req.getRequestDispatcher("shift_condition_edit_result.jsp").forward(req, res);
	}


}
