package bean;

import java.io.Serializable;
import java.util.Date;

import org.apache.catalina.Store;

import javafx.concurrent.Worker;

public class Shift implements Serializable {

//	年月日
	private Date shiftDate;

//	希望シフト時間設定ID
	private String shiftHopeTimeId;

//	希望その他開始時間
	private Date shiftHopeTimeStart;

//	希望その他終了時間
	private Date shiftHopeTimeEnd;

//	シフト時間設定ID
	private String workTimeId;

//	その他開始時間
	private Date shiftTimeStart;

//	その他終了時間
	private Date shiftTimeEnd;

//	シフト番号
	private int shiftId;

//	店舗
	private Store store;

//	従業員
	private Worker worker;





//	ゲッターセッター


	public Date getShiftDate() {
		return shiftDate;
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

	public Date getShiftHopeTimeStart() {
		return shiftHopeTimeStart;
	}

	public void setShiftHopeTimeStart(Date shiftHopeTimeStart) {
		this.shiftHopeTimeStart = shiftHopeTimeStart;
	}

	public Date getShiftHopeTimeEnd() {
		return shiftHopeTimeEnd;
	}

	public void setShiftHopeTimeEnd(Date shiftHopeTimeEnd) {
		this.shiftHopeTimeEnd = shiftHopeTimeEnd;
	}

	public String getWorkTimeId() {
		return workTimeId;
	}

	public void setWorkTimeId(String workTimeId) {
		this.workTimeId = workTimeId;
	}

	public Date getShiftTimeStart() {
		return shiftTimeStart;
	}

	public void setShiftTimeStart(Date shiftTimeStart) {
		this.shiftTimeStart = shiftTimeStart;
	}

	public Date getShiftTimeEnd() {
		return shiftTimeEnd;
	}

	public void setShiftTimeEnd(Date shiftTimeEnd) {
		this.shiftTimeEnd = shiftTimeEnd;
	}

	public int getShiftId() {
		return shiftId;
	}

	public void setShiftId(int shiftId) {
		this.shiftId = shiftId;
	}


}
