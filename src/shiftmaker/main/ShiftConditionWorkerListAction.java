package shiftmaker.main;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import bean.Store;
import bean.Worker;
import dao.StoreDao;
import dao.WorkerDao;
import tool.Action;

public class ShiftConditionWorkerListAction extends Action{

	@Override
	public void execute(HttpServletRequest req, HttpServletResponse res) throws Exception {

		//ログイン情報から名前を取得して"managerName"として渡す
				HttpSession session = req.getSession();//セッション
				StoreDao sDao = new StoreDao();

				Store store_login = (Store)session.getAttribute("user");

				Store store = sDao.get(store_login.getStoreId());
				req.setAttribute("managerName", store.getManagerName());

        // Dao初期化
        WorkerDao workerDao = new WorkerDao();
        // アルバイトのみを取得
        List<Worker> workers = workerDao.getWorkersWithPositionOrScore();

        // 画面のポジションの日本語表示のため
        Map<String, String> positionMapping = new HashMap<>();
        positionMapping.put("kitchen", "キッチン");
        positionMapping.put("hall", "ホール");

	    if (!workers.isEmpty()) {
	        req.setAttribute("workers", workers);
	    } else {
	        req.setAttribute("message", "更新が必要な従業員が見つかりませんでした。");
	    }

	    req.setAttribute("positionMapping", positionMapping);


		//JSPへフォワード 7
		req.getRequestDispatcher("shift_condition_worker_list.jsp").forward(req, res);
	}

}
