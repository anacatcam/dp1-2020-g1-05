<%@ page session="false" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="madaja" tagdir="/WEB-INF/tags" %>

<madaja:layout pageName="vehiculos">
	<c:if test="${esAlquiler}">
		<h2 style="text-align:center;">El alquiler no se pudo realizar</h2>
		<br>
		<spring:url value="/resources/images/noDisponible.jpg" var="noDisponible"/>
	    <img style="display:block; margin: auto;" src="${noDisponible}"/>
	    <br>
		<p style="text-align:center;">No ha sido posible llevar a cabo el alquiler debido a que este vehículo ya se encuentra alquilado.</p>
		<p style="text-align:center;">Este vehículo no estará disponible hasta el <b><c:out value="${fecha}"/></b>.</p>
		<br>
		<p style="text-align:center;">Puedes consultar otros vehículos disponibles en el catálogo.</p>
		<br>
		<div style="display:block; margin: auto; text-align:center;">
			<spring:url value="/vehiculos" var="catalogoUrl">
		    </spring:url>
		    <a href="${fn:escapeXml(catalogoUrl)}" class="btn btn-default">Volver al catálogo</a>
		</div>
	</c:if>
	<c:if test="${esAlquilerVenta}">
		<h2 style="text-align:center;">La compra no se pudo realizar</h2>
		<br>
		<spring:url value="/resources/images/noDisponible.jpg" var="noDisponible"/>
	    <img style="display:block; margin: auto;" src="${noDisponible}"/>
	    <br>
		<p style="text-align:center;">No ha sido posible llevar a cabo la compra debido a que este vehículo se encuentra alquilado.</p>
		<p style="text-align:center;">Este vehículo no estará disponible hasta el <b><c:out value="${fecha}"/></b>.</p>
		<br>
		<p style="text-align:center;">Puedes consultar otros vehículos disponibles en el catálogo.</p>
		<br>
		<div style="display:block; margin: auto; text-align:center;">
			<spring:url value="/vehiculos" var="catalogoUrl">
		    </spring:url>
		    <a href="${fn:escapeXml(catalogoUrl)}" class="btn btn-default">Volver al catálogo</a>
		</div>
	</c:if>
	<c:if test="${esVenta}">
		<h2 style="text-align:center;">La compra no se pudo realizar</h2>
		<br>
		<spring:url value="/resources/images/noDisponible.jpg" var="noDisponible"/>
	    <img style="display:block; margin: auto;" src="${noDisponible}"/>
	    <br>
		<p style="text-align:center;">No ha sido posible llevar a cabo la compra debido a que este vehículo ya ha sido vendido.</p>
		<br>
		<p style="text-align:center;">Puedes consultar otros vehículos disponibles en el catálogo.</p>
		<br>
		<div style="display:block; margin: auto; text-align:center;">
			<spring:url value="/vehiculos" var="catalogoUrl">
		    </spring:url>
		    <a href="${fn:escapeXml(catalogoUrl)}" class="btn btn-default">Volver al catálogo</a>
		</div>
	</c:if>
	<c:if test="${esVentaAlquiler}">
		<h2 style="text-align:center;">El alquiler no se pudo realizar</h2>
		<br>
		<spring:url value="/resources/images/noDisponible.jpg" var="noDisponible"/>
	    <img style="display:block; margin: auto;" src="${noDisponible}"/>
	    <br>
		<p style="text-align:center;">No ha sido posible llevar a cabo el alquiler debido a que este vehículo ya ha sido vendido.</p>
		<br>
		<p style="text-align:center;">Puedes consultar otros vehículos disponibles en el catálogo.</p>
		<br>
		<div style="display:block; margin: auto; text-align:center;">
			<spring:url value="/vehiculos" var="catalogoUrl">
		    </spring:url>
		    <a href="${fn:escapeXml(catalogoUrl)}" class="btn btn-default">Volver al catálogo</a>
		</div>
	</c:if>
	<c:if test="${enRevision}">
		<c:if test="${esRevisionAlquiler}">
			<h2 style="text-align:center;">El alquiler no se pudo realizar</h2>
		</c:if>
		<c:if test="${esRevisionVenta}">
			<h2 style="text-align:center;">La compra no se pudo realizar</h2>
		</c:if>
		<br>
		<spring:url value="/resources/images/mecanico.jpg" var="noDisponible"/>
	    <img style="display:block; margin: auto; width: 40%; height: 40%;" src="${noDisponible}"/>
	    <br>
	    <c:if test="${esRevisionAlquiler}">
			<p style="text-align:center;">No ha sido posible llevar a cabo el alquiler debido a que nuestros mecánicos están revisándolo en este momento. ¡Sentimos las molestias!</p>
		</c:if>
		<c:if test="${esRevisionVenta}">
			<p style="text-align:center;">No ha sido posible llevar a cabo la compra debido a que nuestros mecánicos están revisándolo en este momento. ¡Sentimos las molestias!</p>
		</c:if>
		<br>
		<p style="text-align:center;">Puedes consultar otros vehículos disponibles en el catálogo.</p>
		<br>
		<div style="display:block; margin: auto; text-align:center;">
			<spring:url value="/vehiculos" var="catalogoUrl">
		    </spring:url>
		    <a href="${fn:escapeXml(catalogoUrl)}" class="btn btn-default">Volver al catálogo</a>
		</div>
	</c:if>
	<c:if test="${esConflictivo}">
		<h2 style="text-align:center;">La operación no se pudo realizar</h2>
		<br>
		<spring:url value="/resources/images/conflictivo.jpg" var="conflictivo"/>
	    <img style="display:block; margin: auto; width: 40%; height: 40%;" src="${conflictivo}"/>
	    <br>
		<p style="text-align:center;">¡Lo sentimos! Parece que has provocado tantas incidencias con vehículos nuestros que has sido catalogado como <b>cliente conflictivo</b>.</p>
		<br>
		<p style="text-align:center;">Tendrás que esperar un tiempo hasta poder volver a alquilar o reservar un vehículo, ¡pero aún puedes comprar uno si lo deseas!</p>
		<br>
		<div style="display:block; margin: auto; text-align:center;">
			<spring:url value="/vehiculos" var="catalogoUrl">
		    </spring:url>
		    <a href="${fn:escapeXml(catalogoUrl)}" class="btn btn-default">Volver al catálogo</a>
		</div>
	</c:if>
	<c:if test="${compraHaceMenosDe30Dias}">
		<h2 style="text-align:center;">La operación no se pudo realizar</h2>
		<br>
		<spring:url value="/resources/images/demasiadosCoches.jpg" var="demasiadoscoches"/>
	    <img style="display:block; margin: auto; width: 40%; height: 40%;" src="${demasiadoscoches}"/>
	    <br>
		<p style="text-align:center;">Has hecho una compra hace menos de 30 días, deberás esperar un poco para la próxima.</p>
		<br>
		<div style="display:block; margin: auto; text-align:center;">
			<spring:url value="/vehiculos" var="catalogoUrl">
		    </spring:url>
		    <a href="${fn:escapeXml(catalogoUrl)}" class="btn btn-default">Volver al catálogo</a>
		</div>
	</c:if>
	<c:if test="${alquilerEnMismoPeriodo}">
		<h2 style="text-align:center;">La operación no se pudo realizar</h2>
		<br>
		<spring:url value="/resources/images/demasiadosCoches.jpg" var="demasiadoscoches"/>
	    <img style="display:block; margin: auto; width: 40%; height: 40%;" src="${demasiadoscoches}"/>
	    <br>
		<p style="text-align:center;">El alquiler que quieres hacer está dentro del periodo de tiempo de otro alquiler que has hecho actualmente.</p>
		<br>
		<p style="text-align:center;">Si quieres hacer otro alquiler, este debe ser posterior al día <b><c:out value="${fechaFin}"></c:out></b></p>
		<br>
		<div style="display:block; margin: auto; text-align:center;">
			<spring:url value="/vehiculos" var="catalogoUrl">
		    </spring:url>
		    <a href="${fn:escapeXml(catalogoUrl)}" class="btn btn-default">Volver al catálogo</a>
		</div>
	</c:if>
</madaja:layout>