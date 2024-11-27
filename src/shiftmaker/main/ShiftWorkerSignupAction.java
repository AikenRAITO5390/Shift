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
		List<LocalDate> dates = calende.Calender(2024, 12);

		LocalDate todaysDate = LocalDate.now();// LcalDateインスタンスを取得
		int year = todaysDate.getYear();// 現在の年を取得
		int month = todaysDate.getMonthValue();// 現在の月を取得


		// リクエストにカレンダーをセット
		req.setAttribute("dates", dates);
		// リクエストに従業員データをセット
		req.setAttribute("worker", worker);

		req.setAttribute("year", year);
		req.setAttribute("month", month);

        // 6. JSPへフォワード
        req.getRequestDispatcher("shift_worker_signup.jsp").forward(req, res);
    }


}
