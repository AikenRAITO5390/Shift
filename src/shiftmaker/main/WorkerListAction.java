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



public class WorkerListAction extends Action {
    public void execute(HttpServletRequest req, HttpServletResponse res) throws Exception {
        HttpSession session = req.getSession();
        Store store = (Store) session.getAttribute("user");




        WorkerDao wDao = new WorkerDao();
        StoreDao sDao = new StoreDao();


        List<Store> stores = sDao.filterStore(store.getStoreId());
		List<Worker> workers = wDao.filter(store); // workers変数を宣言し、初期化
		// リクエストにデータをセット

		req.setAttribute("workers", workers);
		req.setAttribute("stores", stores);

		System.out.println("Stores: " + stores);
        System.out.println("Workers: " + workers);

        req.getRequestDispatcher("worker_list.jsp").forward(req, res);
    }
}

