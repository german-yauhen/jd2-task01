package by.epam.library.service.implementation;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import by.epam.library.constants.Constants;
import by.epam.library.dao.UserDAO;
import by.epam.library.dao.exception.DAOException;
import by.epam.library.dao.factory.DAOFactory;
import by.epam.library.entities.User;
import by.epam.library.service.UserService;
import by.epam.library.service.exception.ServiceException;
import by.epam.library.service.validation.ValidationData;

public class UserServiceImpl implements UserService {
	
	private DAOFactory daoFactory;
	private static final ApplicationContext context = new ClassPathXmlApplicationContext(Constants.APPLICATION_CONTEXT);

	@Override
	public void signIn(String login, String password) throws ServiceException {
		if (!ValidationData.validUser(login, password)) {
			throw new ServiceException(Constants.INCORRECT_LOGIN_PASSWORD);
		}
		daoFactory = context.getBean(Constants.DAO_FACTORY, DAOFactory.class);
		UserDAO userDAO = daoFactory.getUserDAO();
		try {
			User user = userDAO.signIn(login, password.hashCode());
			if(user == null){
				throw new ServiceException(Constants.USER_NOT_FOUND);
			}
		} catch (DAOException e) {
			throw new ServiceException(Constants.SIGN_IN_ERROR, e);
		}
	}

	@Override
	public void signUp(String login, String password) throws ServiceException {
		if (!ValidationData.validUser(login, password)) {
			throw new ServiceException(Constants.INCORRECT_LOGIN_PASSWORD);
		}
		daoFactory = context.getBean(Constants.DAO_FACTORY, DAOFactory.class);
		UserDAO userDAO = daoFactory.getUserDAO();
		try {
			userDAO.signUp(login, password.hashCode());
		} catch (DAOException e) {
			throw new ServiceException(Constants.SIGN_UP_ERROR, e);
		}
	}

}
