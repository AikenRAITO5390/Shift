<%-- 時間設定JSP --%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>

<title>シフト時間設定</title>

<style>
.h2 h2{
	margin-top : 80px;
	text-align : center;
}
.time {
	margin-left : 38%;
}
.kara {
	margin-left : 10%;
}
.time_time {
	margin-top : 60px;
	text-align : center;
}
.time_time select{
	margin-left : 30px;
}
.time_time h3{
	margin-left : 1.5%;
}
.ok{
	margin-top : 60px;
	margin-left : 60%;
}
.no{
	margin-top : -30px;
	margin-left : 40%;
}

td {
    width: 100px; /* 必要に応じて幅を調整 */
    box-sizing: border-box; /* パディングとボーダーを含めた幅を計算 */
}
.sita {
	margin-top : 60px;
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
<form action="ShiftTimeSetting.action" method="post" onsubmit="return validateShiftTimes()">
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
				<td><h3 class="kara">～</h3></td>
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


<!-- 営業時間設定（値保持） -->
<div class="time_time">
    <h2>営業時間設定</h2>


    <select name="storeTimeStart">
        <option value="00:00:00" ${storeTimeStart == '00:00:00' ? 'selected' : ''}>00:00</option>
        <option value="01:00:00" ${storeTimeStart == '01:00:00' ? 'selected' : ''}>01:00</option>
        <option value="02:00:00" ${storeTimeStart == '02:00:00' ? 'selected' : ''}>02:00</option>
        <option value="03:00:00" ${storeTimeStart == '03:00:00' ? 'selected' : ''}>03:00</option>
        <option value="04:00:00" ${storeTimeStart == '04:00:00' ? 'selected' : ''}>04:00</option>
        <option value="05:00:00" ${storeTimeStart == '05:00:00' ? 'selected' : ''}>05:00</option>
        <option value="06:00:00" ${storeTimeStart == '06:00:00' ? 'selected' : ''}>06:00</option>
        <option value="07:00:00" ${storeTimeStart == '07:00:00' ? 'selected' : ''}>07:00</option>
        <option value="08:00:00" ${storeTimeStart == '08:00:00' ? 'selected' : ''}>08:00</option>
        <option value="09:00:00" ${storeTimeStart == '09:00:00' ? 'selected' : ''}>09:00</option>
        <option value="10:00:00" ${storeTimeStart == '10:00:00' ? 'selected' : ''}>10:00</option>
        <option value="11:00:00" ${storeTimeStart == '11:00:00' ? 'selected' : ''}>11:00</option>
        <option value="12:00:00" ${storeTimeStart == '12:00:00' ? 'selected' : ''}>12:00</option>
        <option value="13:00:00" ${storeTimeStart == '13:00:00' ? 'selected' : ''}>13:00</option>
        <option value="14:00:00" ${storeTimeStart == '14:00:00' ? 'selected' : ''}>14:00</option>
        <option value="15:00:00" ${storeTimeStart == '15:00:00' ? 'selected' : ''}>15:00</option>
        <option value="16:00:00" ${storeTimeStart == '16:00:00' ? 'selected' : ''}>16:00</option>
        <option value="17:00:00" ${storeTimeStart == '17:00:00' ? 'selected' : ''}>17:00</option>
        <option value="18:00:00" ${storeTimeStart == '18:00:00' ? 'selected' : ''}>18:00</option>
        <option value="19:00:00" ${storeTimeStart == '19:00:00' ? 'selected' : ''}>19:00</option>
        <option value="20:00:00" ${storeTimeStart == '20:00:00' ? 'selected' : ''}>20:00</option>
        <option value="21:00:00" ${storeTimeStart == '21:00:00' ? 'selected' : ''}>21:00</option>
        <option value="22:00:00" ${storeTimeStart == '22:00:00' ? 'selected' : ''}>22:00</option>
        <option value="23:00:00" ${storeTimeStart == '23:00:00' ? 'selected' : ''}>23:00</option>
        <option value="24:00:00" ${storeTimeStart == '24:00:00' ? 'selected' : ''}>24:00</option>
    </select>
    <h3>|</h3>
    <select name="storeTimeEnd">
        <option value="00:00:00" ${storeTimeEnd == '00:00:00' ? 'selected' : ''}>00:00</option>
        <option value="01:00:00" ${storeTimeEnd == '01:00:00' ? 'selected' : ''}>01:00</option>
        <option value="02:00:00" ${storeTimeEnd == '02:00:00' ? 'selected' : ''}>02:00</option>
        <option value="03:00:00" ${storeTimeEnd == '03:00:00' ? 'selected' : ''}>03:00</option>
        <option value="04:00:00" ${storeTimeEnd == '04:00:00' ? 'selected' : ''}>04:00</option>
        <option value="05:00:00" ${storeTimeEnd == '05:00:00' ? 'selected' : ''}>05:00</option>
        <option value="06:00:00" ${storeTimeEnd == '06:00:00' ? 'selected' : ''}>06:00</option>
        <option value="07:00:00" ${storeTimeEnd == '07:00:00' ? 'selected' : ''}>07:00</option>
        <option value="08:00:00" ${storeTimeEnd == '08:00:00' ? 'selected' : ''}>08:00</option>
        <option value="09:00:00" ${storeTimeEnd == '09:00:00' ? 'selected' : ''}>09:00</option>
        <option value="10:00:00" ${storeTimeEnd == '10:00:00' ? 'selected' : ''}>10:00</option>
        <option value="11:00:00" ${storeTimeEnd == '11:00:00' ? 'selected' : ''}>11:00</option>
        <option value="12:00:00" ${storeTimeEnd == '12:00:00' ? 'selected' : ''}>12:00</option>
        <option value="13:00:00" ${storeTimeEnd == '13:00:00' ? 'selected' : ''}>13:00</option>
        <option value="14:00:00" ${storeTimeEnd == '14:00:00' ? 'selected' : ''}>14:00</option>
        <option value="15:00:00" ${storeTimeEnd == '15:00:00' ? 'selected' : ''}>15:00</option>
        <option value="16:00:00" ${storeTimeEnd == '16:00:00' ? 'selected' : ''}>16:00</option>
        <option value="17:00:00" ${storeTimeEnd == '17:00:00' ? 'selected' : ''}>17:00</option>
        <option value="18:00:00" ${storeTimeEnd == '18:00:00' ? 'selected' : ''}>18:00</option>
        <option value="19:00:00" ${storeTimeEnd == '19:00:00' ? 'selected' : ''}>19:00</option>
        <option value="20:00:00" ${storeTimeEnd == '20:00:00' ? 'selected' : ''}>20:00</option>
        <option value="21:00:00" ${storeTimeEnd == '21:00:00' ? 'selected' : ''}>21:00</option>
        <option value="22:00:00" ${storeTimeEnd == '22:00:00' ? 'selected' : ''}>22:00</option>
        <option value="23:00:00" ${storeTimeEnd == '23:00:00' ? 'selected' : ''}>23:00</option>
        <option value="24:00:00" ${storeTimeEnd == '24:00:00' ? 'selected' : ''}>24:00</option>
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

<div class="sita"></div>

<script>
    function validateShiftTimes() {
        // A～D のシフト時間をループで検証
        for (let i = 0; i < 4; i++) {
            var workTimeStart = document.getElementById("timeSelectStart_" + String.fromCharCode(65 + i)).value;
            var workTimeEnd = document.getElementById("timeSelectEnd_" + String.fromCharCode(65 + i)).value;

            // 時間の形式を比較
            if (workTimeStart >= workTimeEnd) {
                alert("シフト開始時間は終了時間より遅く設定できません。正しく選択してください。");
                return false;  // フォーム送信を停止
            }
        }

        // 営業時間のバリデーション
        var storeTimeStart = document.getElementsByName("storeTimeStart")[0].value;
        var storeTimeEnd = document.getElementsByName("storeTimeEnd")[0].value;

        // 営業開始時間と終了時間を比較
        if (storeTimeStart >= storeTimeEnd) {
            alert("営業時間開始時間は終了時間より遅く設定できません。正しく選択してください。");
            return false;  // フォーム送信を停止
        }

        // すべての時間が正しい場合、フォーム送信
        return true;
    }
</script>

<c:import url="../../common/footer.jsp"/>

</body>
</html>
