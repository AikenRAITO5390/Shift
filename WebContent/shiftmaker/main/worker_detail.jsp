<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<style>
.h2 h2{
	margin-top : 60px;
	text-align : center;
}
.label {
	margin-left : 300px;
	margin-top : 50px;
	width: 70%;
}
.label_label {
	margin-left : 800px;
	margin-top : -500px;

}
.label label{
    margin: 0; /* デフォルトのマージンをリセット */
    color: white;
    padding: 10px 0; /* 上下のパディングを設定 */
    margin-top: 10px;
    border: 0.5px solid white; /* 青い枠線を設定 */
    background-color: #75A9FF;
    padding: 5px; /* 枠線と内容の間にスペースを追加 */
    border-radius: 5px;
    text-align: center; /* テキストを中央揃えにする */

    width: 120px; /* 幅を固定 */
    height: 35px; /* 高さを固定 */
    box-sizing: border-box;
    margin-right: 15px;
    text-align: right;
    margin-top: -5px;
    text-align: center;

}
.label p{
	width: 200px; /* 必要に応じて調整 */
    max-width: 700px; /*  最大幅を700pxに設定 */
    padding: 10px; /* 内側の余白を追加してボックスを大きく見せる */
    margin-right: 40px;
    box-sizing: border-box;
}
.label_label label{
    margin: 0; /* デフォルトのマージンをリセット */
    color: white;
    padding: 10px 0; /* 上下のパディングを設定 */
    margin-top: -100px;
    border: 0.5px solid white; /* 白の枠線を設定 */
    background-color: #75A9FF;
    padding: 5px; /* 枠線と内容の間にスペースを追加 */
    border-radius: 5px; /*角*/
    text-align: center; /* テキストを中央揃えにする */

	width: 80px; /* 幅を固定 */
    height: 65px; /* 高さを固定 */
    box-sizing: border-box;
    margin-right: 230px;
    text-align: right;
    margin-top: -5px;
    text-align: center;
}
.label_label p{
	width: 200px; /* 必要に応じて調整 */
    max-width: 700px; /*  最大幅を700pxに設定 */
    padding: 10px; /* 内側の余白を追加してボックスを大きく見せる */
    margin-right: 140px;
    box-sizing: border-box;
}

.back a{
	margin-top : 20px;
	margin-left : 80px;
}
</style>

<head>
    <title>従業員情報詳細</title>
</head>
<c:import url="../../common/header.jsp"/>
<body>

<div class="h2">
    <h2>詳細</h2>
</div>

<div class="label">
    <label>ID  </label>  <p>${worker.workerId} </p>
    <label>名前  </label> <p>${worker.workerName}</p>
    <label>生年月日  </label> <p>${worker.workerDate}</p>
    <label>住所  </label> <p>${worker.workerAddress}</p>
    <label>電話番号  </label> <p>${worker.workerTel}</p>
</div>

<div class="label_label">
	<label>パスワード  </label> <p>${worker.workerPassword}</p>
    <label>店情報  </label> <p>${worker.store.storeName}</p>
    <label>実力 </label> <p>${worker.workerScore}</p>
    <label>得意なポジション </label> <p>${worker.workerPosition}</p>
</div>

<div class="back">
<a href="WorkerDate.action">トップページに戻る</a>
</div>

<c:import url="../../common/footer.jsp"/>
</body>
</html>