<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>

<title>パワーバランス設定</title>

<style>
.Point_Setting {
    display: flex;
    justify-content: flex-start;
    align-items: flex-startr; /* 子要素の高さを揃える */
    /*margin-top: 20px; ヘッダーの高さ分だけ下に配置
    margin-left: 10%;*/
    max-width: 100%; /* 最大幅を指定 */
    width: 80%;
    margin: 20px auto; /* 左右の余白を均等に */
    height: auto;
}
.power_Setting h1{
	text-align: center;
	margin-top: 70px;
}
/*日付*/
.power_Setting p{
	text-align: center;
	margin-top: 30px;
}
/*曜日と枠線*/
.week_Setting, .point_conetnt {
    padding: 0 5px; /* 各カラムに左右のパディングを追加 */
}
/*一週間分のテーブル*/
.week_Setting {
	display: flex;/*追加*/
    width: 25%; /* 必要に応じて幅を設定 */
    /*height: 10px;  縦の長さを指定 */
    /*min-height: 50px;  適切な高さを設定 */
    /*padding: 5px;*/
    flex-direction: column;/*追加*/
    align-items: center;/*追加*/
    padding: 10px;/*追加*/


}
/*一か月分の入力された点数*/
.point_conetnt input[type="number"]{
    width: 100%; /* 必要に応じて幅を設定 */
    padding: 3px; /* 余白を小さく */
    margin: 2px 0; /* 上下の間隔を小さく */
    font-size: 14px; /* フォントサイズを調整 */
}
/*２つの変更ボタン*/
.power_Setting button{
	text-align: center;
}
/*スクロールバー*/
.scrollable-vertical {
    height: 300px; /* 必要に応じて調整 */
    overflow-y: scroll; /* 縦方向のスクロールバーを表示 */
    /*width: 80%; 画面の幅いっぱいに広げる */
    width: calc(100% -10px); /* 余白を防ぐ */
    box-sizing: border-box; /* パディングとボーダーを含めた全体の幅と高さを指定 */
    overflow-x: hidden; /* 横方向のスクロールバーを非表示 */
    max-width: 100%;  /*最大幅を指定して余分なスペースを防ぐ*/
    margin-left: 20px;
    display: flex;
    flex-direction: column;
    position: relative;/* ボタンを右下に固定するため */
}
/*一か月のカレンダー表示する*/
.point_conetnt{
	border: 1px solid ; /* 枠線を設定 */
	background-color: #FFFFFF; /* 背景色を設定 */
    padding: 10px; /* 枠線と内容の間にスペースを追加 */
    border-radius: 5px; /* 角を丸くする */
    /*display: inline-block; 文字の幅だけ線をひく*/
    display: flex;
    flex-direction: column; /* 縦並びにする */
    width: 55%; /* 画面の幅いっぱいに広げる */
    height: 300px; /* 縦の長さを指定 */
    position: relative; /* ボタンを絶対位置指定するため */

}
.point_conetnt>div:last-of-type{
    margin-left: auto;
}
/*一か月の日付*/
.point_conetnt table{
	margin-left: 0;
	width: 50%;
}

/*一か月の日付*/
.point_conetnt label{
	/*margin-left: 32px;
	width: 85%; 画面の幅いっぱいに広げる */
	display: flex;
    align-items: center;
    justify-content: space-between;
    gap:5px; /* ラベルと入力欄の間を狭くする */
    width: 100%;
    padding: 5px 0;
}
/*土曜に色*/
.week_Setting tr:nth-child(6) input[type="number"] {
    background-color: #A4C6FF; /* 必要に応じて色を変更 */
    color: inherit; /* 文字色をデフォルトに設定 */
}
/*日曜に色*/
.week_Setting tr:nth-child(7) input[type="number"] {
    background-color: #FF69A3; /* 必要に応じて色を変更 */
    color: inherit; /* 文字色をデフォルトに設定 */
}
/*一週間の曜日*/
.week_Setting tr {
    padding-bottom: 15px; /* 必要に応じて調整 */

}
/*一週間の日付と土曜と日曜の点数*/
.week_Setting td {
    padding-bottom: 15px; /* 必要に応じて調整 */
}
/*変更ボタン*/
.week_Setting button{
	margin-top: 10px;
	margin-left: 37%;
}
/*変更ボタン*/
.button-container{
    position: absolute;
    right: -70px; /* ボタンを point_conetnt の右側面に配置 */
    top: 95%;
    transform: translateY(-50%); /* 垂直中央揃え */
    display: flex;

}

