<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>

<style>
login {
margin-top: 20px;
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
.logout_ok {
	margin-top : 60px;
	text-align : center;

}
body {
	margin-top: 70px;
  background: #f5f5f5 ;
  font-family: 'Open Sans', sans-serif;
}
.ok input[type="submit"]{
	margin-top : 62px;
	margin-left: 53%;
	background-color: #ff6347; /* 背景色を水色に設定 */
	color: white;
	width: 100px;
    height: 50px;
	border-radius: 5px; /* 角を丸くする */
	border: none; /* ボーダーをなしに設定 */
}
.no input[type="submit"]{
	margin-top : -52px;
	position: absolute;
    left: 50%; /* 画面の中央に配置 */
    transform: translateX(-50%) translateX(-90%); /* 中央から左に20%移動 */
	background-color: #6495ED; /* 背景色を水色に設定 */
	color: white;
	width: 100px;
    height: 55px;
	border-radius: 5px; /* 角を丸くする */
	border: none; /* ボーダーをなしに設定 */
}

.haikei {
    border: 2px solid #000; /* 2pxの黒い実線で囲む */
    background-color: #FFFFFF; /* 背景色を指定 */
    padding: 10px; /* 内側の余白を追加 */
    width: 100%; /* 親要素の幅を設定 */
    max-width: 550px; /* 最大幅を設定 */
     height: 400px;
    margin: 0 auto;
}

.inner-content {
    width: auto; /* 内側の要素の幅を自動に設定 */
}

</style>

<head>
    <title>managerログアウト</title>
</head>

<c:import url="../common/header.jsp"/>

<body>

<div class="haikei">
	<div class="inner-content">

<div class="login">
	<div class="login-triangle"></div>

	<h2 class="login-header">シフト作成者ログアウト</h2>

<div class="logout_ok">
    <h3>ログアウトしますか？</h3>
</div>


<div class="ok">
    <form action="WorkerLogoutExecute.action" method="post">
        <input type="submit" value="はい">
    </form>
</div>

<div class="no">
    <form action="/shift/shiftmaker/main/Main.action" method="post">
        <input type="submit" value="いいえ">
    </form>
</div>

</div>
</div>
<c:import url="../common/footer.jsp"/>

</body>
</html>