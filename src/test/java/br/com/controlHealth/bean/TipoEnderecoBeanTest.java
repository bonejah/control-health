package br.com.controlHealth.bean;

import java.util.Arrays;
import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import br.com.controlHealth.dao.impl.TipoEnderecoDAOImpl;
import br.com.controlHealth.datamodel.TipoEnderecoLazyDataModel;
import br.com.controlHealth.model.Endereco;
import br.com.controlHealth.model.TipoEndereco;

public class TipoEnderecoBeanTest {
	
	@Mock
	private TipoEnderecoBean tipoEnderecoBean;
	
	@Mock
	private TipoEnderecoDAOImpl daoTipoEndereco;
	
	@Mock
	private MenuBean menuBean;
	
	@Mock
	private TipoEnderecoLazyDataModel model;
	
	@Mock
	private TipoEndereco consulta;
	
	private static final String TIPO_ENDERECO_EDIT = "/tipoEndereco/edit?faces-redirect=true";
	private static final String TIPO_ENDERECO_LIST = "/tipoEndereco/list?faces-redirect=true";
	
	@Before
	public void setup() {
		MockitoAnnotations.initMocks(this);
	}
	
	@Test
	public void shouldSaveOrUpdate() {
		Mockito.when(tipoEnderecoBean.saveOrUpdate()).thenReturn(TIPO_ENDERECO_LIST);
		
		String result = tipoEnderecoBean.saveOrUpdate();
		
		Assert.assertEquals(result, TIPO_ENDERECO_LIST);
	}
	
	@Test
	public void shouldDeleteTipoEndereco() {
		Mockito.doNothing().when(tipoEnderecoBean).deleteTipoEndereco(Mockito.any(TipoEndereco.class));
		
		tipoEnderecoBean.deleteTipoEndereco(new TipoEndereco());
		
		Mockito.verify(tipoEnderecoBean, Mockito.times(1)).deleteTipoEndereco(new TipoEndereco());
	}
	
	@Test
	public void shouldGetListaTipoEnderecos() {
		Mockito.when(tipoEnderecoBean.getListaTipoEnderecos()).thenReturn(Arrays.asList(new TipoEndereco()));
		
		List<TipoEndereco> listaTipoEnderecos = tipoEnderecoBean.getListaTipoEnderecos();
		
		Assert.assertEquals(listaTipoEnderecos.size(), 1);
	}
	
	@Test
	public void shouldTipoViewEndereco() throws Exception {
		Mockito.doNothing().when(tipoEnderecoBean).viewTipoEndereco(new TipoEndereco());
		
		tipoEnderecoBean.viewTipoEndereco(new TipoEndereco());
		
		Mockito.verify(tipoEnderecoBean, Mockito.times(1)).viewTipoEndereco(new TipoEndereco());
	}
	
	@Test
	public void shouldListTIpoEnderecos() {
		Mockito.when(tipoEnderecoBean.listTipoEndereco()).thenReturn(TIPO_ENDERECO_LIST);
		
		String result = tipoEnderecoBean.listTipoEndereco();
		
		Assert.assertEquals(result, TIPO_ENDERECO_LIST);
	}
	
	@Test
	public void shouldCreateNewTIpoEndereco() {
		Mockito.when(tipoEnderecoBean.newTipoEndereco()).thenReturn(TIPO_ENDERECO_EDIT);
		
		String result = tipoEnderecoBean.newTipoEndereco();
		
		Assert.assertEquals(result, TIPO_ENDERECO_EDIT);
	}
	
	@Test
	public void shouldEditTipoEndereco() {
		Mockito.when(tipoEnderecoBean.editTipoEndereco(new TipoEndereco())).thenReturn(TIPO_ENDERECO_EDIT);
		
		String result = tipoEnderecoBean.editTipoEndereco(new TipoEndereco());
		
		Assert.assertEquals(result, TIPO_ENDERECO_EDIT);
	}
	
}
