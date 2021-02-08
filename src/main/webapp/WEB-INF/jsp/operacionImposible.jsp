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
		<p style="text-align:center;">No ha sido posible llevar a cabo el alquiler debido a que este veh�culo ya se encuentra alquilado.</p>
		<p style="text-align:center;">Este veh�culo no estar� disponible hasta el <b><c:out value="${fecha}"/></b>.</p>
		<br>
		<p style="text-align:center;">Puedes consultar otros veh�culos disponibles en el cat�logo.</p>
		<br>
		<div style="display:block; margin: auto; text-align:center;">
			<spring:url value="/vehiculos" var="catalogoUrl">
		    </spring:url>
		    <a href="${fn:escapeXml(catalogoUrl)}" class="btn btn-default">Volver al cat�logo</a>
		</div>
	</c:if>
	<c:if test="${esAlquilerVenta}">
		<h2 style="text-align:center;">La compra no se pudo realizar</h2>
		<br>
		<spring:url value="/resources/images/noDisponible.jpg" var="noDisponible"/>
	    <img style="display:block; margin: auto;" src="${noDisponible}"/>
	    <br>
		<p style="text-align:center;">No ha sido posible llevar a cabo la compra debido a que este veh�culo se encuentra alquilado.</p>
		<p style="text-align:center;">Este veh�culo no estar� disponible hasta el <b><c:out value="${fecha}"/></b>.</p>
		<br>
		<p style="text-align:center;">Puedes consultar otros veh�culos disponibles en el cat�logo.</p>
		<br>
		<div style="display:block; margin: auto; text-align:center;">
			<spring:url value="/vehiculos" var="catalogoUrl">
		    </spring:url>
		    <a href="${fn:escapeXml(catalogoUrl)}" class="btn btn-default">Volver al cat�logo</a>
		</div>
	</c:if>
	<c:if test="${esVenta}">
		<h2 style="text-align:center;">La compra no se pudo realizar</h2>
		<br>
		<spring:url value="/resources/images/noDisponible.jpg" var="noDisponible"/>
	    <img style="display:block; margin: auto;" src="${noDisponible}"/>
	    <br>
		<p style="text-align:center;">No ha sido posible llevar a cabo la compra debido a que este veh�culo ya ha sido vendido.</p>
		<br>
		<p style="text-align:center;">Puedes consultar otros veh�culos disponibles en el cat�logo.</p>
		<br>
		<div style="display:block; margin: auto; text-align:center;">
			<spring:url value="/vehiculos" var="catalogoUrl">
		    </spring:url>
		    <a href="${fn:escapeXml(catalogoUrl)}" class="btn btn-default">Volver al cat�logo</a>
		</div>
	</c:if>
	<c:if test="${esVentaAlquiler}">
		<h2 style="text-align:center;">El alquiler no se pudo realizar</h2>
		<br>
		<spring:url value="/resources/images/noDisponible.jpg" var="noDisponible"/>
	    <img style="display:block; margin: auto;" src="${noDisponible}"/>
	    <br>
		<p style="text-align:center;">No ha sido posible llevar a cabo el alquiler debido a que este veh�culo ya ha sido vendido.</p>
		<br>
		<p style="text-align:center;">Puedes consultar otros veh�culos disponibles en el cat�logo.</p>
		<br>
		<div style="display:block; margin: auto; text-align:center;">
			<spring:url value="/vehiculos" var="catalogoUrl">
		    </spring:url>
		    <a href="${fn:escapeXml(catalogoUrl)}" class="btn btn-default">Volver al cat�logo</a>
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
			<p style="text-align:center;">No ha sido posible llevar a cabo el alquiler debido a que nuestros mec�nicos est�n revis�ndolo en este momento. �Sentimos las molestias!</p>
		</c:if>
		<c:if test="${esRevisionVenta}">
			<p style="text-align:center;">No ha sido posible llevar a cabo la compra debido a que nuestros mec�nicos est�n revis�ndolo en este momento. �Sentimos las molestias!</p>
		</c:if>
		<br>
		<p style="text-align:center;">Puedes consultar otros veh�culos disponibles en el cat�logo.</p>
		<br>
		<div style="display:block; margin: auto; text-align:center;">
			<spring:url value="/vehiculos" var="catalogoUrl">
		    </spring:url>
		    <a href="${fn:escapeXml(catalogoUrl)}" class="btn btn-default">Volver al cat�logo</a>
		</div>
	</c:if>
	<c:if test="${esConflictivo}">
		<h2 style="text-align:center;">La operaci�n no se pudo realizar</h2>
		<br>
		<spring:url value="/resources/images/conflictivo.jpg" var="conflictivo"/>
	    <img style="display:block; margin: auto; width: 40%; height: 40%;" src="${conflictivo}"/>
	    <br>
		<p style="text-align:center;">�Lo sentimos! Parece que has provocado tantas incidencias con veh�culos nuestros que has sido catalogado como <b>cliente conflictivo</b>.</p>
		<br>
		<p style="text-align:center;">Tendr�s que esperar un tiempo hasta poder volver a alquilar o reservar un veh�culo, �pero a�n puedes comprar uno si lo deseas!</p>
		<br>
		<div style="display:block; margin: auto; text-align:center;">
			<spring:url value="/vehiculos" var="catalogoUrl">
		    </spring:url>
		    <a href="${fn:escapeXml(catalogoUrl)}" class="btn btn-default">Volver al cat�logo</a>
		</div>
	</c:if>
	<c:if test="${compraHaceMenosDe30Dias}">
		<h2 style="text-align:center;">La operaci�n no se pudo realizar</h2>
		<br>
		<spring:url value="/resources/images/demasiadosCoches.jpg" var="demasiadoscoches"/>
	    <img style="display:block; margin: auto; width: 40%; height: 40%;" src="${demasiadoscoches}"/>
	    <br>
		<p style="text-align:center;">Has hecho una compra hace menos de 30 d�as, deber�s esperar un poco para la pr�xima.</p>
		<br>
		<div style="display:block; margin: auto; text-align:center;">
			<spring:url value="/vehiculos" var="catalogoUrl">
		    </spring:url>
		    <a href="${fn:escapeXml(catalogoUrl)}" class="btn btn-default">Volver al cat�logo</a>
		</div>
	</c:if>
	<c:if test="${alquilerEnMismoPeriodo}">
		<h2 style="text-align:center;">La operaci�n no se pudo realizar</h2>
		<br>
		<spring:url value="/resources/images/demasiadosCoches.jpg" var="demasiadoscoches"/>
	    <img style="display:block; margin: auto; width: 40%; height: 40%;" src="${demasiadoscoches}"/>
	    <br>
		<p style="text-align:center;">El alquiler que quieres hacer est� dentro del periodo de tiempo de otro alquiler que has hecho actualmente.</p>
		<br>
		<p style="text-align:center;">Si quieres hacer otro alquiler, este debe ser posterior al d�a <b><c:out value="${fechaFin}"></c:out></b></p>
		<br>
		<div style="display:block; margin: auto; text-align:center;">
			<spring:url value="/vehiculos" var="catalogoUrl">
		    </spring:url>
		    <a href="${fn:escapeXml(catalogoUrl)}" class="btn btn-default">Volver al cat�logo</a>
		</div>
	</c:if>
</madaja:layout>