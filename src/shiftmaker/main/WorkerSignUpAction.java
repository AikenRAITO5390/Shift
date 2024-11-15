package shiftmaker.main;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import bean.Store;
import dao.StoreDao;
import tool.Action;

public class WorkerSignUpAction extends Action{

	@Override
	public void execute(HttpServletRequest req, HttpServletResponse res) throws Exception {

		//ローカル変数の宣言 1
		// セッションを取得
		HttpSession session = req.getSession();
		// StoreDaoを初期化
		StoreDao storeDao = new StoreDao();
		// ログインユーザーを取得
		Store manager = (Store) session.getAttribute("user");

		//リクエストパラメータ―の取得 2
		//なし

		// DBからデータ取得 3
		// ログインユーザーの店情報をもとに店舗名の一覧を取得
		List<String> list = storeDao.filter(manager.getStoreName());

		// ビジネスロジック 4
		// なし

		// DBへデータ保存 5
		// なし

		// レスポンス値をセット 6
		// クラス番号のlistをセット
		req.setAttribute("store_name_set", list);

		// JSPへフォワード 7
		req.getRequestDispatcher("worker_signup.jsp").forward(req, res);
	}


}
