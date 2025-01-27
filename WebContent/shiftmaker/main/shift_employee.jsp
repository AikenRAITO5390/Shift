<%-- シフト閲覧JSP --%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="ja">

<c:import url="../../common/header.jsp"/>

<head>
    <title>～シフト閲覧（社員）～</title>
    <style>
		.shiftAction{margin-top : 60px;}
        table {border-collapse: collapse; width: 90%;  table-layout: fixed; margin-bottom: 10px;}
        th, td { border: 1px solid #ddd; padding: 8px; text-align: center; }
       .shiftAction th { background-color: #6495ED; color: white;
			  text-align: center; /* テキストを中央揃え */
			  vertical-align: middle; /* 垂直方向も中央揃え */
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

  		  h1{
  		  margin-left: 40%;
  		  }


  		  .grahu th{
  		  	width: 40px !important;
  		  	height: 40px !important;

  		  }
  		  .grahu td{
  		  	width: 40px !important;
  		  	height: 40px !important;
  		  	font-size: 14px;

  		  }

  		  .table_table {
  	  			overflow-x: scroll; /* 縦方向のスクロールバーを表示 */
				}

		.year{
			margin-left: 90%;
		}
    </style>
</head>

<body>
<div class="shiftAction">
    <h1>～シフト閲覧（社員）～</h1>

        <div class="year">
        <h4>${year}年 ${month}月</h4>
    </div>

    <div class="table_table">
    <div class ="grahu">
    <table>
        <!-- 曜日ヘッダー -->
        <thead>
        <tr>
            <th>名前</th>
            <c:forEach var="date" items="${dates}">
                <th>
                	${date.dayOfMonth}
                </th>
            </c:forEach>
        </tr>
        </thead>

        <!-- シフト情報 -->
        <tbody>
        <c:forEach var="workerlist" items="${worker_list}">
		    <tr>
		        <td>${workerlist.workerName}</td>
		        <c:forEach var="date" items="${dates}">
		            <td>
		                <c:choose>
		                    <%-- シフト情報が存在する場合 --%>
		                    <c:when test="${shiftMap[workerlist.workerId][date] != null}">
		                        <c:set var="shift" value="${shiftMap[workerlist.workerId][date]}" />
		                        <c:choose>
	                                    <%-- worker_judgeがTrueの時はTを〇に変換して表示 --%>
	                                    <c:when test="${shift.workTimeId  == 'T'}">
	                                            〇
	                                    </c:when>
	                                    <c:otherwise>
	                                            ${shift.workTimeId}
	                                    </c:otherwise>
	                                </c:choose>
		                        <c:if test="${shift.workTimeId == null}">
		                                <fmt:formatDate value="${shift.shiftTimeStart}" pattern="HH:mm" /> -
		                                <fmt:formatDate value="${shift.shiftTimeEnd}" pattern="HH:mm" />
								</c:if>
		                    </c:when>
		                    <%-- シフト情報がない場合 --%>
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
</div>

	<a href="ShiftChoose.action">戻る</a>
	</div>
</body>

<c:import url="../../common/footer.jsp"/>
</html>