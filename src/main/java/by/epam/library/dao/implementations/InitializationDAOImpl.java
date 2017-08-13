package by.epam.library.dao.implementations;

import java.io.IOException;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import by.epam.library.constants.Constants;
import by.epam.library.dao.InitializationDAO;
import by.epam.library.dao.connection.ConnectionPool;
import by.epam.library.dao.exception.ConnectionPoolException;
import by.epam.library.dao.exception.DAOException;

public class InitializationDAOImpl implements InitializationDAO {
	
	private static final ApplicationContext context = new ClassPathXmlApplicationContext(Constants.APPLICATION_CONTEXT);
	private ConnectionPool connectionPool;

	@Override
	public void initialization() throws DAOException {
		connectionPool = context.getBean(Constants.CONNECTION_POOL, ConnectionPool.class);
		try {
			connectionPool.init();
		} catch (ConnectionPoolException e) {
			throw new DAOException(Constants.INIT_DB_ERROR, e);
		}
	}

	@Override
	public void destroy() throws DAOException {
		connectionPool = context.getBean(Constants.CONNECTION_POOL, ConnectionPool.class);
		try {
			connectionPool.close();
		} catch (IOException e) {
			throw new DAOException(Constants.CLOSE_CONNECTIONS_ERROR, e);
		}
	}

}
