<%@ page import="com.gotoque.torotask.presentacion.struts.Utilidades.JSONObject" %>
<%@ page import="com.gotoque.torotask.presentacion.struts.Utilidades.JSONArray" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%
JSONArray proyectos = (JSONArray)request.getAttribute("proyectos");
%>
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
                            <li id="tab1">
                                Calendario
                            </li>
                            <li id="tab2">
                                Reporte
                            </li>
                            <li id="tab3" class="k-state-active">
                                Gantt
                            </li>
                            <li id="tab4">
                                Recursos
                            </li>
                        </ul>
                        <div style="height:645px"></div>
                        <div style="height:645px"></div>
                        <div style="height:645px"><div id="gantts"></div></div>
                        <div style="height:645px"></div>
                    </div>
                </div>
            </div>
            <span id="popupNotification"></span>
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

					var ganttDataSource = new kendo.data.GanttDataSource({
							batch: false,
                            transport: {
								read: {
									url: "distribuidor.do?toMethod=getConsultarGantt",
									dataType: "json"
								},
							},
							schema: {
								model: {
									id: "id",
									fields: {
										id: { from: "id", type: "number" },
										orderId: { from: "id", type: "number", validation: { required: true } },
										parentId: { from: "idproyecto", type: "number", defaultValue: null, validation: { required: true } },
										start: { from: "fecha_inicio", type: "date" },
										end: { from: "fechaTerminoEstimada", type: "date" },
										title: { from: "tarea", defaultValue: "", type: "string" },
										percentComplete: { from: "porcentaje", type: "number" },
										summary: { from: "summary", type: "boolean" },
										expanded: { from: "expanded", type: "boolean", defaultValue: true }
									}
								}
							}
					});
					$("#gantts").kendoGantt({
                        dataSource: ganttDataSource,
						editable: false,	
	                    views: [
	                        "day",
	                        { type: "week", selected: true },
	                        "month"
	                    ],
	                    columns: [
	                        { field: "id", title: "ID", width: 50 },
	                        { field: "title", title: "Titulo", editable: false, sortable: true },
	                        { field: "start", title: "Inicio", format: "{0:MM/dd/yyyy}", width: 100, editable: false, sortable: true },
	                        { field: "end", title: "Termino", format: "{0:MM/dd/yyyy}", width: 100, editable: false, sortable: true }
	                    ],
	                    height: 550,
	
	                    showWorkHours: false,
	                    showWorkDays: false,
	
	                    snap: false
	                });	
                    
                });		
            </script>
        </div>
    </body>
</html>