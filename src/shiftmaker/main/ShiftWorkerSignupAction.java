package shiftmaker.main;

import java.time.LocalDate;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bean.Worker;
import dao.WorkerDao;
import tool.Action;
import tool.CalendeCreate;

public class ShiftWorkerSignupAction extends Action{

	@Override
    public void execute(HttpServletRequest req, HttpServletResponse res) throws Exception {

		// WorkerDaoを初期化
		WorkerDao workerDao = new WorkerDao();
		// リクエストパラメータからworkerIdを取得
        String workerId = req.getParameter("workerId");
        // データベースから該当の従業員情報を取得
        Worker worker = workerDao.get(workerId);


		// カレンダーを初期化
		CalendeCreate calende = new CalendeCreate();
		// カレンダーを作成
		List<LocalDate> dates = calende.Calender(2024, 11);


		// リクエストにカレンダーをセット
		req.setAttribute("dates", dates);
		// リクエストに従業員データをセット
		req.setAttribute("worker", worker);

        // 6. JSPへフォワード
        req.getRequestDispatcher("shift_worker_signup.jsp").forward(req, res);
    }


}
