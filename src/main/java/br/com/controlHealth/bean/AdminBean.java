package br.com.controlHealth.bean;

import java.io.Serializable;
import java.util.Calendar;

import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;

import org.primefaces.model.LazyDataModel;

import br.com.controlHealth.dao.impl.UserDAOImpl;
import br.com.controlHealth.datamodel.UserLazyDataModel;
import br.com.controlHealth.infra.EncryptAndDecryptPass;
import br.com.controlHealth.model.User;

@Named
@SessionScoped
public class AdminBean implements Serializable {

	private static final long serialVersionUID = 7377787171960972412L;

	private static final String ADMIN_LIST = "/admin/list?faces-redirect=true";
	private static final String ADMIN_EDIT = "/admin/edit?faces-redirect=true";

	@Inject
	private User user;
	
	@Inject
	private UserDAOImpl daoUser;
	
	@Inject
	private MenuBean menuBean;
	
	@Inject
	private UserLazyDataModel model;
	
	public AdminBean() {}
	
	@Inject
	public AdminBean(UserDAOImpl daoUser, MenuBean menuBean, UserLazyDataModel model, User user) {
		super();
		this.daoUser = daoUser;
		this.menuBean = menuBean;
		this.model = model;
		this.user = user;
	}

	public void viewUser(User usuario) throws Exception {
		this.user = this.daoUser.getById(usuario.getId());
	}

	public String addOrUpdate() {
		if (this.user.getId() == null) {
			this.user.setPassword(EncryptAndDecryptPass.cryptPassword(this.user.getPassword()));
			this.user.setDataCadastro(Calendar.getInstance());
			this.daoUser.save(this.user);
			this.menuBean.save();
			return ADMIN_LIST;
		} else {
			this.user.setPassword(EncryptAndDecryptPass.cryptPassword(this.user.getPassword()));
			this.user.setDataAtualizacao(Calendar.getInstance());
			this.daoUser.update(this.user);
			this.menuBean.update();
			return ADMIN_LIST;
		}
	}

	public void deleteUser(User user) {
		this.daoUser.delete(user);
		this.menuBean.delete();
	}

	public String addUser() {
		this.user = new User();
		return ADMIN_EDIT;
	}

	public String editUser(User usuario) {
		this.user = usuario;
		return ADMIN_EDIT;
	}

	public String listUsers() {
		return ADMIN_LIST;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public LazyDataModel<User> getModel() {
		return this.model;
	}
	
}
