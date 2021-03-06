$(document).ready(function(){
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
		},
		error : function(){
			alert("Se ha producido un error");
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
		errorDescuento.innerHTML = "El descuento no puede estar vac??o o ser menor que 0";
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