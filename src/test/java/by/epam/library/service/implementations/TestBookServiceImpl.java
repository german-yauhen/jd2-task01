package by.epam.library.service.implementations;

import java.io.IOException;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import by.epam.library.constants.Constants;
import by.epam.library.dao.connection.ConnectionPool;
import by.epam.library.dao.exception.ConnectionPoolException;
import by.epam.library.service.BookService;
import by.epam.library.service.exception.ServiceException;
import by.epam.library.service.factory.ServiceFactory;

public class TestBookServiceImpl {
	
	private static final ApplicationContext context = new ClassPathXmlApplicationContext(Constants.APPLICATION_CONTEXT);
	private final ServiceFactory serviceFactory = context.getBean(Constants.SERVICE_FACTORY, ServiceFactory.class);
	private final BookService bookService = serviceFactory.getBookService();
	private final String AUTHOR_PARAM = "MyAuthor";
	private final String GENRE_PARAM = "MyGenre";
	private final String YEAR_PARAM = "2017";
	private final String QUANTITY_PARAM = "10";
	private final String ID_BOOK_PARAM = "1";
	
	
	@BeforeClass
	public static void initSource() throws ConnectionPoolException{
		ConnectionPool connectionPool = context.getBean(Constants.CONNECTION_POOL, ConnectionPool.class);
		connectionPool.init();
	}

	@AfterClass
	public static void destroySource() throws ConnectionPoolException, IOException{
		ConnectionPool connectionPool = context.getBean(Constants.CONNECTION_POOL, ConnectionPool.class);
		connectionPool.close();
	}
	
	
	@Test  (expected = ServiceException.class)
	public void testAddNewBook() throws ServiceException{ 
		bookService.addNewBook(null, null, null, null, null);
	}

	@Test
	public void testAddEditBook(){
		try {
			bookService.addEditBook(null, AUTHOR_PARAM, GENRE_PARAM, YEAR_PARAM, QUANTITY_PARAM, ID_BOOK_PARAM);
		} catch (ServiceException e) {
			Assert.assertEquals(Constants.INCORRECT_BOOK_DATA, e.getMessage());
		}
	}
	
}
