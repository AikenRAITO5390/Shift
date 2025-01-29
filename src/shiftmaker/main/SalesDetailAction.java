package shiftmaker.main;

import java.sql.Date;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
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
import tool.SalesAnalyze;


public class SalesDetailAction extends Action {

	@Override
	public void execute(HttpServletRequest req,HttpServletResponse res)throws Exception{


		//ローカル変数の宣言 1
			HttpSession session = req.getSession();// セッションを取得
			SalesDao salesDao = new SalesDao();//売上DAOを初期化
			Store manager = (Store) session.getAttribute("user");// ログインユーザーを取得
			SalesAnalyze salesAnalyze = new SalesAnalyze();// AI予測ツール



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
	            int currentYear = calendar.get(Calendar.YEAR);


	            LocalDate now = LocalDate.now();// 現在の年月日をLocalDateで取得
	            LocalDate pastDate = now.minusDays(7); // 現在から一週間前の日付
	            LocalDate pastYear = now.minusYears(1).minusDays(7); // 現在から一年と一週間前の日付

	            System.out.println(pastDate + "これが現在から一週間前の日にち");

				// salelist をループしてデータを変換
				for (Sales sale : salelist) {

					// データの日付の年月を取得
	                Calendar saleCalendar = Calendar.getInstance();
	                saleCalendar.setTime(sale.getDate());
	                int saleMonth = saleCalendar.get(Calendar.MONTH) + 1; // Calendar.MONTHは0始まりなので+1する
	                int saleYear = saleCalendar.get(Calendar.YEAR);



	                Date saleDate = sale.getDate(); // Date型の取得
	                LocalDate saleLocalDate = saleDate.toLocalDate(); // LocalDateに変換







	             // 現在の年月と一致するデータのみ処理
	                if (saleMonth == currentMonth && saleYear == currentYear) {

//		                もし現在より一週間以上前のデータなら表示しない
	                	if (saleLocalDate.isBefore(pastDate)){
		                	continue;
		                }

					    ArrayList<String> tmp = new ArrayList<>();// データを追加するための仮リスト

				        SimpleDateFormat formatter = new SimpleDateFormat("MM/dd");// フォーマッタの作成
				        String dateString = formatter.format(sale.getDate());// Date → String に変換

					    tmp.add(String.valueOf(sale.getDaySales())); // 売上
					    tmp.add("現在の売上");// 店舗名
					    tmp.add(dateString);// 日付
					    ar1.add(tmp);//一行目にデータを追加
	                }



	             // 一年前の年月と一致するデータのみ処理
	                if (saleMonth == currentMonth && saleYear == currentYear-1) {

//		                もし現在より一年と一週間以上前のデータなら表示しない
	                	if (saleLocalDate.isBefore(pastYear)){
		                	continue;
		                }

	                	// データを追加するための仮リスト
					    ArrayList<String> tmp = new ArrayList<>();

				        SimpleDateFormat formatter = new SimpleDateFormat("MM/dd");// フォーマッタの作成
				        String dateString = formatter.format(sale.getDate());// Date → String に変換

					    tmp.add(String.valueOf(sale.getDaySales())); // 売上
					    tmp.add("一年前の売上");// 店舗名
					    tmp.add(dateString);// 日付
					    ar1.add(tmp);//一行目にデータを追加
	                }


//	 --------------------------------------------------------------------------------------------------

	             // AIで予測した年月と一致するデータのみ処理
	                // 現在の日から一週間だけ表示
//	                if (saleMonth == currentMonth && saleYear == currentYear) {
//		                ArrayList<String> tmp = new ArrayList<>();// データを追加するための仮リスト
//
//					    SimpleDateFormat formatter = new SimpleDateFormat("MM月dd日");// フォーマッタの作成
//				        String dateString = formatter.format(sale.getDate());// Date → String に変換
//
//				        tmp.add(String.valueOf(sale.getDaySales())); // 売上
//					    tmp.add("AIの予測売上");// 店舗名
//					    tmp.add(dateString);// 日付
//					    ar1.add(tmp);//一行目にデータを追加
//
//	                }
//	            	---------------------------------------------------------------------------------------

				}


//				for文でAI予測をグラフに追加
				// 現在の日から一週間だけ表示
				for(int i = 1; i < 8; i++){
					ArrayList<String> tmp = new ArrayList<>();// データを追加するための仮リスト


					DateTimeFormatter formatter2 = DateTimeFormatter.ofPattern("MM/dd");//フォーマッタ

					LocalDate futureDate = now.plusDays(i); // 現在からi日後を計算


					String sales_date_AI = futureDate.format(formatter2);//Stringに直す


					int year = futureDate.getYear();//i日後の年
					int month = futureDate.getMonthValue();//i日後の月
					int day = futureDate.getDayOfMonth();//i日後の日


					int japan_money  = salesAnalyze.main(month, day, year) * 155;//日本円に変換

			        tmp.add(String.valueOf(japan_money)); // AIが予測した売上
				    tmp.add("AIの予測売上");// 店舗名
				    tmp.add(sales_date_AI);// 日付
				    ar1.add(tmp);//一行目にデータを追加
				}

// ------------------------------------------------------------------------------------------------


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
