<%@ page session="false" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="petclinic" tagdir="/WEB-INF/tags" %>

<petclinic:layout pageName="vehiculos">
    <h2>
        <c:if test="${vehiculos['new']}">Nuevo</c:if> Vehículo
    </h2>
    <form:form modelAttribute="owner" class="form-horizontal" id="add-owner-form">
        <div class="form-group has-feedback">
            <petclinic:inputField label="Matrícula" name="matricula"/>
            <petclinic:inputField label="Precio de alquiler" name="precioAlquiler"/>
            <petclinic:inputField label="Precio de venta" name="precioVenta"/>
            <petclinic:inputField label="Marca" name="marca"/>
            <petclinic:inputField label="Modelo" name="modelo"/>
            <petclinic:inputField label="Plazas" name="plazas"/>
            <petclinic:inputField label="Cambio" name="cambio"/>
            <petclinic:inputField label="Maletero" name="maletero"/>
            <petclinic:inputField label="Características" name="caracteristicas"/>
            <petclinic:inputField label="Username" name="user.username"/>
            <petclinic:inputField label="Password" name="user.password"/>
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
</petclinic:layout>