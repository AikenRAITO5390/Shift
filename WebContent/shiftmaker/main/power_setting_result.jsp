<%-- 時間更新完了画面 --%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>

<title>パワーバランス設定完了</title>

<style>
.ok h2,p{
	margin-top : 60px;
	text-align : center;
}
.main{
	margin-top : 4%;
	margin-left : 38%;
}
.power{
	margin-top : -1.9%;
	margin-left : 53%;
}

</style>

<head>
<link rel="stylesheet" href="../../css/style.css">
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
</head>
<body>
<%-- ヘッダーとサイドバー --%>
<c:import url="../../common/header.jsp"/>
<div class="ok">
	<h2>～パワーバランス設定完了～</h2>
		<p>登録が完了しました</p>
	<div class="main"><a href="Main.action">メインメニューへ</a></div>
	<div class="power"><a href="PowerSetting.action">点数設定へ戻る</a></div>
</div>
</body>
</html>

