<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
<c:forEach var="list" items="${shift_list}">
	<p>${list.shiftDate}</p>
	<p>${list.shiftScore}</p>
	<p>${list.shiftHopeTimeId}</p>
	<p>${list.shiftHopeTimeStart}</p>
	<p>${list.shiftHopeTimeEnd}</p>
	<p>${list.shiftId}</p>
</c:forEach>
<h1>シフト作成</h1>
<%--ShiftCreateActionにボタンで飛ばす --%>
<button onclick="location.href='ShiftCreate.action'" >作成</button>
</body>
</html>