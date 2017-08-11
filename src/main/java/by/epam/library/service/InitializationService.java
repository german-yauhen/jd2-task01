package by.epam.library.service;

import by.epam.library.service.exception.ServiceException;

public interface InitializationService {
	void initialization() throws ServiceException;
	void destroy() throws ServiceException;
}
