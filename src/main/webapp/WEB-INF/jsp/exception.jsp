<%@ page session="false" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="madaja" tagdir="/WEB-INF/tags" %>

<madaja:layout pageName="error">

    <spring:url value="/resources/images/clipo.png" var="clipo"/>
    <img src="${clipo}"/>

    <h2>Parece que ha habido un error...</h2>

    <p>${exception.message}</p>

</madaja:layout>
