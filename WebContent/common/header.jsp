<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<div>
    <h1>まるごとシフト君</h1>
</div>
<c:if test="${not empty user}">
    <div>
        <span>${user.name}様</span><br>
        <a href="../Logout.action">ログアウト</a>
    </div>
</c:if>
<c:if test="${empty user}">
    <div>
        <span>ユーザーが設定されていません</span>
    </div>
</c:if>