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

public class BBSAction extends Action {
    public void execute(HttpServletRequest req, HttpServletResponse res) throws Exception {
    	HttpSession session = req.getSession();


    	WorkerDao wDao = new WorkerDao();
        StoreDao sDao = new StoreDao();
        BBSDao bDao = new BBSDao();


    	Store store = (Store) session.getAttribute("user");
    	if (store == null) {
            System.out.println("Store is null");
            res.sendRedirect("login.jsp");
            return;
        }

        Store store_login = sDao.get(store.getStoreId());
        List<Worker> workers = wDao.filter(store);




        // TRUE
        List<Worker> filteredWorkers = workers.stream()
                                              .filter(worker -> worker.isWorkerJudge()) // isWorkerJudge()はWORKER_JUDGEを返すメソッド
                                              .collect(Collectors.toList());

        // FALSE
        List<Worker> filteredWorkersnot = workers.stream()
                .filter(worker -> !worker.isWorkerJudge()) // isWorkerJudge()はWORKER_JUDGEを返すメソッド
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
        req.setAttribute("store_login", store_login);
        req.setAttribute("messages", messages);
        req.setAttribute("managerName", managerName);
        req.setAttribute("managerId", managerId);


        session.setAttribute("store", store);


        req.getRequestDispatcher("bbs_list.jsp").forward(req, res);
    }
}