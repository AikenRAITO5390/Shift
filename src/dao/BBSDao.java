package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import bean.BBS;
import bean.Store;
import bean.Student;
import bean.Worker;


public class BBSDao extends Dao {

	public BBS get(String bbsId) throws Exception{
		BBS bbs = new BBS();
		//データエースへのコネクションを確立
		Connection connection = getConnection();
		//プリペアードステートメント
		PreparedStatement statement = null;

		try{
			//プリペアードステートメントにSQL文をセット
			statement = connection.prepareStatement("select * from BBS where bbs_id=? ");
			//プリペアードステートメントに学生番号をバインド
			statement.setString(1, bbsId);
			//プリペアードステートメントを実行
			ResultSet rSet = statement.executeQuery();

			//workerDaoを初期化
			WorkerDao workerDao = new WorkerDao();

			//storeDaoを初期化
			StoreDao storeDao = new StoreDao();

			if (rSet.next()) {
				//リザルトセットが存在する場合
				//学生インスタンスに検索結果をセット
				bbs.setBbsText(rSet.getString("BBS_TEXT"));
				bbs.setBbsId(rSet.getString("BBS_ID"));
				//WORKERはworker_idコードで検索したworkerインスタンスをセット
				bbs.setworker(WorkerDao.get(rSet.getString("WORKER_ID")));
				//STOREはworker_idコードで検索したworkerインスタンスをセット
				bbs.setstore(StoreDao.get(rSet.getString("STORE_ID")));

			} else {
				//リザルトセットが存在しない場合
				//学生インスタンスにnullをセット
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

	private String baseSql = "select * from BBS where STORE_ID=?  ";

	public List<BBS> postFilter(ResultSet rSet, Store store) throws Exception {
		//戻り値用のリスト
		Worker worker = new Worker();
		List<BBS> list = new ArrayList<>();
		try{
			while(rSet.next()) {
				//学生インスタンスを初期化
				BBS bbs = new BBS();
				//学生インスタンスに検索結果をセット
				bbs.setBbsId(rSet. getString("BBS_ID"));
				bbs.setBbsText (rSet. getString("BBS_TEXT"));
				bbs.setWorker (worker);
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
	    //SQL文のソートー
	    String order = " order by BBS_ID asc";

	    try {
		    //プリペアードステートメントにSQL文をセット
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
	
	public boolean save(BBS bbs) throws Exception {
		//コネクションを確立
		Connection connection = getConnection();
		//プリペアードステートメント
		PreparedStatement statement = null;
		//実行件数
		int count = 0;

		try{
			//データベースから学生を取得
			BBS old = get(bbs.getBbsId());
			if (old == null) {
				//学生が存在しなかった場合
				//プリペアードステートメンにINSERT文をセット
				statement = connection.prepareStatement(
						"insert into BBS (BBS_ID, BBS_TEXT, STORE_ID, WORKER_ID) values(?, ?, ?, ?,) ");
				//プリペアードステートメントに値をバインド
				statement.setString(1, bbs.getBbsId());
				statement.setString(2, bbs.getBbsText());
				statement.setInt(3, bbs.getStore().getStoreId);
				statement.setString(4, bbs.getWorker().getWorkerId);
			} else {
				//学生が存在した場合
				//プリペアードステートメントにUPDATE文をセット
				statement = connection
						.prepareStatement("update student set name=?, ent_year=?, class_num=?, is_attend=? where no=?");
				//プリペアードステートメントに値をバインド
				statement.setString(1, student.getName());
				statement.setInt(2, student.getEntYear());
				statement.setString(3, student.getClassNum());
				statement.setBoolean(4, student.isAttend());
				statement.setString(5, student.getNo());
			}

			//プリペアードステートメントを実行
			count = statement.executeUpdate();

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

		if (count > 0) {
			//実行件数が1以上ある場合
			return true;
		} else {
			//実行件数が0件の場合
			return false;
		}

	}
}
}


