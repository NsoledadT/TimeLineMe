<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>TIMELINE.ME</title>

<link rel="stylesheet" type="text/css" href="../../css/estilo.css"/>
<link rel="stylesheet" type="text/css" href="../css/estilo.css"/>
<link rel="shortcut icon" type="image/x-icon" href="../img/favicon.ico"/>
</head>
<body>
<div id="contenedor_general">
<!----------------------------------------------------------------------------------------------------->
<!--Encabezado-->
<!----------------------------------------------------------------------------------------------------->
    <div id="encabezado">
    <a href="../index.jsp"><img src="../img/isologotipo.png" alt="TimeLine.Me" width="364" height="111" /></a>
    <form>
    <p>Usuario:<input type="text" name="usurio" class="usuario"/></p>
    <p>Contrase&ntilde;a:<input type="password" name="password" class="password"/></p>
	<h5><a href="recuperar_password.html">&iquest;Olvidaste tu contrase&ntilde;a?</a></h5>
	<p><input type="image" src="../img/boton_login_trans.png" id="sesion"/></p>
	</form>
    </div>
<!----------------------------------------------------------------------------------------------------->
<!--Contenido Principal-->
<!----------------------------------------------------------------------------------------------------->
    <div id="contenido_registracion">
	<div class="joven">
	<img src="../img/registracion_empresa/joven.png" alt="Joven" width="287" height="600"/>
	</div>
	<div class="contenedor_empresa">
	<h2>Unete a TIMELINE.ME y comienza a registrar!</h2>
	<form action="enviar.do" method="POST">
	<div class="formu">
	<p>Nombre de la empresa:<input type="text" name="empresa" class="empresa"/></p>
    <p>E-mail:<input type="text" name="mail" class="mail"/></p>
    <p>Rubro:<input type="text" name="rubro" class="usuario2"/></p>
    <p>Contrase&ntilde;a:<input type="password" name="password" class="password2"/></p>
    <p>Reingrese contrase&ntilde;a:<input type="password" name="repassword" class="reingresecon"/></p><br>	
	<p><input type="checkbox" name="condiciones" value=""/>Acepta t&eacute;rminos y condiciones</p>
	<p><input type="image" src="../img/registracion_empresa/boton_cuenta_trans.png" id="crearCuenta"/></p>
	</div>
	</form>
	</div>
	</div>
	</div>
<!----------------------------------------------------------------------------------------------------->
<!--Pie-->	
<!----------------------------------------------------------------------------------------------------->
       <div id="pie">
	   <div id="centrar">
	    <p>
	    <a href="pags/ayuda.html">Ayuda</a>	|
	    <a href="pags/terminos.html">T&eacute;rminos y condiciones</a>  |
	    <a href="pags/publicidad.html">Publicidad</a> |
	    <a href="pags/ayuda.html">Directorio</a></p>
		<p>&copy; Copyright 2013 | Todos los derechos reservados | Prohibida su reproducci&oacute;n total o parcial</p>
	    </div>
	</div>	
</body>
</html>