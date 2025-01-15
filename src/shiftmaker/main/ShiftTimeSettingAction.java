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


		Store store_managerName = sDao.get(store_login.getStoreId());
		req.setAttribute("managerName", store_managerName.getManagerName());


		//リクエストパラメータ―の取得 2
		//A～Dの四つをもらってくる箱
		String[] workTimeId = new String[4];
		String[] workTimeStart = new String[4];
		String[] workTimeEnd = new String[4];

		//四つ分ゲットしてくる
		for (int i = 0; i < 4; i++) {
		    workTimeId[i] = req.getParameter("workTimeId_" + i);
		    workTimeStart[i] = req.getParameter("workTimeStart_" + i);
		    workTimeEnd[i] = req.getParameter("workTimeEnd_" + i);
		}

		// 営業時間取得
		String storeTimeStart = req.getParameter("storeTimeStart");
		String storeTimeEnd = req.getParameter("storeTimeEnd");

		// Time型変換
		Time storeTimeStarts = Time.valueOf(storeTimeStart);
		Time storeTimeEnds = Time.valueOf(storeTimeEnd);


//String型でもってきてるのでTime型に変更する
			Time[] workTimeEndTimes = new Time[workTimeEnd.length];
			Time[] workTimeStartTimes = new Time[workTimeStart.length];

			for (int i = 0; i < workTimeEnd.length; i++) {
			    workTimeEndTimes[i] = Time.valueOf(workTimeEnd[i]);
			    workTimeStartTimes[i] = Time.valueOf(workTimeStart[i]);
			}

		    // ここでworkTimeIdDates, workTimeEnds, workTimeStartsを使って処理を行う


		//DBからデータ取得 3
		List<Store> list = sDao.filterStore(store_login.getStoreId());

		//workTimeIdの数分（A~Dの四つ分やる）
		if (workTimeId != null) {
		    for (int i = 0; i < workTimeId.length; i++) {


		        // 新しいStoreインスタンスを作成または既存のリストから取得
		        Store store;
		        if (i < list.size()) {
		            store = list.get(i);
		        } else {
		            store = new Store(); // 必要に応じて新しいインスタンスを作成
		        }

		        // インスタンスに値をセット
		        //　新規作成の可能性もあるのでstoreの中身全てセットする
		        store.setStoreId(store_login.getStoreId());
		        store.setStoreName(store_login.getStoreName());
		        store.setManagerName(store_login.getManagerName());
		        store.setManagerId(store_login.getManagerId());
		        store.setPassword(store_login.getPassword());
		        store.setEmail(store_login.getEmail());
		        store.setWorkTimeId(workTimeId[i]);
		        store.setWorkTimeStart(workTimeStartTimes[i]);
		        store.setWorkTimeEnd(workTimeEndTimes[i]);
		        store.setWorkWeekScore(store_login.getWorkWeekScore());
		        store.setWeekScore(store_login.getWeekScore());
		        store.setStoreTimeStart(storeTimeStarts);
		        store.setStoreTimeEnd(storeTimeEnds);

		        System.out.println("★★5");

		        // 保存
		        sDao.save_Time(store);
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

