package shiftmaker.main;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bean.Worker;
import dao.WorkerDao;
import tool.Action;

public class WorkerDetailAction extends Action {
    @Override
    public void execute(HttpServletRequest req, HttpServletResponse res) throws Exception {
        String workerId = req.getParameter("WORKER_ID");
        WorkerDao wDao = new WorkerDao();
        Worker worker = wDao.get(workerId);

        System.out.println("動いてますよ～！");
        System.out.println("worker:" + workerId);


        if (worker != null) {
            req.setAttribute("worker", worker);
            req.getRequestDispatcher("worker_detail.jsp").forward(req, res);
        } else {
            req.setAttribute("error", "従業員が見つかりませんでした。");
            req.getRequestDispatcher("worker_list.jsp").forward(req, res);
        }
    }
}