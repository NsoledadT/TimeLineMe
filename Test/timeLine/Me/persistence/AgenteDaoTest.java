package timeLine.Me.persistence;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import timeLine.Me.model.Agente;



public class AgenteDaoTest {
	
AgenteDao dao = DaoFactory.getAgenteDao();
	
	Agente juan;
	Agente carlos;
	
	@Before
	public void setUp() throws PersistenceException {
		// se borran todas los agentes, para iniciar con base vacia
		for (Agente cadaAgente : dao.findAll()) {
			dao.delete(cadaAgente);
		}

		// se inserta a juan
		juan = buildAgente("Juan Lopez", "juanLopez@", "juan2","ntocci@gmail.com");
		dao.insert(juan);

		// se inserta a carlos
		carlos = buildAgente("Carlos Sosa", "carlos@", "carlos3", "soledadt2013@gmail.com");
		dao.insert(carlos);
	}
	
	private Agente buildAgente(String nombreApellido, String emailAgente, String agentePassword, String emailEmpresa) {
		Agente agente = new Agente();
		agente.setEmailAgente(emailAgente);
		agente.setNombre(nombreApellido);
		agente.setPassword(agentePassword);
		agente.setEmailEmpresa(emailEmpresa);

		return agente;
	}
	
	@After
	public void tearDown() throws PersistenceException {
		// se borran todas los agentes

		dao.delete(juan);
		dao.delete(carlos);

	}
	
	@Test
	public void testQueSePuedeBuscarUnAgente() throws PersistenceException {

		Agente agenteEncontrado = dao.findById(juan.getEmailAgente());

		assertNotNull("el agente con juan@ debe existir", agenteEncontrado);
		assertEquals("el agente con nombre: juan Lopez", "Juan Lopez", agenteEncontrado.getNombre());
		assertEquals("el agente con password: juan2", "juan2", agenteEncontrado.getPassword());
		assertEquals("la empresa  con password: ntocci@gmail.com", "ntocci@gmail.com", (String)agenteEncontrado.getEmailEmpresa());

	}
	
	@Test
	public void testQueSePuedeInsertarUnAgente() throws PersistenceException {

		Agente mario = buildAgente("Mario Tocci", "mario@", "mario12","ntocci@gmail.com");
		assertEquals("antes de insertar hay 2 agentes", 2, dao.findAll().size());
        dao.insert(mario);
		assertEquals("luego de insertar hay 3 agentes", 3, dao.findAll().size());
		assertNotNull("puede encontrarse al agente con email:mario@", dao.findById(mario.getEmailAgente()));
		
    }
	
	@Test
	public void testQueSePuedeBorrarUnAgente() throws PersistenceException {

		Agente agenteEncontrado = dao.findById(juan.getEmailAgente());
		dao.delete(agenteEncontrado);

		agenteEncontrado = dao.findById(juan.getEmailAgente());
		assertNull("el agente con juanLopez@ no debe existir", agenteEncontrado);

	}
	
	@Test
	public void testQueSePuedeActualizarUnAgente() throws PersistenceException {

		Agente agenteEncontrado = dao.findById(juan.getEmailAgente());
		assertEquals("el agente con email juanLopez@ se llama Juan Lopez", "Juan Lopez", agenteEncontrado.getNombre());
		agenteEncontrado.setNombre("Mario Sosa");
		dao.update(agenteEncontrado);
		assertEquals("el agente con email juanLopez@ ahora se llama Mario Sosa", "Mario Sosa", agenteEncontrado.getNombre());

	}

	@Test
	public void testQueSePuedenBuscarTodosLosAgentes() throws PersistenceException {

		List<Agente> todasLosAgentes = dao.findAll();
		assertEquals("se espera que haya dos agentes en la base", 2, todasLosAgentes.size());

	}

}
