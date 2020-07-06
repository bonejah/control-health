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
import br.com.controlHealth.dao.impl.MedicoDAOImpl;
import br.com.controlHealth.dao.impl.TipoEnderecoDAOImpl;
import br.com.controlHealth.dao.impl.TipoEspecialidadeDAOImpl;
import br.com.controlHealth.datamodel.MedicoLazyDataModel;
import br.com.controlHealth.model.Medico;
import br.com.controlHealth.model.TipoEndereco;
import br.com.controlHealth.model.TipoEspecialidade;

public class MedicoBeanTest {

	private static final String MEDICO_LIST = "/medico/list?faces-redirect=true";
	private static final String MEDICO_EDIT = "/medico/edit?faces-redirect=true";
	private static final String TIPO_ESPECIALIDADE_EDIT = "/tipoEspecialidade/edit?faces-redirect=true";
	private static final String ENDERECO_EDIT = "/endereco/edit?faces-redirect=true";
	
	@Mock
	private MedicoBean medicoBean;
	
	@Mock
	private MedicoDAOImpl daoMedico;

	@Mock
	private EnderecoDAOImpl daoEndereco;

	@Mock
	private TipoEnderecoDAOImpl daoTipoEndereco;

	@Mock
	private TipoEspecialidadeDAOImpl daoTipoEspecialidade;

	@Mock
	private MenuBean menuBean;

	@Mock
	private MedicoLazyDataModel model;

	@Mock
	private Medico medico;
	
	
	@Before
	public void setup() {
		MockitoAnnotations.initMocks(this);
	}
	
	@Test
	public void shouldSaveOrUpdate() {
		Mockito.when(medicoBean.saveOrUpdate()).thenReturn(MEDICO_LIST);
		
		String result = medicoBean.saveOrUpdate();
		
		Assert.assertEquals(result, MEDICO_LIST);
	}
	
	@Test
	public void shouldDeleteMedico() {
		Mockito.doNothing().when(medicoBean).deleteMedico(Mockito.any(Medico.class));
		
		medicoBean.deleteMedico(new Medico());
		
		Mockito.verify(medicoBean, Mockito.times(1)).deleteMedico(new Medico());
	}
	
	@Test
	public void shouldGetListaMedicos() {
		Mockito.when(medicoBean.getListaMedicos()).thenReturn(Arrays.asList(new Medico()));
		
		List<Medico> listaMedicos = medicoBean.getListaMedicos();
		
		Assert.assertEquals(listaMedicos.size(), 1);
	}
	
	
	@Test
	public void shouldListaMedicos() {
		Mockito.when(medicoBean.getListaMedicos()).thenReturn(Arrays.asList(new Medico()));
		
		List<Medico> listaMedicos = medicoBean.getListaMedicos();
		
		Assert.assertEquals(listaMedicos.size(), 1);
	}
	
	@Test
	public void shouldListaTipoEnderecos() {
		Mockito.when(medicoBean.getListaTipoEnderecos()).thenReturn(Arrays.asList(new TipoEndereco()));
		
		List<TipoEndereco> listaTipoEnderecos = medicoBean.getListaTipoEnderecos();
		
		Assert.assertEquals(listaTipoEnderecos.size(), 1);
	}

	@Test
	public void shouldListaTipoEspecialidade() {
		Mockito.when(medicoBean.getListaTipoEspecialidade()).thenReturn(Arrays.asList(new TipoEspecialidade()));
		
		List<TipoEspecialidade> listaTipoEspecialidade = medicoBean.getListaTipoEspecialidade();
		
		Assert.assertEquals(listaTipoEspecialidade.size(), 1);
	}
	
	@Test
	public void shouldViewMedico() throws Exception {
		Mockito.doNothing().when(medicoBean).viewMedico(new Medico());
		
		medicoBean.viewMedico(new Medico());
		
		Mockito.verify(medicoBean, Mockito.times(1)).viewMedico(new Medico());
	}

	@Test
	public void shouldEditMedico() {
		Mockito.when(medicoBean.editMedico(new Medico())).thenReturn(MEDICO_EDIT);
		
		String result = medicoBean.editMedico(new Medico());
		
		Assert.assertEquals(result, MEDICO_EDIT);
	}
	
	@Test
	public void shouldListMedicos() {
		Mockito.when(medicoBean.listMedicos()).thenReturn(MEDICO_LIST);
		
		String result = medicoBean.listMedicos();
		
		Assert.assertEquals(result, MEDICO_LIST);
	}
	
	@Test
	public void shouldGetNewMedico() {
		Mockito.when(medicoBean.newMedico()).thenReturn(MEDICO_EDIT);
		
		String result = medicoBean.newMedico();
		
		Assert.assertEquals(result, MEDICO_EDIT);
	}
	
	@Test
	public void shouldGetNewTipoEspecialidade() {
		Mockito.when(medicoBean.newTipoEspecialidade()).thenReturn(TIPO_ESPECIALIDADE_EDIT);
		
		String result = medicoBean.newTipoEspecialidade();
		
		Assert.assertEquals(result, TIPO_ESPECIALIDADE_EDIT);
	}
	
	@Test
	public void shouldGetNewEndereco() {
		Mockito.when(medicoBean.newEndereco()).thenReturn(ENDERECO_EDIT);
		
		String result = medicoBean.newEndereco();
		
		Assert.assertEquals(result, ENDERECO_EDIT);
	}

}
