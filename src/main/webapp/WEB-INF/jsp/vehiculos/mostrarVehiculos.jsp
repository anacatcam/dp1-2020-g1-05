<%@ page session="false" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="madaja" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="sec"
	uri="http://www.springframework.org/security/tags"%>

<madaja:layout pageName="vehiculos">
	<h2>Vehículos</h2>
	
	<spring:url value="/vehiculos" var="todosUrl"></spring:url><a class="btn btn-default" href="${fn:escapeXml(todosUrl)}">Todos</a>
	
	<c:forEach items="${disponible}" var="disponible">
		<c:choose>
			<c:when test="${disponible.id > 3}">
				<sec:authorize access="hasAuthority('admin')">
					<spring:url value="/vehiculos/disponible/{disponibleId}" var="disponibleUrl">
		    			<spring:param name="disponibleId" value="${disponible.id}"/>
	    			</spring:url>
	    			<a class="btn btn-default" href="${fn:escapeXml(disponibleUrl)}"><c:out value="${disponible.name}"/></a>
	    		</sec:authorize>
    		</c:when>
    		<c:otherwise>
				<spring:url value="/vehiculos/disponible/{disponibleId}" var="disponibleUrl">
		    		<spring:param name="disponibleId" value="${disponible.id}"/>
	    		</spring:url>
	   			<a class="btn btn-default" href="${fn:escapeXml(disponibleUrl)}"><c:out value="${disponible.name}"/></a>
    		</c:otherwise>
    	</c:choose>
	</c:forEach>
															
	<table id="ownersTable" class="table table-striped">
		<thead>
		<tr>
			<th style="width: 150px;">Matrícula</th>
			<th style="width: 150px;">Marca</th>
			<th style="width: 150px;">Modelo</th>
			<th style="width: 150px;">Precio de alquiler</th>
			<th style="width: 150px;">Precio de venta</th>
			<th style="width: 150px;">Plazas</th>
			<th style="width: 150px;">Disponibilidad</th>
			<sec:authorize access="hasAuthority('admin')">
				<th></th>
			</sec:authorize>
			
		</tr>
		</thead>
		<tbody>
			<c:forEach items="${vehiculos}" var="vehiculo">
				<tr>
					<td>
						<spring:url value="/vehiculos/{vehiculoId}" var="vehiculoUrl">
							<spring:param name="vehiculoId" value="${vehiculo.id}"/>
						</spring:url>
						<a href="${fn:escapeXml(vehiculoUrl)}"><c:out value="${vehiculo.matricula}"/></a>
					</td>
					<td>
						<c:out value="${vehiculo.marca}"/>
					</td>
					<td>
						<c:out value="${vehiculo.modelo}"/>
					</td>
					<td>
		           		<c:out value="${vehiculo.precioAlquiler}"/>
		           	</td>
					<td>
	           			<c:out value="${vehiculo.precioVenta}"/>
	           		</td>
					<td>
						<c:out value="${vehiculo.plazas}"/> pasajeros
					</td>
					<td>
						<c:out value="${vehiculo.disponible}"/>
					</td>
					<sec:authorize access="hasAuthority('admin')">
						<c:choose>
<%-- 							<c:when test="${vehiculo.disponible.id == 4}">
                        		<td>
								    <spring:url value="/vehiculos/{vehiculoId}/devolucion" var="altaUrl">
								        <spring:param name="vehiculoId" value="${vehiculo.id}"/>
								    </spring:url>
								    <a href="${fn:escapeXml(altaUrl)}">Devuelto</a>
								</td>
                        	</c:when> --%>
                        	<c:when test="${vehiculo.disponible.id > 4}">
                        		<td>
								    <spring:url value="/vehiculos/{vehiculoId}/edit" var="altaUrl">
								        <spring:param name="vehiculoId" value="${vehiculo.id}"/>
								    </spring:url>
								    <a href="${fn:escapeXml(altaUrl)}">Dar de alta</a>
								</td>
                        	</c:when>
                        	<c:otherwise>
								<td>
								    <spring:url value="/vehiculos/{vehiculoId}/delete" var="deleteUrl">
								        <spring:param name="vehiculoId" value="${vehiculo.id}"/>
								    </spring:url>
								    <a href="${fn:escapeXml(deleteUrl)}">Dar de baja</a>
								</td>
							</c:otherwise>
						</c:choose>
				    </sec:authorize>
				</tr>
			</c:forEach>
		</tbody>
	</table>
	<br/>
	<sec:authorize access="hasAuthority('admin')">
	<a class="btn btn-default" href='<spring:url value="/vehiculos/new" htmlEscape="true"/>'>Añadir vehículo</a>
	</sec:authorize>
</madaja:layout>