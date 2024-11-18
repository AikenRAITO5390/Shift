<%-- 学生更新JSP --%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
</head>
<body>
	<h2>シフト作成者変更</h2>
	<form action = "ShiftManagerSignupResult.action" method="post">
		<label>ID</label>
		<input type="text" name="storeId" placeholder="IDを入力してください"
			maxlength="7"value="${storeId}">


		<label>名前</label>
		<input type="text" name="managerName"　placeholder="名前を入力してください"
			maxlength="30" value="${managerName}">


		<label>パスワード</label>
		<input type="text" name="password" placeholder="パスワードを入力してください"
			maxlength="20" value="${password}" required />

		<label>メールアドレス</label>
			<input type="text" name="email" placeholder="メールを入力してください"
			maxlength="40" value="${email}" required />

		<label>店情報</label>
			<input type="text" name="storeName" placeholder="お店情報を入力してください"
			maxlength="20" value="${storeName}" required />

		<input type="submit" value="変更">

	</form>

	<a href="Main.action">戻る</a>

</body>
</html>