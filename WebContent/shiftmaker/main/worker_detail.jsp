<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<style>
.h2 h2{
	margin-top : 60px;
	text-align : center;
}

.container {
    display: flex;
    justify-content: space-between; /* 要素間のスペースを均等にする */
}

.label, .label_2 {
    width: 60%; /* 幅を調整して隣に並べる */
    box-sizing: border-box; /* パディングとボーダーを含めた幅を計算 */
}

.back a{
	float: right;
	margin-top : -30px;
	margin-right : 150px;
	font-size: 20px; /* フォントサイズを大きくする */
    font-weight: bold; /* 太字にする */
  /*  color: #2C7CFF;  テキストの色を変更 */
  /*  text-decoration: none; 下線を消す */
}

.inline {
            display: inline-block;
            margin-right: 10px; /* 必要に応じて調整 */
 }

 .label h3{
   	margin-left : 20%;
   	text-align: center !important; /* 水平方向に中央揃え */
    color: white;
    margin-top: 20px; /* 右に10pxの余白を追加 */
    border: 1px solid white; /* 白い枠線を設定 */
    background-color: #6495ED; /* 背景色を設定 */
    padding: 10px; /* 枠線と内容の間にスペースを追加 */
     width:  25%;

 }

  .label p{
    margin-top: 20px; /* 右に10pxの余白を追加 */
    border: 1px solid #a9a9a9; /* 白い枠線を設定 */
    padding: 10px; /* 枠線と内容の間にスペースを追加 */
     width: 40%;


  }

   .label_2 h3{
   margin-left : 5%;
   	text-align: center !important; /* 水平方向に中央揃え */
    color: white;
    margin-top: 20px; /* 右に10pxの余白を追加 */
    border: 1px solid white; /* 白い枠線を設定 */
    background-color: #6495ED; /* 背景色を設定 */
    padding: 10px; /* 枠線と内容の間にスペースを追加 */
     width: 25%;

 }

  .label_2 p{
    margin-top: 20px; /* 右に10pxの余白を追加 */
    border: 1px solid #a9a9a9; /* 白い枠線を設定 */
    padding: 10px; /* 枠線と内容の間にスペースを追加 */
     width: 40%;


  }
</style>

<head>
    <title>従業員情報詳細</title>
</head>
<c:import url="../../common/header.jsp"/>
<body>

<div class="h2">
    <h2>～詳細ページ～</h2>
</div>
<div class="container">
<div class="label">
	<h3 class="inline">ID</h3><p class="inline">${worker.workerId}</p><br>
	<h3 class="inline">名前</h3><p class="inline">${worker.workerName}</p><br>
	<h3 class="inline">生年月日</h3><p class="inline">${worker.workerDate}</p><br>
	<h3 class="inline">住所</h3><p class="inline">${worker.workerAddress}</p><br>
	<h3 class="inline">電話番号</h3><p class="inline">${worker.workerTel}</p><br>
</div>

<div class="label_2">
	<h3 class="inline">パスワード</h3><p class="inline">${worker.workerPassword}</p><br>
	<h3 class="inline">店情報</h3><p class="inline">${worker.store.storeName}</p><br>
	<h3 class="inline">実力</h3><p class="inline">${worker.workerScore}</p><br>
	<h3 class="inline">得意なポジション</h3><p class="inline">${worker.workerPosition}</p>
</div>
</div>

<div class="back">
<a href="WorkerDate.action">従業員一覧に戻る</a>
</div>

<c:import url="../../common/footer.jsp"/>
</body>
</html>