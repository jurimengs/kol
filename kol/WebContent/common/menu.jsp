<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<header style="">
	<div id="logo" class="fl">
		<a href="javascript:void(0);" onclick="turnToIndex();">
			人生<span>BAI</span>科
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
				<a href="javascript:void(0);" onclick="turnTo('/channel/commemorateBoard.do');">纪念板</a>
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
</script>
