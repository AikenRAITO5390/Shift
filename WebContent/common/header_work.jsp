<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<style>
.sidebar {
    display: none;
     background-color: #7d7d7d;
    width: 200px;
    height: 100%;
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
.header_work  h2{
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

button {
    background-color: #75A9FF; /* ボタンの背景色を設定 */
    color: white; /* ボタンのテキスト色を設定 */
    padding: 10px 10px; /* ボタンの内側の余白を設定 */
    border: none; /* ボタンの枠線をなしに設定 */
    border-radius: 5px; /* ボタンの角を丸くする */
    cursor: pointer; /* カーソルをポインタに変更 */
}

button:hover {
    background-color: #005FFF; /* ホバー時の背景色を設定 */
}
</style>
<script>
    function toggleSidebar() {
        var sidebar = document.getElementById("sidebar");
        if (sidebar.style.display === "none" || sidebar.style.display === "") {
            sidebar.style.display = "block";
        } else {
            sidebar.style.display = "none";
        }
    }
</script>

<div class="header_work">
<div style="display: flex; align-items: center;">
    <button onclick="toggleSidebar()">三</button>
    <div id="sidebar" class="sidebar">
        <jsp:include page="../common/navigation_work.jsp" />
    </div>
<h2> まるごとシフトくん</h2>
</div>
    <div class="ushiro">
        <a href="../LogoutWorker.action">ログアウト</a>
        <p>${WorkerName}さん</p>
    </div>
</div>
