package shiftmaker.main;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import bean.Store;
import bean.Worker;
import dao.StoreDao;
import tool.Action;

public class ShiftChooseWorkAction extends Action {

	@Override
	public void execute(HttpServletRequest req,HttpServletResponse res)throws Exception{

		// セッションを取得
		HttpSession session = req.getSession();//セッション
		StoreDao sDao = new StoreDao();

		// ログインユーザーを取得
     	Worker loginuser = (Worker)session.getAttribute("user");
     	// 確認用
    	System.out.println("loginuser：" + loginuser);

		Store store = sDao.get(loginuser.getStoreId());
		req.setAttribute("managerName", store.getManagerName());

		//JSPへフォワード 7
		req.getRequestDispatcher("shift_choose_work.jsp").forward(req, res);
	}

}
