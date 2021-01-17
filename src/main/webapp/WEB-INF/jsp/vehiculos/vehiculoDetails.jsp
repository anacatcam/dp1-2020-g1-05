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
        <c:if test="${vehiculos.disponible.id eq 1}"> 
	        <tr>
	            <th>Precio de alquiler</th>
	            <td><c:out value="${vehiculos.precioAlquiler}"/></td>
	        </tr>
		</c:if>
		<c:if test="${vehiculos.disponible.id eq 2}"> 
        	<tr>
            	<th>Precio de venta</th>
            	<td><c:out value="${vehiculos.precioVenta}"/></td>
        	</tr>
        </c:if>
        <c:if test="${vehiculos.disponible.id >= 3}"> 
	        <tr>
	            <th>Precio de alquiler</th>
	            <td><c:out value="${vehiculos.precioAlquiler}"/></td>
	        </tr>
        	<tr>
            	<th>Precio de venta</th>
            	<td><c:out value="${vehiculos.precioVenta}"/></td>
        	</tr>
        </c:if>
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
					<td>
						<spring:url value="/vehiculos/{vehiculoId}/seguro/{seguroVehiculoId}/view" var="seguroVehiculoUrl">
							<spring:param name="vehiculoId" value="${vehiculos.id}"/>
			        		<spring:param name="seguroVehiculoId" value="${vehiculos.seguroVehiculo.id}"/>
			    		</spring:url>
			    		<a href="${fn:escapeXml(seguroVehiculoUrl)}"><c:out value="${vehiculos.seguroVehiculo.cobertura}"/></a>
			    	</td>
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
    
    <!-- ACCIONES DE ADMINISTRADOR -->
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
    
    
    <!-- ACCIONES DE CLIENTE -->
    <sec:authorize access="hasAuthority('cliente')">
    	<c:if test="${vehiculos.disponible.id eq 1}"> 
			<spring:url value="/vehiculos/{vehiculoId}/alquilar" var="reservarUrl">
		        <spring:param name="vehiculoId" value="${vehiculos.id}"/>
		    </spring:url>
		    <a href="${fn:escapeXml(reservarUrl)}" class="btn btn-default">Alquilar</a>
	    </c:if>
	    
	    <c:if test="${vehiculos.disponible.id eq 2}">
		    <spring:url value="/vehiculos/{vehiculoId}/comprar" var="reservarUrl">
		        <spring:param name="vehiculoId" value="${vehiculos.id}"/>
		    </spring:url>
		    <a href="${fn:escapeXml(reservarUrl)}" class="btn btn-default">Comprar</a>
	    </c:if>
	    
	    <c:if test="${vehiculos.disponible.id eq 3}"> 
			<spring:url value="/vehiculos/{vehiculoId}/alquilar" var="reservarUrl">
		        <spring:param name="vehiculoId" value="${vehiculos.id}"/>
		    </spring:url>
		    <a href="${fn:escapeXml(reservarUrl)}" class="btn btn-default">Alquilar</a>
		
		    <spring:url value="/vehiculos/{vehiculoId}/comprar" var="reservarUrl">
		        <spring:param name="vehiculoId" value="${vehiculos.id}"/>
		    </spring:url>
		    <a href="${fn:escapeXml(reservarUrl)}" class="btn btn-default">Comprar</a>
	    </c:if>
	    <c:if test="${vehiculos.disponible.id < 4}"> 
		    <spring:url value="/reservas/{vehiculoId}/nuevaReserva" var="reservarUrl">
		        <spring:param name="vehiculoId" value="${vehiculos.id}"/>
		    </spring:url>
		    <a href="${fn:escapeXml(reservarUrl)}" class="btn btn-default">Reservar</a>
		</c:if>
    </sec:authorize>
    <sec:authorize access="hasAuthority('admin')">
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
	                        <dt>Cliente responsable</dt>
	                        <dd><c:out value="${incidencia.cliente.firstName}"/>&nbsp;<c:out value="${incidencia.cliente.lastName}"/>, <c:out value="${incidencia.cliente.dni}"/></dd>
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
    </sec:authorize>
       
    
    <br/>
    <br/>
    <br/>
    <h2>Seguro de cliente</h2>
    <table class="table table-striped">
    	<c:forEach var="seguroCliente" items="${vehiculos.segurosCliente}">
    		<td valign="top">
    			<dl class="dl-horizontal">
    				<dt>Cobertura</dt>
    				<dd><c:out value="${seguroCliente.cobertura}"/></dd>
    				<dt>Franquicia</dt>
    				<dd>
    					<c:choose>
        		       	<c:when test="${seguroCliente.franquicia eq 0}">
       		           	<dt>Sin franquicia</dt>
       		        	</c:when>
       		        	<c:otherwise>
       		     		<c:out value="${seguroCliente.franquicia}"/>
       		        	</c:otherwise>
        		    	</c:choose>
      		      	</dd>
      		      	<dt>Fecha inicio</dt>
      		      	<dd><c:out value="${seguroCliente.fechaInicio}"/></dd>
       		     	<dt>Fecha fin</dt>
       		   		<dd><c:out value="${seguroCliente.fechaFin}"/></dd>
        		    <dt>Precio final</dt>
       		   		<dd><c:out value="${seguroCliente.precio}"/></dd>
            		<dt>
            		  <sec:authorize access="hasAuthority('admin')">	
        				<spring:url value="/vehiculos/{vehiculoId}/seguroCliente/{seguroClienteId}/edit" var="seguroClienteUrl">
						<spring:param name="vehiculoId" value="${vehiculos.id}"/>
     				   	<spring:param name="seguroClienteId" value="${seguroCliente.id}"/>
    					</spring:url>
    					<a href="${fn:escapeXml(seguroClienteUrl)}">Editar seguro cliente</a>
    				  </sec:authorize>
           			</dt>
    			</dl>
    		</td>
    	</c:forEach>
    </table>
   
   <sec:authorize access="hasAuthority('admin')">
   		<c:if test="${vehiculos.segurosCliente.size()==0}">
  			<spring:url value="/vehiculos/{vehiculoId}/seguroCliente/new" var="editUrl">
    		<spring:param name="vehiculoId" value="${vehiculos.id}"/>
    		</spring:url>
    		<a href="${fn:escapeXml(editUrl)}" class="btn btn-default">Añadir seguro cliente</a>
    	</c:if>
    
   <c:forEach var="seguroCliente" items="${vehiculos.segurosCliente}">
    <c:set var = "c" value="${seguroCliente.id}"/>
    
   	
    	<c:if test="${c>0}">
    		<spring:url value="/vehiculos/{vehiculoId}/seguroCliente/{seguroClienteId}/delete" var="seguroClienteUrl">
			<spring:param name="seguroClienteId" value="${seguroCliente.id}"/>
			<spring:param name="vehiculoId" value="${vehiculos.id}"/>
			</spring:url>
			<a href="${fn:escapeXml(seguroClienteUrl)}" class="btn btn-default">Eliminar seguro cliente</a>
    	</c:if>
     </c:forEach>
    </sec:authorize>
</madaja:layout>