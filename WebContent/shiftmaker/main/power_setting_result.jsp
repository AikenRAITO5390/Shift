<%-- 時間更新完了画面 --%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<link rel="stylesheet" href="../../css/style.css">
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
</head>
<body>
<%-- ヘッダーとサイドバー --%>
<c:import url="../../common/header.jsp"/>
	<h2>～パワー設定変更完了～</h2>
		<p>変更が完了しました</p>
	<a href="Main.action">メインメニューへ</a>
	<a href="PowerSetting.action">点数設定へ戻る</a>
</body>
</html>

