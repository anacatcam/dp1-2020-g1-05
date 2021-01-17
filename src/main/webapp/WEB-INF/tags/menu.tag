<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="madaja" tagdir="/WEB-INF/tags"%>
<%@ taglib prefix="sec"
	uri="http://www.springframework.org/security/tags"%>
<!--  >%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags"%-->
<%@ attribute name="name" required="true" rtexprvalue="true"
	description="Name of the active menu: home, owners, vets or error"%>


<nav class="navbar navbar-default" role="navigation">
	<div class="container">
		<div class="navbar-collapse collapse" id="main-navbar">
			<ul class="nav navbar-nav">
				<madaja:menuItem active="${name eq 'home'}" url="/"
					title="home page">
					<span class="glyphicon glyphicon-home" aria-hidden="true"></span>
					<span>Inicio</span>
				</madaja:menuItem>
				
				<madaja:menuItem active="${name eq 'concesionarios'}" url="/concesionario"
					title="concesionarios">
					<span class="glyphicon glyphicon-list" aria-hidden="true"></span>
					<span>Concesionarios</span>
				</madaja:menuItem>
				
				<madaja:menuItem active="${name eq 'vehiculos'}" url="/vehiculos"
					title="vehiculos">
					<span class="glyphicon glyphicon-search" aria-hidden="true"></span>
					<span>Vehículos</span>
				</madaja:menuItem>
				
				<madaja:menuItem active="${name eq 'oferta'}" url="/oferta"
					title="oferta">
					<span class="glyphicon glyphicon-eur" aria-hidden="true"></span>
					<span>Ofertas</span>
				</madaja:menuItem>
				
				<sec:authorize access="hasAuthority('admin')">
				<madaja:menuItem active="${name eq 'clientes'}" url="/clientes"
					title="clientes">
					<span class="glyphicon glyphicon-user" aria-hidden="true"></span>
					<span>Clientes</span>
				</madaja:menuItem>
				</sec:authorize>
				
				<sec:authorize access="hasAuthority('admin')">
					<madaja:menuItem active="${name eq 'reservas'}" url="/reservas"
						title="reservas">
						<span class="glyphicon glyphicon-list" aria-hidden="true"></span>
						<span>Reservas</span>
					</madaja:menuItem>
				</sec:authorize>
				
				<madaja:menuItem active="${name eq 'error'}" url="/oups"
					title="trigger a RuntimeException to see how it is handled">
					<span class="glyphicon glyphicon-warning-sign" aria-hidden="true"></span>
					<span>Error</span>
				</madaja:menuItem>
			</ul>
			
			
			
			
			<!-- Botones para loguearse y registrarse -->
			<ul class="nav navbar-nav navbar-right">
				<sec:authorize access="!isAuthenticated()">
					<li><a href="<c:url value="/login" />">Login</a></li>
					<li><a href="<c:url value="/users/new" />">Register</a></li>
				</sec:authorize>
				<sec:authorize access="isAuthenticated()">
					<li class="dropdown"><a href="#" class="dropdown-toggle"
						data-toggle="dropdown"> <span class="glyphicon glyphicon-user"></span>
							<strong><sec:authentication property="name" /></strong> <span
							class="glyphicon glyphicon-chevron-down"></span>
					</a>
						<ul class="dropdown-menu">
							<li>
								<div class="navbar-login">
									<div class="row">
										<div class="col-lg-4">
											<p class="text-center">
												<span class="glyphicon glyphicon-user icon-size"></span>
											</p>
										</div>
										<div class="col-lg-8">
											<p class="text-left">
												<strong><sec:authentication property="name" /></strong>
											</p>
											<p class="text-left">
												<a href="<c:url value="/logout" />"
													class="btn btn-danger btn-block btn-sm">Logout</a>
											</p>
										</div>
									</div>
								</div>
							</li>
							<li class="divider"></li>
                            <li> 
								<div class="navbar-login navbar-login-session">
									<div class="row">
										<div class="col-lg-12">
											<p>
												<a href="<c:url value="/miPerfil"/>" class="btn btn-primary btn-block">Mi perfil</a>
											</p>
										</div>
									</div>
								</div>
							</li>
							<li class="divider"></li>
							<sec:authorize access="hasAuthority('cliente')">
                            <li> 
								<div class="navbar-login navbar-login-session">
									<div class="row">
										<div class="col-lg-12">
											<p>
												<a href="<c:url value="/MisAlquileres"/>" class="btn btn-primary btn-block">Mis alquileres</a>
											</p>
										</div>
									</div>
								</div>
							</li>
							<li class="divider"></li>
                            <li> 
								<div class="navbar-login navbar-login-session">
									<div class="row">
										<div class="col-lg-12">
											<p>
												<a href="<c:url value="/MisVentas"/>" class="btn btn-primary btn-block">Mis ventas</a>
											</p>
										</div>
									</div>
								</div>
							</li>
							<li class="divider"></li>
							<li> 
								<div class="navbar-login navbar-login-session">
									<div class="row">
										<div class="col-lg-12">
											<p>
												<a href="<c:url value="/reservas/mis-reservas"/>" class="btn btn-primary btn-block">Mis reservas</a>
											</p>
										</div>
									</div>
								</div>
							</li>
							</sec:authorize>
						</ul></li>
				</sec:authorize>
			</ul>
		</div>
		
		
		
	</div>
</nav>
