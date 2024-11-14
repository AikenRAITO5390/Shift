package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import bean.Worker;

public class WorkerDao extends Dao{


	/**
	 * getメソッド 従業員IDを指定して従業員インスタンスを1件取得する
	 *
	 * @param worker_id:String
	 *            従業員ID
	 * @return 従業員クラスのインスタンス 存在しない場合はnull
	 * @throws Exception
	 */
	public Worker get(String worker_id) throws Exception {

		// WorkerDaoの初期化
		Worker worker = new Worker();

		Connection connection = getConnection();
	    PreparedStatement statement = null;
//	    ResultSet rSet = null;

	    try {

	    	// SQL文を作成
	    	String sql = "SELECT * FROM worker WHERE worker_id = ?";
	        statement = connection.prepareStatement(sql);
	        statement.setString(1, worker_id);

		    ResultSet rSet = statement.executeQuery();

		    // StoreDaoの初期化
		    StoreDao storeDao = new StoreDao();

		    // リザルトセットから従業員インスタンスを作成
		    if(rSet.next()) {
		    	worker.setWorkerId(rSet.getString("worker_id"));
		    	worker.setWorkerName(rSet.getString("worker_name"));
		    	worker.setWorkerDate(rSet.getString("worker_date"));
		    	worker.setWorkerAddress(rSet.getString("worker_address"));
		    	worker.setWorkerTel(rSet.getString("worker_tel"));
		    	worker.setWorkerPassword(rSet.getString("worker_password"));
		    	worker.setStore(storeDao.get(rSet.getString("store_id")));
		    	worker.setWorkerJudge(rSet.getBoolean("worker_judge"));
		    	worker.setWorkerScore(rSet.getString("worker_score"));
		    	worker.setWorkerPosition(rSet.getString("worker_position"));
		    } else {
		    	worker = null;
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

	    return worker;
	}

}