/* ボタンのスタイル */
.button-container button {
    font-size: 14px;
    padding: 8px 16px;
    color: white;
    border: none;
    border-radius: 5px;
    cursor: pointer;
    white-space: nowrap; /* ボタン内のテキストが折り返されないように */
}


/*トップページに戻るリンク*/
.power_Setting a{
	margin-left: 80%;
	margin-top: 10px;
}
/*hr*/
.hr {
	width: 200%;
}


/* ラベル部分 */
.point_conetnt label span {
    min-width: 20px; /* ラベルの幅を固定 */
    text-align: right; /* 右寄せにする */
}

/* レスポンシブ対応 */
@media screen and (max-width: 768px) {
    .Point_Setting {
        margin-left: 5%;
        flex-direction: column;
    }
    .week_Setting {
        width: 100%;
    }
}
</style>


<head>

<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

</head>
<c:import url="../../common/header.jsp"/>
<body>
<div class = "power_Setting">

	<h1>～パワーバランス設定～</h1>
	<p>"${toDay}分"</p>
	<a href="Main.action">トップページに戻る</a>

<!-- 2つの表を横の並べるためのやつ！ -->
<div class = "Point_Setting">


<!-- 一週間の必要点数を設定する -->
<div class = "week_Setting">
<form id="myForm" action="PowerSettingResult.action" method="post">
    <table class="table table-hover">
        <c:forEach var="i" begin="0" end="6">
            <tr>
                <td><label>${fn:substring('月火水木金土日', i, i+1)}</label></td>
                <td>
                  <input type="number" name="WeekScore_${i}" id="WeekScore_${i}" />
                   <span class="error" id="WeekScore_${i}Error"></span>

                </td>
                <input type="hidden" name="WorkWeekScore_${i}" value="${fn:substring('1234567', i, i+1)}">
            </tr>
        </c:forEach>
    </table>

    <script>
        // 初期値を設定
        <c:forEach var="i" begin="0" end="6">
            document.getElementById("WeekScore_${i}").value = "";
        </c:forEach>

        // power_listの値を設定
        // power_list[曜日を数字にしたもの][曜日の点数]
        <c:forEach var="i" begin="0" end="6">
            <c:forEach var="power" items="${power_list}">
            // もし曜日を数字にしたものと数字が同じなら
                <c:if test="${power[0] == (i+1).toString()}">
           // WeekScoreに元々張っている数字を入れる
                    document.getElementById("WeekScore_${i}").value = "${power[1]}";
                </c:if>
            </c:forEach>
        </c:forEach>

    </script>
    <button type="submit">変更</button>
</form>
</div>


