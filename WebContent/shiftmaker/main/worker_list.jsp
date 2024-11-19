<%-- 学生一覧JSP --%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!-- 下記の一文の宣言がないとJSTLを使用できない -->
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>まるごとシフトくん</title>
</head>
<body>

	<h2>従業員一覧</h2>


	<c:choose>
		<c:when test="${workers.size() > 0}">
                  <table class="table table-hover">
                <tr>
                    <th>ID</th>
                    <th>名前</th>
                    <th>生年月日</th>
                    <th>住所</th>
                    <th>電話番号</th>
                    <th>パスワード</th>
                    <th>店情報</th>
                    <th></th>
                    <th></th>
                </tr>
                <c:forEach var="worker" items="${workers}">

                    <tr>
                        <td>${worker.workerId}</td>
                        <td>${worker.workerName}</td>
                        <td>${worker.workerDate}</td>
                        <td>${worker.workerAddress}</td>
                        <td>${worker.workerTel}</td>
                        <td>${worker.workerPassword}</td>
                        <td>${worker.storeId}</td>
                        <td><a href="WorkerUpdate.action?no=${worker.workerId}">変更</a></td>
                        <td><a href="WorkerDelete.action?no=${worker.workerId}">削除</a></td>
                    </tr>
                </c:forEach>
            </table>
        </c:when>
        <c:otherwise>
            <p>従業員が見つかりませんでした。</p>
        </c:otherwise>
    </c:choose>

</body>
</html>