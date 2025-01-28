package tool;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.DoubleStream;

import smile.data.DataFrame;
import smile.data.Tuple;
import smile.data.formula.Formula;
import smile.data.formula.Term;
import smile.data.type.StructField;
import smile.data.vector.DoubleVector;
import smile.data.vector.IntVector;
import smile.data.vector.StringVector;
import smile.io.Read;
import smile.math.MathEx;
import smile.regression.LinearModel;
import smile.regression.OLS;


public class SalesAnalyze {

	public int main(int sales_month, int sales_day, int sales_year) throws Exception {

		int sales_analyze = 0;

        try {
        	// クラスパスからリソースを取得
            Path csvPath = Paths.get(SalesAnalyze.class.getResource("/csv2/train_1.csv").toURI());
            System.out.println("File Path: " + csvPath);
            DataFrame data = Read.csv(csvPath);

         // データの行数を取得
            int rowCount = data.size();

            // 最初の行を削除（1行目以降の行を取得）
            DataFrame newData = data.slice(1, rowCount);
            // データの1行目を削除
            // プロモーション列を削除
            newData = newData.drop("V4", "V6");



         // StringVectorの例
            StringVector stringVector =newData.stringVector("V5");

            // StringVectorをStreamに変換し、数値にマッピング
            DoubleStream doubleStream = stringVector.stream()
                                                    .mapToDouble(Double::parseDouble);
         // DoubleStream を double[] に変換
            double[] doubleArray = doubleStream.toArray();

            // DoubleVector を作成
            DoubleVector doubleVector = DoubleVector.of("sales", doubleArray);

         // storeVectorの例
            StringVector storeVector =newData.stringVector("V3");

            // storeVectorをStreamに変換し、数値にマッピング
            DoubleStream storeStream = storeVector.stream()
                                                    .mapToDouble(Double::parseDouble);
         // storeStream を double[] に変換
            double[] storeArray = storeStream.toArray();

            // DoubleVector を作成
            DoubleVector storedouVector = DoubleVector.of("store_nbr", storeArray);


            // 結果を表示
            System.out.println("DoubleVector:");
            System.out.println(doubleVector);
            System.out.println("DoubleVector-store:");
            System.out.println(storedouVector);

         // 日付列 (V2) を配列に変換
            String[] dates = newData.stringVector("V2").toStringArray();

         // 日付フォーマットの指定
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

            // 欠損値や空白をフィルタリング
            dates = Arrays.stream(dates)
                    .filter(date -> date != null && !date.trim().isEmpty())
                    .toArray(String[]::new);


            // 年と月を抽出して IntVector を作成
            int[] years = Arrays.stream(dates)
                    .map(date -> LocalDate.parse(date, formatter).getYear())
                    .mapToInt(Integer::intValue)
                    .toArray();
            IntVector year = IntVector.of("year", years);

            int[] months = Arrays.stream(dates)
                    .map(date -> LocalDate.parse(date, formatter).getMonthValue())
                    .mapToInt(Integer::intValue)
                    .toArray();
            IntVector month = IntVector.of("month", months);

         // dates列から日にちを取得
            int[] days = Arrays.stream(dates)
                               .map(date -> LocalDate.parse(date, formatter).getDayOfMonth())
                               .mapToInt(Integer::intValue)
                               .toArray();
            IntVector day = IntVector.of("day", days);

            System.out.println("Original DataFrame size: " + newData.size());
            System.out.println("Year Vector size: " + year.size());
            System.out.println("Month Vector size: " + month.size());
            System.out.println("Day Vector size: " + day.size());

            // 新しいカラムを DataFrame に追加
            DataFrame updatedData =newData.drop("V5").merge(doubleVector,storedouVector, year, month,day);

         // 売上（V5）のデータを取得
            double[] sales = updatedData.doubleVector("sales").toDoubleArray();
         // 最大値を計算
            double maxValue = Arrays.stream(sales).max().orElse(Double.NaN);

            // 結果を表示
            System.out.println("Maximum sales value1: " + maxValue);


            // 四分位数を計算
            double q1 = percentile(sales, 25);
            double q3 = percentile(sales, 75);
            double iqr = q3 - q1; // 四分位範囲

            // 外れ値の閾値を計算
            double lowerBound = q1 - 1.5 * iqr;
            double upperBound = q3 + 1.5 * iqr;

            List<Tuple> filteredRows = updatedData.stream()
                .filter(row -> {
                    double value = Double.parseDouble(row.getString("sales"));
                    return value >= lowerBound && value <= upperBound; // 範囲内の値のみ保持
                })
                .collect(Collectors.toList());

                // フィルタリングしたデータから新しい DataFrame を構築
                DataFrame filteredData = DataFrame.of(filteredRows);

             // 売上（V5）のデータを取得
             double[] salescheck = filteredData.doubleVector("sales").toDoubleArray();

             // 最大値を計算
             double maxValue2 = Arrays.stream(salescheck).max().orElse(Double.NaN);

             // 結果を表示
             System.out.println("Maximum sales value2: " + maxValue2);

            System.out.println("Number of rows in DataFrame1: " + filteredData.size());
            System.out.println("DataFrame loaded:");
            System.out.println(filteredData.structure());
            System.out.println("Sample data:");
            System.out.println(filteredData.slice(0, Math.min(10, filteredData.size())));
            System.out.println("Columns: " + filteredData.schema());


         // 特徴量とターゲットを分離
            String[] featureColumns = {"store_nbr","sales", "year", "month", "day"};
            String targetColumn = "sales";

            System.out.println("Original Columns in DataFrame: " + Arrays.toString(filteredData.schema().fields()));
            System.out.println("Selected Columns (before filtering): " + Arrays.toString(featureColumns));
            String[] name2 = filteredData.names();
            StructField[] name = filteredData.schema().fields();
            for (String str : name2) {
                System.out.println(str);

            }
            for(StructField str2:name){
            	System.out.println(str2);
            }



            // 有効なカラムを選択
            List<String> validColumns = Arrays.stream(featureColumns)
                .filter(column -> Arrays.asList(filteredData.names()).contains(column))
                .collect(Collectors.toList());

            System.out.println("Valid Columns after filtering: " + validColumns);


            DataFrame features = filteredData.select(validColumns.toArray(new String[0]));
            double[] target = filteredData.doubleVector(targetColumn).toDoubleArray();

            System.out.println("Original DataFrame Structure:");
            System.out.println(filteredData.structure());
            System.out.println("Selected feature columns: " + Arrays.toString(featureColumns));
            System.out.println("Features Structure:");
            System.out.println(features.structure());
            System.out.println("First 10 rows of features:");
            System.out.println(features.slice(0, Math.min(10, features.size())));

            // データが空でないことを確認
            if (features.size() == 0 || target.length == 0) {
            	throw new IllegalStateException("Features or target is empty.");
            }
            // サイズ確認
            System.out.println("Features size: " + features.size());
            System.out.println("Target size: " + target.length);

            // データをシャッフル
            int[] indices = MathEx.permutate(features.size());

            // トレーニングとテストの分割
            int trainSize = (int) (features.size() * 0.8);
            System.out.println("trainSize"+trainSize);
            int[] trainIndices = Arrays.copyOfRange(indices, 0, trainSize);
            int[] testIndices = Arrays.copyOfRange(indices, trainSize, features.size());
         // インデックスの中身を確認
            System.out.println("Train Indices (first 10): " + Arrays.toString(Arrays.copyOfRange(trainIndices, 0, Math.min(10, trainIndices.length))));
            System.out.println("Train Indices Size: " + trainIndices.length);

            System.out.println("Test Indices (first 10): " + Arrays.toString(Arrays.copyOfRange(testIndices, 0, Math.min(10, testIndices.length))));
            System.out.println("Test Indices Size: " + testIndices.length);

            System.out.println("Train indices size: " + trainIndices.length);
            if (trainIndices.length > features.size()) {
                throw new IllegalArgumentException("Train indices exceed features size.");
            }

            DataFrame trainFeatures = DataFrame.of(
                    Arrays.stream(trainIndices).mapToObj(features::get).collect(Collectors.toList())
                );
            System.out.println("成功1");
            System.out.println("成功サイズ"+trainFeatures.size());
            System.out.println("Train Features Structure:");
            System.out.println(trainFeatures.structure());

            // テストセットを作成
            DataFrame testFeatures = DataFrame.of(
                Arrays.stream(testIndices).mapToObj(features::get).collect(Collectors.toList())
            );
            System.out.println("成功");
            System.out.println("成功サイズ"+testFeatures.size());
            // 結果表示
            System.out.println("Test Features Structure:");
            System.out.println(testFeatures.structure());



            Formula formula = Formula.of("sales", "month","day");
            for(Term str:formula.predictors()){
            	System.out.println("予測子:"+str);
            }
            System.out.println("ターゲット"+formula.response());
            LinearModel ols = OLS.fit(formula, trainFeatures);

         // モデルの係数を表示
            System.out.println("Model coefficients: " + Arrays.toString(ols.coefficients()));
            double[][] testFeatures1 = {
            	    {sales_day,sales_month, sales_year}, // January 2023
            	};
            double[] predictions = ols.predict(testFeatures1);

            System.out.println("Predictions:");
            for (int i = 0; i < testFeatures1.length; i++) {
                System.out.printf("Month %d: %.2f%n", (int) testFeatures1[i][0], predictions[i]);
            }

            sales_analyze= (int) predictions[0];

            return sales_analyze;

         // 保存先ディレクトリが存在しない場合は作成
//            if (!Files.exists(writePath.getParent())) {
//                Files.createDirectories(writePath.getParent());
//            }

        } catch (Exception e) {
            e.printStackTrace();
            System.err.println("Failed to load training data: " + e.getMessage());
        }
		return sales_analyze;

	}

	// 四分位数を計算するメソッド
    private static double percentile(double[] values, double percentile) {
        Arrays.sort(values);
        int index = (int) Math.ceil(percentile / 100.0 * values.length);
        return values[index - 1];
    }


}
