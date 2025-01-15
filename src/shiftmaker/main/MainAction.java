package shiftmaker.main;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import bean.Store;
import dao.StoreDao;
import tool.Action;

public class MainAction extends Action{

	@Override
	public void execute(HttpServletRequest req, HttpServletResponse res) throws Exception {
		//ローカル変数の宣言 1
		//リクエストパラメータ―の取得 2
		HttpSession session = req.getSession();//セッション
		StoreDao sDao = new StoreDao();
		Store store_login = (Store)session.getAttribute("user");
		Map<String, String> errors = new HashMap<>();

		System.out.println("ログインユーザーの更新");
		System.out.println(store_login.getStoreId());
		System.out.println(store_login.getManagerName());
		Store store = sDao.get(store_login.getStoreId());
		if (store != null) {// ゲットした社員のID,名前、パスワード、メール、店舗名を渡す
			req.setAttribute("managerName", store.getManagerName());

			System.out.println(store.getManagerName());
		} else {// 学生が存在していなかった場合
			errors.put("managername", "社員情報が存在していません");
			req.setAttribute("errors", errors);
		}
		//DBからデータ取得 3
		//なし
		//ビジネスロジック 4
		//なし
		//DBへデータ保存 5
		//なし
		//レスポンス値をセット 6
		//JSPへフォワード 7
		req.getRequestDispatcher("main.jsp").forward(req, res);
	}

}
