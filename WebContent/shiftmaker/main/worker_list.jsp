<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<style>
/*タイトル*/
.h2 {
	margin-top : 60px;
	text-align : center;
}
/*バイト*/
.h3 {
	text-align : left;
	margin-left : 50px;
	margin-bottom : 5px;
}
/*社員*/
.h3_h3 {
	text-align : left;
	margin-left : 50px;
}


/*バイトの一覧テーブル*/
.workertable {
     width: 99%; /* 画面の幅いっぱいに広げる */
    margin-left: 5px;
    text-align: center;
    white-space: nowrap; /* 改行をしない */
    overflow-x: scroll; /* 横方向のスクロールバー */
    overflow-y: hidden; /* 縦方向のスクロールバーを非表示 */
    text-overflow: ellipsis; /* 省略記号を表示 */
}

.workertable td {
    overflow: hidden; /* 親要素のオーバーフローを非表示 */
}
/*バイトの一覧テーブルのラベル*/
.workertable th {
    background-color: #6495ED; /* 背景色を水色に設定 */
    border: 1px solid #000; /* 枠線を黒に設定 */
    color: white;
    width: 10%; /* 画面の幅いっぱいに広げる */
}
.update {
    display: none;
}

.workertable-container {
    display: flex;
}

.table-container {

    width: 90%;
    overflow-x: auto;
}

.button-group {
 margin-bottom: -31px; /* ボタングループ間のスペースを調整 */
    margin-top: 30px; /* ボタングループの上にスペースを追加 */
    margin-left: 50px;
    display: flex;
    justify-content: space-between;
}

.update-link, .delete-link {
    display: inline-block;
    margin-bottom: 0; /* ボタン間のスペースを調整 */
}

.update-link, .delete-link {
    display: block;
    margin-bottom: 5px; /* ボタン間のスペースを調整 */
}

.update-link, .delete-link {
    display: block;
    margin-bottom: 5px; /* ボタン間のスペースを調整 */
}



/*社員の一覧テーブル*/
.managertable {
    width: 100%; /* 画面の幅いっぱいに広げる */
    margin-left : 5px;
    text-align : center;

    white-space: nowrap; /* 改行をしない */
    overflow: hidden; /* はみ出た部分を非表示 */
    text-overflow: ellipsis; /* 省略記号を表示 */

    overflow-x: scroll; /*スクロールバー*/
}
/*バイトの一覧テーブルのラベル*/
.managertable th {
    background-color: #6495ED; /* 背景色を水色に設定 */
    border: 1px solid #000; /* 枠線を黒に設定 */
    color: white;
    width: 10%; /* 画面の幅いっぱいに広げる */
    margin-left : 30px;
    margin-bottom: 50px;
}
/*メインへリンク*/
.main {
    margin-left: 80%;
}
</style>

<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>従業員一覧</title>
</head>

<c:import url="../../common/header.jsp"/>

<body>

<div class="h2">
    <h2>～従業員一覧～</h2>
</div>

<div class="main">
	<a href="Main.action">トップページへ戻る</a>
</div>

<div class="h3">
    <h3>アルバイト</h3>
</div>

<c:if test="${empty workersnot}">
    <p>従業員が見つかりませんでした。</p>
</c:if>

<div class="workertable-container">
    <div class="table-container">
    <div class="workertable">
    <c:if test="${not empty workersnot}">
        <table class="table table-hover workertable">
            <tr>
                <th>ID</th>
                <th>名前</th>
                <th>生年月日</th>
                <th>住所</th>
                <th>電話番号</th>
                <th>パスワード</th>
                <th>店情報</th>
                <th class="update">選択</th>

            </tr>
            <c:forEach var="worker" items="${workersnot}">
                <tr>
					<td>${worker.workerId}</td>
                    <td>${worker.workerName}</td>
                    <td>${worker.workerDate}</td>
                    <td>${worker.workerAddress}</td>
                    <td>${worker.workerTel}</td>
                    <td>${worker.workerPassword}</td>
                    <td>${stores.storeName}</td>
            </c:forEach>
        </table>

        </c:if>
      </div>
      </div>

      <div class="buttons-container">
        <c:forEach var="worker" items="${workersnot}">
            <div class="button-group">


                    <a href="WorkerUpdate.action?workerId=${worker.workerId}" class="update-link">変更</a>
                    <a href="WorkerDelete.action?WORKER_ID=${worker.workerId}" class="delete-link">削除</a>
                                </div>
        </c:forEach>
    </div>
</div>


<hr>

<div class="h3_h3">
	<h3>社員</h3>
</div>

<c:if test="${empty workers}">
    <p>社員が見つかりませんでした。</p>
</c:if>


<div class="workertable-container">
    <div class="table-container">
    <div class="workertable">
<c:if test="${not empty workers}">
    <table class="table table-hover">
        <tr>
            <th>ID</th>
            <th>名前</th>
            <th>生年月日</th>
            <th>住所</th>
            <th>電話番号</th>
            <th>パスワード</th>
            <th>店情報</th>
        </tr>
        <c:forEach var="worker" items="${workers}">
            <tr>
                <td>${worker.workerId}</td>
                <td>${worker.workerName}</td>
                <td>${worker.workerDate}</td>
                <td>${worker.workerAddress}</td>
                <td>${worker.workerTel}</td>
                <td>${worker.workerPassword}</td>
                <td>${stores.storeName}</td>
</c:forEach>
                       </table>

        </c:if>
      </div>
      </div>
  <div class="buttons-container">
        <c:forEach var="worker" items="${workers}">
            <div class="button-group">

               <a href="WorkerUpdate.action?workerId=${worker.workerId}">変更</a>
                <a href="WorkerDelete.action?WORKER_ID=${worker.workerId}">削除</a>
                </div>
        </c:forEach>
    </div>
</div>



<c:import url="../../common/footer.jsp"/>

</body>