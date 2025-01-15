package shiftmaker.main;

import java.util.Arrays;
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

public class ShiftConditionEditAction extends Action{

	public void execute(HttpServletRequest req, HttpServletResponse res) throws Exception {
		// セッションからログイン情報を取得
        HttpSession session = req.getSession();
        Store store = (Store) session.getAttribute("user");

      //ログイン情報から名前を取得して"managerName"として渡す
      		StoreDao sDao = new StoreDao();
      		Store store_name = sDao.get(store.getStoreId());
      		req.setAttribute("managerName", store_name.getManagerName());

        // WorkerDaoとStoreDaoの初期化
        WorkerDao workerDao = new WorkerDao();
        StoreDao storeDao = new StoreDao();

        // リクエストパラメータからworkerIdを取得
        String workerId = req.getParameter("workerId");

        // データベースから該当の従業員情報を取得
        Worker worker = workerDao.get(workerId);

        // ポジションのリスト
        List<Map<String, String>> positions = Arrays.asList(
        	new HashMap<String, String>() {{
        	    put("key", "kitchen");
        	    put("value", "キッチン");
        	}},
        	new HashMap<String, String>() {{
        	    put("key", "hall");
        	    put("value", "ホール");
        	}}
        );

        // リクエストに従業員データを設定
        req.setAttribute("worker", worker);
        req.setAttribute("positions", positions);

        // JSPへフォワード
        req.getRequestDispatcher("shift_condition_edit.jsp").forward(req, res);
	}

}
