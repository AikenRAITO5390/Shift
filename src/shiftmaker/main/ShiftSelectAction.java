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

		//ログイン名をヘッダーに出す
		HttpSession session = req.getSession(); // セッション
        Store store = (Store) session.getAttribute("user"); // ログインユーザーを取得

		StoreDao sDao = new StoreDao();

		Store store_login = sDao.get(store.getStoreId());


		req.setAttribute("managerName", store_login.getManagerName());

		//shift_select.jspに遷移
		req.getRequestDispatcher("shift_select.jsp").forward(req,res);
	}


}
