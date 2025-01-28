<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>

<style>
.header {
	 position: fixed;
    top: 0;
    left: 0;
    width: 100%;
    height: 50px;
    background: #7d7d7d;
    display: flex;
    flex-direction: column;
    align-items: center;
    color: white;
    z-index: 1000;
    flex-direction: row; /* 横方向に配置 */
    justify-content: space-between; /* 左右に配置 */
}
.h2{
	margin-top : 80px;
	text-align : center;
}
.id{
	margin-top : 50px;
	text-align : center;
	letter-spacing: 17px; /* 文字間のスペースを2pxに設定 */
}
.error {
	text-align : center;
}
.pass {
	margin-top : 20px;
	text-align : center;
	letter-spacing: 1px; /* 文字間のスペースを2pxに設定 */
}
.box {
	margin-top : 20px;
	text-align : center;
}
.login input[type="submit"] {
    width: 110px; /* ボタンの幅を200pxに設定 */
    height: 40px; /* ボタンの高さを50pxに設定 */
    background-color: #6495ED; /* 背景色を水色に設定 */
    color: #FFFFFF; /* 文字色を白に設定 */
    font-size: 16px; /* 文字サイズを16pxに設定 */
    border: none; /* ボーダーをなしに設定 */
    border-radius: 5px; /* 角を丸くする */
    cursor: pointer; /* カーソルをポインターに変更 */
    margin-left: 575px;

}
.managerlogin {
	margin-top : 20px;
	margin-left: 750px;
}
.footer {
    position: fixed;
    bottom: 0;
    left: 0;
    width: 100%;
    height: 15px;
    background: #7d7d7d;
    color: white;
    z-index: 1000;
}
.footer p {
    font-size: 12px; /* 文字サイズを12pxに設定 */
    margin: 0; /* デフォルトのマージンをリセット */
    padding: 0; /* デフォルトのパディングをリセット */
    line-height: 15px; /* 行の高さを15pxに設定 */
    text-align: center;
}
}



</style>

<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>まるごとシフトくん</title>
</head>

<div class="header">
    <div class="title">
        <h2>まるごとシフトくん</h2>
    </div>
</div>


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

	<div class="h2">
	<h2>従業員用ログイン</h2>
	</div>

	<!-- ログインＩＤ -->
	<div class="id">
	<label>ＩＤ</label>
	<input type="text" name="WORKER_ID" maxlength="10" placeholder="10文字以内の半角英数字でご入力下さい"
	 autocomplete="off" style="ime-mode: disabled" value="" required/><br>
	</div>

	<!-- パスワード -->
	<div class="pass">
	<label>パスワード</label>
	<input type="password" id="passwordField" name="WORKER_PASSWORD" maxlength="20" placeholder="20文字以内の半角英数字でご入力下さい" style="ime-mode: disabled" required/>
	</div>

	<!-- ストアＩＤ -->
	<!-- <label>STORE_ID</label>-->
	<input type="hidden" name="STORE_ID" placeholder="10文字以内の半角英数字でご入力下さい"value="" required/>

<!-- パスワード表示チェックボックス -->
<div class="box">
<label for="togglePassword">パスワードを表示</label>
<input id="togglePassword" type="checkbox" onclick="togglePasswordVisibility()">
</div>

<script>
function togglePasswordVisibility() {
    var passwordField = document.getElementById("passwordField");
    var togglePassword = document.getElementById("togglePassword");
    if (togglePassword.checked) {
        passwordField.type = "text";
    } else {
        passwordField.type = "password";
    }
}
</script>
	 <c:forEach var="error" items="${errors}">
	 <p class="error">${errors}</p>
	 </c:forEach>

	<!-- ログイン用ボタン -->
	<div class="login">
	<input type="submit" name="login" value="ログイン"/>
	</div>

	<!-- シフト作成者用ログインリンク -->
	<!-- <li><a href="LoginAction">シフト作成者用ログイン</a></li> -->
	<div class="managerlogin">
	<a href="ShiftLogin.action">シフト作成者用ログインページ</a>
	</div>


</form>

</form>

<div class="footer">
	<p>ＴＥＡＭ Ⅽ   Bug Busters</p>
</div>

</body>
</html>
