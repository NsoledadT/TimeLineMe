package timeLine.Me.model;

import java.util.List;


public class Empresa {
	
	private  String nombre;
	private  String email;
	private  String rubro;
	private  String password;
	private Contenido contenido = new Contenido();
	private List<Agente> agente;

	
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	
	public void setEmail(String email) {
		this.email = email;
		
	}
	
	public void setPassword(String password){
		this.password = password;
	}
	
	public void setRubro(String rubro){
		this.rubro = rubro;
	}
	
	public void setAgente(List<Agente> agente){
		this.agente = agente;
	}

     public String getNombre() {
		return this.nombre;
	}


    public String getEmail() {
    	return this.email;
	}


    public String getPassword() {
    	return this.password;
	}
    
    public String getRubro(){
    	return this.rubro;
    }

 
    public List<Agente> getAgente() {
    	return this.agente;
    	
    }
    
    public Contenido getContenido() {
    	return this.contenido;
    }

}
