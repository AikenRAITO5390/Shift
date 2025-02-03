<%-- シフト編集セットJSP --%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>

<title>シフト編集</title>

<style>

.body {
    display: flex;
    flex-direction: column;
    align-items: center; /* 🔹 全体を中央に配置 */
    justify-content: center;
    width: 100%;
}

.submit {
    text-align: center;
    margin-top: 35px;
}

/* "戻る" ボタンの中央配置 */
.a {
    text-align: center;
    margin-top: 28px;
}

/* シフト希望情報 */
.workTimeId {
    padding: 5px 10px; /* 内側の余白 */
    min-width: 100px; /* 最小幅を確保 */
    max-width: 200px; /* 最大幅を設定 */
    margin-left: -15px; /* 🔹 さらに左に寄せる */
}

/* "元のシフト希望" のラベルと内容を横並びに */
.shift-container {
    display: flex; /* 横並び */
    align-items: center; /* 縦方向中央揃え */
    flex-wrap: wrap; /* 画面が狭い時は折り返し */
    gap: 5px; /* `h4` と `workTimeId` の間に適度な余白 */
    justify-content: center; /* 🔹 中央寄せ */

    margin-top : -25px;
}

/*従業員のIDデータ*/
.workerid {
	margin-top : -46px;
	margin-left : 18%;
    border: 1px solid #000; /* 枠線を黒に設定 */
    width: 20%;
    text-align : center;
}

.worker-table-container {
    display: flex; /* worker と table を横並びに */
    justify-content: center; /* 🔹 全体を中央に配置 */
    align-items: flex-start; /* 上端を揃える */
     width: 80%; /* 🔹 横幅を調整（90% → 80% に変更） */
    margin: 0 auto; /* 中央配置 */
     gap: 20px; /* 🔹 余白を調整 */
}

.worker {
   flex: none; /* 🔹 必要以上に伸縮しないようにする */
    width: auto; /* 🔹 必要に応じてサイズ調整 */

}

.table {
   flex: none; /* 🔹 必要以上に伸縮しないようにする */
    width: auto; /* 🔹 必要に応じてサイズ調整 */

}

.h2{
	margin-top:30px;
}

.button-container {
    display: flex;
    justify-content: center; /* 🔹 中央揃え */
    align-items: center; /* 縦方向中央揃え */
    gap: 20px; /* 🔹 ボタンの間隔を調整 */
    margin-top: 50px;
}

/* レスポンシブ対応 */
@media screen and (max-width: 768px) {
    .shift-container {
        flex-direction: column; /* 画面幅が狭い時は縦並びにする */
        align-items: flex-start; /* 左寄せ */
    }
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
		<div class="worker-table-container">
			<div class="worker">
			<c:if test="${not empty workerId}">
		        <h4>選択された従業員:${workerId}</h4>
		            <!-- div class="workerid"></div-->

		    </c:if>

			<c:if test="${not empty date}">
		        <h4>選択された日付:${date}</h4>
		            <!-- div class="date"></div-->

		    </c:if>

			<div class="shift-container">
			    <h4>元のシフト希望:</h4>

				<div class="workTimeId">
		        	<c:if test="${not empty workTimeId}">
				    	<h4>
				       		<c:choose>
				            	<c:when test="${workTimeId == 'T'}">〇</c:when>
				            	<c:otherwise>${workTimeId}</c:otherwise>
				        	</c:choose>
				   		</h4>
					</c:if>


					<h4>
		        	<c:if test="${not empty startHour_c}">
		            	<p>${startHour_c}:00 - ${endHour_c}:00</p>
		        	</c:if>
		        	</h4>
		        </div>
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
    		<div class="button-container">
		        <div class="submit">
		        	<input type="submit" value="変更">
				</div>
		        <div class="a">
		        	<a href="ShiftEdit.action">戻る</a>
		        </div>
		    </div>
	    </form>

</div>

</body>

<c:import url="../../common/footer.jsp"/>

</html>