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

public class WorkerUpdateAction extends Action {

	public void execute(HttpServletRequest req, HttpServletResponse res) throws Exception {
		//ローカル変数の宣言 1
		WorkerDao wDao = new WorkerDao();//学生Dao
		HttpSession session = req.getSession();//セッション
		Store store = (Store)session.getAttribute("user");// ログインユーザーを取得
		StoreDao sDao = new StoreDao();// クラス番号Daoを初期化
		Store stores = new Store();


		//リクエストパラメータ―の取得 2
//		String no = req.getParameter("student_no");//学番
		String workerId = req.getParameter("workerId");//学番

		System.out.println(workerId);



		//DBからデータ取得 3
		stores = sDao.get(store.getStoreId());
		Worker worker = wDao.get(workerId);//学生番号から学生インスタンスを取得
		List<String> list = sDao.filter(store.getStoreId());//ログインユーザーの学校コードをもとにクラス番号の一覧を取得

		req.setAttribute("worker", worker);
        req.setAttribute("stores", list);
        req.setAttribute("stores", stores);


		//JSPへフォワード 7
		req.getRequestDispatcher("worker_update.jsp").forward(req, res);
	}
}

