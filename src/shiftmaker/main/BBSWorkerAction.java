package shiftmaker.main;

import java.util.List;
import java.util.stream.Collectors;

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

public class BBSWorkerAction extends Action {
    public void execute(HttpServletRequest req, HttpServletResponse res) throws Exception {
        HttpSession session = req.getSession();

        System.out.println("①▲▲▲▲▲▲▲▲▲▲▲▲▲▲");

        Worker worker = (Worker) session.getAttribute("user");
        if (worker == null) {
            System.out.println("Worker is null");
            res.sendRedirect("login.jsp");
            return;
        }
        System.out.println("worker: " + worker);

        Store store = worker.getStore();



        String store_id =worker.getStoreId();

        System.out.println("store_id: " + store_id);




        WorkerDao wDao = new WorkerDao();
        StoreDao sDao = new StoreDao();
        BBSDao bDao = new BBSDao();


        List<Worker> workers = wDao.filter(store);

        // TRUE
        List<Worker> filteredWorkers = workers.stream()
                                              .filter(w -> w.isWorkerJudge()) // isWorkerJudge()はWORKER_JUDGEを返すメソッド
                                              .collect(Collectors.toList());

        // FALSE
        List<Worker> filteredWorkersnot = workers.stream()
                .filter(w -> !w.isWorkerJudge()) // isWorkerJudge()はWORKER_JUDGEを返すメソッド
                .collect(Collectors.toList());

        // 掲示板メッセージを取得
        List<BBS> messages = bDao.filter(store);

        // MANAGER_IDからMANAGER_NAMEを取得
        String managerId = store.getManagerId(); // StoreクラスにgetManagerId()があると仮定
        Store managerStore = sDao.manager_get(managerId);
        String managerName = managerStore != null ? managerStore.getManagerName() : null;

        // リクエストにデータをセット
        req.setAttribute("workers", filteredWorkers);
        req.setAttribute("workersnot", filteredWorkersnot);
        req.setAttribute("stores", store);
        req.setAttribute("messages", messages);
        req.setAttribute("managerName", managerName);

        System.out.println("Stores: " + store);
        System.out.println("Filtered Workers: " + filteredWorkers);
        System.out.println("Filtered Workers not: " + filteredWorkersnot);
        System.out.println("Messages: " + messages);
        System.out.println("MessagesName: " + managerName);

        req.getRequestDispatcher("bbs_list.jsp").forward(req, res);
    }
}