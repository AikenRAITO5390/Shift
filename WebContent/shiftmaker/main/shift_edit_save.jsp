<%-- シフト編集JSP --%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="ja">

<style>
.h1 {
	margin-top : 60px;
	text-align : center;
}
.submit {
	margin-top : 35px;
	margin-left : 90%;
}

.submit button{
	width:80px;
}
.main input[type="submit"]{
	margin-top : -40px;
	margin-left : 80%;

	background-color: #6495ED; /* 背景色を水色に設定 */
	color: white;
	border-radius: 4px; /* 角を丸くする */
	border: none; /* ボーダーをなしに設定 */
	width: 6%;
	height: 40px;
}
table {
	margin-top : 50px;
	width: 90%;
	border-collapse: collapse;
	margin: 20px auto;
	table-layout: fixed;
}
th, td {
	border: 1px solid #ddd;
	padding: 8px;
	text-align: center;
	width: 40px !important;
	height: 40px !important;
}
th {
	background-color: #f4f4f4;
}
a {
    text-decoration: none;
}
.table_table {
    overflow-x: scroll; /* 縦方向のスクロールバーを表示 */
}
/*シフト時間変更のラベル*/
.table th {
	background-color: #6495ED; /* 背景色を水色に設定 */
	color: white;
}
/*ラベル(なまえ)*/
.date {
	background-color: #6495ED; /* 背景色を水色に設定 */
	color: white;
}
/*ラベル(日付)*/
.date1 {
	background-color: #6495ED; /* 背景色を水色に設定 */
	color: white;
	/*width: 100%;*/
}
.sita {
	margin-top : 60px;
}
</style>

<c:import url="../../common/header.jsp"/>

<head>
    <title>シフト編集</title>

</head>

<body>

	<div class="h1">
    	<h1>～シフトカレンダー～</h1>
    </div>

	<div class="table_table">
    <table>
        <!-- 曜日ヘッダー -->
        <thead>
        	<tr>
        		<th class="date">名前</th>
            	<c:forEach var="date" items="${dates}">
                	<th class="date1">${date.dayOfMonth}</th>
            	</c:forEach>
        	</tr>
        </thead>

        <!-- シフト情報 -->
        <tbody>
        <c:forEach var="workerlist" items="${worker_list}">
		    <tr>
		        <td>${workerlist.workerName}</td>
		        <c:forEach var="date" items="${dates}">
		            <td>
		            <div class="a">
					    <c:choose>
					        <c:when test="${shiftMap[workerlist.workerId][date] != null}">
					            <c:set var="shift" value="${shiftMap[workerlist.workerId][date]}" />
					            <c:if test="${shift.workTimeId != null}">
					                <c:choose>
	                                    <%-- worker_judgeがTrueの時はTを〇に変換して表示 --%>
	                                    <c:when test="${shift.workTimeId  == 'T'}">
	                                        <a href="ShiftEditSet.action?workerId=${workerlist.workerId}&date=${date}&workTimeId=${shift.workTimeId}&count=${count}">
	                                            〇
	                                        </a>
	                                    </c:when>
	                                    <c:otherwise>
	                                        <a href="ShiftEditSet.action?workerId=${workerlist.workerId}&date=${date}&workTimeId=${shift.workTimeId}&count=${count}">
	                                            ${shift.workTimeId}
	                                        </a>
	                                    </c:otherwise>
	                                </c:choose>
					            </c:if>
					            <c:if test="${shift.workTimeId == null}">
					                <a href="ShiftEditSet.action?workerId=${workerlist.workerId}&date=${date}&shiftTimeStart=${shift.shiftTimeStart}&shiftTimeEnd=${shift.shiftTimeEnd}&count=${count}">
					                    <fmt:formatDate value="${shift.shiftTimeStart}" pattern="HH:mm" /> -
					                    <fmt:formatDate value="${shift.shiftTimeEnd}" pattern="HH:mm" />
					                </a>
					            </c:if>
					        </c:when>
					        <c:otherwise>
					        <div class="a">
					            <a href="ShiftEditSet.action?workerId=${workerlist.workerId}&date=${date}&count=${count}">-</a>
					        </div>
					        </c:otherwise>
					    </c:choose>
					    </div>
					</td>
		        </c:forEach>
		    </tr>
		</c:forEach>
        </tbody>
    </table>
    </div>

	<div class="table">
    <!-- worker_judgeがFalseの場合のみ店舗のシフト時間参考セクションを表示 -->
    <c:if test="${not isWorkerJudgeTrue}">
        <h3>＜店舗のシフト時間参考＞</h3>
        <table>
            <thead>
                <tr>
                    <th>勤務時間ID</th>
                    <th>開始時間</th>
                    <th>終了時間</th>
                </tr>
            </thead>
            <tbody>
                <c:forEach var="workTime" items="${workTimeDetails}">
                    <tr>
                        <c:if test="${workTime.workTimeId != 'F'}">
                            <td>${workTime.workTimeId}</td>
                            <td>${workTime.workTimeStart}</td>
                            <td>${workTime.workTimeEnd}</td>
                        </c:if>
                    </tr>
                </c:forEach>
            </tbody>
        </table>
    </c:if>
    </div>

	<div class="submit">
    <form action="ShiftEditResult.action" method="get">
	    <button type="submit">確定</button>
	</form>
	</div>

	<div class="main">
	<form action="Main.action" method="post">
		<input type="submit" value="メインへ">
	</form>
	</div>

	<div class="sita"></div>

</body>

<c:import url="../../common/footer.jsp"/>
</html>