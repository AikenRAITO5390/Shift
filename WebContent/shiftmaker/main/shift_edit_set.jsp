<%-- シフト編集セットJSP --%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>

<title>シフト編集</title>

<style>
.h2 {
	margin-top : 60px;
	text-align : center;
}
.h3 {
	margin-top : 40px;
	text-align : center;
}
.worker {
	margin-top : 40px;
	margin-left : 20%;
}
.table {
	margin-top : -145px;
	margin-left : 45%;
}
.submit {
	margin-top : 50px;
	margin-left : 65%;
}
.a {
	margin-top : -25px;
	margin-left : 60%;
}
/*アルファベットの場合*/
.workTimeId {
	margin-top : -46px;
	margin-left : 14%;
}
/*時間選択済の場合*/
.workTimeId2 {
	margin-top : -46px;
	margin-left : 14%;
}
</style>

<c:import url="../../common/header.jsp"/>

<body>
<div class="body">
		<div class="h2">
		<!-- 画面タイトル -->
		<h2>～シフト編集～</h2>
		</div>

		<div class="h3">
		<c:if test="${isWorkerJudgeTrue}">
	          <h3>シフトの希望を選択してください。</h3>
	    </c:if>
	    <c:if test="${not isWorkerJudgeTrue}">
	          <h3>シフトの希望時間を選択してください。</h3>
	    </c:if>
		</div>


		<form action="ShiftEditSave.action?date=${date}?workerId=${workerId}&count=${count}" method="post">

			<div class="worker">
			<c:if test="${not empty workerId}">
		        <h3>選択された従業員:
		            ${workerId}
		        </h3>
		    </c:if>

			<c:if test="${not empty date}">
		        <h3>選択された日付:
		            ${date}
		        </h3>
		    </c:if>

		    <h3>元のシフト希望:</h3>

			<div class="workTimeId">
	        <c:if test="${not empty workTimeId}">
			    <h3>
			        <c:choose>
			            <c:when test="${workTimeId == 'T'}">〇</c:when>
			            <c:otherwise>${workTimeId}</c:otherwise>
			        </c:choose>
			    </h3>
			</c:if>
			</div>

			<div class="workTimeId2">
	        <c:if test="${not empty startHour_c}">
	            <h3>${startHour_c}:00 - ${endHour_c}:00</h3>
	        </c:if>
	        </div>


			</div>

			<div class="table">
	        <table>
	            <thead>
	                <tr>
	                    <c:if test="${isWorkerJudgeTrue}">
	                        <!-- worker_judgeがTrueの場合のヘッダー -->
	                        <th>選択</th>
	                    </c:if>
	                    <c:if test="${not isWorkerJudgeTrue}">
	                        <!-- worker_judgeがFalseの場合の従来のヘッダー -->
	                        <th>選択</th>
	                        <th>勤務時間ID</th>
	                        <th>開始時間</th>
	                        <th>終了時間</th>
	                    </c:if>
	                </tr>
	            </thead>
	            <tbody>
	                <c:if test="${isWorkerJudgeTrue}">
	                <!-- worker_judgeがTrueの場合の選択肢（〇または-） -->
	                <tr>
	                    <td>
	                        <input type="radio" name="workTimeId" value="T" required> 〇
	                    </td>
	                </tr>
	                <tr>
	                    <td>
	                        <input type="radio" name="workTimeId" value="NONE" required> -
	                    </td>
	                </tr>
	            </c:if>

	            <c:if test="${not isWorkerJudgeTrue}">
	                <!-- worker_judgeがFalseの場合の従来通りの選択肢（A, B, C, D） -->
	                <c:forEach var="workTime" items="${workTimes}">
	                    <tr>
	                        <c:if test="${workTime.workTimeId != 'F'}">
	                            <td>
	                                <input type="radio" name="workTimeId" value="${workTime.workTimeId}" required>
	                            </td>
	                            <td>${workTime.workTimeId}</td>
	                            <td>${workTime.workTimeStart}</td>
	                            <td>${workTime.workTimeEnd}</td>
	                        </c:if>
	                    </tr>
	                </c:forEach>

	                <!-- Eの選択肢を追加 -->
	                <tr>
	                    <td>
	                        <input type="radio" name="workTimeId" value="E" id="customWorkTime" required>
	                    </td>
	                    <td>E</td>
	                    <td>
	                        <select name="customStartTime" id="customStartTime" disabled>
	                            <c:forEach var="hour" begin="${store_time_start}" end="${store_time_end - 1}">
	                                <option value="${hour}">${hour}:00</option>
	                            </c:forEach>
	                        </select>
	                    </td>
	                    <td>
	                        <select name="customEndTime" id="customEndTime" disabled>
	                            <c:forEach var="hour" begin="${store_time_start + 1}" end="${store_time_end}">
	                                <option value="${hour}">${hour}:00</option>
	                            </c:forEach>
	                        </select>
	                    </td>
	                </tr>
	                <!-- なしにする -->
					<tr>
					    <td>
					        <input type="radio" name="workTimeId" value="NONE" required>
					    </td>
						<td>なし</td>
					    <td colspan="2">希望なし</td>
					</tr>
	            </c:if>
	            </tbody>
	        </table>
	        </div>

	        <script>
			    document.addEventListener("DOMContentLoaded", () => {
			        const customRadio = document.getElementById("customWorkTime");
			        const startTimeSelect = document.getElementById("customStartTime");
			        const endTimeSelect = document.getElementById("customEndTime");
			        const form = document.querySelector("form");

			        document.querySelectorAll('input[name="workTimeId"]').forEach(radio => {
			            radio.addEventListener("change", () => {
			                const isCustom = customRadio.checked;
			                startTimeSelect.disabled = !isCustom;
			                endTimeSelect.disabled = !isCustom;
			            });
			        });

			        form.addEventListener("submit", (event) => {
			            if (customRadio.checked) {
			                const startTime = parseInt(startTimeSelect.value, 10);
			                const endTime = parseInt(endTimeSelect.value, 10);

			                if (startTime >= endTime) {
			                    event.preventDefault(); // フォーム送信をキャンセル
			                    alert("正確に時間を選択してください。");
			                }
			            }
			        });
			    });
			</script>


	        <input type="hidden" name="workerId" value="${param.workerId}">
    		<input type="hidden" name="date" value="${date}">
	        <div class="submit"><input type="submit" value="変更"></div>
	        <div class="a"><a href="ShiftEdit.action">戻る</a></div>
	    </form>



</div>

</body>

<c:import url="../../common/footer.jsp"/>

</html>