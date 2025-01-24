<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<style>
.date1{
    margin-top : -60px;
    margin-left: 85%;
}
.name1{
    margin-left: 20px;
    margin-top : 15px;
}
.text1 textarea{

	margin-top : -8%;
    margin-left: 20px;
    width: 95%; /* 画面の幅いっぱいに広げる */
    height: 320px;
}
.create1{
 	 z-index: 0;
	border: 1px solid ; /* 枠線を設定 */
	background-color: #EEEEEE; /* 背景色を設定 */
    padding: 2px; /* 枠線と内容の間にスペースを追加 */
    border-radius: 5px; /* 角を丸くする */
    margin-top : 70px;
    display: inline-block; /*文字の幅だけ線をひく*/
    margin-left: 10px;
    width: 97%; /* 画面の幅いっぱいに広げる */
    height: 400px;
     position: relative;
}


 .submit input[type="submit"] {
   z-index: 1;
	margin-top : -80px !important;
	margin-left: 70%;
    background-color: #2C7CFF;
    color: white;
    border: 1px solid white;
    padding: 10px 20px; /* ボタンの内側の余白を調整 */
    border: 1px solid white; /* 白い枠線を設定 */
    font-size: 20px; /* フォントサイズを調整 */
    border-radius: 4px; /* 角を丸くする */
    width: 160px;
     height: 60px;
    position: absolute;

}

textarea {
    resize: none; /* リサイズ機能を無効にする */
}

</style>

<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>まるごとシフトくん</title>
</head>

<c:import url="../../common/header_work.jsp"/>

<body>

    <form action="BbsCreateExecuteWorker.action" method="post">


	<div class="create1">

		<div class="name1">
        <label for="worker_id">投稿者:</label>
        <label>${UserName}</label>
        <input type="hidden" name="WORKER_NAME" value="${UserName}"  readonly required><br><br>
        <input type="hidden" name="WORKER_ID" value="${WorkerId}"  readonly required><br><br>
        </div>

        <!-- <label for="store_id">店舗ID:</label>-->
        <input type="hidden" name="STORE_ID" value="${StoreId}" readonly required><br><br>

        <div class="date1">
        <label for="bbs_date">日付:</label>
        <label>${today}</label>
        <input type="hidden" id="bbs_date" name="BBS_DATE" value="${today}" readonly><br><br>
        </div>

        <div class="text1">
        <label for="bbs_text"></label>
        <textarea id="bbs_text" name="BBS_TEXT" rows="4" cols="50" required placeholder="メッセージを入力"></textarea><br><br>
        </div>

	</div>
	<div class ="submit">
		<a href="BBSWorker.action">掲示板へ戻る</a>
        <input type="submit" value="投稿">

        </div>
    </form>

<c:import url="../../common/footer.jsp"/>

</body>
</html>