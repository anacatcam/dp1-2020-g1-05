<%@ page session="false" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="madaja" tagdir="/WEB-INF/tags" %>

<madaja:layout pageName="misAlquileres">

<h2>Envios</h2>

<table id="ownersTable" class="table table-striped">
		<thead>
		<tr>
			<th style="width: 150px;">Número de identificación</th>
			<th style="width: 150px;">Vehículo</th>
			<th style="width: 150px;">Matrícula</th>
			<th style="width: 150px;">Fecha</th>
			<th style="width: 150px;">Hora</th>
			<th style="width: 150px;">Dirección</th>
		</tr>
		</thead>
		<tbody>
			<c:forEach items="${alquileres}" var="alquiler" varStatus="status">
				<tr>
					<td>
						<c:out value="${alquiler.envio.id}"/>
					</td>
					<td>
						<c:out value="${alquiler.vehiculo.marca} ${alquiler.vehiculo.modelo}"/>	
					</td>
				 	<td>
						<spring:url value="/vehiculos/{vehiculoId}" var="vehiculoUrl">
	                            <spring:param name="vehiculoId" value="${alquiler.vehiculo.id}"/>
						</spring:url>
                        <a href="${fn:escapeXml(vehiculoUrl)}"><c:out value="${alquiler.vehiculo.matricula}"/></a>
					</td>
					<td>
						<c:out value="${alquiler.fechaInicio}"/>
					</td>
					<td>
						<c:out value="${alquiler.envio.hora}"/>	
					</td>
					<td>
						<c:out value="${alquiler.envio.direccion} ${alquiler.envio.localidad} ${alquiler.envio.provincia}"/>	
					</td>
				</tr>
			</c:forEach>
		</tbody>
	</table>		    
	 <input type=button class="btn btn-default" value="Volver" onCLick="history.back()">
</madaja:layout>