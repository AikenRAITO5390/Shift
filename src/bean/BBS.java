package bean;

import java.io.Serializable;


public class BBS implements Serializable {

//	投稿内容
	private String bbsText;

//	投稿ID
	private int bbsId;

//	店舗
	private Store store;

//	従業員
	private Worker worker;

//	投稿日
	private String bbsDate;

//	シフト作成者
	private String manager;


	public String getManager() {
		return manager;
	}

	public void setManager(String manager) {
		this.manager = manager;
	}



	public String getBbsText() {
		return bbsText;
	}

	public void setBbsText(String bbsText) {
		this.bbsText = bbsText;
	}

	public String getBbsDate() {
		return bbsDate;
	}

	public void setBbsDate(String bbsDate) {
		this.bbsDate = bbsDate;
	}

	public int getBbsId() {
		return bbsId;
	}

	public void setBbsId(int bbsId) {
		this.bbsId = bbsId;
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



}
