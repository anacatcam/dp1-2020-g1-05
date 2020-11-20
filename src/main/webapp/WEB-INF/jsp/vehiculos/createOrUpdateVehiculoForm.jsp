<%@ page session="false" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="madaja" tagdir="/WEB-INF/tags" %>

<madaja:layout pageName="vehiculos">
    <h2>
        <c:if test="${vehiculos['new']}">Nuevo</c:if> Vehículo
    </h2>
    <form:form modelAttribute="vehiculos" class="form-horizontal" id="add-owner-form">
        <div class="form-group has-feedback">
            <madaja:inputField label="Matrícula" name="matricula"/>
            <madaja:inputField label="Precio de alquiler" name="precioAlquiler"/>
            <madaja:inputField label="Precio de venta" name="precioVenta"/>
            <madaja:inputField label="Marca" name="marca"/>
            <madaja:inputField label="Modelo" name="modelo"/>
            <madaja:inputField label="Puertas" name="puertas"/>
            <madaja:inputField label="Plazas" name="plazas"/>
            <div class="form-group">
            	<label class="col-sm-2 control-label">Cambio </label>
            	<div class="col-sm-10">
	            	<form:select path="cambio">
		            	<form:options itemValue="id" itemLabel="name" items="${cambios}" />
	    	        </form:select>
    	        </div>
            </div>
            <madaja:inputField label="Capacidad del maletero" name="maletero"/>
            <madaja:inputField label="KM actuales" name="kmActuales"/>
            <madaja:inputField label="Características" name="caracteristicas"/>
            <madaja:inputField label="Estado" name="estado"/>
            <div class="form-group">
            	<label class="col-sm-2 control-label">Disponible </label>
            	<div class="col-sm-10">
	            	<form:select path="disponible">
		            	<form:options itemValue="id" itemLabel="name" items="${disponibles}" />
	    	        </form:select>
    	        </div>
            </div>
            <div class="form-group">
            	<label class="col-sm-2 control-label">Combustible </label>
            	<div class="col-sm-10">
	            	<form:select path="combustible">
		            	<form:options itemValue="id" itemLabel="name" items="${combustibles}" />
	    	        </form:select>
    	        </div>
            </div>
			<div class="form-group">
            	<label class="col-sm-2 control-label">Concesionario </label>
            	<div class="col-sm-10">
	            	<form:select path="concesionario">
		            	<form:options itemValue="id" itemLabel="fullLugar" items="${concesionarios}" />
	    	        </form:select>
    	        </div>
            </div>
        </div>
        <div class="form-group">
            <div class="col-sm-offset-2 col-sm-10">
                <c:choose>
                    <c:when test="${vehiculos['new']}">
                        <button class="btn btn-default" type="submit">Añadir vehículo</button>
                    </c:when>
                    <c:otherwise>
                        <button class="btn btn-default" type="submit">Actualizar vehículo</button>
                    </c:otherwise>
                </c:choose>
            </div>
        </div>
    </form:form>
</madaja:layout>