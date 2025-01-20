<%-- シフト削除JSP --%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="ja">

<c:import url="../../common/header.jsp"/>

<head>
    <title>シフト削除</title>
    <style>
        table { width: 90%; border-collapse: collapse; margin: 20px auto; }
        th, td { border: 1px solid #ddd; padding: 8px; text-align: center; }
        th { background-color: #f4f4f4; }
    </style>
</head>

<body>
    <h1>シフト削除</h1>

    <!-- 前月・次月へのリンク -->
    <form action="ShiftDeleteAction?count=${count}" method="get">
	    <input type="hidden" name="year" value="${year}">
	    <input type="hidden" name="month" value="${prevMonth}">
	    <button type="submit">&lt;&lt; 前月</button>
	</form>
	<span>${year}年 ${month}月</span>
	<form action="ShiftDeleteAction?count=${count}" method="get">
	    <input type="hidden" name="year" value="${year}">
	    <input type="hidden" name="month" value="${nextMonth}">
	    <button type="submit">&gt;&gt; 次月</button>
	</form>

    <!-- シフトテーブルの表示 -->
    <table>
        <thead>
            <tr>
                <th>名前</th>
                <c:forEach var="date" items="${dates}">
                    <th>${date.dayOfMonth}</th>
                </c:forEach>
            </tr>
        </thead>
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
                                        ${shift.workTimeId}
                                    </c:if>
                                    <c:if test="${shift.workTimeId == null}">
                                        <fmt:formatDate value="${shift.shiftTimeStart}" pattern="HH:mm" /> -
                                        <fmt:formatDate value="${shift.shiftTimeEnd}" pattern="HH:mm" />
                                    </c:if>
                                </c:when>
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

    <!-- シフト削除フォーム -->
    <form action="ShiftDeleteCheckAction" method="post">
        <input type="hidden" name="year" value="${year}">
        <input type="hidden" name="month" value="${month}">
        <button type="submit">シフト削除</button>
    </form>

    <a href="Main.action">戻る</a>
</body>

<c:import url="../../common/footer.jsp"/>
</html>