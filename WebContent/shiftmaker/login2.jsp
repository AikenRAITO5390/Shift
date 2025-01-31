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
.error {
	text-align : center;
}


.login input[type="submit"] {
    width: 110px; /* ボタンの幅を110pxに設定 */
    height: 40px; /* ボタンの高さを40pxに設定 */
    background-color: #6495ED; /* 背景色を水色に設定 */
    color: #FFFFFF; /* 文字色を白に設定 */
    font-size: 16px; /* 文字サイズを16pxに設定 */
    border: none; /* ボーダーをなしに設定 */
    border-radius: 5px; /* 角を丸くする */
    cursor: pointer; /* カーソルをポインターに変更 */
    margin-left: 47%;

}

.login button {
    width: 150px; /* ボタンの幅を110pxに設定 */
    height: 40px; /* ボタンの高さを40pxに設定 */
    background-color: #6495ED; /* 背景色を水色に設定 */
    color: #FFFFFF; /* 文字色を白に設定 */
    font-size: 16px; /* 文字サイズを16pxに設定 */
    border: none; /* ボーダーをなしに設定 */
    border-radius: 5px; /* 角を丸くする */
    cursor: pointer; /* カーソルをポインターに変更 */
    margin-top:10px;

}
.managerlogin {
	margin-top : 20px;
	margin-left: 58%;

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

body {
	margin-top: 70px;
  background: #f5f5f5 ;
  font-family: 'Open Sans', sans-serif;
}
.login {
  width: 500px;
  margin: 16px auto;
  font-size: 16px;
}
/* Reset top and bottom margins from certain elements */.login-header,.login p {
  margin-top: 0;
  margin-bottom: 0;
}
/* The triangle form is achieved by a CSS hack */.login-triangle {
  width: 0;
  margin-right: auto;
  margin-left: auto;
  border: 12px solid transparent;
  border-bottom-color: #28d;
}
.login-header {
  background: #28d;
  padding: 20px;
  font-size: 1.4em;
  font-weight: normal;
  text-align: center;
  text-transform: uppercase;
  color: #fff;
}

.login input {
  box-sizing: border-box;
  display: block;
  width: 100%;
  border-width: 1px;
  border-style: solid;
  padding: 16px;
  outline: 0;
  font-family: inherit;
  font-size: 0.95em;
}

.haikei {
    border: 2px solid #000; /* 2pxの黒い実線で囲む */
    background-color: #FFFFFF; /* 背景色を指定 */
    padding: 10px; /* 内側の余白を追加 */
    width: 100%; /* 親要素の幅を設定 */
    max-width: 600px; /* 最大幅を設定 */
     height: 420px;
    margin: 0 auto;
}

.inner-content {
    width: auto; /* 内側の要素の幅を自動に設定 */
}


 .hidden { display: none; }

.form-group {
    display: flex;
    align-items: center; /* ラベルと入力フィールドを縦方向に中央揃え */
}

.form-group label {
    margin-right: 10px; /* ラベルと入力フィールドの間に余白を追加 */
    font-size: 20px;
    margin-top: -12px;
}

.id input[type="text"]{
	 width: 400px;
}

.id a{
	margin-top: 20%;
	margin-left: 250px;
	font-size: 15px;
}

.id button{
	margin-top: 30px;
	margin-left:400px;
	 background-color: #6495ED; /* ボタンの背景色 */
    color: #fff; /* テキストの色 */
    padding: 10px 20px; /* 内側の余白 */
    font-size: 16px; /* フォントサイズ */
    cursor: pointer; /* カーソルをポインタに変更 */
    border-radius: 5px;
    text-align: center;
    width: 100px;
}

.pass input[type="password"], input[type="text"] {
	 width: 350px;
	 margin-bottom: 15px;
}

.pass a{
	margin-top: 20%;
	margin-left: 250px;
	font-size: 15px;
}

.pass input[type="submit"]{
	margin-top: -30px;
	margin-left:400px;
	 background-color: #6495ED; /* ボタンの背景色 */
    color: #fff; /* テキストの色 */
    padding: 10px 20px; /* 内側の余白 */
    font-size: 16px; /* フォントサイズ */
    cursor: pointer; /* カーソルをポインタに変更 */
    border-radius: 5px;
    text-align: center;
    width: 100px;
}

.box label {
 	margin-top: 10px;
	margin-left: 100px;
    white-space: nowrap; /* ラベルのテキストを折り返さない */
}

.box input[type="checkbox"] {
    margin-top: -20px; /* チェックボックスの左余白を追加 */
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
<div class="haikei">
	<div class="inner-content">

<form action = "ShiftLoginExecute.action" method="post">



	<div class="login">
	<div class="login-triangle"></div>

	<h2 class="login-header">シフト作成者Login</h2>

	 <div id="idDiv">
	 <div class="id">
		<h3>☆ログインIDを入力してください</h3>
		<div class="form-group">
            <label>ＩＤ:</label>
           <input type="text" name="MANAGER_ID" maxlength="10" placeholder="10文字以内の半角英数字でご入力下さい"
	 			autocomplete="off" style="ime-mode: disabled" value="" required/></div>

           <a href="Login.action">従業員ログインへ</a>
            <button type="button" onclick="showEmailForm()">次へ</button></div>
     </div>

     <div id="emailDiv" class="hidden">
     	<div class="pass">
     	<h3>☆パスワードを入力してください</h3>
     	<div class="form-group">
            <label>パスワード:</label>
            <input type="password" id="passwordField" name="PASSWORD" maxlength="20" placeholder="20文字以内の半角英数字でご入力下さい" style="ime-mode: disabled" required/></div>
            	<div class="box">
				  <label for="togglePassword">パスワードを表示</label>
                            <input id="togglePassword" type="checkbox" onclick="togglePasswordVisibility()">
                        </div>

					<a href="Login.action">従業員ログインへ</a>
			<div class="login">
			<button type="button" onclick="showIdForm()">ID入力画面に戻る</button>
			<input type="submit" name="login" value="ログイン"/>

	</div>
	</div>
	</div>





	<!-- ストアＩＤ -->
	<!-- <label>STORE_ID</label>-->
	<input type="hidden" name="STORE_ID" placeholder="10文字以内の半角英数字でご入力下さい"value="" required/>

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

function showEmailForm() {
    document.getElementById('idDiv').classList.add('hidden');
    document.getElementById('emailDiv').classList.remove('hidden');
}

function showIdForm() {
    document.getElementById('emailDiv').classList.add('hidden');
    document.getElementById('idDiv').classList.remove('hidden');
}
</script>
	 <c:forEach var="error" items="${errors}">
	 <p class="error">${errors}</p>
	 </c:forEach>



</div>
</div>
</form>
</form>


<div class="footer">
	<p>ＴＥＡＭ Ⅽ   Bug Busters</p>
</div>

</body>
</html>
