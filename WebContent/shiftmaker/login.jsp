<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>まるごとシフトくん</title>
</head>
<body>

<form action = "LoginExecute.action" method="post">

<!--
	autocomplete
	on/off:自動補完の制御

	ime-mode
	active:漢字（全角）モードにします
	disabled:日本語入力機能(IME)そのものを使用不可能

	required:input要素を入力必須にする属性
 -->


	<h2>ログイン</h2>
	<!-- ログインＩＤ -->
	<label>ＩＤ</label>
	<input type="text" name="WORKER_ID" maxlength="10" placeholder="10文字以内の半角英数字でご入力下さい"
	 autocomplete="off" style="ime-mode: disabled" value="" required/>

	<!-- パスワード -->
	<label>パスワード</label>
	<input type="password" name="WORKER_PASSWORD" maxlength="20" placeholder="20文字以内の半角英数字でご入力下さい" value=""
	style="ime-mode: disabled" required/>

	<!-- パスワード表示チェックボックス -->
	<!-- <input id="inputCheckbox" type="checkbox"> -->
	<input id="togglePassword" type="checkbox"> パスワードを表示する

	 <c:forEach var="error" items="${errors}">
	 <li>${errors}</li>
	 </c:forEach>

	<!-- ログイン用ボタン -->
	<input type="submit" name="login" value="ログイン"/>

	<!-- シフト作成者用ログインリンク -->
	<li><a href="Login2.Action">Top</a></li>

</form>


</body>
</html>
