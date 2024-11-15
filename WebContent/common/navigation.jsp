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
        h3 {
            color: white;
        }
        ul, li {
        color: white;
   		 }
    	ul li a {
        color: white;
        text-decoration: none;
   		 }
    	ul li a:hover, ul li a:focus {
        color: blue;
   		 }
    </style>
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
        <li><a href="#">変更</a></li>
    </ul>
</div>

<ul>
	<li><a href="#" onclick="showLinks('Shift_conditions')">💪　シフト</a></li>
</ul>
<div id="Shift_conditions" class="links" style="display:none;">
    <ul>
        <li><a href="#">変更</a></li>
        <li><a href="#">時間設定</a></li>
        <li><a href="#">パワーバランス設定</a></li>
    </ul>
</div>

<ul>
	<li><a href="#" onclick="showLinks('Worker')">👬　従業員</a></li>
</ul>
<div id="Worker" class="links" style="display:none;">
   <ul>
        <li><a href="#">作成</a></li>
        <li><a href="#">編集</a></li>
        <li><a href="#">閲覧</a></li>
        <li><a href="#">削除</a></li>
    </ul>
</div>


<ul>
	<li><a href="#" onclick="showLinks('BBS')">📝　掲示板</a></li>
</ul>
<div id="BBS" class="links" style="display:none;">
   <ul>
        <li><a href="#">登録</a></li>
    </ul>
</div>

<ul>
	<li><a href="#" onclick="showLinks('Worker')">💰　売上</a></li>
</ul>
<div id="Worker" class="links" style="display:none;">
   <ul>
        <li><a href="#">売上</a></li>
    </ul>
</div>
