package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import bean.Worker;

public class WorkerDao extends Dao{

	// ログイン処理
	public Worker login(String id, String password) throws Exception {

		Worker worker = null;

    	//school_cdによってJOIN SCHOOLさせて、SCHOOL_nameがゲットできるように
        String sql = "SELECT * FROM WORKER WHERE worker_id = ? and worker_password = ?";

        Connection con = getConnection();
	    PreparedStatement ps = con.prepareStatement(sql);
	    ps.setString(1, id);
	    ps.setString(2, password);

        ResultSet rs = ps.executeQuery();

        if (rs.next()) { // 認証成功の場合
            // Workerオブジェクトを作成して設定
            worker = new Worker();
            worker.setWorkerId(rs.getString("worker_id"));
            worker.setWorkerPassword(rs.getString("worker_password"));
            worker.setWorkerName(rs.getString("worker_name"));
        }

        ps.close();
        rs.close();

        //ログインされたworkerのデータを返す
        return worker;

	}

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
