package shiftmaker.main;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import tool.Action;

public class ShiftCreateResultAction extends Action{
	@Override
	public void execute(HttpServletRequest req, HttpServletResponse res) throws Exception {

		//shift_select.jspに遷移
		req.getRequestDispatcher("shift_create_result.jsp").forward(req,res);
	}
}
