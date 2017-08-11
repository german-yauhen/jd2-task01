package by.epam.library.utilities.commands;

import org.springframework.context.ApplicationContext;

public interface Command {
	public String executeCommand(String request, ApplicationContext context);
}
