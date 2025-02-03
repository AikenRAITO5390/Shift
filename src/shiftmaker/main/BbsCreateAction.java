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

        HttpSession session = req.getSession();

        Store store = (Store) session.getAttribute("store");

        Worker worker = (Worker) session.getAttribute("worker");

        Store stores = (Store) session.getAttribute("user"); // ログインユーザーを取得

		StoreDao sDao = new StoreDao();

		Store store_login = sDao.get(stores.getStoreId());

        // ログインユーザーの情報を取得
        String ManagerName = store != null ? store.getManagerName() : null;
        String StoreId = store != null ? store.getStoreId() : null;
        String WorkerName = worker != null ? worker.getWorkerName() : null;
        String WorkerId = worker != null ? worker.getWorkerId() : null;
        String ManagerId = store != null ? store.getManagerId() : null;


        // 今日の日付
        Date today = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        String formattedDate = sdf.format(today);


        //連番やるやつ
        BBSDao dao = new BBSDao();
        BBS bbsid = dao.get(store.getStoreId());

        //
        int BbsId = bbsid != null ? bbsid.getBbsId() : 0;


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


        if (worker != null) {
            req.getRequestDispatcher("bbs_create_worker.jsp").forward(req, res);
        } else if (store != null) {
            req.getRequestDispatcher("bbs_create_manager.jsp").forward(req, res);
        }
    }
}


