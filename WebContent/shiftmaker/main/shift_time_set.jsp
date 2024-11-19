<%-- 学生一覧JSP --%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<link rel="stylesheet" href="../../css/style.css">
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
</head>
<c:import url="../../common/header.jsp"/>
<body>

    <h2>シフト時間設定</h2>


    <c:choose>
        <c:when test="${time_list.size()>0}">

            <table class="table table-hover">
<form action = "ShiftTimeSetting.action" method="post">
                <c:forEach var="time" items="${time_list}">
                    <tr>
                        <td><label>${time.workTimeId}</label></td>
                        <td>
                            <select id="timeSelectStart_${time.workTimeId}" name="workTimeStart">
                                <option value="08:00:00">08:00</option>
                                <option value="09:00:00">09:00</option>
                                <option value="10:00:00">10:00</option>
                                <option value="11:00:00">11:00</option>
                                <option value="12:00:00">12:00</option>
                                <option value="13:00:00">13:00</option>
                                <option value="14:00:00">14:00</option>
                                <option value="15:00:00">15:00</option>
                                <option value="16:00:00">16:00</option>
                                <option value="17:00:00">17:00</option>
                                <option value="18:00:00">18:00</option>
                                <option value="19:00:00">19:00</option>
                                <option value="20:00:00">20:00</option>
                                <option value="21:00:00">21:00</option>
                                <option value="22:00:00">22:00</option>
                                <option value="23:00:00">23:00</option>
                                <option value="24:00:00">24:00</option>
                            </select>
                        </td>
                        <td>
                            <select id="timeSelectEnd_${time.workTimeId}" name="workTimeEnd">
                                <option value="08:00:00">08:00</option>
                                <option value="09:00:00">09:00</option>
                                <option value="10:00:00">10:00</option>
                                <option value="11:00:00">11:00</option>
                                <option value="12:00:00">12:00</option>
                                <option value="13:00:00">13:00</option>
                                <option value="14:00:00">14:00</option>
                                <option value="15:00:00">15:00</option>
                                <option value="16:00:00">16:00</option>
                                <option value="17:00:00">17:00</option>
                                <option value="18:00:00">18:00</option>
                                <option value="19:00:00">19:00</option>
                                <option value="20:00:00">20:00</option>
                                <option value="21:00:00">21:00</option>
                                <option value="22:00:00">22:00</option>
                                <option value="23:00:00">23:00</option>
                                <option value="24:00:00">24:00</option>
                            </select>
                        </td>
                        <input type="hidden" name="workTimeId" value="${time.workTimeId}">
                    </tr>
                    <script>
                        // workTimeStartの値を設定
                        var workTimeStart = "${time.workTimeStart}";
                        var selectElementStart = document.getElementById("timeSelectStart_${time.workTimeId}");
                        selectElementStart.value = workTimeStart;

                        // workTimeEndの値を設定
                        var workTimeEnd = "${time.workTimeEnd}";
                        var selectElementEnd = document.getElementById("timeSelectEnd_${time.workTimeId}");
                        selectElementEnd.value = workTimeEnd;
                    </script>
                </c:forEach>
            </table>
             <button type="submit">変更</button>
        </c:when>
    </c:choose>

</body>
</html>
