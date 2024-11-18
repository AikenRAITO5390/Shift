package shiftmaker.main;

//import java.util.Date;
import java.sql.Date;
import java.time.LocalDate;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bean.Shift;
import bean.Store;
import bean.Worker;
import dao.ShiftDao;
import dao.StoreDao;
import dao.WorkerDao;
import tool.Action;
import tool.CalendeCreate;

public class ShiftCreateAction extends Action{

	@Override
	public void execute(HttpServletRequest req, HttpServletResponse res) throws Exception {
		CalendeCreate calende = new CalendeCreate();
		ShiftDao  shDao = new ShiftDao();
		WorkerDao wDao  = new WorkerDao();
		StoreDao  stDao = new StoreDao();
		Shift  shift  = null;
		Worker worker = null;
		Store  store  = null;
		String worker_id = "knz123";
		String store_id = "1234567";


		List<LocalDate> dates = calende.Calender(2024, 11);
		worker = wDao.get(worker_id);
		store  = stDao.get(store_id);
		 LocalDate localDate = LocalDate.of(2024, 11, 13);

	    // java.sql.Dateに変換
	    Date shift_date = Date.valueOf(localDate);
		shift = shDao.get(worker, shift_date, store);

		if(shift != null){
			req.setAttribute("shift_date", shift.getShiftDate());
			req.setAttribute("score", shift.getShiftScore() );
			req.setAttribute("hope_time_id",shift.getShiftHopeTimeId());
			req.setAttribute("hope_time_start", shift.getShiftHopeTimeStart() );
			req.setAttribute("hope_time_end", shift.getShiftHopeTimeEnd() );
			req.setAttribute("work_time_id", shift.getWorkTimeId());
			req.setAttribute("shift_time_start",shift.getShiftTimeStart() );
			req.setAttribute("shift_time_end", shift.getShiftTimeEnd());
			req.setAttribute("shift_id",shift.getShiftId() );
		}else{
			System.out.println("しっぱーい＾＾");
		}

		//リクエストにカレンダーをセット
		req.setAttribute("dates", dates);
		//リクエストにシフト情報をセット
		req.setAttribute("shift", shift );
		//shift_select.jspに遷移
		req.getRequestDispatcher("shift_create.jsp").forward(req,res);
	}

}
