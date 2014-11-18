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
								<% 
								for(int i = 0;i<proyectos.length();i++){
									JSONObject proyecto = (JSONObject)proyectos.getJSONObject(i);

								%>							
								<li>
									<%=proyecto.getString("idproyectos") + ".-" + proyecto.getString("proyecto")%>
								</li>
								<%}%>
								<li>
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
                            <li id="tab1" class="k-state-active">
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
                        </ul>
                        <div style="height:645px">
                            <div id="scheduler"></div>
                        </div>
                        <div style="height:645px"></div>
                        <div style="height:645px"></div>
                        <div style="height:645px"></div>
                    </div>
                </div>
            </div>
            <span id="popupNotification"></span>
            <iframe id="iframeprint" width="0" height="0"></iframe>
            <input type="hidden" id="idtarea"/>
            <script>

		$(function(){		
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
                            	document.location.href="reportes.do?tipo=p&id=<%=proyecto.getString("idproyectos")%>";
                            	
								break;
						<%}%>						
							case "Tareas Activas" :						
	                            	document.location.href="reportes.do?tipo=t&id=TA";
								break;
							case "Tareas Cerradas" :						
	                            	document.location.href="reportes.do?tipo=t&id=TC";
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
					
					var formatoFecha = "yyyy/MM/dd hh:mm tt";
					
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
						parseFormat:"yyyy/MM/dd HH:mm:ss",
					}).data("kendoDateTimePicker");
				
					var end = $("#fechaTermino").kendoDateTimePicker({
						format: formatoFecha,
						parseFormat:"yyyy/MM/dd HH:mm:ss",
					}).data("kendoDateTimePicker");
				
					function guardar() {
							if(validacion()){
								var estado = "off";
								if($("#estado").is(":checked")){
									estado = "on";
								}
								var formData = "idproyecto="+$("#proyecto").val()+
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
						comboProyecto.value("");
						$("#tarea").val("");
						$("#descripcion").val("");
						$("#comentario").val("");
						$("#val_tarea").css("visibility", "hidden");
						$("#val_descripcion").css("visibility", "hidden");
						$("#val_integrantes").css("visibility", "hidden");
						$("#val_fechas").css("visibility", "hidden");
					}
				
					function actualizaScheduler(){
						var calendario = $("#scheduler").data("kendoScheduler");
						calendario.dataSource.read();
						calendario.refresh();
					}					
				
					function scheduler_edit(e) {			
						limpiarFormulario();
						$("#idtarea").val(e.event.meetingID);
						$("#tarea").val(e.event.title);
						comboProyecto.value(e.event.idproyecto);
						$("#descripcion").val(e.event.description);
						start.value(new Date(kendo.toString(e.event.start, formatoFecha)));
						end.value(new Date(kendo.toString(e.event.end, formatoFecha)));
						llenarComentario(e.event.meetingID);
						llenarArchivos(e.event.meetingID);
						llenarIntegrantes(e.event.meetingID);
						tempVentanaTarea.open();
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
						actualizaScheduler();
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
					
					var dataSourceCalendario = new kendo.data.SchedulerDataSource({
							batch: true,
							transport: {
								read: {
									url: "calendario.do?toMethod=getConsultarTarea",
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
						timezone:"Etc/UTC",
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
                });		
            </script>
        </div>
    </body>
</html>
<jsp:include page="jsp/include/window.jsp" />