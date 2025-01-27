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
	margin-left : 100px;
	margin-bottom : -40px;
}
.gt {
	margin-top : -90px;
	margin-left : 1100px;
}
.year {
	margin-top : 10px;
	margin-left : 580px;
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
}
.delete {
	margin-top : 60px;
	text-align : center;
}
.main {
	margin-top : 20px;
	text-align : center;
	margin-bottom : 20px;
}

</style>

<c:import url="../../common/header.jsp" />

<head>
    <title>シフト削除</title>
    <style>
        table { width: 90%; border-collapse: collapse; margin: 20px auto; }
        th, td { border: 1px solid #ddd; padding: 8px; text-align: center; }
        th { background-color: #f4f4f4; }
    </style>
</head>

<body>
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
                                        ${shift.workTimeId}
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
        <button type="submit">このシフトを削除する</button>
    </form>
    </div>

	<div class="main">
    <a href="Main.action">戻る</a>
    </div>
</body>

<c:import url="../../common/footer.jsp" />

</html>
