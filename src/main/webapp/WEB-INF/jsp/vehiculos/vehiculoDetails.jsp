<%@ page session="false" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="madaja" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="sec"
	uri="http://www.springframework.org/security/tags"%>
<madaja:layout pageName="vehiculos">
	
	<h2>Información del vehículo</h2>
	
	<table class="table table-striped">
        <tr>
            <th>Matrícula</th>
            <td><b><c:out value="${vehiculos.matricula}"/></b></td>
            <td></td>
        </tr>
        <tr>
            <th>Marca</th>
            <td><c:out value="${vehiculos.marca}"/></td>
            <td></td>
        </tr>
        <tr>
            <th>Modelo</th>
            <td><c:out value="${vehiculos.modelo}"/></td>
            <td></td>
        </tr>
        <tr>
            <th>Plazas</th>
            <td><c:out value="${vehiculos.plazas}"/> pasajeros</td>
            <td></td>
        </tr>
        <tr>
            <th>Precio de alquiler</th>
            <td><c:out value="${vehiculos.precioAlquiler}"/></td>
            <td></td>
        </tr>
        <tr>
            <th>Precio de venta</th>
            <td><c:out value="${vehiculos.precioVenta}"/></td>
            <td></td>
        </tr>
        <tr>
            <th>Tipo de maletero</th>
            <td><c:out value="${vehiculos.maletero}"/></td>
            <td></td>
        </tr>
        <tr>
            <th>Tipo de cambio</th>
            <td><c:out value="${vehiculos.cambio}"/></td>
            <td></td>
        </tr>
        <tr>
            <th>Características</th>
            <td><c:out value="${vehiculos.caracteristicas}"/></td>
            <td></td>
        </tr>
        <tr>
        	<th>Disponibilidad</th>
        	<td>
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
           	</td>
           	<td></td>
		</tr>
		<tr>
			<th>Concesionario</th>
			<td>Dirección: </td>
			<td>
				<spring:url value="/concesionario/{concesionarioId}" var="concesionarioUrl">
			        <spring:param name="concesionarioId" value="${vehiculos.concesionario.id}"/>
			    </spring:url>
			    <a href="${fn:escapeXml(concesionarioUrl)}">Detalles</a>
			</td>
		</tr>
    </table>
    
	<sec:authorize access="hasAuthority('admin')">
	    <spring:url value="/vehiculos/{vehiculoId}/edit" var="editUrl">
	        <spring:param name="vehiculoId" value="${vehiculos.id}"/>
	    </spring:url>
	    <a href="${fn:escapeXml(editUrl)}" class="btn btn-default">Editar vehículo</a>
    </sec:authorize>
    <br/>
    <br/>
    <br/>
    <h2>Incidencias</h2>
    
    <table class="table table-striped">
    	<c:forEach var="incidencia" items="${vehiculos.incidencias}">
    		<tr>
                <td valign="top">
                    <dl class="dl-horizontal">
                        <dt>Descripción</dt>
                        <dd><c:out value="${incidencia.descripcion}"/></dd>
                        <dt>Solucionada</dt>
                        <dd>
                        	<c:choose>
                        		<c:when test="${incidencia.solucionada}">
                        			Sí
                        		</c:when>
                        		<c:otherwise>
                        			No
                        		</c:otherwise>
                        	</c:choose>
                        </dd>
                        <dt>
                        	<spring:url value="/vehiculos/{vehiculoId}/incidencia/{incidenciaId}/edit" var="incidenciaUrl">
	                            <spring:param name="vehiculoId" value="${vehiculos.id}"/>
	                            <spring:param name="incidenciaId" value="${incidencia.id}"/>
                            </spring:url>
                            <a href="${fn:escapeXml(incidenciaUrl)}">Editar incidencia</a>
                        </dt>
                    </dl>
                </td>
    	</c:forEach>
    </table>
    
    <spring:url value="/vehiculos/{vehiculoId}/incidencia/new" var="editUrl">
        <spring:param name="vehiculoId" value="${vehiculos.id}"/>
    </spring:url>
    <a href="${fn:escapeXml(editUrl)}" class="btn btn-default">Añadir incidencia</a>
    
</madaja:layout>