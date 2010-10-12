<%@ page 
	contentType="text/html; charset=iso-8859-1" 
	language="java" 
	import="javax.servlet.jsp.*" 
	errorPage="" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %> 
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<html>
<c:set var="pageTitle" value="Admin Home"/>
<jsp:include page="head.jsp"/>
<body>

<div class="container">
	<div class="span-24">
		<h2>admin home</h2>
		<a href="<c:url value='/do/admin/award'/>">give award</a>
	</div>
</div>

</body>
</html>