<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>

<head>
    <title>掲示板投稿削除</title>
</head>
<body>
<c:import url="../../common/header_work.jsp"/>

    <h3>削除されました</h3>
    <form action="BBSWorker.action" method="get">
        <button type="submit">戻る</button>
    </form>
</body>
</html>