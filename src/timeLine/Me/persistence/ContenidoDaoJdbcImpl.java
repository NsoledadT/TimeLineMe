package timeLine.Me.persistence;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

import timeLine.Me.model.Contenido;

public class ContenidoDaoJdbcImpl implements ContenidoDao {
	
	private static ContenidoDao instance = new ContenidoDaoJdbcImpl();

	public static ContenidoDao getInstance() {
		return instance;
	}

	@Override
	public void insert(Contenido contenido) throws PersistenceException {

		Transaction tx = TransactionJdbcImpl.getInstance();
		Connection conn = (Connection) tx.getConnection();

		try {
			tx.begin();
			String query = "insert into contenido (textoContenido, fecha, emailAgente, titulo, emailEmpresa) values (?, ?, ?, ?, ?)";
			PreparedStatement statement = TransactionJdbcImpl.getInstance()
					.getConnection().prepareStatement(query);
			statement.setString(1, contenido.getTexto().getSubtitulo());
			statement.setString(2, contenido.getFecha());
			statement.setString(3, contenido.getEmailAgente());
			statement.setString(4, contenido.getTexto().getTitulo());
			statement.setString(5, contenido.getEmailEmpresa());

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
	public void delete(Contenido contenido) throws PersistenceException {
		Transaction tx = TransactionJdbcImpl.getInstance();
		Connection conn = tx.getConnection();

		try {
			tx.begin();

			String query = "delete from contenido where emailAgente = ?";
			PreparedStatement statement = conn.prepareStatement(query);
			statement.setString(1, contenido.getEmailAgente());
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
	public void update(Contenido contenido) throws PersistenceException {
		try {
			String query = "update empresaContenido set textoContenido = ?, fecha = ?, emailAgente = ?, titulo= ?, emailEmpresa = ? where emailAgente = ?";

			PreparedStatement statement = TransactionJdbcImpl.getInstance()
					.getConnection().prepareStatement(query);
			statement.setString(1, contenido.getTexto().getSubtitulo());
			statement.setString(2, contenido.getFecha());
			statement.setString(3, contenido.getEmailAgente());
			statement.setString(4, contenido.getTexto().getTitulo());
			statement.setString(5, contenido.getEmailEmpresa());
			statement.executeUpdate();
		} catch (SQLException sqlException) {
			throw new PersistenceException(sqlException);
		}
	}
	
	@Override
	public Contenido findByEmail(String emailAgente) throws PersistenceException {
		if (emailAgente == null) {
			throw new IllegalArgumentException(
					"El email de agente no debe ser nulo");
		}
		Contenido contenido = null;
		try {
			Connection c = ConnectionProvider.getInstance().getConnection();
			String query = "select * from contenido where emailAgente = ? ";
			PreparedStatement statement = c.prepareStatement(query);
			statement.setString(1, emailAgente);
			ResultSet resultSet = statement.executeQuery();
			if (resultSet.next()) {
				contenido = convertOne(resultSet);
			}
		} catch (SQLException sqlException) {
			throw new PersistenceException(sqlException);
		}
		return contenido;
	}
	

	
	@Override
    public List<Contenido> findAll() throws PersistenceException {
		List<Contenido> lista = new LinkedList<Contenido>();
		try {
			String query = "select * from contenido order by fecha DESC";
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
    public List<Contenido> findAllEmail(String emailAgente) throws PersistenceException {
		List<Contenido> lista = new LinkedList<Contenido>();
		try {
			String query = "select * from contenido where emailAgente = ? order by fecha Desc";
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

	

	private Contenido convertOne(ResultSet resultSet) throws SQLException {
		Contenido retorno = new Contenido();
        retorno.getTexto().setSubtitulo(resultSet.getString("textoContenido"));
		retorno.setFecha(resultSet.getString("fecha"));
		retorno.setEmailAgente(resultSet.getString("emailAgente"));
		retorno.getTexto().setTitulo(resultSet.getString("titulo"));
		retorno.setEmailEmpresa(resultSet.getString("emailEmpresa"));

		return retorno;
	}

}
