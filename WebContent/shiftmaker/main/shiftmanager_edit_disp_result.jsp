<%-- シフト作成者更新完了JSP --%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>

<title>シフト作成者変更完了</title>

<style>
.shiftmanager_edit h2{
	text-align: center; /* 水平方向に中央揃え */
    margin-top: 7%; /* 上の余白をリセット */
    margin-bottom: 0; /
}

.shiftmanager_edit p{
 	text-align: center  !important; /* 水平方向に中央揃え */
 	padding-top: 40px;

}
.submit a {
    display: inline-block; /* インラインブロック要素に変更 */
    color: white;
    text-align: center !important; /* 水平方向に中央揃え */
    margin-top: 40px; /* 右に10pxの余白を追加 */
    border: 1px solid white; /* 白い枠線を設定 */
    background-color: #75A9FF; /* 背景色を設定 */
    padding: 10px; /* 枠線と内容の間にスペースを追加 */
    border-radius: 5px; /* 角を丸くする */
    margin-left: 300px;
}

.submit{
			display: flex;
		    justify-content: center; /* 水平方向に中央揃え */
		    align-items: center; /* 垂直方向に中央揃え（必要に応じて） */
}


</style>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
</head>
<body>
<%-- ヘッダーサイドバーの設定 --%>
<c:import url="../../common/header.jsp"/>
<div class="shiftmanager_edit">
	<h2>～シフト作成者変更～</h2>
		<p>変更が完了しました</p>
		<div class ="submit">
	<a href="Main.action">メインメニューへ</a></div>
</div>
</body>
</html>

