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
<form id="myForm" action="PowerSettingResult.action" method="post">
    <table class="table table-hover">
        <c:forEach var="i" begin="0" end="6">
            <tr>
                <td><label>${fn:substring('月火水木金土日', i, i+1)}</label></td>
                <td>
                  <input type="number" name="WeekScore_${i}" id="WeekScore_${i}" />
                   <span class="error" id="WeekScore_${i}Error"></span>

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
        // power_list[曜日を数字にしたもの][曜日の点数]
        <c:forEach var="i" begin="0" end="6">
            <c:forEach var="power" items="${power_list}">
            // もし曜日を数字にしたものと数字が同じなら
                <c:if test="${power[0] == (i+1).toString()}">
           // WeekScoreに元々張っている数字を入れる
                    document.getElementById("WeekScore_${i}").value = "${power[1]}";
                </c:if>
            </c:forEach>
        </c:forEach>

    </script>
    <button type="submit">変更</button>
</form>
</div>

<div class = point_conetnt>
<form id = 'dayForm' action="DayPowerSettingResult.action" method="post">
	    <table class="table table-hover">
	        <c:forEach var="dateMap" items="${dateList}" varStatus="loopStatus">
	            <c:forEach var="entry" items="${dateMap.entrySet()}">
	                <tr>
	                    <td><label>${loopStatus.index + 1}</label></td>
	                    <td>
	                        <input type="number" name="DayScore_${loopStatus.index + 1}" id="DayScore_${loopStatus.index + 1}" >
	                         <span class="error" id="DayScore_${loopStatus.index + 1}Error"></span>
	                    </td>
	                    	<input type="hidden" name="WorkDayScore_${loopStatus.index + 1}" value="${entry.key}">
	                </tr>
	            </c:forEach>
	        </c:forEach>
	    </table>
	    <button type="submit">変更</button>
	</form>
	</div>
</div>

	<script>

	 //　dateList[日付][その時の点数]
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


	document.addEventListener('DOMContentLoaded', function() {
	    console.log("ページが読み込まれました");

	    document.getElementById('myForm').addEventListener('submit', function(event) {
	        var isValid = true;
	        var pointMax = ${pointMax};

	        for (var i = 0; i < 7; i++) {
	            var weekScore = document.getElementById('WeekScore_' + i);
	            var weekScoreError = document.getElementById('WeekScore_' + i + 'Error');
	            var value = weekScore.value.trim();
	            if (weekScore.value.trim() === "") {
	                weekScore.value = ""; // 入力値をクリア
	                weekScore.placeholder = "数字を入力してください";
	                weekScore.classList.add('error-placeholder');
	                weekScore.style.color = "red";
	                isValid = false;
	            } else if (isNaN(Number(value))) {
	                weekScore.value = ""; // 入力値をクリア
	                weekScore.placeholder = "数字のみ入力してください";
	                weekScore.classList.add('error-placeholder');
	                weekScore.style.color = "red";
	                isValid = false;
	            } else if (Number(value) < 0 || Number(value) >pointMax) {
	                weekScore.value = ""; // 入力値をクリア
	                weekScore.placeholder = "0以上"+ pointMax +"以下の数字を入力してください";
	                weekScore.classList.add('error-placeholder');
	                weekScore.style.color = "red";
	                isValid = false;
	            }else {
	                weekScore.placeholder = "";
	                weekScore.classList.remove('error-placeholder');
	            }
	        }
	        if (!isValid) {
	            event.preventDefault();
	        }
	    });

	    document.getElementById('dayForm').addEventListener('submit', function(event) {
	        var isValiDay = true;
	        var Maxpo = ${pointMax};
	    	<c:forEach var="dateMap" items="${dateList}" varStatus="loopStatus">
	            var dayScore = document.getElementById('DayScore_${loopStatus.index + 1}');
	            var dayScoreError = document.getElementById('DayScore_${loopStatus.index + 1}Error');

	            var value = dayScore.value.trim();
	            if (dayScore.value.trim() === "") {
	                dayScore.value = ""; // 入力値をクリア
	                dayScore.placeholder = "数字を入力してください";
	                dayScore.classList.add('error-placeholder');
	                dayScore.style.color = "red";
	                isValiDay = false;
	            } else if (isNaN(Number(value))) {
	            	dayScore.value = ""; // 入力値をクリア
	            	dayScore.placeholder = "数字のみ入力してください";
	            	dayScore.classList.add('error-placeholder');
	            	dayScore.style.color = "red";
	            	isValiDay = false;
	            } else if (Number(value) < 0 || Number(value) > Maxpo) {
	            	dayScore.value = ""; // 入力値をクリア
	            	dayScore.placeholder = "0以上"+ Maxpo +"以下の数字を入力してください";
	            	dayScore.classList.add('error-placeholder');
	            	dayScore.style.color = "red";
	            	isValiDay = false;
	            }else {
	                dayScore.placeholder = "";
	                dayScore.classList.remove('error-placeholder');
	            }
	            </c:forEach>
	        console.log("DayScore isValid: " + isValiDay);

	        if (!isValiDay) {
	            console.log("フォーム送信を防止します");
	            event.preventDefault();
	        }
	    });
	});
	</script>


</body>
</html>
