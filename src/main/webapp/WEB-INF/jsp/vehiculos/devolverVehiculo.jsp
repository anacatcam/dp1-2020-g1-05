<%@ page session="false" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="madaja" tagdir="/WEB-INF/tags" %>

<madaja:layout pageName="vehiculos">

    <h2>Vehículo devuelto</h2>
    
    <form:form modelAttribute="vehiculos" class="form-horizontal" id="add-owner-form">
        <div class="form-group has-feedback">
 			<input type="hidden" name="Matrícula" value="${vehiculos.matricula}"/>
 			<input type="hidden" name="Precio de alquiler" value="${vehiculos.precioAlquiler}"/>
 			<input type="hidden" name="Precio de venta" value="${vehiculos.precioVenta}"/>
 			<input type="hidden" name="Marca" value="${vehiculos.marca}"/>
 			<input type="hidden" name="Modelo" value="${vehiculos.modelo}"/>
 			<input type="hidden" name="Puertas" value="${vehiculos.puertas}"/>
 			<input type="hidden" name="Plazas" value="${vehiculos.plazas}"/>
 			<input type="hidden" name="Cambio" value="${vehiculos.cambio}"/>
 			<input type="hidden" name="Capacidad del maletero" value="${vehiculos.maletero}"/>
 			<input type="hidden" name="KM actuales" value="${vehiculos.kmActuales}"/>
            <input type="hidden" name="Características" value="${vehiculos.caracteristicas}"/>
            <input type="hidden" name="Estado" value="${vehiculos.estado}"/>
            <input type="hidden" name="Combustible" value="${vehiculos.combustible}"/>
            <input type="hidden" name="Concesionario" value="${vehiculos.concesionario}"/>
            <input type="hidden" name="Seguro del vehículo" value="${vehiculos.seguroVehiculo}"/>
            <input type="hidden" name="Oferta" value="${vehiculos.oferta}"/>
            <div class="form-group">
            	<label class="col-sm-2 control-label">Disponibilidad </label>
            	<div class="col-sm-10">
	            	<form:select path="disponible">
		            	<form:options itemValue="id" itemLabel="name" items="${disponibles}" />
	    	        </form:select>
    	        </div>
            </div>
            <label>Fecha de devolución </label>
			<input type="date" name="Fecha de devolución"/>
		</div>
		<button class="btn btn-default" type="submit">Actualizar vehículo</button>
    </form:form>
    
</madaja:layout>