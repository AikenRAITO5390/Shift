<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<%@ page import="java.text.SimpleDateFormat" %>
<%@ page import="java.util.Date" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<link rel="stylesheet" href="../../css/style.css">
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>売上入力</title>
</head>

<c:import url="../../common/header.jsp"/>

<div class="tmain">

	<h2>売上入力</h2>


	<form action="SalesInputResult.action">


		<label>日付を選択してください</label>
		<!-- 入力したい売上の日付選択 -->
		<%
    		java.time.LocalDate today = java.time.LocalDate.now();
    		String formattedDate = today.toString(); // YYYY-MM-DD 形式
		%>
		<input type="date" name="sales_date" value="<%= formattedDate %>" />

		<br>
		<label>売上金額を選択してください</label>
		<!-- 売上金額入力 -->
		<input type="number" name="sales" placeholder="売上金額を入力" maxlength="8" value="${sales}" min="0" required />
		<div>${errors.get("sales")}</div>


		<!-- 登録ボタン -->
	    <input type="submit" value="登録" />

	    <!-- 戻るリンク -->
	    <a href="Sales.action">戻る</a>

	</form>

</div>

<c:import url="../../common/footer.jsp"/>

</html>