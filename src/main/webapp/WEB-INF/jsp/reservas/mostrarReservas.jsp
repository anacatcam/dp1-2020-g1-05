<%@ page session="false" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="madaja" tagdir="/WEB-INF/tags" %>

<madaja:layout pageName="mis-reservas">
	<h2>Reservas</h2>	
	
				
	<table id="ownersTable" class="table table-striped">
		<thead>
		<tr>
			<th style="width: 150px;">Vehículo</th>
			<th style="width: 150px;">Fianza</th>
			<th style="width: 150px;">Día que entra en gastos</th>
			<th style="width: 150px;">Anular</th>
		</tr>
		</thead>
		
		<tbody>
			
			<c:forEach items="${ventas}" var="venta">
				<tr>
					<td>
						<c:out value="${venta.vehiculo.marca} "/>
						<c:out value="${venta.vehiculo.modelo} "/>
						<br/>
						<spring:url value="/vehiculos/{vehiculoId}" var="vehiculoUrl">
							<spring:param name="vehiculoId" value="${venta.vehiculo.id}"/>
						</spring:url>
						<a href="${fn:escapeXml(vehiculoUrl)}"><c:out value="(más info)"/></a>
					</td>
					<td>
						<c:out value="${venta.reserva.fianza}"/>
					</td>
					<td>
						<c:out value="${venta.reserva.fechaGastos}"/>
					</td>					
					<td>
						<a class="btn btn-default" href='<spring:url value="/reservas/delete/{reservaId}" >
															<spring:param name="reservaId" value="${venta.reserva.id}"/>
														</spring:url>'>Anular</a>
					</td>		
				</tr>
			</c:forEach>
		
			<c:forEach items="${alquileres}" var="alquiler">
				<tr>
					<td>
						<c:out value="${alquiler.vehiculo.marca} "/>
						<c:out value="${alquiler.vehiculo.modelo} "/>
						<br/>
						<spring:url value="/vehiculos/{vehiculoId}" var="vehiculoUrl">
							<spring:param name="vehiculoId" value="${alquiler.vehiculo.id}"/>
						</spring:url>
						<a href="${fn:escapeXml(vehiculoUrl)}"><c:out value="(más info)"/></a>
					</td>
					<td>
						<c:out value="${alquiler.reserva.fianza}"/>
					</td>
					<td>
						<c:out value="${alquiler.reserva.fechaGastos}"/>
					</td>					
					<td>
						<a class="btn btn-default" href='<spring:url value="/reservas/delete/{reservaId}" >
															<spring:param name="reservaId" value="${alquiler.reserva.id}"/>
														</spring:url>'>Anular</a>
					</td>		
				</tr>
			</c:forEach>
		

			
		</tbody>
		
	</table>
	
	


</madaja:layout>