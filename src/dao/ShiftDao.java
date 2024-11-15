package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;

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

	    	// SQL文を作成 ※まだ未完成です
	    	String sql = "SELECT * FROM worker WHERE worker_id = ?";
	        statement = connection.prepareStatement(sql);
	        statement.setString(1, worker.getWorkerId());
	        statement.setDate(2, new java.sql.Date(shift_date.getTime()));
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
		    	shift.setShiftHopeTimeStart(rSet.getDate("shifthope_time_start"));
		    	shift.setShiftHopeTimeEnd(rSet.getDate("shifthope_time_end"));
		    	shift.setWorkTimeId(rSet.getString("worker_time_id"));
		    	shift.setShiftTimeStart(rSet.getDate("shift_time_start"));
		    	shift.setShiftTimeEnd(rSet.getDate("shift_time_end"));
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

}

