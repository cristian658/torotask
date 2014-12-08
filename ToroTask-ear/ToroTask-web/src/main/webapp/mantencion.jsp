<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
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
        <script src="js/kendo.web.min.js"></script>
        <script src="js/comun.js"></script>
    </head>
    <body>
        <div id="example">
            <div id="columna"> 
				<div class="pane-content">
					<div class="teamMate" style="background-color: #000;">
						<img src="img/logo.png" width="90px" style="border:#000;">
						<p style="color:#FF4000;font-size: 15px;">
							Herramienta de Gestion de Tareas
						</p>
						<h2 style="color:white;font-size: 25px;">
							TORO-TASK
						</h2>
					</div>
					<div id="menu"></div>
				</div>
            </div>
            <div id="cuerpo">
                <jsp:include page="jsp/include/head.jsp" />
                <div class="pane-content">
                    <div id="tabstrip2" style="height:690px">
                        <ul>
                            <li id="tab1">
                                Calendario
                            </li>
                            <li id="tab2">
                                Reporte
                            </li>
                            <li id="tab3">
                                Gantt
                            </li>
                            <li id="tab4">
                                Recursos
                            </li>
                            <li id="tab4" class="k-state-active">
                                Mantenedor
                            </li>
                         </ul>
                        <div style="height:645px"></div>
                        <div style="height:645px"></div>
                        <div style="height:645px"></div>
                        <div style="height:645px"></div>
                        <div style="height:645px">
                        
                        <ul id="menu2">	
                        	<li>Usuarios</li>
                        	<li>Equipos de trabajo</li>
                        	<li>Parametros</li>
                        	<li>Feriados</li>
                        	<li>Horarios</li>
                        </ul>
	                        <div id="window"></div>
                        </div>
                    </div>
                </div>
            </div>
            <span id="popupNotification"></span>
            <script>

		$(function(){		
					
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

                    var tabStrip = $("#tabstrip2").kendoTabStrip({
                        animation:  {
                            open: {
                                effects: "fadeIn"
                            }
                        },
                        select: onSelect,
                    }).data("kendoTabStrip");
					
                    function onSelect(e) {
                        var accion = $(e.item).find("> .k-link").text().trim();
                        if(accion=="Calendario"){
                            document.location.href="calendario.do";
                        }else if(accion=="Reporte"){
                            document.location.href="reportes.do";
                        }else if(accion=="Gantt"){
                            document.location.href="gantt.do";
                        }else if(accion=="Recursos"){
                            document.location.href="recursos.do";
                        }else if(accion=="Mantenedor"){
                            document.location.href="mantenedor.do";
                        }
                    }
					
					var formatoFecha = "yyyy/MM/dd hh:mm tt";
					
					var popupNotification = $("#popupNotification").kendoNotification({allowHideAfter: 1000}).data("kendoNotification");
					
					var validator = $("#actividad").kendoValidator().data("kendoValidator");
					
					 var menu = $("#menu2").kendoMenu({
		                 select: onSelectMenu,
		             });
					
					function onSelectMenu(e) {
				        var accion = $(e.item).children(".k-link").text();
						var url = "";
						if(accion == "Usuarios"){
							url = "jsp/mantenedor/usuarios.jsp";
						}else if(accion == "Equipos de trabajo"){
							url = "jsp/mantenedor/equipos.jsp";
						}else if(accion == "Parametros"){
							url = "jsp/mantenedor/parametros.jsp";
						}else if(accion == "Feriados"){
							url = "jsp/mantenedor/feriados.jsp";
						}else if(accion == "Horarios"){
							url = "jsp/mantenedor/horarios.jsp";
						}
						$.ajax({
						  url: url,
						  dataType: 'html',
						  success: function( resp ) {
							$("#window").html(resp);
						  },
						  error: function( req, status, err ) {
							console.log( 'Se ha generado un error en la llamada del mantenedor : ' + err, status, err );					
							popupNotification.show("Se ha generado un error en la llamada del mantenedor : " + err , "error");
						  }
						});	
				    }
				    

                });		
            </script>
        </div>
    </body>
</html>
