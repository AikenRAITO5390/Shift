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
	margin-left: 170px;
	margin-top: 30px;
	background-color: #ff6347;
    color: white;
     width: 120px;

}

.delete_button button:hover {
    background-color: #ff9980; /* ホバー時の背景色を同じに設定 */
}

.button button{
	background-color: #2C7CFF;
	color: white;
	margin-left: -200px;
    text-decoration: none;
    margin-top: -40px;
    width: 120px;
    }

.button, .delete_button {
    display: flex;
    justify-content: center; /* 水平方向に中央揃え */
    align-items: center; /* 垂直方向に中央揃え（必要に応じて） */
}


</style>
<head>
    <title>従業員情報削除</title>
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

<c:import url="../../common/footer.jsp"/>

</body>
</html>