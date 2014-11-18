		<div id="WindowUpload">
			<ul id="menu">
				<li>Guardar</li>
				<li>Terminar</li>
				<li>Exportar</li>
				<li>Imprimir</li>
				<li>Salir</li>
			</ul>
			<div class="k-edit-form-container">			
			<form id="actividad">
				<div class="k-editor-field" style="height: 30px;">
					<input id="proyecto" placeholder="Proyecto"  style="width: 620px;height: 28px;"/>
				</div>
				<div class="k-editor-field">
					<input type="checkbox" id="estado"/>
					<input class="editor-input" style="width: 600px;" id="tarea" placeholder="Tarea"/>
					<div id="val_tarea" style="float:right;position: relative;top: -25px;display:none;z-index: 9999;">
						<span class="k-widget k-tooltip k-tooltip-validation k-invalid-msg" data-for="FirstName" role="alert" style=""><span class="k-icon k-warning"> </span> 
							Campo es requerido.
						</span>
					</div>
				</div>
				<div class="k-editor-field">
					<textarea class="k-textbox" id="descripcion" style="margin: 2px 0px;width: 623px;height: 95px;border-width: 2px;"  placeholder="Descripcion"></textarea>
					<div id="val_descripcion" style="float:right;top: -70px;position: relative;display:none;z-index: 9999;">
						<span class="k-widget k-tooltip k-tooltip-validation k-invalid-msg" data-for="FirstName" role="alert" style=""><span class="k-icon k-warning"> </span> 
							Campo es requerido.
						</span>
					</div>
				</div>
				<div class="k-editor-field">
					Fecha de Inicio : <input id="fechaInicio" />
					&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; 
					Fecha de Termino : <input id="fechaTermino" />
					<div id="val_fechas" style="float:right;display:none">
						<span class="k-widget k-tooltip k-tooltip-validation k-invalid-msg" data-for="FirstName" role="alert" style=""><span class="k-icon k-warning"> </span> 
							Campo es requerido.
						</span>
					</div>
				</div>
				<div class="k-editor-field">
					<input type="hidden" id="idtarea" />
					<input id="integrantes" style="width: 622px;"/>
					<div id="val_integrantes" style="float:right;position: relative;top: -24px;;display:none;z-index: 9999;">
						<span class="k-widget k-tooltip k-tooltip-validation k-invalid-msg" data-for="FirstName" role="alert" style=""><span class="k-icon k-warning"> </span> 
							Campo es requerido.
						</span>
					</div>
				</div>
				<div id="tabstrip-tarea" style="height:200px; width:786px">
					<ul>
						<li class="k-state-active">
							Comentario
						</li>
						<li>
							Historial
						</li>
						<li>
							Adjuntos
						</li>
					</ul>
					<div style="height:150px; width:753px">
						<textarea class="k-textbox" id="comentario" style="margin: 2px 0px;width: 756px;height: 144px;" placeholder="Escribe un comentario ..."></textarea>
					</div>
					<div style="height:150px; width:753px">
						<div id="grid_comentarios"></div>
					</div>
					<div id="contenedor-files" style="height:150px; width:753px">
						<div id="div_file"></div>
					</div>
				</div>
			</div>
		</div>
