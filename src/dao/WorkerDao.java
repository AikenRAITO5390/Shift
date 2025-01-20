package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import bean.Store;
import bean.Worker;

public class WorkerDao extends Dao{

	// ログイン処理
	public Worker login(String id, String password) throws Exception {

		Worker worker = null;

    	// SQL文を作成
        String sql = "SELECT * FROM WORKER WHERE worker_id = ? and worker_password = ?";

        Connection con = getConnection();
	    PreparedStatement ps = con.prepareStatement(sql);
	    ps.setString(1, id);
	    ps.setString(2, password);

        ResultSet rs = ps.executeQuery();


        // 認証成功の場合
        if (rs.next()) {
            // Workerオブジェクトを作成して設定
            worker = new Worker();
            worker.setWorkerId(rs.getString("worker_id"));
            worker.setWorkerPassword(rs.getString("worker_password"));
            worker.setWorkerName(rs.getString("worker_name"));
            worker.setStoreId(rs.getString("store_id"));
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
	public Worker get(String workerId) throws Exception {

		// Workerの初期化
		Worker worker = new Worker();

		Connection connection = getConnection();
	    PreparedStatement statement = null;
//	    ResultSet rSet = null;

	    try {

	    	// SQL文を作成
	    	String sql = "SELECT * FROM worker WHERE worker_id = ?";
	        statement = connection.prepareStatement(sql);
	        statement.setString(1, workerId);

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
	    	// e.printStackTrace();
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


	// ポジションおよび点数がnullの従業員を取得するメソッド
	public List<Worker> getWorkersWithNullPositionOrScore() throws Exception {
	    List<Worker> workers = new ArrayList<>();
	    Connection connection = getConnection();
	    PreparedStatement statement = null;
	    ResultSet resultSet = null;

	    try {
	        // SQL文を作成
	        String sql = "SELECT * FROM worker WHERE (worker_position IS NULL OR worker_score IS NULL) AND  worker_judge = FALSE";
	        statement = connection.prepareStatement(sql);
	        resultSet = statement.executeQuery();

	        while (resultSet.next()) {
	            Worker worker = new Worker();
	            worker.setWorkerId(resultSet.getString("worker_id"));
	            worker.setWorkerName(resultSet.getString("worker_name"));
	            // 他の必要なフィールドもセット
	            workers.add(worker);
	        }
	    } catch (SQLException e) {
	        throw e;
	    } finally {
	        if (resultSet != null) {
	            resultSet.close();
	        }
	        if (statement != null) {
	            statement.close();
	        }
	        if (connection != null) {
	            connection.close();
	        }
	    }
	    return workers;
	}

	// ポジションおよび点数がnullではない従業員を取得するメソッド
	public List<Worker> getWorkersWithPositionOrScore() throws Exception {
		List<Worker> workers = new ArrayList<>();
		Connection connection = getConnection();
		PreparedStatement statement = null;
		ResultSet resultSet = null;

		try {
		    // SQL文を作成
		    String sql = "SELECT * FROM worker WHERE (worker_position IS NOT NULL OR worker_score IS NOT NULL) AND  worker_judge = FALSE";
		    statement = connection.prepareStatement(sql);
		    resultSet = statement.executeQuery();

		    while (resultSet.next()) {
		        Worker worker = new Worker();
		        worker.setWorkerId(resultSet.getString("worker_id"));
		        worker.setWorkerName(resultSet.getString("worker_name"));
		        worker.setWorkerPosition(resultSet.getString("worker_position"));
		        worker.setWorkerScore(resultSet.getString("worker_score"));
		        // 他の必要なフィールドもセット
		        workers.add(worker);
		    }
		} catch (SQLException e) {
		    throw e;
		} finally {
		    if (resultSet != null) {
		        resultSet.close();
		    }
		    if (statement != null) {
		        statement.close();
		    }
		    if (connection != null) {
		        connection.close();
		    }
		}
		return workers;
	}


	/**
	 * baseSql:String 共通SQL文 プライベート
	 */
	private String baseSql = "select * from worker where worker_id = ? ";

	/**
	 * postFilterメソッド フィルター後のリストへの格納処理 プライベート
	 *
	 * @param rSet:リザルトセット
	 * @param store:Store
	 *            店
	 * @return 従業員のリスト:List<Worker> 存在しない場合は0件のリスト
	 * @throws Exception
	 */
	private List<Worker> postFilter(ResultSet rSet, Store store) throws Exception {

		// リストを初期化
	    List<Worker> list = new ArrayList<>();

	    try {
	        // リザルトセットを全件走査
	        while (rSet.next()) {

	            // 従業員インスタンスを初期化
	            Worker worker = new Worker();

	            // 従業員インスタンスに検索結果をセット
	            worker.setWorkerId(rSet.getString("worker_id"));
	            worker.setWorkerName(rSet.getString("worker_name"));
	            worker.setWorkerDate(rSet.getString("worker_date"));
	            worker.setWorkerAddress(rSet.getString("worker_address"));
	            worker.setWorkerTel(rSet.getString("worker_tel"));
	            worker.setWorkerPassword(rSet.getString("worker_password"));
	            worker.setStore(store);
	            worker.setWorkerJudge(rSet.getBoolean("worker_judge"));
	            worker.setWorkerScore(rSet.getString("worker_score"));
	            worker.setWorkerPosition(rSet.getString("worker_position"));

	            // リストに追加
	            list.add(worker);
	        }
	    } catch (SQLException | NullPointerException e) {
	        e.printStackTrace();
	    }

	    return list;
	}


	/**
	 * filterメソッド 店を指定して従業員の一覧を取得する
	 *
	 * @param store:Store
	 *            店
	 * @return 店のリスト:List<Worker> 存在しない場合は0件のリスト
	 * @throws Exception
	 */
	public List<Worker> filter(Store store) throws Exception {

		// リストを初期化
		List<Worker> list = new ArrayList<>();
		// コネクションを確立
		Connection connection = getConnection();
		// プリペアードステートメント
		PreparedStatement statement = null;
        // リザルトセット
        ResultSet rSet = null;


        try {
            statement = connection.prepareStatement("select * from worker where store_id=? order by worker_id asc");
            statement.setString(1, store.getStoreId());

            rSet = statement.executeQuery();
            list = postFilter(rSet, store);
        }catch(Exception e){
			throw e;

		}finally {
            // Close resources in finally block to ensure they're always closed

            if (statement != null) {
            	try {

            		statement.close();

				} catch (SQLException sqle) {
					// TODO: handle exception
					throw sqle;
				}

            }
            if (connection != null) {
            	try {
            		 connection.close();

				} catch (SQLException sqle) {
					// TODO: handle exception
					throw sqle;
				}

            }
        }

        return list;
	}

	// バイトのみ取得
	public List<Worker> filter_b(Store store) throws Exception {

		// リストを初期化
		List<Worker> list = new ArrayList<>();
		// コネクションを確立
		Connection connection = getConnection();
		// プリペアードステートメント
		PreparedStatement statement = null;
        // リザルトセット
        ResultSet rSet = null;


        try {
            statement = connection.prepareStatement("select * from worker where store_id=? and worker_judge=false order by worker_id asc");
            statement.setString(1, store.getStoreId());

            rSet = statement.executeQuery();
            list = postFilter(rSet, store);
        }catch(Exception e){
			throw e;

		}finally {
            // Close resources in finally block to ensure they're always closed

            if (statement != null) {
            	try {

            		statement.close();

				} catch (SQLException sqle) {
					// TODO: handle exception
					throw sqle;
				}

            }
            if (connection != null) {
            	try {
            		 connection.close();

				} catch (SQLException sqle) {
					// TODO: handle exception
					throw sqle;
				}

            }
        }

        return list;
	}

	// 社員のみ取得
		public List<Worker> filter_e(Store store) throws Exception {

			// リストを初期化
			List<Worker> list = new ArrayList<>();
			// コネクションを確立
			Connection connection = getConnection();
			// プリペアードステートメント
			PreparedStatement statement = null;
	        // リザルトセット
	        ResultSet rSet = null;


	        try {
	            statement = connection.prepareStatement("select * from worker where store_id=? and worker_judge=true order by worker_id asc");
	            statement.setString(1, store.getStoreId());

	            rSet = statement.executeQuery();
	            list = postFilter(rSet, store);
	        }catch(Exception e){
				throw e;

			}finally {
	            // Close resources in finally block to ensure they're always closed

	            if (statement != null) {
	            	try {

	            		statement.close();

					} catch (SQLException sqle) {
						// TODO: handle exception
						throw sqle;
					}

	            }
	            if (connection != null) {
	            	try {
	            		 connection.close();

					} catch (SQLException sqle) {
						// TODO: handle exception
						throw sqle;
					}

	            }
	        }

	        return list;
		}

	public List<Worker> filterByStoreId(String storeId) throws Exception {
        List<Worker> list = new ArrayList<>();
        Connection connection = getConnection();
        PreparedStatement statement = null;
        ResultSet resultSet = null;

        try {
            //connection  = Database.getConnection();
            String sql = "SELECT * FROM worker WHERE store_id = ?";
            statement = connection.prepareStatement(sql);
            statement.setString(1, storeId);
            resultSet = statement.executeQuery();

            while (resultSet.next()) {
                Worker worker = new Worker();
                worker.setWorkerId(resultSet.getString("worker_id"));
                worker.setWorkerName(resultSet.getString("worker_name"));
                worker.setStoreId(resultSet.getString("store_id"));
                worker.setWorkerJudge(resultSet.getBoolean("worker_judge"));
                // 他のフィールドも必要に応じて設定
                list.add(worker);
            }
        } catch (Exception e) {
            throw e;
        } finally {
            if (resultSet != null) {
                try {
                    resultSet.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            if (statement != null) {
                try {
                    statement.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            if (connection != null) {
                try {
                    connection.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }

        return list;
    }



	public List<Worker> getWorkersByStoreId(String storeId)throws Exception {
	    List<Worker> workers = new ArrayList<>(); {
        String sql = "SELECT * FROM worker WHERE store_id = ?";
        try (Connection conn = getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, storeId);
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                Worker worker = new Worker();
                // 結果セットからデータを取得してworkerオブジェクトに設定
                worker.setWorkerId(rs.getString("worker_id"));
                worker.setWorkerName(rs.getString("worker_name"));
                worker.setWorkerDate(rs.getString("worker_date"));
                worker.setWorkerAddress(rs.getString("worker_address"));
                worker.setWorkerTel(rs.getString("worker_tel"));
                worker.setWorkerPassword(rs.getString("worker_password"));
                worker.setStoreId(rs.getString("store_id"));
                workers.add(worker);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return workers;
    }
}

	public String GetworkerId(String workerName)throws Exception{
		Connection connection = getConnection();
        PreparedStatement statement = null;
        ResultSet rs= null;
        String worker = null;
		try {
			String sql = "SELECT worker_id FROM worker WHERE worker_name LIKE ?";
			statement = connection.prepareStatement(sql);
			statement.setString(1, "%"+workerName+"%");
			rs = statement.executeQuery();
			if (rs.next()) {
	            // 結果がある場合は値を取得
	            worker = rs.getString("worker_id");
	            System.out.println("Worker ID: " + worker);
	        } else {
	            // 結果がない場合の処理
	            System.out.println("有効なデータがありません。");
	        }
		} catch (SQLException e) {
			e.printStackTrace();
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



	public boolean delete(Worker worker) throws Exception {
	    // コネクションを確立
	    Connection connection = getConnection();
	    // プリペアードステートメント
	    PreparedStatement statement = null;
	    // 実行件数
	    int count = 0;

	    try {
	        // データベースから従業員を取得
	        Worker old = get(worker.getWorkerId());

	        // 従業員が存在した場合
	        if (old != null) {
	            // プリペアードステートメントにUPDATE文をセット
	            statement = connection.prepareStatement(
	            		"DELETE FROM WORKER WHERE WORKER_NAME = ? AND STORE_ID = ?;");

	            // プリペアードステートメントに値をバインド
	            statement.setString(1, worker.getWorkerName());
	            statement.setString(2, worker.getStore().getStoreId());

	            // プリペアードステートメントを実行
	            count = statement.executeUpdate();
	        }
	    } catch (Exception e) {
	        throw e;
	    } finally {
	        if (statement != null) {
	            try {
	                statement.close();
	            } catch (SQLException sqle) {
	                throw sqle;
	            }
	        }
	        // コネクションを閉じる
	        if (connection != null) {
	            try {
	                connection.close();
	            } catch (SQLException sqle) {
	                throw sqle;
	            }
	        }
	    }
	    return count > 0;
	}

	// workerIdをStringからWorker型に直すために使ってます
	public Worker getWorkerById(String workerId) {
	    Worker worker = null;
	    try (Connection con = getConnection()) {
	        String sql = "SELECT * FROM worker WHERE worker_id = ?";
	        try (PreparedStatement ps = con.prepareStatement(sql)) {
	            ps.setString(1, workerId);
	            try (ResultSet rs = ps.executeQuery()) {
	                if (rs.next()) {
	                    worker = new Worker();
	                    worker.setWorkerId(rs.getString("worker_id"));
	                    worker.setWorkerName(rs.getString("worker_name"));
	                    // 他のフィールドも設定
	                }
	            }
	        }
	    } catch (Exception e) {
	        e.printStackTrace();
	    }
	    return worker;
	}



	/**
	 * saveメソッド 従業員インスタンスをデータベースに保存する データが存在する場合は更新、存在しない場合は登録
	 *
	 * @param worker：Worker
	 *            従業員
	 * @return 成功:true, 失敗:false
	 * @throws Exception
	 */
	public boolean save (Worker worker) throws Exception {

		//コネクションを確立
		Connection connection = getConnection();
		//プリペアードステートメント
		PreparedStatement statement = null;
		// 実行件数
		int count = 0;

		try {
			// データベースから学生を取得
			Worker wk = get (worker.getWorkerId());

			// 学生が存在しなかった場合
			if(wk == null) {

				// プリペアードステートメンにINSERT文をセットと
				statement = connection. prepareStatement (
				"insert into worker (worker_id,worker_name,worker_date,worker_address,worker_tel,worker_password,store_id,worker_judge,worker_score,worker_position) values(?, ?, ?, ?, ?, ?, ?, ?, ?, ?)");
				// プリペアードステートメントに値をバインド
				statement.setString(1,worker.getWorkerId());
				statement.setString(2,worker.getWorkerName());
				statement.setString(3,worker.getWorkerDate());
				statement.setString(4,worker.getWorkerAddress());
				statement.setString(5,worker.getWorkerTel());
				statement.setString(6,worker.getWorkerPassword());
				statement.setString(7,worker.getStore().getStoreId());
				statement.setBoolean(8,worker.isWorkerJudge());
				statement.setString(9,worker.getWorkerScore());
				statement.setString(10,worker.getWorkerPosition());

			}else {
				// 従業員が存在した場合 更新！
				// プリペアードステートメントにUPDATE文をセット
				statement = connection
				.prepareStatement ("update worker set worker_id=?, worker_name=?, worker_date=?, worker_address=?, worker_tel=?, worker_password=?, store_id=?, worker_judge=?, worker_score=?, worker_position=? where worker_id = ?");
				// プリペアードステートメントに値をバインド
				statement.setString(1,worker.getWorkerId());
				statement.setString(2,worker.getWorkerName());
				statement.setString(3,worker.getWorkerDate());
				statement.setString(4,worker.getWorkerAddress());
				statement.setString(5,worker.getWorkerTel());
				statement.setString(6,worker.getWorkerPassword());
				statement.setString(7,worker.getStore().getStoreId());
				statement.setBoolean(8,worker.isWorkerJudge());
				statement.setString(9,worker.getWorkerScore());
				statement.setString(10,worker.getWorkerPosition());
				// UPDATE文の中でWHERE句のworker_idを設定する必要があるため
				statement.setString(11, worker.getWorkerId());

			}

			//プリペアードステートメントを実行
			count = statement.executeUpdate ();

		}catch(Exception e) {
				throw e;
		}finally {
			// プリペアードステートメントを閉じる
			if (statement != null) {
				try {
					statement. close ();
				}catch (SQLException sqle) {
					throw sqle;
				}
			}
			//コネクションを閉じる
			if (connection != null) {
				try {
					connection.close ();
				}catch (SQLException sqle) {
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
