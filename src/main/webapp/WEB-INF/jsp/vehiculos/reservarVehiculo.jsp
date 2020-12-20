<%@ page session="false" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="madaja" tagdir="/WEB-INF/tags" %>

<madaja:layout pageName="reservarVehiculos">
    <h2>
        Reservar Vehículo
    </h2>
    
    <h3>Información del vehículo</h3>      
	<table class="table table-striped">
    		<tr>
                <td valign="top">
                    <dl class="dl-horizontal">
                    	<dt>Vehículo</dt>
                    	<dd><c:out value="${vehiculos.marca} "/><c:out value="${vehiculos.modelo}"/></dd>
                    	<dt>Matrícula</dt>
                    	<dd><c:out value="${vehiculos.matricula}"/></dd>
                    </dl>
                </td>
    		</tr>		
    </table>
    
    <h3>Formulario de reserva</h3>      
	<form:form modelAttribute="vehiculos" class="form-horizontal" id="add-owner-form">
    	<div class="form-group has-feedback">
    		<div class="form-group">
            	<label class="col-sm-2 control-label">Tipo de reserva</label>
            	<div class="col-sm-10">
	            	<form:select path="disponible">
	            		<option value="0">Seleccione un tipo...</option>
	            		<option value="1">${vehiculos.disponible}</option>
	    	        </form:select>
    	        </div>
            </div>
            <div class="form-group">
            	<label class="col-sm-2 control-label">¿Envío o recogida en tienda?</label>
            	<div class="col-sm-10">
	            	<form:select path="disponible">
	            		<option value="0">Seleccione un tipo...</option>
	            		<option value="1">Envío</option>
	            		<option value="2">Recogida en tienda</option>
	    	        </form:select>
    	        </div>
            </div>
            <label class="col-sm-2 control-label">Fecha</label>
    		<input id="date" type="date">
       	</div>
											
		<div class="form-group">
            <div class="col-sm-offset-2 col-sm-10">
				<button class="btn btn-default" type="submit">Reservar vehículo</button>
            </div>
        </div>
		
    </form:form>	
    

    	
    
    
    

    
</madaja:layout>