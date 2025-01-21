<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<!DOCTYPE html>
<html lang="ja">

<c:import url="../../common/header.jsp" />

<head>
    <title>シフト削除確認</title>
</head>

<body>
    <h1>シフト削除確認</h1>

    <!-- 削除確認メッセージ -->
    <p>本当に削除しますか？</p>

    <form action="ShiftDeleteResult.action" method="post">
        <!-- 年、月を隠しパラメータとして送信 -->
        <input type="hidden" name="year" value="${year}">
        <input type="hidden" name="month" value="${month}">

        <!-- 削除ボタン -->
        <button type="submit">削除</button>
    </form>

    <!-- 戻るボタン -->
    <form action="ShiftDelete.action" method="get">
        <input type="hidden" name="year" value="${year}">
        <input type="hidden" name="month" value="${month}">
        <button type="submit">戻る</button>
    </form>

</body>

<c:import url="../../common/footer.jsp" />

</html>