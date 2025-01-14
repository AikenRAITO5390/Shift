<%-- 時間更新完了画面 --%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>

<style>
.h2 {
	margin-top : 60px;
	text-align : center;
}
.h2 p{
	margin-top : 40px;
	text-align : center;
}
.h2 a{
	margin-top : 70px;
	text-align : center;
}
</style>

<head>
<link rel="stylesheet" href="../../css/style.css">
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
</head>
<body>
<%-- ヘッダーとサイドバー --%>
<c:import url="../../common/header.jsp"/>

<div class="h2">
	<h2>時間変更完了</h2>

		<p>変更が完了しました</p>
	<a href="Main.action">メインメニューへ</a>
</div>
</body>
</html>

