package by.epam.library.service;

import java.util.List;
import by.epam.library.entities.Book;
import by.epam.library.service.exception.ServiceException;

public interface BookService {
	void addNewBook(String title, String genre, String author, String year, String quantity) throws ServiceException;
	void addEditBook(String title, String genre, String author, String year, String quantity, String idBook) throws ServiceException;
	void removeBook(String idBook) throws ServiceException;
	List<Book> getBooklist() throws ServiceException;
	
}
