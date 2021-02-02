<%@ page session="false" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="madaja" tagdir="/WEB-INF/tags" %>

<madaja:layout pageName="home">
    <h2>Bienvenido</h2>
    <div class="row">
    	<h2>Project: ${titulo}</h2>
    	<p><h2>Group: ${grupo}</h2></p>
    	<p>
    		<ul>
    			<c:forEach items="${personas}" var="persona">
    				<li>${persona.firstName} ${persona.lastName}</li>
    			</c:forEach>
    		</ul>
    	</p>
    	<p><h2>Servicios API REST:</h2>
    	<p>
    		<ul> <a href ="/ofertaAPI">Ofertas API</a></ul>
    		
    	</p>
    </div>
    <div>
    	<img alt="Logo Universidad de Sevilla" src="/resources/images/logoPNG_3.png" width="220" height="100">
    </div>
</madaja:layout>