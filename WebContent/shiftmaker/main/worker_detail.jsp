<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<head>
    <title>従業員詳細</title>
</head>
<body>
    <h2>従業員詳細</h2>
    <p>ID : ${worker.workerId}</p>
    <p>名前 : ${worker.workerName}</p>
    <p>生年月日 : ${worker.workerDate}</p>
    <p>住所 : ${worker.workerAddress}</p>
    <p>電話番号 : ${worker.workerTel}</p>
    <p>パスワード : ${worker.workerPassword}</p>
    <p>店情報 : ${worker.store.storeName}</p>
    <p>実力 : ${worker.workerScore}</p>
    <p>得意なポジション ${worker.workerPosition}</p>
    <a href="WorkerDate.action">戻る</a>
</body>
</html>