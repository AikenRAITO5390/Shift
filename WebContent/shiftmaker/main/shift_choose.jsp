<%-- シフト閲覧JSP --%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>

<style>
.body h1{
	margin-top : 60px;
	text-align : center;
}
.body h3{
	margin-top : 60px;
	text-align : center;
}

.button1 {
	 margin-top : 42px;
	 display: flex;
     justify-content: center;
     align-items: center;
}

button {
    width: 100px;  /* 横幅を100pxに設定 */
}

.button2 {
	 display: flex;
     justify-content: center;
     align-items: center;
     margin-left: 100px;
}

.a {
	margin-top : 80px;
	display: flex;
     justify-content: center;
     align-items: center;
     margin-left: 400px;
}
</style>

<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>シフト閲覧</title>
</head>
<body>

<c:import url="../../common/header.jsp"/>

<div class="body">

		<h1>～シフト閲覧～</h1>

	<h3>閲覧したいシフトを選択してください。</h3>

</div>

	<form action="ShiftEmployee.action" method="post">
		<div class="button1">
		<button>社員用</button>
	</form>



	<form action="Shift.action" method="post">
	<div class="button2">
		<button>バイト用</button>
	</div>
	</div>

	</form>

<!-- 戻るリンク -->

<div class="a">
	    <a href="Main.action">メインへ</a>
</div>

<c:import url="../../common/footer.jsp"/>

</body>
</html>