package by.epam.library.utilities.commands.implementations;

import org.apache.log4j.Logger;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import by.epam.library.constants.Constants;
import by.epam.library.service.InitializationService;
import by.epam.library.service.exception.ServiceException;
import by.epam.library.service.factory.ServiceFactory;
import by.epam.library.utilities.commands.Command;

public class InitializationSource implements Command {
	
	private ServiceFactory serviceFactory;
	private static final ApplicationContext context = new ClassPathXmlApplicationContext(Constants.APPLICATION_CONTEXT);
	private static final Logger logger = Logger.getLogger(InitializationSource.class);

	public String executeCommand(String request) {
		serviceFactory = context.getBean(Constants.SERVICE_FACTORY, ServiceFactory.class);
		InitializationService initializationService = serviceFactory.getInitializationService();
		String response = null;
		try {
			initializationService.initialization();
			response = Constants.DATABASE_HAS_BEEN_INITIALIZED;
		} catch (ServiceException e) {
			response = Constants.DATABASE_HASNOT_BEEN_INITIALIZED;
			logger.error(e);	
		}
		return response;
	}

}
