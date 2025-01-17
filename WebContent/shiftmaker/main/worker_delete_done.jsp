<%-- 科目削除完了JSP --%>
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
.p {
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
	<h2>従業員情報削除</h2>
</div>

<div class="p">
	<p>削除が完了しました</p>
</div>

<div class="h2">
	<a href="WorkerList.action">従業員情報一覧へ</a>
</div>

<c:import url="../../common/footer.jsp"/>

</body>
</html>