
<jsp:directive.page import="com.gotoque.torotask.presentacion.struts.Utilidades.JSONArray"/>
<jsp:directive.page import="com.gotoque.torotask.presentacion.struts.Utilidades.JSONObject"/><%
JSONArray recursos = (JSONArray)request.getAttribute("recursos");
for(int i = 0;i<recursos.length();i++){
	JSONObject recurso = recursos.getJSONObject(i);
	int activas = recurso.getInt("tareasActivas");
	int realizadas = recurso.getInt("tareasRealizadas");
	int atrazadas = recurso.getInt("tareasAtrazadas");
	int total = activas + realizadas + atrazadas; //recurso.getInt("tareasTotal");
	
	int porActivas = 0;
	int porRealizadas = 0;
	int porAtrazadas = 0;
	int cumplimiento = 0;
	
	if(total > 0){
		porActivas = (activas*100) / total;
		porRealizadas = (realizadas*100) / total;
		porAtrazadas = (atrazadas*100) / total;
		cumplimiento = 100-(100 * atrazadas)/total;
	}
%>
<div class="teamMate" style="border-bottom: 1px solid rgb(231, 231, 231); padding-top: 10px; padding-bottom: 10px;"> 
	<img width="70px" src="servlet/getImagen.jpg?id=<%=recurso.getString("idUsuario")%>"/> 
	<div style="float: left; width: 300px;"> 
		<div style="font-size: 15px;">Nombre : <%=recurso.getString("nombre")%></div> 
		<div style="float: left; width: 75px;">Cargo  </div><div style="float: left; width: 225px;">: <%=recurso.getString("cargo")%></div> 
		<div style="float: left; width: 75px;">Correo </div><div style="float: left; width: 225px;">: <%=recurso.getString("correo")%></div> 
		<div style="float: left; width: 75px;">Fono   </div><div style="float: left; width: 225px;">: <%=recurso.getString("anexo")%></div> 
	</div> 
	<div style="float: left; width: 300px; position: relative; top: 18px;"> 
		<div style="float: left; width: 125px;">Tareas realizadas </div><div style="float: left; width: 175px;">: <%=porActivas%></div> 
		<div style="float: left; width: 125px;">Tareas activas 	  </div><div style="float: left; width: 175px;">: <%=porRealizadas%></div> 
		<div style="float: left; width: 125px;">Tareas atrazadas  </div><div style="float: left; width: 175px;">: <%=porAtrazadas%></div> 
		<div style="float: left; width: 125px;">Total  </div><div style="float: left; width: 175px;">: <%=recurso.getInt("tareasTotal")%></div> 
	</div>			 
	<div style="font-size: 15px; top: 30px; position: relative;"><a href="#" onclick="grafico(<%=recurso.getString("idUsuario")%>,<%=porActivas%>,<%=porRealizadas%>,<%=porAtrazadas%>)" style="text-decoration: none;">Porcentaje de cumplimiento <%=cumplimiento%>%</a></div>	 
</div> 
<%}%> 
