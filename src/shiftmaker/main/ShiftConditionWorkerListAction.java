package shiftmaker.main;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

        // 画面のポジションの日本語表示のため
        Map<String, String> positionMapping = new HashMap<>();
        positionMapping.put("kitchen", "キッチン");
        positionMapping.put("hall", "ホール");

	    if (!workers.isEmpty()) {
	        req.setAttribute("workers", workers);
	    } else {
	        req.setAttribute("message", "更新が必要な従業員が見つかりませんでした。");
	    }

	    req.setAttribute("positionMapping", positionMapping);


		//JSPへフォワード 7
		req.getRequestDispatcher("shift_condition_worker_list.jsp").forward(req, res);
	}

}
