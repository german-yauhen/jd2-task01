package by.epam.library.utilities.commands.implementations;

import java.util.List;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import by.epam.library.constants.Constants;
import by.epam.library.entities.Book;
import by.epam.library.service.BookService;
import by.epam.library.service.exception.ServiceException;
import by.epam.library.service.factory.ServiceFactory;
import by.epam.library.utilities.commands.Command;
import by.epam.library.view.PrintResponse;

public class GetBookList implements Command {
	
	private ServiceFactory serviceFactory;
	private static final Logger logger = Logger.getLogger(GetBookList.class);

	@SuppressWarnings("resource")
	public String executeCommand(String request) {
		ApplicationContext context = new ClassPathXmlApplicationContext(Constants.APPLICATION_CONTEXT);
		serviceFactory = context.getBean(Constants.SERVICE_FACTORY, ServiceFactory.class);
		BookService bookService = serviceFactory.getBookService();
		List<Book> booklist = null;
		String response = null;
		try {
			booklist = bookService.getBooklist();			
			for(Book book: booklist){
				PrintResponse.out(book.getAuthor() + Constants.SPACE + book.getTitle());
			}
			response = Constants.LIST_OF_BOOKS_RECEIVED;
		} catch (ServiceException e) {
			response = Constants.ERROR_GETTING_BOOKS;
			logger.log(Level.ERROR, e);
		}
		return response;
	}

}
