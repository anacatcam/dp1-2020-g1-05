<%@ page session="false" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="madaja" tagdir="/WEB-INF/tags" %>


<madaja:layout pageName="ofertas">
<body>
	<h2>
		Nueva Oferta
	</h2>
	<form class="form-horizontal" id="add-owner-form" >
		<div class="form-group has-feedback">
			<input id="id" type="hidden" value="${id}"/>	
			<label for="name" class="col-sm-2 control-label" >Título:</label>
			<span id="errorName" class="col-sm-10"></span>
			<div class="col-sm-10">
			<input type="text"  id="name" name="name" class="form-control"><br></br>
			</div>
			<label for="descuento" class="col-sm-2 control-label" >Descuento:</label>
			<span id="errorDescuento" class="col-sm-10"></span>
			<div class="col-sm-10">
			<input type="number" id="descuento" name="descuento" class="form-control" min="0" max="100"><br></br>
			</div>
			<label for="fechaLimite" class="col-sm-2 control-label">Fecha Límite:</label>
			<span id="errorFecha" class="col-sm-10"></span>
			<div class="col-sm-10">
			<input type="date" id="fechaLimite" name="fechaLimite" class="form-control"><br></br>
			</div>
			<label for="horaLimite" class="col-sm-2 control-label">Hora Límite:</label>
			<span id="errorHora" class="col-sm-10"></span>
			<div class="col-sm-10">
			<input type="time" step="1" id="horaLimite" name="horaLimite" class="form-control"><br></br>
			</div>
			<label class="col-sm-2 control-label">Vehiculos disponibles:</label>
			<select id = "vehiculos" multiple = "true">
			
			</select>
			<div class="col-sm-offset-2 col-sm-10">
				<button id="anadir" class="btn btn-default" type="submit" >Añadir Oferta</button>
			</div>
		</div>
	</form>
	<script src="https://code.jquery.com/jquery-3.5.1.min.js" ></script>
    <script src='/resources/js/updateOfertaAPI.js'></script>
</body>
</madaja:layout>