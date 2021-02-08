<%@ page session="false" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="madaja" tagdir="/WEB-INF/tags" %>

<madaja:layout pageName="misReservas">

<h2>Mis Reservas</h2>

<!-- -->
<c:out value="${msg}"/>
<!--  -->

<table id="ownersTable" class="table table-striped">
	<thead>
		<tr>
			<th style="width: 150px;">Veh�culo</th>
			<th style="width: 150px;">Fianza</th>
			<th style="width: 150px;">D�a que entra en gastos</th>
			<th style="width: 150px;">Anular</th>
		</tr>
	</thead>
	<tbody>
			<c:forEach items="${ventas}" var="venta">
				<c:if test = "${not empty venta.reserva}">
					<tr>
						<td>
							<c:out value="${venta.vehiculo.marca}"/>
							<c:out value="${venta.vehiculo.modelo} "/>
							<br/>
							<spring:url value="/vehiculos/{vehiculoId}" var="vehiculoUrl">
								<spring:param name="vehiculoId" value="${venta.vehiculo.id}"/>
							</spring:url>
							<a href="${fn:escapeXml(vehiculoUrl)}"><c:out value="(m�s info)"/></a>
						</td>
						<td>
							<c:out value="${venta.reserva.fianza}"/>
						</td>
						<td>
							<c:out value="${venta.reserva.fechaGastos}"/>
						</td>					
						<td>
							<a class="btn btn-default" href='<spring:url value="/reservas/{reservaId}/delete" >
							<spring:param name="reservaId" value="${venta.reserva.id}"/>
							</spring:url>'>Anular</a>
						</td>		
					</tr>
				</c:if>
			</c:forEach>
			
			<c:forEach items="${alquileres}" var="alquiler">
				<c:if test = "${not empty alquiler.reserva}">
					<tr>
						<td>
							<c:out value="${alquiler.vehiculo.marca} "/>
							<c:out value="${alquiler.vehiculo.modelo} "/>
							<br/>
							<spring:url value="/vehiculos/{vehiculoId}" var="vehiculoUrl">
								<spring:param name="vehiculoId" value="${alquiler.vehiculo.id}"/>
							</spring:url>
							<a href="${fn:escapeXml(vehiculoUrl)}"><c:out value="(m�s info)"/></a>
						</td>
						<td>
							<c:out value="${alquiler.reserva.fianza}"/>
						</td>
						<td>
							<c:out value="${alquiler.reserva.fechaGastos}"/>
						</td>					
						<td>
							<a class="btn btn-default" href='<spring:url value="/reservas/{reservaId}/delete" >
							<spring:param name="reservaId" value="${alquiler.reserva.id}"/>
							</spring:url>'>Anular</a>
						</td>		
					</tr>
				</c:if>
			</c:forEach>
	</tbody>
</table>
			

</madaja:layout>