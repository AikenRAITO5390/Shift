package shiftmaker.main;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import bean.Store;
import dao.StoreDao;
import tool.Action;

public class SalesAction extends Action {

//	オーバーライドする必要ないかも
	@Override
	public void execute(HttpServletRequest req,HttpServletResponse res)throws Exception{

		//ヘッダーに名前を出すためのコード
		//ログイン情報から名前を取得して"managerName"として渡す
		HttpSession session = req.getSession();//セッション
		StoreDao sDao = new StoreDao();

		Store store_login = (Store)session.getAttribute("user");

		Store store = sDao.get(store_login.getStoreId());
		req.setAttribute("managerName", store.getManagerName());

		//ローカル変数の宣言 1
		//なし
		//リクエストパラメータ―の取得 2
		//なし
		//DBからデータ取得 3
		//なし
		//ビジネスロジック 4
		//なし
		//DBへデータ保存 5
		//なし
		//レスポンス値をセット 6
		//なし
		//JSPへフォワード 7


		req.getRequestDispatcher("sales.jsp").forward(req, res);
	}
}
