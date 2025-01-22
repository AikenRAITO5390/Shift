<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>

<style>
.h1 h1{
	margin-top : 60px;
	text-align : center;
}
.date{
	margin-top: -45px;
	margin-left: 460px;
	margin-right: 20px;
}
.delete{
	margin-top : 25px;
	margin-left: 480px;
	margin-bottom : 10px;
}
.name{
	margin-top : 8px;
	margin-left: 60px;
}
.text{
	margin-top : 2px;
	margin-left: 60px;

	width: 90ch; /* 40文字分の幅を指定 */
  	overflow-wrap: break-word; /* 単語の途中でも改行 */
  	word-wrap: break-word; /* 単語の途中でも改行 */
}
.toukou{
	border: 1px solid ; /* 枠線を設定 */
	background-color: #EEEEEE; /* 背景色を設定 */
    padding: 2px; /* 枠線と内容の間にスペースを追加 */
    border-radius: 5px; /* 角を丸くする */
    margin-top : 10px;
    display: inline-block; /*文字の幅だけ線をひく*/
    margin-left: 20px;
    width: 95%; /* 画面の幅いっぱいに広げる */
}
.create{
  text-align : center;
  position: fixed; /* 要素を固定 */
  top: 90px; /* 画面の上部に配置 */
  left: 1140px; /* 画面の左端に配置 */
  width: 6%; /* 画面の幅いっぱいに広げる */
  border: 1px solid ; /* 枠線を設定 */
  background-color: #75A9FF; /* 背景色を設定 */
  border-radius: 5px; /* 角を丸くする */
  display: inline-block; /*文字の幅だけ線をひく*/

  padding: 5px; /* 内側の余白を設定 */
  z-index: 1000; /* 他の要素の上に表示 */
}
.modoru{
  margin-top : -1200px;
  margin-left: 60px; /* 画面の左端に配置 */
  width: 3%; /* 画面の幅いっぱいに広げる */


  padding: 5px; /* 内側の余白を設定 */
  z-index: 1000; /* 他の要素の上に表示 */
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


<div class="tabel">
    <ul>
        <c:forEach var="message" items="${messages}">


			<div class="toukou">

                <!-- <strong>投稿者:</strong> -->
               <div class="name">
                <c:choose>
                    <c:when test="${not empty message.worker.workerName}">
                        ${message.worker.workerName}
                    </c:when>
                    <c:when test="${not empty message.store.managerName}">
                        ${message.store.managerName}
                    </c:when>
                </c:choose>
               </div>

                <br>
                <div class="text">${message.bbsText} <br></div>
                <div class="date"> ${message.bbsDate}<br></div>
                <div class="delete"><td><a href="BbsDelete.action?MANAGER_ID=${store.managerId}&BBS_ID=${message.bbsId}">削除</a></td></div>
             </div>
        </c:forEach>
    </ul>
</div>

<div class="create">
    <a href="BbsCreate.action">投稿作成</a>
</div>

<div class="modoru">
    <a href="Main.action">戻る</a>
</div>

    <c:import url="../../common/footer.jsp"/>

</body>
</html>