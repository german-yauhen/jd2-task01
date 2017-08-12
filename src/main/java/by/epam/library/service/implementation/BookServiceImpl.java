package by.epam.library.service.implementation;

import java.util.IllegalFormatException;
import java.util.List;

import org.springframework.context.ApplicationContext;

import by.epam.library.constants.Constants;
import by.epam.library.dao.BookDAO;
import by.epam.library.dao.exception.DAOException;
import by.epam.library.dao.factory.DAOFactory;
import by.epam.library.entities.Book;
import by.epam.library.service.BookService;
import by.epam.library.service.exception.ServiceException;
import by.epam.library.service.validation.ValidationData;

public class BookServiceImpl implements BookService {

	@Override
	public void addNewBook(String title, String genre, String author, String year, String quantityStr, ApplicationContext context) throws ServiceException {
		if(!ValidationData.validBook(title, genre, author, year, quantityStr)){
			throw new ServiceException(Constants.INCORRECT_BOOK_DATA);
		}
		int quantity = 0;
		try{
			quantity = Integer.parseInt(quantityStr);
		}catch (IllegalFormatException e) {
			throw new ServiceException(Constants.YEAR_FORMAT_ERROR);
		}
		DAOFactory daoFactory = context.getBean(Constants.DAO_FACTORY, DAOFactory.class);
		BookDAO bookDAO = daoFactory.getBookDAO();
		try {
			bookDAO.addNewBook(title, author, genre, year, quantity);
		} catch (DAOException e) {
			throw new ServiceException(Constants.ADDING_BOOK_TO_LIBRARY_ERROR);
		}
		
	}

	@Override
	public void addEditBook(String title, String genre, String author, String year, String quantityStr, String idBookStr, ApplicationContext context) throws ServiceException {
		if(!ValidationData.validBook(title, genre, author, year, quantityStr, idBookStr)){
			throw new ServiceException(Constants.INCORRECT_BOOK_DATA);
		}
		int idBook = Integer.parseInt(idBookStr);
		int quantity = Integer.parseInt(quantityStr);
		DAOFactory daoFactory = context.getBean(Constants.DAO_FACTORY, DAOFactory.class);
		BookDAO bookDAO = daoFactory.getBookDAO();
		try {
			bookDAO.addEditBook(title, genre, author, year, quantity, idBook);
		} catch (DAOException e) {
			throw new ServiceException(Constants.EDIT_BOOK_ERROR);
		}
	}
	
	@Override
	public void removeBook(String idBookStr, ApplicationContext context) throws ServiceException {
		int idBook = Integer.parseInt(idBookStr);
		DAOFactory daoFactory = context.getBean(Constants.DAO_FACTORY, DAOFactory.class);
		BookDAO bookDAO = daoFactory.getBookDAO();
		try {
			bookDAO.removeBook(idBook);
		} catch (DAOException e) {
			throw new ServiceException(Constants.REMOVE_BOOK_ERROR);
		}
		
	}

	@Override
	public List<Book> getBooklist(ApplicationContext context) throws ServiceException {
		DAOFactory daoFactory = context.getBean(Constants.DAO_FACTORY, DAOFactory.class);
		BookDAO bookDAO = daoFactory.getBookDAO();
		List<Book> booklist = null;
		try {
			booklist = bookDAO.getBooklist();
		} catch (DAOException e) {
			throw new ServiceException(e);
		}
		if(booklist == null){
			throw new ServiceException(Constants.BOOKLIST_NOT_FOUND);
		}
		return booklist;
	}

}