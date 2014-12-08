<%
String mensaje = (String)request.getAttribute("mensaje");
if(mensaje==null){
	mensaje = "";
}
%>
<html><head>
  <meta charset="UTF-8">
	<title>TORO-TASK :: The evolution of software.</title>
    <link rel="stylesheet" href="styles/index.css" media="screen" type="text/css">
</head>
<body>
<script src="js/jquery.min.js"></script>
<div id="logmsk" style="display: block;">
    <div id="close">X</div>
    <div id="userbox">
        <form action="acceso.do" name="formulario" method="post">
        <h1 id="signup" style="background-color: rgb(118, 171, 219); background-position: initial initial; background-repeat: initial initial;">ACCESO :: TORO-TASK</h1>
        <div id="sumsk" style="display: none;">Espere por favor ...</div>
        <input id="name" name="correoTT" placeholder="E-MAIL" value="danileruleru@gmail.com" style="opacity: 1; background-color: rgb(255, 255, 255); background-position: initial initial; background-repeat: initial initial;">
        <input id="pass" name="claveTT" type="password" placeholder="Password" value="pollo"  style="opacity: 1; background-color: rgb(255, 255, 255); background-position: initial initial; background-repeat: initial initial;">
        <input type="hidden" name="toMethod" value="login"> 
        <p id="logint" style="opacity: 1;"><%=mensaje%></p>
        <p id="nameal" style="display: none; opacity: 1;">Email:</p>
        <p id="passal" style="display: none; opacity: 1;">Password:</p>
        <button id="signupb" style="opacity: 0.2; cursor: default;">ENTRAR</button>
        </form>
    </div>
</div>
<script src="js/index.js"></script>

</body></html>