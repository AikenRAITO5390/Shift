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
<div class = "power_Setting">

<h1>シフト時間設定</h1>
<p>"${toDay}分"</p>
<div class = "Point_Setting">
<div class = "week_Setting">
<form action="PowerSettingResult.action" method="post" onsubmit="return validateForm()">
    <table class="table table-hover">
        <c:forEach var="i" begin="0" end="6">
            <tr>
                <td><label>${fn:substring('月火水木金土日', i, i+1)}</label></td>
                <td>
                  <input type="number" min="1" max="${pointMax}" name="WeekScore_${i}" maxlength="7" id="WeekScore_${i}" style="ime-mode: disabled" required>

                </td>
                <input type="hidden" name="WorkWeekScore_${i}" value="${fn:substring('1234567', i, i+1)}">
            </tr>
        </c:forEach>
    </table>

    <script>
        // 初期値を設定
        <c:forEach var="i" begin="0" end="6">
            document.getElementById("WeekScore_${i}").value = "";
        </c:forEach>

        // power_listの値を設定
        <c:forEach var="i" begin="0" end="6">
            <c:forEach var="power" items="${power_list}">
                <c:if test="${power[0] == (i+1).toString()}">
                    document.getElementById("WeekScore_${i}").value = "${power[1]}";
                </c:if>
            </c:forEach>
        </c:forEach>

        function validateForm() {
            var isValid = true;
            for (var i = 0; i <= 6; i++) {
                var input = document.getElementById("WeekScore_" + i);
                var value = input.value;
                if (value === "") {
                    input.setCustomValidity("入力されてない日があります");
                    isValid = false;
                } else if (value < 1) {
                    input.setCustomValidity("下限を超えている日があります");
                    isValid = false;
                } else if (value > 30) {
                    input.setCustomValidity("上限を超えている日があります");
                    isValid = false;
                } else {
                    var input = document.getElementById("WeekScore_" + i);
                    input.reportValidity(); // ここでエラーメッセージを表示
                }
            }
            return isValid;
        }
    </script>
    <button type="submit">変更</button>
</form>
</div>
<dic class = point_conetnt>
<form action="DayPowerSettingResult.action" method="post" onsubmit="return validateInput()">
	    <table class="table table-hover">
	        <c:forEach var="dateMap" items="${dateList}" varStatus="loopStatus">
	            <c:forEach var="entry" items="${dateMap.entrySet()}">
	                <tr>
	                    <td><label>${loopStatus.index + 1}</label></td>
	                    <td>
	                        <input type="number" min="1" max="${pointMax}" name="DayScore_${loopStatus.index + 1}" maxlength="7" id="DayScore_${loopStatus.index + 1}" required>
	                    </td>
	                    	<input type="hidden" name="WorkDayScore_${loopStatus.index + 1}" value="${entry.key}">
	                </tr>
	            </c:forEach>
	        </c:forEach>
	    </table>
	<script>

	<c:forEach var="dateMap" items="${dateList}" varStatus="loopStatus">
		<c:forEach var="entry" items="${dateMap.entrySet()}">
			<c:forEach var="power" items="${power_list}">
	    		<c:if test="${power[0] == entry.value.toString()}">
	        	document.getElementById("DayScore_${loopStatus.index + 1}").value = "${power[1]}";
	    		</c:if>
	    	</c:forEach>
		</c:forEach>
	</c:forEach>

	<c:forEach var="dateMap" items="${dateList}" varStatus="loopStatus">
		<c:forEach var="entry" items="${dateMap.entrySet()}">
			<c:forEach var="dateMap" items="${datePoint}" varStatus="pointStatus">
				<c:forEach var="pointy" items="${dateMap.entrySet()}">
    				<c:if test="${pointy.key == entry.key}">
        	 			document.getElementById("DayScore_${loopStatus.index + 1}").value = "${pointy.value}";
    				</c:if>
    			</c:forEach>
    		</c:forEach>
		</c:forEach>
	</c:forEach>

	 function validateInput() {
		<c:forEach var="dateMap" items="${dateList}" varStatus="loopStatus">
			<c:forEach var="entry" items="${dateMap.entrySet()}">
         var input = document.getElementById("DayScore_${loopStatus.index + 1}");
         var value = input.value;
         if (value === "") {
             input.setCustomValidity("入力されてない日があります");
         } else if (value < 1) {
             input.setCustomValidity("下限を超えている日があります");
         } else if (value > 10) {
             input.setCustomValidity("上限を超えている日があります");
         } else {
             input.setCustomValidity("");
         }
         return input.reportValidity();
 			</c:forEach>
 		</c:forEach>
     }
	 console.log('あいうえお');

	</script>

	    <button type="submit">変更</button>
	</form>
	</div>
</div>
</div>
</body>
</html>
