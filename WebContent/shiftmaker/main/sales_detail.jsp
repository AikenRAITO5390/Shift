<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.ArrayList" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>売上予測</title>
</head>
<c:import url="../../common/header.jsp"/>

<div class="tmain">
<c:import url="../../common/navigation.jsp"/>

	<h2>売上予測</h2>


	<!-- グラフ -->


	<%
	//### ①元データの作成 ###

	String [][] input = {
			{"120","売上","1月"},
			{"150","売上","2月"},
			{"130","売上","3月"},
			{"150","クッキー","３月"},
            {"120","クッキー","４月"},
            {"130","クッキー","５月"},
            {"250","グミ","３月"},
            {"190","グミ","４月"},
            {"210","グミ","５月"},
	};

	ArrayList<ArrayList<String>>ar1 = new ArrayList<ArrayList<String>>();
	ArrayList<String> tmp = new ArrayList<String>();
	for(int i=0; i<input.length; i++) {
	        tmp = new ArrayList<String>();
	        tmp.add(input[i][0]);
	        tmp.add(input[i][1]);
	        tmp.add(input[i][2]);
	        ar1.add(tmp);
	}
	//### 元データをセッションに保持 ###
	session.setAttribute("chart1", ar1);
	%>





	<img src="JFreeChartTest?mode=2" />



	<!-- 戻るボタン -->
	<form action="SalesInput.action" method="post">
	<button>戻る</button>
	</form>

</div>

<c:import url="../../common/footer.jsp"/>

</html>