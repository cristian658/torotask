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
					

                    var formatoFecha = "yyyy-MM-dd HH:mm:ss";
				
                    var popupNotification = $("#popupNotification").kendoNotification({allowHideAfter: 1000}).data("kendoNotification");
				
                    var validator = $("#actividad").kendoValidator().data("kendoValidator");
				
                    $("#menu").kendoMenu({
                        select: onSelectMenu,
                    });
				
                    function onSelectMenu(e) {
                        if("Imprimir"==$(e.item).children(".k-link").text()){
                            $(".btnPrint").printPage();
                        };
					
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
                });		

            </script>
        </div>


    </body>
</html>


