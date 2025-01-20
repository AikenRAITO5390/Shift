package shiftmaker.main;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import bean.Store;
import tool.Action;

public class ShiftDeleteCheckAction extends Action {

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

        // リクエストから年と月を取得
        String yearStr = req.getParameter("year");
        String monthStr = req.getParameter("month");

        int year = (yearStr != null) ? Integer.parseInt(yearStr) : 0;
        int month = (monthStr != null) ? Integer.parseInt(monthStr) : 0;

        // 削除するシフトに関連する情報を取得

        req.setAttribute("year", year);
        req.setAttribute("month", month);

        // 確認ページにフォワード
        req.getRequestDispatcher("shift_delete_check.jsp").forward(req, res);
    }
}