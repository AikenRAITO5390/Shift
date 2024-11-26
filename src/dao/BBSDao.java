package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import bean.BBS;
import bean.Store;


public class BBSDao extends Dao {
	private static int currentBbsId = 1;


	public int generateNewBbsId() {
        return currentBbsId++;
    }



	public BBS get(String bbsId) throws Exception{
		BBS bbs = new BBS();
		//データエースへのコネクションを確立
		Connection connection = getConnection();
		//プリペアードステートメント
		PreparedStatement statement = null;

		try{
			//プリペアードステートメントにSQL文をセット
			statement = connection.prepareStatement("select * from BBS where bbs_id=? ");
			//値はBBS_IDでゲットしてくる
			statement.setString(1, bbsId);
			//プリペアードステートメントを実行
			ResultSet rSet = statement.executeQuery();

			//workerDaoを初期化
			WorkerDao workerDao = new WorkerDao();

			//storeDaoを初期化
			StoreDao storeDao = new StoreDao();

			if (rSet.next()) {
				//リザルトセットが存在する場合
				//BBSインスタンスに検索結果をセット
				bbs.setBbsText(rSet.getString("BBS_TEXT"));
				bbs.setBbsId(rSet.getString("BBS_ID"));
				//WORKERはworker_idコードで検索したworkerインスタンスをセット
				bbs.setWorker(workerDao.get(rSet.getString("WORKER_ID")));
				//STOREはworker_idコードで検索したworkerインスタンスをセット
				bbs.setStore(storeDao.get(rSet.getString("STORE_ID")));

			} else {
				//リザルトセットが存在しない場合
				//掲示板にnullをセット
				bbs = null;
			}
		} catch (Exception e) {
			throw e;
		} finally {
			//
			if(statement != null) {
				try {
					statement.close();
				} catch (SQLException sqle) {
					throw sqle;
				}
			}

			if(connection != null) {
				try {
					connection.close();
				} catch (SQLException sqle) {
					throw sqle;
				}
			}
		}
		//listを返す
		return bbs;
	}
	//ログインしているお店別で値を出すのでSQLはSTOREで検索
	private String baseSql = "select * from BBS where STORE_ID=?  ";

	public List<BBS> postFilter(ResultSet rSet,Store store) throws Exception {
		//戻り値用のリスト
		List<BBS> list = new ArrayList<>();
		WorkerDao workerDao = new WorkerDao();
		try{
			while(rSet.next()) {
				//掲示板インスタンスを初期化
				BBS bbs = new BBS();
				//掲示板インスタンスに検索結果をセット
				bbs.setBbsId(rSet. getString("BBS_ID"));
				bbs.setBbsText (rSet. getString("BBS_TEXT"));
				bbs.setWorker(workerDao.get(rSet.getString("WORKER_ID")));
				bbs.setBbsDate (rSet. getString("BBS_DATE"));
				//初期化したworker
				//bbs.setWorker (worker);
				//引数のstore
				bbs.setStore(store);
				//リストに追加
				list.add(bbs);
			}
		} catch (SQLException | NullPointerException e) {
			e.printStackTrace();
		}
		return list;
	}

	public List<BBS> filter(Store store) throws Exception {
		//リストを初期化
	    List<BBS> list = new ArrayList<>();
	    //コネクションを確立
	    Connection connection = getConnection();
	    //プリペアードステートメント
	    PreparedStatement statement = null;
	    //リザルトセット
	    ResultSet rSet = null;
	    //SQL文のソート
	    //投稿された順にしたいので、追加順となるBBS_IDで昇順
	    String order = " order by BBS_DATE desc";

	    try {
		    //プリペアードステートメントにSQL文をセット
	    	//storeでお店で絞った＆投稿順になってる
		    statement = connection. prepareStatement (baseSql + order);
		    //プリペアードステートメントに学校コードをバインド
		    statement. setString(1, store. getStoreId ());
		    // プライベートステートメントを実行
		    rSet = statement.executeQuery ();
		    //帰ってきたResultSet型をStudent型に変えて結果をセットする
		    list = postFilter(rSet,store);
		} catch (Exception e) {
			throw e;
		} finally {
			//
			if(statement != null) {
				try {
					statement.close();
				} catch (SQLException sqle) {
					throw sqle;
				}
			}

			if(connection != null) {
				try {
					connection.close();
				} catch (SQLException sqle) {
					throw sqle;
				}
			}
		}
		//listを返す
		return list;
	}




	//掲示板に変更はないので、UPDATE文は削除しました
	public boolean save(BBS bbs) throws Exception {
	    // コネクションを確立
	    Connection connection = getConnection();
	    // プリペアードステートメント
	    PreparedStatement statement = null;
	    // 実行件数
	    int count = 0;

	    try {
	        // プリペアードステートメントにINSERT文をセット
	        statement = connection.prepareStatement(
	                "INSERT INTO BBS (BBS_ID, BBS_TEXT, STORE_ID, WORKER_ID, BBS_DATE,MANAGER_ID) VALUES (?, ?, ?, ?, ?, ?)");
	        // プリペアードステートメントに値をバインド
	        statement.setString(1, bbs.getBbsId());
	        statement.setString(2, bbs.getBbsText());
	        statement.setString(3, bbs.getStore().getStoreId());
	        statement.setString(4, bbs.getWorker().getWorkerId());
	        statement.setString(5, bbs.getBbsDate());
	        statement.setString(6, bbs.getStore().getManagerId());

	        // プリペアードステートメントを実行
	        count = statement.executeUpdate();

	        // コミット
	        connection.commit();

	    } catch (Exception e) {
	        // ロールバック
	        if (connection != null) {
	            try {
	                connection.rollback();
	            } catch (SQLException sqle) {
	                throw sqle;
	            }
	        }
	        e.printStackTrace();
	        throw e;
	    } finally {
	        // ステートメントのクローズ
	        if (statement != null) {
	            try {
	                statement.close();
	            } catch (SQLException sqle) {
	                throw sqle;
	            }
	        }

	        // コネクションのクローズ
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
//掲示板削除
	public boolean delete(BBS bbs) throws Exception {

		//コネクションを確立
				Connection connection = getConnection();
				//プリペアードステートメント
				PreparedStatement statement = null;
				//実行件数
				int count = 0;

				try{   //選ばれた掲示板投稿（BBSIDで管理）を削除
					BBS old = get(bbs.getBbsId());
					statement=connection.prepareStatement("delete from BBS where BBS_ID = ?");
					statement.setString(1, bbs.getBbsId());
					count = statement.executeUpdate();
				}catch(Exception e){
					throw e;
				}finally{
					if(statement !=null){
						try{
							connection.close();
						}catch(SQLException sqle){
							throw sqle;
						}
					}
				}

				if(count > 0){
					return true;
				}else{
					return false;
				}
	}
}



