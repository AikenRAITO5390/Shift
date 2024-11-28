package shiftmaker.main;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import bean.Sales;
import bean.Store;
import dao.SalesDao;
import tool.Action;

public class SalesInputResultAction extends Action {

	@Override
	public void execute(HttpServletRequest req, HttpServletResponse res) throws Exception {


		//ローカル変数の宣言 1
		HttpSession session = req.getSession();// セッションを取得
		SalesDao salesDao = new SalesDao();//売上DAOを初期化
		Store manager = (Store) session.getAttribute("user");// ログインユーザーを取得
		int money = 0;//売上
		String sales_date = "";//年月日

		System.out.println("１１１１１１１１１１１１１１１１１１１１１１１１１１");

		// リクエストパラメータの取得 ２
		money = Integer.parseInt(req.getParameter("sales"));//売上
		// Date型にして取得
		sales_date = req.getParameter("sales_date");//年月日
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd"); // 日付形式を指定
		LocalDate localDate = LocalDate.parse(sales_date, formatter);


		System.out.println("２２２２２２２２２２２２２２２２２２２２２２２２");

		// LocalDate から sql.Date へ変換
		java.sql.Date sqlDate = java.sql.Date.valueOf(localDate);

		//新しいインスタンスの作成
		Sales sales = new Sales();
		sales.setStore(manager);//店舗情報
		sales.setDate(sqlDate);//日付
		sales.setDaySales(money);//日商売上

		System.out.println("３３３３３３３３３３３３３３３３３３３３３３");

		System.out.println(sales.getStore());
		System.out.println(sales.getDate());
		System.out.println(sales.getDaySales());
		// DBへデータ保存 5
		salesDao.save(sales);

		System.out.println("４４４４４４４４４４４４４４４４４４４４４４");


		req.getRequestDispatcher("sales_input_result.jsp").forward(req, res);
	}

}
