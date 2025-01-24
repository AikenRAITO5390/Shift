<%-- シフト編集完了JSP --%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>

<title>シフト編集完了</title>

<style>
.h2 {
	margin-top : 60px;
	text-align : center;
}
.div {
	margin-top : 60px;
	text-align : center;
}
.a {
	margin-top : 60px;
	text-align : center;
}
</style>

<c:import url="../../common/header.jsp"/>

<body>
<div class="body">


	<!-- 画面タイトル -->
	<div class="h2">
		<h2>～シフト編集完了～</h2>
	</div>

	<div class="div">
		<div>正しく変更されました</div>
	</div>

	<div class="div">
    	<a href="Main.action">メインへ</a>
    </div>


</div>

</body>

<c:import url="../../common/footer.jsp"/>

</html>