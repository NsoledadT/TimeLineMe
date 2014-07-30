package timeLine.Me.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import timeLine.Me.persistence.PersistenceException;


@Controller
@RequestMapping("/paginas")
public class BotonesController {
	
	 @RequestMapping("/registro")
		public ModelAndView RegistroController() throws PersistenceException  {
		     return new ModelAndView("registracion_empresa");
		}
	
     @RequestMapping("/perfil")
		public ModelAndView perfilController() throws PersistenceException  {
		     return new ModelAndView("agente_perfil");
		}
     
     @RequestMapping("/inicio")
		public ModelAndView inicioController() throws PersistenceException  {
		     return new ModelAndView("agente_inicio");
		}
     
     @RequestMapping("/seguirTodos")
		public ModelAndView SeguirController() throws PersistenceException  {
		     return new ModelAndView("seguir_empresa");
		}
     
     
	  
	}



