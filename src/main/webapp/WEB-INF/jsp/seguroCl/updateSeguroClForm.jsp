<%@ page session="false" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="madaja" tagdir="/WEB-INF/tags" %>

<madaja:layout pageName="vehiculos">
    <h2>
        <c:if test="${seguroCliente['new']}">Nuevo</c:if> SeguroCliente
    </h2>
    <form:form modelAttribute="seguroCliente" class="form-horizontal" id="add-owner-form">
        <div class="form-group has-feedback">
            <madaja:inputField label="Cobertura" name="cobertura"/>
            <madaja:inputField label="Franquicia" name="franquicia"/>
            <madaja:inputField label="Fecha inicio" name="fechaInicio"/>
            <madaja:inputField label="Fecha fin" name="fechaFin"/>
            <madaja:inputField label="Precio final" name="precio"/>
        </div>
        <div class="form-group">
            <div class="col-sm-offset-2 col-sm-10">
                <c:choose>
                    <c:when test="${seguroCliente['new']}">
                        <button class="btn btn-default" type="submit">Añadir seguro cliente</button>
                    </c:when>
                    <c:otherwise>
                        <button class="btn btn-default" type="submit">Actualizar seguro cliente</button>
                    </c:otherwise>
                </c:choose>
            </div>
        </div>
    </form:form>
</madaja:layout>