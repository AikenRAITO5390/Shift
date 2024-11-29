package dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import bean.Shift;
import bean.Store;
import bean.Worker;

public class ShiftDao extends Dao{

	/**
	 * getメソッド worker,shift_date,storeを指定してシフトインスタンスを1件取得する
	 *
	 * @param worker:Worker  shift_date:Date   store:Store
 	 *           従業員ID        年月日           店舗ID
	 * @return シフトクラスのインスタンス 存在しない場合はnull
	 * @throws Exception
	 */
	public Shift get(Worker worker, Date shift_date, Store store) throws Exception {

		// Shiftの初期化
		Shift shift = new Shift();

		Connection connection = getConnection();
	    PreparedStatement statement = null;
//	    ResultSet rSet = null;

	    try {

	    	// SQL文を作成
	    	String sql = "SELECT * FROM SHIFT WHERE shift_date = ? and worker_id = ? and store_id = ?";
	        statement = connection.prepareStatement(sql);
	        statement.setDate(1,shift_date);
	        statement.setString(2, worker.getWorkerId());
	        statement.setString(3, store.getStoreId());

		    ResultSet rSet = statement.executeQuery();

		    // WorkerDaoの初期化
		    WorkerDao workerDao = new WorkerDao();
		    // StoreDaoの初期化
		    StoreDao storeDao = new StoreDao();

		    // リザルトセットからシフトインスタンスを作成
		    if(rSet.next()) {
		    	shift.setShiftDate(rSet.getDate("shift_date"));
		    	shift.setShiftScore(rSet.getInt("shift_score"));
		    	shift.setShiftHopeTimeId(rSet.getString("shifthope_time_id"));
		    	shift.setShiftHopeTimeStart(rSet.getTimestamp("shifthope_time_start"));
		    	shift.setShiftHopeTimeEnd(rSet.getTimestamp("shifthope_time_end"));
		    	shift.setWorkTimeId(rSet.getString("work_time_id"));
		    	shift.setShiftTimeStart(rSet.getTimestamp("shift_time_start"));
		    	shift.setShiftTimeEnd(rSet.getTimestamp("shift_time_end"));
		    	shift.setShiftId(rSet.getString("shift_id"));
		    	shift.setWorker(workerDao.get(rSet.getString("worker_id")));
		    	shift.setStore(storeDao.get(rSet.getString("store_id")));
		    } else {
		    	shift = null;
		    }


	    } catch (SQLException e) {
//	        e.printStackTrace();
	        throw e;
	    } finally {
	        // リソースを閉じる
	        if (statement != null) {
	            try {
	                statement.close();
	            } catch (SQLException sqle) {
	                throw sqle;
	            }
	        }


	        if (connection != null) {
	            try {
	                connection.close();
	            } catch (SQLException sqle) {
	                throw sqle;
	            }
	        }
	    }

	    return shift;
	}
	/**
	 * postFilter受けとった検索結果を格納する
	 * @param rSet
	 * @return List<shift> list
	 * @throws Exception
	 */
	private List<Shift> postFilter(ResultSet rSet)throws Exception{
//		空のリストを作成
		List<Shift> list = new ArrayList<>();
		WorkerDao wDao = new WorkerDao();
		StoreDao stDao = new StoreDao();
		try{
			//リザルトセットを全件走査(データベースの登録件数分回る)
			while (rSet.next()) {
//				店舗情報インスタンスを初期化
				Shift shift = new Shift();
//				店舗情報インスタンスに検索結果をセット
				shift.setShiftDate(rSet.getDate("shift_date"));
		    	shift.setShiftScore(rSet.getInt("shift_score"));
		    	shift.setShiftHopeTimeId(rSet.getString("shifthope_time_id"));
		    	shift.setShiftHopeTimeStart(rSet.getTimestamp("shifthope_time_start"));
		    	shift.setShiftHopeTimeEnd(rSet.getTimestamp("shifthope_time_end"));
		    	shift.setWorkTimeId(rSet.getString("work_time_id"));
		    	shift.setShiftTimeStart(rSet.getTimestamp("shift_time_start"));
		    	shift.setShiftTimeEnd(rSet.getTimestamp("shift_time_end"));
		    	shift.setShiftId(rSet.getString("shift_id"));
		    	shift.setStore(stDao.get(rSet.getString("store_id")));
		    	shift.setWorker(wDao.get(rSet.getString("worker_id")));
//				リストに追加
				list.add(shift);
			}
		} catch (SQLException | NullPointerException e) {
			e.printStackTrace();
		}
		return list;
	}

