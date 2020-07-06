
package br.com.controlHealth.bean;

import java.util.Arrays;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.primefaces.model.SortOrder;

import br.com.controlHealth.dao.impl.UserDAOImpl;
import br.com.controlHealth.datamodel.UserLazyDataModel;
import br.com.controlHealth.model.User;

public class AdminBeanTest {
	
	@InjectMocks
	private AdminBean adminBean;
	
	@Mock
	private User user;
	
	@Mock
	private UserDAOImpl daoUser;

	@Mock
	private MenuBean menuBean;
	
	@Mock
	private UserLazyDataModel model;
	
	private Calendar dataCadastroAtualizacao;
	
	private static final String ADMIN_LIST = "/admin/list?faces-redirect=true";
	private static final String ADMIN_EDIT = "/admin/edit?faces-redirect=true";
	
	@Before
	public void setup() {
		MockitoAnnotations.initMocks(this);
		this.adminBean = new AdminBean(daoUser, menuBean, model, user);
	}
	
	@Test
	public void shouldListUsers() {
		Map<String, Object> filters = new HashMap<String, Object>();
		
		adminBean.listUsers();
		
		Mockito
			.when(model.load(0, 10, "", SortOrder.ASCENDING, filters))
			.thenReturn(Arrays.asList(new User(), new User()));
		
		model.init();
	
		List<User> lsUsers = model.load(0, 10, "", SortOrder.ASCENDING, filters);
		Assert.assertEquals(2, lsUsers.size());
	}
	
	@Test
	public void shouldViewUser() throws Exception {
		adminBean.setUser(createUser());
		adminBean.viewUser(adminBean.getUser());
		
//		Assert.assertEquals(adminBean.getUser().getId(), new Integer(1));
		Assert.assertEquals(adminBean.getUser().getEmail(), "teste@gmail.com");
		Assert.assertEquals(adminBean.getUser().getPassword(), "12345");
		Assert.assertEquals(adminBean.getUser().getNickName(), "teste");
		Assert.assertEquals(adminBean.getUser().getIsAdmin(), true);
		Assert.assertEquals(adminBean.getUser().getDataCadastro(), this.dataCadastroAtualizacao);
		Assert.assertEquals(adminBean.getUser().getDataAtualizacao(), this.dataCadastroAtualizacao);
		
		Mockito.verify(adminBean, Mockito.times(1)).viewUser(Mockito.any(User.class));
		
		User user2 = createUser();
		Assert.assertEquals(this.user.getEmail(), user2.getEmail());
		Assert.assertTrue(this.user.equals(user2));
		Assert.assertEquals(adminBean.getUser(), user2);
		
		
	}
	
	@Test
	public void shouldAddOrUpdate() {
		adminBean.setUser(createUserByConstructorWithoutId());
				
		Mockito.doNothing().when(daoUser).save(Mockito.any(User.class));
		Mockito.doNothing().when(menuBean).save();
		
		String result = adminBean.addOrUpdate();
		Assert.assertEquals(result, ADMIN_LIST);
		
		adminBean.setUser(createUserByConstructor());
		Mockito.doNothing().when(daoUser).update(Mockito.any(User.class));
		Mockito.doNothing().when(menuBean).update();
		
		result = adminBean.addOrUpdate();
		Assert.assertEquals(result, ADMIN_LIST);
	}
	
	@Test
	public void shouldDeleteUser() {
		Mockito.doNothing().when(daoUser).delete(Mockito.any(User.class));
		Mockito.doNothing().when(menuBean).delete();
		
		adminBean.deleteUser(this.user);
		
		Mockito.verify(daoUser, Mockito.times(1)).delete(Mockito.any(User.class));
		Mockito.verify(menuBean, Mockito.times(1)).delete();
	}

	@Test
	public void shouldAddUser() {		
		String result = adminBean.addUser();
		
		Assert.assertEquals(result, ADMIN_EDIT);
	}
	
	@Test
	public void shouldEditUser() {
		String result = adminBean.editUser(this.user);
		
		Assert.assertEquals(result, ADMIN_EDIT);
	}
	
	
	public Calendar createDataCadastroAtualizacao() {
		this.dataCadastroAtualizacao = Calendar.getInstance();
		return this.dataCadastroAtualizacao;
	}
	
	public User createUserByConstructor() {
		this.user = new User(1, "teste@gmail.com", "12345", "teste", true, createDataCadastroAtualizacao(), createDataCadastroAtualizacao());
		return this.user; 
	}
	
	public User createUserByConstructorWithoutId() {
		this.user = new User("teste@gmail.com", "12345", "teste", true, createDataCadastroAtualizacao(), createDataCadastroAtualizacao());
		return this.user; 
	}
	
	public User createUser() {
		User user = new User();
		
		user.setId(1);
		user.setEmail("teste@gmail.com");
		user.setPassword("12345");
		user.setNickName("teste");
		user.setIsAdmin(true);
		user.setDataCadastro(createDataCadastroAtualizacao());
		user.setDataAtualizacao(createDataCadastroAtualizacao());
		
		return user;
	}
	
}
