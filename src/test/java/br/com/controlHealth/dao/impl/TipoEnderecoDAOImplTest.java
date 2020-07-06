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

import br.com.controlHealth.model.TipoEndereco;

public class TipoEnderecoDAOImplTest {

	@Mock
	private TipoEnderecoDAOImpl dao;

	@Before
	public void setup() {
		MockitoAnnotations.initMocks(this);
	}

	@Test
	public void shouldSaveTipoEndereco() {
		TipoEndereco tipoEnderecoMock = Mockito.any(TipoEndereco.class);

		dao.save(tipoEnderecoMock);

		Mockito.verify(dao, Mockito.times(1)).save(tipoEnderecoMock);
	}

	@Test
	public void shouldUpdateTipoEndereco() {
		TipoEndereco tipoEnderecoMock = Mockito.any(TipoEndereco.class);

		dao.update(tipoEnderecoMock);

		Mockito.verify(dao, Mockito.times(1)).update(tipoEnderecoMock);
	}

	@Test
	public void shouldDeleteTipoEndereco() {
		TipoEndereco tipoEnderecoMock = Mockito.any(TipoEndereco.class);

		dao.delete(tipoEnderecoMock);

		Mockito.verify(dao, Mockito.times(1)).delete(tipoEnderecoMock);
	}

	@Test
	public void shouldGetAll() {
		Mockito.when(dao.getAll()).thenReturn(Arrays.asList(new TipoEndereco()));

		List<TipoEndereco> listTipoEndereco = dao.getAll();

		Assert.assertEquals(listTipoEndereco.size(), 1);
	}

	@Test
	public void shouldGetById() {
		Mockito.when(dao.getById(Mockito.anyInt())).thenReturn(new TipoEndereco());

		TipoEndereco tipoEndereco = dao.getById(1);

		Assert.assertNotNull(tipoEndereco);
	}
	
	@SuppressWarnings("unchecked")
	@Test
	public void shouldGetListPaginate() {
		Mockito
			.when(dao.getListPaginate(Mockito.anyInt(), Mockito.anyInt(), Mockito.anyString(), Mockito.anyBoolean(), Mockito.any(HashMap.class)))
			.thenReturn(Arrays.asList(new TipoEndereco()));
		
		HashMap<String, Object> filters = new HashMap<String, Object>();
		
		List<TipoEndereco> listTipoEndereco = dao.getListPaginate(0, 1, "", true, filters);
		
		Assert.assertEquals(listTipoEndereco.size(), 1);
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
