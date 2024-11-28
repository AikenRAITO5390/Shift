package shiftmaker.main;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import bean.Sales;
import bean.Store;
import dao.SalesDao;
import tool.Action;


public class SalesDetailAction extends Action {

	@Override
	public void execute(HttpServletRequest req,HttpServletResponse res)throws Exception{


		//ローカル変数の宣言 1
				HttpSession session = req.getSession();// セッションを取得
				Sales sales = new Sales();
				SalesDao salesDao = new SalesDao();//売上DAOを初期化
				Store manager = (Store) session.getAttribute("user");// ログインユーザーを取得
//				String sales_date = "";//年月日

//				店舗の日付ごとの売上情報を取得
				List<Sales> salelist = salesDao.filter(manager);



				//### 元データの作成 ###
				// ArrayList<ArrayList<String>> を作成
				ArrayList<ArrayList<String>> ar1 = new ArrayList<>();

				// salelist をループしてデータを変換
				for (Sales sale : salelist) {
				    ArrayList<String> tmp = new ArrayList<>();

				    tmp.add(String.valueOf(sale.getDaySales())); // 売上
				    tmp.add("日商売上");// 店舗名
			        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");// フォーマッタの作成
			        String dateString = formatter.format(sale.getDate());// Date → String に変換
				    tmp.add(dateString);// 日付
				    ar1.add(tmp);//一行目にデータを追加
				}



				//### 元データをセッションに保持 ###
				session.setAttribute("chart1", ar1);
				//セッションに保存されているか確認
				System.out.println("動的に生成されたセッションデータ: " + ar1);// デバッグ用の出力




		        // リクエストにデータをセット
		        req.setAttribute("salelist", salelist);

		        System.out.println("salelist: " + salelist);// デバッグ用の出力


		req.getRequestDispatcher("sales_detail.jsp").forward(req, res);
	}

}
