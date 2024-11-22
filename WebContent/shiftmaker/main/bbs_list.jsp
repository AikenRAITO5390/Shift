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
                <strong>投稿者:</strong> ${message.worker.workerName} <br>
                <strong>メッセージ:</strong> ${message.bbsText} <br>
                <strong>投稿日:</strong> ${message.bbsDate}
            </li>
        </c:forEach>
    </ul>

    <li><a href="BbsCreate.action">投稿を作成する</a></li>
</body>
</html>