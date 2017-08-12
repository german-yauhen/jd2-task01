package by.epam.library.service;

import java.util.List;

import org.springframework.context.ApplicationContext;

import by.epam.library.entities.Book;
import by.epam.library.service.exception.ServiceException;

public interface BookService {
	void addNewBook(String title, String genre, String author, String year, String quantity, ApplicationContext context) throws ServiceException;
	void addEditBook(String title, String genre, String author, String year, String quantity, String idBook, ApplicationContext context) throws ServiceException;
	void removeBook(String idBook, ApplicationContext context) throws ServiceException;
	List<Book> getBooklist(ApplicationContext context) throws ServiceException;
	
}
