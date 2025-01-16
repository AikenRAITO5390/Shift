<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<style>
.h2 h2{
	margin-top : 60px;
	text-align : center;
}
.label {
	margin-left : 10px;
	margin-top : 50px;
	width: 50%;

}
.labela {
	margin-top : -365px;
	margin-left : 600px;
	width: 50%;
}
.label label{
	margin-left : 50px;

    padding: 2px;
    text-align: center;
    background-color: #6495ED;
    color: white;
    width: 100px;

}
.label p{
	margin-left : 170px;
	margin-top: -20px;

	border: 1px solid #000;
    padding: 2px;
    text-align: center;
}
.labela label{
	margin-left : 80px;

    padding: 4px;
    text-align: center;
    background-color: #6495ED;
    color: white;
}
.labela p{
	margin-left : 270px;
	margin-top: -20px;

	border: 1px solid #000;
    padding: 2px;
    text-align: center;
}
.label a{
	margin-top : 60px;
	margin-left : 55px;
}
</style>

<head>
    <title>従業員情報詳細</title>
</head>
<c:import url="../../common/header.jsp"/>
<body>

<div class="h2">
    <h2>詳細</h2>
</div>

<div class="label">
    <label>ID  </label>  <p>${worker.workerId} </p>
    <label>名前  </label> <p>${worker.workerName}</p>
    <label>生年月日  </label> <p>${worker.workerDate}</p>
    <label>住所  </label> <p>${worker.workerAddress}</p>
    <label>電話番号  </label> <p>${worker.workerTel}</p>
    <label>パスワード  </label> <p>${worker.workerPassword}</p>
    <label>店情報  </label> <p>${worker.store.storeName}</p>


    <a href="WorkerDate.action">戻る</a>
</div>

<div class="labela">
	<label>実力 </label> <p>${worker.workerScore}</p>
    <label>得意なポジション </label> <p>${worker.workerPosition}</p>
</div>

<c:import url="../../common/footer.jsp"/>
</body>
</html>