<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>

<c:import url="../../common/header.jsp"/>

<head>
    <title>シフトカレンダー</title>
</head>
<body>
    <h1>～シフト作成～</h1>
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
        <div class="p">
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
													<c:choose>
            											<c:when test="${shift == 'T'}">○</c:when>
            											<c:otherwise>
											                <p>${shift}</p>
											            </c:otherwise>
											        </c:choose>
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
        </div>
    </table>
    <form action="ShiftEdit.action" method="post">
    	<button>編集</button>
    </form>
    <form action="ShiftCreateResult.action" method="post">
    	<button>確定</button>
    </form>
</body>

<c:import url="../../common/footer.jsp"/>

</html>
