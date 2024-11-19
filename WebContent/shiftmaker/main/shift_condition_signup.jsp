<%-- 従業員シフト条件登録JSP --%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<c:import url="../../common/header.jsp"/>

<div class="body">
    <c:import url="../../common/navigation.jsp"/>

    <!-- 画面タイトル -->
    <h2>シフト条件登録</h2>

	<c:if test="${not empty workers}">
	    <form action="ShiftConditionSignupResult.action" method="post">
	        <table border="1">
	            <tr>
	                <th>ID</th>
	                <th>名前</th>
	                <th>ポジション</th>
	                <th>点数</th>
	            </tr>
	            <c:forEach var="worker" items="${workers}">
	                <tr>
	                    <!-- IDを表示 -->
	                    <td>
	                        <input type="text" name="worker_id" value="${worker.workerId}" readonly />
	                        <input type="hidden" name="worker_id" value="${worker.workerId}" />
	                    </td>
	                    <!-- 名前を表示 -->
	                    <td>
	                        <input type="text" name="worker_name" value="${worker.workerName}" readonly />
	                        <input type="hidden" name="worker_name" value="${worker.workerName}" />
	                    </td>

	                    <td>
	                        <select name="worker_position" required>
	                            <option value="kitchen">キッチン</option>
	                            <option value="hall">ホール</option>
	                            <!-- 必要に応じて選択肢を追加 -->
	                        </select>
	                    </td>
	                    <td>
	                        <input type="text" name="worker_score" placeholder="点数を入力" maxlength="1" required />
	                    </td>

	                </tr>
	            </c:forEach>
	        </table>
	        <input type="submit" value="登録" />
	    </form>
	</c:if>

</div>

</html>