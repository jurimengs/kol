<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/common/common.jsp"%>

<!DOCTYPE html>
<html>
<head>
<meta http-equiv="content-type" content="text/html; charset=UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<title>${channerName }</title>
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
	<header style="">
		<div id="logo" class="fl">
			<a href="javascript:void(0);">
				人生<span>BAI</span>科
			</a>
		</div>
		<%@ include file="/common/menu.jsp"%>
		<div class="clear">
		</div>
	</header>
	<div id="banner">
		<div id="slideBox" class="slideBox">
			<div class="bd">
				<ul>
					<c:set var="bannerArray" value="${fn:split('images/banner-other.jpg', ',') }" />
					<c:forEach var="banner" items="${bannerArray }">
						<li>
							<a href="javascript:void(0);">
								<img src="${ctx }/${banner}">
							</a>
						</li>
					</c:forEach>
				</ul>
			</div>
		</div>
	</div>
	<div class="container">
		<div class="comwidth">
			<h1>
				还有什么<span>事</span>呢？ 我忘记了什么<span>事</span>好象？ 到底是什么呢？让<span>其它</span>来容纳你的人生百科！
			</h1>
			<!-- section one -->
			<section class="first left">
				<c:forEach var="tms" items="${testimonialsArray }" varStatus="index">
					<c:set var="positionFlag" value="${(index.count -1) % 4 }" />
					<c:if test="${positionFlag eq 0}">
						<article>
							<div class="zhuti-bar">
								<a href="javascript:void(0);" class="zhuti">
									<strong>${tms.title }:</strong><br />
									<%-- <img src="${ctx }/images/aimg1.jpg" alt="zhuti"> --%>
								</a>
								<p>${tms.contents}</p>
								<p>
									<span>
										发表时间：${tms.createDate }<br/>
									</span>
									<a href="javascript:void(0);" onclick="openComments('${tms.id}');">
										评价
									</a>
								</p>
							</div>
						</article>
						<br />
					</c:if>
				</c:forEach>
			</section>
			
			<!-- section two -->
			<section>
				<c:forEach var="tms" items="${testimonialsArray }" varStatus="index">
					<c:set var="positionFlag" value="${(index.count -1) % 4 }" />
					<c:if test="${positionFlag eq 1}">
						<article>
							<div class="zhuti-bar">
								<a href="javascript:void(0);" class="zhuti">
									<strong>${tms.title }:</strong><br />
									<%-- <img src="${ctx }/images/aimg2.jpg" alt="zhuti"> --%>
								</a>
								<p>${tms.contents}</p>
								<p>
									<span>
										发表时间：${tms.createDate }<br/>
									</span>
									<a href="javascript:void(0);" onclick="openComments('${tms.id}');">
										评价
									</a>
								</p>
							</div>
						</article>
						<br />
					</c:if>
				</c:forEach>
			</section>
			
			<!-- section three -->
			<section class="left">
				<c:forEach var="tms" items="${testimonialsArray }" varStatus="index">
					<c:set var="positionFlag" value="${(index.count -1) % 4 }" />
					<c:if test="${positionFlag eq 2}">
						<article>
							<div class="zhuti-bar">
								<a href="javascript:void(0);" class="zhuti">
									<strong>${tms.title }:</strong><br />
									<%-- <img src="${ctx }/images/aimg3.jpg" alt="zhuti"> --%>
								</a>
								<p>${tms.contents}</p>
								<p>
									<span>
										发表时间：${tms.createDate }<br/>
									</span>
									<a href="javascript:void(0);" onclick="openComments('${tms.id}');">
										评价
									</a>
								</p>
							</div>
						</article>
						<br />
					</c:if>
				</c:forEach>
			</section>
			<!-- section four -->
			<section>
				<c:forEach var="tms" items="${testimonialsArray }" varStatus="index">
					<c:set var="positionFlag" value="${(index.count -1) % 4 }" />
					<c:if test="${positionFlag eq 3}">
						<article>
							<div class="zhuti-bar">
								<a href="javascript:void(0);" class="zhuti">
									<strong>${tms.title }:</strong><br />
									<%-- <img src="${ctx }/images/aimg4.jpg" alt="zhuti"> --%>
								</a>
								<p>${tms.contents}</p>
								<p>
									<span>
										发表时间：${tms.createDate }<br/>
									</span>
									<a href="javascript:void(0);" onclick="openComments('${tms.id}');">
										评价
									</a>
								</p>
							</div>
						</article>
						<br />
					</c:if>
				</c:forEach>
			</section>
			<div class="clear">
			</div>
		</div>
	</div>
	<%@ include file="/common/footer.jsp"%> 
</body>

<form id="channelForm" action="${ctx }/channel/life.do">
</form>
<script type="text/javascript">
function formTo(formId, href){
	$("#"+formId).prop("action", href);
	$("#"+formId).submit();	
}

// 
/* jQuery(".slideBox").slide({
	mainCell : ".bd ul",
	autoPlay : true
}); */

//alert("${ctx}");
//$d("maskDiv").commentsModule();
function openComments(testimonialsId) {
	var commentsBtn = $d("maskDiv").dialogComments(testimonialsId);
	$("#"+commentsBtn).click(function() {
		//alert("commentsBtn");
		formTo("commentsForm", "${ctx }/comments/saveComments.do");
	});
}

function openTestimonials() {
	var currentChannelId = '${currentChannelId}';
	var submitBtnId = $d("maskDiv").dialogTestimonials(currentChannelId);
	$("#"+submitBtnId).click(function() {
		formTo("commentsForm", "${ctx }/testionials/saveContents.do");
	});
}
	
function turnToTuCaoBa(){
	formTo("channelForm", "${ctx }/channel/tucaoba.do");
}
function turnToOther(){
	formTo("channelForm", "${ctx }/channel/other.do");
}
function turnToCareer(){
	formTo("channelForm", "${ctx }/channel/career.do");
}
function turnToEmotion(){
	formTo("channelForm", "${ctx }/channel/emotion.do");
}
function turnToLife(){
	formTo("channelForm", "${ctx }/channel/life.do");
}
function turnToIndex(){
	formTo("channelForm", "${ctx }/channel/index.do");
}

var ohmg = "${ohmg}";
//alert(ohmg);
if(ohmg == "null" || !!! ohmg){
	turnToIndex();
}
</script>
</html>