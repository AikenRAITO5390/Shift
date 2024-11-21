package shiftmaker.main;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.data.general.DefaultPieDataset;

import tool.Action;

public class SalesDetailAction extends Action {

	@Override
	public void execute(HttpServletRequest req,HttpServletResponse res)throws Exception{

		//ローカル変数の宣言 1
		//なし
		//リクエストパラメータ―の取得 2
		//なし
		// DBからデータ取得 3
		//後々いるはず
		// ビジネスロジック 4
		// DBへデータ保存 5
		// なし
		// レスポンス値をセット 6
		// JSPへフォワード 7
		req.getRequestDispatcher("sales_detail.jsp").forward(req, res);
	}



	public class JFreeChartFunctions {

        //(1) Create Dataset for "createPieChart3D"
        public DefaultPieDataset createDS_PieChart(ArrayList<ArrayList> ar1) {

                //### ⑤データセットの作成処理 ###
                DefaultPieDataset defpie = new DefaultPieDataset();
                for(int i=0; i<ar1.size(); i++) {
                        defpie.setValue(ar1.get(i).get(0),Integer.parseInt(ar1.get(i).get(1)));
                }
                return defpie;
        }
        //(2) Create DataSet for "createLineChart"
        public DefaultCategoryDataset createDS_LineChart(ArrayList key1, ArrayList key2, ArrayList key3) {

                //### ⑤データセットの作成処理 ###
                DefaultCategoryDataset defcat = new DefaultCategoryDataset();
                for(int i=0; i<key1.size(); i++) {
                        defcat.addValue(key1.get(i), key2.get(i), key3.get(i));
                }
                return defcat;
        }
	}




}
