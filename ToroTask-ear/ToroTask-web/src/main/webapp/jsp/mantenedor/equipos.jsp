<%@ page language="java" import="java.util.*" pageEncoding="ISO-8859-1"%>
<html>
<head>
</head>
<body>
<h3>
Equipo de usuarios
</h3>

Jefe de proyectos.<br>
<input id="jefe" style="width: 400px" />
<br></br>
Analistas.<br>
<input id="analistas" style="width: 400px" />
<br><br>
<button id="report">
	Actualizar
</button>
<span id="popupNotification"></span>
<script>
$(document).ready(function() {

	var popupNotification = $("#popupNotification").kendoNotification({allowHideAfter: 1000}).data("kendoNotification");

    $("#jefe").kendoDropDownList({
  		optionLabel:"Seleccione Jefe de Proyectos ...",
    	select: onSelect,
        dataTextField: "correo",
        dataValueField: "idUsuario",
        dataSource: {
            transport: {
                read: {
                    dataType: "json",
                    url: "mantenedor.do?toMethod=getConsultaCargoUsuarios&idPerfil=2",
                }
            }
        }
    });
    
    $("#analistas").kendoMultiSelect({
		placeholder:"Seleccione integrantes ...",
        dataTextField: "correo",
        dataValueField: "idUsuario",
		autoBind: true,
		dataSource:{
			transport:{
				read:{
					url :"mantenedor.do?toMethod=getConsultaCargoUsuarios&idPerfil=1",
					dataType:"json",
					data:{
						q: function(){
								return $("#analistas").data("kendoMultiSelect").value();
							}
						}
				}
			}
		}
    });
	
	$("#report").kendoButton({
		click:guardar
	})	
	
	function onSelect(e) {
		var dataItem = this.dataItem(e.item.index());
       llenarIntegrantes(dataItem.idUsuario);
    };
	
	function llenarIntegrantes(idJefe){
		$.ajax({
			  url: 'mantenedor.do?toMethod=getConsultaEquipo&idJefe='+idJefe,
			  dataType: 'json',
			  success: function( resp ) {
				$("#analistas").data("kendoMultiSelect").value(resp);
			  },
			  error: function( req, status, err ) {
				
			  }
		});					
	}
	
	function guardar(e) {
		if(validacion()){
			var formData = "jefe="+$("#jefe").data("kendoDropDownList").value()+
			"&analistas="+$("#analistas").data("kendoMultiSelect").value();
			$.ajax({
			  url: 'mantenedor.do?toMethod=setActualizaEquipo',
			  data: formData,
			  dataType: 'json',
			  success: function( resp ) {
				popupNotification.show("El equipo de trabajo fue guardado", "info");
					$.ajax({
					  url: 'acceso.do?toMethod=getMenu',
					  data: "",
					  dataType: 'html',
					  success: function( resp ) {
					  	$("#menu").html(resp);
					  },
					  error: function( req, status, err ) {
						popupNotification.show("Error al cargar menu : " + err, "error");
					  }
					});		
			  },
			  error: function( req, status, err ) {
				console.log( 'Error al guardar tarea' + err, status, err );					
				popupNotification.show("Error al guardar equipos: " + err , "error");
			  }
			});					
		}
	}
	
	function validacion(){
		var estado = true;
		var jefe = $("#jefe").data("kendoDropDownList").value();
		var analistas = $("#analistas").data("kendoMultiSelect").value();
		
		if(jefe.length==0){
			alert("Favor de ingresar jefe de proyectos.");
			estado = false;
		}
/*		
		if(analistas.length==0){
			alert("Favor de ingresar analistas del equipo de trabajo.");
			estado = false;
		}
*/		
		return estado;
	}
	
});
</script>
</body>
</html>