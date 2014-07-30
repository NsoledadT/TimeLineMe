package timeLine.Me.persistence;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import timeLine.Me.model.Empresa;


public class EmpresaDaoTest {
EmpresaDao dao = DaoFactory.getEmpresaDao();
	
	Empresa goodLife;
	Empresa bellaDona;
	
	@Before
	public void setUp() throws PersistenceException {
		// se borran todas las empresas, para iniciar con base vacia
		for (Empresa cadaEmpresa : dao.findAll()) {
			dao.delete(cadaEmpresa);
		}

		// se inserta a goodLife
		goodLife = buildEmpresa("ntocci@gmail.com", "GoodLife", "Comunicacion","abcde");
		dao.insert(goodLife);

		// Chuck Norris decide insertarse
		bellaDona = buildEmpresa("natliatoc@yahoo.com", "BellaDona", "Informatica", "cdefg");
		dao.insert(bellaDona);
	}
	
	private Empresa buildEmpresa(String emailEmpresa, String nombre, String rubro, String password) {
		Empresa empresa = new Empresa();
		empresa.setEmail(emailEmpresa);
		empresa.setNombre(nombre);
		empresa.setRubro(rubro);
		empresa.setPassword(password);

		return empresa;
	}
	
	@After
	public void tearDown() throws PersistenceException {
		// se borran todas las empresas

		dao.delete(goodLife);
		dao.delete(bellaDona);

	}
	
	@Test
	public void testQueSePuedeBuscarUnaEmpresa() throws PersistenceException {

		Empresa empresaEncontrada = dao.findById(goodLife.getEmail());

		assertNotNull("la Empresa con ntocci@gmail.com debe existir", empresaEncontrada);
		assertEquals("la Empresa con nombre: goodLife", "GoodLife", empresaEncontrada.getNombre());
		assertEquals("la Empresa con rubro: Comunicacion", "Comunicacion", empresaEncontrada.getRubro());
		assertEquals("la Empresa con password: abcde", "abcde", (String)empresaEncontrada.getPassword());

	}
	
	@Test
	public void testQueSePuedeInsertarUnaEmpresa() throws PersistenceException {

		Empresa bellaDonna = buildEmpresa("soledadt2013@gmail.com", "bellaDonna", "comunicacion","arrjlm");
		assertEquals("antes de insertar hay 2 empresas", 2, dao.findAll().size());
        dao.insert(bellaDonna);
		assertEquals("luego de insertar hay 3 empresas", 3, dao.findAll().size());
		assertNotNull("puede encontrarse a la empresa con email:natllc@gmail.com", dao.findById(bellaDonna.getEmail()));
		
    }
	
	@Test
	public void testQueSePuedeBorrarUnaEmpresa() throws PersistenceException {

		Empresa empresaEncontrada = dao.findById(goodLife.getEmail());
		dao.delete(empresaEncontrada);

		empresaEncontrada = dao.findById(goodLife.getEmail());
		assertNull("la empresa con ntocci@gmail.com no debe existir", empresaEncontrada);

	}
	
	@Test
	public void testQueSePuedeActualizarUnaEmpresa() throws PersistenceException {

		Empresa empresaEncontrada = dao.findById(goodLife.getEmail());
		assertEquals("la empresa con email ntocci@gmail.com se llama GoodLife", "GoodLife", empresaEncontrada.getNombre());
		empresaEncontrada.setNombre("Gertrudis");
		dao.update(empresaEncontrada);
		assertEquals("la Empresa con con email ntocci@gmail.com ahora se llama Gertrudis", "Gertrudis", empresaEncontrada.getNombre());

	}

	@Test
	public void testQueSePuedenBuscarTodasLasEmpresas() throws PersistenceException {

		List<Empresa> todasLasEmpresas = dao.findAll();
		assertEquals("se espera que haya dos empresa en la base", 2, todasLasEmpresas.size());

	}
	

}
