$(document).ready(function(){
	add_options();
	$('#add-owner-form').submit(function(event){
			$("#fechaLimite").datepicker({dateFormat: 'yy-mm-dd'});
			event.preventDefault();
		
			ajax_submit();
		
			

			
	});
});

function add_options(){
	$.ajax({
		type: 'GET',
		url: 'http://localhost:8090/api/v1/vehiculos/disponibles-ofertas',
		dataType : "json",
		contentType: "application/json",
		success:function(response){
			var select = document.getElementById("vehiculos");
			for(var i = 0; i<response.length;i++){
				var opt = document.createElement('option')
				opt.value=response[i].id;
				opt.innerHTML = response[i].matricula;
				select.appendChild(opt);
			}
			console.log("Exito")
		},
		error : function(){
			console.log("Error!")
		}
	});
}
function ajax_submit(){
	var oferta = {}
	oferta["name"] = $('#name').val();
	oferta["descuento"] = $('#descuento').val();
	oferta["fechaLimite"] = $('#fechaLimite').val();
	oferta["horaLimite"] = $('#horaLimite').val();
	
	var vehiculoId = $('#vehiculos').val();
	if(!$('#vehiculos').val()){
		vehiculoId = "";
	}
	$.ajax({
		type: "POST",
		contentType: "application/json",
		url: "http://localhost:8090/api/v1/ofertas/saveOferta/" + vehiculoId,
		data: JSON.stringify(oferta),

		success: function(data){
			console.log("Exito!",data);
			window.location.href = "/ofertaAPI/";
		},
		error: function(e){
			var json = e.responseJSON;
			for(var i=0; i<json.length; i++){
				var field = json[i].field;
				if(field == 'name'){
					var error = document.getElementById('errorName')
					error.innerHTML = json[i].msg;
					error.style.color = "red";
				}
				if(field == 'descuento'){
					var error = document.getElementById('errorDescuento')
					error.innerHTML = json[i].msg;
					error.style.color = "red";
				}
				if(field == 'fechaLimite'){
					var error = document.getElementById('errorFecha')
					error.innerHTML = json[i].msg;
					error.style.color = "red";
				}
				if(field == 'horaLimite'){
					var error = document.getElementById('errorHora')
					error.innerHTML = json[i].msg;
					error.style.color = "red";
				}
			}
			console.log("Error!", json)
		}
	});
}

