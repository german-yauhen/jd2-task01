package by.epam.library.service;

import org.springframework.context.ApplicationContext;

import by.epam.library.service.exception.ServiceException;

public interface UserService {
	void signIn(String login, String password, ApplicationContext context) throws ServiceException;
	void signUp(String login, String password, ApplicationContext context) throws ServiceException;
}
