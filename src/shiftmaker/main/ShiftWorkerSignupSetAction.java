package shiftmaker.main;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import bean.Store;
import bean.Worker;
import dao.ShiftDao;
import dao.StoreDao;
import dao.WorkerDao;
import tool.Action;

public class ShiftWorkerSignupSetAction extends Action{

	@Override
    public void execute(HttpServletRequest req, HttpServletResponse res) throws Exception {

		// セッションを取得
		HttpSession session = req.getSession();
		// WorkerDaoを初期化
		WorkerDao workerDao = new WorkerDao();
		// ShiftDaoを初期化
		ShiftDao shiftDao = new ShiftDao();
		// StoreDaoを初期化
		StoreDao storeDao = new StoreDao();
		// リクエストパラメータからworkerIdを取得
        String workerId = req.getParameter("workerId");
        // データベースから該当の従業員情報を取得
        Worker worker = workerDao.get(workerId);
        // 店舗IDをリクエストパラメータから取得
        String storeId = req.getParameter("storeId");
        // ログインユーザーを取得
     	Worker manager = (Worker)session.getAttribute("user");

     	// 営業時間の取得
     	String store_time_start = storeDao.TimeStartGet(manager.getStoreId());
		String store_time_end = storeDao.TimeEndGet(manager.getStoreId());

		// 時刻を整数値に変換 (時間部分のみを抽出)
	    int startHour = Integer.parseInt(store_time_start.split(":")[0]);
	    int endHour = Integer.parseInt(store_time_end.split(":")[0]);

        // 店舗の勤務時間を取得
        List<Store> workTimes = storeDao.getWorkTimes(manager.getStoreId());

        // 確認用
        System.out.print(workTimes);
        System.out.print(manager.getStoreId());

		// リクエストにデータをセット
		req.setAttribute("worker", worker);
		req.setAttribute("workTimes", workTimes);
		req.setAttribute("store_time_start", startHour);
		req.setAttribute("store_time_end", endHour);

        // 6. JSPへフォワード
        req.getRequestDispatcher("shift_worker_signup_set.jsp").forward(req, res);
    }


}
