package tool;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.YearMonth;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CalendeCreate {

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
	    public List<String> generateCalendarWithDBInfo(int year, int month, String storeId, String workerId) throws Exception {
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

	        // カレンダー用のエントリを格納するリスト
	        List<String> calendarEntries = new ArrayList<>();
	        Map<LocalDate, String> shiftData = new HashMap<>();

	        Connection connection = null;
	        PreparedStatement statement = null;
	        ResultSet resultSet = null;

	        try {
	            connection = getConnection(); // 必要に応じて接続管理を実装

	            // 期間内のシフト情報をDBから取得
	            String sql = "SELECT shift_date, shifthope_time_id, shifthope_time_start, shifthope_time_end "
	                    + "FROM shift WHERE store_id = ? AND worker_id = ? AND shift_date BETWEEN ? AND ?";
	            statement = connection.prepareStatement(sql);
	            statement.setString(1, storeId);
	            statement.setString(2, workerId);
	            statement.setDate(3, Date.valueOf(firstDayOfMonth));
	            statement.setDate(4, Date.valueOf(lastDayOfMonth));

	            resultSet = statement.executeQuery();

	            // DBの結果を日付ごとにマッピング
	            while (resultSet.next()) {
	                Date shiftDate = resultSet.getDate("shift_date");
	                String shiftHopeTimeId = resultSet.getString("shifthope_time_id");
	                Timestamp shiftStartTime = resultSet.getTimestamp("shifthope_time_start");
	                Timestamp shiftEndTime = resultSet.getTimestamp("shifthope_time_end");

	                // シフトの情報をエントリとして作成
	                String entry = (shiftHopeTimeId != null ? shiftHopeTimeId : "") +
	                        (shiftStartTime != null && shiftEndTime != null
	                                ? " (" + shiftStartTime + " - " + shiftEndTime + ")"
	                                : "");

	                // LocalDateに対応するシフト情報をマップに追加
	                shiftData.put(shiftDate.toLocalDate(), entry);
	            }

	            // カレンダーにシフト情報を挿入
	            for (LocalDate date : dates) {
	                if (date == null) {
	                    calendarEntries.add(""); // 空のセル
	                } else {
	                    // もしその日にシフト情報があれば、それを追加。なければ空の文字列を追加
	                    calendarEntries.add(shiftData.getOrDefault(date, date.toString()));
	                }
	            }

	        } finally {
	            if (resultSet != null) resultSet.close();
	            if (statement != null) statement.close();
	            if (connection != null) connection.close();
	        }

	        return calendarEntries;
	    }

	    // DB接続取得メソッド（実装例）
	    private Connection getConnection() throws Exception {
	        // 実際のDB接続ロジックをここに記載
	        return null; // ダミー値
	    }




}
