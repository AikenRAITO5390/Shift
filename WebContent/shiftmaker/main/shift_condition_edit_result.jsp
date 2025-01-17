<%-- 従業員シフト条件変更完了JSP --%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>

<c:import url="../../common/header.jsp"/>

<body>
<div class="body">


	<!-- 画面タイトル -->
	<h2>～シフト条件変更完了～</h2>

	<div>${message}</div>
    <a href="ShiftConditionWorkerList.action">一覧に戻る</a>


</div>

</body>

<c:import url="../../common/footer.jsp"/>

</html>