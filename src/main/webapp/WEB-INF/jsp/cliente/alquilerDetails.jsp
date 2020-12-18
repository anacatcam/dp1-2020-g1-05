<%@ page session="false" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="madaja" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="sec"
	uri="http://www.springframework.org/security/tags"%>
<madaja:layout pageName="clientes">
	<c:if test ="${alquileres.size() == 0}"> 
			<h2>Este cliente no ha hecho ningun alquiler</h2>
	</c:if>
	<c:if test ="${alquileres.size() > 0}"> 
	<h2>Información de los alquileres del cliente con DNI: <c:out value="${cliente.dni}"/></h2>
	
	<table id="ownersTable" class="table table-striped">
	<thead>
	<tr>
		<th style="width: 150px;">DNI cliente</th>
		<th style="width: 150px;">Matrícula</th>
		<th style="width: 150px;">Precio del alquiler</th>
		<th style="width: 150px;">Marca</th>
		<th style="width: 150px;">Modelo</th>
		<th style="width: 150px;">Kilómetros Actuales</th>
		<th style="width: 150px;">Desde</th>
		<th style="width: 150px;">Hasta</th>
		<th style="width: 150px;">Reserva asociada</th>
	</tr>
	</thead>
	<tbody>
			<c:forEach items="${alquileres}" var="alquiler">
				<tr>
					<td>
						<c:out value="${cliente.dni}"/>
					</td>
					<td>
						<c:out value="${alquiler.vehiculo.matricula}"/>
					</td>
					<td>
						<c:out value="${alquiler.vehiculo.precioAlquiler}"/>
					</td>
					<td>
						<c:out value="${alquiler.vehiculo.marca}"/>
					</td>
					<td>
						<c:out value="${alquiler.vehiculo.modelo}"/>
					</td>
					<td>
						<c:out value="${alquiler.vehiculo.kmActuales}"/>
					</td>
					<td>
						<c:out value="${alquiler.fechaInicio}"/>
					</td>
					<td>
						<c:out value="${alquiler.fechaFin}"/>
					</td>
					<td>
						<c:if test ="${alquiler.reserva.id == null }"> 
							No está asociada a ninguna reserva
						</c:if>
						<c:out value="${alquiler.reserva.id}"/>
					</td>
				</tr>
			</c:forEach>
		</tbody>
	</table>
	</c:if>
</madaja:layout>