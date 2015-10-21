<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<!DOCTYPE html>
<%@ include file="/common/common.jsp"%>
<html>
<head>
<meta http-equiv="content-type" content="text/html; charset=UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<title>人生百科 WEB人生百科 webrsbk 人生百科登录页面</title>
<meta name="description"
	content="人生百科,WEB人生百科,webrsbk,提高你的人生高度,工作频道,生活频道,纪念版,创造人生价值,创造社会价值">
<meta name="keywords" content="人生百科">
<meta name="viewport"
	content="width=device-width, initial-scale=1.0, minimum-scale=1.0, maximum-scale=1.0, user-scalable=no">

<link rel="stylesheet" href="/css/simple.css">
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

@media screen and (max-width: 600px) {
	.mainDiv {
		width: 100%;
		padding-top: 80px;
		margin-left: 120px;
	}
}

.loginBtn {
	width: 100%;
}

.div-signup div {
	width: 100%;
}

#loginDiv {
	height: 280px;
}

.enterDiv {
	height: 180px;
	margin-top: 100px;
	text-align: center;
}

.enterDiv a {
	text-decoration: none;
}

.toregistDiv {
	margin-top: 100px;
	text-align: center;
	opacity: 0.3;
}

.toregistDiv:hover {
	opacity: 0.6;
}


</style>
</head>

<body>
	<%@ include file="/common/topDiv.jsp"%>
	<div class="login_bg">
		<div class="mainDiv">
			<div class="div-enter">
			&nbsp;
			</div>
			<%@ include file="/common/index_words.jsp"%>
			<div class="div-signup">
				<div id="loginDiv" style="display: none; ">
					<form method="post" class="form-signup" autocomplete="off" action="/user/login.do" accept-charset="UTF-8">
						<dl>
							<dd>
								<input onclick="" type="text" autofocus="" data-autocheck-url="/signup_check/username" aria-label="Pick a username" placeholder="Pick a username" class="textfield" name="loginName">
							</dd>
						</dl>
						<dl>
							<dd>
								<input type="password" data-autocheck-url="/signup_check/password" aria-label="Create a password" placeholder="Create a password" class="textfield" name="password">
							</dd>
						</dl>
						<dl>
							<dd>
								<button type="submit" id="loginBtn" class="loginBtn btn btn-signup">登录</button>
								<!-- <a href="javascript:void(0);" onclick="document.getElementById('submitBtn').click();" class="btn btn-signup btn-a">登录</a>
								<button type="submit" id="submitBtn" style="display:none;"></button>
								<a href="/regist.jsp" class="btn btn-signup btn-a">注册</a> -->
							</dd>
						</dl>
					</form>
				</div>
				<div id="enterDiv" class="enterDiv">
					<!-- 直接进入 -->
					<a href="javascript:void(0);" onclick="turnToIndex();" >不登录,直接进入</a>
					<br>
					<span style="font-size: 12px;">(旁门左道, 少走为妙)</span>
					<div class="toregistDiv">
						<a href="javascript:void(0);" onclick="showHide('loginDiv', 'enterDiv' );" >登录</a>
						<a href="/regist.jsp" >注册</a>
						<br>
						<span style="font-size: 12px;">(人间正道是沧桑,沧桑的道路是注册)</span>
					</div>
				</div>
			</div>
		</div>
	</div>
	<!-- /.container -->
	<!-- <a href="http://www.alexa.cn">ALEXA</a> -->
	<div class="marketing-section-depth"></div>
</body>

<form id="channelForm" action="/channel/life.do" method="post">
</form>
<script type="text/javascript" src="/js/common.js"></script>
<script type="text/javascript" src="/js/business.js"></script>
<script type="text/javascript">
function showHide(showid, hideid){
	$("#"+showid).fadeIn();
	$("#"+hideid).hide();
}
</script>
<script type="text/javascript" src="/js/listener.js"></script>
</html>