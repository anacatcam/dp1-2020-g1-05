<%@page import="java.util.Objects"%>
<%@ page session="false" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="madaja" tagdir="/WEB-INF/tags" %>

<madaja:layout pageName="Recogidas">

<h2>Recogidas</h2>

	<table id="recogidasTable" class="table table-striped">
		<thead>
			<tr>
				<th style="width: 150px;">Id</th>
				<th style="width: 150px;">Vehículo</th>
				<th style="width: 150px;">Matrícula</th>
				<th style="width: 150px;">Fecha</th>
				<th style="width: 150px;">Hora</th>
				<th style="width: 150px;">Cliente</th>
				<th style="width: 150px;">Dirección</th>
				<th style="width: 150px;">Trabajador encargado</th>
				<th></th>
			</tr>
		</thead>
		
		<tbody>
			<c:forEach items="${recogidas}" var="recogida">
				<tr>
					<td>
						<c:out value="${recogida.id}"/>
					</td>
					<td>
						<c:out value="${recogida.alquiler.vehiculo.marca} 
										${recogida.alquiler.vehiculo.modelo}"/>
					</td>
					<td>
						<spring:url value="/vehiculos/{vehiculoId}" var="vehiculoUrl">
	                            <spring:param name="vehiculoId" value="${recogida.alquiler.vehiculo.id}"/>
						</spring:url>
                        <a href="${fn:escapeXml(vehiculoUrl)}"><c:out value="${recogida.alquiler.vehiculo.matricula}"/></a>
					</td>		
					<td>
						<c:out value="${recogida.alquiler.fechaFin}"/>
					</td>
					<td>
						<c:out value="${recogida.hora}"/>
					</td>
					<td>
						<c:out value="${recogida.alquiler.cliente.firstName} 
										${recogida.alquiler.cliente.lastName}"/>
					</td>
					<td>
						<c:out value="${recogida.direccion}, 
										${recogida.localidad} 
										(${recogida.provincia})"/>	
					</td>	
					<td>
						<c:out value="${recogida.mecanico.firstName}  ${recogida.mecanico.lastName}"/>
					</td>
					<td>
						<spring:url value="/recogida/{recogidaId}/edit" var="envioUrl">
							<spring:param name="recogidaId" value="${recogida.id}"/>
						</spring:url>
		    			<a href="${fn:escapeXml(envioUrl)}" class="btn btn-default" >Editar recogida</a>
					</td>
				</tr>
			</c:forEach>
		</tbody>
		
	</table>

</madaja:layout>