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

import br.com.controlHealth.model.Endereco;

public class EnderecoDAOImplTest {

	@Mock
	private EnderecoDAOImpl dao;

	@Before
	public void setup() {
		MockitoAnnotations.initMocks(this);
	}

	@Test
	public void shouldSaveEndereco() {
		Endereco enderecoMock = Mockito.any(Endereco.class);

		dao.save(enderecoMock);

		Mockito.verify(dao, Mockito.times(1)).save(enderecoMock);
	}

	@Test
	public void shouldUpdateEndereco() {
		Endereco enderecoMock = Mockito.any(Endereco.class);

		dao.update(enderecoMock);

		Mockito.verify(dao, Mockito.times(1)).update(enderecoMock);
	}

	@Test
	public void shouldDeleteEndereco() {
		Endereco enderecoMock = Mockito.any(Endereco.class);

		dao.delete(enderecoMock);

		Mockito.verify(dao, Mockito.times(1)).delete(enderecoMock);
	}

	@Test
	public void shouldGetAll() {
		Mockito.when(dao.getAll()).thenReturn(Arrays.asList(new Endereco()));

		List<Endereco> listEndereco = dao.getAll();

		Assert.assertEquals(listEndereco.size(), 1);
	}

	@Test
	public void shouldGetById() {
		Mockito.when(dao.getById(Mockito.anyInt())).thenReturn(new Endereco());

		Endereco endereco = dao.getById(1);

		Assert.assertNotNull(endereco);
	}
	
	@SuppressWarnings("unchecked")
	@Test
	public void shouldGetListPaginate() {
		Mockito
			.when(dao.getListPaginate(Mockito.anyInt(), Mockito.anyInt(), Mockito.anyString(), Mockito.anyBoolean(), Mockito.any(HashMap.class)))
			.thenReturn(Arrays.asList(new Endereco()));
		
		HashMap<String, Object> filters = new HashMap<String, Object>();
		
		List<Endereco> listEndereco = dao.getListPaginate(0, 1, "", true, filters);
		
		Assert.assertEquals(listEndereco.size(), 1);
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
	
}
