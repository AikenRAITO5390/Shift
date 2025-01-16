<%-- 掲示板投稿完了JSP --%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>

<style>
.h2 h1{
	margin-top : 60px;
	text-align : center;
}
.p h4{
	margin-top : 60px;
	text-align : center;
}
.a {
	margin-top : 40px;
	text-align : center;
	margin-left : 30px;
	}

</style>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>まるごとシフトくん</title>
</head>

<c:import url="../../common/header.jsp"/>


<body>
<div class="h2">
<h1>～掲示板投稿～</h1>
</div>

		<div class="p">
<h4>投稿が完了しました</h4>
</div>

	<div class="a">
<a href="BBS.action">掲示板一覧</a>
</div>

<c:import url="../../common/footer.jsp"/>


</body>
</html>