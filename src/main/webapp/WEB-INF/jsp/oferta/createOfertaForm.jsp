<%@ page session="false" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="madaja" tagdir="/WEB-INF/tags" %>
<%-- Jquery--%>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/2.1.1/jquery.min.js"></script>
<%-- Bootstrap --%>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.2.0/js/bootstrap.min.js"></script>
<%-- DatePicker --%>
<script src="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-datepicker/1.3.0/js/bootstrap-datepicker.js"></script>
<%-- Timepicker --%>
<script src="//cdnjs.cloudflare.com/ajax/libs/timepicker/1.3.5/jquery.timepicker.min.js"></script>

<head>
<link rel="stylesheet" href="//cdnjs.cloudflare.com/ajax/libs/timepicker/1.3.5/jquery.timepicker.min.css">

</head>

<madaja:layout pageName="ofertas">
	<jsp:attribute name="customScript">        
	<script>
            $(function () {
                $("#fechaLimite").datepicker({format: 'yyyy-mm-dd'});
            });
            
            $(document).ready(function(){
                $('#horaLimite').timepicker({timeFormat: 'hh:mm:ss',});
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
			<madaja:inputField label="Descripción"  name="horaLimite"/>
			<label  for="matriculas">Vehículos para aplicar la oferta:</label>
            <select   name="matriculas" multiple id ="matriculas">
				<c:forEach var="matricula" items="${matriculas}">
					<option value="${matricula}">${matricula}</option>
				</c:forEach>
				<option value="none" selected>Ninguno</option>
			</select>
			<div class="col-sm-offset-2 col-sm-10">
			<button class="btn btn-default" type="submit">Añadir Oferta</button>
			</div>
		</div>
	</form:form>
	</jsp:body>
</madaja:layout>