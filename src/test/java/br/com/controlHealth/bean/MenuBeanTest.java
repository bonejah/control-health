package br.com.controlHealth.bean;

import javax.faces.context.FacesContext;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

public class MenuBeanTest {

	@Mock
	private MenuBean menuBean;
	
	@Mock
	private FacesContext context;
	
	@Before
	public void setup() {
		MockitoAnnotations.initMocks(this);
	}
	
	@Test
	public void shouldShowMessageSaveWhenSaveARegister() {
		menuBean.save();
		Mockito.verify(menuBean, Mockito.times(1)).save();
	}

	@Test
	public void shouldShowMessageUpdateWhenSaveARegister() {
		menuBean.update();
		Mockito.verify(menuBean, Mockito.times(1)).update();
	}
	
	@Test
	public void shouldShowMessageDeleteWhenSaveARegister() {
		menuBean.delete();
		Mockito.verify(menuBean, Mockito.times(1)).delete();
	}
	
	@Test
	public void shouldAddMessage() {
		menuBean.addMessage("Registro salvo com sucesso!", "");
	}
	
}
