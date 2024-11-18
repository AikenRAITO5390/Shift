package shiftmaker.main;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import bean.Store;
import dao.StoreDao;
import tool.Action;

public class ShiftManagerSignupResultAction extends Action {
	@Override
	public void execute(HttpServletRequest req, HttpServletResponse res) throws Exception {
		//ローカル変数の宣言 1
		StoreDao sDao = new StoreDao();
		HttpSession session = req.getSession();//セッション
		Store store_login = (Store)session.getAttribute("user");// ログインユーザーを取得
		Map<String, String> errors = new HashMap<>();

		//リクエストパラメータ―の取得 2
		String store_id= req.getParameter("storeId");
		String manager_name = req.getParameter("managerName");
		String password = req.getParameter("password");
		String email = req.getParameter("email");
		String store_name = req.getParameter("storeName");

		//DBからデータ取得 3
		Store store = sDao.get(store_login.getStoreId());
		//ビジネスロジック 4
		//DBへデータ保存 5
		//条件で4～5が分岐
		if (store != null) {
			// 学生が存在していた場合
			// インスタンスに値をセット
			store.setStoreId(store_id);
			store.setManagerName(manager_name);
			store.setPassword(password);
			store.setEmail(email);
			store.setStoreName(store_name);
			// 学生を保存
			sDao.save(store);
		} else {
		}

		//エラーがあったかどうかで手順6~7の内容が分岐


		if(!errors.isEmpty()){//エラーがあった場合、更新画面へ戻る
			// リクエスト属性をセット
			req.setAttribute("errors", errors);
			req.setAttribute("store_id", store_id);
			req.setAttribute("manager_name", manager_name);
			req.setAttribute("password", password);
			req.setAttribute("email", email);
			req.setAttribute("store_name", store_name);
			req.getRequestDispatcher("shiftmanager_edit_disp.jsp").forward(req, res);
			return;
		}

		req.getRequestDispatcher("shiftmanager_edit_disp_result.jsp").forward(req, res);
	}
}
