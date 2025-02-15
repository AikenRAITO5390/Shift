<%-- 従業員シフト条件一覧JSP --%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!-- 下記の一文の宣言がないとJSTLを使用できない -->
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<!-- bootstrapと繋げる -->
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-9ndCyUaIbzAi2FUVXJi0CjmCapSmO7SnpJef0486qhLnuZ2cdeRhO02iuK6FUUVM" crossorigin="anonymous">
<!-- css -->
<link rel="stylesheet" href="../../css/style_k.css">

<html>

<head>
    <title>シフト条件変更（一覧）</title>
</head>

<style>
.table-container th{
	background-color: #6495ED; /* 背景色を水色に設定 */
    color: white;
}
.main{
	display: flex;
   	justify-content: center; /* 水平方向に中央揃え */
    align-items: center;
}
.main a{
	margin-top: 20px;
	margin-left: 900px;
}
コンテキスト メニューあり
.sita {
	margin-top : 60px;
}
</style>


<c:import url="../../common/header.jsp"/>
<body>

	<!-- タイトル -->
	<div class="h2">
		<h2>～シフト条件変更～</h2>
	</div>

	<!-- 余白 -->
	<div class="mt-5"></div>

	<div class="table-container">
	    <c:if test="${not empty workers}">
	        <table  class="table">
	            <tr>
	                <th>ID</th>
	                <th>名前</th>
	                <th>ポジション</th>
	                <th>点数</th>
	                <th> </th>
	            </tr>

	            <c:forEach var="worker" items="${workers}">
	            	<c:if test="${!worker.workerJudge}">
		                <tr>
		                    <td>${worker.workerId}</td>
		                    <td>${worker.workerName}</td>
		                    <td>${positionMapping[worker.workerPosition]}</td> <!-- ポジションの日本語化 -->
		                    <td>${worker.workerScore}</td>
		                    <td><a href="ShiftConditionEdit.action?workerId=${worker.workerId}">変更</a></td>
		                </tr>
	                </c:if>
	            </c:forEach>

	        </table>
	    </c:if>
    </div>

	<div class="main">
    	<a href="Main.action">メインへ戻る</a>
    </div>

    <!-- 余白 -->
    <div class="sita">　</div>

</body>
<c:import url="../../common/footer.jsp"/>

</html>