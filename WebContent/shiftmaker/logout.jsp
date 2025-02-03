<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>

<style>
.h2 h2{
	margin-top : 60px;
	text-align : center;
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
.login {
    margin-top : 60px;
	text-align : center;
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

<form action = "WorkerLogoutExecute.action" method="post">

<div class="h2">
<h2>ログアウト完了</h2>
</div>

<div class="login">
<a href="Login.action">ログインページへ</a>
</div>
</form>

<div class="footer">
	<p>ＴＥＡＭ Ⅽ   Bug Busters</p>
</div>

</body>
</html>
