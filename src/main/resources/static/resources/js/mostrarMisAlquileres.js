$(function(){
	var clienteId = document.getElementById('clienteId').value
	$.ajax({
		type: 'GET',
		url: 'http://localhost:8090/api/v1/alquileres/' + clienteId,
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
						<td>${data[i].vehiculo.matricula}</td>
						<td>${data[i].vehiculo.precioAlquiler}</td>
						<td>${data[i].fechaInicio}</td>
						<td>${data[i].fechaFin}</td>
						<td>${data[i].limiteKM}</td>
				  </tr>`
		table.innerHTML += row


	}
}