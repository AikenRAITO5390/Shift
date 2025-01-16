<%-- 従業員シフト条件登録JSP --%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<!-- bootstrapと繋げる -->
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-9ndCyUaIbzAi2FUVXJi0CjmCapSmO7SnpJef0486qhLnuZ2cdeRhO02iuK6FUUVM" crossorigin="anonymous">
<!-- css -->
<link rel="stylesheet" href="../../css/style_k.css">

<html>
<c:import url="../../common/header.jsp"/>

<div class="body">


    <!-- 画面タイトル -->
    <div class="shift_condtion_signup_h2">
    	<h2>シフト条件登録</h2>
    </div>

	<!-- アルバイトのみ設定画面表示 -->
	<c:if test="${not empty workers}">
	    <form action="ShiftConditionSignupResult.action" method="post">
	    <div class="shift_condtion_signup_table">
	        <table>
	            <tr>
	                <th>ID</th>
	                <th>名前</th>
	                <th>ポジション</th>
	                <th>点数</th>
	            </tr>
	            <c:forEach var="worker" items="${workers}">
	                <tr>
	                    <!-- IDを表示 -->
	                    <div class="shift_condtion_signup_table_label">
	                    <td>
	                    	<p>${worker.workerId}</p>
	                        <input type="hidden" name="worker_id" value="${worker.workerId}" readonly />
	                        <input type="hidden" name="worker_id" value="${worker.workerId}" />
	                    </td>
	                    <!-- 名前を表示 -->
	                    <td>
	                    	<p>${worker.workerName}</p>
	                        <input type="hidden" name="worker_name" value="${worker.workerName}" readonly />
	                        <input type="hidden" name="worker_name" value="${worker.workerName}" />
	                    </td>
	                    </div>

	                    <td>
	                        <select name="worker_position" required>
	                            <option value="kitchen">キッチン</option>
	                            <option value="hall">ホール</option>
	                            <!-- 必要に応じて選択肢を追加 -->
	                        </select>
	                    </td>
	                    <td>
	                        <input type="number" name="worker_score" placeholder="点数を入力" min="1" max="5" required />
	                    </td>

	                </tr>

	            </c:forEach>
	        </table>
	        </div>

	        <div class="shift_condtion_signup_input">
	        <input type="submit" value="登録" />
	        </div>

	        <div class="shift_condtion_signup_input_back">
	        <a href="Main.action">トップページへ戻る</a>
	        </div>

	    </form>
	</c:if>

	<!-- 社員の場合は、すぐtopへ -->
	<c:if test="${empty workers}">
		<div>社員は設定不要です。</div>
		<a href="Main.action">topへ</a>
	</c:if>

</div>

<c:import url="../../common/footer.jsp"/>

</html>