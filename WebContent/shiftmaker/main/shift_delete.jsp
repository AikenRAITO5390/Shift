<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<!DOCTYPE html>
<html lang="ja">

<style>
.h1 {
	margin-top : 60px;
	text-align : center;
}
.lt {
	margin-top : -15px;
	margin-left : 6%;
	margin-bottom : -40px;
}
.gt {
	margin-top : -90px;
	margin-left : 88%;
	margin-bottom : 10px;
}
.year {
	margin-top : 10px;
	margin-left : 47%;
	margin-bottom : 60px;
}
/*ラベル(なまえ)*/
.date {
	background-color: #6495ED; /* 背景色を水色に設定 */
	color: white;

}
/*ラベル(日付)*/
.date1 {
	background-color: #6495ED; /* 背景色を水色に設定 */
	color: white;
	/*width: 100%;*/
}
.table_table {
    overflow-x: scroll; /* 縦方向のスクロールバーを表示 */
    width: 100%;
}
.delete input[type="submit"]{
	margin-top : 60px;
	text-align : center;
	background-color: #FF9999;
    color: white;
    border: 1px solid white;
    padding: 10px 20px; /* ボタンの内側の余白を調整 */
    width:  15%;
    display: inline-block;
    margin-left: 42%;
    border-radius: 6px; /* 角を丸くする */
}
.main {
	margin-top : 20px;
	text-align : center;
	margin-bottom : 20px;
}
.table_table th{
  		  	width: 40px !important;
  		  	height: 40px !important;

  		  }
.table_table td{
  		  	width: 40px !important;
  		  	height: 40px !important;
  		  	font-size: 13px;

  		  }

.shiftAction a{display: inline-block;
   		  padding: 10px;
   		  text-decoration: none; /* 下線を消す */
  		  border-radius: 3px;
  		  background-color: #6495ED;
  		  color: white;
  		  margin-bottom: 5%;
  		  margin-left: 85%;
  		  }

</style>

<c:import url="../../common/header.jsp" />

<head>

    <title>シフト削除</title>
    <style>
        .shiftAction{margin-top : 60px;}
        table {border-collapse: collapse; width: 90%;  table-layout: fixed; margin-bottom: 10px; margin-left: 50px;}
        th, td { border: 1px solid #ddd; padding: 8px; text-align: center; }
       .shiftAction th { background-color: #6495ED; color: white;
			  text-align: center; /* テキストを中央揃え */
			  vertical-align: middle; /* 垂直方向も中央揃え */
}
    </style>
</head>

<body>
<div class = "shiftAction">
	<div class="h1">
    	<h1>～シフト削除～</h1>
    </div>

	<div class="lt">
    <!-- 前月・次月へのリンク -->
    <form action="ShiftDelete.action?count=${count}" method="get">
        <input type="hidden" name="year" value="${year}">
        <input type="hidden" name="month" value="${prevMonth}">
        <button type="submit">&lt;&lt; 前月</button>
    </form>
    </div>

    <div class="year">
    <span>${year}年 ${month}月</span>
    </div>

    <div class="gt">
    <form action="ShiftDelete.action?count=${count}" method="get">
        <input type="hidden" name="year" value="${year}">
        <input type="hidden" name="month" value="${nextMonth}">
        <button type="submit">&gt;&gt; 次月</button>
    </form>
    </div>

	<div class="table_table">
    <!-- シフトテーブルの表示 -->
    <table>
        <thead>
            <tr>
                <th class="date">名前</th>
                <c:forEach var="date" items="${dates}">
                    <th class="date1">${date.dayOfMonth}</th>
                </c:forEach>
            </tr>
        </thead>
        <tbody>
            <c:forEach var="workerlist" items="${worker_list}">
                <tr>
                    <td>${workerlist.workerName}</td>
                    <c:forEach var="date" items="${dates}">
                        <td>
                            <c:choose>
                                <c:when test="${shiftMap[workerlist.workerId][date] != null}">
                                    <c:set var="shift" value="${shiftMap[workerlist.workerId][date]}" />
                                    <c:if test="${shift.workTimeId != null}">
			                            <c:choose>
			                                <c:when test="${shift.workTimeId == 'T'}">
			                                    〇  <%-- Tの場合は〇を表示 --%>
			                                </c:when>
			                                <c:otherwise>
			                                    ${shift.workTimeId}  <%-- A, B, C, Dなどそのまま表示 --%>
			                                </c:otherwise>
			                            </c:choose>
			                        </c:if>
                                    <c:if test="${shift.workTimeId == null}">
                                        <fmt:formatDate value="${shift.shiftTimeStart}" pattern="HH:mm" /> -
                                        <fmt:formatDate value="${shift.shiftTimeEnd}" pattern="HH:mm" />
                                    </c:if>
                                </c:when>
                                <c:otherwise>
                                    -
                                </c:otherwise>
                            </c:choose>
                        </td>
                    </c:forEach>
                </tr>
            </c:forEach>
        </tbody>
    </table>
    </div>

	<div class="delete">
    <!-- シフト削除フォーム (カレンダー外に1つの削除ボタン) -->
    <form action="ShiftDeleteCheck.action" method="post">
        <input type="hidden" name="year" value="${year}">
        <input type="hidden" name="month" value="${month}">
        <input type="submit" value="このシフトを削除する">
    </form>
    </div>

	<div class="main">
    <a href="Main.action">戻る</a>
    </div>
    </div>
</body>

<c:import url="../../common/footer.jsp" />

</html>
