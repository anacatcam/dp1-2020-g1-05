<%@ page session="false" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="madaja" tagdir="/WEB-INF/tags" %>

<madaja:layout pageName="concesionarios">
	<h2>Concesionarios</h2>

	<div>
		<form:form action ="/doSearchConcesionarios" method="POST" role="form">
			<label for="inputSearch">Encuentra tu concesionario</label>
			<div class="form-group">
				<input class="form-control" placeholder="Search for..." id="search" name="search" type="text">
				<span class="col-sm-offset-2 col-sm-10">
					<button type="submit">Buscar</button>
				</span>
			</div>
		</form:form>
	</div>
	
	<table id="ownersTable" class="table table-striped">
		<thead>
		<tr>
			<th style="width: 150px;">Número de identificación</th>
			<th style="width: 150px;">Nombre</th>
			<th style="width: 150px;">Correo</th>
			<th style="width: 150px;">Teléfono</th>
		</tr>
		</thead>
		<tbody>
			<c:forEach items="${concesionarios}" var="concesionario">
				<tr>
					<td>
						<spring:url value="/concesionario/{concesionarioId}" var="concesionarioUrl">
							<spring:param name="concesionarioId" value="${concesionario.id}"/>
						</spring:url>
						<a href="${fn:escapeXml(concesionarioUrl)}"><c:out value="${concesionario.id}"/></a>
					</td>
					<td>
						<c:out value="${concesionario.nombre}"/>
					</td>
					<td>
						<c:out value="${concesionario.email}"/>	
					</td>
					<td>
						<c:out value="${concesionario.telefono}"/>	
					</td>
				</tr>
			</c:forEach>
		</tbody>
	</table>
</madaja:layout>