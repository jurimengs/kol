<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/include/common.jsp" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="renderer" content="ie-comp">
<title>首页</title>
<link rel="stylesheet" type="text/css" href="${ctx }/css/main.css">
<link rel="stylesheet" type="text/css" href="${ctx }/css/mask.css">

<script src="${ctx }/js/jquery.min.js" type="text/javascript"></script>
<script src="${ctx }/js/common.js" type="text/javascript"></script>

<style type="text/css">
.rightmsg {
	width:100%;
	height:50px;
}
</style>
</head>

<body>
<div id="container" class="container">
	<div class="screen-height text-vertical-center ">
		<div class="cells screen-height ">
			<div class="cell-6">&nbsp;</div>
			<div class="cell-18 bg-login-user" style="height:200px; padding:10px;">
				<div class="text-center" style="padding:10px;">
					<span class="gray">
	      	累了的时候, 停下脚步聆听心的声音,带你走进自己的<strong><span class="size-xl orange">心</span></strong>世界
					</span>
	      </div>
	      <div class="form-list">
	      	<label class="form-label t-right"></label>
	        <div class="form-content">
	        	<a href="javascript:void(0);" onclick="openTestimonials();">新的感言</a>
	        </div>
	      </div>
	      <div class="form-list">
	      	<label class="form-label t-right"></label>
	        <div class="form-content">
	        	<a href="javascript:void(0);" onclick="openComments();">评论</a>
	        	<a href="javascript:void(0);">点赞</a>
	        </div>
	      </div>
			</div>
		</div>
	</div>
	<form id="loginForm" method="post">
		<input type="hidden" class="notnull" id="realLoginName" name="loginName" />
		<input type="hidden" class="notnull" id="realLoginPwd" name="loginPwd" />
	</form>
	<form id="registForm" method="post">
		<input type="hidden" class="notnull" id="realLoginName" name="loginName" />
		<input type="hidden" class="notnull" id="realLoginPwd" name="loginPwd" />
	</form>
</div>
</body>

<script type="text/javascript" >
//alert("${ctx}");
//$d("maskDiv").commentsModule();
function openComments(){
	$d("maskDiv").dialogComments();
}

function openTestimonials(){
	var submitBtnId = $d("maskDiv").dialogTestimonials();
	$d(submitBtnId).onclick(function(){
		alert("sfsdf");
	});
}

$("#userLogin").click(function(){
	$("#realLoginName").val($("#username").val());
	$("#realLoginPwd").val($("#loginpwd").val());
	$("#loginForm").prop("action","${ctx}/user/userLogin.do");

	var ids = ['username','loginpwd'];
	if(notNullArr(ids)){
		$("#loginForm").submit();
	}
});


</script>
</html>