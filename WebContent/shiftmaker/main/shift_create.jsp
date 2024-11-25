<%--シフト表示JSP --%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>

<style>
         table {
            width: 50%;
            border-collapse: collapse;
            margin: 20px auto;
        }
        th, td {
            border: 1px solid #ddd;
            padding: 8px;
            text-align: center;
        }
        th {
            background-color: #f4f4f4;
        }
</style>
</head>
<body>
	<h1>シフト情報</h1>
	<table>
        <tr>
            <th>Name</th>
            <th>Role</th>
            <th>Shifts</th>
        </tr>
        <c:forEach var="worker" items="${workerShifts}">
            <tr>
                <td>${worker.name}</td>
                <td>${worker.role}</td>
                <td>
                    <c:forEach var="shift" items="${worker.mergedShifts}">
                        ${shift}<br>
                    </c:forEach>
                </td>
            </tr>
        </c:forEach>
    </table>





    <%-- 一週間分回ると<tr>で次の行へ行く
    <c:if test="${counter % 7 == 0}">
         <tr>
    </c:if> --%>

	<h1>カレンダー</h1>
    <table>
    	<thead>
    	<%--曜日のヘッダー行 --%>
        <tr>
         	<th>名前</th>
         <c:forEach var="date" items="${dates}">
            <th>
                <c:choose>
                	<%-- データが入っていた場合日にちを出力 --%>
                    <c:when test="${date != null}">
                        ${date.dayOfMonth}
                    </c:when>
                    <c:otherwise>
                        <!-- データが何も入っていない場合スルー -->
                    </c:otherwise>
                </c:choose>
            </th>
        </c:forEach>

        </tr>
        </thead>
        <tbody>

        <c:forEach var="worker" items="${workerShifts}">
            <tr>
               	<td>${worker.name},${worker.date}</td>
               	<td>
                    <c:forEach var="shift" items="${worker.mergedShifts}">
                        ${shift}<br>
                    </c:forEach>
                </td>

            </tr>
        </c:forEach>

        </tbody>
    </table>
</body>
</html>