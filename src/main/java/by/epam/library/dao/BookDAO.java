package by.epam.library.dao;

import java.util.List;

import by.epam.library.dao.exception.DAOException;
import by.epam.library.entities.Book;

public interface BookDAO {
	void addNewBook(String title, String authro, String genre, String year, int quantity) throws DAOException;
	void addEditBook(String title, String genre, String author, String year, int quantity, int idBook) throws DAOException;
	void removeBook(int idBook) throws DAOException;
	List<Book> getBooklist() throws DAOException;
	
}
