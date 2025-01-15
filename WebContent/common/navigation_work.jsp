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
.navigation_work h3 {
    color: white !important;
}
.navigation_work ul, .navigation li {
    color: white !important;
}
.navigation_work ul li a {
    color: white !important;
    text-decoration: none;
    font-family: 'Segoe UI Emoji', 'Apple Color Emoji', 'Noto Color Emoji', sans-serif;
}
.navigation_work ul li a:hover, .navigation ul li a:focus {
    color: blue !important;
}

    </style>
<div class="navigation_work">
<h3>まるごとシフトくん</h3>
<ul>
<li><a href="MainWork.action">🏡　Top</a></li>
</ul>

<ul>
    <li><a href="#" >📜　シフト閲覧</a></li>
</ul>

<ul>
	<li><a href="BBSWorker.action">📝　掲示板</a></li>
</ul>

<ul>
	<li><a href="ShiftWorkerSignUp.action">💪　従業員情報登録</a></li>
</ul>
</div>

