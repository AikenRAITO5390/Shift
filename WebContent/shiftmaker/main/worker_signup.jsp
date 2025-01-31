<%-- 従業員情報登録（シフト作成者側）JSP --%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<!-- bootstrapと繋げる -->
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-9ndCyUaIbzAi2FUVXJi0CjmCapSmO7SnpJef0486qhLnuZ2cdeRhO02iuK6FUUVM" crossorigin="anonymous">
<!-- css -->
<link rel="stylesheet" href="../../css/style_k.css">

<html>

<title>従業員情報登録</title>

<style>
.sita {
	margin-top : 60px;
}
</style>

<c:import url="../../common/header.jsp"/>

<body>

	<!-- 全体囲い -->
	<div class="body">

		<!-- 画面タイトル -->
		<div class="h2">
			<h2>～従業員情報登録～</h2>
		</div>
		<!-- 余白 -->
		<div class="mt-3"></div>

		<form id="myForm" class="center" action="WorkerSignUpResult.action" method="post">

			<table>

				<!-- ID入力 -->
				<tr>
					<th>
						<label>ID</label>
					</th>
					<td>
						<input type="text" name="worker_id" placeholder="従業員IDを入力してください" size="30" maxlength="10" value="${worker_id}" required />
						<div>${errors.get("worker_id")}</div>
						<!-- 余白 -->
						<div class="mt-3"></div>
					</td>
				</tr>


				<!-- 名前入力 -->
				<tr>
					<th>
						<label>名前</label>
					</th>
					<td>
						<input type="text" name="worker_name" placeholder="名前を入力してください" size="30" maxlength="30" value="${worker_name}" required />
						<div>${errors.get("worker_name")}</div>
						<div class="mt-3"></div>
					</td>
				</tr>


				<!-- 生年月日選択 -->
				<tr>
					<th>
		        		<label>生年月日</label>
		        	</th>
					<td>
						<select name="year" required>
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
						<div class="mt-3"></div>
					</td>
				</tr>



				<!-- 住所入力 -->
				<tr>
					<th>
						<label>住所</label>
					</th>
					<td>
						<input type="text" name="worker_address" placeholder="住所を入力してください" size="30" maxlength="40" value="${worker_address}" required />
						<div>${errors.get("worker_address")}</div>
						<div class="mt-3"></div>
					</td>
				</tr>


				<!-- 電話番号入力 -->
				<tr>
					<th>
						<label>電話番号</label>
					</th>
					<td>
						<input type="text" name="worker_tel" id="worker_tel" placeholder="電話番号を入力してください" size="30" maxlength="15" value="${worker_tel}"  />
						 <span class="error" id="worker_telError"></span>
						<div class="mt-3"></div>
					</td>
				</tr>


				<!-- パスワード入力 -->
				<tr>
					<th>
						<label>パスワード</label>
					</th>
					<td>
						<input type="text" name="worker_password" placeholder="パスワードを入力してください" size="30" pattern="^[a-zA-Z0-9]+$" minlength="4" maxlength="20" value="${worker_password}" required />
						<div>${errors.get("worker_password")}</div>
						<div class="mt-3"></div>
					</td>
				</tr>


				<!-- 店情報選択 -->
				<tr>
					<th>
						<label>店情報</label>
					</th>
					<td>
				        <select name="store_name">
				            <c:forEach var="storeName" items="${store_name_set}">
				                <option value="${storeName}">${storeName}</option>
				            </c:forEach>
				        </select>
				        <div class="mt-3"></div>
				    </td>
				</tr>

				</table>


				<!-- ラジオボタン選択（社員はTrue,アルバイトはFalseで判別） -->
				<!-- ラジオボタン横並び -->
				<div>
				    <div class="form-check form-check-inline">
				        <input class="form-check-input" type="radio" id="employee" name="worker_judge" value="true" required>
				        <label class="form-check-label" for="employee">社員</label>
				    </div>
				    <div class="form-check form-check-inline">
				        <input class="form-check-input" type="radio" id="parttime" name="worker_judge" value="false" required>
				        <label class="form-check-label" for="parttime">アルバイト</label>
				    </div>
				</div>
				<!-- 余白あける -->
				<div class="mt-2"></div>


	        <!-- 登録ボタン -->
	        <input type="submit" class="btn btn-primary" value="登録" />
	        <!-- 戻るリンク -->
	        <a class="link1" href="Main.action">戻る</a>

		</form>

<!-- 余白 -->
<div class="sita"></div>

</div>

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

<c:import url="../../common/footer.jsp"/>

</html>