<jsp:directive.page import="com.gotoque.torotask.presentacion.struts.Utilidades.JSONArray"/>
<jsp:directive.page import="com.gotoque.torotask.presentacion.struts.Utilidades.JSONObject"/>
<%@ taglib uri="/WEB-INF/tld/pd4ml.tld" prefix="pd4ml" %>
<pd4ml:transform
fileName="FichaTarea.pdf"
        inline="true"
        screenWidth="1000"
        pageFormat="A4"
		adjustScreenWidth="true"
		pageInsets="5,5,5,5,mm"
        pageOrientation="portrait">
<html style="background-color:#FAFAFA">
<%

	JSONObject tarea = (JSONObject)request.getAttribute("tarea");
	JSONArray comentarios = (JSONArray)tarea.getJSONArray("comentarios");
	JSONArray adjuntos = (JSONArray)tarea.getJSONArray("adjuntos");
	JSONArray integrantes = (JSONArray)tarea.getJSONArray("integrantes");
	
	String ingenieros = "";
	for(int i = 0;i < integrantes.length();i++){
		JSONObject integrante = (JSONObject)integrantes.getJSONObject(i);
		ingenieros = ingenieros + " " + integrante.getString("correo");
	}
%>
<head>
</head>
<body>
<center>
<br><br>
<table width="98%">
	<tr>
		<td width="70%">
			<table width="100%">
				<tr>
					<td colspan="2" height="50px" style="font-size: 44px;font-family: sans-serif;color: #848484;padding-bottom: 50px;">
						<center><img src="img/h1-2.png"> FICHA DE TAREA</center>
					</td>
				</tr>
				<tr>
					<td width="30%">
						Proyecto
					</td>
					<td  width="70%">
						: <%=tarea.getString("idproyecto")%>
					</td>
				</tr>
				<tr>
					<td>
						Tarea 
					</td>
					<td>
						: <%=tarea.getString("tarea") %>
					</td>
				</tr>
				<tr>
					<td>
						Fecha de  Ingreso:
					</td>
					<td>
						: <%=tarea.getString("fecha_inicio")%>
					</td>
				</tr>
				<tr>
					<td>
						Jefe de proyecto
					</td>
					<td>
						: <%=tarea.getString("usuario")%>
					</td>
				</tr>
				<tr>
					<td>
						Integrantes
					</td>
					<td>
						: <%=ingenieros %>
					</td>
				</tr>
			</table>
		</td>
		<td width="30%" style="border: 4px solid #e7e7e7;">
			<center>
				<table>
					<tr>
						<td style="font-size: 20;">
							<center>NUMERO DE TAREA</center>
						</td>
					</tr>
					<tr>
						<td style="font-size: 45;">
							<center>N&#176; <%=tarea.getString("idtarea")%></center>
						</td>
					</tr>
				</table>
			</center>
		</td>	
	</tr>
</table>

<table width="98%" height="300px">
	<tr>
		<td style="border: 4px solid #e7e7e7;">
			<div style="height: 280px">
			<div style="font-family: Tahoma,Arial,sans-serif;width:200px;border-right: 4px solid #e7e7e7;border-bottom: 4px solid #e7e7e7;color:#ff6600">Descripcion</div>
				<div style="padding-top: 10px">
					<div style="float:right;width:98%">
						<%=tarea.getString("descripcion")%>
					</div>
				</div>
			</div>
		</td>
	</tr>
</table>
<table width="98%" height="300px;color:#585858">
	<tr>
		<td style="border: 4px solid #e7e7e7;">
			<div style="height: 280px;">
				<div style="height: 280px">
					<div style="font-family: Tahoma,Arial,sans-serif;width:200px;border-right: 4px solid #e7e7e7;border-bottom: 4px solid #e7e7e7;color:#ff6600">Comentarios</div>
					<%
					for(int i = 0;i < comentarios.length();i++){
						JSONObject comentario = (JSONObject)comentarios.getJSONObject(i);
					%>
					<div style="padding-top: 10px;float:left;width:98%">
						<div style="float:left;width:98%">
							<div style="float:left;color:#585858"><%=comentario.getString("usuario")%> : <%=comentario.getString("fecha")%></div>
						</div>
						<div style="float:right;width:98%">
							<%=comentario.getString("comentario")%><br>
						</div>
					</div>
					<%}%>
					
				</div>
			</div>
		</td>
	</tr>
</table>
<table width="98%" height="300px">
	<tr>
		<td style="border: 4px solid #e7e7e7;">
			<div style="height: 280px;">
				<div style="height: 280px">
					<div style="font-family: Tahoma,Arial,sans-serif;width:200px;border-right: 4px solid #e7e7e7;border-bottom: 4px solid #e7e7e7;color:#ff6600">Adjuntos</div>
					<%
					for(int i = 0;i < adjuntos.length();i++){
						JSONObject adjunto = (JSONObject)adjuntos.getJSONObject(i);
					%>
					<div style="padding-top: 10px;float:left;width:98%">
						<div style="float:left;width:98%">
							<div style="float:left;color:#585858"><%=adjunto.getString("usuario")%> : <%=adjunto.getString("fecha")%></div>
						</div>
						<div style="float:right;width:98%">
							<table width="100%">
								<tr>
									<td width="33%">
										Archivo
									</td>
									<td  width="33%">
										Tamaño
									</td>
									<td  width="33%">
										Tipo de archivo
									</td>
								</tr>
								<tr>
									<td>
										<%=adjunto.getString("name").split("-")[1].toString()%>
									</td>
									<td>
										<%=adjunto.getString("size")%>
									</td>
									<td>
										<%=adjunto.getString("extension")%>
									</td>
								</tr>
							</table>
						</div>
					</div>
					<%}%>
				</div>
			</div>	
		</td>
	</tr>
</table>
</center>
</body>
</html>

</pd4ml:transform>