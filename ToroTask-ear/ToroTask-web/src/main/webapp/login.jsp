<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://jakarta.apache.org/struts/tags-html" prefix="html"%>
<html>
    <head>
        <title>ToroTask</title>
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.1/css/bootstrap.min.css">
        <link rel="stylesheet" href="http://cdn.kendostatic.com/2014.2.903/styles/kendo.common-bootstrap.min.css" />
        <link rel="stylesheet" href="http://cdn.kendostatic.com/2014.2.903/styles/kendo.bootstrap.min.css" />
        <link rel="stylesheet" href="http://cdn.kendostatic.com/2014.2.903/styles/kendo.dataviz.min.css" />
        <link rel="stylesheet" href="http://cdn.kendostatic.com/2014.2.903/styles/kendo.dataviz.bootstrap.min.css" />
        <link rel="stylesheet" href="styles/login.css" />
        <script src="http://code.jquery.com/jquery-1.9.1.min.js"></script>
        <script src="http://ajax.googleapis.com/ajax/libs/angularjs/1.2.16/angular.js"></script>
        <script src="http://cdn.kendostatic.com/2014.2.903/js/kendo.all.min.js"></script>
        <script src="http://cdn.kendostatic.com/2014.2.903/js/kendo.timezones.min.js"></script>

    </head>
    <body>

        <div class="container">
            <html:form action="/login?toMethod=login" styleClass="form-signin" styleId="login"  >
            <h2 class="form-signin-heading">Ingresar a ToroTask</h2>
            <label for="inputEmail" class="sr-only">E-mail</label>
            <html:text name="loginForm" styleClass="form-control" styleId="username" property="userName"/>
            <label for="inputPassword" class="sr-only">Clave</label>
            <html:password name="loginForm" styleClass="form-control" styleId="clave" property="password" />
            <html:submit value="Ingresar" styleClass="btn btn-lg btn-primary btn-block" />
        </html:form>
        </div> <!-- /container -->


        <!--form id="login-register" method="post" action="login.do">
        <h1>Ingresar a ToroTask</h1>
        
        <input type="text" placeholder="correo@email.com" name="email" id="email" autofocus />
        <input type="password" placeholder="Clave"  name="password" id="password" />
        <button type="submit">Ingresar</button>
        <span></span>

    </form-->
        <script>
            $("#login").attr("role","form");
            $("#username").attr("placeholder","E-mail");
            $("#clave").attr("placeholder","Contraseña");
        </script>
    </body>
</html>