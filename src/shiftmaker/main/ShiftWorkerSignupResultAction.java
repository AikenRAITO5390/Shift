
package shiftmaker.main;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import tool.Action;


// 希望シフト完了ページへ飛ばす
public class ShiftWorkerSignupResultAction extends Action{

	@Override
	public void execute(HttpServletRequest req, HttpServletResponse res) throws Exception {

		System.out.println("★★★★★★④★★★★★★");

		System.out.println("完了ページ");


	    // JSPへフォワード
	    req.getRequestDispatcher("shift_worker_signup_result.jsp").forward(req, res);
	}

}
