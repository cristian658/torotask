<h3>
Mantenedor de Parametros
</h3>
<div id="grilla"></div>
<script>

$(document).ready(function() {

	var grilla = $("#grilla").kendoGrid({
		dataSource: {
			transport: {
				read: {
					url: "mantenedor.do?toMethod=getConsultaParametros",
					dataType: "json"
					},
					update: {
					   url: "mantenedor.do?toMethod=getActualizaParametros",
					   dataType: "json"
					},
					destroy: {
					   url: "mantenedor.do?toMethod=getEliminaParametros",
					   dataType: "json"
					},
					create: {
					   url: "mantenedor.do?toMethod=getCreaParametros",
					   dataType: "json"
					},
					parameterMap: function(options, operation) {
					   if (operation !== "read" && options.models) {
						   return {models: kendo.stringify(options.models)};
					   }
				   }
				},
				schema: {
				data: "parametros",
				total : "total",
				model: {
					id: "idParametro",
					fields			: {
					idParametro		: { from: "idParametro", editable: false, nullable: true },
					parametro		: { from: "parametro",validation: { required: true}},
					glosa			: { from: "glosa", validation: { required: true} },
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
			{ field: "idParametro", title: "ID",width: "30px",},
			{ field: "parametro", title: "parametro", width: "100px"},
			{ field: "glosa", title: "glosa", width: "120px"},
			{ command: ["edit"], title: " ", width: "180px"}
		],
		editable: "inline"
	}).data("kendoGrid");

})					
</script>
