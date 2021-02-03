<%@ page session="false" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="madaja" tagdir="/WEB-INF/tags" %>

<madaja:layout pageName="vehiculos">
	<jsp:attribute name="customScript">     
		<script>
            $(function () {
                $("#FechaDevolucion").datepicker({dateFormat: 'yy-mm-dd'});
            });
    	</script>   
   	</jsp:attribute>
   	<jsp:body>

    <h2>Devolución del vehículo</h2>
    
    <form method="post" action="" class="form-horizontal" id="add-owner-form">
        <div class="form-group has-feedback">
 			<input type="hidden" name="AlquilerId" value="${alquiler_id}"/>
            <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
            <div class="form-group">
            	<label class="col-sm-2 control-label">Disponibilidad </label>
            	<div class="col-sm-10">
	            	<select name="disponible">
	            		<c:forEach items="${disponibles}" var="disponible">
		            		<option value="${disponible.id}"><c:out value="${disponible.name}"/></option>
		            	</c:forEach>
	    	        </select>
    	        </div>
            </div>
            <label>Fecha de devolución </label>
			<input id="FechaDevolucion" name="FechaDevolucion"/>
		</div>
		<button class="btn btn-default" type="submit">Actualizar vehículo</button>
    </form>
    </jsp:body>
</madaja:layout>