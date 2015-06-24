<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<%@ include file="/common/common.jsp"%>
<html>
<head>
<meta http-equiv="content-type" content="text/html; charset=UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<title>人生百科</title>
<meta name="description" content="">
<meta name="keywords" content="">
<meta name="viewport" content="width=device-width, initial-scale=1, user-scalable=yes">

<link rel="stylesheet" href="/css/base.css">
<link rel="stylesheet" href="/css/main.css">
<link rel="stylesheet" href="/css/mask.css">
<link rel="stylesheet" href="/css/pad.css" media="only screen and (min-width : 768px) and (max-width : 1200px)">

<script type="text/javascript" src="/js/jquery-1.11.1.min.js"></script>
<!-- HTML5 shim and Respond.js for IE8 support of HTML5 elements and media queries -->
<!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
<!--[if lt IE 9]>
<script src="/js/html5shiv.min.js"></script>
<script src="/js/respond.min.js"></script>
<![endif]-->
<!--[if IE 6]>
<script type="text/javascript" src="/js/DD_belatedPNG.js"></script>
<script type="text/javascript">DD_belatedPNG.fix('*');</script>
<![endif]-->
<!--修复IE6下PNG图片背景透明-->

<style type="text/css">
#banner {
	background-color: grey;
	position: absolute;
	width: 100%;
	height: 100%;
}
#banner div {
	position: fixed;
	width: 100%;
	height: 100%;
	display: none;
	text-align:center;
	top: 80px;
}
#skip {
	cursor: pointer;
	position: absolute;
	bottom: 30px;
	right: 20px;
	font: normal 18px/24px solid;
	width: 100px;
}

@media screen and (max-width:600px) {
	img{max-height: 160px; }
}
</style>
</head>

<body>
	<div id="banner">
		<c:set var="bannerArray" value="${fn:split('images/commemorate1.gif,images/commemorate2.gif,images/commemorate1.gif', ',') }" />
		<c:forEach var="banner" items="${bannerArray }" varStatus="ind">
			<div id="imgdiv_${ind.index }">
				<img src="/${banner}">
			</div>
		</c:forEach>
	</div>
	<div id="skip" onclick="turnToIndex();">skip >></div>
</body>

<form id="channelForm" action="/channel/life.do" method="post" >
</form>

<script type="text/javascript" src="/js/common.js"></script>
<script type="text/javascript" src="/js/business.js"></script>
<script type="text/javascript">

$(function(){
	//var imgCount = $("#banner div").length;
	//alert(imgCount);
	$("#banner div").each(function(i){
		// 循环是倒着来的， 所以应该反过来算
		if(i == 0){
			$(this).show();
			// 如果是第一张
			$(this).click(function(){
				cancelBubble();
				next(this);
			});
		} else {
			$(this).click(function(){
				cancelBubble();
				turnToIndex();
			});
		}
	});
});

function next(obj){
	//alert(obj.id);
	$(obj).fadeOut();
	$(obj).next().fadeIn();
	//$(obj).prev().slideDown();
}

//setTimeout("enter()", 4000);
</script>
</html>