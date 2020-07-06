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

import br.com.controlHealth.model.Paciente;

public class PacienteDAOImplTest {

	@Mock
	private PacienteDAOImpl dao;

	@Before
	public void setup() {
		MockitoAnnotations.initMocks(this);
	}

	@Test
	public void shouldSavePaciente() {
		Paciente pacienteMock = Mockito.any(Paciente.class);

		dao.save(pacienteMock);

		Mockito.verify(dao, Mockito.times(1)).save(pacienteMock);
	}

	@Test
	public void shouldUpdatePaciente() {
		Paciente pacienteMock = Mockito.any(Paciente.class);

		dao.update(pacienteMock);

		Mockito.verify(dao, Mockito.times(1)).update(pacienteMock);
	}

	@Test
	public void shouldDeletePaciente() {
		Paciente pacienteMock = Mockito.any(Paciente.class);

		dao.delete(pacienteMock);

		Mockito.verify(dao, Mockito.times(1)).delete(pacienteMock);
	}

	@Test
	public void shouldGetAll() {
		Mockito.when(dao.getAll()).thenReturn(Arrays.asList(new Paciente()));

		List<Paciente> listPaciente = dao.getAll();

		Assert.assertEquals(listPaciente.size(), 1);
	}

	@Test
	public void shouldGetById() {
		Mockito.when(dao.getById(Mockito.anyInt())).thenReturn(new Paciente());

		Paciente paciente = dao.getById(1);

		Assert.assertNotNull(paciente);
	}
	
	@SuppressWarnings("unchecked")
	@Test
	public void shouldGetListPaginate() {
		Mockito
			.when(dao.getListPaginate(Mockito.anyInt(), Mockito.anyInt(), Mockito.anyString(), Mockito.anyBoolean(), Mockito.any(HashMap.class)))
			.thenReturn(Arrays.asList(new Paciente()));
		
		HashMap<String, Object> filters = new HashMap<String, Object>();
		
		List<Paciente> listPaciente = dao.getListPaginate(0, 1, "", true, filters);
		
		Assert.assertEquals(listPaciente.size(), 1);
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
