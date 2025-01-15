<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>削除確認</title>
</head>
<body>
    <h1>削除確認</h1>
    <p>投稿者: ${user}</p>
    <p>コメント: ${text}</p>
    <form action="bbs_delete_confirm.jsp" method="post">
        <input type="hidden" name="BBS_ID" value="${BBS_ID}">
        <input type="submit" value="削除">
    </form>
</body>
</html>