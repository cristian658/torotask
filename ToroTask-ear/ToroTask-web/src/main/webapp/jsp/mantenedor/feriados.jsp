<h3>
Mantenedor de Feriados
</h3>
<div id="grilla"></div>
<script>

$(document).ready(function() {

	var grilla = $("#grilla").kendoGrid({
		dataSource: {
			transport: {
				read: {
					url: "mantenedor.do?toMethod=getConsultaFeriados",
					dataType: "json"
					},
					update: {
					   url: "mantenedor.do?toMethod=getActualizaFeriados",
					   dataType: "json"
					},
					destroy: {
					   url: "mantenedor.do?toMethod=getEliminaFeriados",
					   dataType: "json"
					},
					create: {
					   url: "mantenedor.do?toMethod=getCreaFeriados",
					   dataType: "json"
					},
					parameterMap: function(options, operation) {
					   if (operation !== "read" && options.models) {
						   return {models: kendo.stringify(options.models)};
					   }
				   }
				},
				schema: {
				data: "feriados",
				total : "total",
				model: {
					id: "idCalendario",
					fields			: {
					idFeriado		: { from: "idCalendario", editable: false, nullable: true },
					fecha			: { from: "fecha",validation: { required: true}},
					motivo			: { from: "motivo", validation: { required: true} },
					estadosVO		: { defaultValue: { idEstado : 1, estado: "Activo"}},
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
		toolbar: ["create"],
		columns: [
			{ field: "idFeriado", title: "ID",width: "30px",},
			{ field: "fecha", title: "Fecha",width: "110px",format:"{0:yyyy-MM-dd}",editor: dateEditor},
			{ field: "motivo", title: "Motivo", width: "100px"},
			{ field: "estadosVO", title: "Estado", width: "120px", editor: estadoDropDownEditor, template: "#=estadosVO.estado#" },
			{ command: ["edit",], title: " ", width: "180px" }
		],
		editable: "inline"
	}).data("kendoGrid");
	
	function dateEditor(container, options) {
    $('<input data-text-field="' + options.field + '" data-value-field="' + options.field + '" data-bind="value:' + options.field + '" data-format="' + options.format + '"/>')
            .appendTo(container)
            .kendoDatePicker({});
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
