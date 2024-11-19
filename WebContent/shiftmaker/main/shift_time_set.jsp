<%-- 学生一覧JSP --%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<link rel="stylesheet" href="../../css/style.css">
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
</head>
<c:import url="../../common/header.jsp"/>
<body>

	<h2>シフト時間設定</h2>

	<c:choose>
		<c:when test="${time_list.size()>0}">

			<table class="table table-hover">
				<c:forEach var="time_list" items="${time_list}">
						<label>${time_list.workTimeId}</label>
						<input type="time" name="managerName"　 value="${managerName}">
							<%-- 在学フラグがたっている場合「○」それ以外は「×」を表示 --%>

						<td><a href="ShiftTimeSetting.action">変更</a></td>
				</c:forEach>
			</table>
		</c:when>
		<c:otherwise>
			<div>学生情報が存在しませんでした</div>
		</c:otherwise>
	</c:choose>

</body>
</html>