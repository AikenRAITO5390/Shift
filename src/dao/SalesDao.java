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






//	saveメソッド
	public boolean save(Sales sales)throws Exception{
//		データベースへのコネクションを確立
		Connection connection=getConnection();
//		プリペアードステートメント
		PreparedStatement statement = null;
		// 実行件数
		int count = 0;


		try{
//			データベースから売上情報を取得
			Sales sStore = get(sales.getStore(), sales.getDate());
//			売上が登録されていない場合は追加
			if (sStore == null){

//				プリペアードステートメントにインサートSQLをセット
				statement = connection.prepareStatement("insert into sales (store_id, sales, date) values (?,?,?)");
//				プリペアードステートメントに店舗情報、売上、日付をバインド
				statement.setString(1, sales.getStore().getStoreId());
				statement.setInt(2, sales.getDaySales());
				statement.setDate(3, sales.getDate());

//			売上が登録されている場合は更新
			}else{

//				プリペアードステートメントにアップデートSQLをセット
				statement = connection.prepareStatement("update sales set sales=? where store_id=?, date=?");
//				プリペアードステートメントに売上、店舗情報、日付をバインド
				statement.setInt(1, sales.getDaySales());
				statement.setString(2, sales.getStore().getStoreId());
				statement.setDate(3, sales.getDate());
			}


			//プリペアードステートメントを実行
			count = statement.executeUpdate ();


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
		if (count > 0) {
			//実行件数が1件以上ある場合
			return true;
		}else{
		    //実行件数が0件の場合
		    return false;
		}

	}





}


