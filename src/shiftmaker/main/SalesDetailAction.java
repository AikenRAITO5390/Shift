package shiftmaker.main;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import bean.Sales;
import bean.Store;
import dao.SalesDao;
import dao.StoreDao;
import tool.Action;


public class SalesDetailAction extends Action {

	@Override
	public void execute(HttpServletRequest req,HttpServletResponse res)throws Exception{


		//ローカル変数の宣言 1
			HttpSession session = req.getSession();// セッションを取得
			SalesDao salesDao = new SalesDao();//売上DAOを初期化
			Store manager = (Store) session.getAttribute("user");// ログインユーザーを取得

			//ヘッダーに名前を出すためのコード
			//ログイン情報から名前を取得して"managerName"として渡す
			StoreDao sDao = new StoreDao();

			Store store = sDao.get(manager.getStoreId());
			req.setAttribute("managerName", store.getManagerName());


			try {

				//店舗の日付ごとの売上情報を取得
				List<Sales> salelist = salesDao.filter(manager);

				//売上情報がなかった場合、メッセージを返す
				if (salelist == null || salelist.isEmpty()) {
				    req.setAttribute("message", "売上データがありません。");
				}



				//### 売上グラフの元データの作成 ###
				ArrayList<ArrayList<String>> ar1 = new ArrayList<>();// 現在の年月用


				// 現在の年月を取得
	            Calendar calendar = Calendar.getInstance();
	            int currentMonth = calendar.get(Calendar.MONTH) + 1; // Calendar.MONTHは0始まりなので+1する
	            int currentYear = calendar.get(Calendar.YEAR) + 1; // Calendar.YEARは0始まりなので+1する


				// salelist をループしてデータを変換
				for (Sales sale : salelist) {

					// データの日付の年月を取得
	                Calendar saleCalendar = Calendar.getInstance();
	                saleCalendar.setTime(sale.getDate());
	                int saleMonth = saleCalendar.get(Calendar.MONTH) + 1; // Calendar.MONTHは0始まりなので+1する
	                int saleYear = saleCalendar.get(Calendar.YEAR) + 1; // Calendar.YEARは0始まりなので+1する



	             // 現在の年月と一致するデータのみ処理
	                if (saleMonth == currentMonth && saleYear == currentYear) {
				    ArrayList<String> tmp = new ArrayList<>();// データを追加するための仮リスト

			        SimpleDateFormat formatter = new SimpleDateFormat("MM月dd日");// フォーマッタの作成
			        String dateString = formatter.format(sale.getDate());// Date → String に変換



				    tmp.add(String.valueOf(sale.getDaySales())); // 売上
				    tmp.add("現在の売上");// 店舗名
				    tmp.add(dateString);// 日付
				    ar1.add(tmp);//一行目にデータを追加
	                }



	             // 一年前の年月と一致するデータのみ処理
	                if (saleMonth == currentMonth && saleYear == currentYear-1) {
				    ArrayList<String> tmp = new ArrayList<>();// データを追加するための仮リスト

			        SimpleDateFormat formatter = new SimpleDateFormat("MM月dd日");// フォーマッタの作成
			        String dateString = formatter.format(sale.getDate());// Date → String に変換



				    tmp.add(String.valueOf(sale.getDaySales())); // 売上
				    tmp.add("一年前の売上");// 店舗名
				    tmp.add(dateString);// 日付
				    ar1.add(tmp);//一行目にデータを追加
	                }

				}


				//### 売上グラフの元データをセッションに保持 ###
				session.setAttribute("chart1", ar1);

				//セッションに保存されているか確認
				System.out.println("動的に生成されたセッションデータ: " + ar1);// デバッグ用の出力


				//正しい売上情報が入っているか確認
		        System.out.println("salelist: " + salelist);// デバッグ用の出力


		        //エラーが発生した場合、sale.jspへ戻る
			} catch (Exception e) {
		        e.printStackTrace();
		        req.setAttribute("error", "データの取得中にエラーが発生しました。");
		        req.getRequestDispatcher("sales.jsp").forward(req, res);
		        return;
		    }

				//jspへフォワード
		req.getRequestDispatcher("sales_detail.jsp").forward(req, res);
	}

}
