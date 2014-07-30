package timeLine.Me.persistence;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import timeLine.Me.model.SeguimientoEmpresa;

public class SeguimientoDaoTest {
	
    SeguimientoDao dao = DaoFactory.getSeguimientoDao();
	
	SeguimientoEmpresa uno;
	SeguimientoEmpresa dos;
	SeguimientoEmpresa tres;
	
	@Before
	public void setUp() throws PersistenceException {
		// se borran todos los contenidos, para iniciar con base vacía
		for (SeguimientoEmpresa cadaSeguimiento : dao.findAll()) {
			dao.delete(cadaSeguimiento);
		}
		
		// se inserta uno
		  uno = buildSeguimiento("leia@gmail.com", "solo@gmail.com");
		  dao.insert(uno);
	
		
		// se inserta uno
		dos = buildSeguimiento("luke@gmail.com", "solo@gmail.com");
		dao.insert(dos);

		// se inserta dos
		tres = buildSeguimiento("yoda@gmail.com", "homero@gmail.com");
		dao.insert(tres);
	}
	
	private SeguimientoEmpresa buildSeguimiento(String emailEmpresa, String  emailAgente) {
		SeguimientoEmpresa seguimientoEmpresa = new SeguimientoEmpresa();
		seguimientoEmpresa.setEmailAgente(emailAgente);
		seguimientoEmpresa.setEmailEmpresa(emailEmpresa);


		return seguimientoEmpresa;
	}
	
	@After
	public void tearDown() throws PersistenceException {
		// se borran todas los contenidos

		dao.delete(uno);
		dao.delete(dos);
		dao.delete(tres);
    }
	
   @Test
	public void testQueSePuedeInsertarUnSeguimiento() throws PersistenceException {
	    SeguimientoEmpresa cuatro = buildSeguimiento("yoda@gmail.com", "bart@gmail.com");
		assertEquals("antes de insertar hay 3 seguimientos", 3, dao.findAll().size());
        dao.insert(cuatro);
		assertEquals("luego de insertar hay 4 contenidos", 4, dao.findAll().size());
		
    }
   
   @Test
	public void testQueSePuedeBorrarUnSeguimiento() throws PersistenceException {

	  SeguimientoEmpresa seguimientoEncontrado = dao.findById(uno.getEmailEmpresa(),uno.getEmailAgente());
		dao.delete(seguimientoEncontrado);

		seguimientoEncontrado = dao.findById(uno.getEmailEmpresa(), uno.getEmailAgente());

		seguimientoEncontrado = dao.findById(uno.getEmailEmpresa(),uno.getEmailAgente());
		assertNull("el seguimiento con solo@gmail.com no debe existir", seguimientoEncontrado);

	}
   
	@Test
	public void testQueSePuedeBuscarUnSeguimiento() throws PersistenceException {

		SeguimientoEmpresa seguimientoEncontrado = dao.findById(dos.getEmailEmpresa(),dos.getEmailAgente());

		assertNotNull("el seguimiento con emailEmpresa luke@gmail.com debe existir", seguimientoEncontrado);
		assertEquals("el seguiimiento con emailAgente: solo@gmail.com debe existir", "solo@gmail.com", seguimientoEncontrado.getEmailAgente());
		assertEquals("el seguiimiento con emailEmpresa: luke@gmail.com debe existir", "luke@gmail.com", seguimientoEncontrado.getEmailEmpresa());
		
     }
	
	@Test
	public void testQueSePuedenBuscarTodosLosSeguimientos() throws PersistenceException {

		List<SeguimientoEmpresa> todosLosSeguimientos = dao.findAll();
		assertEquals("se espera que haya dos agentes en la base", 3, todosLosSeguimientos.size());

	}
	
}
