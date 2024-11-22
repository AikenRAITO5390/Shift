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

public class ShiftConditionEditAction extends Action{

	public void execute(HttpServletRequest req, HttpServletResponse res) throws Exception {
		// ローカル変数の宣言 1
		// セッションを取得
		HttpSession session = req.getSession();
		// ログインユーザーを取得
		Store store = (Store)session.getAttribute("user");
		// WorkerDaoを初期化
		WorkerDao workerDao = new WorkerDao();
		// StoreDaoを初期化
		StoreDao storeDao = new StoreDao();
		Store stores = new Store();


		//リクエストパラメータ―の取得 2
		String workerId = req.getParameter("workerId");

		// 確認用
		System.out.println(workerId);

		// DBからデータ取得 3
		stores = storeDao.get(store.getStoreId());
		// 従業員IDから従業員インスタンスを取得
		Worker worker = workerDao.get(workerId);
		List<String> list = storeDao.filter(store.getStoreId());

		req.setAttribute("worker", worker);
        req.setAttribute("stores", list);
        req.setAttribute("stores", stores);


		//JSPへフォワード 7
		req.getRequestDispatcher("shift_condition_edit.jsp").forward(req, res);
	}


}
