<%@ page session="false" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="madaja" tagdir="/WEB-INF/tags" %>

<madaja:layout pageName="ofertaAPI">
<body>
	<h2>Ofertas</h2>

	<table id="ownersTable" class="table table-striped">
		<thead>
		<tr>
			<th style="width: 150px;">Descripción</th>
			<th style="width: 150px;">Descuento (%)</th>
			<th style="width: 150px;"> </th>
		</tr>
		</thead>
	</table>
	<br/>
	<a class="btn btn-default" href='<spring:url value="/ofertaAPI/new" htmlEscape="true"/>'>Añadir oferta</a>
	
	 <script src="https://code.jquery.com/jquery-3.5.1.min.js" crossorigin="anonymous"></script>
     <script src="https://cdn.datatables.net/1.10.22/js/jquery.dataTables.min.js"></script>
     <script src="https://cdn.datatables.net/buttons/1.6.4/js/dataTables.buttons.min.js"></script>
     <script src="https://cdn.datatables.net/1.10.22/js/dataTables.bootstrap4.min.js"></script>
     <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.12.9/umd/popper.min.js" integrity="sha384-ApNbgh9B+Y1QKtv3Rn7W3mgPxhU9K/ScQsAP7hUibX39j7fakFPskvXusvfa0b4Q" crossorigin="anonymous"></script>
     <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js" integrity="sha384-JZR6Spejh4U02d8jOt6vLEHfe/JQGiRRSQQxSfFWpi1MquVdAyjUar5+76PVCmYl" crossorigin="anonymous"></script>
     <script src='/resources/js/mostrarOfertas.js'></script>
   </body>
</madaja:layout>