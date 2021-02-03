<%@ page session="false" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="madaja" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="sec"
	uri="http://www.springframework.org/security/tags"%>
	
<madaja:layout pageName="oferta">
	
	<h2>Información de la oferta</h2>
	
	<table class="table table-striped">
        <tr>
            <th>Descripción</th>
            <td><b><c:out value="${oferta.name}"/></b></td>
            <td></td>
        </tr>
        <tr>
            <th>Descuento (%)</th>
            <td><c:out value="${oferta.descuento}"/></td>
            <td></td>
        </tr>
        <tr>
            <th>Fecha límite</th>
            <td><c:out value="${oferta.fechaLimite}"/></td>
            <td></td>
        </tr>
        <tr>
            <th>Hora límite</th>
            <td><c:out value="${oferta.horaLimite}"/></td>
            <td></td>
        </tr>
        <tr>
            <th>Oferta aplicada a los siguientes vehículos:</th>
           	 <td>
           		 <ul>
            		<c:forEach items="${vehiculos}" var="vehiculo">
            			<li>
            				<spring:url value="/vehiculos/{vehiculoId}" var="vehiculoUrl">
			       				<spring:param name="vehiculoId" value="${vehiculo.id}"/>
			   				</spring:url>
			   				 <a href="${fn:escapeXml(vehiculoUrl)}"><c:out value="${vehiculo.marca} "/> <c:out value="${vehiculo.modelo}"/></a>
			   			</li>
            		</c:forEach>
            	</ul>
            </td>
            <td></td>
        </tr>
    </table>
</madaja:layout>