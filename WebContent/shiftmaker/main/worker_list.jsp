<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<style>
.h2 {
	margin-top : 60px;
	text-align : center;
}
.h3 {
	text-align : left;
	margin-left : 50px;
	margin-bottom : 5px;
}
.h3_h3 {
	text-align : left;
	margin-left : 50px;
}
.workertable {
    width: 100%; /* 画面の幅いっぱいに広げる */
    margin-left : 5px;
    text-align : center;

    white-space: nowrap; /* 改行をしない */
    overflow: hidden; /* はみ出た部分を非表示 */
    text-overflow: ellipsis; /* 省略記号を表示 */
}
.workertable th {
    background-color: #6495ED; /* 背景色を水色に設定 */
    border: 1px solid #000; /* 枠線を黒に設定 */
    color: white;
    width: 10%; /* 画面の幅いっぱいに広げる */
}
.managertable {
    width: 100%; /* 画面の幅いっぱいに広げる */
    margin-left : 5px;
    text-align : center;

    white-space: nowrap; /* 改行をしない */
    overflow: hidden; /* はみ出た部分を非表示 */
    text-overflow: ellipsis; /* 省略記号を表示 */
}
.managertable th {
    background-color: #6495ED; /* 背景色を水色に設定 */
    border: 1px solid #000; /* 枠線を黒に設定 */
    color: white;
    width: 10%; /* 画面の幅いっぱいに広げる */
    margin-left : 30px;
    margin-bottom: 50px;
}
.main {
    margin-left: 1100px;
    margin-top: -400px;
}
</style>

<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>まるごとシフトくん</title>
</head>

<c:import url="../../common/header.jsp"/>

<body>

<div class="h2">
    <h2>～従業員一覧～</h2>
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

                    <td><a href="WorkerUpdate.action?workerId=${worker.workerId}">変更</a>
                    <a href="WorkerDelete.action?WORKER_ID=${worker.workerId}">削除</a></td>


                </tr>
            </c:forEach>
        </table>
        <hr>
    </c:if>
</div>


<div class="h3_h3">
	<h3>社員</h3>
</div>

<c:if test="${empty workers}">
    <p>社員が見つかりませんでした。</p>
</c:if>


<div class="managertable">
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

                <td><a href="WorkerUpdate.action?workerId=${worker.workerId}">変更</a>
                <a href="WorkerDelete.action?WORKER_ID=${worker.workerId}">削除</a></td>
            </tr>
        </c:forEach>
    </table>
    <hr>
</c:if>
</div>

<div class="main">
	<a href="Main.action">トップページへ戻る</a>
</div>
<c:import url="../../common/footer.jsp"/>

</body>