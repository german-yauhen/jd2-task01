package by.epam.library.utilities.commands;

import java.util.HashMap;
import org.apache.log4j.Logger;

public final class CommandProvider {
	
	private HashMap<CommandName, Command> repository;
	private static final Logger logger = Logger.getLogger(CommandProvider.class);
	
	public CommandProvider() {
	}
		
	public CommandProvider(HashMap<CommandName, Command> repository) {
		this.repository = repository;
	}

	public Command getCommand(String key){
		Command command;
		CommandName commandName;
		try {
			commandName = CommandName.valueOf(key.toUpperCase());
			command = repository.get(commandName);			
		} catch (IllegalArgumentException | NullPointerException e) {
			logger.error(e);
			command = repository.get(CommandName.WRONG_REQUEST);
		}	
		return command;
	}
	
}
