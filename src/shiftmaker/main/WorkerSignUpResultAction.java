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

public class WorkerSignUpResultAction extends Action{

	@Override
	public void execute(HttpServletRequest req, HttpServletResponse res) throws Exception {

		// ローカル変数の宣言 1

		// セッションを取得
		HttpSession session = req.getSession();
		//ログイン情報から名前を取得ヘッダーの為に
		StoreDao sDao = new StoreDao();

		Store store_login = (Store)session.getAttribute("user");

		Store store = sDao.get(store_login.getStoreId());
		req.setAttribute("managerName", store.getManagerName());

		// リクエストパラメータの取得
		String year = req.getParameter("year");
		String month = req.getParameter("month");
		String day = req.getParameter("day");

		// WorkerDaoを初期化
		WorkerDao workerDao = new WorkerDao();
		// 従業員ID
		String worker_id = "";
		// 名前
		String worker_name = "";
		// 生年月日
		String worker_date = year + "/" + month + "/" + day;
		// 住所
		String worker_address = "";
		// 電話番号
		String worker_tel = "";
		// パスワード
		String worker_password = "";
		// 店情報
		String store_name = "";

		// 従業員
		Worker worker = null;

		// エラー用
		Map<String, String> errors = new HashMap<>();// エラーメッセージ

		// StoreDaoを初期化
		StoreDao storeDao = new StoreDao();

		// ログインユーザーを取得
		Store manager = (Store) session.getAttribute("user");



		// リクエストパラメータ―の取得 2
		// 従業員ID
		worker_id = req.getParameter("worker_id");
		// 名前
		worker_name = req.getParameter("worker_name");
		// 生年月日は39行目で設定しています
		// 住所
		worker_address = req.getParameter("worker_address");
		// 電話番号
		worker_tel = req.getParameter("worker_tel");
		// パスワード
		worker_password = req.getParameter("worker_password");
		// 店情報
		store_name = req.getParameter("store_name");



		//DBからデータ取得 3

		// 従業員IDから従業員インスタンスを取得
		worker = workerDao.get(worker_id);
		// ログインユーザーの店情報をもとに店舗名の一覧を取得
		List<String> list = storeDao.filter(manager.getManagerId());
		// ラジオボタンの値を取得
		String worker_judge = req.getParameter("worker_judge");

		// 生年月日選択のための年リスト作成（例: 1950年～2023年）
	    List<Integer> years = new ArrayList<>();
	    for (int i = 1950; i <= 2023; i++) {
	        years.add(i);
	    }
	    // 月、日リストを作成
	    List<Integer> months = IntStream.rangeClosed(1, 12).boxed().collect(Collectors.toList());
	    List<Integer> days = IntStream.rangeClosed(1, 31).boxed().collect(Collectors.toList());

		// 従業員が未登録だった場合
		if (worker == null) {

			// 従業員インスタンスを初期化
			worker = new Worker();

			// インスタンスに値をセット
			worker.setWorkerId(worker_id);
			worker.setWorkerName(worker_name);
			worker.setWorkerDate(worker_date);
			worker.setWorkerAddress(worker_address);
			worker.setWorkerTel(worker_tel);
			worker.setWorkerPassword(worker_password);
			if (worker.getStore() == null) {
			    worker.setStore(new Store());
			}
			worker.getStore().setStoreId(((Store) session.getAttribute("user")).getStoreId());
			// workerJudgeをラジオボタンで設定した値に基づいてセット
			worker.setWorkerJudge(Boolean.parseBoolean(worker_judge)); // "true"または"false"をbooleanに変換してセット

			// 従業員を保存
			workerDao.save(worker);

		// 入力された従業員IDがDBに保存されていた場合
		} else {
			errors.put("worker_id", "従業員IDが重複しています");
		}



		//エラーがあったかどうかで手順6~7の内容が分岐
		//エラーがあったか場合に備えて　以下のモノをリクエストに取り込む

		//レスポンス値をセット 6
		//JSPへフォワード 7

		// 店情報のlistをセット
		req.setAttribute("store_name_set", list);

		if(!errors.isEmpty()){
			// リクエスト属性をセット
			req.setAttribute("errors", errors);
			req.setAttribute("worker_id", worker_id);
			req.setAttribute("worker_name", worker_name);
			req.setAttribute("worker_date", worker_date);
			req.setAttribute("worker_address", worker_address);
			req.setAttribute("worker_tel", worker_tel);
			req.setAttribute("worker_password", worker_password);
			req.setAttribute("store_name", store_name);
			// 年、月、日をセット
			req.setAttribute("year_list", years);
			req.setAttribute("month_list", months);
		    req.setAttribute("day_list", days);
			req.getRequestDispatcher("worker_signup.jsp").forward(req, res);
			return;
		}
		req.getRequestDispatcher("worker_signup_result.jsp").forward(req, res);
	}

}
