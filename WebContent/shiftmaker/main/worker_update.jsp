<%-- 従業員更新JSP --%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>

<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>まるごとシフトくん</title>
</head>
<c:import url="../../common/header.jsp"/>
<body>

<div class="h2">
	<h2>従業員情報変更</h2>
</div>

	<form action = "WorkerUpdateExecute.action" method="post">

		<label>ID</label>
		<input type ="text"  name="WORKER_ID"  value="${worker.workerId}"required>


		<label>名前</label>
		<input type ="text"  name="WORKER_NAME"  value="${worker.workerName}" required>



		<label>生年月日</label>
		<input type="text" name="WORKER_DATE" maxlength="10" value="${worker.workerDate}" required />



		<label>住所</label>
		<input type ="text"  name="WORKER_ADDRESS"  value="${worker.workerAddress}"required>


		<label>電話番号</label>
		<input type ="text"  name="WORKER_TEL"  value="${worker.workerTel}"required>


		<label>パスワード</label>
		<input type ="text"  name="WORKER_PASSWORD"  value="${worker.workerPassword}"required>


		<label>店情報</label>
		<input type ="text"  name="STORE_NAME"  value="${stores.storeName}"required>



		<input type="submit" value="変更">

	</form>

	<a href="WorkerList.action">戻る</a>

<c:import url="../../common/footer.jsp"/>
</body>
</html>