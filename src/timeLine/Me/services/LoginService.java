package timeLine.Me.services;

import timeLine.Me.model.Agente;
import timeLine.Me.persistence.PersistenceException;

public class LoginService {
	
	private static LoginService instance = new LoginService();

	  
	  private LoginService() {
	  }
	  
	  public static LoginService getInstance() {
		  return instance;
	  }
	  
	  public boolean validarUsuario(String usuario, String password) throws PersistenceException {
		  Agente agenteActual = encontrarAgente(usuario);
		  if(agenteActual == null) {
			  return false;
		  } else {
		  return agenteActual.getEmailAgente().equals(usuario) && agenteActual.getPassword().equals(password);
		  }
	  }
	  
	  public Agente encontrarAgente(String usuario) throws PersistenceException {
		  UsuarioService agente = UsuarioService.getInstance();
		  return agente.obtenerAgente(usuario);
	  }

}
