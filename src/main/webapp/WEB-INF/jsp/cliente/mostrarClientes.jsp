<%@ page session="false" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="madaja" tagdir="/WEB-INF/tags" %>

<madaja:layout pageName="clientes">
	<h2>Clientes</h2>
	
	<table id="ownersTable" class="table table-striped">
	<thead>
	<tr>
		<th style="width: 150px;">DNI</th>
		<th style="width: 150px;">Nombre</th>
		<th style="width: 150px;">Apellidos</th>
		<th style="width: 150px;">Teléfono</th>
		<th style="width: 150px;">Email</th>
		<th style="width: 150px;">Ventas</th>
		<th style="width: 150px;">Alquileres</th>
	</tr>
	</thead>
		<tbody>
			<c:forEach items="${clientes}" var="cliente">
				<tr>
						<td>
							<c:out value="${cliente.dni}"/>							
						</td>
						<td>
							<c:out value="${cliente.nombre}"/>
						</td>
						<td>
							<c:out value="${cliente.apellidos}"/>
						</td>
						<td>
							<c:out value="${cliente.telefono}"/>
						</td>
						<td>
							<c:out value="${cliente.email}"/>
						</td>
						<td>
							<spring:url value="/clientes/ventas/{clienteId}" var="clienteURL">
								<spring:param name="clienteId" value="${cliente.dni}"/>
							</spring:url>
							<a href="${fn:escapeXml(clienteURL)}">Ventas</a>
						</td>
						<td>
							<spring:url value="/clientes/alquileres/{clienteId}" var="clienteURL">
								<spring:param name="clienteId" value="${cliente.dni}"/>
							</spring:url>
							<a href="${fn:escapeXml(clienteURL)}">Alquileres</a>
						</td>
				</tr>
			</c:forEach>
		</tbody>
	</table>



</madaja:layout>