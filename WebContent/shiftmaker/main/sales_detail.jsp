<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.ArrayList" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<link rel="stylesheet" href="../../css/sales.css">
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>売上予測</title>
</head>
<body>
<c:import url="../../common/header.jsp"/>

<div class="tmain">



	<!-- グラフを画像にして表示 -->
	<img src="${pageContext.request.contextPath}/JFreeChartTest" class="imgGraph" />


	<!-- 戻るボタン -->
	<form action="Sales.action" method="post" class="form2">
	<button class="button2">戻る</button>
	</form>

</div>

<c:import url="../../common/footer.jsp"/>

</body>
</html>