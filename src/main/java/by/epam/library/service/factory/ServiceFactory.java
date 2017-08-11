package by.epam.library.service.factory;

import by.epam.library.service.BookService;
import by.epam.library.service.InitializationService;
import by.epam.library.service.UserService;

public final class ServiceFactory {
	private UserService userService;
	private BookService bookService;
	private InitializationService initializationService;
	
	private ServiceFactory() {}

	public UserService getUserService() {
		return userService;
	}

	public void setUserService(UserService userService) {
		this.userService = userService;
	}

	public BookService getBookService() {
		return bookService;
	}

	public void setBookService(BookService bookService) {
		this.bookService = bookService;
	}

	public InitializationService getInitializationService() {
		return initializationService;
	}

	public void setInitializationService(InitializationService initializationService) {
		this.initializationService = initializationService;
	}
	
}
