<%@ page session="false" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="madaja" tagdir="/WEB-INF/tags" %>

<madaja:layout pageName="error">

    <spring:url value="/resources/images/clipo.png" var="clipo"/>
    <img style="display:block; margin: auto;" src="${clipo}"/>
	<br/>
    <h2 style="text-align: center;">Parece que ha habido un error...¿necesitas ayuda?</h2>

    <p>${exception.message}</p>

</madaja:layout>
