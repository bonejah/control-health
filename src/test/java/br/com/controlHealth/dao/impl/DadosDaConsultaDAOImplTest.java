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

import br.com.controlHealth.model.DadosDaConsulta;

public class DadosDaConsultaDAOImplTest {

	@Mock
	private DadosDaConsultaDAOImpl dao;

	@Before
	public void setup() {
		MockitoAnnotations.initMocks(this);
	}

	@Test
	public void shouldSaveDadosDaDadosDaConsulta() {
		DadosDaConsulta dadosDaConsultaMock = Mockito.any(DadosDaConsulta.class);

		dao.save(dadosDaConsultaMock);

		Mockito.verify(dao, Mockito.times(1)).save(dadosDaConsultaMock);
	}

	@Test
	public void shouldUpdateDadosDaConsulta() {
		DadosDaConsulta dadosDaConsultaMock = Mockito.any(DadosDaConsulta.class);

		dao.update(dadosDaConsultaMock);

		Mockito.verify(dao, Mockito.times(1)).update(dadosDaConsultaMock);
	}

	@Test
	public void shouldDeleteDadosDaConsulta() {
		DadosDaConsulta dadosDaConsultaMock = Mockito.any(DadosDaConsulta.class);

		dao.delete(dadosDaConsultaMock);

		Mockito.verify(dao, Mockito.times(1)).delete(dadosDaConsultaMock);
	}

	@Test
	public void shouldGetAll() {
		Mockito.when(dao.getAll()).thenReturn(Arrays.asList(new DadosDaConsulta()));

		List<DadosDaConsulta> listDadosDaConsulta = dao.getAll();

		Assert.assertEquals(listDadosDaConsulta.size(), 1);
	}

	@Test
	public void shouldGetById() {
		Mockito.when(dao.getById(Mockito.anyInt())).thenReturn(new DadosDaConsulta());

		DadosDaConsulta dadosDaConsulta = dao.getById(1);

		Assert.assertNotNull(dadosDaConsulta);
	}
	
	@SuppressWarnings("unchecked")
	@Test
	public void shouldGetListPaginate() {
		Mockito
			.when(dao.getListPaginate(Mockito.anyInt(), Mockito.anyInt(), Mockito.anyString(), Mockito.anyBoolean(), Mockito.any(HashMap.class)))
			.thenReturn(Arrays.asList(new DadosDaConsulta()));
		
		HashMap<String, Object> filters = new HashMap<String, Object>();
		
		List<DadosDaConsulta> listDadosDaConsulta = dao.getListPaginate(0, 1, "", true, filters);
		
		Assert.assertEquals(listDadosDaConsulta.size(), 1);
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
