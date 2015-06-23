<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>  

<article>
	<div class="zhuti-bar">
		<a href="javascript:void(0);" class="zhuti">
			<strong>标题:</strong>${tms.title }<br />
			<%-- <img src="/images/aimg1.jpg" alt="zhuti"> --%>
		</a>
		<p><strong>内容:</strong>${tms.contents}</p>
		<p>
			<span>
				<fmt:parseDate var="testimonialsDateTemp" value="${tms.createDate }" pattern="yyyyMMddHHmmss" />
				<fmt:formatDate var="testimonialsDate" value="${testimonialsDateTemp }" type="both"/>
				<strong>发表时间：</strong>${testimonialsDate }<br/>
			</span>
			<a href="javascript:void(0);" onclick="openComments('${tms.id}');">
				评价
			</a>
			&nbsp;
			<a href="javascript:void(0);" onclick="allCommentsAbout('${tms.id}');">
				查看所有评价
			</a>
		</p>
	</div>
</article>