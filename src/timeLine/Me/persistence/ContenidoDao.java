package timeLine.Me.persistence;

import java.util.List;

import timeLine.Me.model.Contenido;


public interface ContenidoDao {
	
    public void insert(Contenido contenido) throws PersistenceException;
    
    public void delete(Contenido contenido) throws PersistenceException;
    
    public void update(Contenido contenido) throws PersistenceException;
    
    public Contenido findByEmail(String emailAgente) throws PersistenceException;
    
    public List<Contenido> findAll() throws PersistenceException;
    
    public List<Contenido> findAllEmail(String emailAgente) throws PersistenceException;

}
