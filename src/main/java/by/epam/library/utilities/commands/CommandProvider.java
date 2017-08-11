package by.epam.library.utilities.commands;

import java.util.HashMap;
import java.util.Map;
import org.apache.log4j.Logger;
import by.epam.library.utilities.commands.implementations.AddEditBook;
import by.epam.library.utilities.commands.implementations.AddNewBook;
import by.epam.library.utilities.commands.implementations.DestroySource;
import by.epam.library.utilities.commands.implementations.GetBookList;
import by.epam.library.utilities.commands.implementations.InitializationSource;
import by.epam.library.utilities.commands.implementations.RemoveBook;
import by.epam.library.utilities.commands.implementations.SignIn;
import by.epam.library.utilities.commands.implementations.SignUp;
import by.epam.library.utilities.commands.implementations.WrongRequest;

public final class CommandProvider {
	private static final Map<CommandName, Command> repository = new HashMap<CommandName, Command>();
	private static final Logger logger = Logger.getLogger(CommandProvider.class);
	
	public CommandProvider() {
		repository.put(CommandName.INITIALIZATION_SOURCE, new InitializationSource());
		repository.put(CommandName.DESTROY_SOURCE, new DestroySource());
		repository.put(CommandName.ADD_NEW_BOOK, new AddNewBook());
		repository.put(CommandName.SIGN_IN, new SignIn());
		repository.put(CommandName.SIGN_UP, new SignUp());
		repository.put(CommandName.ADD_EDIT_BOOK, new AddEditBook());
		repository.put(CommandName.GET_BOOKLIST, new GetBookList());
		repository.put(CommandName.REMOVE_BOOK, new RemoveBook());
		repository.put(CommandName.WRONG_REQUEST, new WrongRequest());
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
