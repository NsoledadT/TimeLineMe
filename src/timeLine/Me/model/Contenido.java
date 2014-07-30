package timeLine.Me.model;


public class Contenido {
	private  Texto texto = new Texto();
	private String fecha;
	private String emailAgente;
	private String emailEmpresa;

	
	public void setFecha(String fecha) {
		this.fecha = fecha;

	}
	
	public void setEmailAgente(String emailAgente) {
		this.emailAgente = emailAgente;

	}
	
	
	public void setEmailEmpresa(String emailEmpresa) {
		 this.emailEmpresa = emailEmpresa;

	}

	public String getFecha() {
		return this.fecha;

	}
	
	public Texto getTexto() {
		return this.texto;

	}
	
	
	public String getEmailAgente() {
		return this.emailAgente;

	}

	
	public String getEmailEmpresa() {
		return this.emailEmpresa;

	}
	
	
	
	
}
