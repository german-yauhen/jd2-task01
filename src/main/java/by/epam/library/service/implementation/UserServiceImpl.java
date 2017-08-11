package by.epam.library.service.implementation;

import by.epam.library.constants.Constants;
import by.epam.library.dao.UserDAO;
import by.epam.library.dao.exception.DAOException;
import by.epam.library.dao.factory.DAOFactory;
import by.epam.library.entities.User;
import by.epam.library.service.UserService;
import by.epam.library.service.exception.ServiceException;
import by.epam.library.service.validation.ValidationData;

public class UserServiceImpl implements UserService {

	@Override
	public void signIn(String login, String password) throws ServiceException {
		if(!ValidationData.validUser(login, password)){
			throw new ServiceException(Constants.INCORRECT_LOGIN_PASSWORD);
		}
		
		DAOFactory daoFactory = DAOFactory.getInstance();
		UserDAO userDAO = daoFactory.getUserDAO();
		
		//Attention String_paswword convert to int_password(HashCode)
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
		if(!ValidationData.validUser(login, password)){
			throw new ServiceException(Constants.INCORRECT_LOGIN_PASSWORD);
		}
		
		DAOFactory daoFactory = DAOFactory.getInstance();
		UserDAO userDAO = daoFactory.getUserDAO();
		
		//Attention String_paswword convert to int_password(HashCode)
		try {
			userDAO.signUp(login, password.hashCode());
		} catch (DAOException e) {
			throw new ServiceException(Constants.SIGN_UP_ERROR, e);
		}
	}

}
