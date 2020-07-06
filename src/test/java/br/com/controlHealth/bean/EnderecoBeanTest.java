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
import br.com.controlHealth.dao.impl.TipoEnderecoDAOImpl;
import br.com.controlHealth.datamodel.EnderecoLazyDataModel;
import br.com.controlHealth.model.Endereco;
import br.com.controlHealth.model.TipoEndereco;

public class EnderecoBeanTest {
	
	@Mock
	private EnderecoBean enderecoBean;
	
	@Mock
	private EnderecoDAOImpl daoEndereco;
	
	@Mock
	private TipoEnderecoDAOImpl daoTipoEndereco;
	
	@Mock
	private MenuBean menuBean;
	
	@Mock
	private EnderecoLazyDataModel model;
	
	@Mock
	private Endereco consulta;
	
	private static final String ENDERECO_LIST = "/endereco/list?faces-redirect=true";
	private static final String ENDERECO_EDIT = "/endereco/edit?faces-redirect=true";
	private static final String TIPO_ENDERECO_EDIT = "/tipoEndereco/edit?faces-redirect=true";
	
	@Before
	public void setup() {
		MockitoAnnotations.initMocks(this);
	}
	
	@Test
	public void shouldGetListaEnderecos() {
		Mockito.when(enderecoBean.getListaEnderecos()).thenReturn(Arrays.asList(new Endereco()));
		
		List<Endereco> listaEnderecos = enderecoBean.getListaEnderecos();
		
		Assert.assertEquals(listaEnderecos.size(), 1);
	}
	
	@Test
	public void shouldGetListaTipoEnderecos() {
		Mockito.when(enderecoBean.getListaTipoEnderecos()).thenReturn(Arrays.asList(new TipoEndereco()));
		
		List<TipoEndereco> listaTipoEnderecos = enderecoBean.getListaTipoEnderecos();
		
		Assert.assertEquals(listaTipoEnderecos.size(), 1);
	}
	
	@Test
	public void shouldSaveOrUpdate() {
		Mockito.when(enderecoBean.saveOrUpdate()).thenReturn(ENDERECO_LIST);
		
		String result = enderecoBean.saveOrUpdate();
		
		Assert.assertEquals(result, ENDERECO_LIST);
	}
	
	@Test
	public void shouldDeleteEndereco() {
		Mockito.doNothing().when(enderecoBean).deleteEndereco(Mockito.any(Endereco.class));
		
		enderecoBean.deleteEndereco(new Endereco());
		
		Mockito.verify(enderecoBean, Mockito.times(1)).deleteEndereco(Mockito.any(Endereco.class));
	}
	
	@Test
	public void shouldViewEndereco() throws Exception {
		Mockito.doNothing().when(enderecoBean).viewEndereco(Mockito.any(Endereco.class));
		
		enderecoBean.viewEndereco(new Endereco());
		
		Mockito.verify(enderecoBean, Mockito.times(1)).viewEndereco(Mockito.any(Endereco.class));
	}
	
	@Test
	public void shouldListEnderecos() {
		Mockito.when(enderecoBean.listEnderecos()).thenReturn(ENDERECO_LIST);
		
		String result = enderecoBean.listEnderecos();
		
		Assert.assertEquals(result, ENDERECO_LIST);
	}
	
	@Test
	public void shouldCreateNewEndereco() {
		Mockito.when(enderecoBean.newEndereco()).thenReturn(ENDERECO_EDIT);
		
		String result = enderecoBean.newEndereco();
		
		Assert.assertEquals(result, ENDERECO_EDIT);
	}
	
	@Test
	public void shouldEditEndereco() {
		Mockito.when(enderecoBean.editEndereco(Mockito.any(Endereco.class))).thenReturn(ENDERECO_EDIT);
		
		String result = enderecoBean.editEndereco(new Endereco());
		
		Assert.assertEquals(result, ENDERECO_EDIT);
	}
	
	@Test
	public void shouldCreateNewTipoEndereco() {
		Mockito.when(enderecoBean.newTipoEndereco()).thenReturn(TIPO_ENDERECO_EDIT);
		
		String result = enderecoBean.newTipoEndereco();
		
		Assert.assertEquals(result, TIPO_ENDERECO_EDIT);
	}
	
}
