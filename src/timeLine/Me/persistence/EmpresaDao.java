package timeLine.Me.persistence;

import java.util.List;

import timeLine.Me.model.Empresa;


public interface EmpresaDao {
	 public void insert(Empresa empresa) throws PersistenceException;
	    
	    public void delete(Empresa empresa) throws PersistenceException;
	    
	    public void update(Empresa empresa) throws PersistenceException;
	    
	    public Empresa findById(String emailEmpresa) throws PersistenceException;
	    
	    public List<Empresa> findAll() throws PersistenceException;
	    
	    public List<Empresa> findAllEmpresasNoSeguidas(String emailAgente) throws PersistenceException;
	    
	    public List<Empresa> findAllEmpresasSeguidasPorAgente(String emailAgente) throws PersistenceException;
	    
	    public List<Empresa> findAllEmpresasSeguidas(String emailAgente) throws PersistenceException;
	    
}
