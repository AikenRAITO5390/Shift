package shiftmaker.main;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import bean.Store;
import bean.Worker;
import dao.WorkerDao;
import tool.Action;

public class MainWorkAction extends Action{

	public void execute(HttpServletRequest req, HttpServletResponse res) throws Exception {
		//ローカル変数の宣言 1
		//リクエストパラメータ―の取得 2
		HttpSession session = req.getSession();//セッション
		WorkerDao wDao = new WorkerDao();
		Worker worker_login = (Worker)session.getAttribute("user");
		Map<String, String> errors = new HashMap<>();

		Worker worker = wDao.get(worker_login.getWorkerId());
		Store store = worker.getStore();
		System.out.println(store);
		if (worker != null) {// ゲットした社員のID,名前、パスワード、メール、店舗名を渡す
			req.setAttribute("WorkerName", worker.getWorkerName());
		} else {// 学生が存在していなかった場合
			errors.put("managername", "社員情報が存在していません");
			req.setAttribute("errors", errors);
		}
		//DBへデータ保存 5
		//なし
		//レスポンス値をセット 6
		//JSPへフォワード 7
		req.getRequestDispatcher("main_work.jsp").forward(req, res);
	}
	}


