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

public class PowerSettingResultAction extends Action {
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
		String[] WeekScore = new String[7];
		String[] WorkWeekScore = new String[7];

		int[] weekScoreInts = new int[7];
		int[] workWeekScoreInts = new int[7];

		//7sつ分ゲットしてくる
		for (int i = 0; i < 7; i++) {
			WeekScore[i] = req.getParameter("WeekScore_" + i);
			WorkWeekScore[i] = req.getParameter("WorkWeekScore_" + i);



//String型でもってきてるのでTime型に変更する
		 if (WeekScore[i] != null && !WeekScore[i].isEmpty()) {
		        weekScoreInts[i] = Integer.parseInt(WeekScore[i]);
		    }
		    if (WorkWeekScore[i] != null && !WorkWeekScore[i].isEmpty()) {
		        workWeekScoreInts[i] = Integer.parseInt(WorkWeekScore[i]);
		    }
		}
		    // ここでworkTimeIdDates, workTimeEnds, workTimeStartsを使って処理を行う


		//DBからデータ取得 3
		List<Store> list = sDao.filterStore(store_login.getStoreId());

		//workTimeIdの数分（A~Dの四つ分やる）
		if (workWeekScoreInts != null) {
		    for (int i = 0; i < workWeekScoreInts.length; i++) {


		        // 新しいStoreインスタンスを作成または既存のリストから取得
		        Store store;
		        if (i < list.size()) {
		            store = list.get(i);
		        } else {
		            store = new Store(); // 必要に応じて新しいインスタンスを作成
		        }
		        String woekTimeId = "F";

		        // インスタンスに値をセット
		        //　新規作成の可能性もあるのでstoreの中身全てセットする
		        store.setStoreId(store_login.getStoreId());
		        store.setStoreName(store_login.getStoreName());
		        store.setManagerName(store_login.getManagerName());
		        store.setManagerId(store_login.getManagerId());
		        store.setPassword(store_login.getPassword());
		        store.setEmail(store_login.getEmail());
		        store.setWorkTimeId(woekTimeId);
		        store.setWorkTimeStart(store_login.getWorkTimeStart());
		        store.setWorkTimeEnd(store_login.getWorkTimeEnd());
		        store.setWorkWeekScore(workWeekScoreInts[i]);
		        store.setWeekScore(weekScoreInts[i]);


		        // 学生を保存
		        sDao.save_Power(store);
		    }

		} else {
		}

		//エラーがあったかどうかで手順6~7の内容が分岐


		if(!errors.isEmpty()){//エラーがあった場合、更新画面へ戻る
			// リクエスト属性をセット
			req.setAttribute("errors", errors);
			req.setAttribute("time_list", list);
			req.getRequestDispatcher("power_setting.jsp").forward(req, res);
			return;
		}

		req.getRequestDispatcher("power_setting_result.jsp").forward(req, res);
	}
}

