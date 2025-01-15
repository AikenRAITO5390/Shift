<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<style>
.date1{
    margin-top : -110px;
    margin-left: 820px;
    margin-bottom: 40px;
}
.name1{
    margin-left: 20px;
    margin-top : 15px;
}
.text1 textarea{
	margin-top : -30px;
    margin-left: 20px;
    width: 900px; /* 画面の幅いっぱいに広げる */
    height: 320px;
}
.create1{
	border: 1px solid ; /* 枠線を設定 */
	background-color: #EEEEEE; /* 背景色を設定 */
    padding: 2px; /* 枠線と内容の間にスペースを追加 */
    border-radius: 5px; /* 角を丸くする */
    margin-top : 70px;
    display: inline-block; /*文字の幅だけ線をひく*/
    margin-left: 10px;
    width: 97%; /* 画面の幅いっぱいに広げる */
    height: 400px;
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
.header_work {
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
	color: white;
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
</style>

<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>まるごとシフトくん</title>
</head>

<div class="header_work">
    <button onclick="toggleSidebar()">三</button>
    <div id="sidebar" class="sidebar">
        <jsp:include page="../../common/navigation_work.jsp" />
    </div>
<h2> まるごとシフトくん</h2>
    <div class="ushiro">
        <a href="../Logout.action">ログアウト</a>
        <p>${WorkerName}さん</p>
    </div>
</div>

<body>

    <form action="BbsCreateExecuteWorker.action" method="post">


	<div class="create1">

		<div class="name1">
        <label for="worker_id">投稿者ID:</label>
        <label>${UserName}</label>
        <input type="hidden" name="WORKER_NAME" value="${UserName}"  readonly required><br><br>
        <input type="hidden" name="WORKER_ID" value="${WorkerId}"  readonly required><br><br>
        </div>

        <!-- <label for="store_id">店舗ID:</label>-->
        <input type="hidden" name="STORE_ID" value="${StoreId}" readonly required><br><br>

        <div class="date1">
        <label for="bbs_date"></label>
        <label>${today}</label>
        <input type="hidden" id="bbs_date" name="BBS_DATE" value="${today}" readonly><br><br>
        </div>

        <div class="text1">
        <label for="bbs_text"></label>
        <textarea id="bbs_text" name="BBS_TEXT" rows="4" cols="50" required placeholder="メッセージを入力"></textarea><br><br>
        </div>

	</div>
        <input type="submit" value="投稿">
        <a href="BBSWorker.action">戻る</a>
    </form>

<div class="footer">
	<p>ＴＥＡＭ Ⅽ   Bug Busters</p>
</div>

</body>
</html>