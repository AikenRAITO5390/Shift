package tool;

import java.time.LocalDate;
import java.time.YearMonth;
import java.util.ArrayList;
import java.util.List;

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




}
