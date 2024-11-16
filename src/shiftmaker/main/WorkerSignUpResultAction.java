package shiftmaker.main;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
		String worker_date = year + "-" + month + "-" + day;
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
		// 生年月日
		worker_date = req.getParameter("worker_date");
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
		List<String> list = storeDao.filter(manager.getStoreName());
		// ラジオボタンの値を取得
		String worker_type = req.getParameter("worker_type");



		// ビジネスロジック 4



		// DBへデータ保存 5

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
			worker.getStore().setStoreId(((Store) session.getAttribute("user")).getStoreId());
			// workerJudgeをラジオボタンで設定した値に基づいてセット
		    worker.setWorkerJudge(Boolean.parseBoolean(worker_type)); // "true"または"false"をbooleanに変換してセット

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
			req.getRequestDispatcher("worker_signup.jsp").forward(req, res);
			return;
		}
		req.getRequestDispatcher("worker_signup_result.jsp").forward(req, res);
	}

}
