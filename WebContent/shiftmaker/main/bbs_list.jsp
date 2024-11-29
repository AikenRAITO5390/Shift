<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>掲示板</title>
</head>
<body>
    <h1>掲示板</h1>
    <ul>
        <c:forEach var="message" items="${messages}">
            <li>
                <strong>投稿者:</strong>

                <c:choose>
                    <c:when test="${not empty message.worker.workerName}">
                        ${message.worker.workerName}
                    </c:when>
                    <c:when test="${not empty message.store.managerName}">
                        ${message.store.managerName}
                    </c:when>
                </c:choose>

                <br>
                <strong>メッセージ:</strong> ${message.bbsText} <br>
                <strong>投稿日:</strong> ${message.bbsDate}<br>
                <td><a href="BbsDelete.action?MANAGER_ID=${store.managerId}&BBS_ID=${message.bbsId}">削除</a></td>
            </li>
        </c:forEach>
    </ul>

    <a href="BbsCreate.action">投稿を作成する</a>
    <a href="Main.action">戻る</a>
</body>
</html>