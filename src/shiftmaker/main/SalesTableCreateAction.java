package shiftmaker.main;

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
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.DefaultCategoryDataset;

import tool.JFreeChartFunctions;




@WebServlet("/JFreeChartTest")
public class SalesTableCreateAction extends HttpServlet {

//			データセット
	        JFreeChartFunctions jfc = new JFreeChartFunctions();
	        protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	                drawLineGraph(request,response);
	        }

////	         Line Chart/折れ線グラフ
	        protected void drawLineGraph(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	                response.setContentType("image/png");

//	                元データの取得
	                ArrayList<ArrayList<String>> ar = (ArrayList<ArrayList<String>>) request.getSession().getAttribute("chart1");

//	                データセットの作成
	                ArrayList<Number> ar1 = new ArrayList<>();
	                ArrayList<String> ar2 = new ArrayList<>();
	                ArrayList<String> ar3 = new ArrayList<>();
	                for(int i=0; i<ar.size(); i++) {
	                        ar1.add(Integer.parseInt(ar.get(i).get(0)));
	                        ar2.add(ar.get(i).get(1));
	                        ar3.add(ar.get(i).get(2));
	                }
//	                データセットの作成処理
	                DefaultCategoryDataset ds_cat = jfc.createDS_LineChart(ar1,ar2,ar3);

//	                チャートの作成
	                JFreeChart chart=ChartFactory.createLineChart("お菓子の売上数", "月", "売れた数", ds_cat, PlotOrientation.VERTICAL, true, false, false);

//	                PNG画像生成
	                ServletOutputStream objSos=response.getOutputStream();
	                ChartUtilities.writeChartAsJPEG(objSos, chart, 600, 400);
	        }

}
