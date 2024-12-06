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
			SalesDao salesDao = new SalesDao();//売上DAOを初期化
			Store manager = (Store) session.getAttribute("user");// ログインユーザーを取得

			try {

				//店舗の日付ごとの売上情報を取得
				List<Sales> salelist = salesDao.filter(manager);

				//売上情報がなかった場合、メッセージを返す
				if (salelist == null || salelist.isEmpty()) {
				    req.setAttribute("message", "売上データがありません。");
				}



				//### 売上グラフの元データの作成 ###
				ArrayList<ArrayList<String>> ar1 = new ArrayList<>();

				// salelist をループしてデータを変換
				for (Sales sale : salelist) {
				    ArrayList<String> tmp = new ArrayList<>();

				    tmp.add(String.valueOf(sale.getDaySales())); // 売上
				    tmp.add("日商売上");// 店舗名
			        SimpleDateFormat formatter = new SimpleDateFormat("yyyy年MM月dd日");// フォーマッタの作成
			        String dateString = formatter.format(sale.getDate());// Date → String に変換
				    tmp.add(dateString);// 日付
				    ar1.add(tmp);//一行目にデータを追加
				}


				//
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
