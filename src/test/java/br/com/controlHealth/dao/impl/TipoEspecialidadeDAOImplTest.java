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

import br.com.controlHealth.model.TipoEspecialidade;

public class TipoEspecialidadeDAOImplTest {

	@Mock
	private TipoEspecialidadeDAOImpl dao;

	@Before
	public void setup() {
		MockitoAnnotations.initMocks(this);
	}

	@Test
	public void shouldSaveTipoEspecialidade() {
		TipoEspecialidade tipoEspecialidadeMock = Mockito.any(TipoEspecialidade.class);

		dao.save(tipoEspecialidadeMock);

		Mockito.verify(dao, Mockito.times(1)).save(tipoEspecialidadeMock);
	}

	@Test
	public void shouldUpdateTipoEspecialidade() {
		TipoEspecialidade tipoEspecialidadeMock = Mockito.any(TipoEspecialidade.class);

		dao.update(tipoEspecialidadeMock);

		Mockito.verify(dao, Mockito.times(1)).update(tipoEspecialidadeMock);
	}

	@Test
	public void shouldDeleteTipoEspecialidade() {
		TipoEspecialidade tipoEspecialidadeMock = Mockito.any(TipoEspecialidade.class);

		dao.delete(tipoEspecialidadeMock);

		Mockito.verify(dao, Mockito.times(1)).delete(tipoEspecialidadeMock);
	}

	@Test
	public void shouldGetAll() {
		Mockito.when(dao.getAll()).thenReturn(Arrays.asList(new TipoEspecialidade()));

		List<TipoEspecialidade> listTipoEspecialidade = dao.getAll();

		Assert.assertEquals(listTipoEspecialidade.size(), 1);
	}

	@Test
	public void shouldGetById() {
		Mockito.when(dao.getById(Mockito.anyInt())).thenReturn(new TipoEspecialidade());

		TipoEspecialidade tipoEspecialidade = dao.getById(1);

		Assert.assertNotNull(tipoEspecialidade);
	}
	
	@SuppressWarnings("unchecked")
	@Test
	public void shouldGetListPaginate() {
		Mockito
			.when(dao.getListPaginate(Mockito.anyInt(), Mockito.anyInt(), Mockito.anyString(), Mockito.anyBoolean(), Mockito.any(HashMap.class)))
			.thenReturn(Arrays.asList(new TipoEspecialidade()));
		
		HashMap<String, Object> filters = new HashMap<String, Object>();
		
		List<TipoEspecialidade> listTipoEspecialidade = dao.getListPaginate(0, 1, "", true, filters);
		
		Assert.assertEquals(listTipoEspecialidade.size(), 1);
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
