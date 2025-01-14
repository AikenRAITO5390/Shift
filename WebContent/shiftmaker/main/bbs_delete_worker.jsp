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

            <tr>


                        <form action="BbsDeleteExecuteWorker.action" method="post">

                            <!-- 中江つけたしてみる一行 -->
                             <input type="hidden" name="id_id" value="${BBS_ID}">
                            <p>この投稿を削除しますか？</p>
                            <input type="submit" name="action" value="削除">
                            <input type="button" value="戻る" onclick="history.back()">
                        </form>


            </tr>
    </table>
</body>
</html>