package br.com.controlHealth.datamodel;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.primefaces.model.SortOrder;

import br.com.controlHealth.dao.impl.UserDAOImpl;
import br.com.controlHealth.model.User;


public class UserLazyDataModelTest {
	
	@Mock
	private UserLazyDataModel model;
	
	@Mock
	private UserDAOImpl dao;
	
	@Before
	public void setup() {
		MockitoAnnotations.initMocks(this);
	}
	
	@Test
	public void shouldLoadDataModel() {
		Mockito
			.when(model.load(Mockito.anyInt(), Mockito.anyInt(), Mockito.anyString(), Mockito.any(SortOrder.class), Mockito.any(HashMap.class)))
			.thenReturn(Arrays.asList(new User()));
		
		List<User> load = model.load(0, 0, "", SortOrder.ASCENDING, new HashMap<String, Object>());
		
		Assert.assertEquals(load.size(), 1);
	}
	
	@Test
	public void shouldGetRowKey() {
		Mockito.when(model.getRowKey(Mockito.any(User.class))).thenReturn(new User());
		
		Object object = model.getRowKey(new User());
		
		Assert.assertNotNull(object);
	}
	
	@Test
	public void shouldGetRowData() {
		Mockito.when(model.getRowData()).thenReturn(new User());
		
		User user = model.getRowData();
		
		Assert.assertNotNull(user);
	}

}
