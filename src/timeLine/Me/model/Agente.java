package timeLine.Me.model;


import java.util.List;


public class Agente {
	private  String password;
	private String nombre;
	private  String emailAgente;
	private  String emailEmpresa;
	private List <Contenido> contenido;
	private  List<Empresa> seguimiento;
	private  List<Empresa> empresaASeguir;
	private  List<Empresa> empresaSeguidas;


	
	public void setPassword(String password) {
		this.password = password;
		
	}
	
	public void setEmailEmpresa(String emailEmpresa) {
		this.emailEmpresa = emailEmpresa;
		
	}
	
	public void setNombre(String nombre) {
		this.nombre = nombre;
		
	}
	
	
	public void setEmailAgente(String email) {
		this.emailAgente = email;
		
	}

	public void setContenido(List<Contenido> contenido){
		this.contenido = contenido;

	}
	
	public void setVerNoSeguidas(List<Empresa> empresasASeguir){
		this.empresaASeguir = empresasASeguir;

	}

	public void setVerSeguidas(List<Empresa> empresaSeguidas){
		this.empresaSeguidas= empresaSeguidas;

	}
	
	public String getEmailEmpresa() {
		return this.emailEmpresa;
		
	}
	
	public String getNombre() {
		return this.nombre;
		
	}

	public String getPassword() {
		return this.password;
	}

	public String getEmailAgente() {
		return this.emailAgente;
	}

	public void setEmpresa(List<Empresa> empresa){
		this.seguimiento=empresa;
	}

	public List<Empresa> getEmpresa() {
		return this.seguimiento;
	}

	public List<Contenido> getContenido() {
		return this.contenido;
	}
	
	public  List<Empresa> getEmpresasNoSeguidas(){
		return this.empresaASeguir;

	}
	
	public  List<Empresa> getEmpresasSeguidas(){
		return this.empresaSeguidas;

	}


}
