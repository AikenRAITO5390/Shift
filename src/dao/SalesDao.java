package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Date;

import bean.Sales;
import bean.Store;

public class SalesDao extends Dao {


//	getメソッド
	public Sales get(Store store, Date date)throws Exception{
//		日商売上をインスタンス化
		Sales sales=new Sales();
//		データベースへのコネクションを確立
		Connection connection=getConnection();
//		プリペアードステートメント
		PreparedStatement statement = null;

		try｛
//			プリペアードステートメントにSQLをセット
			statement = connection.prepareStatement("select * from sales where store_id=? and date =?");
//			プリペアードステートメントに店舗IDと年月日をバインド
			statement.setString(1, store.getStoreId());
			statement.setString(2, date);
//			プリペアードステートメントを実行
			ResultSet rSet = statement.executeQuery();

			StoreDao storeDao= new StoreDao();

		｝

	}
}
