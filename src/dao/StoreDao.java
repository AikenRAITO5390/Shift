package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Time;
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
//ログイン情報から全てもらってきました
        if (rs.next()) { // 認証成功の場合
            // Storeオブジェクトを作成して設定
            store = new Store();
            store.setStoreId(rs.getString("store_id"));
            store.setStoreName(rs.getString("store_name"));
            store.setPassword(rs.getString("password"));
            store.setEmail(rs.getString("email"));
            store.setManagerName(rs.getString("manager_name"));
            store.setManagerId(rs.getString("manager_id"));
            store.setWorkTimeId(rs.getString("work_time_id"));
            store.setWorkTimeStart(rs.getTime("work_time_start"));
            store.setWorkTimeEnd(rs.getTime("work_Time_End"));
            store.setWorkWeekScore(rs.getInt("work_week_score"));
            store.setWeekScore(rs.getInt("week_score"));
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

	//時間設定のために、店舗IDとAとかBとかで取得するやつ
	public Store Time_get(String store_id, String store_time_id) throws Exception {
		//店舗情報インスタンスを初期化
		Store store = new Store();
		//データベースのコネクションを確立
		Connection connection = getConnection();
		//プリペアードステートメント
		PreparedStatement statement = null;

		try{
			String sql = "SELECT * FROM store WHERE store_id = ? AND work_time_id =?";
			//プリペアードステートメントにSQL文をセット
			statement = connection.prepareStatement(sql);//後で書く
			//プリペアードステートメントに店舗IDをバインド（？の一個めに入る）
			statement.setString(1, store_id);
			statement.setString(2, store_time_id);
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


	public Store Week_get(String store_id, int work_week_score) throws Exception {
		//店舗情報インスタンスを初期化
		Store store = new Store();
		//データベースのコネクションを確立
		Connection connection = getConnection();
		//プリペアードステートメント
		PreparedStatement statement = null;

		try{
			String sql = "SELECT * FROM store WHERE store_id = ? AND work_week_score =?";
			//プリペアードステートメントにSQL文をセット
			statement = connection.prepareStatement(sql);//後で書く
			//プリペアードステートメントに店舗IDをバインド（？の一個めに入る）
			statement.setString(1, store_id);
			statement.setInt(2, work_week_score);
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
				store.setWorkTimeEnd(rSet.getTime("work_time_end"));
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
	//店舗IDをStringでもらって表示するやつ
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

	/**
	 * WorkTimeのSTARTを取得するクラス
	 * @param store_id
	 * @param work_time_id
	 * @return
	 * @throws Exception
	 */
	public String TimeStartGet(String store_id)throws Exception{
		//コネクションを確立
		Connection connection = getConnection();
		//プリペアードステートメント
		PreparedStatement statement = null;
		//リザルトセット
		ResultSet rSet = null;
		//時間を格納
		String work_time_start =null;

		try{
//			プリペアードステートメントにSQL文をセット
			statement = connection.prepareStatement("select work_time_start from STORE where STORE_ID=? and WORK_TIME_ID='A' ");//後で書く
			//

			statement.setString(1, store_id);

//			プリペアードステートメントを実行
			rSet = statement.executeQuery();
//			リストへの格納処理を実行

			if(rSet.next()){
				work_time_start = rSet.getString("work_time_start");
			}
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
		return work_time_start;

	}

	/**
	 * WorkTimeのSTARTを取得するクラス
	 * @param store_id
	 * @param work_time_id
	 * @return
	 * @throws Exception
	 */
	public String TimeEndGet(String store_id)throws Exception{
		//コネクションを確立
		Connection connection = getConnection();
		//プリペアードステートメント
		PreparedStatement statement = null;
		//リザルトセット
		ResultSet rSet = null;
		//時間を格納
		String work_time_end =null;

		try{
//			プリペアードステートメントにSQL文をセット
			statement = connection.prepareStatement("select work_time_end from STORE where STORE_ID=? and WORK_TIME_ID='D'");
			//

			statement.setString(1, store_id);

//			プリペアードステートメントを実行
			rSet = statement.executeQuery();
//			リストへの格納処理を実行

			if(rSet.next()){
				work_time_end = rSet.getString("work_time_end");
			}
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
		return work_time_end;

	}

	public List<List<String>> Week_filter(String store_id) throws Exception {
	    // 空のリストを作成して、結果を格納する
	    List<List<String>> storeList = new ArrayList<>();
	    // コネクションを確立
	    Connection connection = getConnection();
	    PreparedStatement statement = null;
	    ResultSet rSet = null;

	    try {
	        // SQL文を準備して店舗名に基づくフィルターを適用
	        String sql = "SELECT work_week_score, week_score FROM STORE WHERE store_id = ? AND work_week_score BETWEEN 1 AND 7";
	        statement = connection.prepareStatement(sql);
	        // プレースホルダーにstore_idをセット
	        statement.setString(1, store_id);
	        // クエリを実行
	        rSet = statement.executeQuery();

	        while (rSet.next()) {
	            List<String> scores = new ArrayList<>();
	            scores.add(String.valueOf(rSet.getInt("work_week_score")));
	            scores.add(String.valueOf(rSet.getInt("week_score")));
	            storeList.add(scores);
	        }
	    } finally {
	        // リソースをクローズ
	        if (rSet != null) rSet.close();
	        if (statement != null) statement.close();
	        if (connection != null) connection.close();
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
				statement = connection.prepareStatement("insert into STORE (STORE_ID,STORE_NAME,MANAGER_ID,MANAGER_NAME,PASSWORD,EMAIL,WORK_TIME_ID,"
						+ "WORK_TIME_START,WORK_TIME_END,WORK_WEEK_SCORE,WEEK_SCORE)"
						+ "values(?,?,?,?,?,?,?,?,?,?,?)");
//				プリペアードステートメントに値をバウンド
				statement.setString(1, store.getStoreId());
				statement.setString(2, store.getStoreName());
				statement.setString(3, store.getManagerId());
				statement.setString(4, store.getManagerName());
				statement.setString(5, store.getPassword());
				statement.setString(6, store.getEmail());
				statement.setString(7, store.getWorkTimeId());
				//引数２をTIME型にキャスト。これでいけるかはまだわからん
				statement.setTime(8, (Time) store.getWorkTimeStart());
				statement.setTime(9, (Time) store.getWorkTimeEnd());
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


	//TimeA~Dを設定した際に上書きするためのsave
public boolean save_Time(Store store)throws Exception{
	//データベースへのコネクションを確立
	Connection connection = getConnection();
	//プリペアードステート面と
	PreparedStatement statement = null;
	//実行件数
	int count = 0;

	try{
		//店舗IDとTIMEIDでゲットする
		Store old = Time_get(store.getStoreId(), store.getWorkTimeId());
		if (old == null) {
//			店舗情報が存在しなかった場合
//			プリペアードステートメントにINSERT文をセット
			statement = connection.prepareStatement("insert into STORE (STORE_ID,STORE_NAME,MANAGER_ID,MANAGER_NAME,PASSWORD,EMAIL,WORK_TIME_ID,"
					+ "WORK_TIME_START,WORK_TIME_END,WORK_WEEK_SCORE,WEEK_SCORE)"
					+ "values(?,?,?,?,?,?,?,?,?,?,?)");
//			プリペアードステートメントに値をバウンド
			statement.setString(1, store.getStoreId());
			statement.setString(2, store.getStoreName());
			statement.setString(3, store.getManagerId());
			statement.setString(4, store.getManagerName());
			statement.setString(5, store.getPassword());
			statement.setString(6, store.getEmail());
			statement.setString(7, store.getWorkTimeId());
			//引数２をTIME型にキャスト。これでいけるかはまだわからん
			statement.setTime(8, (Time) store.getWorkTimeStart());
			statement.setTime(9, (Time) store.getWorkTimeEnd());
			statement.setInt(10, store.getWorkWeekScore());
			statement.setInt(11, store.getWeekScore());


		}else {
			//IDが存在した場合
			//プリペアードステートメントにUPDATE文をセット
			statement = connection
					.prepareStatement("UPDATE store SET  work_time_start=?, work_time_end=? WHERE store_id=? AND work_time_id=?");
			//プリペアードステートメントに値をバインド
			statement.setTime(1, store.getWorkTimeStart());
			statement.setTime(2, store.getWorkTimeEnd());
			statement.setString(3, store.getStoreId());
			statement.setString(4, store.getWorkTimeId());
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


//TimeA~Dを設定した際に上書きするためのsave
public boolean save_Power(Store store)throws Exception{
//データベースへのコネクションを確立
Connection connection = getConnection();
//プリペアードステート面と
PreparedStatement statement = null;
//実行件数
int count = 0;

try{
	//店舗IDとTIMEIDでゲットする
	Store old = Week_get(store.getStoreId(), store.getWorkWeekScore());
	if (old == null) {
//		店舗情報が存在しなかった場合
//		プリペアードステートメントにINSERT文をセット
		statement = connection.prepareStatement("insert into STORE (STORE_ID,STORE_NAME,MANAGER_ID,MANAGER_NAME,PASSWORD,EMAIL,WORK_TIME_ID,"
				+ "WORK_TIME_START,WORK_TIME_END,WORK_WEEK_SCORE,WEEK_SCORE)"
				+ "values(?,?,?,?,?,?,?,?,?,?,?)");
//		プリペアードステートメントに値をバウンド
		statement.setString(1, store.getStoreId());
		statement.setString(2, store.getStoreName());
		statement.setString(3, store.getManagerId());
		statement.setString(4, store.getManagerName());
		statement.setString(5, store.getPassword());
		statement.setString(6, store.getEmail());
		statement.setString(7, store.getWorkTimeId());
		//引数２をTIME型にキャスト。これでいけるかはまだわからん
		statement.setTime(8, (Time) store.getWorkTimeStart());
		statement.setTime(9, (Time) store.getWorkTimeEnd());
		statement.setInt(10, store.getWorkWeekScore());
		statement.setInt(11, store.getWeekScore());


	}else {
		//IDが存在した場合
		//プリペアードステートメントにUPDATE文をセット
		statement = connection
				.prepareStatement("UPDATE store SET  work_week_score=?, week_score=? WHERE store_id=? AND work_week_score=?");
		//プリペアードステートメントに値をバインド
		statement.setInt(1, store.getWorkWeekScore());
		statement.setInt(2, store.getWeekScore());
		statement.setString(3, store.getStoreId());
		statement.setInt(4, store.getWorkWeekScore());
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


