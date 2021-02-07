<%@ page session="false" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="madaja" tagdir="/WEB-INF/tags" %>

<madaja:layout pageName="editarRecogida">
	<h2>Editar recogida</h2>
	
	<table class="table table-striped">
		<tr>
            <th>Id</th>
            <td><c:out value="${recogida.id}"/></td>
        </tr>	
		<tr>
            <th>Vehículo</th>
           	<td><c:out value="${recogida.alquiler.vehiculo.marca} 
           						${recogida.alquiler.vehiculo.modelo}"/></td>
        </tr>	
        <tr>
            <th>Matrícula</th>
            <td><c:out value="${recogida.alquiler.vehiculo.matricula}"/></td>
        </tr>
        <tr>
            <th>Cliente</th>
            <td><c:out value="${recogida.alquiler.cliente.firstName} 
            					${recogida.alquiler.cliente.lastName}"/></td>
        </tr>
        <tr>
            <th>Fecha</th>
            <td><c:out value="${recogida.fecha}"/></td>
        </tr>
        <tr>
            <th>Hora</th>
            <td><c:out value="${recogida.hora}"/></td>
        </tr>	
        <tr>
            <th>Dirección</th>
            <td><c:out value="${recogida.direccion}, 
            					${recogida.localidad} 
            					(${recogida.provincia})"/></td>
        </tr>		
	</table>
	
	<form:form modelAttribute="recogida" class="form-horizontal" id="add-owner-form" >
 		<input type="hidden" name="id" value="${recogida.id}"/>
 		<div class="form-group has-feedback">
 			<input type="hidden" name="direccion" value="${recogida.direccion}"/>
 			<input type="hidden" name="localidad" value="${recogida.localidad}"/>
			<input type="hidden" name="provincia" value="${recogida.provincia}"/>
			<input type="hidden" name="codigoPostal" value="${recogida.codigoPostal}"/>
		 	<input type="hidden" name="pais" value="${recogida.pais}"/>
			<input type="time" style="visibility:hidden;" name="Hora" value="${recogida.hora}"/>
			<input type="date" style="visibility:hidden;" name="Fecha" value="${recogida.fecha}"/>
			<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
			<div class="col-sm-5">
				<label  for="estados">Trabajador encargado</label>
				<select id="mecanico" name="mecanico">
					<c:forEach items="${mecanicos}" var="mecanico">
							<option value="${mecanico}">
								<c:out value="${mecanico.firstName} ${mecanico.lastName}"/> 
							</option>
					</c:forEach>
				</select>	
				
    	    </div>
			<div class="col-sm-offset-0 col-sm-5">
				<button class="btn btn-default" type="submit">Aceptar</button>
			</div>
		</div>
	</form:form>


	
</madaja:layout>