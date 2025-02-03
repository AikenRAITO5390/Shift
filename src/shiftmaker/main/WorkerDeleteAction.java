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

public class WorkerDeleteAction extends Action {

    @Override
    public void execute(HttpServletRequest req, HttpServletResponse res) throws Exception {

        // ローカル変数の宣言
        WorkerDao wDao = new WorkerDao(); // WorkerDao
        StoreDao sDao = new StoreDao(); // StoreDao
        HttpSession session = req.getSession(); // セッション
        Store store = (Store) session.getAttribute("user"); // ログインユーザーを取得

    	//ログイン情報から名前を取得して"managerName"として渡す

		Store stores = sDao.get(store.getStoreId());
		req.setAttribute("managerName", stores.getManagerName());

        // リクエストパラメータの取得
        String WORKER_ID = req.getParameter("WORKER_ID");


        // 選択された従業員IDから従業員インスタンスを取得
        Worker worker = wDao.get(WORKER_ID);


        // ログインユーザーの店舗IDをもとに店舗の一覧を取得
        List<Store> list = sDao.filterStore(store.getStoreId());

        // リクエストにデータをセット
        req.setAttribute("stores", list);
        req.setAttribute("worker", worker);


        // フォワード
        req.getRequestDispatcher("worker_delete_ok.jsp").forward(req, res);
    }
}