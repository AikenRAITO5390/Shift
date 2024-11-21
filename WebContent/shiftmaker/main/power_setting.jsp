<%-- 時間設定JSP --%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<%-- cssの取得 --%>
<link rel="stylesheet" href="../../css/style.css">
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
</head>
<%--ヘッダーサイドバー--%>
<c:import url="../../common/header.jsp"/>
<body>

<h2>シフト時間設定</h2>
<%--次とぶやつ--%>
<form action="PowerSettingResult.action" method="post">
    <table class="table table-hover">
        <c:forEach var="i" begin="0" end="6">
            <tr>
            <%--A~Dの表示　--%>
                <td><label>${fn:substring('月火水木金土日', i, i+1)}</label></td>
                <td>
                <%--スタート時間の表示--%>
                <%--初期値timeSelectStart(下で設定）、nameはAのworkTimeStartでわたす　--%>
			<input type="text" name="WeekScore_${i}"
				maxlength="7" id="WeekScore_${fn:substring('1234567', i, i+1)}">
				</td>
				<%--Idをわたす。hiddenで隠す --%>
                  <input type="hidden" name="WorkWeekScore_${i}" value="${fn:substring('1234567', i, i+1)}">
            </tr>
        </c:forEach>
    </table>
     <script>
        <c:forEach var="i" begin="0" end="6">
            <c:choose>
                <c:when test="${i < power_list.size()}">
                    <script>
                        // workTimeStartの値を設定
                        var weekScore = "${power_list[i][1]}";
                        var selectElementStart = document.getElementById("WeekScore_${fn:substring('1234567', i, i+1)}");
                        selectElementStart.value = weekScore;
                    </script>
                </c:when>
                <c:otherwise>
                    <script>
                        // 空のエントリの場合はデフォルト値を設定
                        var selectElementStart = document.getElementById("WeekScore_${fn:substring('1234567', i, i+1)}");
                        selectElementStart.value = "";
                    </script>
                </c:otherwise>
           </c:choose>
        </c:forEach>
    </script>
    <button type="submit">変更</button>
</form>

</body>
</html>
