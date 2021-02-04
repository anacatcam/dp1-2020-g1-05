<%@ page session="false" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="madaja" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="sec"
	uri="http://www.springframework.org/security/tags"%>
<madaja:layout pageName="concesionarios">
	
	<h2>Información del concesionario</h2>
	
	<table class="table table-striped">
        <tr>
            <th>Nombre</th>
            <td><c:out value="${concesionario.nombre}"/></td>
            <td></td>
        </tr>	
        <tr>
            <th>Provincia</th>
            <td><c:out value="${concesionario.provincia}"/></td>
            <td></td>
        </tr>
        <tr>
            <th>Localidad</th>
            <td><c:out value="${concesionario.localidad}"/></td>
            <td></td>
        </tr>
        <tr>
            <th>Dirección</th>
            <td><c:out value="${concesionario.direccion}"/></td>
            <td></td>
        </tr>
        <tr>
            <th>Código Postal</th>
            <td><c:out value="${concesionario.codigoPostal}"/></td>
            <td></td>
        </tr>
        <tr>
            <th>País</th>
            <td><c:out value="${concesionario.pais}"/></td>
            <td></td>
        </tr>
        <tr>
            <th>Email</th>
            <td><c:out value="${concesionario.email}"/></td>
            <td></td>
        </tr>
        <tr>
            <th>Teléfono</th>
            <td><c:out value="${concesionario.telefono}"/></td>
            <td></td>
        </tr>
	</table>
	
	<br/>
    <br/>
    <br/>
    <h2>Vehículos asociados</h2>
    
    <table class="table table-striped">
    	<c:forEach var="vehiculo" items="${concesionario.vehiculos}">
    		<tr>
                <td valign="top">
                    <dl class="dl-horizontal">
                    	<dd>
                        <dt>Matrícula</dt>
                        <dd>
                        	<spring:url value="/vehiculos/{vehiculoId}" var="vehiculoUrl">
	                            <spring:param name="vehiculoId" value="${vehiculo.id}"/>
                            </spring:url>
                        	<a href="${fn:escapeXml(vehiculoUrl)}"><c:out value="${vehiculo.matricula}"/></a>
                        </dd>
                        <dt>Marca</dt>
                        <dd><c:out value="${vehiculo.marca}"/></dd>
                        <dt>Modelo</dt>
                        <dd><c:out value="${vehiculo.modelo}"/></dd>
                        <dt>Plazas</dt>
                        <dd><c:out value="${vehiculo.plazas}"/></dd>
                        <dt>Precio de alquiler</dt>
                        <dd><c:out value="${vehiculo.precioAlquiler}"/></dd>
                        <dt>Precio de venta</dt>
                        <dd><c:out value="${vehiculo.precioVenta}"/></dd>
                        <dt>Disponibilidad</dt>
			        	<dd><c:out value="${vehiculo.disponible}"/></dd>
                    </dl>
                </td>
                <td valign="top">
                    <table class="table-condensed">
                    	<tr>
                                <td valign="top">
                                	<dl class="dl-horizontal">
                                		<dt>INCIDENCIAS</dt>
                                	</dl>
                                </td>
						</tr>
                        <c:forEach var="incidencia" items="${vehiculo.incidencias}">
                        	<tr>
                                <td valign="top">
                                	<dl class="dl-horizontal">
                                		<dd>
                        				<dt>Descripción</dt>
                        				<dd><c:out value="${incidencia.descripcion}"/></dd>
                       				    <dt>Mecánico</dt>
                        				<dd>
             							<c:forEach var="mecanico" items="${incidencia.mecanicos}">
                        					<c:out value="${mecanico.nombre} ${mecanico.apellidos}, ${mecanico.dni}"/><br>
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
                                	</dl>
                                </td>
                            </tr>
                        </c:forEach>
					</table>
				</td>
			</tr>
    	</c:forEach>
    </table>
    
    <br/>
    <br/>
    <br/>
    <h2>Gestores</h2>
    
    <table class="table table-striped">
    	<c:forEach var="gestor" items="${concesionario.gestores}">
    		<tr>
                <td valign="top">
                    <dl class="dl-horizontal">
                    	<dd>
                        <dt>DNI</dt>
                        <dd>
                        	<spring:url value="/gestor/{gestorId}" var="gestorUrl">
	                            <spring:param name="gestorId" value="${gestor.dni}"/>
                            </spring:url>
                        	<a href="${fn:escapeXml(gestorUrl)}"><c:out value="${gestor.dni}"/></a>
                        </dd>
                        <dt>Nombre</dt>
                        <dd><c:out value="${gestor.nombre}"/></dd>
                        <dt>Apellidos</dt>
                        <dd><c:out value="${gestor.apellidos}"/></dd>
                        <dt>Teléfono</dt>
                        <dd><c:out value="${gestor.telefono}"/></dd>
                        <dt>Email</dt>
                        <dd><c:out value="${gestor.email}"/></dd>
                    </dl>
                </td>
                <td valign="top">
                    <table class="table-condensed">
                        <thead>
                        <tr>
                            <th>Concesionario</th>
                            <th>Localización</th>
                        </tr>
                        </thead>
                        <c:forEach var="concesionario" items="${gestor.concesionarios}">
                            <tr>
                                <td>
                                	<spring:url value="/concesionario/{concesionarioId}" var="concesionarioUrl">
                                    	<spring:param name="concesionarioId" value="${concesionario.id}"/>
                                	</spring:url>
                                	<a href="${fn:escapeXml(concesionarioUrl)}"><c:out value="${concesionario.nombre}"/></a>
                                </td>
                                <td><c:out value="${concesionario.provincia}"/><c:out value=" (${concesionario.localidad})"/></td>
                            </tr>
                        </c:forEach>
                    </table>
                </td>
			</tr>
    	</c:forEach>
    </table>
	<sec:authorize access="hasAuthority('admin')">
	<spring:url value="/concesionario/{concesionarioId}/EnviosAlquileres" var="enviosUrl">
		        <spring:param name="concesionarioId" value="${concesionario.id}"/>
		    </spring:url>
		   		    <a href="${fn:escapeXml(enviosUrl)}" class="btn btn-default">Ver envios</a>
	</sec:authorize>

</madaja:layout>