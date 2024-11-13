package bean;

import java.io.Serializable;
import java.util.Date;

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
	private Date workTimeStart;

//	シフト終了時間
	private Date workTimeEnd;

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

	public Date getWorkTimeStart() {
		return workTimeStart;
	}

	public void setWorkTimeStart(Date workTimeStart) {
		this.workTimeStart = workTimeStart;
	}

	public Date getWorkTimeEnd() {
		return workTimeEnd;
	}

	public void setWorkTimeEnd(Date workTimeEnd) {
		this.workTimeEnd = workTimeEnd;
	}



}


