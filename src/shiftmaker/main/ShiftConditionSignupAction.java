package shiftmaker.main;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bean.Worker;
import dao.WorkerDao;
import tool.Action;

public class ShiftConditionSignupAction extends Action {

    @Override
    public void execute(HttpServletRequest req, HttpServletResponse res) throws Exception {
        // 1. リクエストパラメータを取得
        String workerId = req.getParameter("worker_id");

        // 2. DBから対象の従業員情報を取得
        WorkerDao workerDao = new WorkerDao();
        Worker worker = workerDao.get(workerId);

        if (worker != null) {
            // 3. 従業員情報をリクエストスコープに設定
            req.setAttribute("worker", worker);
        } else {
            req.setAttribute("message", "従業員情報が見つかりませんでした。");
        }

        // 4. JSPへフォワード
        req.getRequestDispatcher("shift_condition_signup.jsp").forward(req, res);
    }
}