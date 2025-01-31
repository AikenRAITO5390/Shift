<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>

<c:import url="../../common/header.jsp"/>

<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>シフト作成</title>

<style>
.ShiftMain{
	margin-top: 60px;
    display: flex;
    justify-content: center;
    align-items: center;
}

.buttom{
	 display: flex;
    justify-content: center;
    align-items: center;
	 margin-top: 10px;

}

.buttom button{
	 width: 100px;
	 background-color: #6495ED;

}

 .aAction {
 	 display: flex;
    justify-content: center;
    align-items: center;
	margin-left: 450px;
	margin-top: 30px;
}
</style>


</head>
<body>
<div class="ShiftMain">
<h1>～シフト作成～</h1>
</div>
<div class="buttom">
<%--ShiftCreateActionにボタンで飛ばす --%>
<button onclick="location.href='ShiftCreate.action'" >作成</button>
</div>
<div class =" aAction">
<a href="Main.action">メインへ戻る</a>
</div>
</body>

<c:import url="../../common/footer.jsp"/>

</html>