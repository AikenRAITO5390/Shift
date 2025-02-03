package shiftmaker.main;

import java.text.SimpleDateFormat;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import bean.Shift;
import bean.Store;
import bean.Worker;
import dao.ShiftDao;
import dao.StoreDao;
import dao.WorkerDao;
import tool.Action;

public class PowerSettingAction extends Action{

	@Override
	public void execute(HttpServletRequest req, HttpServletResponse res) throws Exception  {
		ShiftDao shiftDao = new ShiftDao();
		StoreDao sDao = new StoreDao();
		HttpSession session = req.getSession();//セッション
		Store store_login = (Store)session.getAttribute("user");// ログインユーザーを取得
		Map<String, String> errors = new HashMap<>();
		Shift shift = null;
		WorkerDao wDao = new WorkerDao();

//ログイン情報でゲットする
		List<List<String>> list = sDao.Week_filter(store_login.getStoreId());
		List<Worker> worker = wDao.filterByStoreId(store_login.getStoreId());
		Store store = sDao.get(store_login.getStoreId());

		int PointMax = (worker.size()*5);

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
        SimpleDateFormat sdi = new SimpleDateFormat("yyyy年MM月");
        // 結果を表示

        calendar.setTime(firstDayOfNextMonth);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        List<Map<String, Integer>> dateList = new ArrayList<>();
        List<Map<String, Integer>> datepoint = new ArrayList<>();

        while (!calendar.getTime().after(lastDayOfNextMonth)) {
            java.util.Date utilDate = calendar.getTime();
            java.sql.Date sqlDate = new java.sql.Date(utilDate.getTime());
            LocalDate localDate = sqlDate.toLocalDate();

            DayOfWeek dayOfWeek = localDate.getDayOfWeek();
            int dayValue = dayOfWeek.getValue(); // 1 (月曜日) から 7 (日曜日) の数値を取得

            Map<String, Integer> dateMap = new HashMap<>();
            dateMap.put(sdf.format(utilDate), dayValue);
            dateList.add(dateMap);

            // 修正部分: java.util.Dateをjava.sql.Dateに変換して渡す
            Map<String, Integer> datePoi = shiftDao.Powerfilter(store_login.getStoreId(), sqlDate);
            datepoint.add(datePoi);

            calendar.add(Calendar.DAY_OF_MONTH, 1); // 次の日に進む
        }
        String ToDay = sdi.format(firstDayOfNextMonth);
        req.setAttribute("toDay", ToDay);
        req.setAttribute("dateList", dateList);
        req.setAttribute("datePoint", datepoint);
        req.setAttribute("pointMax",PointMax );
        req.setAttribute("managerName",store.getManagerName());

		//月～日の点数を取得、表示するためのもの

		if (list != null) {// 店舗が存在していた場合
			// リクエストに学生リストをセット
			// リクエストにデータをセット
			req.setAttribute("power_list", list);

		} else {// 店舗が存在していなかった場合
			req.setAttribute("errors", errors);
		}
		//JSPへフォワード 7
		req.getRequestDispatcher("power_setting.jsp").forward(req, res);
	}

	}


