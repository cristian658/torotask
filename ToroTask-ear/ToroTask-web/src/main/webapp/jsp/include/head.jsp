<jsp:directive.page import="com.gotoque.torotask.presentacion.struts.Utilidades.JSONObject"/><%
JSONObject usuario = (JSONObject)request.getSession().getAttribute("usuario");
JSONObject perfil = usuario.getJSONObject("perfilVO");
%>
<div id="headerleftcontainer" style="color: rgba(255,255,255,0.65);">
	<img width="70px" src="servlet/getImagen.jpg?id=<%=usuario.getString("idUsuario")%>" style="float:left;border: 1px solid #ccc;">   
	<div id="headerdetailsdatecontainer">
		<div id="headerdetailsdate">
			<span id="headerdetailsdateday"><%=usuario.getString("nombre") + " " + usuario.getString("apellidoPaterno")%></span>
		</div>
		<div id="headerdetailstime">
			<%=perfil.getString("perfil") %>
		</div>
	</div>
	<div id="headerdetailstitle">
		<span id="headerdetailstitlespan" style="font-size: 27px;">Gestor de Tareas</span>
	</div>

	<div id="headerdetailsusercontainer">
	<div style="float: right;color: #FFF;padding-top: 10px;padding-right: 15px;">
		<div style="float: right;color: #FFF;padding-top: 10px;padding-right: 15px;">
		    <a id="cambiarClave"  style="text-decoration: none;color: #fff;cursor: pointer;">Cambiar Clave</a> | 
		    <a id="cerrarSession" style="text-decoration: none;color: #fff;cursor: pointer;">Cerrar session</a>
	    </div>
	</div>
	</div>
</div>


