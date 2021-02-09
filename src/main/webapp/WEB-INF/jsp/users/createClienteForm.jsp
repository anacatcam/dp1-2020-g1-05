<%@ page session="false" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="madaja" tagdir="/WEB-INF/tags" %>

<madaja:layout pageName="clientes">
    <h2>
        <c:if test="${cliente['new']}">Nuevo </c:if> cliente
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
            <div class="form-inline">
            	<label for="user.password" class="col-sm-2 control-label" style="margin-left:-10px; margin-right:15px;">Contraseña</label>
            	<input class="form-control" type="password" id="user.password" name="user.password" pattern="^(?=.*[a-z])(?=.*[A-Z])(?=.*\d)[a-zA-Z\d]{8,}$" placeholder="8 caracteres como mínimo y 20 de máximo, una mayúscula, una minúscula y un número requerido" 
            	maxlength="20" size="126" style="margin-right:-10px;"/>
            </div>
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
