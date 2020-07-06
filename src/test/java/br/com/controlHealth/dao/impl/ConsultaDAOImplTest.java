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

import br.com.controlHealth.model.Consulta;

public class ConsultaDAOImplTest {

	@Mock
	private ConsultaDAOImpl dao;

	@Before
	public void setup() {
		MockitoAnnotations.initMocks(this);
	}

	@Test
	public void shouldSaveConsulta() {
		Consulta consultaMock = Mockito.any(Consulta.class);

		dao.save(consultaMock);

		Mockito.verify(dao, Mockito.times(1)).save(consultaMock);
	}

	@Test
	public void shouldUpdateConsulta() {
		Consulta consultaMock = Mockito.any(Consulta.class);

		dao.update(consultaMock);

		Mockito.verify(dao, Mockito.times(1)).update(consultaMock);
	}

	@Test
	public void shouldDeleteConsulta() {
		Consulta consultaMock = Mockito.any(Consulta.class);

		dao.delete(consultaMock);

		Mockito.verify(dao, Mockito.times(1)).delete(consultaMock);
	}

	@Test
	public void shouldGetAll() {
		Mockito.when(dao.getAll()).thenReturn(Arrays.asList(new Consulta()));

		List<Consulta> listConsulta = dao.getAll();

		Assert.assertEquals(listConsulta.size(), 1);
	}

	@Test
	public void shouldGetById() {
		Mockito.when(dao.getById(Mockito.anyInt())).thenReturn(new Consulta());

		Consulta consulta = dao.getById(1);

		Assert.assertNotNull(consulta);
	}

	@Test
	public void shoudlGetDadosConsulta() {
		Mockito.when(dao.getDadosDaConsulta(Mockito.anyInt())).thenReturn(new Consulta());

		Consulta consulta = dao.getDadosDaConsulta(1);

		Assert.assertNotNull(consulta);
	}
	
	@SuppressWarnings("unchecked")
	@Test
	public void shouldGetListPaginate() {
		Mockito
			.when(dao.getListPaginate(Mockito.anyInt(), Mockito.anyInt(), Mockito.anyString(), Mockito.anyBoolean(), Mockito.any(HashMap.class)))
			.thenReturn(Arrays.asList(new Consulta()));
		
		HashMap<String, Object> filters = new HashMap<String, Object>();
		
		List<Consulta> listConsulta = dao.getListPaginate(0, 1, "", true, filters);
		
		Assert.assertEquals(listConsulta.size(), 1);
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
