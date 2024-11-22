package dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

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

}

