package br.com.controlHealth.bean;

import java.io.InputStream;
import java.io.Serializable;
import java.util.Properties;

import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;

import br.com.controlHealth.dao.impl.UserDAOImpl;
import br.com.controlHealth.infra.EncryptAndDecryptPass;
import br.com.controlHealth.model.User;

//@ManagedBean
//@ViewScoped     		// javax.faces.view.ViewScoped
//@SessionScoped  		// javax.faces.view.SessionScoped
//@SessionScoped		// javax.enterprise.context.SessionScoped	
//@ApplicationScoped	// javax.enterprise.context.ApplicationScoped

@Named
@SessionScoped
public class LoginBean implements Serializable {

	private static final long serialVersionUID = -7400826263349806244L;

	private static final String LOGIN = "login";
	private static final String LOGIN_REDIRECT = "/login?faces-redirect=true";
	private static final String CONSULTA_LIST = "/consulta/list?faces-redirect=true";

	@Inject
	private User usuario;

	@Inject
	private UserDAOImpl daoUser;
	
	@Inject
	private FacesContext context;

	private String nameApp;

	private String versionApp;

	public LoginBean() {
		this.getVersion();
	}

	public String login() {
		User userFounded = this.daoUser.checkIfExistsUser(this.usuario);

		if (userFounded != null) {
			boolean passwordOk = EncryptAndDecryptPass.decryptPassword(this.usuario.getPassword(),
					userFounded.getPassword());

			if (passwordOk) {
				this.context.getExternalContext().getSessionMap().put("usuarioLogado", userFounded);
				return CONSULTA_LIST;
			} else {
				setContextMessage();
				return LOGIN;
			}
		} else {
			setContextMessage();
			return LOGIN;
		}
	}

	private void setContextMessage() {
		this.context.getExternalContext().getFlash().setKeepMessages(true);

		String language = FacesContext.getCurrentInstance().getViewRoot().getLocale().getLanguage();

		if (language.equalsIgnoreCase("pt")) {
			this.context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "Usuário/Senha inválidos!", "Warning"));
		} else if (language.equalsIgnoreCase("en")) {
			this.context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "User/Password invalid!", "Warning"));
		}
	}

	public String logout() {
		this.context.getExternalContext().getSessionMap().remove("usuarioLogado");
		return LOGIN_REDIRECT;
	}

	private void getVersion() {
		try {
			InputStream in = this.context.getCurrentInstance().getExternalContext().getResourceAsStream("/WEB-INF/classes/resources/application.properties");
			Properties props = new Properties();
			props.load(in);
			this.nameApp = props.getProperty("application.name");
			this.versionApp = props.getProperty("application.version");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public User getUsuario() {
		return usuario;
	}

	public String getNameApp() {
		return nameApp;
	}

	public String getVersionApp() {
		return versionApp;
	}

}
