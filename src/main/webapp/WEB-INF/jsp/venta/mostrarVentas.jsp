<%@ page session="false" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="madaja" tagdir="/WEB-INF/tags" %>

<madaja:layout pageName="ventas">

<h2>Ventas</h2>

<table id="ownersTable" class="table table-striped">
		<thead>
		<tr>
			<th style="width: 150px;">Vehículo</th>
			<th style="width: 150px;">Matrícula</th>
			<th style="width: 150px;">Fecha de la compra</th>
			<th style="width: 150px;">Precio</th>
			<th style="width: 150px;">Cliente</th>
		</tr>
		</thead>
		<tbody>
			<c:forEach items="${ventas}" var="venta">
				<tr>
					<td>
						<c:out value="${venta.vehiculo.marca} ${venta.vehiculo.modelo}"/>	
					</td>
					<td>
						<spring:url value="/vehiculos/{vehiculoId}" var="vehiculoUrl">
	                            <spring:param name="vehiculoId" value="${venta.vehiculo.id}"/>
						</spring:url>
                        <a href="${fn:escapeXml(vehiculoUrl)}"><c:out value="${venta.vehiculo.matricula}"/></a>
					</td>
					<td>
						<c:out value="${venta.fecha}"/>
					</td>
					<td>
						<c:out value="${venta.vehiculo.precioVenta}"/>
					</td>
					<td>
						<c:out value="${venta.cliente.firstName} "/><c:out value="${venta.cliente.lastName}"/>	
					</td>
				</tr>
			</c:forEach>
		</tbody>
	</table>
</madaja:layout>