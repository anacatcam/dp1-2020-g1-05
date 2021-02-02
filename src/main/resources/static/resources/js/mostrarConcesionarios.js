$(function(){
	$.ajax({
		type: 'GET',
		url: 'http://localhost:8090/api/v1/concesionarios/all',
		dataType : "json",
		contentType: "application/json",
		success:function(response){
			buildTable(response)
		},
		error : function(){
			alert("Se ha producido un error")
		}
	});
});

function buildTable(data){
	var table = document.getElementById('concesionariosTable')

	for (var i = 0; i < data.length; i++){
		var row = `<tr>
						<td><a href="/concesionariosAPI/${data[i].id}">${data[i].id}</td>
						<td>${data[i].nombre}</td>
						<td>${data[i].email}</td>
						<td>${data[i].telefono}</td>
				  </tr>`
		table.innerHTML += row


	}
}