package br.com.controlHealth.bean;

import javax.faces.context.FacesContext;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

public class LanguageBeanTest {
	
	@Mock
	private LanguageBean languageBean;
	
	@Before
	public void setup() {
		MockitoAnnotations.initMocks(this);
	}
	
	@Test
	public void shouldChangeLanguage() {
		FacesContext contextMock = Mockito.mock(FacesContext.class);
		
		Mockito.doNothing().when(languageBean).changeLanguage("br") ;
		
		languageBean.changeLanguage("br");
		
		Mockito.verify(languageBean, Mockito.times(1)).changeLanguage("br");
	}

}
