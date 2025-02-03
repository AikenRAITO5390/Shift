package shiftmaker.main;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import bean.Worker;
import dao.BBSDao;
import dao.WorkerDao;
import tool.Action;

public class BbsDeleteExecuteWorkerAction extends Action {
	@Override
    public void execute(HttpServletRequest req, HttpServletResponse res) throws Exception {

        // ローカル変数の宣言
        BBSDao bDao = new BBSDao(); // BBS Dao
        HttpSession session = req.getSession(); // セッション
        Worker worker = (Worker) session.getAttribute("user"); // ログインユーザーを取得

        WorkerDao wDao = new WorkerDao();
        Worker worker_login = wDao.get(worker.getWorkerId());

        //中江つけたし
        String Sachiko_Bbs = req.getParameter("id_id");


        // デバッグ情報の追加
        if ( Sachiko_Bbs == null) {

            res.sendRedirect("bbs_delete_error.jsp");
        } else {
            try {
                //int bbsIdInt = Integer.parseInt(BBS_ID);


                // 投稿の削除処理を実行
                bDao.delete(Sachiko_Bbs);
                req.setAttribute("WorkerName", worker_login.getWorkerName());
                // 削除完了後に一覧画面へリダイレクト
                req.getRequestDispatcher("bbs_delete_workerok.jsp").forward(req, res);
            } catch (NumberFormatException e) {

                res.sendRedirect("bbs_delete_error.jsp");
            }
            //BBS_IDの変換に失敗しましたってでる！！なんで！！どこが違うんだ！！！
        }

    }
}