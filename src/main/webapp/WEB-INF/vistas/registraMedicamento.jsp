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
<h1 style="text-align: center">Registra Medicamento</h1>
<h5 style="text-align: center">Celso Rodriguez - i201412626</h5>

	<form id="id_form">
			<div class="row" style="margin-left: 30%"  >
			<div style="margin-top: 1%">
				<div class="form-group  col-sm-6"  >				
					<div class="col-sm-4">
						<label class="control-label" for="id_nombre">Nombre</label>
					</div>
					<div  class="col-sm-8">
						<input class="form-control" type="text" id="id_nombre"
							name="nombre" placeholder="Ingrese el nombre" maxlength="40" >

					</div>
					</div>
				</div>
			</div>



			<div class="row" style="margin-left: 30%" >
			<div style="margin-top: 1%">
				<div class="form-group  col-sm-6">
					<div class="col-sm-4">
						<label class="control-label" for="id_nombre">Precio</label>
					</div>
					<div class="col-sm-8">
						<input class="form-control" type="text" id="id_precio"
							name="precio" placeholder="Ingrese el precio">

					</div>
				</div>
</div>
			</div>

			<div class="row" style="margin-left: 30%" >
			<div style="margin-top: 1%">
				<div class="form-group  col-sm-6">
					<div class="col-sm-4">
						<label class="control-label" for="id_nombre">Stock</label>
					</div>
					<div class="col-sm-8">
						<input class="form-control" type="number" id="id_stock"
							name="stock" placeholder="Ingrese el Stock" maxlength="40">

					</div>
				</div>
				</div>
			</div>

			<div class="row" style="margin-left: 30%"  >
				<div class="form-group col-sm-6">
					<div class="col-sm-4">
						<label class="control-label" for="id_estado">Laboratorio</label>
					</div>
					<div class="col-sm-8">
						<input name="laboratorio" class="form-control" type="text"
							id="id_laboratorio">

					</div>
				</div>

			</div>

			<div class="row" style="margin-top: 2%" align="center">
				<button id="id_registrar" type="button" class="btn btn-primary">Crea
					Medicamento</button>

			</div>
		</form>
</div>

<script type="text/javascript">

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
            url: "registraMedicamento", 
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

}

$(document).ready(function() {
    $('#id_form').bootstrapValidator({
        message: 'This value is not valid',
        feedbackIcons: {
            valid: 'glyphicon glyphicon-ok',
            invalid: 'glyphicon glyphicon-remove',
            validating: 'glyphicon glyphicon-refresh'
        },
        fields: {
        		nombre:{
                    selector: "#id_nombre",
                    validators:{
                        notEmpty: {
                             message: 'El nombre es obligatorio'
                        },
                        stringLength: {
                            min: 3,
                            max: 40,
                            message: 'El nombre es de 3 a 40 caracteres'
                        },
                    }
                },
        }   
    });

    
});

</script>

</body>
</html>




