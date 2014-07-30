<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
      <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %> 
      <%@ page import="java.util.*" %>
      <%@ page import="java.text.SimpleDateFormat"%>
     
      
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head><title>TIMELINE.ME</title>
<link rel="stylesheet" type="text/css" href="../../css/estilo.css"/>
<link rel="stylesheet" type="text/css" href="../css/estilo.css"/>
<link rel="shortcut icon" type="image/x-icon" href="../img/favicon.png" />

</head>
<body>

<div id="contenedor_general">
<!----------------------------------------------------------------------------------------------------->
<!--Encabezado-->
<!----------------------------------------------------------------------------------------------------->
    <div id="encabezado">
    <img src="../img/isologotipo.png" alt="TimeLine.Me" width="364" height="111" />
  </div>
<!----------------------------------------------------------------------------------------------------->
<!--Contenido Principal-->
<!----------------------------------------------------------------------------------------------------->
    <div id="contenido_agente">
	  <img src="../img/agente/agente.png" alt="hombre_agente" width="265" height="401" />
	  <div id="barra_menu">
	     <div id="botones">
	     <a href="${pageContext.request.contextPath}/" class="cerrar_sesion">Sesi&oacute;n/Fin</a>
	     <a href="inicio.do" class="inicio2">Inicio</a>
	     <a href="#" class="perfil2">Perfil</a>
		 </div>
	  </div>
	 
        
	  <div id="lateral">
	    <div class="espacio">
		</div>
	    <div class="recuadro">
		  <h2> ${agente.getNombre()}</h2>
		 <%
         SimpleDateFormat formato = new SimpleDateFormat ("yyyy/MM/dd");
          %>
		 <form action="subir.do" method="POST" >
		  <p class="tituloTexto">Titulo:</p>
		  <p><input type="text" name="titulo" class="inputTitulo" /></p>
		  <p><input type="text" value=<%= formato.format(new java.util.Date()) %> name="fecha" class="ocultos"/></p>
		  <p><input type="text" value= "${agente.getEmailAgente()}" name="usuario" class="ocultos"/></p>
		   <p><input type="text" value= "${agente.getEmailEmpresa()}" name="empresa" class="ocultos" /></p>
		  <p class="subtituloTexto">Contenido:</p>
		  <textarea rows="2" cols="25" name="texto" class="textarea"></textarea>
		  <p><input type="image" src="../img/agente/boton_subir_trans.png" id="subir"/></p>
		  </form>
		</div>
		
		<div class="espacio">
		</div>
		<div class="recuadro">
		   <h4>A quien seguir </h4>
		  <a href="seguirTodos.do" class="todos">Ver Todos</a>
		</div>
	  </div>
	  <div id="linea_general">
	      <div id="encabezado_cuadro">
		     <p>&nbsp;</p>
		     <h2>TIMELINE</h2>
		     <h2> ${agente.getNombre()}</h2>
		  </div>
		  <c:forEach items="${agente.getContenido()}" var="item">
		  <div class="uno">
		  
		     <div class="fecha">
		    <h4>  ${item.getFecha()}</h4>
		    
			 </div>
			 <div class="noticia">
			   <h3> ${item.getTexto().getTitulo()}</h3>
			   <h4> ${item.getTexto().getSubtitulo()}</h4>
			 </div>
			 
		  </div>
		   </c:forEach> 
		 <div id="pie_cuadro">
		  </div>
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