package br.com.controlHealth.bean;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import br.com.controlHealth.dao.impl.UserDAOImpl;
import br.com.controlHealth.model.User;

public class LoginBeanTest {

	@Mock
	private LoginBean loginBean;

	@Mock
	private User usuario;

	@Mock
	private UserDAOImpl daoUser;

	private static final String LOGIN = "login";
	private static final String LOGIN_REDIRECT = "/login?faces-redirect=true";
	private static final String CONSULTA_LIST = "/consulta/list?faces-redirect=true";

	@Before
	public void setup() {
		MockitoAnnotations.initMocks(this);
	}

	@Test
	public void shouldGetVersionAppAndNameApp() {
		Mockito.when(loginBean.getNameApp()).thenReturn("controlHealth");
		Mockito.when(loginBean.getVersionApp()).thenReturn("7");

		String nameApp = loginBean.getNameApp();
		String versionApp = loginBean.getVersionApp();

		Assert.assertEquals(nameApp, "controlHealth");
		Assert.assertEquals(versionApp, "7");
	}

	@Test
	public void shouldGetLogout() {
		Mockito.when(loginBean.logout()).thenReturn(LOGIN_REDIRECT);

		String logout = loginBean.logout();

		Assert.assertEquals(logout, LOGIN_REDIRECT);
	}

	@Test
	public void shouldGetLoginSuccess() {
		Mockito.when(loginBean.login()).thenReturn(CONSULTA_LIST);

		String result = loginBean.login();

		Assert.assertEquals(result, CONSULTA_LIST);
	}

	@Test
	public void shouldGetLoginFail() {
		Mockito.when(loginBean.login()).thenReturn(LOGIN);
		
		String result = loginBean.login();
		
		Assert.assertEquals(result, LOGIN);
	}
	
}	
