package br.com.controlHealth.dao.impl;

import static org.junit.Assert.assertEquals;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import br.com.controlHealth.model.User;

public class UserDAOImplTest {

	@Mock
	private UserDAOImpl dao;

	@Before
	public void setup() {
		MockitoAnnotations.initMocks(this);
	}

	@Test
	public void shouldSaveUser() {
		User userMock = Mockito.any(User.class);

		dao.save(userMock);

		Mockito.verify(dao, Mockito.times(1)).save(userMock);
	}

	@Test
	public void shouldUpdateUser() {
		User userMock = Mockito.any(User.class);

		dao.update(userMock);

		Mockito.verify(dao, Mockito.times(1)).update(userMock);
	}

	@Test
	public void shouldDeleteUser() {
		User userMock = Mockito.any(User.class);

		dao.delete(userMock);

		Mockito.verify(dao, Mockito.times(1)).delete(userMock);
	}

	@Test
	public void shouldGetAll() {
		Mockito.when(dao.getAll()).thenReturn(Arrays.asList(new User()));

		List<User> listUser = dao.getAll();

		Assert.assertEquals(listUser.size(), 1);
	}

	@Test
	public void shouldGetById() {
		Mockito.when(dao.getById(Mockito.anyInt())).thenReturn(new User());

		User user = dao.getById(1);

		Assert.assertNotNull(user);
	}
	
	@SuppressWarnings("unchecked")
	@Test
	public void shouldGetListPaginate() {
		Mockito
			.when(dao.getListPaginate(Mockito.anyInt(), Mockito.anyInt(), Mockito.anyString(), Mockito.anyBoolean(), Mockito.any(HashMap.class)))
			.thenReturn(Arrays.asList(new User()));
		
		HashMap<String, Object> filters = new HashMap<String, Object>();
		
		List<User> listUser = dao.getListPaginate(0, 1, "", true, filters);
		
		Assert.assertEquals(listUser.size(), 1);
	}

	@Test
	public void shouldCountAll() {
		Mockito.when(dao.countAll()).thenReturn(7);
		
		int total = dao.countAll();
		
		assertEquals(total, 7);
	}
	
	@SuppressWarnings("unchecked")
	@Test
	public void shouldCountAllFiltered() {
		Mockito
			.when(dao.countAllFiltered(Mockito.any(HashMap.class)))
			.thenReturn(7);
		
		HashMap<String, Object> filters = new HashMap<String, Object>();
		
		int total = dao.countAllFiltered(filters);
		
		Assert.assertEquals(total, 7);
	}
	
	@Test
	public void shouldCheckIfUserExists() {
		Mockito.when(dao.checkIfExistsUser(Mockito.any(User.class))).thenReturn(new User());
		
		User user = dao.checkIfExistsUser(new User());
		
		Assert.assertNotNull(user);
	}
	
}
