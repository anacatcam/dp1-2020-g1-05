<%@ page session="false" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="madaja" tagdir="/WEB-INF/tags" %>

<madaja:layout pageName="editarEnvios">
	<h2>Editar Envio: <c:out value="${object.envio.id}"></c:out>
	</h2>

	<table class="table table-striped">
		<tr>
            <th>Vehículo</th>
            <td><c:out value="${object.vehiculo.marca} ${object.vehiculo.modelo }"/></td>
            <td></td>
        </tr>	
        <tr>
            <th>Matrícula</th>
            <td><c:out value="${object.vehiculo.matricula}"/></td>
            <td></td>
        </tr>	
        <tr>
            <th>Hora</th>
            <td><c:out value="${envio.hora}"/></td>
            <td></td>
        </tr>	
        <tr>
            <th>Cliente</th>
            <td><c:out value="${object.cliente.firstName} ${object.cliente.lastName}"/></td>
            <td></td>
        </tr>	
        <tr>
            <th>Dirección</th>
            <td><c:out value="${envio.direccion} ${envio.localidad} ${envio.provincia}"/></td>
            <td></td>
        </tr>
        
	</table>
 	<form:form modelAttribute="envio" class="form-horizontal" id="add-owner-form" >
		<input type="hidden" name="id" value="${envio.id}"/>
		<div class="form-group has-feedback">
 			<input type="hidden" name="Direccion" value="${envio.direccion}"/>
			<input type="hidden" name="Localidad" value="${envio.localidad}"/>
			<input type="hidden" name="Provincia" value="${envio.provincia}"/>
			<input type="hidden" name="codigoPostal" value="${envio.codigoPostal}"/>
		 	<input type="hidden" name="pais" value="${envio.pais}"/>
			<input type="time" style="visibility:hidden;" name="Hora" value="${envio.hora}"/>
			<input type="date" style="visibility:hidden;" name="Fecha" value="${envio.fecha}"/>
			<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
			<div>
				<label  for="estados">Estado:</label>
				<form:select path="estadoEnvio">
        			<form:options itemValue="id" itemLabel="name" items="${estados}" />
				</form:select>
			</div>
			<div class="col-sm-offset-2 col-sm-10">
				<button class="btn btn-default" type="submit">Aceptar</button>
			</div>
		</div>
	</form:form>
</madaja:layout>