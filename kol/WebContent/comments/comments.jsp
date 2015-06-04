<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<!DOCTYPE html>
<%@ include file="/common/common.jsp"%>
<html>
<head>
<meta http-equiv="content-type" content="text/html; charset=UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<title>人生百科-评论</title>
<meta name="description" content="">
<meta name="keywords" content="">
<meta name="viewport" content="width=device-width, initial-scale=1, user-scalable=yes">

<link href="${ctx }/css/base.css" rel="stylesheet">
<link rel="stylesheet" href="${ctx }/css/main.css">
<link rel="stylesheet" href="${ctx }/css/mask.css">
<link rel="stylesheet" href="${ctx }/css/pad.css" media="only screen and (min-width : 768px) and (max-width : 1200px)">

<script type="text/javascript" src="${ctx }/js/common.js"></script>
<script type="text/javascript" src="${ctx }/js/jquery-1.11.1.min.js"></script>
<script type="text/javascript" src="${ctx }/js/jquery.SuperSlide.2.1.1.js"></script>
<!-- HTML5 shim and Respond.js for IE8 support of HTML5 elements and media queries -->
<!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
<!--[if lt IE 9]>
<script src="js/html5shiv.min.js"></script>
<script src="js/respond.min.js"></script>
<![endif]-->
<!--[if IE 6]>
<script type="text/javascript" src="js/DD_belatedPNG.js"></script>
<script type="text/javascript">DD_belatedPNG.fix('*');</script>
<![endif]-->
<!--修复IE6下PNG图片背景透明-->
</head>

<body>
	<%@ include file="/common/menu.jsp"%>
	<div class="container">
		<div class="comwidth">
			<c:set var="tesTimonial" value="${res.tesTimonial }" scope="page" />
			<c:set var="commentsArray" value="${res.commentsArray }" scope="page" />
			<h1 style="text-align:left; padding-left:20px;">
			发言内容：${tesTimonial.contents }
			</h1>
			<h1 style="text-align:left; padding-left:20px;">所有评论：<br /><br />
				<c:forEach var="comment" items="${commentsArray }">
					<fmt:parseDate var="dateTemp" value="${comment.createDate }" pattern="yyyyMMddHHmmss" />
					<fmt:formatDate var="createDate" value="${dateTemp }" type="both"/>
					${comment.contents } <br /> ${createDate } <br /><br />
				</c:forEach>
			</h1>
		<div class="clear">
		</div>
		</div>
	</div>
	<%@ include file="/common/footer.jsp"%> 
</body>
<c:remove var="res" scope="session"/>

<form id="channelForm" action="${ctx }/channel/life.do">
</form>
<script type="text/javascript" src="/js/common.js"></script>
<script type="text/javascript" src="/js/business.js"></script>
<script type="text/javascript">

// 
jQuery(".slideBox").slide({
	mainCell : ".bd ul",
	autoPlay : true
});

//alert("${ctx}");
//$d("maskDiv").commentsModule();
function openComments(testimonialsId) {
	var commentsBtn = $d("maskDiv").dialogComments(testimonialsId);
	$("#"+commentsBtn).click(function() {
		//alert("commentsBtn");
		formTo("commentsForm", "${ctx }/comments/saveComments.do");
	});
}

/**
 * 查看发言的所有评论
 */
function allCommentsAbout(testimonialsId){
	var newInput = $("<input type='hidden' name='testimonialsId' value='"+testimonialsId+"'>");
	$("#channelForm").append(newInput);
	formTo("commentsForm", "${ctx }/comments/saveComments.do");
}

function openTestimonials() {
	var currentChannelId = '${currentChannelId}';
	$("#"+submitBtnId).click(function() {
		formTo("commentsForm", "${ctx }/testionials/saveContents.do");
	});
}
</script>
</html>