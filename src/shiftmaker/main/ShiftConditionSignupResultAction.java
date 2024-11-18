package shiftmaker.main;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bean.Worker;
import dao.WorkerDao;
import tool.Action;

public class ShiftConditionSignupResultAction extends Action {

    @Override
    public void execute(HttpServletRequest req, HttpServletResponse res) throws Exception {
        // 1. リクエストパラメータを取得
        String workerId = req.getParameter("worker_id");
        String workerPosition = req.getParameter("worker_position");
        String workerScore = req.getParameter("worker_score");

        // 2. DBから対象の従業員情報を取得
        WorkerDao workerDao = new WorkerDao();
        Worker worker = workerDao.get(workerId);

        // 従業員がNULLではない場合
        if (worker != null) {
            // 3. 従業員情報に新しいポジションと点数を設定
            worker.setWorkerPosition(workerPosition);
            worker.setWorkerScore(workerScore);

            // 4. 更新を保存
            boolean isSuccess = workerDao.save(worker);

            // 5. 更新結果に応じたメッセージ設定
            if (isSuccess) {
                req.setAttribute("message", "シフト条件が正常に登録されました。");
            } else {
                req.setAttribute("message", "登録に失敗しました。");
            }
        } else {
            req.setAttribute("message", "従業員情報が見つかりませんでした。");
        }

        // 6. JSPへフォワード
        req.getRequestDispatcher("shift_condition_signup_result.jsp").forward(req, res);
    }
}