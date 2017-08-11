package by.epam.library.service.validation;

import static org.junit.Assert.assertFalse;
import org.junit.Test;

public class TestValidationData {
	private final String TITLE_PARAM = "BookTitle";
	private final String AUTHOR_PARAM = "MyAuthor";
	private final String GENRE_PARAM = "MyGenre";
	private final String YEAR_PARAM = "2017";
	private final String QUANTITY_PARAM = "10";

	@Test
	public void testValidBook(){
		boolean result = ValidationData.validBook(TITLE_PARAM, GENRE_PARAM, AUTHOR_PARAM, YEAR_PARAM, QUANTITY_PARAM);
		assertFalse(result);
	}
	
	@Test
	public void testValidUser(){
		boolean result = ValidationData.validUser(null, null);
		assertFalse(result);
	}

}
