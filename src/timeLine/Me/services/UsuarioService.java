package timeLine.Me.services;





import java.util.List;

import timeLine.Me.model.Agente;
import timeLine.Me.model.Contenido;
import timeLine.Me.model.Empresa;
import timeLine.Me.model.SeguimientoEmpresa;
import timeLine.Me.persistence.AgenteDao;
import timeLine.Me.persistence.AgenteDaoJdbcImpl;
import timeLine.Me.persistence.ContenidoDao;
import timeLine.Me.persistence.ContenidoDaoJdbcImpl;
import timeLine.Me.persistence.EmpresaDao;
import timeLine.Me.persistence.EmpresaDaoJdbcImpl;
import timeLine.Me.persistence.PersistenceException;
import timeLine.Me.persistence.SeguimientoDao;
import timeLine.Me.persistence.SeguimientoDaoJdbcImpl;



public class UsuarioService {
	
	private static UsuarioService instance = new UsuarioService();

	  
	  private UsuarioService() {
	  }
	  
	  public static UsuarioService getInstance() {
		  return instance;
	  }
	  
	  
	  public Agente obtenerAgente(String usuario) throws PersistenceException {
		     AgenteDao agente = new AgenteDaoJdbcImpl();
		     return agente.findById(usuario);
	  }
	  
	  public List <Contenido> obtenerContenido(String usuario) throws PersistenceException {
		  ContenidoDao contenido = new ContenidoDaoJdbcImpl();
		  return contenido.findAllEmail(usuario);
		  
	  }
	  
	  public List <Empresa> obtenerEmpresaSeguidasPorAgente(String usuario) throws PersistenceException {
		  EmpresaDao empresa = new EmpresaDaoJdbcImpl();
		  return empresa.findAllEmpresasSeguidasPorAgente(usuario);
		  
	  }
	  
	  public List <Empresa> obtenerEmpresaSeguidas(String usuario) throws PersistenceException {
		  EmpresaDao empresa = new EmpresaDaoJdbcImpl();
		  return empresa.findAllEmpresasSeguidas(usuario);
		  
	  }
	  
	  
	  public List <Empresa> obtenerEmpresasNoSeguidas(String usuario) throws PersistenceException {
		  EmpresaDao empresa = new EmpresaDaoJdbcImpl();
		  return empresa.findAllEmpresasNoSeguidas(usuario);
		  
	  }
	  
		 
	  public void insertContenido(Contenido contenido) throws PersistenceException {
		  ContenidoDao contenidoInsertar = new ContenidoDaoJdbcImpl();
			contenidoInsertar.insert(contenido);
	  }
		 
		 
	 public void insertSeguimiento(SeguimientoEmpresa seguimiento) throws PersistenceException {
		  SeguimientoDao seguimientoEmpresa = new SeguimientoDaoJdbcImpl();
		  seguimientoEmpresa.insert(seguimiento);
	  }
		 
	 public void deleteSeguimiento(SeguimientoEmpresa seguimiento) throws PersistenceException {
		 SeguimientoDao seguimientoEmpresa = new SeguimientoDaoJdbcImpl();
		 seguimientoEmpresa.delete(seguimiento);
	  }

}
