<%-- シフト閲覧JSP --%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="ja">

<c:import url="../../common/header_work.jsp"/>

<head>
    <title>シフト閲覧（バイト）</title>
    <style>
        table { width: 90%; border-collapse: collapse; margin: 20px auto; }
        th, td { border: 1px solid #ddd; padding: 8px; text-align: center; }
        th { background-color: #f4f4f4; }
    </style>
</head>

<body>
    <h1>シフト閲覧（バイト）</h1>
    <table>
        <!-- 曜日ヘッダー -->
        <thead>
        <tr>
            <th>名前</th>
            <c:forEach var="date" items="${dates}">
                <th>
                	${date.dayOfMonth}
                </th>
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
		                <c:choose>
		                    <%-- シフト情報が存在する場合 --%>
		                    <c:when test="${shiftMap[workerlist.workerId][date] != null}">
		                        <c:set var="shift" value="${shiftMap[workerlist.workerId][date]}" />
		                        <c:if test="${shift.workTimeId != null}">
		                                ${shift.workTimeId}
		                        </c:if>
		                        <c:if test="${shift.workTimeId == null}">
		                                <fmt:formatDate value="${shift.shiftTimeStart}" pattern="HH:mm" /> -
		                                <fmt:formatDate value="${shift.shiftTimeEnd}" pattern="HH:mm" />
								</c:if>
		                    </c:when>
		                    <%-- シフト情報がない場合 --%>
		                    <c:otherwise>
									-
		                    </c:otherwise>
		                </c:choose>
		            </td>
		        </c:forEach>
		    </tr>
		</c:forEach>
        </tbody>
    </table>

    <!-- StoreDBから情報取得。表示するだけ -->
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

	<a href="ShiftChooseWork.action">戻る</a>
</body>

<c:import url="../../common/footer.jsp"/>
</html>