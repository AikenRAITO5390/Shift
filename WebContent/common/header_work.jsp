<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<style>
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
    justify-content: flex-start; /* 左側に配置 */
}
.header_work h2{
    color: white;
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
    <button onclick="toggleSidebar()">三</button>
    <div id="sidebar" class="sidebar">
        <jsp:include page="../common/navigation_work.jsp" />
    </div>
<h2> まるごとシフトくん</h2>
</div>
