<%-- シフト閲覧JSP --%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>シフト閲覧</title>
</head>
<body>

<c:import url="../../common/header.jsp"/>

<div class="body">

	<h2>シフト閲覧</h2>

	<h3>閲覧したいシフトを選択してください</h3>



	<form action="ShiftEmployee.action" method="post" class="form1">

	<button class="button1">社員用</button>

	</form>



	<form action="Shift.action" method="post" class="form1">

	<button class="button1">バイト用</button>

	</form>


</div>

<!-- 戻るリンク -->
<div>
	    <a href="Main.action">戻る</a>
</div>

<c:import url="../../common/footer.jsp"/>

</body>
</html>