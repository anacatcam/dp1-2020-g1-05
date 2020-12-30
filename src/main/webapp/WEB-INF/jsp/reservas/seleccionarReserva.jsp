<%@ page session="false" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="madaja" tagdir="/WEB-INF/tags" %>

<madaja:layout pageName="vehiculos">
	<h2>Selecciona el tipo de reserva que quieres hacer</h2>
	
	<table class="table table-striped">
        <tr>
            <th>Matrícula</th>
            <td><b><c:out value="${vehiculo.matricula}"/></b></td>
        </tr>
        <tr>
            <th>Marca</th>
            <td><c:out value="${vehiculo.marca}"/></td>
        </tr>
        <tr>
            <th>Modelo</th>
            <td><c:out value="${vehiculo.modelo}"/></td>
        </tr>
        <c:if test="${vehiculo.disponible.id eq 1}"> 
	        <tr>
	            <th>Precio de alquiler</th>
	            <td><c:out value="${vehiculo.precioAlquiler}"/></td>
	        </tr>
		</c:if>
		<c:if test="${vehiculo.disponible.id eq 2}"> 
        	<tr>
            	<th>Precio de venta</th>
            	<td><c:out value="${vehiculo.precioVenta}"/></td>
        	</tr>
        </c:if>
        <c:if test="${vehiculo.disponible.id >= 3}"> 
	        <tr>
	            <th>Precio de alquiler</th>
	            <td><c:out value="${vehiculo.precioAlquiler}"/></td>
	        </tr>
        	<tr>
            	<th>Precio de venta</th>
            	<td><c:out value="${vehiculo.precioVenta}"/></td>
        	</tr>
        </c:if>
        <tr>
        	<th>Disponibilidad</th>
        	<td><c:out value="${vehiculo.disponible}"/></td>
		</tr>
	</table>
	
	<c:if test="${vehiculo.disponible.id eq 1}"> 
		<spring:url value="/reservas/{vehiculoId}/reservar{tipo}" var="reservarUrl">
	        <spring:param name="vehiculoId" value="${vehiculo.id}"/>
	        <spring:param name="tipo" value="Alquiler"/>
	    </spring:url>
	    <a href="${fn:escapeXml(reservarUrl)}" class="btn btn-default">Reserva de alquiler</a>
    </c:if>
    
    <c:if test="${vehiculo.disponible.id eq 2}">
	    <spring:url value="/reservas/{vehiculoId}/reservar{tipo}" var="reservarUrl">
	        <spring:param name="vehiculoId" value="${vehiculo.id}"/>
	        <spring:param name="tipo" value="Compra"/>
	    </spring:url>
	    <a href="${fn:escapeXml(reservarUrl)}" class="btn btn-default">Reserva de compra</a>
    </c:if>
    
    <c:if test="${vehiculo.disponible.id eq 3}"> 
		<spring:url value="/reservas/{vehiculoId}/reservar{tipo}" var="reservarUrl">
	        <spring:param name="vehiculoId" value="${vehiculo.id}"/>
	        <spring:param name="tipo" value="Alquiler"/>
	    </spring:url>
	    <a href="${fn:escapeXml(reservarUrl)}" class="btn btn-default">Reserva de alquiler</a>
	    <spring:url value="/reservas/{vehiculoId}/reservar{tipo}" var="reservarUrl">
	        <spring:param name="vehiculoId" value="${vehiculo.id}"/>
	        <spring:param name="tipo" value="Compra"/>
	    </spring:url>
	    <a href="${fn:escapeXml(reservarUrl)}" class="btn btn-default">Reserva de compra</a>
    </c:if>
    
</madaja:layout>