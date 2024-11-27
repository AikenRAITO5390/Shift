<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <title>Shift Calendar</title>
    <style>
        table { width: 90%; border-collapse: collapse; margin: 20px auto; }
        th, td { border: 1px solid #ddd; padding: 8px; text-align: center; }
        th { background-color: #f4f4f4; }
    </style>
</head>
<body>
    <h1>Shift Calendar</h1>
    <table>
        <!-- 曜日ヘッダー -->
        <thead>
        <tr>
            <th>名前</th>
            <c:forEach var="date" items="${dates}">
                <th>${date.dayOfMonth}</th>
            </c:forEach>
        </tr>
        </thead>

        <!-- シフト情報 -->
        <tbody>
        <c:forEach var="wlist" items="${worker_list}">
        	<tr>
        		<td>${wlist.workerName}</td>

				<c:forEach var="date" items="${dates}">
					<td>
						<c:forEach var="worker" items="${innerList}">
							<c:choose>
								<c:when test="${worker.date == date.dayOfMonth}">
                                	<ul>
                                    	<c:forEach var="shift" items="${worker.mergedShifts}">
                                    		<c:choose>
												<c:when test="${wlist.workerName == worker.name}">
                                        			<li>${shift}</li>
                                        		</c:when>
                                        	</c:choose>
                                    	</c:forEach>
                                	</ul>
                            	</c:when>

                        	</c:choose>
						</c:forEach>
					</td>
				</c:forEach>
        	</tr>
        </c:forEach>
        </tbody>
    </table>
</body>
</html>
