<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html lang="ja">

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

<c:import url="../../common/header.jsp" />

<head>
    <title>シフト削除結果</title>
</head>

<body>
	<div class="h1">
    <h1>～シフト削除結果～</h1>
    </div>

	<div class="message">
    <c:if test="${not empty message}">
        <p>${message}</p>
    </c:if>
    </div>

    <c:if test="${not empty errorMessage}">
        <p style="color: red;">${errorMessage}</p>
    </c:if>


	<div class="link">
    <a href="Main.action">メインページへ戻る</a>
    </div>

</body>

<c:import url="../../common/footer.jsp" />

</html>
