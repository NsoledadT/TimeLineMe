package timeLine.Me.controllers;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;




import timeLine.Me.persistence.PersistenceException;
import timeLine.Me.services.LoginService;
import timeLine.Me.services.SesionService;



@Controller
@RequestMapping("/paginas")
@SessionAttributes("agente")
public class LoginController {
	LoginService loginService = LoginService.getInstance();
	SesionService sesionService = SesionService.getInstance();
	
	
 @RequestMapping("/recordar")
	public ModelAndView loginController(HttpServletRequest request, HttpSession session) throws PersistenceException {
        String usuario = (String) request.getParameter("usuario");
        String password = (String) request.getParameter("password");
	    ModelAndView modelAndView = new ModelAndView();
	    String mensaje = "Password o Usuario no encontrado";
	  
	    if(loginService.validarUsuario(usuario, password)) {
		    modelAndView.addObject("agente", sesionService.obtenerDatos(usuario));
			modelAndView.setViewName("agente_inicio");
            return modelAndView;  
		 } else {
		   return new ModelAndView("index2","mensaje", mensaje);
	   }
  }
	   
}

