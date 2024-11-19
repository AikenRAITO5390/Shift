package shiftmaker.main;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import bean.Store;
import dao.StoreDao;
import tool.Action;

public class ShiftSelectAction extends Action{

	@Override
	public void execute(HttpServletRequest req, HttpServletResponse res) throws Exception {
		//ローカル変数の宣言 1
		HttpSession session = req.getSession();
		Store manager = (Store)session.getAttribute("user");
		StoreDao stDao = new StoreDao();
		String work_time_start = stDao.TimeStartGet(manager.getStoreId());
		String work_time_end = stDao.TimeEndGet(manager.getStoreId());
		System.out.println(work_time_end);
		System.out.println(timeToMinutes("09:30:00"));

		//shift_select.jspに遷移
		req.getRequestDispatcher("shift_select.jsp").forward(req,res);
	}

	private static int timeToMinutes(String time) {
        String[] parts = time.split(":");
        return Integer.parseInt(parts[0]) * 60 + Integer.parseInt(parts[1]);
    }

}
