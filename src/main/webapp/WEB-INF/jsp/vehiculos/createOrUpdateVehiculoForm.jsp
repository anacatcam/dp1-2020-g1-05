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
            <madaja:inputField label="Plazas" name="plazas"/>
            <madaja:inputField label="Cambio" name="cambio"/>
            <madaja:inputField label="Maletero" name="maletero"/>
            <madaja:inputField label="Características" name="caracteristicas"/>
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