<%@ page session="false" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="madaja" tagdir="/WEB-INF/tags" %>

<madaja:layout pageName="misAlquileres">
    <h2>Nuevo alquiler</h2>
    <form:form modelAttribute="alquiler" class="form-horizontal" id="add-owner-form">
        <div class="form-group has-feedback">
        	<input type="hidden" name="id" value="${alquiler.id}"/>
        	<input type="hidden" name="depLleno" value="${alquiler.depLleno}"/>
            <madaja:inputField label="Fecha de inicio" name="fechaInicio"/>
            <madaja:inputField label="Fecha de finalizacion" name="fechaFin"/>
            <madaja:inputField label="Limite de kilometros" name="limiteKM"/>
        	<input type="hidden" name="vehiculo" value="${alquiler.vehiculo.id}"/>
        	<input type="hidden" name="cliente" value="${alquiler.cliente.id}"/>
        	<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
        </div>
        <div class="form-group">
            <div class="col-sm-offset-2 col-sm-10">
				<button class="btn btn-default" type="submit">Alquilar</button>
            </div>
        </div>
    </form:form>
</madaja:layout>