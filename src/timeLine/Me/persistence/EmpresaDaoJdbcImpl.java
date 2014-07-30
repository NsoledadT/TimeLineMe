package timeLine.Me.persistence;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

import timeLine.Me.model.Empresa;



public class EmpresaDaoJdbcImpl implements EmpresaDao {
	
	private static EmpresaDao instance = new EmpresaDaoJdbcImpl();

	public static EmpresaDao getInstance() {
		return instance;
	}

	@Override
	public void insert(Empresa empresa) throws PersistenceException {

		Transaction tx = TransactionJdbcImpl.getInstance();
		Connection conn = tx.getConnection();

		try {
			tx.begin();
			String query = "insert into empresa (emailEmpresa, nombre, rubro, empresaPassword) values (?, ?, ?, ?)";
			PreparedStatement statement = TransactionJdbcImpl.getInstance()
					.getConnection().prepareStatement(query);
			statement.setString(1, empresa.getEmail());
			statement.setString(2, empresa.getNombre());
			statement.setString(3, empresa.getRubro());
			statement.setString(4, empresa.getPassword());

			statement.executeUpdate();

			tx.commit();

		} catch (SQLException sqlException) {
			throw new PersistenceException(sqlException);
		} finally {
			try {
				conn.close();
			} catch (SQLException sqlException) {
				throw new PersistenceException(sqlException);
			}
		}
	}

	@Override
	public void delete(Empresa empresa) throws PersistenceException {
		Transaction tx = TransactionJdbcImpl.getInstance();
		Connection conn = tx.getConnection();

		try {
			tx.begin();

			String query = "delete from empresa where emailEmpresa = ?";
			PreparedStatement statement = conn.prepareStatement(query);
			statement.setString(1, empresa.getEmail());
			statement.executeUpdate();

			tx.commit();

		} catch (SQLException sqlException) {
			throw new PersistenceException(sqlException);
		} finally {
			try {
				conn.close();
			} catch (SQLException sqlException) {
				throw new PersistenceException(sqlException);
			}
		}
	}

	@Override
	public void update(Empresa empresa) throws PersistenceException {
		try {
			String query = "update empresa set emailEmpresa = ?, nombre = ?, rubro = ?, empresaPassword = ? where emailEmpresa = ?";

			PreparedStatement statement = TransactionJdbcImpl.getInstance()
					.getConnection().prepareStatement(query);
			statement.setString(1, empresa.getEmail());
			statement.setString(2, empresa.getNombre());
			statement.setString(3, empresa.getRubro());
			statement.setString(4, empresa.getPassword());
			statement.executeUpdate();
		} catch (SQLException sqlException) {
			throw new PersistenceException(sqlException);
		}
	}
	
	@Override
    public List<Empresa> findAll() throws PersistenceException {
		List<Empresa> lista = new LinkedList<Empresa>();
		try {
			String query = "select * from empresa";
			PreparedStatement statement = ConnectionProvider.getInstance()
					.getConnection().prepareStatement(query);
			ResultSet resultSet = statement.executeQuery();
			while (resultSet.next()) {
				lista.add(convertOne(resultSet));
			}
		} catch (SQLException sqlException) {
			throw new PersistenceException(sqlException);
		}
		return lista;
	}

	@Override
	public Empresa findById(String emailEmpresa) throws PersistenceException {
		if (emailEmpresa == null) {
			throw new IllegalArgumentException(
					"El email de empresa no debe ser nulo");
		}
		Empresa empresa = null;
		try {
			Connection c = ConnectionProvider.getInstance().getConnection();
			String query = "select * from empresa where emailEmpresa = ?";
			PreparedStatement statement = c.prepareStatement(query);
			statement.setString(1, emailEmpresa);
			ResultSet resultSet = statement.executeQuery();
			if (resultSet.next()) {
				empresa = convertOne(resultSet);
			}
		} catch (SQLException sqlException) {
			throw new PersistenceException(sqlException);
		}
		return empresa;
	}
	
    
    
    @Override
    public List<Empresa> findAllEmpresasNoSeguidas(String emailAgente) throws PersistenceException {
		List<Empresa> lista = new LinkedList<Empresa>();
		try {
			String query = "select * from empresa where emailEmpresa  not in " +
					"(select emailEmpresa from seguimientoEmpresa where emailAgente = ?)";
			PreparedStatement statement = ConnectionProvider.getInstance()
					.getConnection().prepareStatement(query);
			statement.setString(1, emailAgente);
			ResultSet resultSet = statement.executeQuery();
			while (resultSet.next()) {
				lista.add(convertOne(resultSet));
			}
		} catch (SQLException sqlException) {
			throw new PersistenceException(sqlException);
		}
		return lista;
	}
    
    @Override
    public List<Empresa> findAllEmpresasSeguidasPorAgente(String emailAgente) throws PersistenceException {
		List<Empresa> lista = new LinkedList<Empresa>();
		try {
			String query = "select empresa.nombre, contenido.textoContenido, contenido.fecha from empresa" +
					       " inner join (select emailEmpresa, textoContenido, fecha, titulo from  contenido " +
					       "where emailEmpresa  in (select emailEmpresa from seguimientoEmpresa where emailAgente  = ? )) " +
					       "as contenido where empresa.emailEmpresa = contenido.emailEmpresa order by fecha desc";
			PreparedStatement statement = ConnectionProvider.getInstance()
					.getConnection().prepareStatement(query);
			statement.setString(1, emailAgente);
			ResultSet resultSet = statement.executeQuery();
			while (resultSet.next()) {
				lista.add(convertOne2(resultSet));
			}
		} catch (SQLException sqlException) {
			throw new PersistenceException(sqlException);
		}
		return lista;
	}
    
    @Override
    public List<Empresa> findAllEmpresasSeguidas(String emailAgente) throws PersistenceException {
		List<Empresa> lista = new LinkedList<Empresa>();
		try {
			String query = "select * from empresa where emailEmpresa in" +
					" (select emailEmpresa from seguimientoEmpresa where emailAgente = ?) ";
			PreparedStatement statement = ConnectionProvider.getInstance()
					.getConnection().prepareStatement(query);
			statement.setString(1, emailAgente);
			ResultSet resultSet = statement.executeQuery();
			while (resultSet.next()) {
				lista.add(convertOne(resultSet));
			}
		} catch (SQLException sqlException) {
			throw new PersistenceException(sqlException);
		}
		return lista;
	}


	private Empresa convertOne(ResultSet resultSet) throws SQLException {
		Empresa retorno = new Empresa();

		retorno.setEmail(resultSet.getString("emailEmpresa"));
		retorno.setNombre(resultSet.getString("nombre"));
		retorno.setRubro(resultSet.getString("rubro"));
		retorno.setPassword(resultSet.getString("empresaPassword"));

		return retorno;
	}
	
	private Empresa convertOne2(ResultSet resultSet) throws SQLException {
		Empresa retorno = new Empresa();

		retorno.setNombre(resultSet.getString("empresa.nombre"));
		retorno.getContenido().getTexto().setSubtitulo(resultSet.getString("contenido.textoContenido"));
		retorno.getContenido().setFecha(resultSet.getString("contenido.fecha"));
	
        return retorno;
	}


}
