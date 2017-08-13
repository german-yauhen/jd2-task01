package by.epam.library.view;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import by.epam.library.constants.Constants;
import by.epam.library.utilities.controller.Controller;

public final class View {
	
	private final static ApplicationContext context = new ClassPathXmlApplicationContext(Constants.APPLICATION_CONTEXT);
	private final static Controller CONTROLLER = context.getBean(Constants.CONTROLLER, Controller.class);
	
	public static void main(String [] args){
		String response = null;
		
//		##Initialization DB connection##
		response = CONTROLLER.executeAction(Constants.INIT_SOURCE);
		PrintResponse.out(response);
		
//		##Sign up user
//		response = CONTROLLER.executeAction(Constants.SIGN_UP_VASILIY_PUPKIN);
//		PrintResponse.out(response);
		
// 		##Sign in user
		response = CONTROLLER.executeAction(Constants.SIGN_IN_VASILIY_PUPKIN);
		PrintResponse.out(response);
		

// 		##Add new book
//		Example: add_new_book Title Genre Author Year Quantity
//		response = CONTROLLER.executeAction(Constants.ADD_NEW_BOOK_VASYA_PUPKIN_2017);
//		PrintResponse.out(response);
		
// 		##Add editbook
//		Example: add_edit_book Title Genre Author Year Quantity idBook
		response = CONTROLLER.executeAction(Constants.ADD_EDIT_BOOK_ID15);
		PrintResponse.out(response);
		
//		##Get booklist
		response = CONTROLLER.executeAction(Constants.GET_BOOK_LIST);
		PrintResponse.out(response);

		
// 		##Remove book
//		Example: remove_book idBook
		response = CONTROLLER.executeAction(Constants.REMOVE_BOOK_ID10);
		PrintResponse.out(response);
		
		
//		##Destroy DB connection##
		response = CONTROLLER.executeAction(Constants.DESTROY_SOURCE);
		PrintResponse.out(response);
	}
}
