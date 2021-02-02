<%@ page session="false" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="madaja" tagdir="/WEB-INF/tags" %>

<madaja:layout pageName="concesionarios">
	<h2>Concesionarios</h2>

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