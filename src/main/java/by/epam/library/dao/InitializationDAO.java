package by.epam.library.dao;

import by.epam.library.dao.exception.DAOException;

public interface InitializationDAO {
	void initialization() throws DAOException;
	void destroy() throws DAOException;
}
