package shiftmaker.main;

import java.time.LocalDate;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.ShiftDao;
import dao.StoreDao;
import dao.WorkerDao;
import tool.Action;

public class ShiftAction extends Action{


	@Override
	public void execute(HttpServletRequest req, HttpServletResponse res) throws Exception {

//		Daoの初期化
		ShiftDao  shDao = new ShiftDao();
		StoreDao  stDao = new StoreDao();
		WorkerDao wDao  = new WorkerDao();

//		現在の日付から次の月を計算
		LocalDate todaysDate = LocalDate.now();
		int year = todaysDate.getYear();
		int month = todaysDate.getMonthValue();
	}
}
