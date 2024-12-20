<%-- 従業員シフト登録前JSP --%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="ja">

<c:import url="../../common/header_work.jsp"/>

<head>
    <meta charset="UTF-8">
    <title>カレンダー</title>
    <style>
        table { border-collapse: collapse; }
        th, td { border: 1px solid black; width: 50px; height: 50px; text-align: center; }
    </style>
</head>
<body>
    <h1>カレンダー</h1>
    <table>
        <tr>
            <th>日</th>
            <th>月</th>
            <th>火</th>
            <th>水</th>
            <th>木</th>
            <th>金</th>
            <th>土</th>
        </tr>

        <c:set var="counter" value="0" />
        <c:forEach var="date" items="${dates}">
            <c:if test="${counter % 7 == 0}">
                <tr>
            </c:if>
            <td>
                <c:choose>
                    <c:when test="${date != null}">
                    	<!-- ShiftDayとして選択した日を送る -->
                        <a href="ShiftWorkerSignupSet.action?shiftDay=${date.dayOfMonth}&count=${count}">${date.dayOfMonth}</a>
                    </c:when>
                    <c:otherwise>
                        <!-- 空セル -->
                    </c:otherwise>
                </c:choose>
            </td>
            <c:set var="counter" value="${counter + 1}" />
            <c:if test="${counter % 7 == 0}">
                </tr>
            </c:if>
        </c:forEach>

        <!-- 最終行が7列に満たない場合に空のセルで埋める -->
        <c:if test="${counter % 7 != 0}">
            <c:forEach begin="0" end="${6 - (counter % 7)}">
                <td></td>
            </c:forEach>
            </tr>
        </c:if>
    </table>
</body>
</html>