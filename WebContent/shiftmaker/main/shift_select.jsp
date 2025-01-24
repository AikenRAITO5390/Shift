<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>

<c:import url="../../common/header.jsp"/>

<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>シフト作成</title>
</head>
<body>

<h1>～シフト作成～</h1>
<%--ShiftCreateActionにボタンで飛ばす --%>
<button onclick="location.href='ShiftCreate.action'" >作成</button>
<a href="Main.action">メインへ戻る</a>
</body>

<c:import url="../../common/footer.jsp"/>

</html>