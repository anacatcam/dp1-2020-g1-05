<%@ page session="false" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="madaja" tagdir="/WEB-INF/tags" %>

<madaja:layout pageName="clientes">
    <h2>
        <c:if test="${cliente['new']}">Nuevo </c:if> Cliente
    </h2>
    <form:form modelAttribute="cliente" class="form-horizontal" id="add-owner-form">
        <div class="form-group has-feedback">
      	  <input type="hidden" name="id" value="${cliente.id}"/>
            <madaja:inputField label="DNI" name="dni"/>
            <madaja:inputField label="Nombre" name="firstName"/>
            <madaja:inputField label="Apellidos" name="lastName"/>
            <madaja:inputField label="Teléfono" name="telefono"/>
            <madaja:inputField label="Correo" name="email"/>
            <input name="esConflictivo" type="hidden" value="No">
            <madaja:inputField label="Username" name="user.username"/>
            <madaja:inputField label="Password" name="user.password"/>
            <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
        </div>
        <div class="form-group">
            <div class="col-sm-offset-2 col-sm-10">
                <c:choose>
                    <c:when test="${cliente['new']}">
                        <button class="btn btn-default" type="submit">Registrarme</button>
                    </c:when>
                    <c:otherwise>
                        <button class="btn btn-default" type="submit">Actualizar cuenta</button>
                    </c:otherwise>
                </c:choose>
            </div>
        </div>
    </form:form>
</madaja:layout>
