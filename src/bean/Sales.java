package bean;

import java.io.Serializable;
import java.sql.Date;

public class Sales implements Serializable {

//	日商売上
	private int daySales;

//	年月日
	private Date date;

//	店舗
	private Store store;


	public int getDaySales() {
		return daySales;
	}

	public void setDaySales(int daySales) {
		this.daySales = daySales;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public Store getStore() {
		return store;
	}

	public void setStore(Store store) {
		this.store = store;
	}



}
