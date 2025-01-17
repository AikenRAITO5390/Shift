<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<style>
.delete {
	margin-top : 60px;
	text-align : center;
}

   .inline {
            display: inline-block;
            margin-right: 10px; /* 必要に応じて調整 */

        }

<!---->
.user h3{
  	margin-left : 20%;
  	text-align: center !important; /* 水平方向に中央揃え */
    color: white;
    margin-right: 20px; /* 右に10pxの余白を追加 */
    margin-top: 20px; /* 右に10pxの余白を追加 */
    border: 1px solid white; /* 白い枠線を設定 */
    background-color: #4169E1; /* 背景色を設定 */
    padding: 10px; /* 枠線と内容の間にスペースを追加 */
     width:  100px;


        }

    .user p{
    margin-right: 20px; /* 右に10pxの余白を追加 */
    margin-top: 20px; /* 右に10pxの余白を追加 */
    border: 1px solid black; /* 白い枠線を設定 */
    padding: 10px; /* 枠線と内容の間にスペースを追加 */
     width: 150px;


        }
<!---->
        .text h3{
  	margin-left : 20%;
  	text-align: center !important; /* 水平方向に中央揃え */
    color: white;
    margin-right: 20px; /* 右に10pxの余白を追加 */
    margin-top: 20px; /* 右に10pxの余白を追加 */
    border: 1px solid white; /* 白い枠線を設定 */
    background-color: #4169E1; /* 背景色を設定 */
    padding: 10px; /* 枠線と内容の間にスペースを追加 */
     width: 100px;


        }

    .text p{
    margin-right: 20px; /* 右に10pxの余白を追加 */
    margin-top: 20px; /* 右に10pxの余白を追加 */
    border: 1px solid black; /* 白い枠線を設定 */
    padding: 10px; /* 枠線と内容の間にスペースを追加 */
     width: 40%;
    height: 100px; /* 高さを100pxに設定 */
        }

  .kakunin{
  text-align: center  !important;
  }

 .submit input[type="submit"] {
    background-color: #ff6347;
    color: white;
    border: 1px solid white;
    padding: 10px 20px; /* ボタンの内側の余白を調整 */
    border: 1px solid white; /* 白い枠線を設定 */
    font-size: 16px; /* フォントサイズを調整 */
    border-radius: 4px; /* 角を丸くする */
    width: 120px;

}

.submit a{
	 margin-left: 40%;
	 margin-right: 40px;
    display: inline-block; /* インラインブロック要素に変更 */
    color: white;
    text-align: center !important; /* 水平方向に中央揃え */
    border: 1px solid white; /* 白い枠線を設定 */
    background-color: #2C7CFF; /* 背景色を設定 */
    padding: 10px 20px; /* 枠線と内容の間にスペースを追加 */
    font-size: 16px; /* フォントサイズを調整 */
    border-radius: 4px; /* 角を丸くする */
    width: 70px;
}
</style>
<head>
	<meta charset="UTF-8">
    <title>掲示板削除確認</title>
</head>
<body>
<c:import url="../../common/header_work.jsp"/>

<div class="delete">
    <h1>～削除確認～</h1>
</div>

<div class = "user">
       <h3 class="inline">投稿者:</h3><p class="inline">${user}</p><br>
</div>

<div class = "text">
       <h3 class="inline">コメント:</h3><p class="inline">${text}</p>
</div>

<div class="kakunin">
    	<h2>この投稿を削除しますか？</h2>
</div>

   <form action="BbsDeleteExecuteWorker.action" method="post">

                            <!-- 中江つけたしてみる一行 -->
                             <input type="hidden" name="id_id" value="${BBS_ID}">
							<div class ="submit">
							<a href="BBSWorker.action">戻る</a>
							        <input type="submit" value="削除">

</div>
    </form>
<c:import url="../../common/footer.jsp"/>

</body>
</html>