<%@page import="java.util.Objects"%>
<%@ page session="false" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="madaja" tagdir="/WEB-INF/tags" %>

<madaja:layout pageName="Envios">

<h2>Envios</h2>

	<spring:url value="/concesionario/{concesionarioId}/EnviosAlquileres" var="enviosUrl">
		        <spring:param name="concesionarioId" value="${concesionario}"/>
		    </spring:url>
		    <a href="${fn:escapeXml(enviosUrl)}" class="btn btn-default">Ver envios alquileres</a>
		    
	<spring:url value="/concesionario/{concesionarioId}/EnviosVentas" var="enviosUrl">
		        <spring:param name="concesionarioId" value="${concesionario}"/>
		    </spring:url>
		    <a href="${fn:escapeXml(enviosUrl)}" class="btn btn-default">Ver envios ventas</a>
	     
<table id="enviosTable" class="table table-striped">
		<thead>
		<tr>
			<th style="width: 150px;">Número de identificación</th>
			<th style="width: 150px;">Vehículo</th>
			<th style="width: 150px;">Matrícula</th>
		 	<th style="width: 150px;">Fecha</th>
			<th style="width: 150px;">Hora</th>
			<th style="width: 150px;">Cliente</th>
			<th style="width: 150px;">Dirección</th>
			<th style="width: 150px;">Estado</th>
		</tr>
		</thead>
		<tbody>
			<c:forEach items="${objects}" var="object" varStatus="status">
				<tr>
					<td>
						<c:out value="${object.id}"/>
					</td>
					<td>
						<c:out value="${object.vehiculo.marca} ${object.vehiculo.modelo}"/>	
					</td>
				 	<td>
						<spring:url value="/vehiculos/{vehiculoId}" var="vehiculoUrl">
	                            <spring:param name="vehiculoId" value="${object.vehiculo.id}"/>
						</spring:url>
                        <a href="${fn:escapeXml(vehiculoUrl)}"><c:out value="${object.vehiculo.matricula}"/></a>
					</td>
					<td>
						<c:out value="${object.envio.fecha}"/>
					</td> 
					<td>
						<c:out value="${object.envio.hora}"/>	
					</td>
					<td>
						<c:out value="${object.cliente.firstName} ${object.cliente.lastName}"/>	
					</td>
					<td>
						<c:out value="${object.envio.direccion} ${object.envio.localidad} ${object.envio.provincia}"/>	
					</td>
					<td>
						<c:out value="${object.envio.estadoEnvio}"/>
					</td>
					<td>
						<spring:url value="/concesionario/{concesionarioId}/envio={envioId}/edit" var="envioUrl">
		       				<spring:param name="concesionarioId" value="${concesionario}"/>
		       				<spring:param name="envioId" value="${object.envio.id}"/>
	    			</spring:url>
		    			<a href="${fn:escapeXml(envioUrl)}" class="btn btn-default">Editar envio</a>
					</td>
				</tr>
			</c:forEach>
		</tbody>
	</table>		    
	 <spring:url value="/concesionario/{concesionarioId}" var="concesionarioUrl">
		        <spring:param name="concesionarioId" value="${concesionario}"/>
		    </spring:url>
		    <a href="${fn:escapeXml(concesionarioUrl)}" class="btn btn-default">Volver</a>
</madaja:layout>