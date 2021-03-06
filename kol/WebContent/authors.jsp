<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<!DOCTYPE html>
<%@ include file="/common/common.jsp"%>
<html>
<head>
<meta http-equiv="content-type" content="text/html; charset=UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<title>人生百科 WEB人生百科 webrsbk 提高你的人生高度 工作频道 生活频道 纪念版 纪念 创造人生价值 创造社会价值</title>
<meta name="description" content="人生百科,WEB人生百科,webrsbk,提高你的人生高度,工作频道,生活频道,纪念版,创造人生价值,创造社会价值">
<meta name="keywords" content="人生百科">
<meta name="viewport" content="width=device-width, initial-scale=1.0, minimum-scale=1.0, maximum-scale=1.0, user-scalable=no">

<link rel="stylesheet" href="/css/base.css">
<link rel="stylesheet" href="/css/main.css">
<link rel="stylesheet" href="/css/mask.css">
<link rel="stylesheet" href="/css/pad.css" media="only screen and (max-width : 768px)">

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
.commemorateDiv tr td {vertical-align: top; padding-left:20px;}
</style>
</head>

<body>
	<%@ include file="/common/menu.jsp"%>
	<div class="container">
		<div class="comwidth">
			<section>
			<div class="h1style zhuti-bar">
				<div>策划参与：</div><span>千秋</span>、<span>张洲</span>、<span>神棍</span><br><br><br><br><br>
				<div>设计参与：</div><span>张洲</span>、<span>神棍</span><br><br><br><br><br>
				<div>UI参与：</div><span>张洲</span>、<span>神棍</span><br><br><br><br><br>
				(排名一定分先后－－排在前面的总是该领域的大牛)
				期待更多的朋友加入你的家庭！
				如有任何建议或意见，请联系jurimengs@163.com
			</div>
			</section>
			<section class="first left">
			</section>
			<div class="clear">
			</div>
		</div>
	</div>
</body>

<form id="channelForm" action="/channel/life.do" method="post" >
</form>
<c:if test="${! empty commemorate }">
<fmt:parseDate var="commemorateDateTemp" value="${commemorate.commemorateDate }" pattern="yyyyMMdd" />
<fmt:formatDate var="commemorateDate" value="${commemorateDateTemp }" type="both"/>

</c:if>
<script type="text/javascript" src="/js/common.js"></script>
<script type="text/javascript" src="/js/business.js"></script>
<script type="text/javascript">

function createCommemorateDiv(){
	if(!! "${commemorateDateTemp}" && "null" != "${commemorateDateTemp}"){
		var maskContentDiv = $d("maskDiv").commonDialog();
		var imgHtml = "<table width='100%' class='commemorateDiv'>";
		imgHtml += "<tr>";
		imgHtml += "<td rowspan='3'><img style='max-height:450px;' src='${commemorate.filePath}'></td>";
		imgHtml += "<td>${commemorate.comments}</td>";
		imgHtml += "</tr>";
		// 底行
		imgHtml += "<tr>";
		imgHtml += "<td>";
		imgHtml += "被查看次数：${commemorate.viewTimes eq '' ? 0 : commemorate.viewTimes} 被顶次数：<span id='topTimes_home'>${commemorate.topTimes}</span>";
		imgHtml += "<br>";
		imgHtml += "${createDate}";
		imgHtml += "<br>";
		imgHtml += '<a href="javascript:void(0);" class="topOnce" onclick="topOnce(\'${commemorate.id }\', \'topTimes_home\');">顶一下</a>';
		imgHtml += "</td>";
		imgHtml += "</tr>";
		imgHtml += "</table>";
		var imgObj = getObjFromHtml(imgHtml);
		$d(maskContentDiv).append(imgObj);
	}
}

createCommemorateDiv();

</script>
<script type="text/javascript" src="/js/listener.js"></script>
</html>