package shiftmaker.main;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bean.Worker;
import dao.WorkerDao;
import tool.Action;

public class ShiftConditionWorkerListAction extends Action{

	@Override
	public void execute(HttpServletRequest req, HttpServletResponse res) throws Exception {

        // Dao初期化
        WorkerDao workerDao = new WorkerDao();
        // アルバイトのみを取得
        List<Worker> workers = workerDao.getWorkersWithPositionOrScore();

	    if (!workers.isEmpty()) {
	        req.setAttribute("workers", workers);
	    } else {
	        req.setAttribute("message", "更新が必要な従業員が見つかりませんでした。");
	    }


		//JSPへフォワード 7
		req.getRequestDispatcher("shift_condition_worker_list.jsp").forward(req, res);
	}

}
