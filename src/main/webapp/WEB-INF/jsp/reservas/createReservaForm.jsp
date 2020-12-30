<%@ page session="false" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="madaja" tagdir="/WEB-INF/tags" %>

<madaja:layout pageName="reservarVehiculos">

	<jsp:body>
		<h2>Reservar Veh�culo</h2>
		
		<form:form modelAttribute="reserva" class="form-horizontal" id="add-owner-form" action="/reservas/new">
			 <div class="form-group has-feedback">
			 	<input type="number" id="fianza" name="fianza"/>
			</div>
			<div class="form-group">
				<button class="btn btn-default" type="submit">Reservar veh�culo</button>
			</div>
		</form:form>
	    
	    <h3>Informaci�n del veh�culo</h3>      
	    
		<table class="table table-striped">
	    		<tr>
	                <td valign="top">
	                    <dl class="dl-horizontal">
	                    	<dt>Veh�culo</dt>
	                    	<dd><c:out value="${vehiculos.marca} "/><c:out value="${vehiculos.modelo}"/></dd>
	                    	<dt>Matr�cula</dt>
	                    	<dd><c:out value="${vehiculos.matricula}"/></dd>
	                    </dl>
	                </td>
	    		</tr>		
	    </table>
	    
	    <h3>Informaci�n de la reserva</h3>
	    
	    <table class="table table-striped">
	    		<tr>
	                <td valign="top">
	                    <dl class="dl-horizontal">
	                    	<dt>Fianza</dt>
	                    	<dd>Valor por definir</dd>
	                    	<dt>D�a que entra en gastos</dt>
	                    	<dd>Fecha por definir</dd>
	                    </dl>
	                </td>
	    		</tr>		
	    </table>
	    
	    
	</jsp:body>

    
    

    	
    
    
    

    
</madaja:layout>