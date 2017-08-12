package by.epam.library.utilities.commands.implementations;

import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import by.epam.library.constants.Constants;
import by.epam.library.service.BookService;
import by.epam.library.service.exception.ServiceException;
import by.epam.library.service.factory.ServiceFactory;
import by.epam.library.utilities.commands.Command;

public class RemoveBook implements Command {
	
	private static final int ID_BOOK_PARAM = 1;
	private ServiceFactory serviceFactory;
	private static final Logger logger = Logger.getLogger(RemoveBook.class);

	@SuppressWarnings("resource")
	public String executeCommand(String request) {
		String [] parameter = request.split(Constants.SPACE);
		String idBook = parameter[ID_BOOK_PARAM];
		ApplicationContext context = new ClassPathXmlApplicationContext(Constants.APPLICATION_CONTEXT);
		serviceFactory = context.getBean(Constants.SERVICE_FACTORY, ServiceFactory.class);
		BookService bookService = serviceFactory.getBookService();
		String response = null;
		try {
			bookService.removeBook(idBook);
			response = Constants.BOOK_REMOVED;
		} catch (ServiceException e) {
			response = Constants.REMOVE_BOOK_ERROR;
			logger.log(Level.ERROR, e);
		}
		return response;
	}

}