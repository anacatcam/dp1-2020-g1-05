<%@ page session="false" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="madaja" tagdir="/WEB-INF/tags" %>

<madaja:layout pageName="reservas">
	<h2>Reservas</h2>
	<h6>Buscar reservas de un cliente: </h6>
	
	
	
	<table id="ownersTable" class="table table-striped">
		<thead>
		<tr>
			<th style="width: 150px;">Fianza</th>
			<th style="width: 150px;">Día que entra en gastos</th>
			<th style="width: 150px;">Alquiler/Compra asociado/a</th>
			<th style="width: 150px;">Vehículo</th>
			<th style="width: 150px;">Anular</th>
		</tr>
		</thead>
		
		<tbody>
			<c:forEach items="${reservas}" var="reserva">
				<tr>
					<td>
						<c:out value="${reserva.fianza}"/>
					</td>
					<td>
						<c:out value="${reserva.fechaGastos}"/>
					</td>
					<td>
						-
					</td>
					<td>
						-
					</td>
					<td>
						<a class="btn btn-default" href='<spring:url value="/reservas/delete/{reservaId}" >
															<spring:param name="reservaId" value="${reserva.id}"/>
														</spring:url>'>Anular</a>
					</td>
					
				</tr>
			</c:forEach>
			
			
		</tbody>
		
	</table>


</madaja:layout>