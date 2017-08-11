package by.epam.library.view;

import org.apache.log4j.Logger;

public final class PrintResponse {
	private static final Logger logger = Logger.getLogger(PrintResponse.class);
	private PrintResponse() {}
	
	public static void out(String response){
		logger.info(response);
	}
}