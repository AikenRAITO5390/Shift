package shiftmaker.main;

import java.sql.Date;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import bean.Shift;
import bean.Store;
import dao.ShiftDao;
import dao.StoreDao;
import tool.Action;

public class ShiftSelectAction extends Action{

	@Override
	public void execute(HttpServletRequest req, HttpServletResponse res) throws Exception {
		//ローカル変数の宣言 1
		HttpSession session = req.getSession();
		//ログインユーザ情報の取得
		Store manager = (Store)session.getAttribute("user");
		StoreDao stDao = new StoreDao();//StoreDao初期化
		ShiftDao shDao = new ShiftDao();//ShiftDao初期化
		List<Shift> shift_list = new ArrayList<>();
		String work_time_start = stDao.TimeStartGet(manager.getStoreId());
		String work_time_end = stDao.TimeEndGet(manager.getStoreId());
		System.out.println(work_time_end+work_time_start);

		LocalDate localDate = LocalDate.of(2024, 11, 14);
		// java.sql.Dateに変換
		Date shift_date = Date.valueOf(localDate);
		shift_list = shDao.filter(stDao.get(manager.getStoreId()), shift_date);
		System.out.println(shift_list);

		for(Shift shift: shift_list){
			String availableFrom = null;
			String availableTo	= null;
			int maxHours;


		}

		req.setAttribute("shift_list", shift_list);
		//shift_select.jspに遷移
		req.getRequestDispatcher("shift_select.jsp").forward(req,res);
	}

	private static int timeToMinutes(String time) {
        String[] parts = time.split(":");
        return Integer.parseInt(parts[0]) * 60 + Integer.parseInt(parts[1]);
    }

	// バイトの情報を表すクラス
    static class WorkerShift {
        String name;         // バイトの名前
        String role;         // 役割（キッチン or ホール）
        String availableFrom; // 勤務可能開始時刻
        String availableTo;   // 勤務可能終了時刻
        int maxHours;         // 最大勤務時間
        List<String> assignedShifts = new ArrayList<>(); // 割り振られたシフト

        WorkerShift(String name, String role, String availableFrom, String availableTo, int maxHours) {
            this.name = name;
            this.role = role;
            this.availableFrom = availableFrom;
            this.availableTo = availableTo;
            this.maxHours = maxHours;
        }
    }


}