	/**
	 * 指定した日の従業員情報を返すfilter
	 * @param store
	 * @param shift_date
	 * @return List<shift> shift_list
	 * @throws Exception
	 */
	public List<Shift> filter(Store store, Date shift_date) throws Exception {
	    // 空のリストを作成して、結果を格納する
	    List<Shift> shift_list = new ArrayList<>();
	    // コネクションを確立
	    Connection connection = getConnection();
	    PreparedStatement statement = null;
	    ResultSet rSet = null;

	    try {
	        // SQL文を準備して店舗名に基づくフィルターを適用
	        String sql = "SELECT * FROM shift WHERE store_id = ? and shift_date = ?";
	        statement = connection.prepareStatement(sql);
	        // プレースホルダーにstoreNameをセット
	        statement.setString(1, store.getStoreId());
	        statement.setDate(2, shift_date);
	        // クエリを実行
	        rSet = statement.executeQuery();
	        shift_list = postFilter(rSet);

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

	    return shift_list;
	}


	//お店シフト日付情報があるか
	public Shift WeekScore_get(String store_id, Date StartDate, String worker_id) throws Exception {
		//店舗情報インスタンスを初期化
		Shift shift = new Shift();
		//データベースのコネクションを確立
		Connection connection = getConnection();
		//プリペアードステートメント
		PreparedStatement statement = null;
	    // WorkerDaoの初期化
	    WorkerDao workerDao = new WorkerDao();
	    // StoreDaoの初期化
	    StoreDao storeDao = new StoreDao();

		try{
			String sql = "SELECT * FROM shift WHERE store_id = ? AND shift_date =? AND worker_id = ?";
			//プリペアードステートメントにSQL文をセット
			statement = connection.prepareStatement(sql);//後で書く
			//プリペアードステートメントに店舗IDをバインド（？の一個めに入る）
			statement.setString(1, store_id);
			statement.setDate(2, StartDate);
			statement.setString(3, worker_id);
			//プリペアードステートメントを実行
			ResultSet rSet = statement.executeQuery();


			if(rSet.next()){
				//リザルトセットが存在する場合(検索結果が存在した場合)
				//店舗情報インスタンスに検索結果をセット
		    	shift.setShiftDate(rSet.getDate("shift_date"));
		    	shift.setShiftScore(rSet.getInt("shift_score"));
		    	shift.setShiftHopeTimeId(rSet.getString("shifthope_time_id"));
		    	shift.setShiftHopeTimeStart(rSet.getTimestamp("shifthope_time_start"));
		    	shift.setShiftHopeTimeEnd(rSet.getTimestamp("shifthope_time_end"));
		    	shift.setWorkTimeId(rSet.getString("work_time_id"));
		    	shift.setShiftTimeStart(rSet.getTimestamp("shift_time_start"));
		    	shift.setShiftTimeEnd(rSet.getTimestamp("shift_time_end"));
		    	shift.setShiftId(rSet.getString("shift_id"));
		    	shift.setWorker(workerDao.get(rSet.getString("worker_id")));
		    	shift.setStore(storeDao.get(rSet.getString("store_id")));

			} else {
//				リザルトセットが存在しない場合
//				店舗情報インスタンスにNullをセット
				shift = null;
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
	return shift;

	}

	//パワー設定filter
	public Map<String, Integer> Powerfilter(String store_id, Date StartDate) throws Exception {
	    // 空のリストを作成して、結果を格納する
		Map<String, Integer> dateMap = new HashMap<>();
	    // コネクションを確立
	    Connection connection = getConnection();
	    PreparedStatement statement = null;
	    ResultSet rSet = null;

	    try {
	        // SQL文を準備して店舗名に基づくフィルターを適用
	        String sql = "SELECT shift_date, shift_score FROM SHIFT WHERE store_id = ? and shift_date=?";
	        statement = connection.prepareStatement(sql);
	        // プレースホルダーにstoreNameをセット
	        statement.setString(1, store_id);
	        statement.setDate(2, StartDate);

	        // クエリを実行
	        rSet = statement.executeQuery();

	        while(rSet.next()){
	        	dateMap.put(rSet.getString("shift_date"), rSet.getInt("shift_score"));
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

	    return dateMap;
	}

	// Eの選択肢用にデータを保存するメソッド
	public void insertCustomWorkTime(Date shiftDate, String workerId, String storeId, String startTime, String endTime, String shiftScore) throws Exception {
		Connection connection = getConnection();
		PreparedStatement statement = null;

		Shift old = WeekScore_get(storeId, shiftDate, workerId);

		try {

			if(old == null){

				String sql = "INSERT INTO SHIFT (SHIFT_DATE, WORKER_ID, STORE_ID, SHIFTHOPE_TIME_START, SHIFTHOPE_TIME_END, SHIFT_SCORE) VALUES (?, ?, ?, ?, ?, ?)";
			    statement = connection.prepareStatement(sql);
			    statement.setDate(1, shiftDate);
			    statement.setString(2, workerId);
			    statement.setString(3, storeId);
			    statement.setString(4, startTime);
			    statement.setString(5, endTime);
			    statement.setString(6, shiftScore);
			    statement.executeUpdate();
			}  else {

				//プリペアードステートメントにUPDATE文をセット
				statement = connection.prepareStatement("UPDATE shift SET shifthope_time_start=?, shifthope_time_end=? WHERE shift_date=? AND worker_id=? AND store_id=? AND shift_score=?");
				//プリペアードステートメントに値をバインド
				statement.setString(1, startTime);
				statement.setString(2, endTime);
				statement.setDate(3, shiftDate);
				statement.setString(4, workerId);
				statement.setString(5, storeId);
				statement.setString(6, shiftScore);
			}

		} finally {
		    if (statement != null) statement.close();
		    if (connection != null) connection.close();
		}
	}

	// A~Dの選択肢用にデータを保存するメソッド
	public void insertWorkTime(Date shiftDate, String workerId, String storeId, String workTimeId, String shiftScore) throws Exception {
		Connection connection = getConnection();
		PreparedStatement statement = null;

		Shift old = WeekScore_get(storeId, shiftDate, workerId);

		try {

			if(old == null){
				String sql = "INSERT INTO SHIFT (SHIFT_DATE, WORKER_ID, STORE_ID, WORK_TIME_ID, SHIFT_SCORE) VALUES (?, ?, ?, ?, ?)";
				statement = connection.prepareStatement(sql);
				statement.setDate(1, shiftDate);
				statement.setString(2, workerId);
				statement.setString(3, storeId);
				statement.setString(4, workTimeId);
				statement.setString(5, shiftScore);
				statement.executeUpdate();
			} else {

				//プリペアードステートメントにUPDATE文をセット
				statement = connection.prepareStatement("UPDATE shift SET work_time_id=? WHERE shift_date=? AND worker_id=? AND store_id=? AND shift_score=?");
				//プリペアードステートメントに値をバインド
				statement.setString(1, workTimeId);
				statement.setDate(2, shiftDate);
				statement.setString(3, workerId);
				statement.setString(4, storeId);
				statement.setString(5, shiftScore);
			}

		} finally {
			if (statement != null) statement.close();
			if (connection != null) connection.close();
		}
	}

	// 追加メソッドを作成して、登録済みのシフトを取得
	public Map<String, String> getSelectedShifts(String workerId) throws Exception {
	    Connection connection = getConnection();
	    PreparedStatement statement = null;
	    ResultSet resultSet = null;
	    Map<String, String> shifts = new HashMap<>();

	    try {
	        String sql = "SELECT SHIFT_DATE, SHIFTHOPE_TIME_ID FROM SHIFT WHERE WORKER_ID = ?";
	        statement = connection.prepareStatement(sql);
	        statement.setString(1, workerId);
	        resultSet = statement.executeQuery();

	        while (resultSet.next()) {
	            String date = resultSet.getString("SHIFT_DATE");
	            String shiftHope = resultSet.getString("SHIFTHOPE_TIME_ID");
	            shifts.put(date, shiftHope);
	        }
	    } finally {
	        if (resultSet != null) resultSet.close();
	        if (statement != null) statement.close();
	        if (connection != null) connection.close();
	    }

	    return shifts;
	}

	/**
	 * getメソッド worker,shift_date,storeを指定してshiftscoreを1件取得する
	 *
	 * @param worker:Worker  shift_date:Date   store:Store
 	 *           従業員ID        年月日           店舗ID
	 * @return シフトクラスのインスタンス 存在しない場合はnull
	 * @throws Exception
	 */
	public Shift getShiftScore(Worker worker, Date shift_date, Store store) throws Exception {

		// Shiftの初期化
		Shift shift = new Shift();

		Connection connection = getConnection();
	    PreparedStatement statement = null;
//	    ResultSet rSet = null;

	    try {

	    	// SQL文を作成
	    	String sql = "SELECT shift_score FROM SHIFT WHERE shift_date = ? and worker_id = ? and store_id = ?";
	        statement = connection.prepareStatement(sql);
	        statement.setDate(1,shift_date);
	        statement.setString(2, worker.getWorkerId());
	        statement.setString(3, store.getStoreId());

		    ResultSet rSet = statement.executeQuery();

		    // WorkerDaoの初期化
		    WorkerDao workerDao = new WorkerDao();
		    // StoreDaoの初期化
		    StoreDao storeDao = new StoreDao();

		    // リザルトセットからシフトインスタンスを作成
		    if(rSet.next()) {
		    	shift.setShiftScore(rSet.getInt("shift_score"));
		    } else {
		    	shift = null;
		    }


	    } catch (SQLException e) {
//	        e.printStackTrace();
	        throw e;
	    } finally {
	        // リソースを閉じる
	        if (statement != null) {
	            try {
	                statement.close();
	            } catch (SQLException sqle) {
	                throw sqle;
	            }
	        }


	        if (connection != null) {
	            try {
	                connection.close();
	            } catch (SQLException sqle) {
	                throw sqle;
	            }
	        }
	    }

	    return shift;
	}


	//パワー作成save
	public boolean save_Score(Shift shift)throws Exception{
	//データベースへのコネクションを確立
	Connection connection = getConnection();
	//プリペアードステート面と
	PreparedStatement statement = null;
	//実行件数
	int count = 0;
	try{
		//店舗IDとTIMEIDでゲットする
		Shift old = WeekScore_get(shift.getStore().getStoreId(), shift.getShiftDate(),shift.getWorker().getWorkerId());

		if (old == null) {
			System.out.println("ここに来ました");
	//		店舗情報が存在しなかった場合
	//		プリペアードステートメントにINSERT文をセット
			statement = connection.prepareStatement("insert into Shift (SHIFT_DATE,WORKER_ID,SHIFTHOPE_TIME_ID,SHIFT_SCORE,SHIFTHOPE_TIME_START,"
					+ "SHIFTHOPE_TIME_END,WORK_TIME_ID,SHIFT_TIME_START,SHIFT_TIME_END,SHIFT_ID,STORE_ID)"
					+ "values(?,?,?,?,?,?,?,?,?,?,?)");

			statement.setDate(1, shift.getShiftDate());
			statement.setString(2, shift.getWorker().getWorkerId());
			statement.setString(3, shift.getShiftHopeTimeId());
			statement.setInt(4, shift.getShiftScore());
			statement.setTimestamp(5, shift.getShiftHopeTimeStart());
			statement.setTimestamp(6, shift.getShiftHopeTimeEnd());
			statement.setString(7, shift.getWorkTimeId());
			statement.setTimestamp(8, shift.getShiftTimeStart());
			statement.setTimestamp(9, shift.getShiftTimeEnd());
			statement.setString(10, shift.getShiftId());
			statement.setString(11, shift.getStore().getStoreId());

		}else {
			//IDが存在した場合
			//プリペアードステートメントにUPDATE文をセット
			statement = connection.prepareStatement("UPDATE shift SET  shift_score=? WHERE shift_date=? AND store_id=? ");
			//プリペアードステートメントに値をバインド
			statement.setDate(2, shift.getShiftDate());
			statement.setString(3, shift.getStore().getStoreId());
			statement.setInt(1, shift.getShiftScore());
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








