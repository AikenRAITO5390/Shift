package shiftmaker.main;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import bean.Store;
import dao.BBSDao;
import dao.StoreDao;
import tool.Action;

public class BbsDeleteExecuteAction extends Action {
	@Override
    public void execute(HttpServletRequest req, HttpServletResponse res) throws Exception {
        System.out.println("②★★★★★★managerdelete★★★★★★");
        // ローカル変数の宣言
        BBSDao bDao = new BBSDao(); // BBS Dao
        HttpSession session = req.getSession(); // セッション
        Store store = (Store) session.getAttribute("user"); // ログインユーザーを取得
        // ログインユーザーを取得

		StoreDao sDao = new StoreDao();

		Store store_login = sDao.get(store.getStoreId());

        // リクエストパラメータの取得
        String BBS_ID = req.getParameter("bbsId");

        System.out.println("取得したBBS_ID：" + BBS_ID);

        // デバッグ情報の追加
        if ( BBS_ID == null) {
            System.out.println("BBS_IDがnullです。パラメータ名を確認してください。");
            res.sendRedirect("bbs_delete_error.jsp");

        } else {
            try {
                //int bbsIdInt = Integer.parseInt(BBS_ID);
                System.out.println("変換後のBBS_ID: " +  BBS_ID);

                // 投稿の削除処理を実行
                bDao.delete(BBS_ID);
                req.setAttribute("managerName", store_login.getManagerName());

                // 削除完了後に一覧画面へリダイレクト
                req.getRequestDispatcher("bbs_delete_ok.jsp").forward(req, res);

            } catch (NumberFormatException e) {
                System.out.println("BBS_IDの変換に失敗しました: " + e.getMessage());
                res.sendRedirect("bbs_delete_error.jsp");
            }
            //BBS_IDの変換に失敗しましたってでる！！なんで！！どこが違うんだ！！！
        }

    }
}