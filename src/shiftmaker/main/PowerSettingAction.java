package shiftmaker.main;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import bean.Store;
import dao.StoreDao;
import tool.Action;

public class PowerSettingAction extends Action{

	@Override
	public void execute(HttpServletRequest req, HttpServletResponse res) throws Exception  {
		StoreDao sDao = new StoreDao();
		HttpSession session = req.getSession();//セッション
		Store store_login = (Store)session.getAttribute("user");// ログインユーザーを取得
		Map<String, String> errors = new HashMap<>();

//ログイン情報でゲットする
		List<List<String>> list = sDao.Week_filter(store_login.getStoreId());

		//月、日付を取得する方法
        Calendar calendar = Calendar.getInstance();

        // 次の月の最初の日付を設定
        calendar.set(Calendar.MONTH, calendar.get(Calendar.MONTH) + 1);
        calendar.set(Calendar.DAY_OF_MONTH, 1);
        java.util.Date firstDayOfNextMonth = calendar.getTime();

        // 次の月の最後の日付を設定
        int lastDay = calendar.getActualMaximum(Calendar.DAY_OF_MONTH);
        calendar.set(Calendar.DAY_OF_MONTH, lastDay);
        java.util.Date lastDayOfNextMonth = calendar.getTime();

        // 日付フォーマットを指定
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

        // 結果を表示
        System.out.println("次の月の最初の日付: " + sdf.format(firstDayOfNextMonth));
        System.out.println("次の月の最後の日付: " + sdf.format(lastDayOfNextMonth));

        calendar.setTime(firstDayOfNextMonth);
        while (!calendar.getTime().after(lastDayOfNextMonth)) {
            System.out.println(sdf.format(calendar.getTime()));
            calendar.add(Calendar.DAY_OF_MONTH, 1);
        }






		//月～日の点数を取得、表示するためのもの

		if (list != null) {// 店舗が存在していた場合
			// リクエストに学生リストをセット
			// リクエストにデータをセット
			req.setAttribute("power_list", list);
			System.out.println(list);
		} else {// 店舗が存在していなかった場合
			req.setAttribute("errors", errors);
		}
		//JSPへフォワード 7
		req.getRequestDispatcher("power_setting.jsp").forward(req, res);
	}

	}


