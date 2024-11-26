package shiftmaker;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import bean.Store;
import bean.Worker;
import dao.StoreDao;
import dao.WorkerDao;
import tool.Action;

public class LoginAction extends Action {

    @Override
    public void execute(HttpServletRequest req, HttpServletResponse res) throws Exception {
        login(req, res);
    }

    public void login(HttpServletRequest req, HttpServletResponse res) throws Exception {
        HttpSession session = req.getSession();
        StoreDao sDao = new StoreDao();
        WorkerDao wDao = new WorkerDao();
        String storeId = req.getParameter("storeId");
        String workerId = req.getParameter("workerId");
        String password = req.getParameter("password");

        Store store = sDao.login(storeId, password);
        Worker worker = wDao.login(workerId, password);

        if (store != null) {
            session.setAttribute("store", store);
        }

        if (worker != null) {
            session.setAttribute("worker", worker);
        }

        if (store != null || worker != null) {
            res.sendRedirect("bbs_list.jsp");
        } else {
            res.sendRedirect("login.jsp");
        }
    }
}