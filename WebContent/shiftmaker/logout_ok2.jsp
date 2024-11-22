<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <title>ログアウト</title>
</head>
<body>
	<h2>ログアウト</h2>
    <h3>ログアウトしますか？</h3>
    <form action="WorkerLogoutExecute.action" method="post">
        <button type="submit">はい</button>
    </form>
    <form action="/shift/shiftmaker/main/MainWork.action" method="post">
        <button type="submit">いいえ</button>
    </form>
</body>
</html>