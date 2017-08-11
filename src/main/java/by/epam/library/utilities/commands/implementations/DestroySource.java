package by.epam.library.utilities.commands.implementations;

import org.apache.log4j.Logger;
import org.springframework.context.ApplicationContext;
import by.epam.library.constants.Constants;
import by.epam.library.service.InitializationService;
import by.epam.library.service.exception.ServiceException;
import by.epam.library.service.factory.ServiceFactory;
import by.epam.library.utilities.commands.Command;

public class DestroySource implements Command {
	private static final Logger logger = Logger.getLogger(DestroySource.class);

	public String executeCommand(String request, ApplicationContext context) {
		ServiceFactory serviceFactory = context.getBean(Constants.SERVICE_FACTORY, ServiceFactory.class);
		InitializationService initializationService = serviceFactory.getInitializationService();
		String response = null;
		try {
			initializationService.destroy();
			response = Constants.DATABASE_HAS_BEEN_DESTROYED;
		} catch (ServiceException e) {
			response = Constants.DATABASE_HASNOT_BEEN_DESTROYED;
			logger.error(e);	
		}
		return response;
	}

}
