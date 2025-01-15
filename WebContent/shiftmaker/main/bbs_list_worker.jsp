<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>

<style>
.h1 h1{
	margin-top : 60px;
	text-align : center;
}
.date{
	margin-top: -45px;
	margin-left: 460px;
	margin-right: 20px;
}
.delete{
	margin-top : 25px;
	margin-left: 480px;
	margin-bottom : 10px;
}
.name{
	margin-top : 8px;
	margin-left: 60px;
}
.text{
	margin-top : 2px;
	margin-left: 60px;
}
.toukou{
	border: 1px solid ; /* 枠線を設定 */
	background-color: #EEEEEE; /* 背景色を設定 */
    padding: 2px; /* 枠線と内容の間にスペースを追加 */
    border-radius: 5px; /* 角を丸くする */
    margin-top : 10px;
    display: inline-block; /*文字の幅だけ線をひく*/
    margin-left: 180px;
    width: 60%; /* 画面の幅いっぱいに広げる */
}
.create{
  position: fixed; /* 要素を固定 */
  top: 400px; /* 画面の上部に配置 */
  left: 805px; /* 画面の左端に配置 */
  width: 11%; /* 画面の幅いっぱいに広げる */
  border: 1px solid ; /* 枠線を設定 */
  background-color: #75A9FF; /* 背景色を設定 */
  border-radius: 5px; /* 角を丸くする */
  display: inline-block; /*文字の幅だけ線をひく*/
  color: white; /* テキストの色を設定 */
  padding: 5px; /* 内側の余白を設定 */
  z-index: 1000; /* 他の要素の上に表示 */
}
.modoru{
  position: fixed; /* 要素を固定 */
  top: 400px; /* 画面の上部に配置 */
  left: 929px; /* 画面の左端に配置 */
  width: 3%; /* 画面の幅いっぱいに広げる */
  border: 1px solid ; /* 枠線を設定 */
  background-color: #75A9FF; /* 背景色を設定 */
  border-radius: 5px; /* 角を丸くする */
  display: inline-block; /*文字の幅だけ線をひく*/
  color: white; /* テキストの色を設定 */
  padding: 5px; /* 内側の余白を設定 */
  z-index: 1000; /* 他の要素の上に表示 */
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
    <meta charset="UTF-8">
    <title>掲示板</title>
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

	<div class="h1">
    	<h1>掲示板</h1>
	</div>

<div class="tabel">
    <ul>
        <c:forEach var="message" items="${messages}">

			<div class="toukou">
                <!-- <strong>投稿者:</strong> -->

				<div class="name">
                <c:choose>
                    <c:when test="${not empty message.worker.workerName}">
                        ${message.worker.workerName}
                    </c:when>
                    <c:when test="${not empty message.store.managerName}">
                        ${message.store.managerName}
                    </c:when>
                </c:choose>
                </div>


				<div class="text">${message.bbsText} <br></div>
                <div class="date"> ${message.bbsDate}<br></div>
				<div class="delete">
                	<c:choose>
						<c:when test="${message.worker.workerId == WorkerLogin_Name}">
   							<a href="BbsDeleteWorker.action?Worker_ID=${WorkerLogin_Name}&BBS_ID=${message.bbsId}">削除</a>
						</c:when>
					</c:choose>
				</div>

			</div>
        </c:forEach>
    </ul>
</div>

<div class="create">
    <a href="BbsCreateWorker.action">投稿を作成する</a>
</div>

<div class="modoru">
    <a href="MainWork.action">戻る</a>
</div>

<div class="footer">
	<p>ＴＥＡＭ Ⅽ   Bug Busters</p>
</div>

</body>
</html>