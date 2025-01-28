package shiftmaker.main;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import bean.Store;
import dao.StoreDao;
import tool.Action;

public class ShiftTimeSignupAction extends Action {
	public void execute(HttpServletRequest req, HttpServletResponse res) throws Exception {
		StoreDao sDao = new StoreDao();
		HttpSession session = req.getSession();//セッション
		Store store_login = (Store)session.getAttribute("user");// ログインユーザーを取得
		Map<String, String> errors = new HashMap<>();

		//ログイン情報でゲットする
		List<Store> list = sDao.filterStore(store_login.getStoreId());
		Store store = sDao.get(store_login.getStoreId());


		// "A" の work_time_id を持つデータを検索
		String storeTimeStart = sDao.TimeStartGet(store_login.getStoreId());
		String storeTimeEnd = sDao.TimeEndGet(store_login.getStoreId());

		req.setAttribute("storeTimeStart", storeTimeStart);
		req.setAttribute("storeTimeEnd", storeTimeEnd);


		if (list != null) {   // 店舗が存在していた場合
			req.setAttribute("time_list", list);
			req.setAttribute("managerName", store.getManagerName());
		} else {   // 店舗が存在していなかった場合
			errors.put("storeId", "時間");
			req.setAttribute("errors", errors);
		}

		//JSPへフォワード 7
		req.getRequestDispatcher("shift_time_set.jsp").forward(req, res);
	}


}

