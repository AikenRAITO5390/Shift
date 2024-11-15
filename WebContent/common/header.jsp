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
            top: 0;
            padding: 10px;
            box-shadow: 2px 0 5px rgba(0,0,0,0.1);
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

    <button onclick="toggleSidebar()">三</button>
    <div id="sidebar" class="sidebar">
        <jsp:include page="../common/navigation.jsp" />
    </div>