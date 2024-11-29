<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<style>
.h2 h2{
	margin-top : 60px;
	text-align : center;

}

.label {
	margin-left : 100px;
	margin-top : 50px;
	width: 100%;
}

.labela {
	margin-left : 590px;
	margin-top : -250px;

}

.label label{
	margin-left : 100px;


}

.label p{
	margin-left : 270px;
	margin-top: -20px;

}

.labela label{
	margin-left : 80px;


}

.labela p{
	margin-left : 270px;
	margin-top: -20px;

}

.label a{
	margin-left : 850px;


}






</style>




<head>
    <title>詳細</title>
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