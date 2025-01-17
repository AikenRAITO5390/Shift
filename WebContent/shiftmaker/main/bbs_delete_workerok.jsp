<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>

<style>
.main {
	margin-top : 60px;
	text-align : center;
}

 .main a {
    background-color: #75A9FF;
    color: white;
    border: 1px solid white;
    padding: 10px; /* ボタンの内側の余白を調整 */
    border: 1px solid white; /* 白い枠線を設定 */
    font-size: 16px; /* フォントサイズを調整 */
    border-radius: 4px; /* 角を丸くする */
    margin-left: 120px;

}

.main h4{
	margin-top : 50px;
	margin-bottom : 50px;
}
</style>

<head>
    <title>掲示板投稿削除</title>
</head>
<body>
<c:import url="../../common/header_work.jsp"/>

    <div class = "main">
	<h1>～削除完了～</h1>
    <h4>正常に削除されました</h4>
        <a href="BBSWorker.action">投稿一覧へ戻る</a>

</div>
<c:import url="../../common/footer.jsp"/>
</body>
</html>