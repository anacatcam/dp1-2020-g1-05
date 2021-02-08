<%@ page session="false" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="madaja" tagdir="/WEB-INF/tags" %>

<madaja:layout pageName="clientes">
	<h2>Clientes</h2>
	
	<div>
		<form:form action ="/doSearchClientes" method="POST" role="form">
			<label for="inputSearch">Buscar clientes</label>
			<div class="form-group">
				<div class="form-inline">
						<input class="form-control" placeholder="Nombre, apellidos o dni" id="search" name="search" type="text" pattern="^[^\s].+|^\d{1}$|^[a-zA-Z]{1}$" maxlength="27" size="30">
					<button class="btn btn-default" type="submit" style="margin-left:15px;">Buscar</button>
				</div>
			</div>
		</form:form>
	</div>
	
	<table id="ownersTable" class="table table-striped">
	<thead>
	<tr>
		<th style="width: 150px;">DNI</th>
		<th style="width: 150px;">Nombre</th>
		<th style="width: 150px;">Apellidos</th>
		<th style="width: 150px;">Teléfono</th>
		<th style="width: 150px;">Email</th>
		<th style="width: 150px;">Conflictivo</th>
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
							<c:out value="${cliente.firstName}"/>
						</td>
						<td>
							<c:out value="${cliente.lastName}"/>
						</td>
						<td>
							<c:out value="${cliente.telefono}"/>
						</td>
						<td>
							<c:out value="${cliente.email}"/>
						</td>
						<td>
							<c:out value="${cliente.esConflictivo}"/>
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

	<!--  -->
	<div>
			<nav aria-label="Pagination">
				<ul class="pagination">
				
					<c:choose>
						<c:when test="${prev == 0}">
							<li class="page-item disabled">
			    				<a class="page-link" href="#">&laquo;</a>
			    			</li>
						</c:when>
						<c:otherwise>
							<li class="page-item">
			    				<a class="page-link" href="?page=${prev}">&laquo;</a>
			    			</li>
						</c:otherwise>
					</c:choose>

					
					<c:forEach items="${pages}" var="page">
					
					<c:choose>
						<c:when test="${current == page}">
							<li class="page-item active">
				    			<a class="page-link" href="?page=${page}">${page}</a>
				    		</li>
						</c:when>
						<c:otherwise>
							<li class="page-item">
				    			<a class="page-link" href="?page=${page}">${page}</a>
				    		</li>
						</c:otherwise>
					</c:choose>
						
				    </c:forEach>
					
					
					<c:choose>
						<c:when test="${current == max}">
							<li class="page-item disabled">
			    				<a class="page-link" href="#">&raquo</a>
			    			</li>
						</c:when>
						<c:otherwise>
							<li class="page-item">
			    				<a class="page-link" href="?page=${next}">&raquo</a>
			    			</li>
						</c:otherwise>
					</c:choose>

			  	</ul>
			</nav>
	</div>
	
	<!--  -->


</madaja:layout>