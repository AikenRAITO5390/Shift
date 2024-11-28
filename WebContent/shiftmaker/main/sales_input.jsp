<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<%@ page import="java.text.SimpleDateFormat" %>
<%@ page import="java.util.Date" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>売上入力</title>
</head>

<c:import url="../../common/header.jsp"/>

<div class="tmain">

	<h2>売上入力</h2>


	<form action="SalesInputResult.action">


		<label>日付を選択してください</label>
		<!-- 入力したい売上の日付選択 -->
		<input type="date" name="sales_date" />

		<label>売上金額を選択してください</label>
		<!-- 売上金額入力 -->
		<input type="text" name="sales" placeholder="売上金額を入力" maxlength="8" value="${sales}" required />
		<div>${errors.get("sales")}</div>


		<!-- 登録ボタン -->
	    <input type="submit" value="登録" />

	    <!-- 戻るリンク -->
	    <a href="Main.action">TOPへ</a>

	</form>

</div>

<c:import url="../../common/footer.jsp"/>

</html>