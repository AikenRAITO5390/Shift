package dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import bean.Store;


public class StoreDao extends Dao{

	public Store login(String id, String password) throws Exception {

		Store store = null;

    	//manager_idとpasswordを使ってシフト作成者を取得
        String sql = "SELECT * FROM STORE WHERE manager_id = ? and password = ?";

        Connection con = getConnection();
	    PreparedStatement ps = con.prepareStatement(sql);
	    ps.setString(1, id);
	    ps.setString(2, password);

        ResultSet rs = ps.executeQuery();

        if (rs.next()) { // 認証成功の場合
            // Storeオブジェクトを作成して設定
            store = new Store();
            store.setStoreId(rs.getString("store_id"));
            store.setPassword(rs.getString("password"));
            store.setManagerName(rs.getString("manager_name"));
            store.setManagerId(rs.getString("manager_id"));
        }

        ps.close();
        rs.close();

        //ログインされたworkerのデータを返す
        return store;

	}

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
			String sql = "SELECT * FROM store WHERE store_id = ?";
			//プリペアードステートメントにSQL文をセット
			statement = connection.prepareStatement(sql);//後で書く
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
				store.setWorkTimeId(rSet.getString("work_time_id"));
				store.setWorkTimeStart(rSet.getTime("work_time_start"));
				store.setWorkTimeEnd(rSet.getTime("work_time_end"));
				store.setWorkWeekScore(rSet.getInt("work_week_score"));
				store.setWeekScore(rSet.getInt("week_score"));

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
				store.setWorkTimeId(rSet.getString("work_time_id"));
				store.setWorkTimeStart(rSet.getTime("work_time_start"));
				store.setWorkTimeEnd(rSet.getTime("work_time_start"));
				store.setWorkWeekScore(rSet.getInt("work_week_score"));
				store.setWeekScore(rSet.getInt("week_score"));
//				リストに追加
				list.add(store);
			}
		} catch (SQLException | NullPointerException e) {
			e.printStackTrace();
		}
		return list;
	}

	/**
	 * filterメソッド　店舗情報IDを指定して一覧を取得
	 * @param store
	 * @return　店舗情報のリスト:List<Store> 存在しない場合０件
	 * @throws Exception
	 */
	public List<Store> filterStore(String storeId)throws Exception{
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
			statement = connection.prepareStatement("select * from STORE where STORE_ID=?");//後で書く
			//
			statement.setString(1, storeId);
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

	public List<Store> filter(Store store, String work_time_id)throws Exception{
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
			statement.setString(1, store.getStoreId());
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


	/**
	 * filterメソッド　IDを指定して一覧を取得
	 * @param manager_id
	 * @return　店舗名のリスト:List<Store> 存在しない場合０件
	 * @throws Exception
	 */
	public List<String> filter(String manager_id) throws Exception {
	    // 空のリストを作成して、結果を格納する
	    List<String> storeList = new ArrayList<>();
	    // コネクションを確立
	    Connection connection = getConnection();
	    PreparedStatement statement = null;
	    ResultSet rSet = null;

	    try {
	        // SQL文を準備して店舗名に基づくフィルターを適用
	        String sql = "SELECT store_name FROM STORE WHERE manager_id = ?";
	        statement = connection.prepareStatement(sql);
	        // プレースホルダーにstoreNameをセット
	        statement.setString(1, manager_id);
	        // クエリを実行
	        rSet = statement.executeQuery();

	        while(rSet.next()){
	        	storeList.add(rSet.getString("store_name"));
	        }

	    } catch (Exception e) {
	        e.printStackTrace();
	        throw e;
	    } finally {
	        // リソースを閉じる
	        if (rSet != null) {
	            try {
	                rSet.close();
	            } catch (SQLException e) {
	                e.printStackTrace();
	            }
	        }
	        if (statement != null) {
	            try {
	                statement.close();
	            } catch (SQLException e) {
	                e.printStackTrace();
	            }
	        }
	        if (connection != null) {
	            try {
	                connection.close();
	            } catch (SQLException e) {
	                e.printStackTrace();
	            }
	        }
	    }

	    return storeList;
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
				statement = connection.prepareStatement("insertsql");
//				プリペアードステートメントに値をバウンド
				statement.setString(1, store.getStoreId());
				statement.setString(2, store.getStoreName());
				statement.setString(3, store.getManagerId());
				statement.setString(4, store.getManagerName());
				statement.setString(5, store.getPassword());
				statement.setString(6, store.getEmail());
				statement.setString(7, store.getWorkTimeId());
				//引数２をDate型にキャスト。これでいけるかはまだわからん
				statement.setDate(8, (Date) store.getWorkTimeStart());
				statement.setDate(9, (Date) store.getWorkTimeEnd());
				statement.setInt(10, store.getWorkWeekScore());
				statement.setInt(11, store.getWeekScore());


			}else {
				//学生が存在した場合
				//プリペアードステートメントにUPDATE文をセット
				statement = connection
						.prepareStatement("update store set store_id=?, manager_name=?, password=?, email=?, store_name=? where store_id=?");
				//プリペアードステートメントに値をバインド
				statement.setString(1, store.getStoreId());
				statement.setString(2, store.getManagerName());
				statement.setString(3, store.getPassword());
				statement.setString(4, store.getEmail());
				statement.setString(5, store.getStoreName());
				statement.setString(6, store.getStoreId());

			}
			//プリペアードステートメントにを実行
			count = statement.executeUpdate();
		} catch (Exception e) {
			throw e;
		} finally {
			//プリペアードステート面とをとじる
			if (statement != null) {
				try {
					statement.close();
				} catch (SQLException sqle) {
					throw sqle;
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
		}
		if (count > 0){
			//実行件数が1件以上ある場合
			return true;
		} else {
			//実行件数が0件の場合
			return false;
	}
	}
}
