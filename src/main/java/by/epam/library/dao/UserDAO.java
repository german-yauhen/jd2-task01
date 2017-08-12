package by.epam.library.dao;

import by.epam.library.dao.exception.DAOException;
import by.epam.library.entities.User;

public interface UserDAO {
	
	User signIn(String login, int password) throws DAOException;
	void signUp(String login, int password) throws DAOException;
	
}
