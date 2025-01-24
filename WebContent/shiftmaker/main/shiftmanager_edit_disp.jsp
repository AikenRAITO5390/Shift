<%-- 学生更新JSP --%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>

<title>シフト作成者変更</title>

<head>
<%-- cssを取得してくる --%>

<link rel="stylesheet" href="../../css/style.css">
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
</head>
<body>
<%-- headerとサイドバーの取得 --%>
<c:import url="../../common/header.jsp"/>
<div class = "shift_manager">

	<h2>～シフト作成者変更～</h2>
<%-- 次とばす場所の設定 --%>
        <form id="myForm" action="ShiftManagerSignupResult.action" method="post">
<%-- css設定のためのdiv_class --%>
<%-- 店舗ID --%>
<div class ="form-group">
		<label>店舗ID</label>
		<input type="text" value="${storeId}">

</div>
<%--名前  --%>
<div class ="form-group">
		<label>名前</label>
		<input type="text" name="managerName" id="managerName" placeholder="名前を入力してください"
			value="${managerName}" />
			  <span class="error" id="nameError"></span>
</div>

<div class ="form-group">
<%-- パスワード --%>
		<label>パスワード</label>
		<input type="text" name="password" id="password" placeholder="パスワードを入力してください"
			value="${password}" />
			  <span class="error" id="passwordError"></span>
</div>

<div class ="form-group">
	<%-- メール --%>
		<label>メールアドレス</label>
			<input type="text" id="email" name="email" placeholder="メールを入力してください"
			 value="${email}" />
			<span class="error" id="emailError"></span>

</div>
<div class ="form-group">
	<%-- 店舗名 --%>
		<label>店情報</label>
			<input type="text" name="storeName" id="storeName" placeholder="お店情報を入力してください"
			 value="${storeName}" />
			 <span class="error" id="storeNameError"></span>
</div>

<div class="managert_submit">
    <a href="Main.action">戻る</a>
    <%-- 更新ボタン --%>
    <input type="submit" value="変更">
</div>
</form>


<c:import url="../../common/footer.jsp"/>

</div>

  <script>
  document.addEventListener('DOMContentLoaded', function() {
	    console.log("ページが読み込まれました");
	    document.getElementById('myForm').addEventListener('submit', function(event) {
	        var isValid = true;
	        var emailPattern = /^[^\s@]+@[^\s@]+\.[^\s@]+$/;

	        // メールアドレスの検証
	        var email = document.getElementById('email');
	        var emailError = document.getElementById('emailError');
	        if (email.value.trim() === "") {
	            email.value = ""; // 入力値をクリア
	            email.placeholder = "メールを入力してください";
	            email.classList.add('error-placeholder');
	            email.style.color = "red";
	            isValid = false;
	        } else if (email.value.length > 40) {
	            email.value = ""; // 入力値をクリア
	            email.placeholder = "メールアドレスは40文字以内で入力してください";
	            email.classList.add('error-placeholder');
	            email.style.color = "red";
	            isValid = false;
	        } else if (!emailPattern.test(email.value)) {
	            email.value = ""; // 入力値をクリア
	            email.placeholder = "有効なメールアドレスを入力してください";
	            email.classList.add('error-placeholder');
	            email.style.color = "red";
	            isValid = false;
	        } else {
	            email.placeholder = "";
	            email.classList.remove('error-placeholder');
	        }

	        var managerName = document.getElementById('managerName');
	        var nameError = document.getElementById('nameError');
	        if (managerName.value.trim() === "") {
	        	managerName.value = "";
	        	managerName.placeholder = "名前を入力してください";
	            managerName.classList.add('error-placeholder');
	            managerName.style.color = "red";
	            isValid = false;
	        } else if (managerName.value.length > 30) {
	        	managerName.value = "";
	        	managerName.placeholder = "名前は30文字以内で入力してください";
	        	managerName.classList.add('error-placeholder');
	        	managerName.style.color = "red";
	            isValid = false;
	        } else {
	            managerName.placeholder = "";
	            managerName.classList.remove('error-placeholder');
	        }

	        var storeName = document.getElementById('storeName');
	        var storeNameError = document.getElementById('storeNameError');
	        if (storeName.value.trim() === "") {
	        	storeName.value = "";
	            storeName.placeholder = "店舗名を入力してください";
	            storeName.classList.add('error-placeholder');
	            storeName.style.color = "red";
	            isValid = false;
	        } else if (storeName.value.length > 20) {
	        	storeName.value = "";
	            storeName.placeholder = "店舗名は20文字以内で入力してください";
	            storeName.classList.add('error-placeholder');
	            storeName.style.color = "red";

	            isValid = false;
	        } else {
	            storeName.placeholder = "";
	            storeName.classList.remove('error-placeholder');
	        }

	        var password = document.getElementById('password');
	        var passwordError = document.getElementById('passwordError');
	        if (password.value.trim() === "") {
	        	password.value = "";
	        	password.placeholder = "パスワードを入力してください";
	        	password.classList.add('error-placeholder');
	        	password.style.color = "red";
	            isValid = false;
	        } else if (password.value.length > 20) {
	        	password.value = "";
	        	password.placeholder = "パスワードは20文字以内で入力してください";
	        	password.classList.add('error-placeholder');
	        	password.style.color = "red";
	            isValid = false;
	        } else {
	            password.placeholder = "";
	            password.classList.remove('error-placeholder');
	        }

	        if (!isValid) {
	            event.preventDefault();
	        }
	    });
	});
	</script>
</body>
</html>