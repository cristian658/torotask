<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<meta http-equiv="content-type" content="text/html; charset=UTF-8" />
		<title>ASANA</title>
		<link href="styles/examples-offline.css" rel="stylesheet">
		<link href="styles/kendo.common.min.css" rel="stylesheet">
		<link href="styles/kendo.default.min.css" rel="stylesheet">
		<link href="styles/kendo.dataviz.default.min.css" rel="stylesheet" />
		<link href="styles/kendo.dataviz.min.css" rel="stylesheet" />
		<link href="styles/kendo.rtl.min.css" rel="stylesheet">
		<script src="js/jquery.min.js"></script>
		<script src="js/angular.min.js"></script>
		<script src="js/kendo.web.min.js"></script>
	</head>
	<body>
		<div id="WindowUpload">
			<ul id="menu">
				<li>
					Archivo
					<ul>
						<li>Guardar</li>
						<li>Terminar</li>
						<li>Eliminar</li>
						<li>Salir</li>
					</ul>
				</li>
				<li>Enviar Correo</li>
				<li>Imprimir</li>
			</ul>
			<div class="k-edit-form-container">			
			<form id="actividad">
				<div class="k-editor-field" style="height: 30px;">
					<input id="proyecto" placeholder="Proyecto"  style="width: 620px;height: 28px;"/>
				</div>
				<div class="k-editor-field">
					<input type="checkbox" id="estado"/><input class="editor-input" style="width: 600px;" id="tarea" placeholder="Tarea"/>
				</div>
				<div class="k-editor-field">
					<textarea class="k-textbox" id="descripcion" style="margin: 2px 0px;width: 764px;height: 95px;border-width: 2px;"  placeholder="Descripcion"></textarea>
				</div>
				<div class="k-editor-field">
					Fecha de Inicio : <input id="fechaInicio" />
					&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; 
					Fecha de Termino : <input id="fechaTermino" />
				</div>
				<div class="k-editor-field">
					<input id="integrantes" /><input type="hidden" id="idtarea" />
				</div>
				<div id="tabstrip-tarea" style="height:200px; width:786px">
					<ul>
						<li class="k-state-active">
							Comentario
						</li>
						<li>
							Historial
						</li>
						<li>
							Adjuntos
						</li>
					</ul>
					<div style="height:150px; width:753px">
						<textarea class="k-textbox" id="comentario" style="margin: 2px 0px;width: 756px;height: 144px;" placeholder="Escribe un comentario ..."></textarea>
					</div>
					<div style="height:150px; width:753px">
						<div id="grid_comentarios"></div>
					</div>
					<div id="contenedor-files" style="height:150px; width:753px">
						<div id="div_file"></div>
					</div>
				</div>
				<div class="k-edit-buttons k-state-default">
					<button class="k-button" type="submit">Guardar</button>
					<button  id="cerrarVentarTarea" class="k-button k-scheduler-cancel" type="button">Cancelar</button>
				</div>
			</div>
		</div>
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
					<ul id="panelbar">
						<li class="k-state-active">
							<span class="k-link k-state-selected"><img
									src="img/contacticon_client.png" width="20px">Personal</span>
							<div style="padding: 10px;">
								<div class="teamMate">
									<img src="img/sin-imagen.jpg">
									<h2>
										Esteban Toro
									</h2>
									<div style=" color: white;">
										Ingeniero Informatico
									</div>
								</div>
								<div class="teamMate">
									<img src="img/sin-imagen.jpg">
									<h2>
										Cristian Quezada
									</h2>
									<div style=" color: white;">
										Ingeniero Informatico
									</div>
								</div>
							</div>
						</li>
						<li>
							<img src="img/icon_project.png" width="20px">
							Projectos
							<ul>
								<li>
									P:125-Herramientas de tareas.
								</li>
								<li>
									P:136-Pulso de Panico.
								</li>
							</ul>

						</li>
						<li>
							<img src="img/dashtasksicon.png" width="20px">
							Tareas
							<ul>
								<li>
									T:A-Tareas Activas
								</li>
								<li>
									T:C-Tareas Cerradas
								</li>
							</ul>
						</li>
						<li disabled="disabled">
							<img src="img/helpicon_on.png">
							Ayuda.
						</li>
					</ul>
				</div>
			</div>
			<div id="cuerpo">
				<div id="headerleftcontainer" style="color: rgba(255,255,255,0.65);">
					<div id="headerdetailsdatecontainer">
						<div id="headerdetailsdate">
							<span id="headerdetailsdateday">4</span> Oct
						</div>
						<div id="headerdetailstime">
							11:58 PM
						</div>
					</div>

					<div id="headerdetailstitle">
						<span id="headerdetailstitlespan" style="font-size: 27px;">Sitio
							de Tareas</span>
					</div>

					<div id="headerdetailsusercontainer">
						<div class="headerdetailsusertitle">
							Hola
							<span id="headerloggedinusersname"
								class="headerdetailsusercontainervalues headerdetailsusercontainervalueslink">Victor
								Gonzalez</span>.&nbsp;(Logout)
							<br>
							Perfil
							<span id="showinfo"
								class="headerdetailsusercontainervalues headerdetailsusercontainervalueslink">Administrador</span>
						</div>
					</div>
				</div>
				<div class="pane-content">
					<div id="tabstrip" style="height:690px">
						<ul>
							<li id="tab1" class="k-state-active">
								Calendario
							</li>
							<li id="tab2">
								Reporte
							</li>
						</ul>
						<div style="height:645px">
							<div id="scheduler"></div>
						</div>
						<div style="height:645px">
							<div style="padding: 10px 10px 10px 10px;">
								<label for="start">
									Inicio:
								</label>
								<input id="startReport" style="width: 200px" value="10/10/2011" />
								<label for="end">
									Termino:
								</label>
								<input id="endReport" style="width: 200px" value="10/10/2012" />
								&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
								<button id="textButton">
									Consultar
								</button>
								&nbsp;&nbsp;
								<button id="textButtonExportar">
									Exportar a Excel
								</button>
							</div>
							<div id="grilla-indicadores"></div>
						</div>
					</div>
				</div>
			</div>
			<span id="popupNotification"></span>
			<script>
			$(document).ready(function() {
				
				var formatoFecha = "yyyy-MM-dd HH:mm:ss";
				
				var popupNotification = $("#popupNotification").kendoNotification({allowHideAfter: 1000}).data("kendoNotification");
				
				var validator = $("#actividad").kendoValidator().data("kendoValidator");
				
				 $("#menu").kendoMenu({
                    select: onSelectMenu,
                });
				
				$("#startReport").kendoDatePicker();
				
				$("#endReport").kendoDatePicker();
				
				function onSelectMenu(e) {
                    if("Imprimir"==$(e.item).children(".k-link").text()){
						$(".btnPrint").printPage();
					}
                }
				
				function onSelectPanelBar(e) {
					var seleccion = $(e.item).find("> .k-link").text().trim().split(":");
					if(seleccion.length>1){
						$("#tabstrip").kendoTabStrip().data("kendoTabStrip").activateTab($("#tab2"));
						actualizaReporte();
					}
				}
				
				$("#integrantes").kendoMultiSelect({	
					placeholder:"Seleccione integrantes ...",
					dataTextField:"correo",
					dataValueField:"idUsuario",
					autoBind: true,
					dataSource:{
						transport:{
							read:{
							url :"distribuidor.do?toMethod=getConsultaIntegrantes",
							dataType:"json",
							data:{
								q: function(){
										return $("#integrantes").data("kendoMultiSelect").value();
									}
								}
							}
						}
					}
				});
						
				$("#proyecto").kendoAutoComplete({
					dataTextField:"proyecto",
					filter:"contains",
					minLength:3,
					dataSource:{
						transport:{
							read:{
							url :"distribuidor.do?toMethod=getConsultaProyectos",
							dataType:"json",
							data:{
								q: function(){
										return $("#proyecto").data("kendoAutoComplete").value();
									}
								}
							}
						}
					}
				});
				
				$("#rep_comentarios").kendoGrid({
					height: 200,
					sortable: true
				});
				
				$("#textButton").kendoButton({
					icon: "search",
					click: function(e){
						actualizaReporte();
					}
				});
			
				$("#textButtonExportar").kendoButton({
					icon: "ungroup",
					click: onClickExport
				});
				
				function onClickExport(e) {
                    window.location.href = "servlet/reporteExcel";
                }
				
				$("#panelbar").kendoPanelBar({
					expandMode: "single",
					select: onSelectPanelBar,
				});				
				
				$("#tabstrip").kendoTabStrip({
					animation:  {
						open: {
							effects: "fadeIn"
						}
					},
					select: onSelectTabStrip,
				});
				
				 function onSelectTabStrip(e) {
                    if("Calendario"==$(e.item).find("> .k-link").text().trim()){
						//actualizaScheduler();
					}else if("Reporte"==$(e.item).find("> .k-link").text().trim()){
						actualizaReporte();
					};
                }
				
				$("#tabstrip-tarea").kendoTabStrip({
					animation:  {
						open: {
							effects: "fadeIn"
						}
					}
				});

				var start = $("#fechaInicio").kendoDateTimePicker({
					format: formatoFecha,
				}).data("kendoDateTimePicker");

				var end = $("#fechaTermino").kendoDateTimePicker({
					format: formatoFecha,
				}).data("kendoDateTimePicker");
			
				$("#actividad").submit(function(event) {
					event.preventDefault();
						if(validacion()){
							var estado = "off";
							if($("#estado").is(":checked")){
								estado = "on";
							}
							var formData = "proyecto="+$("#proyecto").val()+
							"&tarea="+$("#tarea").val()+
							"&descripcion="+$("#descripcion").val()+
							"&comentario="+$("#comentario").val()+
							"&fechaInicio="+$("#fechaInicio").val()+
							"&fechaTermino="+$("#fechaTermino").val()+
							"&idtarea="+$("#idtarea").val()+
							"&estado="+estado+
							"&integrantes="+$("#integrantes").data("kendoMultiSelect").value();
							$.ajax({
							  url: 'distribuidor.do?toMethod=setActualizaTarea',
							  data: formData,
							  dataType: 'json',
							  success: function( resp ) {
								popupNotification.show("La Tarea se ha guardado con el numero <strong>" + resp.tareas + "</strong>", "info");
								actualizaScheduler();
							  },
							  error: function( req, status, err ) {
								console.log( 'Error al guardar tarea' + err, status, err );					
								popupNotification.show("Error al guardar tarea : " + err , "error");
							  }
							});					
							tempVentanaTarea.close();
						}
				});	
				
				function validacion(){
					var estado = true;
					var tarea = $("#tarea");
					var descripcion = $("#descripcion");
					var integrantes = $("#integrantes").data("kendoMultiSelect").value();
					if(tarea.val()==""){
						tarea.css("border-color","#ff0000")
						estado = false;
					}else{
						tarea.css("border-color","gainsboro")
					}
					if(descripcion.val()==""){
						descripcion.css("border-color","#ff0000")						
						estado = false;
					}else{
						descripcion.css("border-color","gainsboro")
					}
					if(integrantes.length==0){
						$("#integrantes").css("border-color","#ff0000")
						estado = false;
					}else{
						$("#integrantes").css("border-color","gainsboro")
					}
					return estado;
				}
				
				function limpiarFormulario(){
					$("#proyecto").val("");
					$("#tarea").val("");
					$("#descripcion").val("");
					$("#comentario").val("");
					$("#tarea").css("border-color","gainsboro");
					$("#descripcion").css("border-color","gainsboro");
					$("#integrantes").css("border-color","gainsboro");
				}
				
				function actualizaScheduler(){
					var calendario = $("#scheduler").data("kendoScheduler");
					calendario.dataSource.read();
					calendario.refresh();
				}
				
				function actualizaReporte(){
					var reporte = $("#grilla-indicadores").data("kendoGrid");
					reporte.dataSource.read();
					reporte.refresh();
				}
				
				function scheduler_edit(e) {			
					limpiarFormulario();
					$("#idtarea").val(e.event.meetingID);
					$("#tarea").val(e.event.title);
					$("#proyecto").val(e.event.proyecto);
					$("#descripcion").val(e.event.description);
					$("#fechaInicio").val(kendo.toString(e.event.start, formatoFecha));
					$("#fechaTermino").val(kendo.toString(e.event.end, formatoFecha));
					llenarComentario(e.event.meetingID);
					llenarArchivos(e.event.meetingID);
					llenarIntegrantes(e.event.meetingID);
					tempVentanaTarea.open();
				}
				
				function llenarIntegrantes(idtarea){
					$.ajax({
					  url: 'distribuidor.do?toMethod=getConsultaIntegrantesxTarea&idtarea='+idtarea,
					  dataType: 'json',
					  success: function( resp ) {
						$("#integrantes").data("kendoMultiSelect").value(resp);
					  },
					  error: function( req, status, err ) {
						
					  }
					});					
				}
				
				function llenarComentario(idtarea){
					$("#grid_comentarios").kendoGrid({
						dataSource: {
							transport: { 
								read: {
									url:"distribuidor.do?toMethod=getConsultarComentarios&idtarea="+idtarea,
									dataType: "json",
									cache: false                                	
								}
							},
							schema: {
								data: "comentarios",
								total: "total",
							},
							pageSize: 4,
						},
						navigatable: true,
						pageable: {
							refresh: true,
							pageSizes: true
						},
						selectable: 'row',
						height: 150,
						filterable: true,
						columns: [
								{field: "fecha",width: 120} , 
								{field: "comentario"} 
								]
					});
				}
				
				function llenarArchivos(idtarea){
					$(".k-upload").remove();
					$("#div_file").append("<input name='files[]' id='TheFile' type='file' multiple='multiple'/>");
					$.ajax({
					  url: 'distribuidor.do?toMethod=getConsultarAdjuntos&idtarea='+idtarea,
					  dataType: 'json',
					  success: function( resp ) {
						$("#TheFile").kendoUpload({
							async: {
								saveUrl: "distribuidor.do?toMethod=setUpLoad",
								autoUpload: true
							},
							files : resp
						}).data("kendoUpload");	
					  },
					  error: function( req, status, err ) {
						filesUpload = $("#files").kendoUpload({
							async: {
								saveUrl: "distribuidor.do?toMethod=setUpLoad",
								autoUpload: true
							}
						}).data("kendoUpload");
					  }
					});					
				}
				
				$("#cerrarVentarTarea").click(function () {
					actualizaScheduler();
					tempVentanaTarea.close();
				});

				var tempVentanaTarea = $("#WindowUpload").kendoWindow({
					actions: ["Maximize", "Close"],
					minWidth: 800,
					minHeight :470,
					modal: true,
					resizable: true,
					title: "Informacion de tarea",
					visible: false
				}).data("kendoWindow").center();
				
				var dataSourceCalendario = new kendo.data.SchedulerDataSource({
						batch: true,
						transport: {
							read: {
								url: "distribuidor.do?toMethod=getConsultarTarea",
								dataType: "json"
							},
						},
						schema: {
							data: "tareas",
							model: {
								id: "idtarea",
								fields: {
									meetingID: { from: "idtarea", type: "number" },
									proyecto: { from: "proyecto", validation: { required: true} },
									title: { from: "tarea", validation: { required: true} },
									start: { type: "date", from: "fecha_inicio" },
									end: { type: "date", from: "fechaTerminoEstimada" },
									description: { from: "descripcion" },
								}
							}
						}
					});
				
				
				$("#scheduler").kendoScheduler({
					date: new Date(),
					startTime: new Date("2014-10-11 08:00:00"),
					endTime: new Date("2014-10-11 23:00:00"),
					height: 640,
					selectable: true,
					views: [
						"week",
						{ type: "month", selected: true },
						"agenda"
					],
					dataSource : dataSourceCalendario,
					editable: true,
					edit : scheduler_edit
					});	
					actualizaScheduler();
					
					
					$("#grilla-indicadores").kendoGrid({
                        dataSource: {
                            transport: {
								read: {
									url: "distribuidor.do?toMethod=getConsultarTarea&inicio"+$("#startReport").val()+"&termino"+$("#endReport").val(),
									dataType: "json"
								},
							},
							schema: {
								data: "tareas",
								total : "total",
								model: {
									id: "meetingID",
									fields: {
									meetingID: { from: "idtarea", type: "number" },
									proyecto: { from: "proyecto", validation: { required: true} },
									title: { from: "tarea", validation: { required: true} },
									start: { type: "date", from: "fecha_inicio" },
									end: { type: "date", from: "fechaTerminoEstimada" },
									cierre: { type: "date", from: "fechaTerminoReal" },
									description: { from: "descripcion" },
									}
								}
							},
                            pageSize: 20
                        },
						resources: [
							{
							  field: "meetingID",
							  dataSource: [
								{ text: "Small meeting room", value: 3, key: "#aabbcc" },
								{ text: "Big meeting room", value: 2, key: "green" }
							  ]
							}
						],
                        height: 550,
                        sortable: true,
						selectable: "row",
						change: function (e) {
							var selectedRows = this.select();
							var dataItem = this.dataItem(selectedRows[0]);
							limpiarFormulario();
							$("#idtarea").val(dataItem.meetingID);
							$("#tarea").val(dataItem.title);
							$("#proyecto").val(dataItem.proyecto);
							$("#descripcion").val(dataItem.description);
							$("#fechaInicio").val(kendo.toString(dataItem.start, formatoFecha));
							$("#fechaTermino").val(kendo.toString(dataItem.end, formatoFecha));
							llenarComentario(dataItem.meetingID);
							llenarArchivos(dataItem.meetingID);
							llenarIntegrantes(dataItem.meetingID);
							tempVentanaTarea.open();						  
					    },
					    pageable: true,
						columns: [
                            { field: "meetingID", title: "ID",width: "30px" },
                            { field: "proyecto", title: "Proyecto",width: "120px" },
                            { field: "title", title: "Tarea",width: "200px" },
                            { field: "start", title: "Fecha Inicio", format: "{0:dd/MM/yyyy hh:mm}", width: "110px" },
                            { field: "end", title: "Fecha Estimada", format: "{0:dd/MM/yyyy hh:mm}", width: "110px" },
                            { field: "cierre", title: "Fecha Real", format: "{0:dd/MM/yyyy hh:mm}", width: "110px" },
                        ]
                    });

					function detailInit(e) {
						var detailRow = e.detailRow;
						detailRow.find(".tabstrip").kendoTabStrip({
							animation: {
								open: { effects: "fadeIn" }
							}
						});
						detailRow.find(".comentarios_g").kendoGrid({
							dataSource: {
								type: "json",
								transport: {
									read: {
										url:"distribuidor.do?toMethod=getConsultarComentarios&idtarea="+e.data.meetingID,
										dataType: "json"
									},
								},
								schema: {
									data: "comentarios",
									total: "total",
								},
								pageSize: 3
								},
							scrollable: false,
							sortable: true,
							pageable: true,
							columns: [
								{ field: "fecha", title:"Fecha", width: "100px" },
								{ field: "comentario", title: "Comentario" }
							]
						});
					}
					
				});					
            </script>
			<style scoped>
				html{
				 background :#4a4d54;
				}
				#columna {
					float: left;
					width: 22%;
					height: 750px;
				}

				#cuerpo {
					float: left;
					width: 77%;
					height: 750px;
				}
			
			
                #vertical {
                    height: 750px;
                    width: 100%;
                }

                #left-pane, #center-pane, #right-pane  { background-color: rgba(60, 70, 80, 0.05); }

				#tabstrip {
                    width: 100%;
                }
				#tabstrip h2 {
                    font-weight: lighter;
                    font-size: 5em;
                    padding: 0;
                    margin: 0;
                }
                #tabstrip h2 span {
                    background: none;
                    padding-left: 5px;
                    font-size: .5em;
                    vertical-align: top;
                }

                #tabstrip p {
                    margin: 0;
                    padding: 0;
                }
				#special-days {
                    height: 500px;
                    width: 100%;
                    margin: 0;
                    padding: 0;
                    background: url('img/calendar-template.jpg') transparent no-repeat 0 bottom;
                }
                #calendar {
                    margin: 20px 0 0 265px;
                    width: 340px;
                    text-align: center;
                }
                #calendar .k-content {
                    height: 300px;
                }
                #calendar,
                #calendar .k-content,
                #calendar .k-header,
                #calendar th,
                #calendar .k-link,
                #calendar .k-state-hover,
                #calendar .k-state-selected,
                #calendar .k-state-focused {
                    background: transparent;
                    border-color: transparent;
                    color: #fff;
                    box-shadow: none;
                }
                #calendar .k-content .k-state-hover,
                #calendar .k-content .k-state-focused {
                    font-size: 14px;
                    font-weight: bold;
                }
                #calendar .k-state-selected, #calendar .k-state-selected.k-state-focused {
                    font-size: 24px;
                    font-weight: bold;
                }
                #calendar .k-content .k-link {
                    padding: 0;
                    min-height: 40px;
                    line-height: 40px;
                }
                #calendar th {
                    padding-top: 20px;
                    color: #8cbabf;
                }
                #calendar td.k-other-month .k-link {
                    color: #8cbabf;
                }
                #calendar th,
                #calendar td {
                    text-align: center;
                }

                /* Template Days */
                .exhibition, .party, .cocktail {
                        width: 40px;
                        height: 40px;
                        margin: auto;
                        -webkit-border-radius: 100px;
                        -moz-border-radius: 100px;
                        border-radius: 50%;
                        line-height: 40px;
                }
                .exhibition {
                    background-color: #fff;
                    color: #000;
                }
                .party {
                    background-color: #70c114;
                }
                .cocktail {
                    background-color: #00a1e8;
                }				
				.k-nav-current > .k-link span + span {
					max-width: 200px;
					display: inline-block;
					white-space: nowrap;
					text-overflow: ellipsis;
					overflow: hidden;
					vertical-align: top;
				}
				#team-schedule {
					height: 115px;
					position: relative;
				}
				.teamMate:after {
                    content: ".";
                    display: block;
                    height: 0;
                    line-height: 0;
                    clear: both;
                    visibility: hidden;
                }
                .teamMate p {
                    margin: 0 0;
					padding-top: 30px;
                }
                .teamMate img {
                    float: left;
                    margin: 5px 15px 5px 5px;
                    border: 1px solid #ccc;
                }			
				.fecha{
					padding-left: 10px;
					color: #999999;
				}
				.usuario{
					font-family: proxima-nova, "Helvetica Neue", Arial, sans-serif;
					color: #999999;
					font-size: 15px;					
				}
				.comentarios_g{
					border-top: 1px solid #e5e5e5;
					height: 150px; 
					overflow-y: scroll;
				}
				.comentarios{
					border-top: 1px solid #e5e5e5;
					width: 760px; 
					height: 150px; 
					overflow-y: scroll;
				}
				.k-upload-files{
					overflow-y: scroll;
					height: 80px;
				}
				.comentario{
					border-top: 1px solid #e5e5e5;
					background-color: #f5f9fc;
				}
				.bloque{
					padding: 10px 10px 10px 10px; 
					border-top: 1px solid #f5f9fc;
					background-color:#F7F8E0;
				}
				.k-editor-field{
					padding: 5px 5px 5px 10px;
					width: 760px;
				}
				.editor-input{
					color:#999999;
					width: 760px;
					border-radius: 4px;
					height: 20px;
					border-color: gainsboro;
				}			
				.k-edit-buttons{
					width: 760px;
				}	
				.well {
					min-height: 20px;
					padding: 19px;
					margin-bottom: 20px;
					background-color: #f5f5f5;
					border: 1px solid #e3e3e3;
					border-radius: 4px;
					-webkit-box-shadow: inset 0 1px 1px rgba(0,0,0,0.05);
					box-shadow: inset 0 1px 1px rgba(0,0,0,0.05);
				}
				#profile {
					position: relative;
				}
				.ra-well-title {
					font-size: 1.2857em;
					line-height: 1.2857em;
					border-bottom: 1px solid #e7e7e7;
					margin: -5px -19px 0.8333em;
					padding: 0 19px 0.7222em;
				}
				.row {
					margin-right: -15px;
					margin-left: -15px;
				}
				.select-estado{
					width: 80px;
				}
                .logo h2 {
                    font-size: 1.4em;
                    font-weight: normal;
                    padding-top: 20px;
                }
                .logo p {
                    margin: 5px 0;
                }
                .logo img {
                    float: left;
                    margin: 5px 15px 5px 5px;
                }
				.k-tab-on-top{
					color: #000;
				}
				.k-edit-form-container {
				position: relative;
				width: 780px;
				height: 520px;
				}
				
				#headerleftcontainer {
					display: table-cell;
					vertical-align: middle;
					background-color: #FF6600;
					width: 25%;
				}
				#headerdetailsdatecontainer {
					margin-left: 25px;
					width: 65px;
					float: left;
				}
				#headerdetailsdate {
					border-bottom: 1px solid rgba(255,255,255,0.7);
				}
				#headerdetailsdateday {
					font-size: 22px;
				}
				#headerdetailstime {
					width: 70px;
					height: 30px;
					text-align: center;
				}
				#headerdetailstitle {
					margin-left: 25px;
					margin-right: 10px;
					font-size: 28px;
					float: left;
					height: 50px;
					line-height: 50px;
					overflow: hidden;
					white-space: nowrap;
					border-right: 1px solid rgba(255,255,255,0.7);
					width: 240px;
				}
				#headerdetailsusercontainer {
					color: rgba(255,255,255,0.65);
					border-left: 1px solid rgba(255,255,255,0.7);
				}
				.headerdetailsusertitle {
					margin-top: 0;
				}
				.headerdetailsusercontainervalues, .headerdetailsusercontainervalueslink {
					color: rgba(255,255,255,1) !important;
					text-decoration: none;
				}				
				#headerdetailscontainer {
					color: rgba(255,255,255,0.65);
				}				
				.k-iconsa{
					background-image: url('img/icon_project.png');
				}
				#tabstrip-1{
					height: 645px !important;
				}
				#tabstrip-2{
					height: 645px !important;
				}
            </style>
		</div>


	</body>
</html>


