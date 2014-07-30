<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head><title>TIMELINE.ME</title>
<link rel="stylesheet" type="text/css" href="../../css/estilo.css"/>
<link rel="stylesheet" type="text/css" href="../css/estilo.css"/>
<link rel="shortcut icon" type="image/x-icon" href="img/favicon.png" />
</head>
<body>

<div id="contenedor_general">
<!----------------------------------------------------------------------------------------------------->
<!--Encabezado-->
<!----------------------------------------------------------------------------------------------------->
    <div id="encabezado">
    <img src="../img/isologotipo.png" alt="TimeLine.Me" width="364" height="111" />
    <form action="recordar.do" method="POST">
    <p>Usuario:<input type="text" name="usuario"class="usuario"/></p>
    <p>Contrase&ntilde;a:<input type="password" name="password" class="password"/></p>
	<h5><a href="recuperar_password.html">¿Olvidaste tu contrase&ntilde;a?</a></h5>
	<p><input type="image" src="../img/boton_login_trans.png" id="sesion"/></p>
	<p>${mensaje}</p>
	</form>
    </div>
<!----------------------------------------------------------------------------------------------------->
<!--Contenido Principal-->
<!----------------------------------------------------------------------------------------------------->
    <div id="contenido">
	<div class="titular">
	<h2>Bienvenidos a TIMELINE.ME</h2>
	<h3>Comunica tus ideas m&aacute;s significativas</h3>
	<h2>¿Sos Nuevo?</h2>
	</div>
	<p><a href="registro.do" class="boton_registrarte">Reg&iacute;strate</a></p>
	</div>
</div>
<!----------------------------------------------------------------------------------------------------->
<!--Información Adicional-->	
 <!----------------------------------------------------------------------------------------------------->
  	<div id="franja_azul">
	  <div id="franja_centro">
	  <div class="parrafo">
	  <h4>¿QUE ES TIMELINE.ME?</h4>
	   <p>Es el lugar donde las Start UP pueden<br/>
          comunicar y registrar día a día sus<br/>
          logros y noticias más significativas.
	   </p>
	  </div>
	  <div class="parrafo">
	  <h4>¿COMO FUNCIONA?</h4>
	  <p>Fácil! Llena el formulario de ingreso<br/>
       y podrás designar a los agentes que<br/>
       se encargarán de subir las noticias<br/>
       que día a día se vayan generando.
	  </p>
	  </div>
	  <div class="parrafo">
	  <h4>BENEFICIOS</h4>
	   <p>Participar en un espacio común<br/>
        donde empresas en permanente<br/>
        desarrollo e innovación comparten<br/>
        sus avances generando un espacio<br/>
        de intercambio enriquecedor.
	  </p>
	  </div>
	 </div>
   </div>
<!----------------------------------------------------------------------------------------------------->
<!--Pie-->	
<!----------------------------------------------------------------------------------------------------->
	<div id="pie">
	   <div id="centrar">
	    <p>
	    <a href="ayuda.html">Ayuda</a>	|
	    <a href="terminos.html">T&eacute;rminos y condiciones</a>  |
	    <a href="publicidad.html">Publicidad</a> |
	    <a href="ayuda.html">Directorio</a></p>
	    <p>© Copyright 2013 | Todos los derechos reservados | Prohibida su reproducción total o parcial</p>
	    </div>
	</div>
</body>
</html>