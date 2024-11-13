package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import bean.Store;


public class StoreDao extends Dao{

	/**
	 * getメソッド　店舗IDを指定して店舗インスタンスを一件取得
	 *
	 * @param store_id
	 * @return　店舗クラスのインスタンス　存在しない場合はNull
	 * @throws Exception
	 */
	public Store get(String store_id) throws Exception {
		//店舗情報インスタンスを初期化
		Store store = new Store();
		//データベースのコネクションを確立
		Connection connection = getConnection();
		//プリペアードステートメント
		PreparedStatement statement = null;

		try{
			//プリペアードステートメントにSQL文をセット
			statement = connection.prepareStatement("sql");//後で書く
			//プリペアードステートメントに店舗IDをバインド（？の一個めに入る）
			statement.setString(1, store_id);
			//プリペアードステートメントを実行
			ResultSet rSet = statement.executeQuery();


			if(rSet.next()){
				//リザルトセットが存在する場合(検索結果が存在した場合)
				//店舗情報インスタンスに検索結果をセット
				store.setStoreId(rSet.getString("store_id"));
				store.setStoreName(rSet.getString("store_name"));
				store.setManagerId(rSet.getString("manager_id"));
				store.setManagerName(rSet.getString("manager_name"));
				store.setPassword(rSet.getString("password"));
				store.setEmail(rSet.getString("email"));
				store.setShiftTimeId(rSet.getString("work_time_id"));
				store.setWorkTimeStart(rSet.getDate("work_time_start"));
				store.setWorkTimeEnd(rSet.getDate("work_time_start"));

			} else {
//				リザルトセットが存在しない場合
//				店舗情報インスタンスにNullをセット
				store = null;
			}
		}catch (Exception e) {
			throw e;
		}finally {
//			プリペアードステートメントを閉じる
			if(statement != null) {
				try {
					statement.close();
				} catch (SQLException sqle) {
					throw sqle;
				}
			}

		}

		//コネクションを閉じる
		if (connection != null) {
			try {
				connection.close();
			} catch (SQLException sqle) {
				throw sqle;
			}
		}
	//storeに入れた情報を返す
	return store;

	}

	/**
	 * postFilterメソッド DB情報の一覧をリストへの格納処理。filterで使う
	 *
	 * @param rSet
	 * @return 店舗情報のリスト:List<Store> 存在しない場合は０件
	 * @throws Exception
	 */
	private List<Store> postFilter(ResultSet rSet)throws Exception{
//		空のリストを作成
		List<Store> list = new ArrayList<>();
		try{
			//リザルトセットを全件走査(データベースの登録件数分回る)
			while (rSet.next()) {
//				店舗情報インスタンスを初期化
				Store store = new Store();
//				店舗情報インスタンスに検索結果をセット
				store.setStoreId(rSet.getString("store_id"));
				store.setStoreName(rSet.getString("store_name"));
				store.setManagerId(rSet.getString("manager_id"));
				store.setManagerName(rSet.getString("manager_name"));
				store.setPassword(rSet.getString("password"));
				store.setEmail(rSet.getString("email"));
				store.setShiftTimeId(rSet.getString("work_time_id"));
				store.setWorkTimeStart(rSet.getDate("work_time_start"));
				store.setWorkTimeEnd(rSet.getDate("work_time_start"));
//				リストに追加
				list.add(store);
			}
		} catch (SQLException | NullPointerException e) {
			e.printStackTrace();
		}
		return list;
	}

	/**
	 * filterメソッド　店舗情報、シフト時間設定IDを指定して一覧を取得
	 * @param store
	 * @param work_time_id
	 * @return　店舗情報のリスト:List<Store> 存在しない場合０件
	 * @throws Exception
	 */
	public List<Store> filter(Store store,String work_time_id )throws Exception{
//		空のリストを作成
		List<Store> list = new ArrayList<>();
		//コネクションを確立
		Connection connection = getConnection();
		//プリペアードステートメント
		PreparedStatement statement = null;
		//リザルトセット
		ResultSet rSet = null;


		try{
//			プリペアードステートメントにSQL文をセット
			statement = connection.prepareStatement("sql");//後で書く
			//
			statement.setString(1, "x");
//			プリペアードステートメントを実行
			rSet = statement.executeQuery();
//			リストへの格納処理を実行
			list = postFilter(rSet);
		} catch (Exception e) {
			throw e;
		}finally {
			//プリペアードステート面とをとじる
			if (statement != null) {
				try {
					statement.close();
				} catch (SQLException sqle) {
					throw sqle;
				}
			}
		}
		//コネクションを閉じる
		if (connection != null) {
			try {
				connection.close();
			} catch (SQLException sqle) {
				throw sqle;
			}
		}
		return list;

	}

	public boolean save(Store store)throws Exception{
		//データベースへのコネクションを確立
		Connection connection = getConnection();
		//プリペアードステート面と
		PreparedStatement statement = null;
		//実行件数
		int count = 0;

		try{
//			データベースから店舗情報を取得
			Store old = get(store.getStoreId());
			if (old == null) {
//				店舗情報が存在しなかった場合
//				プリペアードステートメントにINSERT文をセット
				statement = connection.prepareStatement("sql");
//				プリペアードステートメントに値をバウンド
			}
		}
	}
}
