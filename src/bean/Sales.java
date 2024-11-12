package bean;

import java.io.Serializable;
import java.util.Date;

import org.apache.catalina.Store;

public class Sales implements Serializable {

//	日商売上
	private int sales;

//	年月日
	private Date date;

//	店舗
	private Store store;

	public int getSales() {
		return sales;
	}

	public void setSales(int sales) {
		this.sales = sales;
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
