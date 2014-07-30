package timeLine.Me.persistence;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import timeLine.Me.model.Contenido;



public class ContenidoDaoTest {
     ContenidoDao dao = DaoFactory.getContenidoDao();
	
	Contenido uno;
	Contenido dos;
	Contenido tres;
	Contenido cuatro;
	
	@Before
	public void setUp() throws PersistenceException {
		// se borran todos los contenidos, para iniciar con base vacía
		for (Contenido cadaContenido : dao.findAll()) {
			dao.delete(cadaContenido);
		}

		// se inserta uno
		uno = buildContenido("Se abre nueva sucursal en Morón", "5/2/2013", "solo@gmail.com","BellaDonna");
		dao.insert(uno);

		// se inserta dos
		dos = buildContenido("Iniciamos Proyecto Innovador", "16/2/2013", "homero@gmail.com","GoodLife");
		dao.insert(dos);
		
		// se inserta tres
		tres = buildContenido("Iniciamos Proyecto Innovador", "16/2/2013", "solo@gmail.com","BellaLife");
		dao.insert(tres);
		
		// se inserta cuatro
		cuatro = buildContenido("Iniciamos Proyecto Innovador", "16/2/2013", "solo@gmail.com","Innovation");
		dao.insert(cuatro);
	}
	
	private Contenido buildContenido(String textoContenido, String fecha, String emailAgente, String titulo) {
		Contenido contenido = new Contenido();
		contenido.setEmailAgente(emailAgente);
		contenido.setFecha(fecha);
		contenido.getTexto().setSubtitulo(textoContenido);
		contenido.getTexto().setTitulo(titulo);

		return contenido;
	}
	
	@After
	public void tearDown() throws PersistenceException {
		// se borran todas los contenidos

		dao.delete(uno);
		dao.delete(dos);

	}
	
	@Test
	public void testQueSePuedeBuscarUnContenido() throws PersistenceException {

		Contenido contenidoEncontrado = dao.findByEmail(uno.getEmailAgente());

		assertNotNull("el contenido con  emailAgente solo@gmail.com debe existir", contenidoEncontrado);
		assertEquals("la Empresa con contenido: Se abre nueva sucursal en Morón debe existir", "Se abre nueva sucursal en Morón", contenidoEncontrado.getTexto().getSubtitulo());
		assertEquals("la Empresa con fecha: 5/2/2013", "5/2/2013", contenidoEncontrado.getFecha());

	}
	
	@Test
	public void testQueSePuedeInsertarUnContenido() throws PersistenceException {

		Contenido tres = buildContenido("Nuevo inversionista se incorpora", "17/11/2013", "bart@gmail.com","BellaDonna");
		assertEquals("antes de insertar hay 4 contenidos", 4, dao.findAll().size());
        dao.insert(tres);
		assertEquals("luego de insertar hay 5 contenidos", 5, dao.findAll().size());
		assertNotNull("puede encontrarse al agente con email:bart@gmail", dao.findByEmail(tres.getEmailAgente()));
		
    }
	
	@Test
	public void testQueSePuedeBorrarUnContenido() throws PersistenceException {

		Contenido contenidoEncontrado = dao.findByEmail(dos.getEmailAgente());
		dao.delete(contenidoEncontrado);

		contenidoEncontrado = dao.findByEmail(dos.getEmailAgente());
		assertNull("el contenido con homero@gmail.cm no debe existir", contenidoEncontrado);

	}
	
	@Test
	public void testQueSePuedeActualizarUnContenido() throws PersistenceException {

		Contenido contenidoEncontrado = dao.findByEmail(dos.getEmailAgente());
		assertEquals("el contenido con email homero@gmail.com dice Iniciamos Proyecto Innovador", "Iniciamos Proyecto Innovador", contenidoEncontrado.getTexto().getSubtitulo());
		contenidoEncontrado.getTexto().setSubtitulo("Nuevo inversionista se incorpora");
		dao.update(contenidoEncontrado);
		assertEquals("el contenido con email homero@gmail.com ahora dice: Nuevo inversionista se incorpora", "Nuevo inversionista se incorpora", contenidoEncontrado.getTexto().getSubtitulo());

	}

	@Test
	public void testQueSePuedenBuscarTodosLosConenidos() throws PersistenceException {

		List<Contenido> todosContenidos = dao.findAll();
		assertEquals("se espera que haya 4 contenidos en la base", 4, todosContenidos.size());

	}
	
	@Test
	public void testQueSePuedenObtenerTodosLosConenidosDeUnAgente() throws PersistenceException {

		List<Contenido> todosContenidosDeAgente = dao.findAllEmail(uno.getEmailAgente());
		assertEquals("se espera que haya 4 contenidos en la base", 3, todosContenidosDeAgente.size());

	}
	
	
	

}
