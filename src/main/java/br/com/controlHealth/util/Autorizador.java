package br.com.controlHealth.util;

import javax.faces.application.NavigationHandler;
import javax.faces.context.FacesContext;
import javax.faces.event.PhaseEvent;
import javax.faces.event.PhaseId;
import javax.faces.event.PhaseListener;

import br.com.controlHealth.model.User;

public class Autorizador implements PhaseListener {
	
	private static final long serialVersionUID = 2221631364176545911L;

	private static final String LOGIN = "/login.xhtml";
	private static final String LOGIN_REDIRECT = "/login?faces-redirect=true";
	
	@Override
	public void afterPhase(PhaseEvent event) {
		System.out.println("After phase: " + event.getPhaseId());
		FacesContext context = event.getFacesContext();
		String nomePagina = context.getViewRoot().getViewId();
		System.out.println(nomePagina);

		if (nomePagina.equals(LOGIN)) {
			return;
		}

		User usuarioLogado = (User) context.getExternalContext().getSessionMap().get("usuarioLogado");

		if (usuarioLogado != null) {
			return;
		}
		
		NavigationHandler handler = context.getApplication().getNavigationHandler();
		handler.handleNavigation(context, null, LOGIN_REDIRECT);
		context.renderResponse();
	}

	@Override
	public void beforePhase(PhaseEvent event) {
		System.out.println("Before phase: " + event.getPhaseId());
	}

	@Override
	public PhaseId getPhaseId() {
		return PhaseId.RESTORE_VIEW;
	}
}
