package by.epam.library.utilities.commands.implementations;

import by.epam.library.constants.Constants;
import by.epam.library.utilities.commands.Command;

public class WrongRequest implements Command {

	public String executeCommand(String request) {
		return Constants.WRONG_REQUEST;
	}

}
