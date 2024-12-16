package tool;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.time.LocalDate;
import java.time.YearMonth;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import dao.Dao;

public class CalendeCreate extends Dao{

	public List<LocalDate> Calender_side(int year, int month)throws Exception{

		YearMonth yearMonth = YearMonth.of(year, month);

        List<LocalDate> dates = new ArrayList<>();
        for (int i = 1; i <= yearMonth.lengthOfMonth(); i++) {
            dates.add(yearMonth.atDay(i));
        }
        return dates;

	}


	    public List<LocalDate> Calender(int year, int month)throws Exception{

	        YearMonth yearMonth = YearMonth.of(year, month);
	        LocalDate firstDayOfMonth = yearMonth.atDay(1);

	        List<LocalDate> dates = new ArrayList<>();
	        for (int i = 0; i < firstDayOfMonth.getDayOfWeek().getValue(); i++) {
	            dates.add(null); // 空の日付で埋める（1日の曜日まで）
	        }
	        for (int i = 1; i <= yearMonth.lengthOfMonth(); i++) {
	            dates.add(yearMonth.atDay(i));
	        }
	        return dates;

	    }


	    // 使うかも？byもりや
	    // DBに基づいたカレンダーを生成するメソッド
	    public Map<LocalDate, String> generateCalendarWithDBInfo(int year, int month, String storeId, String workerId) throws Exception {
	        // 月の最初の日と最後の日を取得
	        YearMonth yearMonth = YearMonth.of(year, month);
	        LocalDate firstDayOfMonth = yearMonth.atDay(1);
	        LocalDate lastDayOfMonth = yearMonth.atEndOfMonth();

	        // 空のカレンダー日付リストを作成
	        List<LocalDate> dates = new ArrayList<>();
	        // 最初の日の曜日に合わせて空の日付を追加
	        for (int i = 0; i < firstDayOfMonth.getDayOfWeek().getValue(); i++) {
	            dates.add(null); // 空の日付を埋める
	        }
	        // 実際の日付を追加
	        for (int i = 1; i <= yearMonth.lengthOfMonth(); i++) {
	            dates.add(yearMonth.atDay(i));
	        }

	        // シフトデータを格納するマップ
	        Map<LocalDate, String> shiftData = new HashMap<>();

	        Connection connection = getConnection();
	        PreparedStatement statement = null;
	        ResultSet resultSet = null;

	        try {
	            // 期間内のシフト情報をDBから取得
	            String sql = "SELECT shift_date, shifthope_time_id FROM shift WHERE store_id = ? AND worker_id = ? AND shift_date BETWEEN ? AND ?";
	            statement = connection.prepareStatement(sql);
	            statement.setString(1, storeId);
	            statement.setString(2, workerId);
	            statement.setDate(3, Date.valueOf(firstDayOfMonth));
	            statement.setDate(4, Date.valueOf(lastDayOfMonth));

	            resultSet = statement.executeQuery();

	            // DBの結果を日付ごとにマッピング
	            while (resultSet.next()) {
	                Date shiftDate = resultSet.getDate("shift_date");
	                String shifthope_time_id = resultSet.getString("shifthope_time_id");

	                // LocalDateに対応する情報をマップに追加
	                shiftData.put(shiftDate.toLocalDate(), shifthope_time_id);
	                // 確認用
	            	System.out.println("tool側shiftData：" + shiftData);
	            }

	            // シフトデータを日付順に並び替える
	            Map<LocalDate, String> sortedShiftData = new TreeMap<>(shiftData);

	            // カレンダーにシフト情報を挿入（LinkedHashMapを使用して順序を保持）
	            Map<LocalDate, String> calendarWithShifts = new LinkedHashMap<>();
	            for (LocalDate date : sortedShiftData.keySet()) {
	                calendarWithShifts.put(date, sortedShiftData.get(date));
	                // 確認用
	                System.out.println("tool側calendarWithShifts：" + calendarWithShifts);
	            }

	            return calendarWithShifts;

	        } finally {
	            if (resultSet != null) resultSet.close();
	            if (statement != null) statement.close();
	            if (connection != null) connection.close();
	        }
	    }


}
