<%-- シフト編集JSP --%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="ja">

<c:import url="../../common/header_work.jsp"/>

<head>
    <title>シフト編集</title>
    <style>
        table { width: 90%; border-collapse: collapse; margin: 20px auto; }
        th, td { border: 1px solid #ddd; padding: 8px; text-align: center; }
        th { background-color: #f4f4f4; }
    </style>
</head>

<body>
    <h1>シフト編集</h1>
    <table>
        <!-- 曜日ヘッダー -->
        <thead>
        <tr>
            <th>名前</th>
            <c:forEach var="date" items="${dates}">
                <th>${date.dayOfMonth}</th>
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
					        <c:when test="${shiftMap[workerlist.workerId][date] != null}">
					            <c:set var="shift" value="${shiftMap[workerlist.workerId][date]}" />
					            <c:if test="${shift.workTimeId != null}">
					                <a href="ShiftEditSet.action?workerId=${workerlist.workerId}&date=${date}&workTimeId=${shift.workTimeId}&count=${count}">
					                    ${shift.workTimeId}
					                </a>
					            </c:if>
					            <c:if test="${shift.workTimeId == null}">
					                <a href="ShiftEditSet.action?workerId=${workerlist.workerId}&date=${date}&shiftTimeStart=${shift.shiftTimeStart}&shiftTimeEnd=${shift.shiftTimeEnd}&count=${count}">
					                    <fmt:formatDate value="${shift.shiftTimeStart}" pattern="HH:mm" /> -
					                    <fmt:formatDate value="${shift.shiftTimeEnd}" pattern="HH:mm" />
					                </a>
					            </c:if>
					        </c:when>
					        <c:otherwise>
					            <a href="ShiftEditSet.action?workerId=${workerlist.workerId}&date=${date}&count=${count}">-</a>
					        </c:otherwise>
					    </c:choose>
					</td>
		        </c:forEach>
		    </tr>
		</c:forEach>
        </tbody>
    </table>
</body>

<c:import url="../../common/footer.jsp"/>
</html>