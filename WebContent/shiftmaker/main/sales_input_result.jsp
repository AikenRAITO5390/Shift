<%-- 売上登録完了JSP --%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<link rel="stylesheet" href="../../css/sales.css">
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>売上入力</title>
</head>
<body>
	<c:import url="../../common/header.jsp"/>

<div class="tmain">
	<h2>～売上登録完了～</h2>

	<p>売上の登録が完了しました</p>


	<a href="Sales.action">売上へ</a>

	<a href="Main.action">メインメニューへ</a>


</div>
<c:import url="../../common/footer.jsp"/>

</body>
</html>