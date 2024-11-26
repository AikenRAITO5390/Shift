package shiftmaker.main;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import bean.BBS;
import bean.Store;
import bean.Worker;
import dao.BBSDao;
import dao.StoreDao;
import dao.WorkerDao;
import tool.Action;

public class BbsCreateExecuteAction extends Action {
    @Override
    public void execute(HttpServletRequest req, HttpServletResponse res) throws Exception {

        WorkerDao wDao = new WorkerDao();
        StoreDao sDao = new StoreDao();
        BBSDao bDao = new BBSDao();

        HttpSession session = req.getSession();
        Store store = (Store) session.getAttribute("user");

        if (store == null) {
            System.out.println("Store is null");
            res.sendRedirect("login.jsp");
            return;
        }

        // リクエストパラメータの取得
        String WORKER_ID = req.getParameter("WORKER_ID");
        String BBS_TEXT = req.getParameter("BBS_TEXT");
        String BBS_ID = req.getParameter("BBS_ID");
        String STORE_ID = req.getParameter("STORE_ID");
        String BBS_DATE = req.getParameter("BBS_DATE");
        String MANAGER_ID = req.getParameter("MANAGER_ID");


        System.out.println("④★★★★★★★★★★★★★");
        System.out.println("WORKER_ID :"+ WORKER_ID);
        System.out.println("BBS_TEXT :"+ BBS_TEXT);
        System.out.println("BBS_ID :"+ BBS_ID);
        System.out.println("STORE_ID :"+ STORE_ID);
        System.out.println("BBS_DATE :"+ BBS_DATE);
        System.out.println("MANAGER_ID :"+ MANAGER_ID);

        // DBからデータ取得
        Worker worker = null;
        if (WORKER_ID != null) {
            worker = wDao.get(WORKER_ID);
        } else if (MANAGER_ID != null) {
            worker = wDao.get(MANAGER_ID);
        }

        Store storeFromDb = sDao.get(STORE_ID);


        // 新しいBBSインスタンスを作成
        BBS bbs = new BBS();
        bbs.setWorker(worker);
        bbs.setBbsText(BBS_TEXT);
        bbs.setBbsId(BBS_ID);
        bbs.setStore(storeFromDb);
        bbs.setBbsDate(BBS_DATE);

        if (worker != null) {
            System.out.println("従業員：" + worker);
            System.out.println("従業員ID：" + worker.getWorkerId());
        }

        // データベースの更新
        try {
            bDao.save(bbs);
            System.out.println("データベースの更新に成功しました。");
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("データベースの更新に失敗しました。");
        }

        // JSPへフォワード
        req.getRequestDispatcher("bbs_create_done.jsp").forward(req, res);
    }
}