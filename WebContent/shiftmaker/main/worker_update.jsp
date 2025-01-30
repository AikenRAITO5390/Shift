<%-- 従業員更新JSP --%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>

<title>従業員情報変更</title>

<style>
.h2 h2{
	margin-top : 60px;
	text-align : center;
}
.worker_update {
  display: flex;
  flex-direction: column;
  align-items: center; /* 垂直方向の中央揃え */
}

.worker_update div {
  display: flex;
  align-items: center; /* ラベルと入力フィールドを垂直方向に中央揃え */
}

.worker_update label {
  color: white;
  border: 1px solid white; /* 白い枠線を設定 */
  background-color: #6495ED; /* 背景色を設定 */
  padding: 5px; /* 枠線と内容の間にスペースを追加 */
  width: 100px;
  margin-right: 10px; /* ラベルと入力フィールドの間にスペースを追加 */
  display: inline-block;
  text-align: center; /* ラベルのテキストを右揃え */
}

.worker_update input[type="text"]{
  border: 1px solid black; /* 枠線を設定 */
  padding: 5px; /* 枠線と内容の間にスペースを追加 */
  display: inline-block;
  width: 300px; /* 入力フィールドの幅を設定 */
  box-sizing: border-box;
}
.worker_update select {
  border: 1px solid black; /* 枠線を設定 */
  padding: 5px; /* 枠線と内容の間にスペースを追加 */
  display: inline-block;
  width: 95px; /* 入力フィールドの幅を設定 */
  margin-right: 5px;
  box-sizing: border-box;

}

.submit{
			display: flex;
   			 justify-content: center; /* 水平方向に中央揃え */
    		align-items: center;
}

.submit input[type="submit"]{
    background-color: #6495ED;
    color: white;
    border: 1px solid white;
    padding: 10px 20px; /* ボタンの内側の余白を調整 */
     width:  100px;
    display: inline-block;
    margin-left: 700px;

}

.link_a{
			display: flex;
   			 justify-content: center; /* 水平方向に中央揃え */
    		align-items: center;
}

.link_a{
    margin-left: 900px;
    margin-top: -20px;

}


.nenn{
	margin-left: 1.2%;
}
.btn {
    margin-left: 20%;
}



</style>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
</head>
<body>
<c:import url="../../common/header.jsp"/>

<div class="h2">
	<h2>～従業員情報変更～</h2>
</div>

	<form id="myForm"  action = "WorkerUpdateExecute.action" method="post">
<div class = "worker_update">
<div>
		<label>ID</label>
		<input type="text" name="WORKER_ID" size="70" value="${worker.workerId}" required readonly>
</div>
<br>
<div>
		<label>名前</label>
		<input type ="text"  name="WORKER_NAME" size="70" value="${worker.workerName}" required maxlength="30">
</div>
<br>

<div>
		<label>生年月日</label>
	    <!-- 年、月、日を選択するフォーム -->
		    <select name="year" class="nenn" required>
				<option value="">年</option>
					<c:forEach var="year" items="${year_list}">
						<option value="${year}" ${year == selectedYear ? 'selected' : ''}>${year}</option>
					</c:forEach>
			</select>
			<select name="month" required>
				<option value="">月</option>
					<c:forEach var="month" items="${month_list}">
						<option value="${month}" ${month == selectedMonth ? 'selected' : ''}>${month}</option>
					</c:forEach>
			</select>
			<select name="day" required>
				<option value="">日</option>
					<c:forEach var="day" items="${day_list}">
						 <option value="${day}" ${day == selectedDay ? 'selected' : ''}>${day}</option>
					</c:forEach>
			</select>

</div>
<br>
<div>
		<label>住所</label>
		<input type ="text"  name="WORKER_ADDRESS" size="70"  value="${worker.workerAddress}" required maxlength="40">
</div>
<br>
<div>
		<label>電話番号</label>
		<input type ="text"  name="WORKER_TEL" size="70" id="worker_tel" value="${worker.workerTel}" required maxlength="15">
		<span class="error" id="worker_telError"></span>
</div>
<br>
<div>
		<label>パスワード</label>

		<input type="text" name="WORKER_PASSWORD" size="70" value="${worker.workerPassword}" required maxlength="20">
</div>
<br>
<div>
		<label>店情報</label>
		<input type ="text"  name="STORE_NAME"  size="70" value="${stores.storeName}"required>

</div>
</div>
<div class ="submit">

		<input type="submit" class="btn" value="変更">
</div>

<div class ="link_a">
		<a  href="WorkerList.action">戻る</a>
		</div>
	</form>


<c:import url="../../common/footer.jsp"/>

<script>
document.addEventListener('DOMContentLoaded', function() {
    console.log("ページが読み込まれました");
    document.getElementById('myForm').addEventListener('submit', function(event) {
        var isValid = true;
        var phonePattern = /^[0-9-]+$/;

        // メールアドレスの検証
        var worker_tel = document.getElementById('worker_tel');
        var worker_telError = document.getElementById('worker_telError');
        if (worker_tel.value.trim() === "") {
        	worker_tel.value = ""; // 入力値をクリア
        	worker_tel.placeholder = "電話番号を入力してください";
        	worker_tel.classList.add('error-placeholder');
        	worker_tel.style.color = "red";
            isValid = false;
        } else if (worker_tel.value.length > 30) {
        	worker_tel.value = ""; // 入力値をクリア
        	worker_tel.placeholder = "電話番号は30文字以内で入力してください";
        	worker_tel.classList.add('error-placeholder');
        	worker_tel.style.color = "red";
            isValid = false;
        } else if (!phonePattern.test(worker_tel.value)) {
        	worker_tel.value = ""; // 入力値をクリア
        	worker_tel.placeholder = "有効な電話番号を入力してください";
        	worker_tel.classList.add('error-placeholder');
        	worker_tel.style.color = "red";
            isValid = false;
        } else {
        	worker_tel.placeholder = "";
        	worker_tel.classList.remove('error-placeholder');
        }

    if (!isValid) {
        event.preventDefault();
    }
});
});

</script>
</body>
</html>