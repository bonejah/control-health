package br.com.controlHealth.bean;

import java.util.Arrays;
import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import br.com.controlHealth.dao.impl.EnderecoDAOImpl;
import br.com.controlHealth.dao.impl.PacienteDAOImpl;
import br.com.controlHealth.dao.impl.TipoEnderecoDAOImpl;
import br.com.controlHealth.dao.impl.TipoEspecialidadeDAOImpl;
import br.com.controlHealth.datamodel.PacienteLazyDataModel;
import br.com.controlHealth.model.Paciente;
import br.com.controlHealth.model.TipoEndereco;

public class PacienteBeanTest {
	
	private static final String PACIENTE_LIST = "/paciente/list?faces-redirect=true";
	private static final String PACIENTE_EDIT = "/paciente/edit?faces-redirect=true";
	private static final String ENDERECO_EDIT = "/endereco/edit?faces-redirect=true";
	private static final String TIPO_ESPECIALIDADE_EDIT = "/tipoEspecialidade/edit?faces-redirect=true";

	@Mock
	private PacienteBean pacienteBean;
	
	@Mock
	private Paciente paciente;

	@Mock
	private MenuBean menuBean;

	@Mock
	private PacienteDAOImpl daoPaciente;

	@Mock
	private EnderecoDAOImpl daoEndereco;
	
	@Mock
	private TipoEnderecoDAOImpl daoTipoEndereco;
	
	@Mock
	private TipoEspecialidadeDAOImpl daoTipoEspecialidade;

	@Mock
	private PacienteLazyDataModel model;
	
	@Before
	public void setup() {
		MockitoAnnotations.initMocks(this);
	}
	
	@Test
	public void shouldSaveOrUpdate() {
		Mockito.when(pacienteBean.saveOrUpdate()).thenReturn(PACIENTE_LIST);
		
		String result = pacienteBean.saveOrUpdate();
		
		Assert.assertEquals(result, PACIENTE_LIST);
	}
	
	@Test
	public void shouldDeletePaciente() {
		Mockito.doNothing().when(pacienteBean).deletePaciente(Mockito.any(Paciente.class));
		
		pacienteBean.deletePaciente(new Paciente());
		
		Mockito.verify(pacienteBean, Mockito.times(1)).deletePaciente(new Paciente());
	}
	
	@Test
	public void shouldGetListaPacientes() {
		Mockito.when(pacienteBean.getListaPacientes()).thenReturn(Arrays.asList(new Paciente()));
		
		List<Paciente> listaPacientes = pacienteBean.getListaPacientes();
		
		Assert.assertEquals(listaPacientes.size(), 1);
	}
	
	
	@Test
	public void shouldListaPacientes() {
		Mockito.when(pacienteBean.getListaPacientes()).thenReturn(Arrays.asList(new Paciente()));
		
		List<Paciente> listaPacientes = pacienteBean.getListaPacientes();
		
		Assert.assertEquals(listaPacientes.size(), 1);
	}
	
	@Test
	public void shouldListaTipoEnderecos() {
		Mockito.when(pacienteBean.getListaTipoEnderecos()).thenReturn(Arrays.asList(new TipoEndereco()));
		
		List<TipoEndereco> listaTipoEnderecos = pacienteBean.getListaTipoEnderecos();
		
		Assert.assertEquals(listaTipoEnderecos.size(), 1);
	}
	
	@Test
	public void shouldViewPaciente() throws Exception {
		Mockito.doNothing().when(pacienteBean).viewPaciente(new Paciente());
		
		pacienteBean.viewPaciente(new Paciente());
		
		Mockito.verify(pacienteBean, Mockito.times(1)).viewPaciente(new Paciente());
	}

	@Test
	public void shouldEditPaciente() {
		Mockito.when(pacienteBean.editPaciente(new Paciente())).thenReturn(PACIENTE_EDIT);
		
		String result = pacienteBean.editPaciente(new Paciente());
		
		Assert.assertEquals(result, PACIENTE_EDIT);
	}
	
	@Test
	public void shouldListPacientes() {
		Mockito.when(pacienteBean.listPacientes()).thenReturn(PACIENTE_LIST);
		
		String result = pacienteBean.listPacientes();
		
		Assert.assertEquals(result, PACIENTE_LIST);
	}
	
	@Test
	public void shouldGetNewPaciente() {
		Mockito.when(pacienteBean.newPaciente()).thenReturn(PACIENTE_EDIT);
		
		String result = pacienteBean.newPaciente();
		
		Assert.assertEquals(result, PACIENTE_EDIT);
	}
	
	@Test
	public void shouldGetNewTipoEspecialidade() {
		Mockito.when(pacienteBean.newTipoEspecialidade()).thenReturn(TIPO_ESPECIALIDADE_EDIT);
		
		String result = pacienteBean.newTipoEspecialidade();
		
		Assert.assertEquals(result, TIPO_ESPECIALIDADE_EDIT);
	}
	
	@Test
	public void shouldGetNewEndereco() {
		Mockito.when(pacienteBean.newEndereco()).thenReturn(ENDERECO_EDIT);
		
		String result = pacienteBean.newEndereco();
		
		Assert.assertEquals(result, ENDERECO_EDIT);
	}

}
