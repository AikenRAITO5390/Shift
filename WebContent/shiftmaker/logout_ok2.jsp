<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>

<style>
.logout{
    margin-top : 80px;
	text-align : center;
}

</style>


<head>
    <title>ログアウト</title>
</head>

<c:import url="../../common/header.jsp"/>

<body>

<div class="logout">
	<h2>ログアウト</h2>
</div>
    <h3>ログアウトしますか？</h3>
    <form action="WorkerLogoutExecute.action" method="post">
        <button type="submit">はい</button>
    </form>
    <form action="/shift/shiftmaker/main/MainWork.action" method="post">
        <button type="submit">いいえ</button>
    </form>

    <c:import url="../../common/footer.jsp"/>
</body>
</html>