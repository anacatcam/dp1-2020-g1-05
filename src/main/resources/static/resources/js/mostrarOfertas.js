

$(function(){
	$.ajax({
		type: 'GET',
		url: 'http://localhost:8090/api/v1/ofertas/all',
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
		var table = document.getElementById('ownersTable')

		for (var i = 0; i < data.length; i++){
			var row = `<tr>
							<td>${data[i].name}</td>
							<td>${data[i].descuento}</td>
							<td>
								<dl>
						

						<a href="/ofertaAPI/${data[i].id}">Detalles</a>
						<dt>
						
						<a href="/ofertaAPI/${data[i].id}/edit">Editar oferta</a>
						</dt>
						<dt>
						<button onclick="deleteOferta('${data[i].id}')">Eliminar oferta</a>
						</dt>
						</dl>
							</td>
					  </tr>`
			table.innerHTML += row

		}
}

function deleteOferta(id){
	$.ajax({
		type: 'DELETE',
		url: 'http://localhost:8090/api/v1/ofertas/delete/' + id,
		dataType : "json",
		contentType: "application/json",
		success: function(data){
		
			location.reload();
		},
		error: function(){
			alert("Se ha producido un error")
		}
	});
}
	
	

