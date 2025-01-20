package shiftmaker.main;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import bean.Store;
import dao.StoreDao;
import tool.Action;

public class ShiftChooseAction extends Action {

	@Override
	public void execute(HttpServletRequest req,HttpServletResponse res)throws Exception{

		// セッションを取得
		HttpSession session = req.getSession();//セッション
		StoreDao sDao = new StoreDao();

		Store store_login = (Store)session.getAttribute("user");

		Store store = sDao.get(store_login.getStoreId());
		req.setAttribute("managerName", store.getManagerName());

		//JSPへフォワード 7
		req.getRequestDispatcher("shift_choose.jsp").forward(req, res);
	}

}
