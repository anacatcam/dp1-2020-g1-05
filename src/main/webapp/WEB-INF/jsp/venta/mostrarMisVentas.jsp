<%@ page session="false" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="madaja" tagdir="/WEB-INF/tags" %>

<madaja:layout pageName="misCompras">

<h2>Mis compras</h2>

<table id="comprasTable" class="table table-striped">
		<thead>
		<tr>
			<th style="width: 150px;">Número de identificación</th>
			<th style="width: 150px;">Vehículo</th>
			<th style="width: 150px;">Matrícula</th>
			<th style="width: 150px;">Precio</th>
		</tr>
		</thead>
		<tbody>
			<c:forEach items="${ventas}" var="venta">
				<tr>
					<td>
						<c:out value="${venta.id}"/>
					</td>
					<td>
						<c:out value="${venta.vehiculo.marca} ${venta.vehiculo.modelo}"/>	
					</td>
					<td>
						<spring:url value="/vehiculos/{vehiculoId}" var="vehiculoUrl">
	                            <spring:param name="vehiculoId" value="${venta.vehiculo.id}"/>
                            </spring:url>
                        	<a href="${fn:escapeXml(vehiculoUrl)}"><c:out value="${venta.vehiculo.matricula}"/></a>
					</td>
					<td>
						<c:out value="${venta.vehiculo.precioVenta}"/>
					</td>
					
				</tr>
			</c:forEach>
		</tbody>
	</table>
<<<<<<< HEAD
	
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
	
=======
	<a class="btn btn-default" href='<spring:url value="/vehiculos/disponible/2" htmlEscape="true"/>'>Comprar nuevo vehículo</a>
>>>>>>> branch 'master' of https://github.com/gii-is-DP1/dp1-2020-g1-05.git
</madaja:layout>