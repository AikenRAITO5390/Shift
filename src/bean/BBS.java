package bean;

import java.io.Serializable;

import org.apache.catalina.Store;

public class BBS implements Serializable {

//	投稿内容
	private String bbsText;


//	投稿ID
	private String bbsId;

//	店舗
	private Store store;

//	従業員
	private Worker worker;

	public String getBbsText() {
		return bbsText;
	}

	public void setBbsText(String bbsText) {
		this.bbsText = bbsText;
	}

	public String getBbsId() {
		return bbsId;
	}

	public void setBbsId(String bbsId) {
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
