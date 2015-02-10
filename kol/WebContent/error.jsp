<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/include/common.jsp" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="renderer" content="ie-comp">
<title>错误</title>
<link rel="stylesheet" type="text/css" href="${ctx }/css/main.css">

<script src="${ctx }/images/js/jquery.min.js" type="text/javascript"></script>
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
		<div class="cells" style="height:200px">
			<div class="cell-24">
				
			</div>
		</div>
		<div class="cells screen-height ">
			<div class="cell-6">&nbsp;</div>
			<div class="cell-20 bg-login-user" style="height:200px; padding:10px;">
			<div class="text-center" style="padding:10px;">登录错误</div>
	      <div class="form-list">
	      	<label class="form-label t-right">错误码：</label>
	        <div class="form-content">
	        ${result.respCode }
	        </div>
	      </div>
	      <div class="form-list">
	      	<label class="form-label t-right">错误信息</label>
	        <div class="form-content">
	        	${result.respMsg }
	        </div>
	      </div>
	      
			</div>
		</div>
	</div>
	<form action="" id="loginForm" method="post">
		<input type="hidden" id="realLoginName" name="loginName" />
		<input type="hidden" id="realLoginPwd" name="loginPwd" />
	</form>
</div>
</body>

<script type="text/javascript" >
//alert("${ctx}");
$("#userLogin").click(function(){
	$("#realLoginName").val($("#username").val());
	$("#realLoginPwd").val($("#loginpwd").val());
	$("loginForm").attr("action","${ctx}/user/userLogin.do");
	$("loginForm").submit();
});

$("#companyLogin").click(function(){
	$("#realLoginName").val($("#username-comp").val());
	$("#realLoginPwd").val($("#loginpwd-comp").val());
	$("loginForm").attr("action","${ctx}/user/companyLogin.do");
	$("loginForm").submit();
});

</script>
</html>