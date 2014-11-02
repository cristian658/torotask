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
                <jsp:include page="menu.jsp" />
            </div>
            <div id="cuerpo">
                <jsp:include page="head.jsp" />
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
                        <div style="height:645px"></div>
                        <div style="height:645px"></div>
                        <div style="height:645px"></div>
                        <div style="height:645px">
                            <div class="teamMate" style="border-bottom: 1px solid #e7e7e7;padding-top: 10px;padding-bottom: 10px;">
                                <img src="img/sin-imagen.jpg">
                                <div style="float:left;width:300px">
                                    <div style="font-size: 15px;">Nombre : Esteban Toro</div>
                                    <div>Cargo : Ingenieiro de proyectos</div>
                                    <div>Correo : danileruleru@gmail.com</div>
                                    <div>Fono : 2-4137089</div>
                                </div>
                                <div style="float:left;width: 300px;position: relative;top: 15px;">
                                    <div>Tareas realizadas : 12</div>
                                    <div>Tareas activas : 2</div>
                                    <div>Tareas atrazadas : 1</div>
                                </div>			
                                <div style="font-size: 15px;top: 30px;position: relative">
                                    <a href="#" onclick="grafico(15)" style="text-decoration: none;">Porcentaje de cumplimiento 68%</a>
                                </div>	
                            </div>
                            <div class="teamMate" style="border-bottom: 1px solid #e7e7e7;padding-top: 10px;padding-bottom: 10px;">
                                <img src="img/sin-imagen.jpg">
                                <div style="float:left;width:300px">
                                    <div style="font-size: 15px;">Nombre : Criatian Quezada</div>
                                    <div>Cargo : Ingenieiro de proyectos</div>
                                    <div>Correo : quezadaCristian@gmail.com</div>
                                    <div>Fono : 2-4137066</div>
                                </div>
                                <div style="float:left;width: 300px;position: relative;top: 15px;">
                                    <div>Tareas realizadas : 18</div>
                                    <div>Tareas activas : 1</div>
                                    <div>Tareas atrazadas : 4</div>
                                </div>			
                                <div style="font-size: 15px;top: 30px;position: relative">
                                    <a href="#" onclick="grafico(45)" style="text-decoration: none;">Porcentaje de cumplimiento 79%</a>
                                </div>	
                            </div>		
                        </div>
                    </div>
                </div>
            </div>
            <div id="WindowUpload">
                <div id="chart"></div>
            </div>
            <br>
            <br>
            <span id="popupNotification"></span>
            <script>
                $(document).ready(function() {
				
                    $("#panelbar").kendoPanelBar({
                        expandMode: "single",
                        select: onSelectPanelBar,
                    });
				
                    function onSelectPanelBar(e) {
                        var seleccion = $(e.item).find("> .k-link").text().trim();
                        if(seleccion=="Projectos"){
                            document.location.href="reporte.jsp";
                        }else if(seleccion=="Q1 XXXXXXXXX"){
                            document.location.href="reporte.jsp";
                        }else if(seleccion=="Q2 XXXXXXXXX"){
                            document.location.href="reporte.jsp";
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
                            document.location.href="calendario.jsp";
                        }else if(accion=="Reporte"){
                            document.location.href="reporte.jsp";
                        }else if(accion=="Gantt"){
                            document.location.href="gantt.jsp";
                        }else if(accion=="Recursos"){
                            document.location.href="recursos.jsp";
                        }
                    }
				
                });		
                var tempVentanaTarea;
                function grafico(e) {
                    tempVentanaTarea.open();   
                }
				
                tempVentanaTarea = $("#WindowUpload").kendoWindow({
                    actions: ["Maximize", "Close"],
                    minWidth: 800,
                    minHeight :470,
                    modal: true,
                    resizable: true,
                    title: "Informacion de tarea",
                    visible: false
                }).data("kendoWindow").center();
				

				
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
                                    value: 70
                                }, {
                                    category: "Activas",
                                    value: 20
                                }, {
                                    category: "Atrazadas",
                                    value: 10
                                }]
                        }],
                    tooltip: {
                        visible: true,
                        template: "#= category # - #= kendo.format('{0:P}', percentage) #"
                    }
                });
            </script>
        </div>	
    </body>
</html>



