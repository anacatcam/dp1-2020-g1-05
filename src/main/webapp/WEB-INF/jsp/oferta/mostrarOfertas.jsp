<%@ page session="false" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="madaja" tagdir="/WEB-INF/tags" %>

<madaja:layout pageName="oferta">
	<h2>Ofertas</h2>

	<table id="ownersTable" class="table table-striped">
		<thead>
		<tr>
			<th style="width: 150px;">Descripción</th>
			<th style="width: 150px;">Descuento</th>
			<th style="width: 150px;"> </th>
		</tr>
		</thead>
		<tbody>
			<c:forEach items="${ofertas}" var="oferta">
				<tr>
					<td>
						<c:out value="${oferta.name}"/>
					</td>
					<td>
						<c:out value="${oferta.descuento}"/>
					</td>
					<td>
						<spring:url value="/oferta/{ofertaId}" var="ofertaUrl">
							<spring:param name="ofertaId" value="${oferta.id}"/>
						</spring:url>
						<a href="${fn:escapeXml(ofertaUrl)}">Detalles</a>
					</td>
				</tr>
			</c:forEach>
		</tbody>
	</table>
	<br/>
	<a class="btn btn-default" href='<spring:url value="/oferta/new" htmlEscape="true"/>'>Añadir oferta</a>
</madaja:layout>