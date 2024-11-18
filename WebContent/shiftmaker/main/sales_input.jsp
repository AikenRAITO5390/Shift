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

<c:import url="./common/header.jsp"/>

<div class="tmain">
<c:import url="./common/navigation.jsp"/>

	<h2>売上入力</h2>

	<label>日付を選択してください</label>

	<form action="SalesSignupResult">

		<!-- 年の表示 -->
		<%
	    	SimpleDateFormat formatter = new SimpleDateFormat("yyyy年");
	    	String formattedDate = formatter.format(new Date());
		%>

		<!-- 月日の選択 -->
		<select name="month" required>
			<option value="">月を選択</option>
			<c:forEach var="month" items="${month_list}">
				<option value="${month}" ${month == selectedMonth ? 'selected' : ''}>${month}</option>
			</c:forEach>
		</select>

		<select name="day" required>
			<option value="">日を選択</option>
			<c:forEach var="day" items="${day_list}">
				<option value="${day}" ${day == selectedDay ? 'selected' : ''}>${day}</option>
			</c:forEach>
		</select>


		<label>売上金額を選択してください</label>

		<!-- 名前入力 -->
		<label>名前</label>
		<input type="text" name="sales" placeholder="売上金額を入力" maxlength="8" value="${sales}" required />
		<div>${errors.get("sales")}</div>


		<!-- 登録ボタン -->
	    <input type="submit" value="登録" />
	    <!-- 戻るリンク -->
	    <a href="Main.action">TOPへ</a>


	</form>


</div>

<c:import url="./common/footer.jsp"/>

</html>