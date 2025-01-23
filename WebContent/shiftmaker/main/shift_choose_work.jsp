<%-- シフト閲覧JSP --%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>

<style>
.body h2{
	margin-top : 60px;
	text-align : center;
}
.body h3{
	margin-top : 60px;
	text-align : center;
}
.button1 {
	margin-top : 40px;
	margin-left : 520px;
}
.button2 {
	margin-top : -40px;
	margin-left : 700px;
}
.a {
	margin-top : 80px;
	margin-left : 860px;
}
</style>

<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>シフト閲覧</title>
</head>
<body>

<c:import url="../../common/header_work.jsp"/>

<div class="body">

		<h2>シフト閲覧</h2>

	<h3>閲覧したいシフトを選択してください。</h3>



	<form action="ShiftEmployeeWork.action" method="post">
		<button class="button1">社員用</button>
	</form>



	<form action="ShiftWork.action" method="post">
	<div class="button2">
		<button>バイト用</button>
	</div>

	</form>


</div>

<!-- 戻るリンク -->
<div>
<div class="a">
	    <a href="MainWork.action">戻る</a>
</div>
</div>

<c:import url="../../common/footer.jsp"/>

</body>
</html>