<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<style>
.addCommemorate {
	display: none;
	/* background-color: #afcadc; */
	margin-left: 20px;
	margin-top: 5px;
	position: absolute;
	background: url(/images/addcommemorate.gif) no-repeat;
	width: 70px;
	height: 70px;
	text-align:center;
	padding-top:23px;
	cursor: pointer;
	font:normal 16px "Microsoft Yahei";
	color:#fff;
}
</style>
<header style="">
	<div id="logo" class="fl">
		<a href="javascript:void(0);" onclick="turnToIndex();">
			人生<span>BAI</span>科 &nbsp;<span style="font: 12px/24px solid ">版本: 1.0</span>
		</a>
	</div>
	<nav class="fr">
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
				<a href="javascript:void(0);" id="commemorateLi" onmouseover="showAddCommemorate();" onclick="turnTo('/channel/commemorateBoard.do');">纪念板</a>
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

<script type="text/javascript">
function openComments(testimonialsId) {
	var commentsBtn = $d("maskDiv").dialogComments(testimonialsId);
	$("#"+commentsBtn).click(function() {
		//alert("commentsBtn");
		formTo("commentsForm", "/comments/saveComments.do");
	});
}

function openTestimonials() {
	var currentChannelId = '${currentChannelId}';
	var submitBtnId = $d("maskDiv").dialogTestimonials(currentChannelId);
	$("#"+submitBtnId).click(function() {
		formTo("commentsForm", "/testimonials/saveContents.do");
	});
}

function showAddCommemorate(){
	cancelBubble();
	//$("#addCommemorate").css("borderRight", "1px solid #ccc");
	$("#addCommemorate").slideDown();
}

function hideAddCommemorate(){
	cancelBubble();
	$("#addCommemorate").slideUp();
}

function addCommemorate(){
	window.location.href="/channel/addCommemorate.jsp";
}

function cancelBubble(){
	var e = getEvent();
	if(window.event){
		//e.returnValue=false;//阻止自身行为
		e.cancelBubble=true;//阻止冒泡
	}else if(e.preventDefault){
		//e.preventDefault();//阻止自身行为
		e.stopPropagation();//阻止冒泡
	}
}

function getEvent(){
    if(window.event) {
    	return window.event;
    }
    var func = getEvent.caller;
    while(func != null){
        var arg0 = func.arguments[0];
        if(arg0){
            if((arg0.constructor==Event || arg0.constructor ==MouseEvent
               || arg0.constructor==KeyboardEvent)
               ||(typeof(arg0)=="object" && arg0.preventDefault
               && arg0.stopPropagation)){
                return arg0;
            }
        }
        func=func.caller;
    }
    return null;
}
</script>
