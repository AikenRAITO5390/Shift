package tool;

import java.util.ArrayList;

import org.jfree.data.category.DefaultCategoryDataset;

public class JFreeChartFunctions {

        public DefaultCategoryDataset createDS_LineChart(ArrayList<Number> key1, ArrayList<String> key2, ArrayList<String> key3) {

                //### ⑤データセットの作成処理 ###
                DefaultCategoryDataset defcat = new DefaultCategoryDataset();
                for(int i=0; i<key1.size(); i++) {
                        defcat.addValue(key1.get(i), key2.get(i), key3.get(i));
                }
                return defcat;
        }


}




////(1) Create Dataset for "createPieChart3D"
//public DefaultPieDataset createDS_PieChart(ArrayList<ArrayList> ar1) {
//
//      //### ⑤データセットの作成処理 ###
//      DefaultPieDataset defpie = new DefaultPieDataset();
//      for(int i=0; i<ar1.size(); i++) {
//              defpie.setValue(ar1.get(i).get(0),Integer.parseInt(ar1.get(i).get(1)));
//      }
//      return defpie;
//}
////(2) Create DataSet for "createLineChart"
