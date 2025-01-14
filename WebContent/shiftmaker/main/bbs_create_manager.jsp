<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>

<style>
.date1{
    margin-top : -50px;
    margin-left: 820px;
}
.name1{
    margin-left: 20px;
    margin-top : 15px;
}
.text1 textarea{
	margin-top : -10px;
    margin-left: 20px;
    width: 900px; /* 画面の幅いっぱいに広げる */
    height: 320px;
}
.create1{
	border: 1px solid ; /* 枠線を設定 */
	background-color: #EEEEEE; /* 背景色を設定 */
    padding: 2px; /* 枠線と内容の間にスペースを追加 */
    border-radius: 5px; /* 角を丸くする */
    margin-top : 70px;
    display: inline-block; /*文字の幅だけ線をひく*/
    margin-left: 10px;
    width: 97%; /* 画面の幅いっぱいに広げる */
    height: 400px;
}


</style>

<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>まるごとシフトくん</title>
</head>

<c:import url="../../common/header.jsp"/>

<body>
<!-- magager用！！！ -->
    <form action="BbsCreateExecute.action" method="post">

    <div class="create1">

        <div class="name1">
        <label for="manager_id">投稿者:</label>
        <label>${UserName}</label>
        <input type="hidden" name="WORKER_NAME" value="${UserName}"  readonly required><br>
        <input type="hidden" name="MANAGER_ID" value="${ManagerId}"  readonly required><br>
        </div>

        <!-- <label for="store_id">店舗ID:</label> -->
        <input type="hidden" name="STORE_ID" value="${StoreId}" readonly required><br>

        <div class="date1">
        <label for="bbs_date"></label>
        <label>${today}</label>
        <input type="hidden" id="bbs_date" name="BBS_DATE" value="${today}" readonly><br><br>
        </div>

        <div class="text1">
        <label for="bbs_text"></label>
        <textarea id="bbs_text" name="BBS_TEXT" rows="4" cols="50" required placeholder="メッセージを入力"></textarea><br><br>
        </div>

   </div>
        <input type="submit" value="投稿">
        <a href="BBS.action">戻る</a>
    </form>

<c:import url="../../common/footer.jsp"/>

</body>
</html>