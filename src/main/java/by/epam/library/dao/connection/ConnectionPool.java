package by.epam.library.dao.connection;

import java.io.*;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import by.epam.library.constants.Constants;
import by.epam.library.dao.connection.manager.DBParameter;
import by.epam.library.dao.connection.manager.DBResourceManager;
import by.epam.library.dao.exception.ConnectionPoolException;
import by.epam.library.dao.exception.DAOException;

public final class ConnectionPool implements Closeable {
	private static final Logger logger = Logger.getLogger(ConnectionPool.class);
	private static final ConnectionPool instance = new ConnectionPool();
	private static final int poolSizeValue = 6;
	private BlockingQueue<Connection> freeConnection;
	private BlockingQueue<Connection> busyConnection;
	private int poolsize;
	private String driver;
	private String user;
	private String password;
	private String url;

	private ConnectionPool() {
		DBResourceManager resourceManager = DBResourceManager.getInstance();
		this.driver = resourceManager.getValue(DBParameter.DB_DRIVER);
		this.user = resourceManager.getValue(DBParameter.DB_USER);
		this.password = resourceManager.getValue(DBParameter.DB_PASSWORD);
		this.url = resourceManager.getValue(DBParameter.DB_URL);

		try {
			this.poolsize = Integer.parseInt(resourceManager.getValue(DBParameter.DB_POOLSIZE));
		} catch (NumberFormatException e) {
			this.poolsize = poolSizeValue;
		}
	}

	public void init() throws ConnectionPoolException {
		freeConnection = new ArrayBlockingQueue<Connection>(poolsize);
		busyConnection = new ArrayBlockingQueue<Connection>(poolsize);

		try {
			Class.forName(driver);
			for (int i = 0; i < poolsize; i++) {
				freeConnection.add(DriverManager.getConnection(url, user, password));
			}
		} catch (ClassNotFoundException e) {
			throw new ConnectionPoolException(Constants.DB_DRIVER_NOT_FOUND, e);
		} catch (SQLException e) {
			throw new ConnectionPoolException(Constants.SQL_EXCEPTION_IN_CONNECTIONPOOL, e);
		}

	}

	public Connection take() throws ConnectionPoolException {
		Connection connection = null;
		try {
			connection = freeConnection.take();
			busyConnection.put(connection);
		} catch (InterruptedException e) {
			throw new ConnectionPoolException(Constants.CONNECTING_TO_DATA_SOURCE_ERROR, e);
		}
		return connection;
	}

	public void free(Connection connection) throws InterruptedException, DAOException {
		if (connection == null) {
			throw new DAOException(Constants.CONNECTION_IS_NULL);
		}
		Connection tempConnection = connection;
		connection = null;
		busyConnection.remove(tempConnection);
		freeConnection.put(tempConnection);
	}

	public static ConnectionPool getInstance() {
		return instance;
	}

	@Override
	public void close() throws IOException {
		List<Connection> listConnection = new ArrayList<Connection>();
		listConnection.addAll(this.busyConnection);
		listConnection.addAll(this.freeConnection);
		for (Connection connection : listConnection) {
			try {
				if (connection != null) {
					connection.close();
				}
			} catch (SQLException e) {
				logger.error(e);
			}
		}
	}

	public void closeConnection(Connection con, Statement st, PreparedStatement preSt, ResultSet rs) {
		if (con != null) {
			try {
				free(con);
			} catch (InterruptedException | DAOException e) {
				logger.log(Level.ERROR, Constants.CONNECTION_NOT_RETURNED, e);
			}
		}

		if (st != null) {
			try {
				st.close();
			} catch (SQLException e) {
				logger.log(Level.ERROR, Constants.STATEMENT_NOT_CLOSED, e);
			}
		}

		if (preSt != null) {
			try {
				preSt.close();
			} catch (SQLException e) {
				logger.log(Level.ERROR, Constants.PREPARED_STATEMENT_NOT_CLOSED, e);
			}
		}

		if (rs != null) {
			try {
				rs.close();
			} catch (SQLException e) {
				logger.log(Level.ERROR, Constants.RESULT_SET_NOT_CLOSED, e);
			}
		}
	}

	public void closeConnection(Connection con, Statement st) {
		if (con != null) {
			try {
				free(con);
			} catch (InterruptedException | DAOException e) {
				logger.log(Level.ERROR, Constants.CONNECTION_NOT_RETURNED, e);
			}
		}

		if (st != null) {
			try {
				st.close();
			} catch (SQLException e) {
				logger.log(Level.ERROR, Constants.STATEMENT_NOT_CLOSED, e);
			}
		}
	}

	public void closeConnection(Connection con, PreparedStatement preSt) {
		if (con != null) {
			try {
				free(con);
			} catch (InterruptedException | DAOException e) {
				logger.log(Level.ERROR, Constants.CONNECTION_NOT_RETURNED, e);
			}
		}

		if (preSt != null) {
			try {
				preSt.close();
			} catch (SQLException e) {
				logger.log(Level.ERROR, Constants.PREPARED_STATEMENT_NOT_CLOSED, e);
			}
		}
	}

	public void closeConnection(Connection con, ResultSet rs) {
		if (con != null) {
			try {
				free(con);
			} catch (InterruptedException | DAOException e) {
				logger.log(Level.ERROR, Constants.CONNECTION_NOT_RETURNED, e);
			}
		}

		if (rs != null) {
			try {
				rs.close();
			} catch (SQLException e) {
				logger.log(Level.ERROR, Constants.RESULT_SET_NOT_CLOSED, e);
			}
		}
	}

	public void closeConnection(Connection con, Statement st, PreparedStatement preSt) {
		if (con != null) {
			try {
				free(con);
			} catch (InterruptedException | DAOException e) {
				logger.log(Level.ERROR, Constants.CONNECTION_NOT_RETURNED, e);
			}
		}

		if (st != null) {
			try {
				st.close();
			} catch (SQLException e) {
				logger.log(Level.ERROR, Constants.STATEMENT_NOT_CLOSED, e);
			}
		}

		if (preSt != null) {
			try {
				preSt.close();
			} catch (SQLException e) {
				logger.log(Level.ERROR, Constants.PREPARED_STATEMENT_NOT_CLOSED, e);
			}
		}

	}

	public void closeConnection(Connection con, PreparedStatement preSt, ResultSet rs) {
		if (con != null) {
			try {
				free(con);
			} catch (InterruptedException | DAOException e) {
				logger.log(Level.ERROR, Constants.CONNECTION_NOT_RETURNED, e);
			}
		}

		if (preSt != null) {
			try {
				preSt.close();
			} catch (SQLException e) {
				logger.log(Level.ERROR, Constants.PREPARED_STATEMENT_NOT_CLOSED, e);
			}
		}

		if (rs != null) {
			try {
				rs.close();
			} catch (SQLException e) {
				logger.log(Level.ERROR, Constants.RESULT_SET_NOT_CLOSED, e);
			}
		}
	}

	public void closeConnection(Connection con, Statement st, ResultSet rs) {
		if (con != null) {
			try {
				free(con);
			} catch (InterruptedException | DAOException e) {
				logger.log(Level.ERROR, Constants.CONNECTION_NOT_RETURNED, e);
			}
		}

		if (st != null) {
			try {
				st.close();
			} catch (SQLException e) {
				logger.log(Level.ERROR, Constants.STATEMENT_NOT_CLOSED, e);
			}
		}

		if (rs != null) {
			try {
				rs.close();
			} catch (SQLException e) {
				logger.log(Level.ERROR, Constants.RESULT_SET_NOT_CLOSED, e);
			}
		}
	}

}
