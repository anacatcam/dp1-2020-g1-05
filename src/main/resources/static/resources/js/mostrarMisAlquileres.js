$(function(){
	$.ajax({
		type: 'GET',
		url: 'http://localhost:8090/api/v1/alquileres/',
		dataType : "json",
		contentType: "application/json",
		success:function(response){
			buildTable(response)
		},
		error : function(){
		}
	});
});

function buildTable(data){
	var table = document.getElementById('alquileresTable')

	for (var i = 0; i < data.length; i++){
		var row = `<tr>
						<td>${data[i].id}</td>
						<td>${data[i].vehiculo.marca} ${data[i].vehiculo.modelo}</td>
						<td><a href="/vehiculos/${data[i].vehiculo.id}">${data[i].vehiculo.matricula}</a></td>
						<td>${data[i].vehiculo.precioAlquiler}</td>
						<td>${data[i].fechaInicio}</td>
						<td>${data[i].fechaFin}</td>
						<td>${data[i].limiteKM}</td>
				  </tr>`
		table.innerHTML += row


	}
}