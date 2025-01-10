<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<link rel="stylesheet" href="../../css/sales.css">
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>売上</title>
</head>
<body>

<c:import url="../../common/header.jsp"/>

<div class="tmain">

	<h2>売上</h2>

	<h3>売上の操作したい項目を選択してください</h3>



	<form action="SalesInput.action" method="post" class="form1">

	<button class="button1">売上入力</button>

	</form>



	<form action="SalesDetail.action" method="post" class="form1">

	<button class="button1">売上予測</button>

	</form>


</div>

<!-- 戻るリンク -->
<div class="tmain">
	    <a href="Main.action">TOPへ</a>
</div>

<c:import url="../../common/footer.jsp"/>

</body>
</html>