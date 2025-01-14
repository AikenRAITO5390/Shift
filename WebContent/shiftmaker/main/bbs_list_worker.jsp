<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>


<head>
    <meta charset="UTF-8">
    <title>掲示板</title>
</head>



<body>


    	<h1>掲示板</h1>



    <ul>
        <c:forEach var="message" items="${messages}">

                <!-- <strong>投稿者:</strong> -->

                <c:choose>
                    <c:when test="${not empty message.worker.workerName}">
                        ${message.worker.workerName}
                    </c:when>
                    <c:when test="${not empty message.store.managerName}">
                        ${message.store.managerName}
                    </c:when>
                </c:choose>
                			<c:choose>
				<c:when test="${message.worker.workerId == WorkerLogin_Name}">
   					<a href="BbsDeleteWorker.action?Worker_ID=${WorkerLogin_Name}&BBS_ID=${message.bbsId}">削除</a>
				</c:when>
			</c:choose>



                <br>

                <div class="text">${message.bbsText} <br></div>
                <div class="date"> ${message.bbsDate}</div>

        </c:forEach>
    </ul>



    <a href="BbsCreateWorker.action">投稿を作成する</a>



    <a href="MainWork.action">戻る</a>




</body>
</html>