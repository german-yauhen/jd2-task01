package by.epam.library.service;

import by.epam.library.service.exception.ServiceException;

public interface UserService {
	void signIn(String login, String password) throws ServiceException;
	void signUp(String login, String password) throws ServiceException;
}
