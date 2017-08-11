package by.epam.library.dao.factory;

import by.epam.library.dao.BookDAO;
import by.epam.library.dao.InitializationDAO;
import by.epam.library.dao.UserDAO;
import by.epam.library.dao.implementations.BookDAOImpl;
import by.epam.library.dao.implementations.InitializationDAOImpl;
import by.epam.library.dao.implementations.UserDAOImpl;

public final class DAOFactory {
	private static DAOFactory instance = null;
	private final UserDAO userDAO = new UserDAOImpl();
	private final BookDAO bookDAO = new BookDAOImpl();
	private final InitializationDAO initializationDAO = new InitializationDAOImpl();
	
	private DAOFactory() {}

	public static DAOFactory getInstance() {
		if(instance == null){
			instance = new DAOFactory();
		}
		return instance;
	}

	public UserDAO getUserDAO() {
		return userDAO;
	}

	public BookDAO getBookDAO() {
		return bookDAO;
	}

	public InitializationDAO getInitializationDAO() {
		return initializationDAO;
	}
	
}
