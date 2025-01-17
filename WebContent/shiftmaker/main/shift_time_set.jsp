<%-- 時間設定JSP --%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<style>
.h2 h2{
	margin-top : 80px;
	text-align : center;
}
.time label{
	margin-left: 440px;

}
.time h3{
	margin-left: 30px;

}
.time select{
	margin-left : 30px;
}


.time_time {
	margin-top : 60px;
	text-align : center;
}
.time_time select{
	margin-left : 30px;
}
.ok{
	margin-top : 60px;
	margin-left : 750px;
}
.no{
	margin-top : -30px;
	margin-left : 220px;
}

td {
    width: 100px; /* 必要に応じて幅を調整 */
    box-sizing: border-box; /* パディングとボーダーを含めた幅を計算 */
}



</style>

<head>
<%-- cssの取得 --%>
<link rel="stylesheet" href="../../css/style.css">
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
</head>
<%--ヘッダーサイドバー--%>
<c:import url="../../common/header.jsp"/>
<body>

<div class="h2">
	<h2>～シフト時間設定～</h2>
</div>
<%--次とぶやつ--%>

<div class="time">
<form action="ShiftTimeSetting.action" method="post">
    <table class="table table-hover">
    <%--四回繰り返す（A～D)　--%>
        <c:forEach var="i" begin="0" end="3">
            <tr>
            <%--A~Dの表示　--%>
                <td><label>${fn:substring('ABCD', i, i+1)}</label></td>

                <td>
                <%--スタート時間の表示--%>
                <%--初期値timeSelectStart(下で設定）、nameはAのworkTimeStartでわたす　--%>
                    <select id="timeSelectStart_${fn:substring('ABCD', i, i+1)}" name="workTimeStart_${i}">
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
				<td><h3>～</h3></td>
                <td>
                <%--エンド時間の表示--%>
                <%--初期値timeSelectEnd(下で設定）、nameはAのworkTimeEndでわたす　--%>
                    <select id="timeSelectEnd_${fn:substring('ABCD', i, i+1)}" name="workTimeEnd_${i}">
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
				<%--Idをわたす。hiddenで隠す --%>
                  <input type="hidden" name="workTimeId_${i}" value="${fn:substring('ABCD', i, i+1)}">
            </tr>
        </c:forEach>
    </table>
</div>


    <!-- 営業時間設定 -->
<div class="time_time">
    <h2>営業時間設定</h2>


    <select name="storeTimeStart">
    	<option value="00:00:00">00:00</option>
		<option value="01:00:00">01:00</option>
		<option value="02:00:00">02:00</option>
	    <option value="03:00:00">03:00</option>
	    <option value="04:00:00">04:00</option>
	    <option value="05:00:00">05:00</option>
	    <option value="06:00:00">06:00</option>
	    <option value="07:00:00">07:00</option>
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
     <h3>|</h3>
     <select name="storeTimeEnd">
     	<option value="00:00:00">00:00</option>
		<option value="01:00:00">01:00</option>
		<option value="02:00:00">02:00</option>
	    <option value="03:00:00">03:00</option>
	    <option value="04:00:00">04:00</option>
	    <option value="05:00:00">05:00</option>
	    <option value="06:00:00">06:00</option>
	    <option value="07:00:00">07:00</option>
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
</div>
     <script>
        <c:forEach var="i" begin="0" end="3">
            <c:choose>
                <c:when test="${i < time_list.size()}">
                    // workTimeStartの値を設定
                    var workTimeStart = "${time_list[i].workTimeStart}";
                    var selectElementStart = document.getElementById("timeSelectStart_${fn:substring('ABCD', i, i+1)}");
                    selectElementStart.value = workTimeStart;

                    // workTimeEndの値を設定
                    var workTimeEnd = "${time_list[i].workTimeEnd}";
                    var selectElementEnd = document.getElementById("timeSelectEnd_${fn:substring('ABCD', i, i+1)}");
                    selectElementEnd.value = workTimeEnd;
                </c:when>
                <c:otherwise>
                // もし値がまだ未入力の場合の処理
                    // 空のエントリの場合はデフォルト値を設定
                    var selectElementStart = document.getElementById("timeSelectStart_${fn:substring('ABCD', i, i+1)}");
                    selectElementStart.value = "08:00:00";

                    var selectElementEnd = document.getElementById("timeSelectEnd_${fn:substring('ABCD', i, i+1)}");
                    selectElementEnd.value = "08:00:00";
                </c:otherwise>
            </c:choose>
        </c:forEach>
    </script>

    <div class="ok">
    <button type="submit">決定</button>
    </div>

    <div class="no">
    <a href="Main.action">戻る</a>
    </div>

</form>

</body>
</html>
