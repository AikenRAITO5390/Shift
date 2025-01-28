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
	margin-left : 40%;
}
.button2 {
	margin-top : -33px;
	margin-left : 54%;

	background-color: #6495ED; /* 背景色を水色に設定 */
    color: white;
}
.a {
	margin-top : 80px;
	margin-left : 65%;
}
.ok input[type="submit"]{
	margin-top : -20px;
	margin-left: 55%;
	background-color: #6495ED; /* 背景色を水色に設定 */
	color: white;
	border-radius: 4px; /* 角を丸くする */
	border: none; /* ボーダーをなしに設定 */
}
.ok2 input[type="submit"]{
	margin-top : 25px;
	margin-left: 40%;
	background-color: #6495ED; /* 背景色を水色に設定 */
	color: white;
	border-radius: 4px; /* 角を丸くする */
	border: none; /* ボーダーをなしに設定 */
}

</style>

<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>シフト閲覧</title>
</head>
<body>

<c:import url="../../common/header_work.jsp"/>

<div class="body">

		<h1>～シフト閲覧～</h1>

	<h3>閲覧したいシフトを選択してください</h3>



	<form action="ShiftEmployeeWork.action" method="post">
		<div class="ok2">
		<input type="submit" value="社員用">
		</div>
	</form>



	<form action="ShiftWork.action" method="post">
	<div class="ok">
		<input type="submit" value="バイト用">
	</div>

	</form>


</div>

<!-- 戻るリンク -->
<div>
<div class="a">
	    <a href="MainWork.action">メインへ</a>
</div>
</div>

<c:import url="../../common/footer.jsp"/>

</body>
</html>