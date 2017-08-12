package by.epam.library.dao.factory;

import by.epam.library.dao.BookDAO;
import by.epam.library.dao.InitializationDAO;
import by.epam.library.dao.UserDAO;

public final class DAOFactory {
	private UserDAO userDAO;
	private BookDAO bookDAO;
	private InitializationDAO initializationDAO;
	
	private DAOFactory() {}

	public UserDAO getUserDAO() {
		return userDAO;
	}

	public void setUserDAO(UserDAO userDAO) {
		this.userDAO = userDAO;
	}

	public BookDAO getBookDAO() {
		return bookDAO;
	}

	public void setBookDAO(BookDAO bookDAO) {
		this.bookDAO = bookDAO;
	}

	public InitializationDAO getInitializationDAO() {
		return initializationDAO;
	}

	public void setInitializationDAO(InitializationDAO initializationDAO) {
		this.initializationDAO = initializationDAO;
	}
	
}
