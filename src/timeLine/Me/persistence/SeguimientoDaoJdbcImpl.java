package timeLine.Me.persistence;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

import timeLine.Me.model.SeguimientoEmpresa;


public class SeguimientoDaoJdbcImpl implements SeguimientoDao {
	
	private static SeguimientoDao instance = new SeguimientoDaoJdbcImpl();

	public static SeguimientoDao getInstance() {
		return instance;
	}

	@Override
	public void insert(SeguimientoEmpresa seguimiento) throws PersistenceException {

		Transaction tx = TransactionJdbcImpl.getInstance();
		Connection conn = (Connection) tx.getConnection();

		try {
			tx.begin();
			String query = "insert into seguimientoEmpresa (emailEmpresa, emailAgente) values (?, ?)";
			PreparedStatement statement = TransactionJdbcImpl.getInstance()
					.getConnection().prepareStatement(query);
			statement.setString(1, seguimiento.getEmailEmpresa());
			statement.setString(2, seguimiento.getEmailAgente());
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
	public void delete(SeguimientoEmpresa seguimiento) throws PersistenceException {
		Transaction tx = TransactionJdbcImpl.getInstance();
		Connection conn = tx.getConnection();

		try {
			tx.begin();

			String query = "delete from seguimientoEmpresa where emailEmpresa = ? and emailAgente = ?";
			PreparedStatement statement = conn.prepareStatement(query);
		    statement.setString(1, seguimiento.getEmailEmpresa());
			statement.setString(2, seguimiento.getEmailAgente());
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
	public SeguimientoEmpresa findById(String emailEmpresa, String emailAgente) throws PersistenceException {
		if (emailEmpresa == null || emailAgente == null) {
			throw new IllegalArgumentException(
					"El ninguno de los email puede debe ser nulo");
		}
		SeguimientoEmpresa seguimiento = null;
		try {
			Connection c = ConnectionProvider.getInstance().getConnection();
			String query = "select * from seguimientoEmpresa where emailEmpresa = ? and emailAgente = ? ";
			PreparedStatement statement = c.prepareStatement(query);
			statement.setString(1, emailEmpresa);
			statement.setString(2, emailAgente);
			ResultSet resultSet = statement.executeQuery();
			if (resultSet.next()) {
				seguimiento = convertOneSeguimiento(resultSet);
			}
		} catch (SQLException sqlException) {
			throw new PersistenceException(sqlException);
		}
		return seguimiento;
	}
	
	@Override
    public List<SeguimientoEmpresa> findAll() throws PersistenceException {
		List<SeguimientoEmpresa> lista = new LinkedList<SeguimientoEmpresa>();
		try {
			String query = "select * from seguimientoEmpresa";
			PreparedStatement statement = ConnectionProvider.getInstance()
					.getConnection().prepareStatement(query);
			ResultSet resultSet = statement.executeQuery();
			while (resultSet.next()) {
				lista.add(convertOneSeguimiento(resultSet));
			}
		} catch (SQLException sqlException) {
			throw new PersistenceException(sqlException);
		}
		return lista;
	}
	

     private SeguimientoEmpresa convertOneSeguimiento(ResultSet resultSet) throws SQLException {
		SeguimientoEmpresa retorno = new SeguimientoEmpresa();
        retorno.setEmailEmpresa(resultSet.getString("emailEmpresa"));
		retorno.setEmailAgente(resultSet.getString("emailAgente"));
		return retorno;
	}
}
