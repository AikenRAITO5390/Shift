package shiftmaker.main;

import java.util.Optional;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import bean.BBS;
import bean.Store;
import bean.Worker;
import dao.BBSDao;
import dao.StoreDao;
import tool.Action;

public class BbsDeleteAction extends Action {

    @Override
    public void execute(HttpServletRequest req, HttpServletResponse res) throws Exception {

        // ローカル変数の宣言
        BBSDao bDao = new BBSDao(); // BBS Dao
        HttpSession session = req.getSession(); // セッション
        Store store = (Store) session.getAttribute("user"); // ログインユーザーを取得

		StoreDao sDao = new StoreDao();

		Store store_login = sDao.get(store.getStoreId());

        // リクエストパラメータの取得
        String BBS_ID = req.getParameter("BBS_ID");
        String MANAGER_ID = req.getParameter("MANAGER_ID");

        BBS bbs = bDao.get_BbsId(BBS_ID);


        // コンソールで確認


        Worker worker = bbs.getWorker();
        String worker_name = Optional.ofNullable(worker)
                                     .map(Worker::getWorkerName)
                                     .orElse(null);

        //投稿者とコメントを表示
        String user = bbs.getManager(); // 投稿者を取得

       // worker = bbs.getWorker();
    //    String WorkerName = (worker != null) ? worker.getWorkerName() : "defaultName";


        String text = bbs.getBbsText(); // コメントを取得

        if (worker_name != null) {
        req.setAttribute("BBS_ID", BBS_ID);
        req.setAttribute("bbs", bbs);
        req.setAttribute("text", text);
        req.setAttribute("user", worker_name);
        req.setAttribute("managerName", store_login.getManagerName());
      //  req.setAttribute("WorkerName", WorkerName);



        req.getRequestDispatcher("bbs_delete.jsp").forward(req, res);
     } else {
         req.setAttribute("BBS_ID", BBS_ID);
         req.setAttribute("bbs", bbs);
         req.setAttribute("text", text);
         req.setAttribute("user", user);
         req.setAttribute("managerName", store_login.getManagerName());



    	 req.getRequestDispatcher("bbs_delete.jsp").forward(req, res);
     }
    }
}