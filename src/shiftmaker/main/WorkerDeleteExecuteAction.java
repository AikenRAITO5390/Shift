package shiftmaker.main;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import bean.Store;
import bean.Worker;
import dao.StoreDao;
import dao.WorkerDao;
import tool.Action;

public class WorkerDeleteExecuteAction extends Action {
    @Override
    public void execute(HttpServletRequest req, HttpServletResponse res) throws Exception {

        // ローカル変数の宣言
        WorkerDao wDao = new WorkerDao(); // 従業員Dao
        StoreDao sDao = new StoreDao(); // 店Dao
        HttpSession session = req.getSession(); // セッション
        Store store = (Store) session.getAttribute("user"); // ログインユーザーを取得

    	//ログイン情報から名前を取得して"managerName"として渡す
		Store stores = sDao.get(store.getStoreId());

        // 店IDの取得
        String STORE_ID = store.getStoreId();

        // 従業員IDを探す
        String WORKER_ID = req.getParameter("WORKER_ID");

        // 従業員IDから従業員インスタンスを取得
        Worker worker = wDao.get(WORKER_ID);

        // エラーハンドリング用の変数を宣言
        Map<String, String> errors = new HashMap<>();

        // 従業員が存在していた場合
            // 従業員を削除
            wDao.delete(worker);


        // エラーがあった場合、更新画面へ戻る
        if (!errors.isEmpty()) {
            // リクエスト属性をセット
            req.setAttribute("errors", errors);
            req.setAttribute("WORKER_ID", WORKER_ID);
            req.setAttribute("managerName", stores.getManagerName());
            req.getRequestDispatcher("worker_delete_ok.jsp").forward(req, res);
            return;
        }
        req.setAttribute("managerName", stores.getManagerName());

        // 削除完了後に一覧画面へリダイレクト
        req.getRequestDispatcher("worker_delete_done.jsp").forward(req, res);
    }
}