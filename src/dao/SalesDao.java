package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
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

		try{
//			プリペアードステートメントにSQLをセット
			statement = connection.prepareStatement("select * from sales where store_id=? and date =?");
//			プリペアードステートメントに店舗IDと年月日をバインド
			statement.setString(1, store.getStoreId());
			statement.setDate(2, new java.sql.Date(date.getTime()));
//			プリペアードステートメントを実行
			ResultSet rSet = statement.executeQuery();
//			店舗DAOを初期化
			StoreDao storeDao= new StoreDao();

			if (rSet.next()) {
//				リザルトセットが存在する場合
//				日商売上インスタンスに結果をセット
				sales.setDaySales(rSet.getInt("sales"));
				sales.setDate(rSet.getDate("date"));
				sales.setStore(storeDao.get(rSet.getString("store_id")));
			} else {
//				リザルトセットが存在しない場合
//				日商売上インスタンスにnullをセット
				sales = null;
			}

		} catch (Exception e){
			throw e;
		} finally {
//			プリペアードステートメントを閉じる
			if (statement != null) {
				try {
					statement.close();
				} catch (SQLException sqle) {
					throw sqle;
				}
			}
//			コネクションを閉じる
			if (connection != null) {
				try {
					connection.close();
				} catch (SQLException sqle) {
					throw sqle;
				}
			}
		}
		return sales;

	}
}


