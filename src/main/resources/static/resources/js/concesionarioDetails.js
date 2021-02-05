
$(function(){
	var concesionarioId = document.getElementById('concesionarioId').value
	$.ajax({
		type: 'GET',
		url: 'http://localhost:8090/api/v1/concesionarios/' + concesionarioId,
		dataType : "json",
		contentType: "application/json",
		success:function(response_concesionario){
			buildTableConcesionario(response_concesionario)
			$.ajax({
				type: 'GET',
				url: 'http://localhost:8090/api/v1/concesionarios/' + concesionarioId +'/gestores',
				dataType : "json",
				contentType: "application/json",
				success:function(response_gestores){
					buildTableGestor(response_gestores)
				},error : function(){
					alert("No se han encontrado gestores en el concesionario con id=" + concesionarioId)
					window.location = "http://localhost:8090/concesionariosAPI"
				}
			});
			$.ajax({
				type: 'GET',
				url: 'http://localhost:8090/api/v1/concesionarios/' + concesionarioId +'/vehiculos',
				dataType : "json",
				contentType: "application/json",
				success:function(response_vehiculos){
					buildTableVehiculo(response_vehiculos)
				},error : function(){
					alert("No se han encontrado vehiculos en el concesionario con id=" + concesionarioId)
					window.location = "http://localhost:8090/concesionariosAPI"
				}
			});
		},error : function(){
			alert("No se ha encontrado el concesionario con id=" + concesionarioId)
			window.location = "http://localhost:8090/concesionariosAPI"
		}
	});
});


function buildTableConcesionario(concesionario){
	var tableConcesionario = document.getElementById('detallesConcesionario')
	var rowConcesionario = 
		 `<tr>
				<th>Nombre</th>
				<td>${concesionario.nombre}</td>
				<td></td>
		  </tr>
		  <tr>
				<th>Provincia</th>
				<td>${concesionario.provincia}</td>
				<td></td>
		  </tr>
		  <tr>
				<th>Localidad</th>
				<td>${concesionario.localidad}</td>
				<td></td>
		  </tr>
		  <tr>
				<th>Direcci\u00f3n</th>
				<td>${concesionario.direccion}</td>
				<td></td>
		  </tr>
		  <tr>
				<th>C\u00f3digo Postal</th>
				<td>${concesionario.codigoPostal}</td>
				<td></td>
		  </tr>
		  <tr>
				<th>Pa\u00eds</th>
				<td>${concesionario.pais}</td>
				<td></td>
		  </tr>	
		  <tr>
				<th>Email</th>
				<td>${concesionario.email}</td>
				<td></td>
		  </tr>
		  <tr>
				<th>Tel\u00e9fono</th>
				<td>${concesionario.telefono}</td>
				<td></td>
		 </tr>`

	tableConcesionario.innerHTML = rowConcesionario
}

var solucionada="Sí"
var stringVehiculoHTML=""

function buildTableVehiculo(vehiculos){
	var tableVehiculo= document.getElementById('detallesVehiculos')	
	
	for(var it=0; it<vehiculos.length; it++){
		var column1Vehiculo=
			`<tr>
		    <td valign="top">
            	<dl class="dl-horizontal">
            		<dt>Matr\u00edcula</dt>
					<dd>${vehiculos[it].matricula}</dd>
					
					<dt>Marca</dt>
					<dd>${vehiculos[it].marca}</dd>

					<dt>Modelo</dt>
					<dd>${vehiculos[it].modelo}</dd>

					<dt>Plazas</dt>
					<dd>${vehiculos[it].plazas}</dd>

					<dt>Precio de alquiler</dt>
					<dd>${vehiculos[it].precioAlquiler}</dd>

					<dt>Precio de venta</dt>
					<dd>${vehiculos[it].precioVenta}</dd>
					
					<dt>Disponibilidad</dt>
					<dd>${vehiculos[it].disponible.name}</dd>							
				</dl>
			</td>
			<td valign="top">
				<table id="detallesIncidencia" class="table-condensed">
					<tr>
					<td valign="top">
						<dl class="dl-horizontal">
				    		<dt>INCIDENCIAS</dt>
				    	</dl>
				    </td>
					</tr>`
		stringVehiculoHTML+= column1Vehiculo
			
		var incidenciasVehiculo=vehiculos[it].incidencias
		
		for(var i=0; i<incidenciasVehiculo.length; i++){
			
			if(!incidenciasVehiculo[i].solucionada){
				solucionada="No"
			}
			
			var column2Vehiculo=
				`<tr>
					<td valign="top">
                		<dl class="dl-horizontal">
                			<dd>
        					<dt>Descripci\u00f3n</dt>
        					<dd>${incidenciasVehiculo[i].descripcion}</dd>
       				    	<dt>Mec\u00e1nico</dt>`
				
		    stringVehiculoHTML+= column2Vehiculo    
			for(var j=0; j<incidenciasVehiculo[i].mecanicos.length; j++){
				stringVehiculoHTML+=`<dd>${incidenciasVehiculo[i].mecanicos[j].nombre} ${incidenciasVehiculo[i].mecanicos[j].apellidos}, ${incidenciasVehiculo[i].mecanicos[j].dni}</dd>`  
			}
			stringVehiculoHTML+=`<dt>Solucionada</dt>
            				<dd>${solucionada}</dd>
                        </dl>
                    </td>
                </tr>`	
		}
		stringVehiculoHTML += `</table></td></tr>`
		tableVehiculo.innerHTML = stringVehiculoHTML
	}
}

var stringGestorHTML=""
	
function buildTableGestor(gestores){
	var tableGestor= document.getElementById('detallesGestores')
	for (var i = 0; i < gestores.length; i++){
		var column1Gestor=
			`<tr>
			    <td valign="top">
                	<dl class="dl-horizontal">
						<dt>DNI</dt>
						<dd>${gestores[i].dni}</dd>

						<dt>Nombre</dt>
						<dd>${gestores[i].nombre}</dd>

						<dt>Apellidos</dt>
						<dd>${gestores[i].apellidos}</dd>

						<dt>Tel\u00e9fono</dt>
						<dd>${gestores[i].telefono}</dd>

						<dt>Email</dt>
						<dd>${gestores[i].email}</dd>
					</dl>
				</td>
				<td valign="top">
					<table id="detallesGestores2" class="table-condensed">
						<thead>
						<tr>
					 		<th>Concesionario</th>
			                <th>Localizaci\u00f3n</th>
		          		</tr>
				        <thead>`
		stringGestorHTML +=column1Gestor
		
		for(var j=0; j <gestores[i].concesionarios.length; j++){
			var column2Gestor2=`
                <tr>
                	<td>
                		<a href="/concesionariosAPI/${gestores[i].concesionarios[j].id}">${gestores[i].concesionarios[j].nombre}
                	</td>
                	<td>
                		${gestores[i].concesionarios[j].provincia} (${gestores[i].concesionarios[j].localidad})
                	</td>
                </tr>`
				stringGestorHTML += column2Gestor2
		}
		stringGestorHTML += `</table></td></tr>`
		tableGestor.innerHTML = stringGestorHTML
	}
}

