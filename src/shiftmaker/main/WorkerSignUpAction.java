package shiftmaker.main;

import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import bean.Store;
import dao.StoreDao;
import tool.Action;

public class WorkerSignUpAction extends Action{

	@Override
	public void execute(HttpServletRequest req, HttpServletResponse res) throws Exception {

		//ローカル変数の宣言 1
		// セッションを取得
		HttpSession session = req.getSession();
		// StoreDaoを初期化
		StoreDao storeDao = new StoreDao();
		// ログインユーザーを取得
		Store manager = (Store)session.getAttribute("user");

		//リクエストパラメータ―の取得 2
		//なし

		// DBからデータ取得 3
		// ログインユーザーのIDをもとに店舗名の一覧を取得
        List<String> rawStoreList = storeDao.filter(manager.getManagerId());

        // 重複を排除した店舗名リストを作成
        Set<String> uniqueStoreNames = new LinkedHashSet<>(rawStoreList);

		// ビジネスロジック 4

		// 生年月日選択のための年リスト作成（例: 1950年～2023年）
	    List<Integer> years = new ArrayList<>();
	    for (int i = 1950; i <= 2023; i++) {
	        years.add(i);
	    }
	    // 月、日リストを作成
	    List<Integer> months = IntStream.rangeClosed(1, 12).boxed().collect(Collectors.toList());
	    List<Integer> days = IntStream.rangeClosed(1, 31).boxed().collect(Collectors.toList());


		// DBへデータ保存 5
		// なし

		// レスポンス値をセット 6
		// 店舗名のlistをセット
	    req.setAttribute("store_name_set", uniqueStoreNames);
		// 年、月、日のlistをセット
		req.setAttribute("year_list", years);
		req.setAttribute("month_list", months);
	    req.setAttribute("day_list", days);

		// JSPへフォワード 7
		req.getRequestDispatcher("worker_signup.jsp").forward(req, res);
	}


}
