package shiftmaker.main;

import java.util.List;
import java.util.stream.Collectors;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import bean.Store;
import bean.Worker;
import dao.StoreDao;
import dao.WorkerDao;
import tool.Action;

public class WorkerListAction extends Action {
    public void execute(HttpServletRequest req, HttpServletResponse res) throws Exception {
        HttpSession session = req.getSession();
        Store store = (Store) session.getAttribute("user");

        WorkerDao wDao = new WorkerDao();
        StoreDao sDao = new StoreDao();
        Store stores = new Store();

        stores = sDao.get(store.getStoreId());

    	//ログイン情報から名前を取得して"managerName"として渡す
		req.setAttribute("managerName", stores.getManagerName());

        List<Worker> workers = wDao.filter(store);

        //TRUE
        List<Worker> filteredWorkers = workers.stream()
                                              .filter(worker -> worker.isWorkerJudge()) // isWorkerJudge()はWORKER_JUDGEを返すメソッド
                                              .collect(Collectors.toList());

        //FLASE
        List<Worker> filteredWorkersnot = workers.stream()
                .filter(worker -> !worker.isWorkerJudge()) // isWorkerJudge()はWORKER_JUDGEを返すメソッド
                .collect(Collectors.toList());



        // リクエストにデータをセット
        req.setAttribute("workers", filteredWorkers);
        req.setAttribute("workersnot", filteredWorkersnot);
        req.setAttribute("stores", stores);



        System.out.println("Stores: " + stores);
        System.out.println("Filtered Workers: " + filteredWorkers);
        System.out.println("Filtered Workers: " + filteredWorkersnot);

        req.getRequestDispatcher("worker_list.jsp").forward(req, res);
    }
}