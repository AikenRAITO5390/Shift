package shiftmaker.main;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import bean.Store;
import dao.StoreDao;
import tool.Action;

public class SalesInputAction extends Action {
	@Override
	public void execute(HttpServletRequest req,HttpServletResponse res)throws Exception{

		//ログイン情報から名前を取得ヘッダーに表示
			HttpSession session = req.getSession();//セッション
			StoreDao sDao = new StoreDao();

			Store store_login = (Store)session.getAttribute("user");

			Store store = sDao.get(store_login.getStoreId());
			req.setAttribute("managerName", store.getManagerName());

		//ローカル変数の宣言 1
		//なし

			//リクエストパラメータ―の取得 2
			//なし


			// DBからデータ取得 3
			//なし


			// ビジネスロジック 4
			// 月、日リストを作成
		    List<Integer> months = IntStream.rangeClosed(1, 12).boxed().collect(Collectors.toList());
		    List<Integer> days = IntStream.rangeClosed(1, 31).boxed().collect(Collectors.toList());


			// DBへデータ保存 5
			// なし

			// レスポンス値をセット 6
			// 月、日のlistをセット
			req.setAttribute("month_list", months);
		    req.setAttribute("day_list", days);

			// JSPへフォワード 7
			req.getRequestDispatcher("sales_input.jsp").forward(req, res);
			}


		}
