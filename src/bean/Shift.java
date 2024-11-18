package bean;

import java.io.Serializable;
import java.sql.Date;
import java.sql.Timestamp;

public class Shift implements Serializable {

//	年月日
	private Date shiftDate;

//  希望シフト点数
	private int shiftScore;

//	希望シフト時間設定ID
	private String shiftHopeTimeId;

//	希望その他開始時間
	private Timestamp shiftHopeTimeStart;

//	希望その他終了時間
	private Timestamp shiftHopeTimeEnd;

//	シフト時間設定ID
	private String workTimeId;

//	その他開始時間
	private Timestamp shiftTimeStart;

//	その他終了時間
	private Timestamp shiftTimeEnd;

//	シフト番号
	private String shiftId;

//	店舗
	private Store store;

//	従業員
	private Worker worker;





//	ゲッターセッター


	public Date getShiftDate() {
		return shiftDate;
	}

	public int getShiftScore() {
		return shiftScore;
	}

	public void setShiftScore(int shiftScore) {
		this.shiftScore = shiftScore;
	}

	public void setShiftDate(Date shiftDate) {
		this.shiftDate = shiftDate;
	}

	public Store getStore() {
		return store;
	}

	public void setStore(Store store) {
		this.store = store;
	}

	public Worker getWorker() {
		return worker;
	}

	public void setWorker(Worker worker) {
		this.worker = worker;
	}

	public String getShiftHopeTimeId() {
		return shiftHopeTimeId;
	}

	public void setShiftHopeTimeId(String shiftHopeTimeId) {
		this.shiftHopeTimeId = shiftHopeTimeId;
	}

	public Timestamp getShiftHopeTimeStart() {
		return shiftHopeTimeStart;
	}

	public void setShiftHopeTimeStart(Timestamp shiftHopeTimeStart) {
		this.shiftHopeTimeStart = shiftHopeTimeStart;
	}

	public Timestamp getShiftHopeTimeEnd() {
		return shiftHopeTimeEnd;
	}

	public void setShiftHopeTimeEnd(Timestamp shiftHopeTimeEnd) {
		this.shiftHopeTimeEnd = shiftHopeTimeEnd;
	}

	public String getWorkTimeId() {
		return workTimeId;
	}

	public void setWorkTimeId(String workTimeId) {
		this.workTimeId = workTimeId;
	}

	public Timestamp getShiftTimeStart() {
		return shiftTimeStart;
	}

	public void setShiftTimeStart(Timestamp shiftTimeStart) {
		this.shiftTimeStart = shiftTimeStart;
	}

	public Timestamp getShiftTimeEnd() {
		return shiftTimeEnd;
	}

	public void setShiftTimeEnd(Timestamp shiftTimeEnd) {
		this.shiftTimeEnd = shiftTimeEnd;
	}

	public String getShiftId() {
		return shiftId;
	}

	public void setShiftId(String shiftId) {
		this.shiftId = shiftId;
	}


}
