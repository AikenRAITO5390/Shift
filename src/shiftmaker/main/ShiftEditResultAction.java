package shiftmaker.main;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import tool.Action;

// シフト編集完了ページへ飛ばす
public class ShiftEditResultAction extends Action{

	@Override
	public void execute(HttpServletRequest req, HttpServletResponse res) throws Exception {

		System.out.println("★★★★★★④★★★★★★");

		System.out.println("完了ページ");


	    // JSPへフォワード
	    req.getRequestDispatcher("shift_edit_result.jsp").forward(req, res);
	}

}
