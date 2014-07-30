package timeLine.Me.persistence;

import java.util.List;

import timeLine.Me.model.Agente;



public interface AgenteDao {
	public void insert(Agente agente) throws PersistenceException;
    
    public void delete(Agente agente) throws PersistenceException;
    
    public void update(Agente agente) throws PersistenceException;
    
    public Agente findById(String emailAgente) throws PersistenceException;
    
    public List<Agente> findAll() throws PersistenceException;
    
}
