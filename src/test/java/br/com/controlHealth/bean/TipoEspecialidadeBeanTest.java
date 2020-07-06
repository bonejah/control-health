package br.com.controlHealth.bean;

import java.util.Arrays;
import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import br.com.controlHealth.dao.impl.TipoEspecialidadeDAOImpl;
import br.com.controlHealth.datamodel.TipoEspecialidadeLazyDataModel;
import br.com.controlHealth.model.TipoEndereco;
import br.com.controlHealth.model.TipoEspecialidade;

public class TipoEspecialidadeBeanTest {
	
	@Mock
	private TipoEspecialidadeBean tipoEspecialidadeBean;
	
	@Mock
	private TipoEspecialidadeDAOImpl daoTipoEspecialidade;
	
	@Mock
	private MenuBean menuBean;
	
	@Mock
	private TipoEspecialidadeLazyDataModel model;
	
	@Mock
	private TipoEspecialidade tipoEspecialidade;
	
	private static final String TIPO_ESPECIALIDADE_LIST = "/tipoEspecialidade/list?faces-redirect=true";
	private static final String TIPO_ESPECIALIDADE_EDIT = "/tipoEspecialidade/edit?faces-redirect=true";
	
	@Before
	public void setup() {
		MockitoAnnotations.initMocks(this);
	}
	
	@Test
	public void shouldSaveOrUpdate() {
		Mockito.when(tipoEspecialidadeBean.saveOrUpdate()).thenReturn(TIPO_ESPECIALIDADE_LIST);
		
		String result = tipoEspecialidadeBean.saveOrUpdate();
		
		Assert.assertEquals(result, TIPO_ESPECIALIDADE_LIST);
	}
	
	@Test
	public void shouldDeleteTipoEspecialidade() {
		Mockito.doNothing().when(tipoEspecialidadeBean).deleteTipoEspecialidade(Mockito.any(TipoEspecialidade.class));
		
		tipoEspecialidadeBean.deleteTipoEspecialidade(new TipoEspecialidade());
		
		Mockito.verify(tipoEspecialidadeBean, Mockito.times(1)).deleteTipoEspecialidade(new TipoEspecialidade());
	}
	
	@Test
	public void shouldGetListaTipoEspecialidades() {
		Mockito.when(tipoEspecialidadeBean.getTipoEspecialidades()).thenReturn(Arrays.asList(new TipoEspecialidade()));
		
		List<TipoEspecialidade> listTipoEspecialidades = tipoEspecialidadeBean.getTipoEspecialidades();
		
		Assert.assertEquals(listTipoEspecialidades.size(), 1);
	}
	
	@Test
	public void shouldTipoViewTipoEspecialidade() throws Exception {
		Mockito.doNothing().when(tipoEspecialidadeBean).viewTipoEspecialidade(new TipoEspecialidade());
		
		tipoEspecialidadeBean.viewTipoEspecialidade(new TipoEspecialidade());
		
		Mockito.verify(tipoEspecialidadeBean, Mockito.times(1)).viewTipoEspecialidade(new TipoEspecialidade());
	}
	
	@Test
	public void shouldLListTipoEspecialidade() {
		Mockito.when(tipoEspecialidadeBean.listTipoEspecialidade()).thenReturn(TIPO_ESPECIALIDADE_LIST);
		
		String result = tipoEspecialidadeBean.listTipoEspecialidade();
		
		Assert.assertEquals(result, TIPO_ESPECIALIDADE_LIST);
	}
	
	@Test
	public void shouldCreateNewTipoEspecialidade() {
		Mockito.when(tipoEspecialidadeBean.newTipoEspecialidade()).thenReturn(TIPO_ESPECIALIDADE_EDIT);
		
		String result = tipoEspecialidadeBean.newTipoEspecialidade();
		
		Assert.assertEquals(result, TIPO_ESPECIALIDADE_EDIT);
	}
	
	@Test
	public void shouldeditTipoEspecialidade() {
		Mockito.when(tipoEspecialidadeBean.editTipoEspecialidade(new TipoEspecialidade())).thenReturn(TIPO_ESPECIALIDADE_EDIT);
		
		String result = tipoEspecialidadeBean.editTipoEspecialidade(new TipoEspecialidade());
		
		Assert.assertEquals(result, TIPO_ESPECIALIDADE_EDIT);
	}
	
}
