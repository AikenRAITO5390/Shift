package shiftmaker.main;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import bean.Store;
import bean.Worker;
import dao.StoreDao;
import dao.WorkerDao;
import tool.Action;

public class WorkerDetailAction extends Action {
    @Override
    public void execute(HttpServletRequest req, HttpServletResponse res) throws Exception {
        String workerId = req.getParameter("WORKER_ID");
        WorkerDao wDao = new WorkerDao();
        Worker worker = wDao.get(workerId);

		HttpSession session = req.getSession();//セッション
		StoreDao sDao = new StoreDao();

		Store store_login = (Store)session.getAttribute("user");

		Store store = sDao.get(store_login.getStoreId());



        if (worker != null) {
            req.setAttribute("worker", worker);
            req.setAttribute("managerName", store.getManagerName());
            req.getRequestDispatcher("worker_detail.jsp").forward(req, res);
        } else {
            req.setAttribute("error", "従業員が見つかりませんでした。");
            req.setAttribute("managerName", store.getManagerName());
            req.getRequestDispatcher("worker_list.jsp").forward(req, res);
        }
    }
}