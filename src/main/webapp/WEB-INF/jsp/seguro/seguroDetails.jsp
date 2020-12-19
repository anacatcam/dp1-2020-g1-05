<%@ page session="false" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="madaja" tagdir="/WEB-INF/tags" %>

<madaja:layout pageName="segurosVehiculos">
	
	<h2>Información del seguro</h2>
	
	<table class="table table-striped">
        <tr>
         	<th>Número Póliza</th>
            <td><c:out value="${seguroVehiculo.numeroPoliza}"/></td>
            <td></td>
        </tr>	
        <tr>
            <th>Franquicia</th>
            <c:choose>
               <c:when test="${seguroVehiculo.franquicia eq 0}">
                  <td>Sin franquicia</td>
               </c:when>
               <c:otherwise>
            	<td><c:out value="${seguroVehiculo.franquicia}"/></td>
               </c:otherwise>
            </c:choose>
            <td></td>
        </tr>
        <tr>
            <th>Precio</th>
            <td><c:out value="${seguroVehiculo.precio}"/></td>
            <td></td>
        </tr>
        <tr>
            <th>Cobertura</th>
            <td><c:out value="${seguroVehiculo.cobertura}"/></td>
            <td></td>
        </tr>
        <tr>
            <th>Fecha Inicio</th>
            <td><c:out value="${seguroVehiculo.fechaInicio}"/></td>
            <td></td>
        </tr>
        <tr>
            <th>Fecha Fin</th>
            <td><c:out value="${seguroVehiculo.fechaFin}"/></td>
            <td></td>
        </tr>
        <tr>
            <th>Compañía aseguradora</th>
            <td><c:out value="${seguroVehiculo.compania.nombre}"/></td>
            <td></td>
        </tr>
        <tr>
            <th>Email</th>
            <td><c:out value="${seguroVehiculo.compania.email}"/></td>
            <td></td>
        </tr>
	</table>
	
	<br/>
    <br/>
    <br/>

</madaja:layout>