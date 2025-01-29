<%-- 従業員更新JSP --%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>

<title>従業員情報変更</title>

<style>
.h2 h2{
	margin-top : 60px;
	text-align : center;
}

.worker_update label{
    color: white;
    border: 1px solid white; /* 白い枠線を設定 */
    text-align: center !important; /* 水平方向に中央揃え */
    background-color: #6495ED; /* 背景色を設定 */
    padding: 5px; /* 枠線と内容の間にスペースを追加 */
     width:  100px;
    margin-top: 10px;
    margin-left: 15%;
    display: inline-block;

}

.worker_update input[type="text"]{
    border: 1px solid brack; /* 白い枠線を設定 */
    padding: 5px; /* 枠線と内容の間にスペースを追加 */
     width:  50%;
    margin-top: 10px;
    margin-left: 15px;
    display: inline-block;

}


.submit input[type="submit"]{
    background-color: #6495ED;
    color: white;
    border: 1px solid white;
    padding: 10px 20px; /* ボタンの内側の余白を調整 */
     width:  100px;
    display: inline-block;
    float: right;

}

.submit a{
     width:  100px;
    display: inline-block;
    float: right;

}
.nenn{
	margin-left: 1.2%;
}
.btn {
    margin-left: 20%;
}

.link1 {
	margin-top: 0.6%;
	margin-left: 1%;
}


</style>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
</head>
<body>
<c:import url="../../common/header.jsp"/>

<div class="h2">
	<h2>～従業員情報変更～</h2>
</div>

	<form action = "WorkerUpdateExecute.action" method="post">
<div class = "worker_update">
		<label>ID</label>
		<input type ="text"  name="WORKER_ID"  value="${worker.workerId}"required>

<br>

		<label>名前</label>
		<input type ="text"  name="WORKER_NAME"  value="${worker.workerName}" required>
<br>


		<label>生年月日</label>
	    <!-- 年、月、日を選択するフォーム -->
		    <select name="year" class="nenn" required>
				<option value="">年</option>
					<c:forEach var="year" items="${year_list}">
						<option value="${year}" ${year == selectedYear ? 'selected' : ''}>${year}</option>
					</c:forEach>
			</select>
			<select name="month" required>
				<option value="">月</option>
					<c:forEach var="month" items="${month_list}">
						<option value="${month}" ${month == selectedMonth ? 'selected' : ''}>${month}</option>
					</c:forEach>
			</select>
			<select name="day" required>
				<option value="">日</option>
					<c:forEach var="day" items="${day_list}">
						 <option value="${day}" ${day == selectedDay ? 'selected' : ''}>${day}</option>
					</c:forEach>
			</select>

<br>

		<label>住所</label>
		<input type ="text"  name="WORKER_ADDRESS"  value="${worker.workerAddress}"required>
<br>

		<label>電話番号</label>
		<input type ="text"  name="WORKER_TEL"  value="${worker.workerTel}"required>
<br>

		<label>パスワード</label>
		<input type ="text"  name="WORKER_PASSWORD"  value="${worker.workerPassword}"required>
<br>

		<label>店情報</label>
		<input type ="text"  name="STORE_NAME"  value="${stores.storeName}"required>


</div>
<div class ="submit">
		<a class="link1" href="WorkerList.action">戻る</a>
		<input type="submit" class="btn" value="変更">
</div>
	</form>


<c:import url="../../common/footer.jsp"/>
</body>
</html>