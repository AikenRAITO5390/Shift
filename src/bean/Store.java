package bean;

import java.io.Serializable;
import java.sql.Time;

public class Store extends User implements Serializable {

//	店舗ID
	private String storeId;

//	店舗名
	private String storeName;

//	シフト作成者ID
	private String managerId;

//	シフト作成者名
	private String managerName;

//	パスワード
	private String password;

//	メールアドレス
	private String email;

//	シフト時間設定ID
	private String workTimeId;

//	シフト開始時間
	private Time workTimeStart;

//	シフト終了時間
	private Time workTimeEnd;

//	曜日別シフト点数
	private int workWeekScore;

//	シフト点数
	private int weekScore;

//	営業開始時間
	private Time storeTimeStart;

//	営業終了時間
	private Time storeTimeEnd;


	public Time getStoreTimeStart() {
		return storeTimeStart;
	}

	public void setStoreTimeStart(Time storeTimeStart) {
		this.storeTimeStart = storeTimeStart;
	}

	public Time getStoreTimeEnd() {
		return storeTimeEnd;
	}

	public void setStoreTimeEnd(Time storeTimeEnd) {
		this.storeTimeEnd = storeTimeEnd;
	}

	public String getStoreId() {
		return storeId;
	}

	public void setStoreId(String storeId) {
		this.storeId = storeId;
	}

	public String getStoreName() {
		return storeName;
	}

	public void setStoreName(String storeName) {
		this.storeName = storeName;
	}

	public String getManagerId() {
		return managerId;
	}

	public void setManagerId(String managerId) {
		this.managerId = managerId;
	}

	public String getManagerName() {
		return managerName;
	}

	public void setManagerName(String managerName) {
		this.managerName = managerName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getWorkTimeId() {
		return workTimeId;
	}

	public void setWorkTimeId(String workTimeId) {
		this.workTimeId = workTimeId;
	}

	public Time getWorkTimeStart() {
		return workTimeStart;
	}

	public void setWorkTimeStart(Time workTimeStart) {
		this.workTimeStart = workTimeStart;
	}

	public Time getWorkTimeEnd() {
		return workTimeEnd;
	}

	public void setWorkTimeEnd(Time workTimeEnd) {
		this.workTimeEnd = workTimeEnd;
	}

	public int getWorkWeekScore() {
		return workWeekScore;
	}

	public void setWorkWeekScore(int workWeekScore) {
		this.workWeekScore = workWeekScore;
	}

	public int getWeekScore() {
		return weekScore;
	}

	public void setWeekScore(int weekScore) {
		this.weekScore = weekScore;
	}


}


