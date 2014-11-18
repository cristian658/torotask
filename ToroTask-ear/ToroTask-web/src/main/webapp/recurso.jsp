<jsp:directive.page import="com.gotoque.torotask.presentacion.struts.Utilidades.JSONObject"/>
<jsp:directive.page import="com.gotoque.torotask.presentacion.struts.Utilidades.JSONArray"/><!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%
JSONArray recursos = (JSONArray)request.getAttribute("tareas");
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
        <script src="js/kendo.all.min.js"></script>
		<style>
			a:hover {
				border: none;
				color: #FF4000;
				text-decoration: none;
			}
		</style>
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
                            <li id="tab3"> 
                                Gantt 
                            </li> 
                            <li id="tab4" class="k-state-active"> 
                                Recursos 
                            </li> 
                        </ul> 
                        <div style="height: 645px;"></div> 
                        <div style="height: 645px;"></div> 
                        <div style="height: 645px;"></div> 
                        <div style="height: 645px;"> 
							<br> 
							<br> 
								<input id="startReport" value="Enero 2014">
								<input id="endReport" value="Noviembre 2014">
								<button id="report">
									Consultar
								</button>
							<br>
							<br>
							<%
							for(int i = 0;i<recursos.length();i++){
								JSONObject recurso = recursos.getJSONObject(i);
								int activas = recurso.getInt("tareasActivas");
								int realizadas = recurso.getInt("tareasRealizadas");
								int atrazadas = recurso.getInt("tareasAtrazadas");
								int total = recurso.getInt("tareasTotal");
								
								int porActivas = (activas*100) / total;
								int porRealizadas = (realizadas*100) / total;
								int porAtrazadas = (atrazadas*100) / total;
								
								
								
							%>
							<div class="teamMate" style="border-bottom: 1px solid rgb(231, 231, 231); padding-top: 10px; padding-bottom: 10px;"> 
								<img src="img/sin-imagen.jpg"> 
								<div style="float: left; width: 300px;"> 
									<div style="font-size: 15px;">Nombre : <%=recurso.getString("nombre")%></div> 
									<div style="float: left; width: 75px;">Cargo  </div><div style="float: left; width: 225px;">: <%=recurso.getString("cargo")%></div> 
									<div style="float: left; width: 75px;">Correo </div><div style="float: left; width: 225px;">: <%=recurso.getString("correo")%></div> 
									<div style="float: left; width: 75px;">Fono   </div><div style="float: left; width: 225px;">: <%=recurso.getString("anexo")%></div> 
								</div> 
								<div style="float: left; width: 300px; position: relative; top: 18px;"> 
									<div style="float: left; width: 125px;">Tareas realizadas </div><div style="float: left; width: 175px;">: <%=realizadas%></div> 
									<div style="float: left; width: 125px;">Tareas activas 	  </div><div style="float: left; width: 175px;">: <%=activas%></div> 
									<div style="float: left; width: 125px;">Tareas atrazadas  </div><div style="float: left; width: 175px;">: <%=atrazadas%></div> 
									<div style="float: left; width: 125px;">Total  </div><div style="float: left; width: 175px;">: <%=total%></div> 
								</div>			 
								<div style="font-size: 15px; top: 30px; position: relative;"><a href="#" onclick="grafico(<%=porActivas%>,<%=porRealizadas%>,<%=porAtrazadas%>)" style="text-decoration: none;">Porcentaje de cumplimiento <%=100-(100 * recurso.getInt("tareasAtrazadas"))/recurso.getInt("tareasTotal")%>%</a></div>	 
							</div> 
							<%}%> 
						</div> 
                    </div> 
                </div> 
            </div>
            <div id="WindowUpload">
				<div id="chart"></div>
			</div>
            <span id="popupNotification"></span>
            <iframe id="iframeprint" width="0" height="0"></iframe>
            <script>
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
                    					
					function grafico(activas,realizadas,atrazadas) {
						createChar(activas,realizadas,atrazadas);
						tempVentanaTarea.open();   
					}
						
					var tempVentanaTarea = $("#WindowUpload").kendoWindow({
						actions: ["Maximize", "Close"],
						minWidth: 800,
						minHeight :470,
						modal: true,
						resizable: true,
						title: "Informacion de tarea",
						visible: false
					}).data("kendoWindow").center();
					
					var formatoFechaReport = "MMMM yyyy";
					
					var startReport = $("#startReport").kendoDatePicker({
						format: formatoFechaReport,
						depth:"year",
						start:"year",
					}).data("kendoDatePicker");
				
					var endReport = $("#endReport").kendoDatePicker({
						format: formatoFechaReport,
						depth:"year",
						start:"year",                
					}).data("kendoDatePicker");	
					
					$("#report").kendoButton({
						click:consultarReporte
					})										
					
					function consultarReporte(e){

					}
					function createChar(activas,realizadas,atrazadas){
						$("#chart").kendoChart({
							title: {
								text: "Estado de Tareas acumuladas."
							},
							legend: {
							   position: "top"
							},
							seriesDefaults: {
								labels: {
									template: "#= category # - #= kendo.format('{0:P}', percentage)#",
									position: "outsideEnd",
									visible: true,
									background: "transparent"
								}
							},
							series: [{
								type: "donut",
								data: [{
									category: "Realizador",
									value: realizadas
								}, {
									category: "Activas",
									value: activas
								}, {
									category: "Atrazadas",
									value: atrazadas
								}]
							}],
							tooltip: {
								visible: true,
								template: "#= category # - #= kendo.format('{0:P}', percentage) #"
							}
						});
					}
            </script>
        </div>
    </body>
</html>