package shiftmaker.main;

import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import bean.Store;
import bean.Worker;
import tool.Action;

public class BbsCreateWorkerAction extends Action {
	//worker用のやつ！！
    // 連番のBBS_IDを生成するメソッド
    private static int currentBbsId = 1;

    public int generateNewBbsId() {
        return currentBbsId++;
    }

    public void execute(HttpServletRequest req, HttpServletResponse res) throws Exception {
        System.out.println("動いてますよー！");

        HttpSession session = req.getSession();

        Store store = (Store) session.getAttribute("store");
        System.out.println("②★★★★★★worker★★★★★★");
        System.out.println("Store: " + store);

        Worker worker = (Worker) session.getAttribute("user");
        System.out.println("Worker: " + worker);


        if (store == null && worker == null) {
            System.out.println("Store and Worker are null");
            res.sendRedirect("login.jsp");
            return;
        }

        // ログインユーザーの情報を取得
        String ManagerName = store != null ? store.getManagerName() : null;
        String StoreId = store != null ? store.getStoreId() : null;
        String WorkerName = worker != null ? worker.getWorkerName() : null;
        String WorkerId = worker != null ? worker.getWorkerId() : null;


        // デバッグメッセージを追加
        System.out.println("③★★★★★★worker★★★★★★" );
        System.out.println("ManagerName :" + ManagerName);
        System.out.println("WorkerName :" + WorkerName);
        System.out.println("WorkerId :" + WorkerId);

        System.out.println("投稿者 :" + (WorkerName != null ? WorkerName : ManagerName));
        System.out.println("STORE_ID :" + StoreId);
        System.out.println("WorkerName :" + WorkerName);


        // 今日の日付
        System.out.println("④★★★★★★worker★★★★★★" );
        Date today = new Date();
        System.out.println("Today (Date): " + today);
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");
        String formattedDate = sdf.format(today);

        // 連番のBBS_IDを生成
        int BbsId = generateNewBbsId();

        System.out.println("BbsId: " + BbsId);

        String UserName = WorkerName != null ? WorkerName : ManagerName;

        // リクエストにユーザー名、日付、BBS_IDを設定
        req.setAttribute("WorkerId", WorkerId);
        req.setAttribute("UserName", UserName);
        req.setAttribute("today", formattedDate);
        req.setAttribute("BbsId", BbsId);
        req.setAttribute("StoreId", StoreId);

        System.out.println(UserName);

        if (worker != null) {
            req.getRequestDispatcher("bbs_create_worker.jsp").forward(req, res);
        } else if (store != null) {
            req.getRequestDispatcher("bbs_create_manager.jsp").forward(req, res);
        }
    }
}


