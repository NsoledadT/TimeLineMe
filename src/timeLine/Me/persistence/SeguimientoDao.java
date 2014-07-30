package timeLine.Me.persistence;

import java.util.List;
import timeLine.Me.model.SeguimientoEmpresa;

public interface SeguimientoDao {
	
	public void insert(SeguimientoEmpresa seguimiento) throws PersistenceException;
    
    public void delete(SeguimientoEmpresa seguimiento) throws PersistenceException;
    
    public SeguimientoEmpresa findById(String emailEmpresa,String emailAgente) throws PersistenceException;
    
    public List<SeguimientoEmpresa> findAll() throws PersistenceException;
    

}
