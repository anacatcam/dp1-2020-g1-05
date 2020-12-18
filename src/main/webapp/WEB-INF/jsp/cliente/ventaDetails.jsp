<%@ page session="false" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="madaja" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="sec"
	uri="http://www.springframework.org/security/tags"%>
<madaja:layout pageName="clientes">
	<c:if test ="${ventas.size() == 0}"> 
			<h2>Este cliente no ha hecho ninguna venta</h2>
	</c:if>
	<c:if test ="${ventas.size() > 0}"> 
	<h2>Información de las ventas del cliente con DNI: <c:out value="${cliente.dni}"/></h2>
	<table id="ownersTable" class="table table-striped">
	<thead>
	<tr>
		<th style="width: 150px;">ID</th>
		<th style="width: 150px;">DNI</th>
		<th style="width: 150px;">Matrícula</th>
		<th style="width: 150px;">Precio</th>
		<th style="width: 150px;">Marca</th>
		<th style="width: 150px;">Modelo</th>
		<th style="width: 150px;">Kilómetros Actuales</th>
		<th style="width: 150px;">Reserva asociada</th>
	</tr>
	</thead>
	<tbody>
			<c:forEach items="${ventas}" var="venta">
				<tr>
						<td>
							<c:out value="${venta.id}"/>							
						</td>
						<td>
							<c:out value="${cliente.dni}"/>
						</td>
						<td>
							<c:out value="${venta.vehiculo.matricula}"/>
						</td>
						<td>
							<c:out value="${venta.vehiculo.precioVenta}"/>
						</td>
						<td>
							<c:out value="${venta.vehiculo.marca}"/>
						</td>
						<td>
							<c:out value="${venta.vehiculo.modelo}"/>
						</td>
						<td>
							<c:out value="${venta.vehiculo.kmActuales}"/>
						</td>
						<td>
							<c:if test ="${venta.reserva.id == null }"> 
								No está asociada a ninguna reserva
							</c:if>
							<c:out value="${venta.reserva.id}"/>
						</td>
					
				</tr>
			</c:forEach>
		</tbody>
	</table>
	</c:if>
</madaja:layout>