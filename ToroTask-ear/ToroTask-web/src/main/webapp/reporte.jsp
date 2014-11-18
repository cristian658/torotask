<%@ page import="com.gotoque.torotask.presentacion.struts.Utilidades.JSONObject" %>
<%@ page import="com.gotoque.torotask.presentacion.struts.Utilidades.JSONArray" %>
<%
JSONArray proyectos = (JSONArray)request.getAttribute("proyectos");
%>
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
		<link href="styles/style.css" rel="stylesheet">
		<script src="js/jquery.min.js"></script>
		<script src="js/angular.min.js"></script>
		<script src="js/kendo.web.min.js"></script>
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
					<ul id="panelbar">
						<li>
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
						<li class="k-state-active">
							<img src="img/icon_project.png" width="20px">
							Projectos
							<ul>
								<% 
								for(int i = 0;i<proyectos.length();i++){
									JSONObject proyecto = (JSONObject)proyectos.getJSONObject(i);

								%>							
								<li>
									<%=proyecto.getString("idproyectos") + ".-" + proyecto.getString("proyecto")%>
								</li>
								<%}%>
								<li class="k-state-active">
									<img src="img/dashtasksicon.png" width="20px">
									Tareas
									<ul>
										<li>
											Tareas Activas
										</li>
										<li>
											Tareas Cerradas
										</li>
									</ul>
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
				<jsp:include page="jsp/include/head.jsp" />
				<div class="pane-content">
					<div id="tabstrip" style="height:690px">
						<ul>
							<li id="tab1">
								Calendario
							</li>
							<li id="tab2" class="k-state-active">
								Reporte
							</li>
							<li id="tab3">
								Gantt
							</li>
							<li id="tab4">
								Recursos
							</li>
						</ul>  
						<div style="height:645px"></div>
						<div style="height:645px">
							<div style="padding: 10px;">
								<input id="startReport"
									value="<%=request.getAttribute("fechaInicio")%>">
								<input id="endReport"
									value="<%=request.getAttribute("fechaTermino")%>">
								<button id="report">
									Consultar
								</button>
								<button id="export">
									Exportar
								</button>
							</div>
							<div id="grilla"></div>
						</div>
						<div style="height:645px"></div>
						<div style="height:645px"></div>
					</div>
				</div>
			</div>
			<span id="popupNotification"></span>
			<input type="hidden" id="id" value="<%=request.getAttribute("id")%>"/>
			<input type="hidden" id="tipo" value="<%=request.getAttribute("tipo")%>"/>
			<iframe id="iframeprint" width="0" height="0"></iframe>
			<script>
                $(document).ready(function() {
				
                    $("#panelbar").kendoPanelBar({
                        expandMode: "single",
                        select: onSelectPanelBar,
                    });
				
                    function onSelectPanelBar(e) {
                        var id = $(e.item).find("> .k-link").text().trim();
						switch (id){
					<% 
						for(int i = 0;i<proyectos.length();i++){
							JSONObject proyecto = (JSONObject)proyectos.getJSONObject(i);
					%>							
						case "<%=proyecto.getString("idproyectos") + ".-" +proyecto.getString("proyecto")%>" :
                        	var url="reportes.do?toMethod=getConsultarReporteTarea&tipo=p&id=<%=proyecto.getString("idproyectos")%>&inicio="+$("#startReport").val()+"&termino="+$("#endReport").val();
							actualizagrilla(url);
							break;
					<%}%>
						case "Tareas Activas" :						
                            	url="reportes.do?toMethod=getConsultarReporteTarea&tipo=t&id=TA&inicio="+$("#startReport").val()+"&termino="+$("#endReport").val();
								actualizagrilla(url);
							break;
						case "Tareas Cerradas" :						
                        	var url="reportes.do?toMethod=getConsultarReporteTarea&tipo=t&id=TC&inicio="+$("#startReport").val()+"&termino="+$("#endReport").val();
							actualizagrilla(url);
							break;
						default:
							break; 
						}
                    }
				
                    var tabStrip = $("#tabstrip").kendoTabStrip({
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
                        }
                    }
					
					var formatoFecha = "yyyy-MM-dd hh:mm tt";
					
					var popupNotification = $("#popupNotification").kendoNotification({allowHideAfter: 1000}).data("kendoNotification");
					
					var validator = $("#actividad").kendoValidator().data("kendoValidator");
					
					 var menu = $("#menu").kendoMenu({
		                 select: onSelectMenu,
		             });
					
					function onSelectMenu(e) {
				        var accion = $(e.item).children(".k-link").text();
						if(accion == "Imprimir"){
							print("servlet/getTarea.pdf?online=true&id="+$("#idtarea").val());
						}else if(accion == "Guardar"){
							guardar();
						}else if(accion == "Exportar"){
							document.location.href="servlet/getTarea.pdf?online=false&id="+$("#idtarea").val();
						}else if(accion == "Salir"){
							cerrarVentana();
						}
				    }
					
					function print(url){
						  var _this = this,
							  iframeId = 'iframeprint',
							  $iframe = $('iframe#iframeprint');
						  $iframe.attr('src', url);
					 
						  $iframe.load(function() {
							  var PDF = document.getElementById(iframeId);
							  PDF.focus();
							  PDF.contentWindow.print();
						  });
					  }
					 
					
					$("#tabstrip-tarea").kendoTabStrip({
						animation:  {
							open: {
								effects: "fadeIn"
							}
						}
					});
					
					$("#integrantes").kendoMultiSelect({	
						placeholder:"Seleccione integrantes ...",
						dataTextField:"correo",
						dataValueField:"idUsuario",
						autoBind: true,
						dataSource:{
							transport:{
								read:{
								url :"calendario.do?toMethod=getConsultaIntegrantes",
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
							
					var comboProyecto = $("#proyecto").kendoComboBox({
						dataTextField:"proyecto",
						dataValueField:"idproyectos",
						placeholder:"Seleccione proyecto ...",
						dataSource:{
							transport:{
								read:{
								url :"calendario.do?toMethod=getConsultaProyectos",
								dataType:"json",
								data:{
									q: function(){
											return $("#proyecto").data("kendoComboBox").value();
										}
									}
								}
							}
						}
					}).data("kendoComboBox");
					
					$("#rep_comentarios").kendoGrid({
						height: 200,
						sortable: true
					});
					
					var start = $("#fechaInicio").kendoDateTimePicker({
						format: formatoFecha,
					}).data("kendoDateTimePicker");
				
					var end = $("#fechaTermino").kendoDateTimePicker({
						format: formatoFecha,
					}).data("kendoDateTimePicker");
				
					function guardar() {
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
								  url: 'calendario.do?toMethod=setActualizaTarea',
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
					}
					
					function validacion(){
						var estado = true;
						var tarea = $("#tarea");
						var descripcion = $("#descripcion");
						var fechaInicio = $("#fechaInicio");
						var fechaTermino = $("#fechaTermino");
						
						var integrantes = $("#integrantes").data("kendoMultiSelect").value();
						if(tarea.val()==""){
							$("#val_tarea").css("visibility", "visible");
							estado = false;
						}else{
							$("#val_tarea").css("visibility", "hidden");
							}
						if(descripcion.val()==""){
							$("#val_descripcion").css("visibility", "visible");
							estado = false;
						}else{
							$("#val_descripcion").css("visibility", "hidden");
						}
						if(integrantes.length==0){
							$("#val_integrantes").css("visibility", "visible");
							estado = false;
						}else{
							$("#val_integrantes").css("visibility", "hidden");
						}
						
						if(fechaInicio.length==0 || fechaTermino == 0){
							$("#val_fechas").css("visibility", "visible");
						}else{
							$("#val_fechas").css("visibility", "hidden");
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
					
					function llenarIntegrantes(idtarea){
						$.ajax({
						  url: 'calendario.do?toMethod=getConsultaIntegrantesxTarea&idtarea='+idtarea,
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
										url:"calendario.do?toMethod=getConsultarComentarios&idtarea="+idtarea,
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
						  url: 'calendario.do?toMethod=getConsultarAdjuntos&idtarea='+idtarea,
						  dataType: 'json',
						  success: function( resp ) {
							$("#TheFile").kendoUpload({
								async: {
									saveUrl: "calendario.do?toMethod=setUpLoad",
									autoUpload: true
								},
								files : resp
							}).data("kendoUpload");	
						  },
						  error: function( req, status, err ) {
							filesUpload = $("#files").kendoUpload({
								async: {
									saveUrl: "calendario.do?toMethod=setUpLoad",
									autoUpload: true
								}
							}).data("kendoUpload");
						  }
						});					
					}
					
					function cerrarVentana() {
						tempVentanaTarea.close();
					};
				
					var tempVentanaTarea = $("#WindowUpload").kendoWindow({
						actions: [], 
						minWidth: 800,
						minHeight :470,
						modal: true,
						resizable: true,
						title: "Informacion de tarea",
						visible: false
					}).data("kendoWindow").center();
					
					var formatoFechaReport = "dd/MM/yyyy";
					
					var startReport = $("#startReport").kendoDatePicker({
						format: formatoFechaReport,
                        change: startChange
					}).data("kendoDatePicker");
				
					var endReport = $("#endReport").kendoDatePicker({
						format: formatoFechaReport,
                        change: endChange
					}).data("kendoDatePicker");	
					
					function startChange() {
                        var startDate = startReport.value(),
                        endDate = endReport.value();

                        if (startDate) {
                            startDate = new Date(startDate);
                            startDate.setDate(startDate.getDate());
                            endReport.min(startDate);
                        } else if (endDate) {
                            startReport.max(new Date(endDate));
                        } else {
                            endDate = new Date();
                            startReport.max(endDate);
                            endReport.min(endDate);
                        }
                    }

                    function endChange() {
                        var endDate = endReport.value(),
                        startDate = startReport.value();

                        if (endDate) {
                            endDate = new Date(endDate);
                            endDate.setDate(endDate.getDate());
                            startReport.max(endDate);
                        } else if (startDate) {
                            endReport.min(new Date(startDate));
                        } else {
                            endDate = new Date();
                            startReport.max(endDate);
                            endReport.min(endDate);
                        }
                    }

                    startReport.max(endReport.value());
                    endReport.min(startReport.value());

					$("#export").kendoButton({
						click:exportarReporte
					})	
					
					function exportarReporte(e){
						document.location.href="servlet/reporteExcel.xls";
					}
					
					$("#report").kendoButton({
						click:consultarReporte
					})										
					
					function consultarReporte(e){
						var url = "reportes.do?toMethod=getConsultarReporteTarea&id=&tipo=&inicio="+$("#startReport").val()+"&termino="+$("#endReport").val();
						actualizagrilla(url);
					}
					function consultarReporte(e){
						var url = "reportes.do?toMethod=getConsultarReporteTarea&id=&tipo=&inicio="+$("#startReport").val()+"&termino="+$("#endReport").val();
						actualizagrilla(url);
					}
					
					function actualizagrilla(url){
						grilla.dataSource.options.transport.read.url = url;
						grilla.dataSource.read();
						grilla.refresh();					
					}
					
					var gridDataSource = new kendo.data.DataSource({
                        transport: {
							read: {
								url: "reportes.do?toMethod=getConsultarReporteTarea&id="+$("#id").val()+"&tipo="+$("#tipo").val()+"&inicio="+$("#startReport").val()+"&termino="+$("#endReport").val(),
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
					});
					
					var grilla = $("#grilla").kendoGrid({
						dataSource: gridDataSource,
		                     height: 550,
		                     sortable: true,
							selectable: "row",
							change: function (e) {
								var selectedRows = this.select();
								var dataItem = this.dataItem(selectedRows[0]);
								limpiarFormulario();
								$("#idtarea").val(dataItem.meetingID);
								$("#tarea").val(dataItem.title);
								comboProyecto.value(dataItem.proyecto);
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
		                         { field: "start", title: "Fecha Inicio", format: "{0:dd/MM/yyyy hh:mm tt}", width: "110px" },
		                         { field: "end", title: "Fecha Estimada", format: "{0:dd/MM/yyyy hh:mm tt}", width: "110px" },
		                         { field: "cierre", title: "Fecha Real", format: "{0:dd/MM/yyyy hh:mm tt}", width: "110px" },
		                     ]
					}).data("kendoGrid");	
                });		
            </script>
		</div>
	</body>
</html>
<jsp:include page="jsp/include/window.jsp" />
