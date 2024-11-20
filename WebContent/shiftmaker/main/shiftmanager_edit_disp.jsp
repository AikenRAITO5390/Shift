<%-- 学生更新JSP --%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<%-- cssを取得してくる --%>

<link rel="stylesheet" href="../../css/style.css">
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
</head>
<body>
<%-- headerとサイドバーの取得 --%>
<c:import url="../../common/header.jsp"/>
	<h2>シフト作成者変更</h2>
<%-- 次とばす場所の設定 --%>
	<form action = "ShiftManagerSignupResult.action" method="post">
<%-- css設定のためのdiv_class --%>
	<div class="form-group">
<%-- 店舗ID --%>
		<label>店舗ID</label>
		<input type="text" name="storeId"
			maxlength="7"value="${storeId}"readonly>
	</div>

<%--名前  --%>
	<div class="form-group">

		<label>名前</label>
		<input type="text" name="managerName"　placeholder="名前を入力してください"
			maxlength="30" value="${managerName}">
	</div>
<%-- パスワード --%>

		<label>パスワード</label>
		<input type="text" name="password" placeholder="パスワードを入力してください"
			maxlength="20" value="${password}" required />
	<div class="form-group">

	<%-- メール --%>
		<label>メールアドレス</label>
			<input type="text" name="email" placeholder="メールを入力してください"
			maxlength="40" value="${email}" required />
	</div>

	<%-- 店舗名 --%>
	<div class="form-group">
		<label>店情報</label>
			<input type="text" name="storeName" placeholder="お店情報を入力してください"
			maxlength="20" value="${storeName}" required />
	</div>

	<%-- 更新ボタン --%>
		<input type="submit" value="変更">

	</form>

	<a href="Main.action">戻る</a>

</body>
</html>