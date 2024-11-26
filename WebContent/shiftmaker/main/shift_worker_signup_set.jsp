<%-- 従業員希望シフト登録JSP --%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>

<c:import url="../../common/header.jsp"/>

<body>
<div class="body">

		<c:import url="../../common/navigation.jsp"/>

		<!-- 画面タイトル -->
		<h2>従業員希望シフト登録</h2>

		<div>シフトの希望時間を選択してください。</div>

		<form action="ShiftWorkerSignupResult.action" method="post">
	        <table>
	            <thead>
	                <tr>
	                    <th>選択</th>
	                    <th>勤務時間ID</th>
	                    <th>開始時間</th>
	                    <th>終了時間</th>
	                </tr>
	            </thead>
	            <tbody>
	                <c:forEach var="workTime" items="${workTimes}">
	                    <tr>
	                        <td>
	                            <input type="radio" name="workTimeId" value="${workTime.workTimeId}" required>
	                        </td>
	                        <td>${workTime.workTimeId}</td>
	                        <td>${workTime.workTimeStart}</td>
	                        <td>${workTime.workTimeEnd}</td>
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
	            </tbody>
	        </table>

	        <script>
			    document.addEventListener("DOMContentLoaded", () => {
			        const customRadio = document.getElementById("customWorkTime");
			        const startTimeSelect = document.getElementById("customStartTime");
			        const endTimeSelect = document.getElementById("customEndTime");

			        document.querySelectorAll('input[name="workTimeId"]').forEach(radio => {
			            radio.addEventListener("change", () => {
			                const isCustom = customRadio.checked;
			                startTimeSelect.disabled = !isCustom;
			                endTimeSelect.disabled = !isCustom;
			            });
			        });
			    });
			</script>


	        <input type="hidden" name="workerId" value="${worker.workerId}">
	        <input type="hidden" name="storeId" value="${param.storeId}">
	        <input type="submit" value="決定">
	        <a href="ShiftWorkerSignup.action">戻る</a>
	    </form>



</div>

</body>

<c:import url="../../common/footer.jsp"/>

</html>