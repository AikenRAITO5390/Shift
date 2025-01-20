<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<!DOCTYPE html>
<html lang="ja">

<c:import url="../../common/header.jsp" />

<head>
    <title>シフト削除結果</title>
</head>

<body>
    <h1>シフト削除結果</h1>

    <!-- 正常メッセージ -->
    <c:if test="${not empty message}">
        <p style="color: green;">${message}</p>
    </c:if>

    <!-- エラーメッセージ -->
    <c:if test="${not empty errorMessage}">
        <p style="color: red;">${errorMessage}</p>
    </c:if>

    <!-- メインへボタン -->
    <a href="Main.action">
    	<button type="submit">メインへ</button>
    </a>

    <!-- 戻るボタン -->
    <form action="ShiftDeleteCheckAction" method="get">
        <input type="hidden" name="year" value="${param.year}">
        <input type="hidden" name="month" value="${param.month}">
        <button type="submit">戻る</button>
    </form>

</body>

<c:import url="../../common/footer.jsp" />

</html>