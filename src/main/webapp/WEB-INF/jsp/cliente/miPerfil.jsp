<%@ page session="false" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="madaja" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="sec"
	uri="http://www.springframework.org/security/tags"%>
<madaja:layout pageName="clientes">
	<h2>Perfil de usuario</h2>
	
	<c:out value="${cliente.dni }"></c:out><br>
	<c:out value="${cliente.firstName }"></c:out><br>
	<c:out value="${cliente.lastName }"></c:out><br>
	<c:out value="${cliente.telefono }"></c:out><br>
</madaja:layout>