<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>従業員情報削除</title>
</head>
<body>
    <h2>${worker.workerName}さんの情報を削除しますか？</h2>
    <form action="WorkerDeleteExecute.action" method="post">
        <input type="hidden" name="WORKER_ID" value="${worker.workerId}">
        <input type="hidden" name="confirm" value="yes">
        <button type="submit">削除</button>
    </form>
    <form action="WorkerList.action" method="get">
        <button type="submit">戻る</button>
    </form>
</body>
</html>