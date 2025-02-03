package shiftmaker.main;

import java.util.ArrayList;
import java.util.List;
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

public class WorkerUpdateAction extends Action {

	public void execute(HttpServletRequest req, HttpServletResponse res) throws Exception {
		//ローカル変数の宣言 1
		WorkerDao wDao = new WorkerDao();//学生Dao
		HttpSession session = req.getSession();//セッション
		Store store = (Store)session.getAttribute("user");// ログインユーザーを取得
		StoreDao sDao = new StoreDao();// クラス番号Daoを初期化
		Store stores = new Store();


		//リクエストパラメータ―の取得 2
//		String no = req.getParameter("student_no");//学番
		String workerId = req.getParameter("workerId");//学番



		// 生年月日選択のための年リスト作成（例: 1950年～2023年）
	    List<Integer> years = new ArrayList<>();
	    for (int i = 1950; i <= 2023; i++) {
	        years.add(i);
	    }
	    // 月、日リストを作成
	    List<Integer> months = IntStream.rangeClosed(1, 12).boxed().collect(Collectors.toList());
	    List<Integer> days = IntStream.rangeClosed(1, 31).boxed().collect(Collectors.toList());

	    // 年、月、日のlistをセット
	 	req.setAttribute("year_list", years);
	 	req.setAttribute("month_list", months);
	 	req.setAttribute("day_list", days);



		//DBからデータ取得 3
		stores = sDao.get(store.getStoreId());

		Store store_login = sDao.get(stores.getStoreId());
		req.setAttribute("managerName", store_login.getManagerName());

		Worker worker = wDao.get(workerId);
		List<String> list = sDao.filter(store.getStoreId());

		// 生年月日が存在する場合、年、月、日を抽出
	    String workerDate = worker.getWorkerDate(); // yyyy/MM/dd 形式で取得
	    String selectedYear = "";
	    String selectedMonth = "";
	    String selectedDay = "";

	    if (workerDate != null && !workerDate.isEmpty()) {
	        // スラッシュで分割して年、月、日を取り出す
	        String[] dateParts = workerDate.split("/"); // yyyy/MM/dd 形式なので、"/"で分割

	        selectedYear = dateParts[0];   // 年
	        selectedMonth = dateParts[1];  // 月
	        selectedDay = dateParts[2];    // 日

	        // 月と日が1桁の場合、ゼロを追加
	        if (selectedMonth.length() == 1) {
	            selectedMonth = "0" + selectedMonth;  // 例: 9月 -> 09
	        }
	        if (selectedDay.length() == 1) {
	            selectedDay = "0" + selectedDay;  // 例: 9日 -> 09
	        }
	    }


	    // 年、月、日をJSPに渡す
	    req.setAttribute("selectedYear", selectedYear);
	    req.setAttribute("selectedMonth", selectedMonth);
	    req.setAttribute("selectedDay", selectedDay);

		req.setAttribute("worker", worker);
        req.setAttribute("stores", list);
        req.setAttribute("stores", stores);


		//JSPへフォワード 7
		req.getRequestDispatcher("worker_update.jsp").forward(req, res);
	}
}

