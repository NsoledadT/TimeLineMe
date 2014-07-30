package timeLine.Me.persistence;


public class DaoFactory {
	
	public static EmpresaDao getEmpresaDao(){
		return EmpresaDaoJdbcImpl.getInstance();
	}
	
	public static AgenteDao getAgenteDao(){
		return AgenteDaoJdbcImpl.getInstance();
	}
	
	public static ContenidoDao getContenidoDao(){
		return ContenidoDaoJdbcImpl.getInstance();
	}
	
	public static SeguimientoDao getSeguimientoDao(){
		return SeguimientoDaoJdbcImpl.getInstance();
	}
	

}
