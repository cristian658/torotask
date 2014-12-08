<h3>
Mantenedor de Horario.
</h3>
<div id="grilla"></div>
<script>

$(document).ready(function() {

	var grilla = $("#grilla").kendoGrid({
		dataSource: {
			transport: {
				read: {
					url: "mantenedor.do?toMethod=getConsultaHorarios",
					dataType: "json"
					},
					update: {
					   url: "mantenedor.do?toMethod=getActualizaHorarios",
					   dataType: "json"
					},
					parameterMap: function(options, operation) {
					   if (operation !== "read" && options.models) {
						   return {models: kendo.stringify(options.models)};
					   }
				   }
				},
				schema: {
				data: "horarios",
				total : "total",
				model: {
					id: "idDia",
					fields			: {
					idDia			: { from: "idDia", editable: false, nullable: true },
					dia				: { from: "dia",editable: false, nullable: true},
					inicio1			: { from: "inicio1",validation: { required: true}},
					termino1		: { from: "termino1",validation: { required: true}},
					inicio2			: { from: "inicio2",validation: { required: true}},
					termino2		: { from: "termino2",validation: { required: true}},
					estadoVO		: { defaultValue: { idEstado : 1, estado: "Activo"}},
					}
				}
			},
			batch: true,
			pageSize: 20		
		},
		height: 500,
		sortable: true,
		selectable: "row",
		pageable: true,
		columns: [
			{ field: "idDia", title: "ID",width: "30px",},
			{ field: "dia", title: "Dia",width: "110px"},
			{ field: "inicio1",format:"{0:HH:mm}", title: "Inicio", width: "100px",editor: dateEditor},
			{ field: "termino1",format:"{0:HH:mm}", title: "Termino", width: "100px",editor: dateEditor},
			{ field: "inicio2",format:"{0:HH:mm}", title: "Inicio", width: "100px",editor: dateEditor},
			{ field: "termino2",format:"{0:HH:mm}", title: "Termino", width: "100px",editor: dateEditor},
			{ field: "estadoVO", title: "Estado", width: "120px", editor: estadoDropDownEditor, template: "#=estadoVO.estado#" },
			{ command: ["edit",], title: " ", width: "180px" }
		],
		editable: "inline"
	}).data("kendoGrid");
	
	function dateEditor(container, options) {
    $('<input data-text-field="' + options.field + '" data-value-field="' + options.field + '" data-bind="value:' + options.field + '" data-format="' + options.format + '"/>')
            .appendTo(container)
            .kendoTimePicker({});
	}
	
	function estadoDropDownEditor(container, options) {
	$('<input required data-text-field="estado" data-value-field="idEstado" data-bind="value:' + options.field + '"/>')
		.appendTo(container)
		.kendoDropDownList({
			autoBind: false,
			dataSource: {
				transport: {
					read: {
						url: "mantenedor.do?toMethod=getConsultaEstados",
						dataType: "json"
						}
					}
				}
		});
	}

})					
</script>
