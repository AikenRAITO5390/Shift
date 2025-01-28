<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>

<style>
.logout {
	margin-top : 60px;
	text-align : center;
}
.logout_ok {
	margin-top : 60px;
	text-align : center;
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
.sidebar {
    display: none;
    width: 200px;
    height: 100%;
    background-color: #7d7d7d;
    position: fixed;
    left: 0;
    top: 50px;
    padding: 10px;
    box-shadow: 2px 0 5px rgba(0,0,0,0.1);
}

.ushiro {
    display: flex;
    flex-direction: column; /* 縦方向に配置 */
    align-items: flex-end; /* 右端に配置 */
    justify-content: center; /* 垂直方向に中央揃え */
    height: 100%; /* 親要素の高さを継承 */
     padding-right: 15px;
}

.ushiro a,
.ushiro p {
    margin: 0; /* デフォルトのマージンをリセット */
    padding: 2px 0; /* 上下のパディングを設定 */

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

</style>


<head>
    <title>ログアウト</title>
</head>


<div class="header">
    <div style="display: flex; align-items: center;">
        <button onclick="toggleSidebar()">三</button>
        <div id="sidebar" class="sidebar">
        <jsp:include page="../common/navigation_work.jsp" />
    </div>
        <h2>まるごとシフトくん</h2>
    </div>
    <div class="ushiro">
        <a href="../LogoutWorker.action">ログアウト</a>
        <p>${WorkerName}さん</p>
    </div>
</div>


<body>

<div class="logout">
	<h2>ログアウト</h2>
</div>

<div class="logout_ok">
    <h3>ログアウトしますか？</h3>
</div>

    <div class="ok">
    <form action="WorkerLogoutExecute.action" method="post">
        <input type="submit" value="はい">
    </form>
    </div>

<div class="no">
    <form action="/shift/shiftmaker/main/MainWork.action" method="post">
        <input type="submit" value="いいえ">
    </form>
    </div>

<div class="footer">
	<p>ＴＥＡＭ Ⅽ   Bug Busters</p>
</div>
</body>
</html>