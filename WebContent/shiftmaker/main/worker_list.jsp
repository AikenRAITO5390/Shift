<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<style>
.h2 h2{
	margin-top : 60px;
	text-align : center;
}
.h3 h3{
	text-align : left;
}
.table{
	border-collapse: collapse;
    width: 88%;
}
.tr ,th {
    border: 1px solid #000;
    padding: 4px;
    text-align: center;
    background-color: #6495ED;
    color: white;
}
.tr ,td {
    border: 1px solid #000;
    padding: 8px;
    text-align: center;
}
.td ,a {
	margin-left : 50px;
	color: red;
}


.worker-links {
    display: flex;
    justify-content: flex-end;
    margin-top: -30px;
    margin-right: 19px;
}
.worker-links a {
    margin-left: 10px;
    color: red;
    text-decoration: none;
}


.main a {
    margin-left: 20px;
    color: black;
    margin-top: 50px;
    margin-bottom: 30px;

}
</style>

<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>まるごとシフトくん</title>
</head>

<c:import url="../../common/header.jsp"/>

<body>

<div class="h2">
    <h2>従業員一覧</h2>
</div>

<div class="h3">
    <h3>アルバイト</h3>
</div>

<c:if test="${empty workersnot}">
    <p>従業員が見つかりませんでした。</p>
</c:if>

<div class="workertable">
    <c:if test="${not empty workersnot}">
        <table class="table table-hover">
            <tr>
                <th>ID</th>
                <th>名前</th>
                <th>生年月日</th>
                <th>住所</th>
                <th>電話番号</th>
                <th>パスワード</th>
                <th>店情報</th>
            </tr>
            <c:forEach var="worker" items="${workersnot}">
                <tr>
                    <td>${worker.workerId}</td>
                    <td>${worker.workerName}</td>
                    <td>${worker.workerDate}</td>
                    <td>${worker.workerAddress}</td>
                    <td>${worker.workerTel}</td>
                    <td>${worker.workerPassword}</td>
                    <td>${stores.storeName}</td>
                </tr>
            </c:forEach>
        </table>
        <!-- 変更と削除リンクをテーブル外に配置 -->
        <div class="worker-links">
            <c:forEach var="worker" items="${workersnot}">
                <a href="WorkerUpdate.action?workerId=${worker.workerId}">変更</a>
                <a href="WorkerDelete.action?WORKER_ID=${worker.workerId}">削除</a>
            </c:forEach>
        </div>
    </c:if>
</div>

<h3>社員</h3>

<c:if test="${empty workers}">
    <p>社員が見つかりませんでした。</p>
</c:if>

<c:if test="${not empty workers}">
    <table class="table table-hover">
        <tr>
            <th>ID</th>
            <th>名前</th>
            <th>生年月日</th>
            <th>住所</th>
            <th>電話番号</th>
            <th>パスワード</th>
            <th>店情報</th>
        </tr>
        <c:forEach var="worker" items="${workers}">
            <tr>
                <td>${worker.workerId}</td>
                <td>${worker.workerName}</td>
                <td>${worker.workerDate}</td>
                <td>${worker.workerAddress}</td>
                <td>${worker.workerTel}</td>
                <td>${worker.workerPassword}</td>
                <td>${stores.storeName}</td>
            </tr>
        </c:forEach>
    </table>
    <!-- 変更と削除リンクをテーブル外に配置 -->
    <div class="worker-links">
        <c:forEach var="worker" items="${workers}">
            <a href="WorkerUpdate.action?workerId=${worker.workerId}">変更</a>
            <a href="WorkerDelete.action?WORKER_ID=${worker.workerId}">削除</a>
        </c:forEach>
    </div>
</c:if>

<div class="main">
	<a href="Main.action">メインへ戻る</a>
</div>
<c:import url="../../common/footer.jsp"/>

</body>