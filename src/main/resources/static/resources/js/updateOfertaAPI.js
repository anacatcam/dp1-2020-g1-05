$(document).ready(function(){
	add_values();
	add_options();
	$('#add-owner-form').submit(function(event){
		var errors = validate();
		if(errors == true){
			event.preventDefault();
		}else{
			ajax_submit();
		}
				
	});
});

function add_values(){
	var id = document.getElementById('id').value;
	$.ajax({
		type: 'GET',
		url: 'http://localhost:8090/api/v1/ofertas/oferta/' + id,
		dataType : "json",
		contentType: "application/json",
		success:function(response){
				document.getElementById("name").value = response.name;
				document.getElementById("descuento").value = response.descuento;
				document.getElementById("fechaLimite").value = response.fechaLimite;
				document.getElementById("horaLimite").value = response.horaLimite;

		},
		error : function(){
			alert("No se encuentra ninguna oferta con id = " + id)
			window.location = "http://localhost:8090/ofertaAPI"
		}
	});
}

function add_options(){
	var id = document.getElementById('id').value;
	$.ajax({
		type: 'GET',
		url: 'http://localhost:8090/api/v1/vehiculos/oferta/' + id,
		dataType : "json",
		contentType: "application/json",
		success:function(response){
			add_options_html(response)
			$.ajax({
				type: 'GET',
				url: 'http://localhost:8090/api/v1/vehiculos/disponibles-ofertas',
				dataType : "json",
				contentType: "application/json",
				success:function(response){
					add_options_html(response)
				},
				error : function(){
					alert("Se ha producido un error")
				}
			});
			
		},
		error : function(){
			alert("Se ha producido un error")
		}
	});
}

function add_options_html(response){
	var select = document.getElementById("vehiculos");
	for(var i = 0; i<response.length;i++){
		var opt = document.createElement('option')
		opt.value=response[i].id;
		opt.innerHTML = response[i].matricula;
		select.appendChild(opt);
	}
}
function ajax_submit(){
	var id = document.getElementById('id').value;
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
		type: "PUT",
		contentType: "application/json",
		url: "http://localhost:8090/api/v1/ofertas/oferta/" + id + "/" + vehiculoId,
		data: JSON.stringify(oferta),

		success: function(data){
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
		}
	});
}

function validate(){
	const name = document.getElementById('name');
	const descuento = document.getElementById('descuento');
	const fechaLimite = document.getElementById('fechaLimite');
	const horaLimite = document.getElementById('horaLimite');
	var fecha = new Date(fechaLimite.value);
	var errors = false;
	
	var errorName = document.getElementById('errorName');
	errorName.innerHTML = "";
	
	var errorDescuento = document.getElementById('errorDescuento');
	errorDescuento.innerHTML = "";
	
	var errorFecha = document.getElementById('errorFecha');
		errorFecha.innerHTML = "";
		
	var errorHora = document.getElementById('errorHora');
		errorHora.innerHTML = "";
		
	if(name.value == null || name.value==''){
		errors = true;
		errorName.innerHTML = "El nombre no puede estar vacio";
		errorName.style.color = "red";
	}
	if(descuento.value == '' || descuento.value < 0 || descuento.value == null){
		errors = true;
		errorDescuento.innerHTML = "El descuento no puede estar vacío o ser menor que 0";
		errorDescuento.style.color = "red";
	}
	if(isDateBeforeToday(fecha) || fechaLimite.value == null || fechaLimite.value == '')	{
		errors = true;
		errorFecha.innerHTML = "La fecha no puede estar vacia y no puede ser anterior a la fecha de hoy ";
		errorFecha.style.color = "red";
	}
	if(horaLimite.value == null || horaLimite.value == '')	{
		errors = true;
		errorHora.innerHTML = "La hora limite no puede estar vacia";
		errorHora.style.color = "red";
	}
	return errors; 
}

function isDateBeforeToday(date) {
	var today = new Date();
    return date.valueOf() <= today.valueOf();
}

