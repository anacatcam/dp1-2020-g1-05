<%@ page session="false" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="madaja" tagdir="/WEB-INF/tags" %>

<madaja:layout pageName="vehiculos">
    <h2>
        <c:if test="${incidencia['new']}">Nueva</c:if> Incidencia
    </h2>
    <form:form modelAttribute="incidencia" class="form-horizontal" id="add-owner-form">
        <div class="form-group has-feedback">
            <madaja:inputField label="Descripcion" name="descripcion"/>
            <div class="form-group">
            	<label class="col-sm-2 control-label">Mec�nico </label>
            	<div class="col-sm-10">
	            	<form:select path="mecanicos">
		            	<form:options itemValue="dni" itemLabel="nombre" items="${mecanicos}" />
	    	        </form:select>
    	        </div>
            </div>
            <%-- <div class="form-group">
            	<label class="col-sm-2 control-label">Cliente responsable </label>
            	<div class="col-sm-10">
	            	<form:select path="clientes">
		            	<form:options itemValue="dni" itemLabel="firstName" items="${clientes}" />
	    	        </form:select>
    	        </div>
            </div> --%>
            <input name="solucionada" type="hidden" value="false">
        </div>
        <div class="form-group">
            <div class="col-sm-offset-2 col-sm-10">
                <c:choose>
                    <c:when test="${incidencia['new']}">
                        <button class="btn btn-default" type="submit">A�adir incidencia</button>
                    </c:when>
                    <c:otherwise>
                        <button class="btn btn-default" type="submit">Actualizar incidencia</button>
                    </c:otherwise>
                </c:choose>
            </div>
        </div>
    </form:form>
</madaja:layout>