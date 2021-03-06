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

public class AddNewBook implements Command {
	
	private static final int TITLE_PARAM = 1;
	private static final int GENRE_PARAM = 2;
	private static final int AUTHOR_PARAM = 3;
	private static final int YEAR_PARAM = 4;
	private static final int QUANTITY = 5;
	private static final Logger logger = Logger.getLogger(AddNewBook.class);
	private ServiceFactory serviceFactory;
	
	@SuppressWarnings("resource")
	public String executeCommand(String request) {
		String [] parameter = request.split(Constants.SPACE);
		String title = parameter[TITLE_PARAM];
		String genre = parameter[GENRE_PARAM];
		String author = parameter[AUTHOR_PARAM];
		String year = parameter[YEAR_PARAM];
		String quantity = parameter[QUANTITY];
		ApplicationContext context = new ClassPathXmlApplicationContext(Constants.APPLICATION_CONTEXT);
		serviceFactory = context.getBean(Constants.SERVICE_FACTORY, ServiceFactory.class);
		BookService bookService = serviceFactory.getBookService();
		String response = null;
		try {
			bookService.addNewBook(title, genre, author, year, quantity);
			response = Constants.BOOK_ADDED;
		} catch (ServiceException e) {
			logger.log(Level.ERROR, e);
			response = Constants.ERROR_ADDING_BOOK;
		}
		return response;
	}

}
