package shiftmaker.main;

import java.sql.Date;
import java.time.LocalDate;
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
import dao.StoreDao;
import dao.WorkerDao;
import tool.Action;
import tool.CalendeCreate;

public class ShiftDeleteAction extends Action{

	@Override
    public void execute(HttpServletRequest req, HttpServletResponse res) throws Exception {

        // セッションを取得
        HttpSession session = req.getSession();

        // countがセッションにすでに保存されていない場合、初期値として1を設定
        Integer count = (Integer) session.getAttribute("count");
        if (count == null) {
            count = 1;  // 初回は1に設定
            session.setAttribute("count", count);  // セッションに保存
        }

        // 現在の年月を取得
        String yearStr = req.getParameter("year");
        String monthStr = req.getParameter("month");

        // Daoを初期化
        ShiftDao shiftDao = new ShiftDao();
        WorkerDao workerDao = new WorkerDao();
        StoreDao storeDao = new StoreDao();

        LocalDate todaysDate = LocalDate.now();
        int year = (yearStr != null) ? Integer.parseInt(yearStr) : todaysDate.getYear();
        int month = (monthStr != null) ? Integer.parseInt(monthStr) : todaysDate.getMonthValue();

        // 前月・次月の計算
        int prevMonth = (month == 1) ? 12 : month - 1;
        int nextMonth = (month == 12) ? 1 : month + 1;
        int prevYear = (month == 1) ? year - 1 : year;
        int nextYear = (month == 12) ? year + 1 : year;

        // カレンダー生成
        CalendeCreate calende = new CalendeCreate();
        List<LocalDate> dates = calende.Calender(year, month);
        dates.removeIf(Objects::isNull);

        req.setAttribute("dates", dates);
        req.setAttribute("year", year);
        req.setAttribute("month", month);
        req.setAttribute("prevYear", prevYear);
        req.setAttribute("prevMonth", prevMonth);
        req.setAttribute("nextYear", nextYear);
        req.setAttribute("nextMonth", nextMonth);

        // ログインユーザーを取得
        Store manager = (Store) session.getAttribute("user");
        if (manager == null) {
            res.sendRedirect("login.jsp");
            return;
        }

        // 店舗IDを取得
        String storeId = manager.getStoreId();

        // 従業員情報一覧を取得
        List<Worker> worker_list = workerDao.filter(manager);

        // 店舗IDに基づいてシフト情報を取得
        List<Shift> shifts = shiftDao.getShiftsByStoreId(storeId);

        // シフトデータを整理
        Map<String, Map<LocalDate, Shift>> shiftMap = new HashMap<>();
        Map<LocalDate, Map<String, Integer>> shiftScores = new HashMap<>();

        for (Shift shift : shifts) {
            String workerId = shift.getWorker().getWorkerId();
            LocalDate shiftDate = shift.getShiftDate().toLocalDate();
            String shiftScore = shiftDao.shiftScoreGet2(Date.valueOf(shiftDate), storeId, workerId);

            int shiftScore2 = 0;
            try {
                shiftScore2 = Integer.parseInt(shiftScore);
            } catch (NumberFormatException e) {
                System.out.println("shiftScore の変換に失敗しました: " + shiftScore);
            }

            shiftMap.computeIfAbsent(workerId, k -> new HashMap<>()).put(shiftDate, shift);
            shiftScores.computeIfAbsent(shiftDate, k -> new HashMap<>()).put(workerId, shiftScore2);
        }

        // 店舗の勤務時間を取得
        List<Store> workTimeDetails = storeDao.getWorkTimes(manager.getStoreId());
        req.setAttribute("workTimeDetails", workTimeDetails);

        // countを更新
        count++;

        // リクエストにデータをセット
        req.setAttribute("shiftMap", shiftMap);
        req.setAttribute("shiftScores", shiftScores);
        req.setAttribute("worker_list", worker_list);
        req.setAttribute("count", count);

        // JSPへフォワード
        req.getRequestDispatcher("shift_delete.jsp").forward(req, res);
    }

}
