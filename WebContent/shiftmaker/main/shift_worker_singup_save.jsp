<%-- 従業員希望シフト登録反映JSP --%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="ja">

<style>
.h1 {
	margin-top : 60px;
	text-align : center;
}
.table {
	margin-top : 70px;
	display: flex;
   	justify-content: center; /* 水平方向に中央揃え */
}
.table a{
	text-decoration: none;
}
.red {
    color: #FF69A3;
}

.blue {
    color: #6495ED;
}
.table_table th{
    background-color: #6495ED;
    color: white;
}
.table_table h3{
	 margin-top: 0; /* 必要に応じて調整 */
    margin-left: -44%;
}
.table_table {
	margin-top: 100px;
    text-align: center;
}
.table_table table{
	 margin: 0 auto;
}

.ok{
	display: flex;
   	justify-content: center; /* 水平方向に中央揃え */
    align-items: center;
    margin-bottom: 100px;

}
.ok input[type="submit"]{
	margin-top : -420px;
	margin-left : 700px;
	background-color: #6495ED; /* 背景色を水色に設定 */
	padding: 10px 20px;
	color: white;
	border-radius: 4px; /* 角を丸くする */
	border: none; /* ボーダーをなしに設定 */
}
.sita {
	margin-top : 60px;
}
</style>

<c:import url="../../common/header_work.jsp"/>

<head>
    <meta charset="UTF-8">
    <title>シフト希望提出</title>
    <style>
        table { border-collapse: collapse; }
        .table_table th { border: 1px solid black; width: 350px; height: 10px; text-align: center; }
        td { border: 1px solid black; width: 75px; height: 75px; text-align: center; }

    </style>
</head>
<body>
	<div class="h1">
    <h1>～シフト希望提出～</h1>
    </div>

	<div class="table">
    <table>
        <tr>
            <th class="red">日</th>
            <th>月</th>
            <th>火</th>
            <th>水</th>
            <th>木</th>
            <th>金</th>
            <th class="blue">土</th>
        </tr>


        <c:set var="counter" value="0" />
        <c:if test="${seru > 0}">
        <tr>
        <c:forEach var="seru" begin="1" end="${seru}">
        	<td>　</td>
        	<c:set var="counter" value="${counter + 1}" />
        </c:forEach>
        </c:if>
		<c:forEach var="dateKey" items="${dateKeys}" varStatus="status">
		    <c:if test="${counter % 7 == 0}">
		        <tr>
		    </c:if>
		    <td>
		        <c:choose>
		            <c:when test="${dateKey != null}">
		                <%-- 日付リンクを生成 --%>
		                <div class="date">
		                <a href="ShiftWorkerSignupSet.action?shiftDay=${dateKey}&count=${count}">
		                    ${fn:substring(dateKey.toString(), 8, 10)}
		                </a>
		                </div>

		                <%-- 勤務時間情報を表示（datesマップから値を取得） --%>
		                <c:choose>
						    <c:when test="${shiftHopeTimeIds[status.index] == 'T'}">
						        <%-- shiftHopeTimeIdが'T'の場合は〇を表示 --%>
						        <p>〇</p>
						    </c:when>
						    <c:when test="${shiftHopeTimeIds[status.index] != ''}">
						        <%-- shiftHopeTimeIdが空でない場合はそのまま表示 --%>
						        <p>${shiftHopeTimeIds[status.index]}</p>
						    </c:when>
						</c:choose>

		                <c:if test="${nullAndTime[status.index] != null}">
		                    <p>${nullAndTime[status.index]}</p>
		                </c:if>
		            </c:when>
		            <c:otherwise>
		                <!-- 空セル -->
		            </c:otherwise>
		        </c:choose>
		    </td>
		    <c:set var="counter" value="${counter + 1}" />
		    <c:if test="${counter % 7 == 0}">
		        </tr>
		    </c:if>
		</c:forEach>

        <c:if test="${counter % 7 != 0}">
            <c:forEach begin="0" end="${6 - (counter % 7)}">
                <td></td>
            </c:forEach>
            </tr>
        </c:if>
    </table>
    </div>

	<!-- worker_judgeがFalseの場合のみ店舗のシフト時間参考セクションを表示 -->
    <c:if test="${not isWorkerJudgeTrue}">
    	<div class="table_table">
        <h3>＜店舗のシフト時間参考＞</h3>
        <table>
            <thead>
                <tr>
                    <th>勤務時間ID</th>
                    <th>開始時間</th>
                    <th>終了時間</th>
                </tr>
            </thead>
            <tbody>
                <c:forEach var="workTime" items="${workTimeDetails}">
                    <tr>
                        <c:if test="${workTime.workTimeId != 'F'}">
                            <td>${workTime.workTimeId}</td>
                            <td>${workTime.workTimeStart}</td>
                            <td>${workTime.workTimeEnd}</td>
                        </c:if>
                    </tr>
                </c:forEach>
            </tbody>
        </table>
        </div>
    </c:if>

	<div class="ok">
    <form action="ShiftWorkerSignupResult.action" method="get">
	    <input type="submit" value="登録">
	</form>
	</div>

	<div class="sita"></div>

</body>

<c:import url="../../common/footer.jsp"/>
</html>