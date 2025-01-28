<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>

<style>
.h1 {
	margin-top : 60px;
	text-align : center;
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
	width: 65%; /* 各セルの幅 */
  	height: 60px; /* 各セルの高さ */
}
/*削除*/
.delete {
	margin-top : 60px;
	margin-left : 60%;
}
/*編集*/
.check {
	margin-top : -40px;
	margin-left : 40%;
	margin-bottom : 20px;
}
/*データ*/
.p {
	text-align: center;
}


table {
	width: 100%;
	border-collapse: collapse;
	margin: 20px auto;
	table-layout: fixed;
}

th {
	width: 40px !important; /* 各セルの幅 */
  	height: 40px !important; /* 各セルの高さ */
	border: 1px solid #ddd;
	padding: 8px;
	text-align: center;
}
td {
	width: 40px !important; /* 各セルの幅 */
  	height: 40px !important; /* 各セルの高さ */
  	border: 1px solid #ddd; /* 枠線の太さと色 */
  	text-align: center; /* テキストの中央揃え */
  	vertical-align: middle; /* 垂直方向の中央揃え */
  	font-size: 13px;
}
.table_table {
    overflow-x: scroll; /* 縦方向のスクロールバーを表示 */
}


</style>

<c:import url="../../common/header.jsp"/>

<head>
    <title>シフトカレンダー</title>
</head>
<body>

	<div class="h1">
    <h1>～シフト作成～</h1>
    </div>

	<div class="table_table">
    <table>
        <!-- 曜日ヘッダー -->
        <thead>
        <tr>
            <th class="date">名前</th>
            <c:forEach var="date" items="${dates}">
                <th class="date1">${date.dayOfMonth}</th>
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

            												<div class ="p">
											                <p>${shift}</p>
											                </div>

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
    </div>


	<div class="delete">
    <form action="ShiftEdit.action" method="post">
    	<button>編集</button>
    </form>
    </div>

    <div class="check">
    <form action="ShiftCreateResult.action" method="post">
    	<button>確定</button>
    </form>
    </div>
</body>

<c:import url="../../common/footer.jsp"/>

</html>
