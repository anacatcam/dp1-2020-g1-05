<%@ page session="false" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="madaja" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="sec"
	uri="http://www.springframework.org/security/tags"%>
<madaja:layout pageName="vehiculos">
	
	<h2>Información del vehículo</h2>
	
	<table class="table table-striped">
        <tr>
            <th>Matrícula</th>
            <td><b><c:out value="${vehiculos.matricula}"/></b></td>
        </tr>
        <tr>
            <th>Marca</th>
            <td><c:out value="${vehiculos.marca}"/></td>
        </tr>
        <tr>
            <th>Modelo</th>
            <td><c:out value="${vehiculos.modelo}"/></td>
        </tr>
        <tr>
            <th>Número de puertas</th>
            <td><c:out value="${vehiculos.puertas}"/> puertas</td>
        </tr>
        <tr>
            <th>Plazas</th>
            <td><c:out value="${vehiculos.plazas}"/> pasajeros</td>
        </tr>
        <tr>
            <th>Precio de alquiler</th>
            <td><c:out value="${vehiculos.precioAlquiler}"/></td>
        </tr>
        <tr>
            <th>Precio de venta</th>
            <td><c:out value="${vehiculos.precioVenta}"/></td>
        </tr>
        <tr>
            <th>Capacidad del maletero</th>
            <td><c:out value="${vehiculos.maletero}"/> L</td>
        </tr>
        <tr>
            <th>Kilómetros recorridos</th>
            <td><c:out value="${vehiculos.kmActuales}"/></td>
        </tr>
        <tr>
            <th>Tipo de cambio</th>
            <td><c:out value="${vehiculos.cambio}"/></td>
        </tr>
        <tr>
            <th>Tipo de combustible</th>
            <td><c:out value="${vehiculos.combustible}"/></td>
        </tr>
        <tr>
            <th>Características</th>
            <td><c:out value="${vehiculos.caracteristicas}"/></td>
        </tr>
        <tr>
            <th>Estado</th>
            <td><c:out value="${vehiculos.estado}"/></td>
        </tr>
        <tr>
        	<th>Disponibilidad</th>
        	<td><c:out value="${vehiculos.disponible}"/></td>
		</tr>
		<tr>
			<th>Concesionario</th>
			<td>
				<spring:url value="/concesionario/{concesionarioId}" var="concesionarioUrl">
			        <spring:param name="concesionarioId" value="${vehiculos.concesionario.id}"/>
			    </spring:url>
			    <a href="${fn:escapeXml(concesionarioUrl)}"><c:out value="${vehiculos.concesionario.nombre}"/></a>
			</td>
		</tr>
		<tr>
		<c:choose>
            <c:when test="${vehiculos.seguroVehiculo != null}">
				<tr>
					<th>Seguro</th>
					<td><c:out value="${vehiculos.seguroVehiculo.cobertura}"/></td>
				</tr>
			</c:when>
			<c:otherwise>
				<tr></tr>
			</c:otherwise>
		</c:choose>
        </tr>
		<c:choose>
			<c:when test="${vehiculos.oferta != null}">
				<tr>
					<th>Oferta</th>
					<td>
						<spring:url value="/oferta/{ofertaId}" var="ofertaUrl">
		        			<spring:param name="ofertaId" value="${vehiculos.oferta.id}"/>
		    			</spring:url>
						<a href="${fn:escapeXml(ofertaUrl)}"><c:out value="${vehiculos.oferta.name}"/></a>
					</td>
				</tr>
			</c:when>
			<c:otherwise>
				<tr></tr>
			</c:otherwise>
		</c:choose>
    </table>
    
	<sec:authorize access="hasAuthority('admin')">
	    <spring:url value="/vehiculos/{vehiculoId}/edit" var="editUrl">
	        <spring:param name="vehiculoId" value="${vehiculos.id}"/>
	    </spring:url>
	    <a href="${fn:escapeXml(editUrl)}" class="btn btn-default">Editar vehículo</a>
    </sec:authorize>
    <sec:authorize access="hasAuthority('admin')">
    	<c:choose>
           	<c:when test="${vehiculos.disponible.id eq 4}">
           		<td>
				    <spring:url value="/vehiculos/{vehiculoId}/edit" var="altaUrl">
				        <spring:param name="vehiculoId" value="${vehiculos.id}"/>
				    </spring:url>
				    <a href="${fn:escapeXml(altaUrl)}"  class="btn btn-default">Dar de alta</a>
				</td>
           	</c:when>
           	<c:otherwise>
				<td>
				    <spring:url value="/vehiculos/{vehiculoId}/delete" var="deleteUrl">
				        <spring:param name="vehiculoId" value="${vehiculos.id}"/>
				    </spring:url>
				    <a href="${fn:escapeXml(deleteUrl)}"  class="btn btn-default">Dar de baja</a>
				</td>
			</c:otherwise>
		</c:choose>
    </sec:authorize>
    <br/>
    <br/>
    <br/>
    <h2>Incidencias</h2>
    
    <table class="table table-striped">
    	<c:forEach var="incidencia" items="${vehiculos.incidencias}">
    		<tr>
                <td valign="top">
                    <dl class="dl-horizontal">
                        <dt>Descripción</dt>
                        <dd><c:out value="${incidencia.descripcion}"/></dd>
                        <dt>Mecánico</dt>
                        <dd>
             				<c:forEach var="mecanico" items="${incidencia.mecanicos}">
                        		<c:out value="${mecanico.nombre}"/>, <c:out value="${mecanico.dni}"/><br>
                        	</c:forEach>
                        </dd>
                        <dt>Solucionada</dt>
                        <dd>
                        	<c:choose>
                        		<c:when test="${incidencia.solucionada}">
                        			Sí
                        		</c:when>
                        		<c:otherwise>
                        			No
                        		</c:otherwise>
                        	</c:choose>
                        </dd>
                        <dt>
                        	<spring:url value="/vehiculos/{vehiculoId}/incidencia/{incidenciaId}/edit" var="incidenciaUrl">
	                            <spring:param name="vehiculoId" value="${vehiculos.id}"/>
	                            <spring:param name="incidenciaId" value="${incidencia.id}"/>
                            </spring:url>
                            <a href="${fn:escapeXml(incidenciaUrl)}">Editar incidencia</a>
                        </dt>
                    </dl>
                </td>
    	</c:forEach>
    </table>
    
    <spring:url value="/vehiculos/{vehiculoId}/incidencia/new" var="editUrl">
        <spring:param name="vehiculoId" value="${vehiculos.id}"/>
    </spring:url>
    <a href="${fn:escapeXml(editUrl)}" class="btn btn-default">Añadir incidencia</a>
    
</madaja:layout>