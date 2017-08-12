package by.epam.library.service;

import org.springframework.context.ApplicationContext;
import by.epam.library.service.exception.ServiceException;

public interface InitializationService {
	void initialization(ApplicationContext context) throws ServiceException;
	void destroy(ApplicationContext context) throws ServiceException;
}
