package by.epam.library.dao.implementations;

import java.sql.*;
import by.epam.library.constants.Constants;
import by.epam.library.dao.UserDAO;
import by.epam.library.dao.connection.ConnectionPool;
import by.epam.library.dao.constants.ColumnLabel;
import by.epam.library.dao.constants.SQLQueries;
import by.epam.library.dao.exception.ConnectionPoolException;
import by.epam.library.dao.exception.DAOException;
import by.epam.library.entities.User;

public class UserDAOImpl implements UserDAO {

	@Override
	public User signIn(String login, int password) throws DAOException {
		ConnectionPool connectionPool = ConnectionPool.getInstance();
		PreparedStatement preparedStatement = null;
		Connection connection = null;
		ResultSet resultSet = null;
		User user = null;
		try {
			connection = connectionPool.take();
			preparedStatement = connection.prepareStatement(SQLQueries.SELECT_USER_BY_LOGIN_PASSWORD);
			preparedStatement.setString(1, login);
			preparedStatement.setInt(2, password);
			resultSet = preparedStatement.executeQuery();
			while (resultSet.next()) {
				user = new User();
				user.setId(resultSet.getInt(ColumnLabel.USER_ID));
				user.setLogin(resultSet.getString(ColumnLabel.USER_LOGIN));
				user.setPassword(resultSet.getInt(ColumnLabel.USER_PASSWORD));
			}
		} catch (ConnectionPoolException e) {
			throw new DAOException(Constants.CONNECTING_TO_DB_ERROR, e);
		} catch (SQLException e) {
			throw new DAOException(Constants.EXECUTE_QUERY_SELECT_USER_ERROR, e);
		} finally {
			connectionPool.closeConnection(connection, preparedStatement, resultSet);
		}
		return user;
	}

	@Override
	public void signUp(String login, int password) throws DAOException {
		ConnectionPool connectionPool = ConnectionPool.getInstance();
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		try {
			connection = connectionPool.take();
			preparedStatement = connection.prepareStatement(SQLQueries.INSERT_USER);
			preparedStatement.setString(1, login);
			preparedStatement.setInt(2, password);
			preparedStatement.executeUpdate();
		} catch (ConnectionPoolException e) {
			throw new DAOException(Constants.CONNECTING_TO_DB_ERROR, e);
		} catch (SQLException e) {
			throw new DAOException(Constants.EXECUTE_QUERY_INSERT_USER_ERROR, e);
		} finally {
			connectionPool.closeConnection(connection, preparedStatement);
		}
	}

}
