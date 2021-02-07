<%@ page session="false" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="madaja" tagdir="/WEB-INF/tags" %>

<madaja:layout pageName="reservas">
	
	<h2>Reservas</h2>	
	
	<!-- v -->
	<c:out value="${msg}"/>
	<!-- ^ -->
				
	<table id="ownersTable" class="table table-striped">
		<thead>
		<tr>
			<th style="width: 150px;">Vehículo</th>
			<th style="width: 150px;">Fianza</th>
			<th style="width: 150px;">Día que entra en gastos</th>
			<th style="width: 150px;">Anular</th>
		</tr>
		</thead>
		
		<tbody>
			
			<c:forEach items="${ventas}" var="venta">
				<tr>
					<td>
						<c:out value="${venta.vehiculo.marca} "/>
						<c:out value="${venta.vehiculo.modelo} "/>
						<br/>
						<spring:url value="/vehiculos/{vehiculoId}" var="vehiculoUrl">
							<spring:param name="vehiculoId" value="${venta.vehiculo.id}"/>
						</spring:url>
						<a href="${fn:escapeXml(vehiculoUrl)}"><c:out value="(más info)"/></a>
					</td>
					<td>
						<c:out value="${venta.reserva.fianza}"/>
					</td>
					<td>
						<c:out value="${venta.reserva.fechaGastos}"/>
					</td>					
					<td>
						<a class="btn btn-default" href='<spring:url value="/reservas/{reservaId}/delete" >
						<spring:param name="reservaId" value="${venta.reserva.id}"/>
						</spring:url>'>Anular</a>
					</td>		
				</tr>
			</c:forEach>
		
			<c:forEach items="${alquileres}" var="alquiler">
				<tr>
					<td>
						<c:out value="${alquiler.vehiculo.marca} "/>
						<c:out value="${alquiler.vehiculo.modelo} "/>
						<br/>
						<spring:url value="/vehiculos/{vehiculoId}" var="vehiculoUrl">
							<spring:param name="vehiculoId" value="${alquiler.vehiculo.id}"/>
						</spring:url>
						<a href="${fn:escapeXml(vehiculoUrl)}"><c:out value="(más info)"/></a>
					</td>
					<td>
						<c:out value="${alquiler.reserva.fianza}"/>
					</td>
					<td>
						<c:out value="${alquiler.reserva.fechaGastos}"/>
					</td>					
					<td>
						<a class="btn btn-default" href='<spring:url value="/reservas/{reservaId}/delete" >
						<spring:param name="reservaId" value="${alquiler.reserva.id}"/>
						</spring:url>'>Anular</a>
					</td>		
				</tr>
			</c:forEach>
		

			
		</tbody>
		
	</table>
	
	<div>
            <nav aria-label="Pagination">
                <ul class="pagination">

                    <c:choose>
                        <c:when test="${prev == 0}">
                            <li class="page-item disabled">
                                <a class="page-link" href="#">&laquo;</a>
                            </li>
                        </c:when>
                        <c:otherwise>
                            <li class="page-item">
                                <a class="page-link" href="?page=${prev}">&laquo;</a>
                            </li>
                        </c:otherwise>
                    </c:choose>


                    <c:forEach items="${pages}" var="page">

                    <c:choose>
                        <c:when test="${current == page}">
                            <li class="page-item active">
                                <a class="page-link" href="?page=${page}">${page}</a>
                            </li>
                        </c:when>
                        <c:otherwise>
                            <li class="page-item">
                                <a class="page-link" href="?page=${page}">${page}</a>
                            </li>
                        </c:otherwise>
                    </c:choose>

                    </c:forEach>
					<c:choose>
                        <c:when test="${current == max}">
                            <li class="page-item disabled">
                                <a class="page-link" href="#">&raquo</a>
                            </li>
                        </c:when>
                        <c:otherwise>
                            <li class="page-item">
                                <a class="page-link" href="?page=${next}">&raquo</a>
                            </li>
                        </c:otherwise>
                    </c:choose>

                  </ul>
            </nav>
    </div>


</madaja:layout>