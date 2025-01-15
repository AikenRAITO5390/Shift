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

public class ShiftConditionSignupAction extends Action {

	@Override
	public void execute(HttpServletRequest req, HttpServletResponse res) throws Exception {

		// WorkerDaoを初期化
	    WorkerDao workerDao = new WorkerDao();
	    // ポジションと点数がnullのアルバイトを取得
	    List<Worker> workers = workerDao.getWorkersWithNullPositionOrScore();

		HttpSession session = req.getSession();//セッション
		StoreDao sDao = new StoreDao();

		Store store_login = (Store)session.getAttribute("user");

		Store store = sDao.get(store_login.getStoreId());
		req.setAttribute("managerName", store.getManagerName());

	    if (!workers.isEmpty()) {
	        req.setAttribute("workers", workers);
	    } else {
	        req.setAttribute("message", "更新が必要な従業員が見つかりませんでした。");
	    }

	    // JSPにフォワード
	    req.getRequestDispatcher("shift_condition_signup.jsp").forward(req, res);
	}
}