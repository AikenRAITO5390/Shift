<%-- 科目削除入力JSP --%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>従業員情報削除</title>
</head>
<body>
	<h2>～従業員情報削除～</h2>
	<form action = "SubjectDeleteExecute.action" method="post">



	<!-- <label>科目名</label> -->
	<input type = "hidden" name = "no" value = "${worker.workerId}">



		<p>「${worker.workerName}(${worker.workerId})」を削除してもよろしいですか</p>


		<input type="submit" value="削除">

	</form>

	<a href="SubjectList.action">戻る</a>

<c:import url="../../common/footer.jsp"/>

</body>
</html>