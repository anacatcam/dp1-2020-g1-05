<%@ page session="false" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="madaja" tagdir="/WEB-INF/tags" %>

<madaja:layout pageName="alquileres">

<h2>Alquileres</h2>

<table id="ownersTable" class="table table-striped">
		<thead>
		<tr>
			<th style="width: 150px;">Vehículo</th>
			<th style="width: 150px;">Matrícula</th>
			<th style="width: 150px;">Precio</th>
			<th style="width: 150px;">Fecha de inicio</th>
			<th style="width: 150px;">Fecha de fin</th>
			<th style="width: 150px;">Cliente</th>
			<th style="width: 150px;">Devuelto</th>
			<th style="width: 150px;"></th>
		</tr>
		</thead>
		<tbody>
			<c:forEach items="${alquileres}" var="alquiler">
				<tr>
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
						<c:out value="${alquiler.vehiculo.precioAlquiler}"/>
					</td>
					<td>
						<c:out value="${alquiler.fechaInicio}"/>	
					</td>
					<td>
						<c:out value="${alquiler.fechaFin}"/>	
					</td>
					<td>
						<c:out value="${alquiler.cliente.firstName} "/>	<c:out value="${alquiler.cliente.lastName}"/>	
					</td>
					<td>
						<c:choose>
							<c:when test="${alquiler.devuelto eq true}">
								Sí
							</c:when>
							<c:otherwise>
								No
							</c:otherwise>
	                    </c:choose>
					</td>
					<td>
						<c:choose>
							<c:when test="${alquiler.devuelto eq false}">
								<spring:url value="/alquileres/{alquilerId}/devolucion" var="devolverUrl">
		                            <spring:param name="alquilerId" value="${alquiler.id}"/>
								</spring:url>
		                        <a href="${fn:escapeXml(devolverUrl)}">Marcar como devuelto</a>
							</c:when>
							<c:otherwise>
							</c:otherwise>
	                    </c:choose>
					</td>
				</tr>
			</c:forEach>
		</tbody>
	</table>
</madaja:layout>