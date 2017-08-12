package by.epam.library.dao.implementations;

import java.sql.*;
import java.util.*;
import by.epam.library.constants.Constants;
import by.epam.library.dao.BookDAO;
import by.epam.library.dao.connection.ConnectionPool;
import by.epam.library.dao.constants.ColumnLabel;
import by.epam.library.dao.constants.SQLQueries;
import by.epam.library.dao.exception.ConnectionPoolException;
import by.epam.library.dao.exception.DAOException;
import by.epam.library.entities.Book;

public class BookDAOImpl implements BookDAO {

	@Override
	public void addNewBook(String title, String author, String genre, String year, int quantity) throws DAOException {
		ConnectionPool connectionPool = ConnectionPool.getInstance();
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		try {
			connection = connectionPool.take();
			preparedStatement = connection.prepareStatement(SQLQueries.INSERT_BOOK);
			preparedStatement.setString(1, title);
			preparedStatement.setString(2, author);
			preparedStatement.setString(3, genre);
			preparedStatement.setString(4, year);
			preparedStatement.setInt(5, quantity);
			preparedStatement.executeUpdate();
		} catch (ConnectionPoolException e) {
			throw new DAOException(Constants.CONNECTING_TO_DB_ERROR, e);
		} catch (SQLException e) {
			throw new DAOException(Constants.EXECUTE_QUERY_INSERT_BOOK_ERROR, e);
		} finally {
			connectionPool.closeConnection(connection, preparedStatement);
		}
	}

	@Override
	public void addEditBook(String title, String genre, String author, String year, int quantity, int idBook)
			throws DAOException {
		ConnectionPool connectionPool = ConnectionPool.getInstance();
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		try {
			connection = connectionPool.take();
			preparedStatement = connection.prepareStatement(SQLQueries.UPDATE_BOOK);
			preparedStatement.setString(1, title);
			preparedStatement.setString(2, author);
			preparedStatement.setString(3, genre);
			preparedStatement.setString(4, year);
			preparedStatement.setInt(5, quantity);
			preparedStatement.setInt(6, idBook);
			preparedStatement.executeUpdate();
		} catch (ConnectionPoolException e) {
			throw new DAOException(Constants.CONNECTING_TO_DB_ERROR, e);
		} catch (SQLException e) {
			throw new DAOException(Constants.EXECUTE_QUERY_UPDATE_BOOK_ERROR, e);
		} finally {
			connectionPool.closeConnection(connection, preparedStatement);
		}
	}

	@Override
	public void removeBook(int idBook) throws DAOException {
		ConnectionPool connectionPool = ConnectionPool.getInstance();
		Connection connection = null;
		PreparedStatement statement = null;
		try {
			connection = connectionPool.take();
			statement = connection.prepareStatement(SQLQueries.REMOVE_BOOK);
			statement.setInt(1, idBook);
			statement.executeUpdate();
		} catch (ConnectionPoolException e) {
			throw new DAOException(Constants.CONNECTING_TO_DB_ERROR, e);
		} catch (SQLException e) {
			throw new DAOException(Constants.EXECUTE_QUERY_REMOVE_BOOK_ERROR, e);
		} finally {
			connectionPool.closeConnection(connection, statement);
		}
	}

	@Override
	public List<Book> getBooklist() throws DAOException {
		ConnectionPool connectionPool = ConnectionPool.getInstance();
		Connection connection = null;
		Statement statement = null;
		ResultSet resultSet = null;
		List<Book> booklist = null;
		try {
			connection = connectionPool.take();
			statement = connection.createStatement();
			resultSet = statement.executeQuery(SQLQueries.SELECT_BOOK);
			booklist = new ArrayList<Book>();
			Book book = null;
			while (resultSet.next()) {
				book = new Book();
				book.setId(resultSet.getInt(ColumnLabel.BOOK_ID));
				book.setTitle(resultSet.getString(ColumnLabel.BOOK_TITLE));
				book.setAuthor(resultSet.getString(ColumnLabel.BOOK_AUTHOR));
				book.setGenre(resultSet.getString(ColumnLabel.BOOK_GENRE));
				book.setYear(resultSet.getString(ColumnLabel.BOOK_YEAR));
				book.setQuantity(resultSet.getInt(ColumnLabel.BOOK_QUANTITY));
				book.setStatus(resultSet.getBoolean(ColumnLabel.BOOK_STATUS));
				booklist.add(book);
			}
		} catch (ConnectionPoolException e) {
			throw new DAOException(Constants.CONNECTING_TO_DB_ERROR, e);
		} catch (SQLException e) {
			throw new DAOException(Constants.EXECUTE_QUERY_SELECT_BOOK_ERROR, e);
		} finally {
			connectionPool.closeConnection(connection, statement, resultSet);
		}
		return booklist;
	}

}
