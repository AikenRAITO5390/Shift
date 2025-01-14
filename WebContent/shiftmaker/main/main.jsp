<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<link rel="stylesheet" href="../../css/style.css">
<jsp:include page="../../common/header.jsp" />
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>まるごとシフトくん</title>
</head>
<c:import url="../../common/header.jsp"/>
<body>
<div class="main-content">
<div class="left-content">
<h2>📜　シフト</h2>
<ul>
        <li><a href="ShiftSelect.action">作成</a></li>
        <li><a href="ShiftEdit.action">編集</a></li>
        <li><a href="#">閲覧</a></li>
        <li><a href="#">削除</a></li>
</ul>
<div class="h2top-content">
<h2>👦　シフト作成者</h2>
<ul>
        <li><a href="ShiftManagerSignUp.action">変更</a></li>
</ul>
</div>
</div>

<div class="center-content">
<h2>👬　従業員</h2>
<ul>
        <li><a href="WorkerSignUp.action">登録</a></li>
        <li><a href="WorkerList.action">編集</a></li>
        <li><a href="WorkerDate.action">閲覧</a></li>
        <li><a href="WorkerList.action">削除</a></li>
</ul>

<div class="h2top-content">
<h2>💪　シフト条件</h2>
<ul>
        <li><a href="ShiftConditionWorkerList.action">変更</a></li>
        <li><a href="ShiftTimeSignup.action">時間設定</a></li>
        <li><a href="PowerSetting.action">パワーバランス設定</a></li>
</ul>
</div>
</div>

<div class="right-content">
<h2>📝　掲示板</h2>
<ul>
        <li><a href="BBS.action">掲示板</a></li>
</ul>


<h2>💰　売上</h2>
<ul>
        <li><a href="Sales.action">売上</a></li>

</ul>
</div>
</div>
</body>
<c:import url="../../common/footer.jsp"/>
</html>