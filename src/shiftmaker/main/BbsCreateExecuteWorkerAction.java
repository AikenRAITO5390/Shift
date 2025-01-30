package shiftmaker.main;

import java.text.SimpleDateFormat;
import java.util.Date;

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

public class BbsCreateExecuteWorkerAction extends Action {
	//worker用のやつ！！！！
	@Override
    public void execute(HttpServletRequest req, HttpServletResponse res) throws Exception {

        System.out.println("⑤★★★★★★worker★★★★★★");
        WorkerDao wDao = new WorkerDao();
        StoreDao sDao = new StoreDao();
        BBSDao bDao = new BBSDao();

        //ログイン情報から名前を取得ヘッダー
        HttpSession session = req.getSession();
        Worker workers = (Worker) session.getAttribute("user");
        Worker worker_login = wDao.get(workers.getWorkerId());


        Store store = (Store) session.getAttribute("store");

        if (store == null) {
            System.out.println("Store is null");
            res.sendRedirect("login.jsp");
            return;
        }
        System.out.println("store :" + store);

        // リクエストパラメータの取得
        String WORKER_ID = req.getParameter("WORKER_ID");
        String BBS_TEXT = req.getParameter("BBS_TEXT");
        //String BBS_ID = req.getParameter("BBS_ID");
        String STORE_ID = req.getParameter("STORE_ID");
        String BBS_DATE = req.getParameter("BBS_DATE");
        String MANAGER_ID = req.getParameter("MANAGER_ID");


        System.out.println("⑥★★★★★★worker★★★★★★");
        System.out.println("WORKER_ID :" + WORKER_ID);
        System.out.println("BBS_TEXT :" + BBS_TEXT);
        //System.out.println("BBS_ID :" + BBS_ID);
        System.out.println("STORE_ID :" + STORE_ID);
        System.out.println("BBS_DATE :" + BBS_DATE);
        System.out.println("MANAGER_ID :" + MANAGER_ID);

        // DBからデータ取得
        Worker worker = null;
        if (WORKER_ID != null && !WORKER_ID.isEmpty()) {
            worker = wDao.get(WORKER_ID);
        } else if (MANAGER_ID != null && !MANAGER_ID.isEmpty()) {
            worker = wDao.get(MANAGER_ID);
        }
        System.out.println("⑦★★★★★★worker★★★★★★★");
        System.out.println("worker :" + worker);

        System.out.println("workerId :" +(worker != null ? worker.getWorkerId() : "null"));

        Store storeFromDb = sDao.get(STORE_ID);
        if (storeFromDb == null) {
            System.out.println("Store from DB is null");
            res.sendRedirect("error.jsp");
            return;
        }

        //BBS_DATEをstring型からdate型にする
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date date = dateFormat.parse(BBS_DATE);

        System.out.println("⑧★★★★★★worker★★★★★★");
        System.out.println(date);


        //連番のやつほしい
        System.out.println("⑤★★★★★★manager★★連番★★★★");
        BBSDao dao = new BBSDao();
        BBS bbsid = dao.get(store.getStoreId());

        int BbsId = bbsid != null ? bbsid.getBbsId() : 0;
        System.out.println("取得したBbsIdは: " + BbsId);


        // 新しいBBSインスタンスを作成
        BBS bbs = new BBS();
        bbs.setWorker(worker);
        bbs.setBbsText(BBS_TEXT);
        //bbs.setBbsId(BBS_ID);
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
        req.setAttribute("WorkerName", worker_login.getWorkerName());

        // JSPへフォワード
        req.getRequestDispatcher("bbs_create_worker_done.jsp").forward(req, res);
    }
}