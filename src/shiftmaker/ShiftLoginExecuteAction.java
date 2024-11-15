package shiftmaker;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import bean.Store;
import dao.StoreDao;
import tool.Action;

public class ShiftLoginExecuteAction extends Action{

	@Override
	public void execute(HttpServletRequest req, HttpServletResponse res) throws Exception {
		//ローカル変数の宣言 1
		String url = "";

		Store store = new Store();
		//School school = new School();

		//これ作った
		StoreDao storeDAO=new StoreDao();

		//リクエストパラメータ―の取得 2
		String MANAGER_ID = req.getParameter("MANAGER_ID");
		String PASSWORD = req.getParameter("PASSWORD");


		//String name = req.getParameter("namae");
		//String school_cd = req.getParameter("school_cd");

		System.out.println(req.getParameter("MANAGER_ID"));
		System.out.println(PASSWORD);

		//DBからデータ取得 3
		store=storeDAO.login(MANAGER_ID,PASSWORD);
		//なし
		//ビジネスロジック 4
/*
		teacher.setId(id);
		teacher.setPassword(password);
		teacher.setName(name);

		school.setCd(school_cd);
		school.setName("金沢情報ITクリエイター専門学校");

		teacher.setSchool(school);//School型*/

		// 認証済みフラグを立てる
//		teacher.setAuthenticated(true);
		System.out.println("①★★★★★★★★★★★★★★");
		//もし、ログインが成功したら
		if(store!=null){
			System.out.println("②★★★★★★★★★★★★★★");
			//Sessionを有効にする
			HttpSession session = req.getSession(true);
			store.setAuthenticated(true);


			//セッションに"user"という変数名で値はTeacher変数の中身
			session.setAttribute("user", store);


			//リダイレクト
			url = "main/Main.action";

			res.sendRedirect(url);
		}else{
				System.out.println("ffff");
				//認証失敗
				 List<String> errors = new ArrayList<>();
					errors.add("ログインに失敗しました。IDまたはパスワードが正しくありません。");
					req.setAttribute("errors", errors);

				//JSPへフォワード
				url = "login2.jsp";
				req.getRequestDispatcher(url).forward(req, res);



	}
}
}
