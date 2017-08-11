package by.epam.library.service.implementations;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import by.epam.library.constants.Constants;
import by.epam.library.service.UserService;
import by.epam.library.service.exception.ServiceException;
import by.epam.library.service.factory.ServiceFactory;

public class TestUserServiceImpl {
	private static final ApplicationContext context = new ClassPathXmlApplicationContext(Constants.APPLICATION_CONTEXT);
	private static final String LOGIN_PARAM = "Dylan O'Brien";
	private static final String PASSWORD_PARAM = "12345678";
/*	Зарегистрировать пользователя не получиться, т.к. мы не инициализировали ConnectionPool
 	Соответственно когда берем Connection получаем NullPointerException.
*/
	@Test (expected = NullPointerException.class)
	public void signUp() throws ServiceException {
		ServiceFactory factory = context.getBean(Constants.SERVICE_FACTORY, ServiceFactory.class);;
		UserService userService = factory.getUserService();
		userService.signUp(LOGIN_PARAM, PASSWORD_PARAM);
	}
}
