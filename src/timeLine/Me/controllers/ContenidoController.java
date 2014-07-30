package timeLine.Me.controllers;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;


import timeLine.Me.persistence.PersistenceException;
import timeLine.Me.services.SesionService;

@Controller
@RequestMapping("/paginas")
public class ContenidoController {
	    SesionService sesionService = SesionService.getInstance();
		
		 @RequestMapping("/subir")
			public  ModelAndView  subirContenidoController(HttpServletRequest request, HttpSession session) throws PersistenceException {
			 String emailAgente = (String) request.getParameter("usuario");
		     String emailEmpresa = (String) request.getParameter("empresa");
		     String titulo = (String) request.getParameter("titulo");
		     String textoContenido = (String) request.getParameter("texto");
		     String fecha = (String) request.getParameter("fecha");
		     
			 
			 ModelAndView modelAndView = new ModelAndView();
			 sesionService.insertarContenido(textoContenido, fecha, emailAgente, titulo, emailEmpresa);
			 session.setAttribute("agente", sesionService.obtenerDatos(emailAgente));
		     modelAndView.setViewName("agente_perfil");
			 return  modelAndView;
			    
			}
		 
		 @RequestMapping("/seguir")
			public  ModelAndView  seguirEmpresaController(HttpServletRequest request, HttpSession session) throws PersistenceException {
			 String emailAgente = (String) request.getParameter("emailAgente");
			 String emailEmpresa = (String) request.getParameter("emailEmpresa");
			 
			 ModelAndView modelAndView = new ModelAndView();
			 sesionService.insertarSeguimiento(emailEmpresa,emailAgente);
			 session.setAttribute("agente", sesionService.obtenerDatos(emailAgente));
             modelAndView.setViewName("seguir_empresa");
			 return  modelAndView;
			    
			}
		 
		 @RequestMapping("/noSeguir")
			public  ModelAndView  noSeguirEmpresaController(HttpServletRequest request, HttpSession session) throws PersistenceException {
				 String emailAgente = (String) request.getParameter("emailAgente");
				 String emailEmpresa = (String) request.getParameter("emailEmpresa");
			 
			 ModelAndView modelAndView = new ModelAndView();
			 sesionService.eliminarSeguimiento(emailEmpresa,emailAgente);
			 session.setAttribute("agente", sesionService.obtenerDatos(emailAgente));
             modelAndView.setViewName("seguir_empresa");
			 return  modelAndView;
			    
			}
		 
}
		
	     
	     
	     
		  



