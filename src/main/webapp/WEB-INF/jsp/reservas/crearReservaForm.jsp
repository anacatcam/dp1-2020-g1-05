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
                $("#fechaGastos").datepicker({dateFormat: 'yy-mm-dd'});
            });
    </script>   
   	</jsp:attribute>
	<jsp:body>
    <h2>Confirmar reserva</h2>
    
    <span><b>Fianza:</b> <c:out value="${reserva.fianza}"></c:out></span><br/>
    
    <form:form modelAttribute="reserva" class="form-horizontal" id="add-owner-form">
        <div class="form-group has-feedback">
        	<input type="hidden" name="id" value="${reserva.id}"/>
        	<input type="hidden" name="fianza" value="${reserva.fianza}"/>
        	<madaja:inputField label="Fecha en la que entra en gastos" name="fechaGastos"/>
        	<input type="hidden" name="cliente" value="${reserva.cliente.id}"/>
        	<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
        </div>
        <div class="form-group">
            <div class="col-sm-offset-2 col-sm-10">
				<button class="btn btn-default" type="submit">Reservar</button>
            </div>
        </div>
    </form:form>
    
    <br/>
    <br/>
    <h3>Información de usuario</h3>
    <table class="table table-striped">
   		<tr>
	        <td valign="top">
	            <dl class="dl-horizontal">
	            	<dd>
	                <dt>DNI</dt>
	                <dd><c:out value="${cliente.dni}"/></dd>
	                <dt>Nombre</dt>
	                <dd><c:out value="${cliente.firstName}"/></dd>
	                <dt>Apellidos</dt>
	                <dd><c:out value="${cliente.lastName}"/></dd>
	                <dt>Teléfono</dt>
	                <dd><c:out value="${cliente.telefono}"/></dd>
	                <dt>Email</dt>
	                <dd><c:out value="${cliente.email}"/></dd>
	            </dl>
	        </td>
		</tr>
	</table>
	</jsp:body>
</madaja:layout>