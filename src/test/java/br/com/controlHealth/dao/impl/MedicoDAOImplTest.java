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

import br.com.controlHealth.model.Medico;

public class MedicoDAOImplTest {

	@Mock
	private MedicoDAOImpl dao;

	@Before
	public void setup() {
		MockitoAnnotations.initMocks(this);
	}

	@Test
	public void shouldSaveMedico() {
		Medico medicoMock = Mockito.any(Medico.class);

		dao.save(medicoMock);

		Mockito.verify(dao, Mockito.times(1)).save(medicoMock);
	}

	@Test
	public void shouldUpdateMedico() {
		Medico medicoMock = Mockito.any(Medico.class);

		dao.update(medicoMock);

		Mockito.verify(dao, Mockito.times(1)).update(medicoMock);
	}

	@Test
	public void shouldDeleteMedico() {
		Medico medicoMock = Mockito.any(Medico.class);

		dao.delete(medicoMock);

		Mockito.verify(dao, Mockito.times(1)).delete(medicoMock);
	}

	@Test
	public void shouldGetAll() {
		Mockito.when(dao.getAll()).thenReturn(Arrays.asList(new Medico()));

		List<Medico> listMedico = dao.getAll();

		Assert.assertEquals(listMedico.size(), 1);
	}

	@Test
	public void shouldGetById() {
		Mockito.when(dao.getById(Mockito.anyInt())).thenReturn(new Medico());

		Medico medico = dao.getById(1);

		Assert.assertNotNull(medico);
	}
	
	@SuppressWarnings("unchecked")
	@Test
	public void shouldGetListPaginate() {
		Mockito
			.when(dao.getListPaginate(Mockito.anyInt(), Mockito.anyInt(), Mockito.anyString(), Mockito.anyBoolean(), Mockito.any(HashMap.class)))
			.thenReturn(Arrays.asList(new Medico()));
		
		HashMap<String, Object> filters = new HashMap<String, Object>();
		
		List<Medico> listMedico = dao.getListPaginate(0, 1, "", true, filters);
		
		Assert.assertEquals(listMedico.size(), 1);
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
