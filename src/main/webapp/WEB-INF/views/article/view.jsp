<%@ page 
	contentType="text/html; charset=iso-8859-1" 
	language="java"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %> 
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<html>
<c:set var="pageTitle" value="Article View"/>
<jsp:include page="../head.jsp"/>
<script>
	$(function() {
		$( "a", ".actions" ).button();
	});
</script>
<body>

<div class="container">
	<div class="span-24">
		<jsp:include page="../header.jsp"/>
	</div>
	<div class="span-24">
		<h2><a href="<c:url value='/publisher/article/list' />">all articles</a> : ${article.name}</h2>
		<hr/>
		<div class="actions span-24 last">
			<a href="<c:url value='/publisher/article/edit/${article.id}' />" class="button">edit</a>
			<a href="<c:url value='/publisher/article/delete/${article.id}' />" class="button">delete</a>
		</div>
		<div class="span-24 last">
			<div class="strong-12 span-4">${article.id}</div>
			<div class="span-19">
				<div class="span-19"><a href="${article.url}">${article.url}</a></div>
				<div class="span-19">${article.description}</div>
				<div class="span-19">${article.summary}</div>
			</div>
		</div>
	</div>
</div>

</body>