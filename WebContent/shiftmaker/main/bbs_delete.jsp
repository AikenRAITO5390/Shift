<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>BBS</title>
</head>
<body>
    <table>
        <tr>
            <th>投稿者</th>
            <th>コメント</th>
        </tr>
        <c:forEach var="post" items="${posts}">
            <tr>
                <td>${post.userId}</td>
                <td>${post.text}</td>
                <td>
                    <c:if test="${post.userId == loggedInUserId}">
                        <form action="BbsDeleteExecute.action" method="post">
                            <input type="hidden" name="bbsId" value="${post.bbsId}">
                            <p>この投稿を削除しますか？</p>
                            <input type="submit" name="action" value="削除">
                            <input type="button" value="戻る" onclick="history.back()">
                        </form>
                    </c:if>
                </td>
            </tr>
        </c:forEach>
    </table>
</body>
</html>