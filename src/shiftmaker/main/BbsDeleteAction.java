package shiftmaker.main;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import bean.BBS;
import bean.Store;
import dao.BBSDao;
import tool.Action;

public class BbsDeleteAction extends Action {

    @Override
    public void execute(HttpServletRequest req, HttpServletResponse res) throws Exception {
    	System.out.println("①★★★★★★managerdelete★★★★★★");
        // ローカル変数の宣言
        BBSDao bDao = new BBSDao(); // BBS Dao
        HttpSession session = req.getSession(); // セッション
        Store store = (Store) session.getAttribute("user"); // ログインユーザーを取得

        // リクエストパラメータの取得
        String BBS_ID = req.getParameter("BBS_ID");
        String WORKER_ID = req.getParameter("WORKER_ID");
        String MANAGER_ID = req.getParameter("MANAGER_ID");

        BBS bbs = bDao.get_BbsId(BBS_ID);
        System.out.println("取得したbbs: " + bbs);

        // コンソールで確認
        System.out.println("取得したBBS_ID: " + BBS_ID);
        System.out.println("取得したWORKER_ID: " + WORKER_ID);
        System.out.println("取得したMANAGER_ID: " + MANAGER_ID);

        //投稿者とコメントを表示
        String user = bbs.getManager(); // 投稿者を取得




        String text = bbs.getBbsText(); // コメントを取得

        req.setAttribute("BBS_ID", BBS_ID);
        req.setAttribute("bbs", bbs);
        req.setAttribute("user", user);
        req.setAttribute("text", text);


        System.out.println("取得したuser: " + user);
        System.out.println("取得したtext: " + text);
        // 削除完了後に一覧画面へリダイレクト
       // res.sendRedirect("bbs_delete.jsp");

        req.getRequestDispatcher("bbs_delete.jsp").forward(req, res);
    }
}