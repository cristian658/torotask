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
                            <li id="tab4">
                                Mantenedor
                            </li>
                        </ul>
                        <div style="height:645px"></div>
                        <div style="height:645px"></div>
                        <div style="height:645px"><div id="gantts"></div></div>
                        <div style="height:645px"></div>
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
                    
                });		
            </script>
        </div>
    </body>
</html>