<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<style>
.addCommemorate {
	display: none; margin-left: 20px; margin-top: 5px; position: absolute; background: url(/images/addcommemorate.gif) no-repeat; width: 70px; height: 70px; text-align:center; padding-top:23px; cursor: pointer; font:normal 16px "Microsoft Yahei"; color:#fff;
}
#movmenu {
	display: none;
}
#movmenu div{
	width: 100%; font: normal 14px/24px solid; display: transparent;
}
.switchDivBtn {
}

#menudiv {
	display: none; background-color: #2e3138; color: #ccc;
}

#menudiv div{
	padding-left: 24px;
}

.topnav {
	color: #2e3136; padding-left: 24px;
}
/* 菜单的响应式 CSS*/
@media screen and (max-width: 600px) {
	#pcmenu { display: none; }
	#movmenu { display: block; }
}
</style>
<header style="">
	<div id="logo" class="fl">
		<a href="javascript:void(0);" onclick="turnToIndex();">
			人生<span>BAI</span>科 &nbsp;<span style="font: 12px/24px solid ">版本: 1.0</span>
		</a>
	</div>
	<nav id="pcmenu" class="fr">
		<div class="btn-gp fr">
			<c:if test="${empty sessionUser }">
				<a class="btn" href="javascript:void(0);">登录</a>
				<a class="btn" href="javascript:void(0);">注册</a>
			</c:if>
			<c:if test="${!empty sessionUser }">
				欢迎您,${sessionUser.loginName }<a class="btn" href="javascript:void(0);">完善资料</a>
			</c:if>
		</div>
		<ul class="fr">
			<li <c:if test="${currentChannelId eq '5' }">class="currentChannel"</c:if>>
				<a href="javascript:void(0);" onmouseover="showAddCommemorate();" onclick="turnTo('/channel/commemorateBoard.do');">纪念板</a>
				<div id="addCommemorate" onclick="addCommemorate();" onmouseout="hideAddCommemorate();" class="addCommemorate">我要纪念</div>
			</li>
			<%-- <li <c:if test="${currentChannelId eq '4' }">class="currentChannel"</c:if>>
				<a href="javascript:void(0);" onclick="turnToTuCaoBa();">吐槽吧</a>
			</li> --%>
			<li <c:if test="${currentChannelId eq '3' }">class="currentChannel"</c:if>>
				<a href="javascript:void(0);" onclick="turnToOther();">其他</a>
			</li>
			<li <c:if test="${currentChannelId eq '2' }">class="currentChannel"</c:if>>
				<a href="javascript:void(0);" onclick="turnToCareer();">职场</a>
			</li>
			<li <c:if test="${currentChannelId eq '1' }">class="currentChannel"</c:if>>
				<a href="javascript:void(0);" onclick="turnToEmotion();">情感</a>
			</li>
			<li <c:if test="${currentChannelId eq '0' }">class="currentChannel"</c:if>>
				<a href="javascript:void(0);" onclick="turnToLife();">生活</a>
			</li>
			<li <c:if test="${currentChannelId eq 'home' }">class="currentChannel"</c:if>>
				<a href="javascript:void(0);" onclick="turnToIndex();">首页</a>
			</li>
			<li>
				<a href="javascript:void(0);" onclick="openTestimonials();">发表感言</a>
			</li>
		</ul>
	</nav>
	<div class="clear">
	</div>
</header>

<div id="movmenu">
	<div class="topnav">
		<c:if test="${empty sessionUser }">
			<a class="btn" href="javascript:void(0);">登录</a>
			<a class="btn" href="javascript:void(0);">注册</a>
		</c:if>
		<c:if test="${!empty sessionUser }">
			欢迎${sessionUser.loginName }<a class="btn" href="javascript:void(0);">完善资料</a>
		</c:if>
		<a class="btn" onclick="switchMenu(this);" class="switchDivBtn">菜单</a>
	</div>
	<div id="menudiv">
		<div onclick="turnTo('/channel/commemorateBoard.do');"<c:if test="${currentChannelId eq '5' }">class="currentChannel"</c:if>>
			纪念板
		</div>
		<div onclick="turnToOther();" <c:if test="${currentChannelId eq '3' }">class="currentChannel"</c:if>>
			其他
		</div>
		<div onclick="turnToCareer();" <c:if test="${currentChannelId eq '2' }">class="currentChannel"</c:if>>
			职场
		</div>
		<div onclick="turnToEmotion();" <c:if test="${currentChannelId eq '1' }">class="currentChannel"</c:if>>
			情感
		</div>
		<div onclick="turnToLife();" <c:if test="${currentChannelId eq '0' }">class="currentChannel"</c:if>>
			生活
		</div>
		<div onclick="turnToIndex();" <c:if test="${currentChannelId eq 'home' }">class="currentChannel"</c:if>>
			首页
		</div>
		<div onclick="openTestimonials();">
			发表感言
		</div>
	</div>
</div>

<script type="text/javascript">
function switchMenu(obj){
	var disp = $("#menudiv").css("display");
	if(disp == "block"){
		$("#menudiv").slideUp();
		$(obj).text("菜单");
	} else {
		$("#menudiv").slideDown();
		$(obj).text("隐藏菜单");
	}
}
</script>
