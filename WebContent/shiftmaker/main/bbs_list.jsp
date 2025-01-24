<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>

<style>
.h1 h1{
	margin-top : 70px;
	text-align : center;
}
.date{
	margin-top : 15px;
	 font-size: 18px;
}
.delete{
	margin-top : 15px;
	margin-left: 3%;
	 font-size: 18px;
	 margin-bottom: 7px;

}
.delete a {
    display: inline-block; /* リンクをブロック要素にする */
    padding: 5px 5px; /* 内側の余白を設定 */
    background-color: #ff6347; /* 背景色を薄い赤色に設定 */
    color: white; /* 文字色を赤色に設定 */
    text-decoration: none; /* 下線を消す */
}

.delete a:hover {
    background-color: #ffcccc; /* ホバー時の背景色を変更 */
    border-color: #cc0000; /* ホバー時の枠線色を変更 */
}
.name{
	margin-left: 2%;
  flex-grow: 1;
	margin-top : 15px;
}

.text{
	margin-top : 2px;
	margin-left: 60px;
	width: 80%; /* 40文字分の幅を指定 */
  	overflow-wrap: break-word; /* 単語の途中でも改行 */
  	word-wrap: break-word; /* 単語の途中でも改行 */
}



.toukou{
	border: 1px solid ; /* 枠線を設定 */
	background-color: #EEEEEE; /* 背景色を設定 */
    padding: 2px; /* 枠線と内容の間にスペースを追加 */
    margin-top : 10px;
    margin-left: 5%;
    width: 85%; /* 画面の幅いっぱいに広げる */
}
.create{
  text-align : center;
  position: fixed; /* 要素を固定 */
  left: 78%; /* 画面の左端に配置 */
}

.create a{
	 background-color: #75A9FF; /* 背景色を設定 */
     text-decoration: none; /* 下線を消す */
 	 padding: 5px 40px; /* 内側の余白を設定 */
 	  color: white; /* 文字色を赤色に設定 */
 	 z-index: 1000; /* 他の要素の上に表示 */
 	  border: 0.5px solid black; /* 赤色の枠線を設定 */
}


.modoru{
  margin-left:10%; /* 画面の左端に配置 */
}

.modoru a {
	border: 0.5px solid black; /* 赤色の枠線を設定 */
    display: inline-block; /* リンクをブロック要素にする */
    padding: 5px 20px; /* 内側の余白を設定 */
    background-color: #75A9FF; /* 背景色を薄い赤色に設定 */
    color: white; /* 文字色を赤色に設定 */
    text-decoration: none; /* 下線を消す */

}

.message-container {
    display: flex;
    align-items: center; /* 垂直方向の中央揃え */
       padding-right: 5%;
}

a:hover {
    background-color: #71C5E8; /* ホバー時の背景色を変更 */

}
</style>


<head>
    <meta charset="UTF-8">
    <title>シフト作成者掲示板</title>
</head>

<c:import url="../../common/header.jsp"/>

<body>

<div class="h1">
    <h1>～掲示板～</h1>
</div>
 <div class="message-container">
<div class="create">
    <a href="BbsCreate.action">投稿作成</a>
</div>

<div class="modoru">
    <a href="Main.action">戻る</a>
</div>
</div>
    <ul>
        <c:forEach var="message" items="${messages}">


			<div class="toukou">
<div class="message-container">
                <!-- <strong>投稿者:</strong> -->
               <div class="name">
                <c:choose>
                    <c:when test="${not empty message.worker.workerName}">
                       投稿者： ${message.worker.workerName}
                    </c:when>
                    <c:when test="${not empty message.store.managerName}">
                       投稿者： ${message.store.managerName}
                    </c:when>
                </c:choose>
               </div>

                <br>
                <div class="date"> ${message.bbsDate}<br></div>
 </div>
 <div class="message-container">
 				 <div class="text">${message.bbsText} <br></div>
                <div class="delete"><td><a href="BbsDelete.action?MANAGER_ID=${store.managerId}&BBS_ID=${message.bbsId}">投稿削除</a></td></div>
             </div></div>
        </c:forEach>
    </ul>


    <c:import url="../../common/footer.jsp"/>

</body>
</html>