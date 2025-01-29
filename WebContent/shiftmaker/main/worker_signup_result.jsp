<%-- 従業員登録完了JSP --%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<head>
<style>

.body {
    margin-top: 60px;
    display: flex;
    justify-content: center; /* 水平方向に中央揃え */
    align-items: center; /* 垂直方向に中央揃え */
    flex-direction: column; /* 縦方向に配置 */
}

.body p{
	margin-top: 10px;
}

.body a{
	margin-top: 10px;
	margin-left: 200px;
}

</style>

<title>従業員情報登録完了</title>
<!-- bootstrapと繋げる -->
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-9ndCyUaIbzAi2FUVXJi0CjmCapSmO7SnpJef0486qhLnuZ2cdeRhO02iuK6FUUVM" crossorigin="anonymous">

</head>
<html>


	<c:import url="../../common/header.jsp"/>

	<body>

	<div class="body">

				<h2>～従業員情報登録～</h2>
			<!-- 余白 -->

			<p >登録が完了しました。</p>
			<a href="ShiftConditionSignup.action">従業員シフト条件登録へ</a>


	</div>

	<c:import url="../../common/footer.jsp"/>
</html>