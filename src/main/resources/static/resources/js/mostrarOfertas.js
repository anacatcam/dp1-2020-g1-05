

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
						

						<a href="/oferta/${data[i].id}">Detalles</a>
						<dt>
						
						<a href="/oferta/${data[i].id}/edit">Editar oferta</a>
						</dt>
						<dt>
						<a href="/oferta/${data[i].id}/delete">Eliminar oferta</a>
						</dt>
						</dl>
							</td>
					  </tr>`
			table.innerHTML += row


		}
	}


