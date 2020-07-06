package br.com.controlHealth.bean;

import java.util.Arrays;
import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import br.com.controlHealth.dao.impl.ConsultaDAOImpl;
import br.com.controlHealth.dao.impl.DadosDaConsultaDAOImpl;
import br.com.controlHealth.dao.impl.MedicoDAOImpl;
import br.com.controlHealth.dao.impl.PacienteDAOImpl;
import br.com.controlHealth.datamodel.ConsultaLazyDataModel;
import br.com.controlHealth.model.Consulta;
import br.com.controlHealth.model.Medico;
import br.com.controlHealth.model.Paciente;

public class ConsultaBeanTest {
	
	@Mock
	private ConsultaBean consultaBean;
	
	@Mock
	private ConsultaDAOImpl daoConsulta;
	
	@Mock
	private DadosDaConsultaDAOImpl daoDadosConsulta;

	@Mock
	private MedicoDAOImpl daoMedico;

	@Mock
	private PacienteDAOImpl daoPaciente;

	@Mock
	private MenuBean menuBean;
	
	@Mock
	private ConsultaLazyDataModel model;
	
	@Mock
	private Consulta consulta;
	
	private static final String CONSULTA_LIST = "/consulta/list?faces-redirect=true";
	private static final String CONSULTA_EDIT = "/consulta/edit?faces-redirect=true";
	
	
	@Before
	public void setup() {
		MockitoAnnotations.initMocks(this);
	}
	
	@Test
	public void shouldGetListaMedicos() {
		Mockito.when(consultaBean.getListaMedicos()).thenReturn(Arrays.asList(new Medico()));
		
		List<Medico> listaMedicos = consultaBean.getListaMedicos();
		
		Assert.assertEquals(listaMedicos.size(), 1);
	}
	
	@Test
	public void shouldGetListaPacientes() {
		Mockito.when(consultaBean.getListaPacientes()).thenReturn(Arrays.asList(new Paciente()));
		
		List<Paciente> listaPacientes = consultaBean.getListaPacientes();
		
		Assert.assertEquals(listaPacientes.size(), 1);
	}
	
	@Test
	public void shouldSaveOrUpdate() {
		Mockito.when(consultaBean.saveOrUpdate()).thenReturn(CONSULTA_LIST);
		
		String result = consultaBean.saveOrUpdate();
		
		Assert.assertEquals(result, CONSULTA_LIST);
	}
	
	@Test
	public void shouldDeleteConsulta() {
		Mockito.doNothing().when(consultaBean).deleteConsulta(Mockito.any(Consulta.class));
		
		consultaBean.deleteConsulta(new Consulta());
		
		Mockito.verify(consultaBean, Mockito.times(1)).deleteConsulta(new Consulta());
	}
	
	@Test
	public void shouldListConsultas() {
		Mockito.when(consultaBean.listConsultas()).thenReturn(CONSULTA_LIST);
		
		String result = consultaBean.listConsultas();
		
		Assert.assertEquals(result, CONSULTA_LIST);
	}
	
	@Test
	public void shouldCreateNewConsulta() {
		Mockito.when(consultaBean.newConsulta()).thenReturn(CONSULTA_EDIT);
		
		String result = consultaBean.newConsulta();
		
		Assert.assertEquals(result, CONSULTA_EDIT);
	}
	
	@Test
	public void shouldEditConsulta() {
		Mockito.when(consultaBean.editConsulta(new Consulta())).thenReturn(CONSULTA_EDIT);
		
		String result = consultaBean.editConsulta(new Consulta());
		
		Assert.assertEquals(result, CONSULTA_EDIT);
	}
	
	@Test
	public void shouldViewConsulta() throws Exception {
		Mockito.doNothing().when(consultaBean).viewConsulta(new Consulta());
		
		consultaBean.viewConsulta(new Consulta());
		
		Mockito.verify(consultaBean, Mockito.times(1)).viewConsulta(new Consulta());
	}

}
