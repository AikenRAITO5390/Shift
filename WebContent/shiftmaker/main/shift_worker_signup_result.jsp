<%-- 従業員希望シフト登録完了JSP --%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="ja">

<title>送信完了</title>

<style>
.h1 {
	margin-top : 60px;
	text-align : center;
}
.p {
	margin-top : 60px;
	text-align : center;
}
.main {
	margin-top : 60px;
	text-align : center;
}
</style>

	<c:import url="../../common/header_work.jsp"/>

	<body>

	<div class="body">

			<div class="h1">
			<h2>～送信完了～</h2>
			</div>

			<div class="p">
			<p>正しく送信されました</p>
			</div>

			<div class="main">
			<a href="MainWork.action">メインへ</a>
			</div>

	</div>

	<c:import url="../../common/footer.jsp"/>

</html>