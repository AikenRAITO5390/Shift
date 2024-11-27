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
}
.navigation ul, .navigation li {
    color: white !important;
}
.navigation ul li a {
    color: white !important;
    text-decoration: none;
}
.navigation ul li a:hover, .navigation ul li a:focus {
    color: blue !important;
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
        <li><a href="#">作成</a></li>
        <li><a href="#">編集</a></li>
        <li><a href="#">閲覧</a></li>
        <li><a href="#">削除</a></li>
    </ul>
</div>

<ul>
	<li><a href="#" onclick="showLinks('Maneger')">👦　シフト作成者</a></li>
</ul>
<div id="Maneger" class="links" style="display:none;">
    <ul>
         <li><a href="ShiftManagerSignUp.action">変更</a></li>
    </ul>
</div>

<ul>
	<li><a href="#" onclick="showLinks('Shift_conditions')">💪　シフト</a></li>
</ul>
<div id="Shift_conditions" class="links" style="display:none;">
    <ul>
        <li><a href="ShiftConditionWorkerList.action">変更</a></li>
        <li><a href="ShiftTimeSignup.action">時間設定</a></li>
        <li><a href="PowerSetting.action">パワーバランス設定</a></li>
    </ul>
</div>

<ul>
	<li><a href="#" onclick="showLinks('Worker')">👬　従業員</a></li>
</ul>
<div id="Worker" class="links" style="display:none;">
   <ul>
        <li><a href="WorkerSignUp.action">登録</a></li>
        <li><a href="WorkerList.action">編集</a></li>
        <li><a href="WorkerList.action">閲覧</a></li>
        <li><a href="WorkerList.action">削除</a></li>
    </ul>
</div>


<ul>
	<li><a href="#" onclick="showLinks('BBS')">📝　掲示板</a></li>
</ul>
<div id="BBS" class="links" style="display:none;">
   <ul>
        <li><a href="BBS.action">登録</a></li>
    </ul>
</div>

<ul>
	<li><a href="#" onclick="showLinks('Salse')">💰　売上</a></li>
</ul>
<div id="Salse" class="links" style="display:none;">
   <ul>
        <li><a href="Sales.action">売上</a></li>
    </ul>
</div>
</div>