<!-- 一か月の必要点数を設定する -->
<div class = point_conetnt>
	<form id = 'dayForm' action="DayPowerSettingResult.action" method="post">

		<!-- スクロールバーつける -->
		<div class="scrollable-vertical">
		    <table class="table table-hover">
		        <c:forEach var="dateMap" items="${dateList}" varStatus="loopStatus">
		            <c:forEach var="entry" items="${dateMap.entrySet()}">
		                <tr>
		                    <td><label>${loopStatus.index + 1}</label></td>
		                    <td>
		                        <input type="number" name="DayScore_${loopStatus.index + 1}" id="DayScore_${loopStatus.index + 1}">
		                         <span class="error" id="DayScore_${loopStatus.index + 1}Error"></span>
		                		<div class="hr">
		                		<hr>
		                		</div>
		                    </td>
		                    	<input type="hidden" name="WorkDayScore_${loopStatus.index + 1}" value="${entry.key}">
		                </tr>
		            </c:forEach>
		        </c:forEach>
		    </table>
		 </div>
		 <div class="button-container">
        	<button type="submit">変更</button>
		 </div>
	</form>
	</div>


	<script>

	 //　dateList[日付][その時の点数]

	<c:forEach var="dateMap" items="${dateList}" varStatus="loopStatus">
		<c:forEach var="entry" items="${dateMap.entrySet()}">
			<c:forEach var="power" items="${power_list}">
	    		<c:if test="${power[0] == entry.value.toString()}">
	        	document.getElementById("DayScore_${loopStatus.index + 1}").value = "${power[1]}";
	    		</c:if>
	    	</c:forEach>
		</c:forEach>
	</c:forEach>

	<c:forEach var="dateMap" items="${dateList}" varStatus="loopStatus">
		<c:forEach var="entry" items="${dateMap.entrySet()}">
			<c:forEach var="dateMap" items="${datePoint}" varStatus="pointStatus">
				<c:forEach var="pointy" items="${dateMap.entrySet()}">
    				<c:if test="${pointy.key == entry.key}">
        	 			document.getElementById("DayScore_${loopStatus.index + 1}").value = "${pointy.value}";
    				</c:if>
    			</c:forEach>
    		</c:forEach>
		</c:forEach>
	</c:forEach>


	document.addEventListener('DOMContentLoaded', function() {
	    console.log("ページが読み込まれました");

	    document.getElementById('myForm').addEventListener('submit', function(event) {
	        var isValid = true;
	        var pointMax = ${pointMax};

	        for (var i = 0; i < 7; i++) {
	            var weekScore = document.getElementById('WeekScore_' + i);
	            var weekScoreError = document.getElementById('WeekScore_' + i + 'Error');
	            var value = weekScore.value.trim();
	            if (weekScore.value.trim() === "") {
	                weekScore.value = ""; // 入力値をクリア
	                weekScore.placeholder = "数字を入力してください";
	                weekScore.classList.add('error-placeholder');
	                weekScore.style.color = "red";
	                isValid = false;
	            } else if (isNaN(Number(value))) {
	                weekScore.value = ""; // 入力値をクリア
	                weekScore.placeholder = "数字のみ入力してください";
	                weekScore.classList.add('error-placeholder');
	                weekScore.style.color = "red";
	                isValid = false;
	            } else if (Number(value) < 0 || Number(value) >pointMax) {
	                weekScore.value = ""; // 入力値をクリア
	                weekScore.placeholder = "0以上"+ pointMax +"以下の数字を入力してください";
	                weekScore.classList.add('error-placeholder');
	                weekScore.style.color = "red";
	                isValid = false;
	            }else {
	                weekScore.placeholder = "";
	                weekScore.classList.remove('error-placeholder');
	            }
	        }
	        if (!isValid) {
	            event.preventDefault();
	        }
	    });

	    document.getElementById('dayForm').addEventListener('submit', function(event) {
	        var isValiDay = true;
	        var Maxpo = ${pointMax};
	    	<c:forEach var="dateMap" items="${dateList}" varStatus="loopStatus">
	            var dayScore = document.getElementById('DayScore_${loopStatus.index + 1}');
	            var dayScoreError = document.getElementById('DayScore_${loopStatus.index + 1}Error');

	            var value = dayScore.value.trim();
	            if (dayScore.value.trim() === "") {
	                dayScore.value = ""; // 入力値をクリア
	                dayScore.placeholder = "数字を入力してください";
	                dayScore.classList.add('error-placeholder');
	                dayScore.style.color = "red";
	                isValiDay = false;
	            } else if (isNaN(Number(value))) {
	            	dayScore.value = ""; // 入力値をクリア
	            	dayScore.placeholder = "数字のみ入力してください";
	            	dayScore.classList.add('error-placeholder');
	            	dayScore.style.color = "red";
	            	isValiDay = false;
	            } else if (Number(value) < 0 || Number(value) > Maxpo) {
	            	dayScore.value = ""; // 入力値をクリア
	            	dayScore.placeholder = "0以上"+ Maxpo +"以下の数字を入力してください";
	            	dayScore.classList.add('error-placeholder');
	            	dayScore.style.color = "red";
	            	isValiDay = false;
	            }else {
	                dayScore.placeholder = "";
	                dayScore.classList.remove('error-placeholder');
	            }
	            </c:forEach>
	        console.log("DayScore isValid: " + isValiDay);

	        if (!isValiDay) {
	            console.log("フォーム送信を防止します");
	            event.preventDefault();
	        }
	    });
	});
	</script>

</div>
<c:import url="../../common/footer.jsp"/>

</body>
</html>
