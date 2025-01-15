<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<style>
.delete {
	margin-top : 60px;
	text-align : center;
}
.p {
	margin-top : 120px;
	margin-left : 420px;
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
.header h2{
color: white;}

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
</style>

<head>
    <meta charset="UTF-8">
    <title>掲示板削除確認</title>
</head>

<div class="header">
    <div style="display: flex; align-items: center;">
        <button onclick="toggleSidebar()">三</button>
        <div id="sidebar" class="sidebar">
        <jsp:include page="../../common/navigation.jsp" />
    </div>
        <h2>まるごとシフトくん</h2>
    </div>
    <div class="ushiro">
        <a href="../Logout.action">ログアウト</a>
        <p>${managerName}さん</p>
    </div>
</div>

<body>

<div class="delete">
    <h1>削除確認</h1>
</div>
    <p>投稿者: ${user}</p>
    <p>コメント: ${text}</p>
    <p>コメント: ${BBS_ID}</p>

	<div class="p">
    	<p>この投稿を削除しますか？</p>
    </div>
    <form action="BbsDeleteExecute.action" method="post">
        <input type="hidden" name="bbsId" value="${BBS_ID}">
        <input type="submit" value="削除">
    </form>

<div class="footer">
	<p>ＴＥＡＭ Ⅽ   Bug Busters</p>
</div>

</body>
</html>