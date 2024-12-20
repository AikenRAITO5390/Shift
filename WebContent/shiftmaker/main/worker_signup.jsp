<%-- 従業員情報登録（シフト作成者側）JSP --%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<!-- bootstrapと繋げる -->
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-9ndCyUaIbzAi2FUVXJi0CjmCapSmO7SnpJef0486qhLnuZ2cdeRhO02iuK6FUUVM" crossorigin="anonymous">

<html>

<c:import url="../../common/header.jsp"/>

<body>
<div class="body">

		<!-- 上に余白空けてタイトル表示 ＋ 中央配置 -->
		<div class="mt-5 text-center">

		<!-- 画面タイトル -->
		<h2>従業員情報登録</h2>
		<!-- 上に余白 -->
		<div class="mt-3"></div>

		<form action="WorkerSignUpResult.action" method="post">

			<!-- ID入力 -->
			<div class="row mb-3">
				<label class="col-sm-2 col-form-label">ID</label>
				<div class="col-sm-5">
					<input class="form-control" type="text" name="worker_id" placeholder="従業員IDを入力してください" maxlength="10" value="${worker_id}" required />
					<div>${errors.get("worker_id")}</div>
				</div>
				<!-- 余白あける -->
				<div class="mt-2"></div>
			</div>


			<!-- 名前入力 -->
			<label>名前</label>
			<input type="text" name="worker_name" placeholder="名前を入力してください" maxlength="30" value="${worker_name}" required />
			<div>${errors.get("worker_name")}</div>
			<!-- 余白あける -->
			<div class="mt-2"></div>


			<!-- 生年月日選択 -->
	        <label>生年月日</label>
			<select name="year" required>
			    <option value="">年を選択</option>
			    <c:forEach var="year" items="${year_list}">
			        <option value="${year}" ${year == selectedYear ? 'selected' : ''}>${year}</option>
			    </c:forEach>
			</select>
			<select name="month" required>
			    <option value="">月を選択</option>
			    <c:forEach var="month" items="${month_list}">
			        <option value="${month}" ${month == selectedMonth ? 'selected' : ''}>${month}</option>
			    </c:forEach>
			</select>
			<select name="day" required>
			    <option value="">日を選択</option>
			    <c:forEach var="day" items="${day_list}">
			        <option value="${day}" ${day == selectedDay ? 'selected' : ''}>${day}</option>
			    </c:forEach>
			</select>
			<!-- 余白あける -->
			<div class="mt-2"></div>


			<!-- 住所入力 -->
			<label>住所</label>
			<input type="text" name="worker_address" placeholder="住所を入力してください" maxlength="40" value="${worker_address}" required />
			<div>${errors.get("worker_address")}</div>
			<!-- 余白あける -->
			<div class="mt-2"></div>


			<!-- 電話番号入力 -->
			<label>電話番号</label>
			<input type="text" name="worker_tel" placeholder="電話番号を入力してください" maxlength="15" value="${worker_tel}" required />
			<div>${errors.get("worker_tel")}</div>
			<!-- 余白あける -->
			<div class="mt-2"></div>


			<!-- パスワード入力 -->
			<label>パスワード</label>
			<input type="text" name="worker_password" placeholder="パスワードを入力してください" maxlength="20" value="${worker_password}" required />
			<div>${errors.get("worker_password")}</div>
			<!-- 余白あける -->
			<div class="mt-2"></div>


			<!-- 店情報選択 -->
			<label>店情報</label>
	        <select name="store_name">
	            <c:forEach var="storeName" items="${store_name_set}">
	                <option value="${storeName}">${storeName}</option>
	            </c:forEach>
	        </select>
	        <!-- 余白あける -->
			<div class="mt-2"></div>


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
	        <a href="Main.action">戻る</a>

		</form>


</div>
</div>

</body>

<c:import url="../../common/footer.jsp"/>

</html>