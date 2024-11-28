<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>売上</title>
</head>

<c:import url="../../common/header.jsp"/>

<div class="tmain">

	<h2>売上</h2>



	<form action="SalesInput.action" method="post">

	<button>売上入力</button>

	</form>



	<form action="SalesDetail.action" method="post">

	<button>売上予測</button>

	</form>

</div>

<c:import url="../../common/footer.jsp"/>

</html>