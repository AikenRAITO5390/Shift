package shiftmaker.main;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import bean.Store;
import dao.ShiftDao;
import tool.Action;

public class ShiftDeleteResultAction extends Action {

	@Override
	public void execute(HttpServletRequest req, HttpServletResponse res) throws Exception {

	    // セッションから店舗情報を取得
	    HttpSession session = req.getSession();
	    Store manager = (Store) session.getAttribute("user");
	    if (manager == null) {
	        System.out.println("Error: storeがセッションにありません");
	        res.sendRedirect("login.jsp");
	        return;
	    }

	    // リクエストから年、月を取得
	    String yearStr = req.getParameter("year");
	    String monthStr = req.getParameter("month");

	    int year = (yearStr != null) ? Integer.parseInt(yearStr) : 0;
	    int month = (monthStr != null) ? Integer.parseInt(monthStr) : 0;

	    String message = "";
	    String errorMessage = "";

	    try {
	        // ShiftDaoを使ってシフト削除処理を実行
	        ShiftDao shiftDao = new ShiftDao();
	        shiftDao.deleteShiftsByYearAndMonth(manager.getStoreId(), year, month);  // 年と月を条件に削除

	        message = "正しく削除されました。";
	    } catch (Exception e) {
	        // エラーが発生した場合
	        e.printStackTrace();
	        errorMessage = "error：正しく処理できませんでした。";
	    }

	    // メッセージをリクエストにセット
	    if (!message.isEmpty()) {
	        req.setAttribute("message", message);
	    }
	    if (!errorMessage.isEmpty()) {
	        req.setAttribute("errorMessage", errorMessage);
	    }

	    // 削除後、結果ページに遷移
	    req.getRequestDispatcher("shift_delete_result.jsp").forward(req, res);
	}

}
