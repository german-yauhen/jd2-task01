package by.epam.library.utilities.commands.implementations;

import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import by.epam.library.constants.Constants;
import by.epam.library.service.UserService;
import by.epam.library.service.exception.ServiceException;
import by.epam.library.service.factory.ServiceFactory;
import by.epam.library.utilities.commands.Command;

public class SignUp implements Command {
	
	private static final int LOGIN_PARAM = 1;
	private static final int PASSWORD_PARAM = 2;
	private ServiceFactory serviceFactory;
	private static final Logger logger = Logger.getLogger(SignUp.class);


	@SuppressWarnings("resource")
	public String executeCommand(String request) {
		String [] parameter = request.split(Constants.SPACE);
		String login = parameter[LOGIN_PARAM];
		String password = parameter[PASSWORD_PARAM];
		ApplicationContext context = new ClassPathXmlApplicationContext(Constants.APPLICATION_CONTEXT);
		serviceFactory = context.getBean(Constants.SERVICE_FACTORY, ServiceFactory.class);
		UserService userService = serviceFactory.getUserService();
		String response = null;
		try {
			userService.signUp(login, password);
			response = Constants.USER_WAS_REGISTERED + login;
		} catch (ServiceException e) { 
			response = Constants.SIGN_UP_ERROR;
			logger.log(Level.ERROR, e);
		}
		return response;
	}

}