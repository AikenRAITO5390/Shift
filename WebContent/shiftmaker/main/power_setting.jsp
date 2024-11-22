<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<link rel="stylesheet" href="../../css/style.css">
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
</head>
<c:import url="../../common/header.jsp"/>
<body>

<h2>シフト時間設定</h2>
<form action="PowerSettingResult.action" method="post">
    <table class="table table-hover">
        <c:forEach var="i" begin="0" end="6">
            <tr>
                <td><label>${fn:substring('月火水木金土日', i, i+1)}</label></td>
                <td>
                    <input type="text" name="WeekScore_${i}" maxlength="7" id="WeekScore_${i}">
                </td>
                <input type="hidden" name="WorkWeekScore_${i}" value="${fn:substring('1234567', i, i+1)}">
            </tr>
        </c:forEach>
    </table>

<script>
    // 初期値を設定
    <c:forEach var="i" begin="0" end="6">
        document.getElementById("WeekScore_${i}").value = "0";
    </c:forEach>

    // power_listの値を設定
    <c:forEach var="i" begin="0" end="6">
        <c:forEach var="power" items="${power_list}">
            <c:if test="${power[0] == (i+1).toString()}">
                document.getElementById("WeekScore_${i}").value = "${power[1]}";
            </c:if>
        </c:forEach>
    </c:forEach>
</script>
    <button type="submit">変更</button>
</form>

</body>
</html>
