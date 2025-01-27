<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>

<style>
.h1 {
	margin-top : 60px;
	text-align : center;
}
.message {
	margin-top : 3%;
	text-align : center;
}
.link {
	margin-top : 5%;
	text-align : center;
}
</style>

<c:import url="../../common/header.jsp"/>

<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>シフト作成完了</title>
</head>
<body>

<div class="h1">
<h1>～シフト作成完了～</h1>
</div>

<div class="message">
<h3>正しく保存されました</h3>
</div>

<div class="link">
<a href="Main.action">メインページへ戻る</a>
</div>
</body>

<c:import url="../../common/footer.jsp"/>

</html>