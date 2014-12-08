<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
    <head>
        <meta http-equiv="content-type" content="text/html; charset=UTF-8" />
        <title>TORO-TASK :: The evolution of software.</title>
        <link href="styles/examples-offline.css" rel="stylesheet">
        <link href="styles/kendo.common.min.css" rel="stylesheet">
        <link href="styles/kendo.default.min.css" rel="stylesheet">
        <link href="styles/kendo.dataviz.default.min.css" rel="stylesheet" />
        <link href="styles/kendo.dataviz.min.css" rel="stylesheet" />
        <link href="styles/kendo.rtl.min.css" rel="stylesheet">
        <link href="styles/style.css" rel="stylesheet">
        <script src="js/jquery.min.js"></script>
        <script src="js/kendo.all.min.js"></script>
        <script src="js/comun.js"></script>
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
					<div id="menu"></div>
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
                            <li id="tab4">
                                Mantenedor
                            </li>
                        </ul> 
                        <div style="height: 645px;"></div> 
                        <div style="height: 645px;"></div> 
                        <div style="height: 645px;"></div>
						<div style="height: 645px;">
							<br> 
							<br> 
								<input id="startReport" value="<%=request.getAttribute("inicio")%>">
								<input id="endReport" value="<%=request.getAttribute("termino")%>">
								<button id="report">
									Consultar
								</button>
							<br>
							<br>
 							<div style="height: 500px;width: 100%;overflow-y: scroll;">
 								<div id="resultado"></div>  								
 							</div> 
						</div> 
                        <div style="height:645px"></div>
                    </div> 
                </div> 
            </div>
            <div id="WindowGrafico">
				<div id="chart"></div>
			</div>
            <div id="WindowDetalle">
				<div id="grilla"></div>
			</div>
            <script>
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
                        }else if(accion=="Mantenedor"){
                            document.location.href="mantenedor.do";
                        }
                    }
                    					
					function grafico(user,activas,realizadas,atrazadas) {
						createChar(user,activas,realizadas,atrazadas);
						tempVentanaTarea.open();   
					}
						
					var tempVentanaTarea = $("#WindowGrafico").kendoWindow({
						actions: ["Maximize", "Close"],
						minWidth: 800,
						minHeight :470,
						modal: true,
						resizable: true,
						title: "Informacion de tarea",
						visible: false
					}).data("kendoWindow").center();
					
					var tempVentanaDetalle = $("#WindowDetalle").kendoWindow({
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
						var formData = "inicio="+startReport._oldText+"&termino="+endReport._oldText;
						$.ajax({
						 url: 'recursos.do?toMethod=getrRecursosDetalle',
						 data: formData,
						 dataType: 'html',
						 success: function( resp ) {
						 	$("#resultado").html(resp);
						 },
						 error: function( req, status, err ) {
							console.log( 'Error al guardar tarea' + err, status, err );					
						 }
						});	
					}
					
					function createChar(user,activas,realizadas,atrazadas){
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
							seriesClick: function(e) {
								var url = "recursos.do?toMethod=getConsultarReporteUsuario&idUsuario="+e.dataItem.usuario +"&categoria="+e.dataItem.category;
								actualizagrilla(url);
								tempVentanaDetalle.open();	
								if (e.originalEvent.type === "contextmenu") {
								  e.originalEvent.preventDefault();
								}
							},
							series: [{
								type: "donut",
								data: [{
									usuario : user,
									category: "Atrazadas",
									value: atrazadas
								},{
									usuario : user,
									category: "Realizadas",
									value: realizadas
								}, {
									usuario : user,
									category: "Activas",
									value: activas
								}]
							}],
							tooltip: {
								visible: true,
								template: "#= category # - #= kendo.format('{0:P}', percentage) #"
							}
						});
						function actualizagrilla(url){
							grilla.dataSource.options.transport.read.url = url;
							grilla.dataSource.read();
							grilla.refresh();					
						}
						var gridDataSource = new kendo.data.DataSource({
							transport: {
								read: {
									url: "reportes.do?toMethod=getConsultarReporteTarea&id=tipo=&inicio=&termino=",
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
						    pageable: true,
							columns: [
		                         { field: "meetingID", title: "ID",width: "30px",},
		                         { field: "proyecto", title: "Proyecto",width: "120px"},
		                         { field: "title", title: "Tarea",width: "200px"},
		                     ]
						}).data("kendoGrid");
						
					}
            </script>
        </div>
    </body>
</html>