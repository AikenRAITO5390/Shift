<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>

<c:import url="../../common/header.jsp"/>

<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>シフト作成</title>

<style>
.ShiftMain{
	margin-top: 60px;
    display: flex;
    justify-content: center;
    align-items: center;
    font-size: 20px;
}

.buttom{
	 display: flex;
    justify-content: center;
    align-items: center;
	 margin-top: 30px;

}

.buttom button{
	 width: 140px;
	 height: 60px;
	 font-size: 20px;
	 background-color: #6495ED;

}


 .aAction {
 	 display: flex;
    justify-content: center;
    align-items: center;
	margin-left: 450px;
	margin-top: 30px;
}

            .underline {
             display: flex;
			 justify-content: center;
			 align-items: center;

            position: relative;
        }
        .underline::after {
            content: '';
            position: absolute;
            left: 600px;
            right: 600px;
            bottom: -10px; /* 下に飛び出す部分の高さ */
            height: 2px; /* 線の太さ */
            background-color: orange;
        }
        .underline::before {
            content: '';
            position: absolute;
            left: 50%;
            transform: translateX(-50%);
            bottom: -20px; /* 下に飛び出す部分の位置 */
            width: 0;
            height: 0;
            border-left: 10px solid transparent;
            border-right: 10px solid transparent;
            border-top: 10px solid orange; /* 下向きの三角形 */
        }

.shimmer {
    font-size: 25px;
    font-weight: bold;
    background: linear-gradient(90deg, #f3ec78, #af4261, #f3ec78);
    background-size: 200% 200%;
    color: transparent;
    background-clip: text;
    -webkit-background-clip: text;
    animation: shimmer 3s infinite;
}

@keyframes shimmer {
    0% {
        background-position: 0% 50%;
    }
    50% {
        background-position: 100% 50%;
    }
    100% {
        background-position: 0% 50%;
    }
}



</style>


</head>
<body>
<div class="ShiftMain">
<h1>～シフト作成～</h1>
</div>
 <div class="underline">
 <div class="shimmer">シフトを作成する</div>
 </div>
<div class="buttom">
<%--ShiftCreateActionにボタンで飛ばす --%>
<button onclick="location.href='ShiftCreate.action'" >📜作成</button>
</div>
<div class =" aAction">
<a href="Main.action">メインへ戻る</a>
</div>
</body>

<c:import url="../../common/footer.jsp"/>

</html>