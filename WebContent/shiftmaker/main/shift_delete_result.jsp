<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html lang="ja">

<c:import url="../../common/header.jsp" />

<head>
    <title>シフト削除結果</title>
</head>

<body>
    <h1>～シフト削除結果～</h1>

    <c:if test="${not empty message}">
        <p>${message}</p>
    </c:if>

    <c:if test="${not empty errorMessage}">
        <p style="color: red;">${errorMessage}</p>
    </c:if>

    <a href="Main.action">メインページへ戻る</a>

</body>

<c:import url="../../common/footer.jsp" />

</html>
