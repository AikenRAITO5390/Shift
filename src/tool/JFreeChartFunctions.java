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