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
	            </tbody>
	        </table>
	        <input type="hidden" name="workerId" value="${worker.workerId}">
	        <input type="hidden" name="storeId" value="${param.storeId}">
	        <input type="submit" value="送信">
	        <a href="ShiftWorkerSignup.action">戻る</a>
	    </form>



</div>

</body>

<c:import url="../../common/footer.jsp"/>

</html>