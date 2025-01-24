<%-- 従業員希望シフト登録反映JSP --%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="ja">

<c:import url="../../common/header_work.jsp"/>

<head>
    <meta charset="UTF-8">
    <title>シフト希望提出</title>
    <style>
        table { border-collapse: collapse; }
        th, td { border: 1px solid black; width: 50px; height: 50px; text-align: center; }
    </style>
</head>
<body>
    <h1>～シフト希望提出～</h1>
    <table>
        <tr>
            <th>日</th>
            <th>月</th>
            <th>火</th>
            <th>水</th>
            <th>木</th>
            <th>金</th>
            <th>土</th>
        </tr>

        <c:set var="counter" value="0" />
		<c:forEach var="dateKey" items="${dateKeys}" varStatus="status">
		    <c:if test="${counter % 7 == 0}">
		        <tr>
		    </c:if>
		    <td>
		        <c:choose>
		            <c:when test="${dateKey != null}">
		                <%-- 日付リンクを生成 --%>
		                <a href="ShiftWorkerSignupSet.action?shiftDay=${dateKey}&count=${count}">
		                    ${fn:substring(dateKey.toString(), 8, 10)}
		                </a>

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

	<!-- worker_judgeがFalseの場合のみ店舗のシフト時間参考セクションを表示 -->
    <c:if test="${not isWorkerJudgeTrue}">
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
    </c:if>

    <form action="ShiftWorkerSignupResult.action" method="get">
	    <button type="submit">登録</button>
	</form>
</body>

<c:import url="../../common/footer.jsp"/>
</html>