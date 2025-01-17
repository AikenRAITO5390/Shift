<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<style>
.h2 h2{
	margin-top : 60px;
	text-align : center;
}
.h3 h3{
	margin-top : 50px;
	text-align : center;
}

.delete_button button {
	margin-left: 700px;
	margin-top: 30px;

}
.button {
	margin-left: 520px;
    text-decoration: none;
    margin-top: -40px;

</style>
<head>
    <title>～従業員情報削除～</title>
</head>

<c:import url="../../common/header.jsp"/>
<body>

<div class="h2">
	<h2>従業員情報削除</h2>
</div>

<div class="h3">
    <h3>${worker.workerName}さんの情報を削除しますか？</h3>
</div>


    <form action="WorkerDeleteExecute.action" method="post">
        <input type="hidden" name="WORKER_ID" value="${worker.workerId}">
        <input type="hidden" name="confirm" value="yes">

        <div class="delete_button">
        	<button type="submit">削除</button>
        </div>
    </form>
    <form action="WorkerList.action" method="get">

    <div class="button">
        <button type="submit">戻る</button>
    </div>

    </form>
</body>
</html>