package shiftmaker.main;

import java.sql.Time;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import bean.Store;
import dao.StoreDao;
import tool.Action;

public class ShiftTimeSettingAction extends Action {
	@Override
	public void execute(HttpServletRequest req, HttpServletResponse res) throws Exception {
		//ローカル変数の宣言 1
		StoreDao sDao = new StoreDao();
		HttpSession session = req.getSession();//セッション
		Store store_login = (Store)session.getAttribute("user");// ログインユーザーを取得
		Map<String, String> errors = new HashMap<>();

		//リクエストパラメータ―の取得 2
        String[] workTimeId = req.getParameterValues("workTimeId");
		String[] workTimeEnd = req.getParameterValues("workTimeEnd");
		String[] workTimeStart = req.getParameterValues("workTimeStart");


			Time[] workTimeEndTimes = new Time[workTimeEnd.length];
			Time[] workTimeStartTimes = new Time[workTimeStart.length];
			System.out.println("★★１");

			for (int i = 0; i < workTimeEnd.length; i++) {
			    workTimeEndTimes[i] = Time.valueOf(workTimeEnd[i]);
			    workTimeStartTimes[i] = Time.valueOf(workTimeStart[i]);
			}
			System.out.println("★★2");
		    // ここでworkTimeIdDates, workTimeEnds, workTimeStartsを使って処理を行う


		//DBからデータ取得 3
		List<Store> list = sDao.filterStore(store_login.getStoreId());
		System.out.println("★★3");
		//ビジネスロジック 4
		//DBへデータ保存 5
		//条件で4～5が分岐
		if (list != null) {
			int i=0;
			for(Store store : list){
				System.out.println("★★4");

			// インスタンスに値をセット
				store.setStoreId(store_login.getStoreId());
				store.setWorkTimeId(workTimeId[i]);
				store.setWorkTimeStart(workTimeStartTimes[i]);
				store.setWorkTimeEnd(workTimeEndTimes[i]);

				System.out.println("★★5");
			// 学生を保存
			sDao.save_Time(store);
			i += 1;
			}
		} else {
		}

		//エラーがあったかどうかで手順6~7の内容が分岐


		if(!errors.isEmpty()){//エラーがあった場合、更新画面へ戻る
			// リクエスト属性をセット
			req.setAttribute("errors", errors);
			req.setAttribute("time_list", list);
			req.getRequestDispatcher("shift_time_set.jsp").forward(req, res);
			return;
		}

		req.getRequestDispatcher("shift_time_set_result.jsp").forward(req, res);
	}
}

