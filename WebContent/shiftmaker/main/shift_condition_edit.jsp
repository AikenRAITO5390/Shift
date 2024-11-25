<%-- 従業員シフト条件変更JSP --%>
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
		<h2>シフト条件変更</h2>

		<form action="ShiftConditionEditResult.action" method="post">

		    <!-- IDの表示（変更不可） -->
		    <label>ID:</label>
		    <span>${worker.workerId}</span>
			<input type="hidden" name="workerId" value="${worker.workerId}" />

		    <!-- 名前の表示（変更不可） -->
		    <label>名前:</label>
		    <span>${worker.workerName}</span>
			<input type="hidden" name="workerName" value="${worker.workerName}" />

		    <!-- ポジション選択 -->
		    <label>ポジション:</label>
		    <select name="workerPosition">
			    <c:forEach var="position" items="${positions}">
			        <option value="${position.key}" ${position.key == worker.workerPosition ? 'selected' : ''}>
			            ${position.value}
			        </option>
			    </c:forEach>
			</select>

		    <!-- 点数入力 -->
		    <label>点数:</label>
		    <input type="number" name="workerScore" value="${worker.workerScore}" min="1" max="5" required />

		    <!-- 変更ボタン -->
		    <input type="submit" value="変更" />

		    <!-- 戻るリンク -->
		    <a href="ShiftConditionWorkerList.action">戻る</a>
		</form>

</div>

</body>

<c:import url="../../common/footer.jsp"/>

</html>