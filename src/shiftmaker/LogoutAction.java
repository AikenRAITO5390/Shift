package shiftmaker;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import tool.Action;

public  class LogoutAction extends Action{


	@Override
	public void execute(HttpServletRequest req, HttpServletResponse res) throws Exception {
		//ローカル変数の宣言 1
		HttpSession session=req.getSession();
		System.out.println("★★★★★★manager★★★★★★");
		//リクエストパラメータ―の取得 2
		//なし
		//DBからデータ取得 3
		//なし
		//ビジネスロジック 4
		//なし
		//DBへデータ保存 5
		//なし
		//レスポンス値をセット 6
		session.invalidate();
		System.out.println("a");
		//JSPへフォワード 7
		req.getRequestDispatcher("logout_ok.jsp").forward(req, res);
	}

}
