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
import tool.Action;

public class BbsCreateAction extends Action {


    public void execute(HttpServletRequest req, HttpServletResponse res) throws Exception {
        System.out.println("動いてますよー！");

        HttpSession session = req.getSession();

        Store store = (Store) session.getAttribute("store");
        System.out.println("②★★★★★★manager★★★★★★");
        System.out.println("Store: " + store);

        Worker worker = (Worker) session.getAttribute("worker");
        System.out.println("Worker: " + worker);

        Store stores = (Store) session.getAttribute("user"); // ログインユーザーを取得

		StoreDao sDao = new StoreDao();

		Store store_login = sDao.get(stores.getStoreId());

        // ログインユーザーの情報を取得
        String ManagerName = store != null ? store.getManagerName() : null;
        String StoreId = store != null ? store.getStoreId() : null;
        String WorkerName = worker != null ? worker.getWorkerName() : null;
        String WorkerId = worker != null ? worker.getWorkerId() : null;
        String ManagerId = store != null ? store.getManagerId() : null;

        // デバッグメッセージを追加
        System.out.println("③★★★★★★manager★★★★★★");
        System.out.println("ManagerName :" + ManagerName);
        System.out.println("WorkerName :" + WorkerName);
        System.out.println("WorkerId :" + WorkerId);
        System.out.println("投稿者 :" + ManagerName);
        System.out.println("STORE_ID :" + StoreId);
        System.out.println("WorkerName :" + WorkerName);

        // 今日の日付
        System.out.println("④★★★★★★manager★★★★★★");
        Date today = new Date();
        System.out.println("Today (Date): " + today);
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");
        String formattedDate = sdf.format(today);


        //連番やるやつ
        System.out.println("⑤★★★★★★manager★★連番★★★★");
        BBSDao dao = new BBSDao();
        BBS bbsid = dao.get(store.getStoreId());

        //
        int BbsId = bbsid != null ? bbsid.getBbsId() : 0;


        System.out.println("取得したBbsIdは: " + BbsId);

        // 取得したBBS_IDをリクエストに設定する
        req.setAttribute("BbsId", BbsId);




        String UserName = ManagerName;

        // リクエストにユーザー名、日付、BBS_IDを設定
        req.setAttribute("ManagerId", ManagerId);
        req.setAttribute("UserName", UserName);
        req.setAttribute("today", formattedDate);
        req.setAttribute("BbsId", BbsId);
        req.setAttribute("StoreId", StoreId);
        req.setAttribute("managerName", store_login.getManagerName());

        System.out.println(UserName);

        if (worker != null) {
            req.getRequestDispatcher("bbs_create_worker.jsp").forward(req, res);
        } else if (store != null) {
            req.getRequestDispatcher("bbs_create_manager.jsp").forward(req, res);
        }
    }
}


