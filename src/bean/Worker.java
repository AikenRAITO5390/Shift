package bean;

import java.io.Serializable;

import org.apache.catalina.Store;

public class Worker extends UserWorker implements Serializable {

//	従業員ID
	private String workerId;

//	従業員名
	private String workerName;

//	生年月日
	private String workerDate;

//	住所
	private String workerAddress;

//	電話番号
	private String workerTel;

//	パスワード
	private String workerPassword;

//	従業員判別
	private boolean workerJudge;

//	パワーバランス
	private String workerScore;

//	ポジション
	private String workerPosition;

//	店舗
	private Store store;


//	ゲッターセッター



	public String getWorkerId() {
		return workerId;
	}

	public void setWorkerId(String workerId) {
		this.workerId = workerId;
	}

	public String getWorkerName() {
		return workerName;
	}

	public void setWorkerName(String workerName) {
		this.workerName = workerName;
	}

	public String getWorkerDate() {
		return workerDate;
	}

	public void setWorkerDate(String workerDate) {
		this.workerDate = workerDate;
	}

	public String getWorkerAddress() {
		return workerAddress;
	}

	public void setWorkerAddress(String workerAddress) {
		this.workerAddress = workerAddress;
	}

	public String getWorkerTel() {
		return workerTel;
	}

	public void setWorkerTel(String workerTel) {
		this.workerTel = workerTel;
	}


	public String getWorkerPassword() {
		return workerPassword;
	}

	public void setWorkerPassword(String workerPassword) {
		this.workerPassword = workerPassword;
	}

	public boolean isWorkerJudge() {
		return workerJudge;
	}

	public void setWorkerJudge(boolean workerJudge) {
		this.workerJudge = workerJudge;
	}

	public String getWorkerScore() {
		return workerScore;
	}

	public void setWorkerScore(String workerScore) {
		this.workerScore = workerScore;
	}

	public String getWorkerPosition() {
		return workerPosition;
	}

	public void setWorkerPosition(String workerPosition) {
		this.workerPosition = workerPosition;
	}

	public Store getStore() {
		return store;
	}

	public void setStore(Store store) {
		this.store = store;
	}



}
