package timeLine.Me.services;




import timeLine.Me.model.Agente;
import timeLine.Me.model.Contenido;
import timeLine.Me.model.SeguimientoEmpresa;

import timeLine.Me.persistence.PersistenceException;



public class SesionService {
	
	private static SesionService instance = new SesionService();

	  
	  private SesionService() {
	  }
	  
	  public static SesionService getInstance() {
		  return instance;
	  }
	  
	  public Agente obtenerDatos(String usuario) throws PersistenceException {
		  UsuarioService agente = UsuarioService.getInstance();
		
		  Agente agenteContenido = agente.obtenerAgente(usuario);
		  agenteContenido.setContenido(agente.obtenerContenido(usuario));
		  agenteContenido.setEmpresa(agente.obtenerEmpresaSeguidasPorAgente(usuario));
		  agenteContenido.setVerNoSeguidas(agente.obtenerEmpresasNoSeguidas(usuario));
		  agenteContenido.setVerSeguidas(agente.obtenerEmpresaSeguidas(usuario));
		  return agenteContenido;   
	 }
		  
	  
	  public void insertarContenido(String textoContenido, String fecha, String emailAgente, String titulo, String emailEmpresa) throws PersistenceException {
		  UsuarioService contenido = UsuarioService.getInstance();
		  Contenido insertarContenido = new Contenido();
		  insertarContenido.setEmailAgente(emailAgente);
		  insertarContenido.setFecha(fecha);
		  insertarContenido.getTexto().setSubtitulo(textoContenido);
		  insertarContenido.getTexto().setTitulo(titulo);
		  insertarContenido.setEmailEmpresa(emailEmpresa);
		  contenido.insertContenido(insertarContenido);
	 }
	  
	  public void insertarSeguimiento(String emailEmpresa, String emailAgente) throws PersistenceException {
		  UsuarioService empresaSeguir = UsuarioService.getInstance();
		  empresaSeguir.insertSeguimiento(setSeguimiento(emailEmpresa, emailAgente));
		
	 }
	  
	  public void eliminarSeguimiento(String emailEmpresa, String emailAgente) throws PersistenceException {
		  UsuarioService empresaNoSeguir = UsuarioService.getInstance();
		  empresaNoSeguir.deleteSeguimiento(setSeguimiento(emailEmpresa, emailAgente));
	 }
	  
	  
	  private SeguimientoEmpresa setSeguimiento(String emailEmpresa, String emailAgente){
		  SeguimientoEmpresa seguimiento = new SeguimientoEmpresa();
		  seguimiento.setEmailAgente(emailAgente);
		  seguimiento.setEmailEmpresa(emailEmpresa);
		  return seguimiento;
		  
	  }
}
