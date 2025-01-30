package shiftmaker.main;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import bean.Store;
import bean.Worker;
import dao.StoreDao;
import dao.WorkerDao;
import tool.Action;

public class WorkerUpdateExecuteAction extends Action {
	@Override
	public void execute(HttpServletRequest req, HttpServletResponse res) throws Exception {
		//ローカル変数の宣言 1
		WorkerDao wDao = new WorkerDao();
		StoreDao sDao = new StoreDao();


		HttpSession session = req.getSession();//セッション
		Store store = (Store)session.getAttribute("user");// ログインユーザーを取得object型で取り出されるためTeacher型にキャストする

		//ログイン情報から名前を取得ヘッダーの為

		Store stores = sDao.get(store.getStoreId());
		req.setAttribute("managerName", stores.getManagerName());

		// 生年月日選択のための年リスト作成（例: 1950年～2023年）
	    List<Integer> years = new ArrayList<>();
	    for (int i = 1950; i <= 2023; i++) {
	        years.add(i);
	    }
	    // 月、日リストを作成
	    List<Integer> months = IntStream.rangeClosed(1, 12).boxed().collect(Collectors.toList());
	    List<Integer> days = IntStream.rangeClosed(1, 31).boxed().collect(Collectors.toList());

		// エラー用
		Map<String, String> errors = new HashMap<>();// エラーメッセージ


		//リクエストパラメータ―の取得 2
		String WORKER_ID= req.getParameter("WORKER_ID");
		String WORKER_NAME = req.getParameter("WORKER_NAME");
		String year = req.getParameter("year");
		String month = req.getParameter("month");
		String day = req.getParameter("day");
		// 生年月日
		String WORKER_DATE = year + "/" + month + "/" + day;
		String WORKER_ADDRESS = req.getParameter("WORKER_ADDRESS");
		String WORKER_TEL = req.getParameter("WORKER_TEL");
		String WORKER_PASSWORD = req.getParameter("WORKER_PASSWORD");
		String STORE_NAME = req.getParameter("STORE_NAME");

		System.out.println(WORKER_ID);
	    System.out.println(WORKER_NAME);
	    System.out.println(WORKER_DATE);
	    System.out.println(WORKER_ADDRESS);
	    System.out.println(WORKER_TEL);



		//DBからデータ取得 3
		Worker worker = wDao.get(WORKER_ID);
		List<String> list = sDao.filter(store.getStoreId());


        worker.setWorkerName(WORKER_NAME);
        worker.setWorkerDate(WORKER_DATE);
        worker.setWorkerAddress(WORKER_ADDRESS);
        worker.setWorkerTel(WORKER_TEL);
        worker.setWorkerPassword(WORKER_PASSWORD);
        stores.setStoreName(STORE_NAME);

        // データベースの更新
        wDao.save(worker);

        req.getRequestDispatcher("worker_updata_done.jsp").forward(req, res);

	}
}

