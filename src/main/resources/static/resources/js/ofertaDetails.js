

$(function(){
	var id = document.getElementById('id').value
	$.ajax({
		type: 'GET',
		url: 'http://localhost:8090/api/v1/ofertas/oferta/' + id,
		dataType : "json",
		contentType: "application/json",
		success:function(response){
				$.ajax({
					type: 'GET',
					url: 'http://localhost:8090/api/v1/vehiculos/oferta/' + id,
					dataType : "json",
					contentType: "application/json",
					success:function(response_vehiculos){
						console.log(response_vehiculos)
						buildTable(response,response_vehiculos)
					},
					error : function(){
					}
				});

		},
		error : function(){
			alert("No se encuentra ninguna oferta con id = " + id)
			window.location = "http://localhost:8090/ofertaAPI"
		}
	});
});

function buildTable(data,data_veh){
		var table = document.getElementById('tablaDetalles')
		var row = `<tr>
						<th>Descripcion</th>
						<td><b>${data.name}</b></td>
						<td></td>
				  </tr>
				  <tr>
						<th>Descuento</th>
						<td><b>${data.descuento}</b></td>
						<td></td>
				  </tr>
				  <tr>
						<th>Fecha l\u00EDmite</th>
						<td><b>${data.fechaLimite}</b></td>
						<td></td>
				  </tr>
				  <tr>
						<th>Hora l\u00EDmite</th>
						<td><b>${data.horaLimite}</b></td>
						<td></td>
				  </tr>
				  <tr>
						<th>Oferta aplicada a los siguientes veh\u00EDculos:</th>
						<td>
							<ul id="vehiculos">
								
							</ul>
						</td>
						<td></td>
				 </tr>`
			table.innerHTML = row
			var ul = document.getElementById('vehiculos')

			for(var i = 0; i < data_veh.length; i++){
				var li = `<li>
							<a href="/vehiculos/${data_veh[i].id}">${data_veh[i].marca} ${data_veh[i].modelo}</a>
						 </li`
				ul.innerHTML += li
			}
	}


