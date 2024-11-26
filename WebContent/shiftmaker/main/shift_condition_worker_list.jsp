<%-- 従業員シフト条件一覧JSP --%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!-- 下記の一文の宣言がないとJSTLを使用できない -->
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<c:import url="../../common/header.jsp"/>
<body>


    <h2>シフト条件変更</h2>

    <c:if test="${not empty workers}">
        <table  class="table table-hover">
            <tr>
                <th>ID</th>
                <th>名前</th>
                <th>ポジション</th>
                <th>点数</th>
            </tr>
            <c:forEach var="worker" items="${workers}">
            	<c:if test="${!worker.workerJudge}">
	                <tr>
	                    <td>${worker.workerId}</td>
	                    <td>${worker.workerName}</td>
	                    <td>${positionMapping[worker.workerPosition]}</td> <!-- ポジションの日本語化 -->
	                    <td>${worker.workerScore}</td>
	                    <td><a href="ShiftConditionEdit.action?workerId=${worker.workerId}">変更</a></td>
	                </tr>
                </c:if>
            </c:forEach>
        </table>
    </c:if>


    <a href="Main.action">メインへ戻る</a>

</body>

<c:import url="../../common/footer.jsp"/>
</html>