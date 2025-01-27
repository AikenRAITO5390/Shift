package shiftmaker.main;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import bean.Store;
import dao.StoreDao;
import tool.Action;

// シフト編集完了ページへ飛ばす
public class ShiftEditResultAction extends Action{

	@Override
	public void execute(HttpServletRequest req, HttpServletResponse res) throws Exception {

		System.out.println("★★★★★★④★★★★★★");

		System.out.println("完了ページ");
		HttpSession session = req.getSession(); // セッション
        Store store = (Store) session.getAttribute("user"); // ログインユーザーを取得

		StoreDao sDao = new StoreDao();

		Store store_login = sDao.get(store.getStoreId());

		req.setAttribute("managerName", store_login.getManagerName());
	    // JSPへフォワード
	    req.getRequestDispatcher("shift_edit_result.jsp").forward(req, res);
	}

}
