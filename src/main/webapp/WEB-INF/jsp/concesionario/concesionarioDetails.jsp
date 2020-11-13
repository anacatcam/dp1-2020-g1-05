<%@ page session="false" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="madaja" tagdir="/WEB-INF/tags" %>

<madaja:layout pageName="concesionarios">
	
	<h2>Información del concesionario</h2>
	
	<table class="table table-striped">
        <tr>
            <th>Provincia</th>
            <td></td>
            <td></td>
        </tr>
        <tr>
            <th>Localidad</th>
            <td></td>
            <td></td>
        </tr>
        <tr>
            <th>Dirección</th>
            <td></td>
            <td></td>
        </tr>
        <tr>
            <th>Código Postal</th>
            <td></td>
            <td></td>
        </tr>
        <tr>
            <th>País</th>
            <td></td>
            <td></td>
        </tr>
        <tr>
            <th>Email</th>
            <td><c:out value="${concesionario.email}"/></td>
            <td></td>
        </tr>
        <tr>
            <th>Teléfono</th>
            <td><c:out value="${concesionario.telefono}"/></td>
            <td></td>
        </tr>
	</table>
	
	<br/>
    <br/>
    <br/>
    <h2>Vehículos asociados</h2>
    
    <table class="table table-striped">
    	<c:forEach var="vehiculo" items="${concesionario.vehiculos}">
    		<tr>
                <td valign="top">
                    <dl class="dl-horizontal">
                    	<dd>
                        <dt>Matrícula</dt>
                        <dd>
                        	<spring:url value="/concesionario/{concesionarioId}/vehiculos/{vehiculoId}" var="vehiculoUrl">
	                            <spring:param name="vehiculoId" value="${vehiculos.id}"/>
                            </spring:url>
                        	<a href="${fn:escapeXml(vehiculoUrl)}"><c:out value="${vehiculo.matricula}"/></a>
                        </dd>
                        <dt>Marca</dt>
                        <dd><c:out value="${vehiculo.marca}"/></dd>
                        <dt>Modelo</dt>
                        <dd><c:out value="${vehiculo.modelo}"/></dd>
                        <dt>Plazas</dt>
                        <dd><c:out value="${vehiculo.plazas}"/></dd>
                        <dt>Precio de alquiler</dt>
                        <dd><c:out value="${vehiculo.precioAlquiler}"/></dd>
                        <dt>Precio de venta</dt>
                        <dd><c:out value="${vehiculo.precioVenta}"/></dd>
                        <dt>Disponibilidad</dt>
			        	<dd>
			        		<c:choose>
			                	<c:when test="${vehiculos.disponible}">
			                   		<span>Disponible</span>
			               		</c:when>
			               		<c:when test="${vehiculos.alquilado}">
			                   		<span>Alquilado</span>
			               		</c:when>
			               		<c:when test="${vehiculos.vendido}">
			                   		<span>Vendido</span>
			               		</c:when>
			               		<c:otherwise>
			               			<span>No especificado</span>
			               		</c:otherwise>
			           		</c:choose>
			           	</dd>
                    </dl>
                </td>
    	</c:forEach>
    </table>
	
</madaja:layout>