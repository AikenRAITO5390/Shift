<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<!DOCTYPE html>
<html lang="ja">

<style>
.h1 {
	margin-top : 60px;
	text-align : center;
}
.message {
	margin-top : 50px;
	text-align : center;
}
.delete {
	margin-top : 30px;
	margin-left : 55%;
}
.modoru {
	margin-top : -40px;
	margin-left : 40%;
}
</style>

<c:import url="../../common/header.jsp" />

<head>
    <title>シフト削除確認</title>
</head>

<body>

	<div class="h1">
    <h1>～シフト削除確認～</h1>
    </div>

	<div class="message">
    <!-- 削除確認メッセージ -->
    <p>本当に削除しますか？</p>
    </div>

	<div class="delete">
    <form action="ShiftDeleteResult.action" method="post">
        <!-- 年、月を隠しパラメータとして送信 -->
        <input type="hidden" name="year" value="${year}">
        <input type="hidden" name="month" value="${month}">

        <!-- 削除ボタン -->
        <button type="submit">削除</button>
    </form>
    </div>

	<div class="modoru">
    <!-- 戻るボタン -->
    <form action="ShiftDelete.action" method="get">
        <input type="hidden" name="year" value="${year}">
        <input type="hidden" name="month" value="${month}">
        <button type="submit">戻る</button>
    </form>
    </div>

</body>

<c:import url="../../common/footer.jsp" />

</html>