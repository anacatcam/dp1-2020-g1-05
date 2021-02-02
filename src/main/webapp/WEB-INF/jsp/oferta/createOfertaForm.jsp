<%@ page session="false" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="madaja" tagdir="/WEB-INF/tags" %>

<madaja:layout pageName="ofertas">
	<jsp:attribute name="customScript">     
		<script>
            $(function () {
                $("#fechaLimite").datepicker({dateFormat: 'yy-mm-dd'});
            });
            
            $(document).ready(function(){
                $("#horaLimite").timepicker({timeFormat: 'hh:mm:ss'});
            });
    </script>   
   	</jsp:attribute>
   	<jsp:body>
	<h2>
		Nueva Oferta
	</h2>
	<form:form modelAttribute="oferta" class="form-horizontal" id="add-owner-form">
		<input type="hidden" name="id" value="${oferta.id}"/>
		<div class="form-group has-feedback">
			<madaja:inputField label="Título" name="name" />
			<madaja:inputField label="Descuento" name="descuento"/>
			<madaja:inputField label="Fecha Límite" name="fechaLimite"/>
			<madaja:inputField label="Hora Límite"  name="horaLimite"/>
			<label>Vehiculos disponibles:</label>
			<form:select multiple="true" path="vehiculos">
            	<c:forEach var = "vehiculo" items = "${vehiculosDisponibles}">
					<form:option value="${vehiculo.id}"><c:out value="${vehiculo.matricula}"></c:out></form:option>            	
            	</c:forEach>
            </form:select>
            <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
			<div class="col-sm-offset-2 col-sm-10">
			<button class="btn btn-default"  type="submit">Añadir Oferta</button>
			</div>
		</div>
	</form:form>
	
	</jsp:body>
</madaja:layout>