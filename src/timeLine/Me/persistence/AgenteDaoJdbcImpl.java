package timeLine.Me.persistence;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

import timeLine.Me.model.Agente;


public class AgenteDaoJdbcImpl implements AgenteDao {
	
	private static AgenteDao instance = new AgenteDaoJdbcImpl();

	public static AgenteDao getInstance() {
		return instance;
	}

	@Override
	public void insert(Agente agente) throws PersistenceException {

		Transaction tx = TransactionJdbcImpl.getInstance();
		Connection conn = tx.getConnection();

		try {
			tx.begin();
			String query = "insert into agente (nombreApellido, emailAgente, agentePassword, emailEmpresa) values (?, ?, ?, ?)";
			PreparedStatement statement = TransactionJdbcImpl.getInstance()
					.getConnection().prepareStatement(query);
			statement.setString(1, agente.getNombre());
			statement.setString(2, agente.getEmailAgente());
			statement.setString(3, agente.getPassword());
			statement.setString(4, agente.getEmailEmpresa());

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
	public void delete(Agente agente) throws PersistenceException {
		Transaction tx = TransactionJdbcImpl.getInstance();
		Connection conn = tx.getConnection();

		try {
			tx.begin();

			String query = "delete from agente where emailAgente = ?";
			PreparedStatement statement = conn.prepareStatement(query);
			statement.setString(1, agente.getEmailAgente());
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
	public void update(Agente agente) throws PersistenceException {
		try {
			String query = "update agente set emailAgente = ?, nombreApellido = ?, agentePassword = ?, emailEmpresa = ? where emailAgente = ?";

			PreparedStatement statement = TransactionJdbcImpl.getInstance()
					.getConnection().prepareStatement(query);
			statement.setString(1, agente.getEmailAgente());
			statement.setString(2, agente.getNombre());
			statement.setString(3, agente.getPassword());
			statement.setString(4, agente.getEmailEmpresa());
			statement.executeUpdate();
		} catch (SQLException sqlException) {
			throw new PersistenceException(sqlException);
		}
	}
	
	@Override
	public List<Agente> findAll() throws PersistenceException {
		List<Agente> lista = new LinkedList<Agente>();
		try {
			String query = "select * from agente";
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
	public Agente findById(String emailAgente) throws PersistenceException {
		if (emailAgente == null) {
			throw new IllegalArgumentException(
					"El email de empresa no debe ser nulo");
		}
		Agente agente = null;
		try {
			Connection c = ConnectionProvider.getInstance().getConnection();
			String query = "select * from agente where emailAgente = ?";
			PreparedStatement statement = c.prepareStatement(query);
			statement.setString(1, emailAgente);
			ResultSet resultSet = statement.executeQuery();
			if (resultSet.next()) {
				agente = convertOne(resultSet);
			}
		} catch (SQLException sqlException) {
			throw new PersistenceException(sqlException);
		}
		return agente;
	}

	private Agente convertOne(ResultSet resultSet) throws SQLException {
		Agente retorno = new Agente();

		retorno.setEmailAgente(resultSet.getString("emailAgente"));
		retorno.setNombre(resultSet.getString("nombreApellido"));
		retorno.setPassword(resultSet.getString("agentePassword"));
		retorno.setEmailEmpresa(resultSet.getString("emailEmpresa"));
		return retorno;
	}



}
