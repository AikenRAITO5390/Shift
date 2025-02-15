<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<%@ page import="java.text.SimpleDateFormat" %>
<%@ page import="java.util.Date" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<link rel="stylesheet" href="../../css/sales.css">
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>売上入力</title>
</head>
<body>

<c:import url="../../common/header.jsp"/>


<div class="under">
	<div class="tmain">

	<h2>～売上入力～</h2>


		<form action="SalesInputResult.action" class="sales_input">

			<div class="hiduke">

				<label class="label1">日付を選択してください</label>
				<!-- 入力したい売上の日付選択 -->
				<%
		    		java.time.LocalDate today = java.time.LocalDate.now();
		    		String formattedDate = today.toString(); // YYYY-MM-DD 形式
				%>
				<input type="date" name="sales_date" class="input_date" max="<%= formattedDate %>" value="<%= formattedDate %>" />

			</div>

			<br>

			<div class="money">

			<label class="label1">売上金額を選択してください</label>
			<!-- 売上金額入力 -->
			<input type="number" name="sales" placeholder="売上金額を入力" maxlength="8" value="${sales}" max="10000000" min="0" required />
			<div>${errors.get("sales")}</div>

			</div>

			<div class="touroku">
				<!-- 登録ボタン -->
			    <button class="button2">登録</button>


			    <!-- 戻るリンク -->
			    <a href="Sales.action">戻る</a>

			</div>
		</form>

	</div>
</div>

<c:import url="../../common/footer.jsp"/>

</body>
</html>