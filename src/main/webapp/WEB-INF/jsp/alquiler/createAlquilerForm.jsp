<%@ page session="false" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="madaja" tagdir="/WEB-INF/tags" %>

<madaja:layout pageName="createAlquiler">
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
        <div class="form-group has-feedback">
            <label class="col-sm-2 control-label">¿Dónde desea recoger el vehículo? </label>
            	<div class="col-sm-10">
					<select id="concesionariosE" name="concesionariosE">
						<c:forEach items="${concesionarios}" var="concesionario">
							<option value="${concesionario.id}">
								<c:out value="${concesionario.nombre} - "/>  
								<c:out value="${concesionario.direccion}, "/>
								<c:out value="${concesionario.localidad} "/> 
								<c:out value="(${concesionario.provincia}), "/>
								<c:out value="${concesionario.codigoPostal}"/> 
							</option>
						</c:forEach>
					</select>		
    	        </div>
      	</div>
      	<div class="form-group has-feedback">
            <label class="col-sm-2 control-label">¿Dónde desea entregar el vehículo? </label>
            	<div class="col-sm-10">
					<select id="concesionariosR" name="concesionariosR">
						<c:forEach items="${concesionarios}" var="concesionario">
							<option value="${concesionario.id}">
								<c:out value="${concesionario.nombre} - "/>  
								<c:out value="${concesionario.direccion}, "/>
								<c:out value="${concesionario.localidad} "/> 
								<c:out value="(${concesionario.provincia}), "/>
								<c:out value="${concesionario.codigoPostal}"/> 
							</option>
						</c:forEach>
					</select>		
    	        </div>
      	</div>
            
        <div class="form-group">
            <div class="col-sm-offset-2 col-sm-10">
				<button class="btn btn-default" type="submit">Alquilar</button>
            </div>
        </div>
    </form:form>
</madaja:layout>