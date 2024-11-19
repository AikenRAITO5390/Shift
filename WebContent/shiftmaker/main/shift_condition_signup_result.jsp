<%-- シフト条件登録完了JSP --%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<html>
	<c:import url="../../common/header.jsp"/>
<div class="body">
	<c:import url="../../common/navigation.jsp"/>
	    <h2>シフト条件登録結果</h2>

	    <!-- メッセージ表示 -->
	    <c:if test="${not empty message}">
	        <div style="padding: 10px; border: 1px solid #ccc; background-color: #f8f8f8; margin-bottom: 10px;">
	            ${message}
	        </div>
	    </c:if>

	    <!-- 他のコンテンツやリンクを配置 -->
	    <a href="Main.action">topへ</a>

</div>
</html>