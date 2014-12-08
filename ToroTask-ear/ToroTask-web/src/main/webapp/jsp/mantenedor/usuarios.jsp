<html>
    <head>
        <meta http-equiv="content-type" content="text/html; charset=UTF-8"></meta>
        <title>TORO-TASK :: The evolution of software.</title>
        <link href="styles/examples-offline.css" rel="stylesheet">
        <link href="styles/kendo.common-bootstrap.min.css" rel="stylesheet">
        <link href="styles/kendo.common.min.css" rel="stylesheet"/>
        <link href="styles/kendo.default.min.css" rel="stylesheet"/>
        <link href="styles/kendo.dataviz.default.min.css" rel="stylesheet" />
        <link href="styles/kendo.dataviz.min.css" rel="stylesheet" />
        <link href="styles/kendo.rtl.min.css" rel="stylesheet">
        <link href="styles/style.css" rel="stylesheet">
        <script src="js/jquery.min.js"></script>
        <script src="js/angular.min.js"></script>
        <script src="js/kendo.web.min.js"></script>
    </head>
<body>
<h3>
Mantenedor de usuarios
</h3>
<div id="grilla"></div>
<script>

$(document).ready(function() {

	var grilla = $("#grilla").kendoGrid({
		dataSource: {
			transport: {
				read: {
					url: "mantenedor.do?toMethod=getConsultaUsuarios",
					dataType: "json"
					},
					update: {
					   url: "mantenedor.do?toMethod=getActualizaUsuarios",
					   dataType: "json"
					},
					destroy: {
					   url: "mantenedor.do?toMethod=getEliminaUsuarios",
					   dataType: "json"
					},
					create: {
					   url: "mantenedor.do?toMethod=getCreaUsuarios",
					   dataType: "json"
					},
					parameterMap: function(options, operation) {
					   if (operation !== "read" && options.models) {
						   return {models: kendo.stringify(options.models)};
					   }
				   }
				},
				schema: {
				data: "usuarios",
				total : "total",
				model: {
					id: "idUsuario",
					fields			: {
					idUsuario		: { from: "idUsuario", editable: true, nullable: true },
					correo			: { from: "correo",validation: {validaCorreo: function (input) {
										 if(input.is("[name='correo']")){
											var val_id = $("input[type='text'][name='idUsuario']").val();
											var val_email = $("input[type='text'][name='correo']").val();
											var val_nombre = $("input[type='text'][name='nombre']").val()+ " " +$("input[type='text'][name='apellidoPaterno']").val();
											
											expr = /^([a-zA-Z0-9_\.\-])+\@(([a-zA-Z0-9\-])+\.)+([a-zA-Z0-9]{2,4})+$/;
											if(val_email==""){
												input.attr("data-validaCorreo-msg", "El correo es requerido.");
												return false;
											}else if(!expr.test(val_email)){
												input.attr("data-validaCorreo-msg", "El correo "+ val_email +" no es valido.");
												return false;
											}	
											var estado = true;
											$.ajax({
											  url: 'mantenedor.do?toMethod=getConsultaCorreo',
											  data: "correo="+val_email+"&id="+val_id,
											  async:false,
											  dataType: 'html',
											  success: function(resp){
											   if(resp=="false"){
													estado = false;	
												}
											  }   
											});
											if(!estado){
												input.attr("data-validaCorreo-msg", "El correo "+ val_email +" ya existe.");
												return false;	
											}else{
												return true;
											}										  
										 }else{
											return true;
										}
										}
			                           }
									  },
					nombre			: { from: "nombre", validation: { required: true} },
					apellidoPaterno	: { from: "apellidoPaterno", validation: { required: true} },
					apellidoMaterno	: { from: "apellidoMaterno", validation: { required: true} },
					cargo			: { from: "cargo", validation: { required: true} },
					clave			: { from: "clave",type:"password", validation: {required: true}},
					anexo			: { from: "anexo", validation: { required: true} },
					perfilVO		: { defaultValue: { idPerfil : 2, perfil: "Analista"}},
					estadoVO		: { defaultValue: { idEstado : 1, estado: "Activo"}},
					}
				}
			},
			batch: true,
			pageSize: 20		
		},
		height: 500,
		sortable: true,
		selectable: "row",
		pageable: true,
		toolbar: ["create"],
		columns: [
			{ field: "idUsuario", title: "ID",width: "30px"},
			{ field: "", title: "",width: "80px",template: "<img width='80px' src='servlet/getImagen.jpg?id=#=idUsuario#'/>"},
			{ field: "nombre", title: "Nombre",width: "110px"},
			{ field: "apellidoPaterno", title: "Ap. paterno", width: "100px"},
			{ field: "apellidoMaterno", title: "Ap. Materno", width: "100px"},
			{ field: "correo", title: "Correo",width: "180px"},
			{ field: "anexo", title: "Fono", width: "100px"},
			{ field: "clave", title: "Clave", width: "100px",editor: passwordEditor, template:"******" },
			{ field: "perfilVO", title: "Cargo", width: "120px", editor: perfilDropDownEditor, template: "#=perfilVO.perfil#" },
			{ field: "estadoVO", title: "Estado", width: "120px", editor: estadoDropDownEditor, template: "#=estadoVO.estado#" },
			{ command: ["edit",], title: " ", width: "180px" }
		],
		editable: "inline",
	}).data("kendoGrid");
	
	function passwordEditor(container, options){
		$('<input type="password" required data-bind="value:' + options.field + '"/>').appendTo(container);
	};
	
	function perfilDropDownEditor(container, options) {
	$('<input required data-text-field="perfil" data-value-field="idPerfil" data-bind="value:' + options.field + '"/>')
		.appendTo(container)
		.kendoDropDownList({
			autoBind: false,
			dataSource: {
				transport: {
					read: {
						url: "mantenedor.do?toMethod=getConsultaCargos",
						dataType: "json"
						}
					}
				}
		});
	}
	
	function estadoDropDownEditor(container, options) {
	$('<input required data-text-field="estado" data-value-field="idEstado" data-bind="value:' + options.field + '"/>')
		.appendTo(container)
		.kendoDropDownList({
			autoBind: false,
			dataSource: {
				transport: {
					read: {
						url: "mantenedor.do?toMethod=getConsultaEstados",
						dataType: "json"
						}
					}
				}
		});
	}

})					
</script>