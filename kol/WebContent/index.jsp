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
<script type="text/javascript" src="/js/jquery.SuperSlide.2.1.1.js"></script>
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
}
#banner div {
position:absolute;
}

</style>
</head>

<body>
	<div id="banner">
		<c:set var="bannerArray" value="${fn:split('images/commemorate1.gif,images/commemorate1.gif,images/commemorate1.gif', ',') }" />
		<c:forEach var="banner" items="${bannerArray }">
			<div>
			<a id="enter" href="javascript:void(0);"><img src="/${banner}"></a>
			</div>
		</c:forEach>
	</div>
</body>

<form id="channelForm" action="/channel/life.do" method="post" >
</form>

<script type="text/javascript" src="/js/common.js"></script>
<script type="text/javascript" src="/js/business.js"></script>
<script type="text/javascript">
function enter(){
	$("#enter").click();
}

$(function(){
	//var imgCount = $("#banner div").length;
	//alert(imgCount);
	$("#banner div").each(function(i){
		// 循环是倒着来的， 所以应该反过来算
		if(i == 0){
			// 如果是第一张
			$(this).click(function(){
				cancelBubble();
				turnToIndex();
			});
		} else {
			$(this).click(function(){
				cancelBubble();
				next(this);
			});
		}
		
	});
});

function next(obj){
	//alert(obj.tagName);
	$(obj).slideUp();
	$(obj).nextSibling().slideDown();
}

//setTimeout("enter()", 4000);
</script>
</html>