<%-- サイドバー --%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<script>
function showLinks(id) {
    var element = document.getElementById(id);
    if (element.style.display === "none" || element.style.display === "") {
        element.style.display = "block";
    } else {
        element.style.display = "none";
    }
}

function hideLinks(id) {
    var element = document.getElementById(id);
    element.style.display = "none";
}
</script>
<style>
/* 修正後のCSS */
.navigation h3 {
    color: white !important;
    font-size: 22px;
}
.navigation ul, .navigation li {
    color: white !important;
}
.navigation ul li a {
    color: white !important;
    text-decoration: none;
    font-family: 'Segoe UI Emoji', 'Apple Color Emoji', 'Noto Color Emoji', sans-serif;
}
.navigation ul li a:hover, .navigation ul li a:focus {
    color: blue !important;
}
.navigation ul a{
    color: white !important;
    margin-left: 15px;
}
.navigation li a{
    color: white !important;
    margin-left: -20px;
}
/*スクロールバー出したかった！！*/
.navigation {
	height: 510px; /* 必要に応じて調整 */
    overflow-y: scroll; /* 縦方向のスクロールバーを表示 */
    width: calc(100% -10px); /* 余白を防ぐ */
    box-sizing: border-box; /* パディングとボーダーを含めた全体の幅と高さを指定 */
    overflow-x: hidden; /* 横方向のスクロールバーを非表示 */
    max-width: 100%;  /*最大幅を指定して余分なスペースを防ぐ*/
}

</style>
<div class="navigation">
<h3>まるごとシフトくん</h3>
<ul>
<li><a href="Main.action">🏡　Top</a></li>
</ul>

<ul>
    <li><a href="#" onclick="showLinks('Shift')">📜　シフト</a></li>
</ul>
<div id="Shift" class="links" style="display:none;">
    <ul>
        <a href="ShiftSelect.action">作成</a><br>
        <a href="ShiftEdit.action">編集</a><br>
        <a href="ShiftChoose.action">閲覧</a><br>
        <a href="ShiftDelete.action">削除</a><br>
    </ul>
</div>

<ul>
	<li><a href="#" onclick="showLinks('Maneger')">👦　シフト作成者</a></li>
</ul>
<div id="Maneger" class="links" style="display:none;">
    <ul>
         <a href="ShiftManagerSignUp.action">変更</a>
    </ul>
</div>

<ul>
	<li><a href="#" onclick="showLinks('Shift_conditions')">💪　シフト条件</a></li>
</ul>
<div id="Shift_conditions" class="links" style="display:none;">
    <ul>
        <a href="ShiftConditionWorkerList.action">変更</a><br>
        <a href="ShiftTimeSignup.action">時間設定</a><br>
        <a href="PowerSetting.action">パワーバランス設定</a><br>
    </ul>
</div>

<ul>
	<li><a href="#" onclick="showLinks('Worker')">👬　従業員</a></li>
</ul>
<div id="Worker" class="links" style="display:none;">
   <ul>
        <a href="WorkerSignUp.action">登録</a><br>
        <a href="WorkerList.action">編集・削除</a><br>
        <a href="WorkerDate.action">閲覧</a><br>
    </ul>
</div>


<ul>
	<li><a href="#" onclick="showLinks('BBS')">📝　掲示板</a></li>
</ul>
<div id="BBS" class="links" style="display:none;">
   <ul>
        <a href="BBS.action">登録</a>
    </ul>
</div>

<ul>
	<li><a href="#" onclick="showLinks('Salse')">💰　売上</a></li>
</ul>
<div id="Salse" class="links" style="display:none;">
   	<ul>
        <a href="Sales.action">売上</a>
    </ul>
</div>
</div>
