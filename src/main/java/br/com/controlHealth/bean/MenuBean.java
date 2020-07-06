package br.com.controlHealth.bean;

import java.io.Serializable;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

@Named
@ViewScoped
public class MenuBean implements Serializable{
	
	private static final long serialVersionUID = -4036843100486633877L;
	
	@Inject
	private FacesContext context;
	
	public void save() {
		addMessage("Registro salvo com sucesso!", "Sucesso");
	}

	public void update() {
		addMessage("Registro atualizado com sucesso!", "Successo");
	}

	public void delete() {
		addMessage("Registro removido com sucesso!", "Successo");
	}

	public void addMessage(String summary, String detail) {
		FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, summary, detail);
		this.context.getCurrentInstance().addMessage("messages", message);
		this.context.getCurrentInstance().getExternalContext().getFlash().setKeepMessages(true);
	}

	public void errorMessage(String summary, String detail) {
		FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, summary, detail);
		this.context.getCurrentInstance().addMessage("messages", message);
		this.context.getCurrentInstance().getExternalContext().getFlash().setKeepMessages(true);
	}
	
}
