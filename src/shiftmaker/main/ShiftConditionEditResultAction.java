package shiftmaker.main;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bean.Worker;
import dao.WorkerDao;
import tool.Action;

public class ShiftConditionEditResultAction extends Action{

	@Override
	public void execute(HttpServletRequest req, HttpServletResponse res) throws Exception {

		// パラメータを取得
        String workerId = req.getParameter("workerId");
        String workerPosition = req.getParameter("workerPosition");
        String workerScore = req.getParameter("workerScore");

        // WorkerDaoの初期化
        WorkerDao workerDao = new WorkerDao();
        Worker worker = workerDao.get(workerId);

        // データを更新
        worker.setWorkerPosition(workerPosition);
        worker.setWorkerScore(workerScore);

        // 保存処理
        boolean success = workerDao.save(worker);

        // 結果によってメッセージを設定
        if (success) {
            req.setAttribute("message", "変更されました。");
        } else {
            req.setAttribute("message", "変更に失敗しました。");
        }

		req.getRequestDispatcher("shift_condition_edit_result.jsp").forward(req, res);
	}


}
