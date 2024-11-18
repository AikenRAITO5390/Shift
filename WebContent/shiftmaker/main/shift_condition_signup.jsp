<%-- 従業員シフト条件登録JSP --%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<c:import url="./common/header.jsp"/>

<div class="body">
    <c:import url="./common/navigation.jsp"/>

    <!-- 画面タイトル -->
    <h2>シフト条件登録</h2>

    <form action="ShiftConditionSignUpResult.action" method="post">

        <!-- ID表示（変更不可） -->
        <label>ID</label>
        <input type="text" name="worker_id" value="${worker.workerId}" disabled />
        <input type="hidden" name="worker_id" value="${worker.workerId}" /> <!-- hiddenで送信用 -->

        <!-- 名前表示（変更不可） -->
        <label>名前</label>
        <input type="text" name="worker_name" value="${worker.workerName}" disabled />
        <input type="hidden" name="worker_name" value="${worker.workerName}" /> <!-- hiddenで送信用 -->

        <!-- ポジション選択 -->
        <label>ポジション</label>
        <select name="worker_position" required>
            <option value="kitchen">キッチン</option>
            <option value="hole">ホール</option>
            <!-- 必要に応じて選択肢を追加 -->
        </select>

        <!-- 点数入力（星形わかんないので、いったん入力式にしてます） -->
        <label>点数</label>
        <input type="text" name="worker_score" placeholder="点数を入力してください" maxlength="5" required />

        <!-- 登録ボタン -->
        <input type="submit" value="登録" />

    </form>

</div>

</html>