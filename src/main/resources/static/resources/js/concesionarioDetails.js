$(function(){
	var id = document.getElementById('id').value
	$.ajax({
		type: 'GET',
		url: 'http://localhost:8090/api/v1/concesionarios/concesionario/' + id,
		dataType : "json",
		contentType: "application/json",
		success:function(response){
			$.ajax({
				type: 'GET',
				url: 'http://localhost:8090/api/v1/concesionarios/concesionario/' + id +'/gestores',
				dataType : "json",
				contentType: "application/json",
				success:function(response_gestores){
					$.ajax({
						type: 'GET',
						url: 'http://localhost:8090/api/v1/concesionarios/concesionario/' + id +'/vehiculos',
						dataType : "json",
						contentType: "application/json",
						success:function(response_vehiculos){					
							$.ajax({
							type: 'GET',
							url: 'http://localhost:8090/api/v1/concesionarios/concesionario/' + id +'/vehiculos/disponibilidad',
							dataType : "json",
							contentType: "application/json",
							success:function(response_disponibilidad){
								$.ajax({
									type: 'GET',
									url: 'http://localhost:8090/api/v1/concesionarios/concesionario/' + id +'/vehiculos/incidencias',
									dataType : "json",
									contentType: "application/json",
									success:function(response_vehiculos){
										buildTables(response, response_gestores, response_vehiculos)
									},
									error : function(){
										alert("No se ha encontrado ningun vehiculo en el concesionario con id = " + id)
										window.location = "http://localhost:8090/concesionariosAPI"
									}
								});

							},
							error : function(){
								alert("No se ha encontrado ningun vehiculo en el concesionario con id = " + id)
								window.location = "http://localhost:8090/concesionariosAPI"
							}
						});
						},
						error : function(){
							alert("No se ha encontrado ningun vehiculo en el concesionario con id = " + id)
							window.location = "http://localhost:8090/concesionariosAPI"
						}
					});
				},
				error : function(){
					alert("No se ha encontrado ningun gestor en el concesionario con id = " + id)
					window.location = "http://localhost:8090/concesionariosAPI"
				}
			});
		},
		error : function(){
				alert("No se ha encontrado ningun concesionario con id = " + id)
				window.location = "http://localhost:8090/concesionariosAPI"
		}
	});
});

function buildTables(data, dataGestores, dataVehiculos){
		var tableConcesionario = document.getElementById('detallesConcesionario')
		var tableGestor= document.getElementById('detallesGestores')
		var tableVehiculo= document.getElementById('detallesVehiculos')
		var rowConcesionario = `<tr>
						<th>Nombre</th>
						<td><b>${data.nombre}</b></td>
						<td></td>
				  </tr>
				  <tr>
						<th>Provincia</th>
						<td><b>${data.provincia}</b></td>
						<td></td>
				  </tr>
				  <tr>
						<th>Localidad</th>
						<td><b>${data.localidad}</b></td>
						<td></td>
				  </tr>
				  <tr>
						<th>Direcci\u00f3n</th>
						<td><b>${data.direccion}</b></td>
						<td></td>
				  </tr>
				  <tr>
						<th>C\u00f3digo Postal</th>
						<td><b>${data.codigoPostal}</b></td>
						<td></td>
				  </tr>
				  <tr>
						<th>Pa\u00eds</th>
						<td><b>${data.pais}</b></td>
						<td></td>
				  </tr>	
				  <tr>
						<th>Email</th>
						<td><b>${data.email}</b></td>
						<td></td>
				  </tr>
				  <tr>
						<th>Tel\u00e9fono</th>
						<td><b>${data.telefono}</b></td>
						<td></td>
				  </tr>`
			
			tableConcesionario.innerHTML = rowConcesionario
			for(var it=0; it<dataVehiculos.length; it++){
				var column1Vehiculo=
					`<tr>
				    <td valign="top">
                    	<dl class="dl-horizontal">
							<dt>Marca</dt>
							<dd><b>${dataVehiculos[it].marca}</b></dd>

							<dt>Modelo</dt>
							<dd><b>${dataVehiculos[it].modelo}</b></dd>

							<dt>Plazas</dt>
							<dd><b>${dataVehiculos[it].plazas}</b></dd>

							<dt>Precio de alquiler</dt>
							<dd><b>${dataVehiculos[it].precioAlquiler}</b></dd>

							<dt>Precio de venta</dt>
							<dd><b>${dataVehiculos[it].precioVenta}</b></dd>
							
							<dt>Disponibilidad</dt>
							<dd><b>${dataVehiculos[it].disponible}</b></dd>							
						</dl>
					</td>
					<td valign="top">
						<table id="detallesVehiculos2" class="table-condensed">
		                </table>
		            </td>
		         </tr>`
				tableVehiculo.innerHTML += column1Vehiculo
			}
			for (var i = 0; i < dataGestores.length; i++){
				var column1Gestor=
					`<tr>
					    <td valign="top">
	                    	<dl class="dl-horizontal">
								<dt>DNI</dt>
								<dd><b>${dataGestores[i].dni}</b></dd>
	
								<dt>Nombre</dt>
								<dd><b>${dataGestores[i].nombre}</b></dd>
	
								<dt>Apellidos</dt>
								<dd><b>${dataGestores[i].apellidos}</b></dd>
	
								<dt>Tel\u00e9fono</dt>
								<dd><b>${dataGestores[i].telefono}</b></dd>
	
								<dt>Email</dt>
								<dd><b>${dataGestores[i].email}</b></dd>
							</dl>
						</td>
						<td valign="top">
							<table id="detallesGestores2" class="table-condensed">
			                </table>
			            </td>
			         </tr>`
				tableGestor.innerHTML +=column1Gestor
				var tableGestor2= document.getElementById('detallesGestores2')
				tableGestor2.innerHTML +=`<thead>
												<tr>
										 		<th>Concesionario</th>
								                <th>Localizaci\u00f3n</th>
								          </tr>
								          <thead>`
				
				for(var j=0; j <dataGestores[i].concesionarios.length; j++){
					var column2Gestor2=`
	                    <tr>
	                    	<td>
	                    		<a href="/concesionariosAPI/${dataGestores[i].concesionarios[j].id}">${dataGestores[i].concesionarios[j].nombre}
	                    	</td>
	                    	<td>
	                    		${dataGestores[i].concesionarios[j].provincia} (${dataGestores[i].concesionarios[j].localidad})
	                    	</td>
	                    </tr>`
					tableGestor2.innerHTML += column2Gestor2
				}
		}
}
