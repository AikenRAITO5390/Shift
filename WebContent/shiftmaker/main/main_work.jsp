<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<link rel="stylesheet" href="../../css/style.css">
<jsp:include page="../../common/header_work.jsp" />
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>まるごとシフトくん</title>
</head>
<body>
<c:import url="../../common/header_work.jsp"/>
<div class="main_worker-content">

<h2><a href="ShiftChooseWork.action">📜　シフト閲覧</a></h2>

<h2><a href="BBSWorker.action">📝　掲示板</a></h2>


<h2><a href="ShiftWorkerSignup.action">💪　従業員情報登録</a></h2>


</div>
</body>
<c:import url="../../common/footer.jsp"/>
</html>