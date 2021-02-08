<%@ page session="false" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="madaja" tagdir="/WEB-INF/tags" %>

<madaja:layout pageName="error">

	<c:if test="${error400}">
		<br>
		<spring:url value="/resources/images/error400.png" var="badRequest"/>
	    <img style="display:block; margin: auto; with:60%; height:60%" src="${badRequest}"/>	
		<br>	    
	</c:if>
	<c:if test="${error401}">
		<br>
		<spring:url value="/resources/images/error401.jpg" var="unauthorized"/>
	    <img style="display:block; margin: auto; with:60%; height:60%" src="${unauthorized}"/>
	    <br>		
	</c:if>
	<c:if test="${error403}">
		<br>
		<spring:url value="/resources/images/error403.jpg" var="forbidden"/>
	    <img style="display:block; margin: auto;" src="${forbidden}"/>
	    <br>	
	</c:if>
	<c:if test="${error404}">
		<br>
		<spring:url value="/resources/images/error404.jpg" var="notFound"/>
	    <img style="display:block; margin: auto; width:55%; height:55% " src="${notFound}"/>
	    <br>	
	</c:if>
	<c:if test="${error500}">
		<br>
		<spring:url value="/resources/images/error500.png" var="internalServerError"/>
	    <img style="display:block; margin: auto; width:50%; height:50%" src="${internalServerError}"/>
	    <br>	
	</c:if>
	<c:if test="${error503}">
		<br>
		<spring:url value="/resources/images/error503.jpg" var="serviceUnavailable"/>
	    <img style="display:block; margin: auto; width:50%; height:50%" src="${serviceUnavailable}"/>
	    <br>	
	</c:if>				

    <p>${exception.message}</p>

</madaja:layout>
