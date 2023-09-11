<!DOCTYPE html>
<html lang="esS">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no">
<script type="text/javascript" src="js/jquery.min.js"></script>
<script type="text/javascript" src="js/bootstrap.min.js"></script>
<script type="text/javascript" src="js/bootstrapValidator.js"></script>
<script type="text/javascript" src="js/global.js"></script>

<link rel="stylesheet" href="css/bootstrap.css"/>
<link rel="stylesheet" href="css/bootstrapValidator.css"/>
<title >Ejercicios Propuestos DAW-I (Celso Rodriguez) </title>
</head>
<body>

	<div class="container" >
<h1 style="text-align: center">Registra Proveedor</h1>
<h5 style="text-align: center">Celso Rodriguez - i201412626</h5>

		<form id="id_form">
			<div class="row" style="margin-left: 30%">
				<div style="margin-top: 1%">
					<div class="form-group  col-sm-6">
						<div class="col-sm-4">
							<label class="control-label" for="id_nombre">Nombre</label>
						</div>
						<div class="col-sm-8">
							<input class="form-control" type="text" id="id_nombre"
								name="nombre" placeholder="Ingrese el nombre" maxlength="40">

						</div>
					</div>
				</div>
			</div>


			<div class="row" style="margin-left: 30%">
				<div style="margin-top: 1%">
					<div class="form-group  col-sm-6">
						<div class="col-sm-4">
							<label class="control-label" for="id_dni">Dni</label>
						</div>
						<div class="col-sm-8">
							<input class="form-control" type="text" id="id_dni" name="dni"
								placeholder="Ingrese el dni" maxlength="8">

						</div>
					</div>
				</div>
			</div>

			<div class="row" style="margin-left: 30%">
				<div style="margin-top: 1%">
					<div class="form-group  col-sm-6">
						<div class="col-sm-4">
							<label class="control-label" for="id_tipo">Tipo</label>
						</div>
						<div class="col-sm-8">
							<select id="id_tipo" name="tipo.idTipo" class='form-control'>
								<option value=" ">[Seleccione]</option>
							</select>
						</div>
					</div>
				</div>
			</div>

			<div class="row" style="margin-left: 30%">
				<div style="margin-top: 1%">
					<div class="form-group  col-sm-6">
						<div class="col-sm-4">
							<label class="control-label" for="id_pais">Pais</label>
						</div>
						<div class="col-sm-8">
							<select id="id_pais" name="pais.idPais" class='form-control'>
								<option value=" ">[Seleccione]</option>
							</select>
						</div>
					</div>
				</div>
			</div>

			<div class="row" style="margin-top: 2%" align="center">
				<button id="id_registrar" type="button" class="btn btn-primary">Registrar
					Proveedor</button>

			</div>
		</form>
	</div>

<script type="text/javascript">

$.getJSON("listaPais", {}, function(data){
	$.each(data, function(index,item){
		$("#id_pais").append("<option value="+item.idPais +">"+ item.nombre +"</option>");
	});
});

$.getJSON("listaTipo", {}, function(data){
	$.each(data, function(index,item){
		$("#id_tipo").append("<option value="+item.idTipo +">"+ item.descripcion +"</option>");
	});
});

$("#id_registrar").click(function (){ 
	
	//Lanza la validacion.
	var validator = $('#id_form').data('bootstrapValidator');
    validator.validate();

	
   
    //Para ver la validacion en el sistema.
    if (validator.isValid()){
    	console.log('Es valido');
    	console.log($('#id_form').serialize());
    	
    	$.ajax({
    		type: "POST",
            url: "registraProveedor", 
            data: $('#id_form').serialize(),
            success: function(data){
           
            	limpiar();
            	validator.resetForm();
            	mostrarMensaje(data.MENSAJE);
            },
            error: function(){
            	mostrarMensaje(data.MSG_ERROR);
            }
    	});
    }

});


function limpiar(){
	$('#id_nombre').val('');
	$('#id_dni').val('');
	$('#id_tipo').val('');
	$('#id_pais').val('');

}

    $('#id_form').bootstrapValidator({
        message: 'This value is not valid',
        feedbackIcons: {
            valid: 'glyphicon glyphicon-ok',
            invalid: 'glyphicon glyphicon-remove',
            validating: 'glyphicon glyphicon-refresh'
        },
        fields: {
        	nombre: {
        		selector : '#id_nombre',
                validators: {
                    notEmpty: {
                        message: 'El nombre es un campo obligatorio'
                    },
                    stringLength :{
                    	message:'El nombre es de 3 a 100 caracteres',
                    	min : 3,
                    	max : 100
                    },
                    remote :{
                	    delay: 1000,
                	 	url: 'buscaPorNombreProveedor',
                	 	message: 'El Nombre ya existe'
                 	}
                }
            },
            
            dni: {
        		selector : '#id_dni',
                validators: {
                    notEmpty: {
                        message: 'El dni es un campo obligatorio'
                    },
                    stringLength :{
                    	message:'El dni es de 8 caracteres',
                    	max : 8
                    },
                    remote :{
                	    delay: 1000,
                	 	url: 'buscaPorDniProveedor',
                	 	message: 'El Dni ya existe'
                 	}
                }
            },
            
            tipo: {
        		selector : '#id_tipo',
                validators: {
                	notEmpty: {
                        message: 'El Tipo un campo obligatorio'
                    },
                }
            },
            
            pais: {
        		selector : '#id_pais',
                validators: {
                	notEmpty: {
                        message: 'El Pais un campo obligatorio'
                    },
                }
            },
            
        }   
    });

    


</script>

</body>
</html>




