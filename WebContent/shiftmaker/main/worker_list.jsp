<%-- 学生一覧JSP --%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!-- 下記の一文の宣言がないとJSTLを使用できない -->
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
    width: 85%;

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
        <table  class="table table-hover">
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
                    <td><a href="WorkerUpdate.action?workerId=${worker.workerId}">変更</a></td>
                    <td><a href="WorkerDelete.action?WORKER_ID=${worker.workerId}">削除</a></td>


                </tr>
            </c:forEach>
        </table>
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
                    <div class="update">
                    	<a href="WorkerUpdate.action?workerId=${worker.workerId}">変更</a>
                    </div>

                    <div class="delete">
                    	<a href="WorkerDelete.action?WORKER_ID=${worker.workerId}">削除</a>
                    </div>
                </tr>
            </c:forEach>
        </table>
    </c:if>

    <a href="Main.action">メインへ戻る</a>
<c:import url="../../common/footer.jsp"/>
</body>
</html>