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

import br.com.controlHealth.dao.impl.MedicoDAOImpl;
import br.com.controlHealth.model.Medico;


public class MedicoLazyDataModelTest {
	
	@Mock
	private MedicoLazyDataModel model;
	
	@Mock
	private MedicoDAOImpl dao;
	
	@Before
	public void setup() {
		MockitoAnnotations.initMocks(this);
	}
	
	@Test
	public void shouldLoadDataModel() {
		Mockito
			.when(model.load(Mockito.anyInt(), Mockito.anyInt(), Mockito.anyString(), Mockito.any(SortOrder.class), Mockito.any(HashMap.class)))
			.thenReturn(Arrays.asList(new Medico()));
		
		List<Medico> load = model.load(0, 0, "", SortOrder.ASCENDING, new HashMap<String, Object>());
		
		Assert.assertEquals(load.size(), 1);
	}
	
	@Test
	public void shouldGetRowKey() {
		Mockito.when(model.getRowKey(Mockito.any(Medico.class))).thenReturn(new Medico());
		
		Object object = model.getRowKey(new Medico());
		
		Assert.assertNotNull(object);
	}
	
	@Test
	public void shouldGetRowData() {
		Mockito.when(model.getRowData()).thenReturn(new Medico());
		
		Medico medico = model.getRowData();
		
		Assert.assertNotNull(medico);
	}

}
