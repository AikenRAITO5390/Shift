<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>BBS</title>
</head>
<body>
<c:import url="../../common/header_work.jsp"/>
    <table>
        <tr>
            <p>投稿者: ${user}</p>
   			 <p>コメント: ${text}</p>


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