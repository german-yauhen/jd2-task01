package by.epam.library.utilities.controller;

import org.springframework.context.ApplicationContext;
import by.epam.library.constants.Constants;
import by.epam.library.utilities.commands.Command;
import by.epam.library.utilities.commands.CommandProvider;

public final class Controller {
	private static final int START_INDEX_PARAM = 0;
	
	public String executeAction(String request, ApplicationContext context) {
		String commandName = request.substring(START_INDEX_PARAM, request.indexOf(Constants.PARAMETER_DELIMETER));
		CommandProvider provider = context.getBean(Constants.COMMAND_PROVIDER, CommandProvider.class);
		Command command = provider.getCommand(commandName);
		String response = command.executeCommand(request, context);
		return response;
	}
}
