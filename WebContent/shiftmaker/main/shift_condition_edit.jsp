<%-- 従業員シフト条件変更JSP --%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<!-- bootstrapと繋げる -->
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-9ndCyUaIbzAi2FUVXJi0CjmCapSmO7SnpJef0486qhLnuZ2cdeRhO02iuK6FUUVM" crossorigin="anonymous">
<!-- css -->
<link rel="stylesheet" href="../../css/style_k.css">

<html>

<head>
    <title>シフト条件変更</title>
</head>

<style>
.body label{
	background-color: #6495ED; /* 背景色を水色に設定 */
    color: white;
    border: 2px solid white;
}

</style>

<c:import url="../../common/header.jsp"/>

<body>

<script>
/* JavaScriptで星型評価を実装 */
function setRating(rating) {
    const stars = document.querySelectorAll('.star'); // すべての星を取得
    stars.forEach((star, index) => {
        if (index < rating) {
            star.classList.add('selected'); // 選択された星にクラスを追加
        } else {
            star.classList.remove('selected'); // 選択解除
        }
    });
    document.getElementById('workerScore').value = rating; // hidden inputに値を設定
}
</script>

<div class="body">


		<!-- 画面タイトル -->
		<div class="h2">
			<h2>～シフト条件変更～</h2>
		</div>

		<!-- 余白 -->
			<div class="mt-5"></div>

		<form class="center" action="ShiftConditionEditResult.action" method="post">

			<table class="edit_table">

			    <!-- IDの表示（変更不可） -->
			    <tr>
					<th>
			    		<label class="label1">ID</label>
			    	</th>
					<td class="edit_td">
					    <span>${worker.workerId}</span>
						<input type="hidden" name="workerId" value="${worker.workerId}" />
						<!-- 余白 -->
						<div class="mt-3"></div>
					</td>
				</tr>

			    <!-- 名前の表示（変更不可） -->
			    <tr>
					<th>
					    <label class="label1">名前</label>
					</th>
					<td class="edit_td">
					    <span>${worker.workerName}</span>
						<input type="hidden" name="workerName" value="${worker.workerName}" />
						<!-- 余白 -->
						<div class="mt-3"></div>
					</td>
				</tr>

			    <!-- ポジション選択 -->
			    <tr>
					<th>
					    <label class="label1">ポジション</label>
					</th>
					<td class="edit_td">
					    <select  class="form-select" aria-label="Default select example" name="workerPosition">
						    <c:forEach var="position" items="${positions}">
						        <option value="${position.key}" ${position.key == worker.workerPosition ? 'selected' : ''}>
						            ${position.value}
						        </option>
						    </c:forEach>
						</select>
						<!-- 余白 -->
						<div class="mt-3"></div>
					</td>
				</tr>

			    <!-- 点数入力 -->
			    <tr>
					<th>
					    <label class="label1">点数</label>
					</th>
					<td class="edit_td">
						<div class="star-rating">
		                    <!-- 星型アイコンを作成 -->
		                    <span class="star" onclick="setRating(1)">&#9733;</span>
		                    <span class="star" onclick="setRating(2)">&#9733;</span>
		                    <span class="star" onclick="setRating(3)">&#9733;</span>
		                    <span class="star" onclick="setRating(4)">&#9733;</span>
		                    <span class="star" onclick="setRating(5)">&#9733;</span>
		                </div>
					    <input type="hidden" id="workerScore" name="workerScore" value="${worker.workerScore}" min="1" max="5" required />
						<!-- 余白 -->
						<div class="mt-3"></div>
					</td>
				</tr>

				</table>

				<!-- 変更ボタン -->
				<input type="submit" class="btn btn-primary" value="変更" />

			    <!-- 戻るリンク -->
			    <a class="link1" href="ShiftConditionWorkerList.action">戻る</a>

		</form>


</div>

</body>

<c:import url="../../common/footer.jsp"/>

</html>