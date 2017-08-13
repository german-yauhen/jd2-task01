package by.epam.library.dao.connection.manager;

import java.util.ResourceBundle;
import by.epam.library.constants.Constants;

public final class DBResourceManager {
	
	private final ResourceBundle bundle = ResourceBundle.getBundle(Constants.DATABASE);
	
	private DBResourceManager() {}
	
	public String getValue(String key){
		return bundle.getString(key);
	}
	
}
