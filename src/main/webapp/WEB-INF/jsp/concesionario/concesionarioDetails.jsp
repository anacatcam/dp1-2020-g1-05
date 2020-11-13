<%@ page session="false" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="madaja" tagdir="/WEB-INF/tags" %>

<madaja:layout pageName="concesionarios">
	
	<h2>Informaci�n del concesionario</h2>
	
	<table class="table table-striped">
        <tr>
            <th>Provincia</th>
            <td></td>
            <td></td>
        </tr>
        <tr>
            <th>Localidad</th>
            <td></td>
            <td></td>
        </tr>
        <tr>
            <th>Direcci�n</th>
            <td></td>
            <td></td>
        </tr>
        <tr>
            <th>C�digo Postal</th>
            <td></td>
            <td></td>
        </tr>
        <tr>
            <th>Pa�s</th>
            <td></td>
            <td></td>
        </tr>
        <tr>
            <th>Email</th>
            <td><c:out value="${concesionario.email}"/></td>
            <td></td>
        </tr>
        <tr>
            <th>Tel�fono</th>
            <td><c:out value="${concesionario.telefono}"/></td>
            <td></td>
        </tr>
	</table>
	
</madaja:layout>