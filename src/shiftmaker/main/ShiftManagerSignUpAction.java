package shiftmaker.main;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import bean.Store;
import dao.StoreDao;
import tool.Action;

public class ShiftManagerSignUpAction extends Action {

	public void execute(HttpServletRequest req, HttpServletResponse res) throws Exception {
		StoreDao sDao = new StoreDao();
		HttpSession session = req.getSession();//セッション
		Store store_login = (Store)session.getAttribute("user");// ログインユーザーを取得
		Map<String, String> errors = new HashMap<>();

		Store store = sDao.get(store_login.getStoreId());

		if (store != null) {// 学生が存在していた場合
			req.setAttribute("storeId", store.getStoreId());
			req.setAttribute("managerName", store.getManagerName());
			req.setAttribute("password", store.getPassword());
			req.setAttribute("email", store.getEmail());
			req.setAttribute("storeName", store.getStoreName());
		} else {// 学生が存在していなかった場合
			errors.put("storeId", "学生が存在していません");
			req.setAttribute("errors", errors);
		}
		//JSPへフォワード 7
		req.getRequestDispatcher("shiftmanager_edit_disp.jsp").forward(req, res);
	}

	}


