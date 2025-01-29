package shiftmaker.main;

import java.awt.BasicStroke;
import java.awt.Color;
import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartUtilities;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.StandardChartTheme;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.renderer.category.LineAndShapeRenderer;
import org.jfree.data.category.DefaultCategoryDataset;

import tool.JFreeChartFunctions;




@WebServlet("/JFreeChartTest")
public class SalesTableCreateAction extends HttpServlet {

//			データセット
	        JFreeChartFunctions jfc = new JFreeChartFunctions();
	        protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	                drawLineGraph(request,response);
	        }

//	         Line Chart/折れ線グラフ
	        protected void drawLineGraph(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	                response.setContentType("image/png");

//	                元データの取得
	                ArrayList<ArrayList<String>> ar_1 = (ArrayList<ArrayList<String>>) request.getSession().getAttribute("chart1");


	                System.out.println("動的に生成されたセッションデータ: " + ar_1);//デバッグ用

//	                データセットの作成
	                ArrayList<Number> ar1 = new ArrayList<>();
	                ArrayList<String> ar2 = new ArrayList<>();
	                ArrayList<String> ar3 = new ArrayList<>();
	                for(int i=0; i<ar_1.size(); i++) {
	                        ar1.add(Integer.parseInt(ar_1.get(i).get(0)));
	                        ar2.add(ar_1.get(i).get(1));
	                        ar3.add(ar_1.get(i).get(2));

	                }

//	                データが順番に入っているかの確認
	                System.out.println("動的に生成されたセッションデータ: " + ar1);
	                System.out.println("動的に生成されたセッションデータ: " + ar2);
	                System.out.println("動的に生成されたセッションデータ: " + ar3);



//	                データセットの作成処理
	                DefaultCategoryDataset ds_cat = jfc.createDS_LineChart(ar1,ar2,ar3);

	                ChartFactory.setChartTheme(StandardChartTheme.createLegacyTheme());

//	                チャートの作成
	                JFreeChart chart=ChartFactory.createLineChart("売上の変動", "日付", "売上金額", ds_cat, PlotOrientation.VERTICAL, true, false, false);


//	                背景色の設定
	                chart.setBackgroundPaint(new Color(230, 230, 230));
//	                グラフ内背景色の設定
	                CategoryPlot plot = chart.getCategoryPlot();
	                plot.setBackgroundPaint(new Color(230, 230, 230));
	                LineAndShapeRenderer renderer = (LineAndShapeRenderer)plot.getRenderer();
//	                点の設定
	                renderer.setSeriesShapesVisible(0, true);
	                renderer.setSeriesShapesVisible(1, true);
	                renderer.setSeriesShapesVisible(2, true);
//	                折れ線の太さ設定
	                chart.getCategoryPlot().getRenderer().setSeriesStroke(0,new BasicStroke(4));
	                chart.getCategoryPlot().getRenderer().setSeriesStroke(1,new BasicStroke(4));
	                chart.getCategoryPlot().getRenderer().setSeriesStroke(2,new BasicStroke(4));

//	                PNG画像生成
	                ServletOutputStream objSos=response.getOutputStream();
	                ChartUtilities.writeChartAsJPEG(objSos, chart, 1000, 500);
	        }


}
