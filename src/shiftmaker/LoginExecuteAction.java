package shiftmaker;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import bean.Worker;
import dao.WorkerDao;
import tool.Action;

public class LoginExecuteAction extends Action {

    @Override
    public void execute(HttpServletRequest req, HttpServletResponse res) throws Exception {
        // ローカル変数の宣言
        String url = "";

        // WorkerDaoのインスタンスを作成
        WorkerDao workerDAO = new WorkerDao();

        // リクエストパラメータの取得
        String WORKER_ID = req.getParameter("WORKER_ID");
        String WORKER_PASSWORD = req.getParameter("WORKER_PASSWORD");

        System.out.println(req.getParameter("WORKER_ID"));
        System.out.println(WORKER_PASSWORD);

        // DBからデータ取得
        Worker worker = workerDAO.login(WORKER_ID, WORKER_PASSWORD);

        System.out.println("①★★★★★★★★★★★★★★");
        // もし、ログインが成功したら
        if (worker != null) {
            System.out.println("②★★★★★★★★★★★★★★");
            // Sessionを有効にする
            HttpSession session = req.getSession(true);

            // セッションに"user"という変数名で値はWorker変数の中身
            session.setAttribute("user", worker);

            // リダイレクト
            url = "main/MainWork.action";
            res.sendRedirect(url);
        } else {
            System.out.println("ffff");
            // 認証失敗
            List<String> errors = new ArrayList<>();
            errors.add("ログインに失敗しました。IDまたはパスワードが正しくありません。");
            req.setAttribute("errors", errors);

            // JSPへフォワード
            url = "login.jsp";
            req.getRequestDispatcher(url).forward(req, res);
        }
    }
}