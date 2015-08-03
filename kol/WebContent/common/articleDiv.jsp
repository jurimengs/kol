<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>  

<article>
	<div class="zhuti-bar">
		<a href="/comments/queryComments.do?testimonialsId=${tms.id }" class="zhuti">
			<strong>标题:</strong>${tms.title }<br />
			<c:if test="${! empty tms.filePath }">
				<img class="testim_img" src="${tms.filePath }" />
			</c:if>
		</a>
		<div class="contents"><strong>内容:</strong>${tms.contents}</div>
		<p>
			<span>
				<fmt:parseDate var="testimonialsDateTemp" value="${tms.createDate }" pattern="yyyyMMddHHmmss" />
				<fmt:formatDate var="testimonialsDate" value="${testimonialsDateTemp }" type="both"/>
				<strong>发表时间：</strong>${testimonialsDate }<br/>
			</span>
			<a href="javascript:void(0);" onclick="openComments('${tms.id}');">
				发表高见
			</a>
			&nbsp;
			<a href="/comments/queryComments.do?testimonialsId=${tms.id }">
				查看所有评价(${tms.commentCounts })
			</a>
			<%-- <a href="javascript:void(0);" onclick="allCommentsAbout('${tms.id}');">
				查看所有评价
			</a> --%>
		</p>
	</div>
</article>