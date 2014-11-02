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
                            <li id="tab3" class="k-state-active">
                                Gantt
                            </li>
                            <li id="tab4">
                                Recursos
                            </li>
                        </ul>
                        <div style="height:645px"></div>
                        <div style="height:645px"></div>
                        <div style="height:645px">
                            <div id="gantt"></div>
                        </div>
                        <div style="height:645px"></div>
                    </div>
                </div>
            </div>
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

                $("#gantt").kendoGantt({
                    dataSource: [
                        {
                            id: 1,
                            orderId: 0,
                            parentId: null,
                            title: "Task1",
                            start: new Date("2014/6/17 9:00"),
                            end: new Date("2014/6/17 11:00")
                        },
                        {
                            id: 2,
                            orderId: 1,
                            parentId: null,
                            title: "Task2",
                            start: new Date("2014/6/17 12:00"),
                            end: new Date("2014/6/17 14:00")
                        }
                    ],
                    dependencies: [
                        {
                            predecessorId: 1,
                            successorId: 2,
                            type: 1
                        }
                    ],
                    columns: ["title" , "start"]
                });
            </script>
        </div>


    </body>
</html>


