package shiftmaker.main;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import bean.Shift;
import bean.Store;
import bean.Worker;
import dao.ShiftDao;
import dao.WorkerDao;
import tool.Action;
import tool.CalendeCreate;

public class ShiftEditAction extends Action{

	@Override
    public void execute(HttpServletRequest req, HttpServletResponse res) throws Exception {

		System.out.println("★★★★★★①★★★★★★");

		// セッションを取得
        HttpSession session = req.getSession();

        int count = 1;

        // Daoを初期化
        ShiftDao shiftDao = new ShiftDao();
        WorkerDao workerDao  = new WorkerDao();
        CalendeCreate calende = new CalendeCreate();

        LocalDate todaysDate = LocalDate.now();
		int year = todaysDate.getYear();
		int month = todaysDate.getMonthValue();

		int nextmonth = month +1;

		if(nextmonth == 13){
			nextmonth = 1;
			year = year+1;
		}

		// カレンダーを生成
		List<LocalDate> dates = calende.Calender(year, nextmonth);
		dates.removeIf(Objects::isNull);

        // ログインユーザーを取得
     	Store manager = (Store)session.getAttribute("user");
        if (manager == null) {
            System.out.println("Error: storeがセッションにありません");
            res.sendRedirect("login.jsp");
            return;
        }

        // 店舗IDを取得
        String storeId = manager.getStoreId();
        System.out.println("storeId: " + storeId);

        //従業員情報一覧を格納するリスト
      	List<Worker> worker_list = new ArrayList<>();
      	//従業員情報一覧を取得
      	worker_list = workerDao.filter(manager);

        // 店舗IDに基づいてシフト情報を取得
        List<Shift> shifts = shiftDao.getShiftsByStoreId(storeId);

        // 確認用
        for (Shift aaa : shifts){
            System.out.println("shifts: " + aaa.getWorkTimeId());
            System.out.println("shifts: " + aaa.getShiftTimeStart());
            System.out.println("shifts: " + aaa.getShiftTimeEnd());
        }

        // 従業員IDと日付をキーにシフト情報を格納するマップ
        Map<String, Map<LocalDate, Shift>> shiftMap = new HashMap<>();

        // シフトデータを整理
        for (Shift shift : shifts) {
        	// workeridのnullチェック
            if (shift.getWorker() == null) {
                System.out.println("Worker is null for shift: " + shift);
                continue;
            }
            String workerId = shift.getWorker().getWorkerId();


            // 日付を取得
            LocalDate shiftDate = shift.getShiftDate().toLocalDate();
            System.out.println("shiftDate: " + shiftDate);
            String workTimeId = shift.getWorkTimeId();
            System.out.println("workTimeId: " + workTimeId);

            shiftMap.computeIfAbsent(workerId, k -> new HashMap<>()).put(shiftDate, shift);


        }


        // リクエストにデータをセット
        req.setAttribute("shiftMap", shiftMap);
        req.setAttribute("dates", dates);
        req.setAttribute("shifts", shifts);
        req.setAttribute("worker_list", worker_list);
        req.setAttribute("count", count);


        // JSPへフォワード
        req.getRequestDispatcher("shift_edit.jsp").forward(req, res);
    }

}
