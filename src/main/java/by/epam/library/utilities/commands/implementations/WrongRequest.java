package by.epam.library.utilities.commands.implementations;

import org.springframework.context.ApplicationContext;
import by.epam.library.constants.Constants;
import by.epam.library.utilities.commands.Command;

public class WrongRequest implements Command {

	public String executeCommand(String request, ApplicationContext context) {
		return Constants.WRONG_REQUEST;
	}

}
