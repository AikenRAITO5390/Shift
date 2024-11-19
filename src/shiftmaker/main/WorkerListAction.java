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

        String WORKER_ID = req.getParameter("f1");
        String WORKER_NAME = req.getParameter("f2");
        String WORKER_DATE = req.getParameter("f3");
        String WORKER_ADDRESS = req.getParameter("f4");
        String WORKER_TEL = req.getParameter("f5");
        String WORKER_PASSWORD = req.getParameter("f6");
        String STORE_ID = req.getParameter("f7");
        String STORE_NAME = req.getParameter("f8");
        String WORK_TIME_ID = req.getParameter("f9");

        WorkerDao wDao = new WorkerDao();
        StoreDao sDao = new StoreDao();


        List<String> list = sDao.filter(store.getStoreName());
        //stores = sDao.filter(store, WORK_TIME_ID);
        List<Store> stores = sDao.filter(store, WORK_TIME_ID);

		// リクエストに学生リストをセット

		List<Worker> workers = wDao.getWorkersByStoreId(STORE_ID); // workers変数を宣言し、初期化
		// リクエストにデータをセット

		req.setAttribute("stores", stores);


		//JSPへフォワード 7
		req.getRequestDispatcher("worker_list.jsp").forward(req, res);
	}
}

