<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

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

<link href="/css/base.css" rel="stylesheet">
<link rel="stylesheet" href="/css/main.css">
<link rel="stylesheet" href="/css/mask.css">
<link rel="stylesheet" href="/css/pad.css" media="only screen and (min-width : 768px) and (max-width : 1200px)">

<script type="text/javascript" src="/js/jquery-1.11.1.min.js"></script>
<script type="text/javascript" src="/js/jquery.SuperSlide.2.1.1.js"></script>
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
<style type="text/css">
.commemorateDiv tr td {vertical-align: top; padding-left:20px;}
</style>
</head>

<body>
	<%@ include file="/common/menu.jsp"%>
	<div id="banner">
		<div id="slideBox" class="slideBox">
			<div class="bd">
				<ul>
					<c:set var="bannerArray" value="${fn:split('images/banner1.jpg,images/banner2.jpg,images/banner3.jpg,images/banner4.jpg,images/banner5.jpg', ',') }" />
					<c:forEach var="banner" items="${bannerArray }">
						<li>
							<a href="javascript:void(0);">
								<img src="/${banner}">
							</a>
						</li>
					</c:forEach>
				</ul>
			</div>
		</div>
	</div>
	<div class="container">
		<div class="comwidth">
			<!-- <h1>
				感知生活<span>100%</span>了解您、<span>No.1</span>
				国内首页人生BAI科平台、<span>1,000,000</span>位的访问者即将来临
			</h1> -->
			<h1>
				人生的每一次经历，<span>都是</span>一笔宝贵的财富，<span>时光</span>飞逝光阴似箭时间如白驹过隙，
				<span>让我们</span>记录你的人生，留下属于你的每时每刻
			</h1>
			<!-- section one -->
			<section class="first left">
				<c:forEach var="tms" items="${testimonialsArray }" varStatus="index">
					<c:set var="positionFlag" value="${(index.count -1) % 4 }" />
					<c:if test="${positionFlag eq 0}">
						<%@ include file="/common/articleDiv.jsp"%>
						<br />
					</c:if>
				</c:forEach>
			</section>
			
			<!-- section two -->
			<section>
				<c:forEach var="tms" items="${testimonialsArray }" varStatus="index">
					<c:set var="positionFlag" value="${(index.count -1) % 4 }" />
					<c:if test="${positionFlag eq 1}">
						<%@ include file="/common/articleDiv.jsp"%>
						<br />
					</c:if>
				</c:forEach>
			</section>
			
			<!-- section three -->
			<section class="">
				<c:forEach var="tms" items="${testimonialsArray }" varStatus="index">
					<c:set var="positionFlag" value="${(index.count -1) % 4 }" />
					<c:if test="${positionFlag eq 2}">
						<%@ include file="/common/articleDiv.jsp"%>
						<br />
					</c:if>
				</c:forEach>
			</section>
			<!-- section four -->
			<section>
				<c:forEach var="tms" items="${testimonialsArray }" varStatus="index">
					<c:set var="positionFlag" value="${(index.count -1) % 4 }" />
					<c:if test="${positionFlag eq 3}">
						<%@ include file="/common/articleDiv.jsp"%>
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

<form id="channelForm" action="/channel/life.do" method="post" >
</form>

<c:if test="${! empty commemorate }">
<fmt:parseDate var="commemorateDateTemp" value="${commemorate.commemorateDate }" pattern="yyyyMMddHHmmss" />
<fmt:formatDate var="commemorateDate" value="${commemorateDateTemp }" type="both"/>
</c:if>
<script type="text/javascript" src="/js/common.js"></script>
<script type="text/javascript" src="/js/business.js"></script>
<script type="text/javascript">

// 
jQuery(".slideBox").slide({
	mainCell : ".bd ul",
	interTime : "3000",
	delayTime : "2000",
	autoPlay : true
});

function createCommemorateDiv(){
	if(!! "${commemorateDateTemp}"){
		var maskContentDiv = $d("maskDiv").dialogCommemorate();
		var imgHtml = "<table width='100%' class='commemorateDiv'>";
		imgHtml += "<tr>";
		imgHtml += "<td rowspan='3'><img style='max-height:450px;' src='${commemorate.filePath}'></td>";
		imgHtml += "<td>${commemorate.comments}</td>";
		imgHtml += "</tr>";
		// 底行
		imgHtml += "<tr>";
		imgHtml += "<td>";
		imgHtml += "被查看次数：${commemorate.viewTimes} 被顶次数：${commemorate.topTimes}";
		imgHtml += "<br>";
		imgHtml += "${createDate}";
		imgHtml += "<br>";
		imgHtml += '<a href="javascript:void(0);" class="topOnce" onclick="topOnce(\'${commemorate.id }\');">顶一下</a>';
		imgHtml += "</td>";
		imgHtml += "</tr>";
		imgHtml += "</table>";
		var imgObj = getObjFromHtml(imgHtml);
		$d(maskContentDiv).append(imgObj);
	}
}

createCommemorateDiv();

</script>
</html>