<%-- 学生更新JSP --%>
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
<c:import url="../../common/header.jsp"/>
	<h2>シフト作成者変更</h2>
	<form action = "ShiftManagerSignupResult.action" method="post">
	<div class="form-group">
		<label>A</label>
		<input type="text" name="work_time_id"
			maxlength="1"value="${work_time_id}">
	</div>
	<div class="form-group">

		<label>名前</label>
		<input type="text" name="managerName"　placeholder="名前を入力してください"
			maxlength="30" value="${managerName}">
	</div>

		<label>パスワード</label>
		<input type="text" name="password" placeholder="パスワードを入力してください"
			maxlength="20" value="${password}" required />
	<div class="form-group">
		<label>メールアドレス</label>
			<input type="text" name="email" placeholder="メールを入力してください"
			maxlength="40" value="${email}" required />
	</div>
	<div class="form-group">
		<label>店情報</label>
			<input type="text" name="storeName" placeholder="お店情報を入力してください"
			maxlength="20" value="${storeName}" required />
	</div>
		<input type="submit" value="変更">

	</form>

	<a href="Main.action">戻る</a>

</body>
</html>