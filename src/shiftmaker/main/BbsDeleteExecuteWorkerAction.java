package shiftmaker.main;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import bean.Worker;
import dao.BBSDao;
import tool.Action;

public class BbsDeleteExecuteWorkerAction extends Action {
	@Override
    public void execute(HttpServletRequest req, HttpServletResponse res) throws Exception {
        System.out.println("②★★★★★★managerdelete★★★★★★");
        // ローカル変数の宣言
        BBSDao bDao = new BBSDao(); // BBS Dao
        HttpSession session = req.getSession(); // セッション
        Worker worker = (Worker) session.getAttribute("user"); // ログインユーザーを取得

        //中江つけたし
        String Sachiko_Bbs = req.getParameter("id_id");
        System.out.println("取得したsachikoBBS_ID：" + Sachiko_Bbs);

        // デバッグ情報の追加
        if ( Sachiko_Bbs == null) {
            System.out.println("BBS_IDがnullです。パラメータ名を確認してください。");
            res.sendRedirect("bbs_delete_error.jsp");
        } else {
            try {
                //int bbsIdInt = Integer.parseInt(BBS_ID);
                System.out.println("変換後のBBS_ID: " +  Sachiko_Bbs);

                // 投稿の削除処理を実行
                bDao.delete(Sachiko_Bbs);

                // 削除完了後に一覧画面へリダイレクト
                res.sendRedirect("bbs_delete_workerok.jsp");
            } catch (NumberFormatException e) {
                System.out.println("BBS_IDの変換に失敗しました: " + e.getMessage());
                res.sendRedirect("bbs_delete_error.jsp");
            }
            //BBS_IDの変換に失敗しましたってでる！！なんで！！どこが違うんだ！！！
        }

    }
}