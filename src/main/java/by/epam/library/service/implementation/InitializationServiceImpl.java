package by.epam.library.service.implementation;

import org.springframework.context.ApplicationContext;

import by.epam.library.constants.Constants;
import by.epam.library.dao.InitializationDAO;
import by.epam.library.dao.exception.DAOException;
import by.epam.library.dao.factory.DAOFactory;
import by.epam.library.service.InitializationService;
import by.epam.library.service.exception.ServiceException;

public class InitializationServiceImpl implements InitializationService {

	@Override
	public void initialization(ApplicationContext context) throws ServiceException {
		DAOFactory daoFactory = context.getBean(Constants.DAO_FACTORY, DAOFactory.class);
		InitializationDAO initializationDAO = daoFactory.getInitializationDAO();
		try {
			initializationDAO.initialization();
		} catch (DAOException e) {
			throw new ServiceException(Constants.INIT_ERROR, e);
		}
	}

	@Override
	public void destroy(ApplicationContext context) throws ServiceException {
		DAOFactory daoFactory = context.getBean(Constants.DAO_FACTORY, DAOFactory.class);
		InitializationDAO initializationDAO = daoFactory.getInitializationDAO();
		try {
			initializationDAO.destroy();
		} catch (DAOException e) {
			throw new ServiceException(Constants.DESTROY_ERROR, e);
		}
	}

}
