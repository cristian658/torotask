<%@ page import="com.gotoque.torotask.presentacion.struts.Utilidades.JSONArray" %>
<%@ page import="com.gotoque.torotask.presentacion.struts.Utilidades.JSONObject" %>
<%
	JSONArray equipo = (JSONArray)request.getAttribute("equipo");
%>
<ul id="panelbar">
	<li class="k-state-active" style="width:100%">
		<span class="k-link k-state-selected"><img
				src="img/contacticon_client.png" width="20px">Personal</span>
		<div style="padding: 10px;">
		<%for(int i = 0;i<equipo.length();i++){ 
			JSONObject usuario = equipo.getJSONObject(i);
			JSONObject perfil = usuario.getJSONObject("perfilVO");
		%>
			<div class="teamMate">
				<img width="70px" src="servlet/getImagen.jpg?id=<%=usuario.getString("idUsuario")%>">
				<div style="padding-bottom: 0px;font-size: 18px;">
					<%=usuario.getString("nombre")+ " " + usuario.getString("apellidoPaterno")%>
				</div>
				<div style=" color: white;"><%=usuario.getString("correo") %></div>
				<div style=" color: white;">Cargo : <%=perfil.getString("perfil") %></div>
				<div style=" color: white;">Fono : <%=usuario.getString("anexo") %></div>
			</div>
		<%}%>
		</div>
	</li>
	<li disabled="disabled"  style="width:100%">
		<img src="img/helpicon_on.png">
		Ayuda.
	</li>
</ul>
<script>
	$("#panelbar").kendoPanelBar({
		expandMode: "single",
	});
</script>
