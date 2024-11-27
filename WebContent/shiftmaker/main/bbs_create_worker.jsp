<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>まるごとシフトくん</title>
</head>
<body>
<h2>Worker</h2>
    <form action="BbsCreateExecute.action" method="post">
        <label for="worker_id">投稿者ID:</label>
        <input type="text" name="WORKER_ID" value="${UserName}"  readonly required><br><br>

        <label for="bbs_id">BBS ID:</label>
        <input type="text" name="BBS_ID" value="${BbsId}"  readonly required><br><br>

        <label for="store_id">店舗ID:</label>
        <input type="text" name="STORE_ID" value="${StoreId}" readonly required><br><br>

        <label for="bbs_date">日付:</label>
        <input type="text" id="bbs_date" name="BBS_DATE" value="${today}" readonly><br><br>

        <label for="bbs_text">内容:</label>
        <textarea id="bbs_text" name="BBS_TEXT" rows="4" cols="50" required></textarea><br><br>

        <input type="submit" value="投稿">
        <a href="BBSWorker.action">戻る</a>
    </form>
</body>
</html>