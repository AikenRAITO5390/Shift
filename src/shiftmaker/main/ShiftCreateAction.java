package shiftmaker.main;

//import java.util.Date;
import java.sql.Date;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import bean.Shift;
import bean.Store;
import dao.ShiftDao;
import dao.StoreDao;
import tool.Action;
import tool.CalendeCreate;
import tool.ShiftCreate;;

public class ShiftCreateAction extends Action{

	@Override
	public void execute(HttpServletRequest req, HttpServletResponse res) throws Exception {
		CalendeCreate calende = new CalendeCreate();
		ShiftDao  shDao = new ShiftDao();
		StoreDao  stDao = new StoreDao();
		ShiftCreate shift_create = new ShiftCreate();
		Shift  shift  = null;


		List<LocalDate> dates = calende.Calender(2024, 11);
		LocalDate localDate = LocalDate.of(2024, 11, 14);

	    // java.sql.Dateに変換
	    Date shift_date = Date.valueOf(localDate);
		HttpSession session = req.getSession();
		Store manager = (Store)session.getAttribute("user");
		String work_time_start = stDao.TimeStartGet(manager.getStoreId());
		String work_time_end = stDao.TimeEndGet(manager.getStoreId());
		List<Shift> shift_list = new ArrayList<>();
		shift_list = shDao.filter(stDao.get(manager.getStoreId()), shift_date);
		List<Map<String, Object>> workerShifts = shift_create.Shiftmain(work_time_start, work_time_end,shift,manager,shift_list);


		//リクエストにカレンダーをセット
		req.setAttribute("dates", dates);
		//
		req.setAttribute("workerShifts", workerShifts);
		//shift_select.jspに遷移
		req.getRequestDispatcher("shift_create.jsp").forward(req,res);
	}

}
