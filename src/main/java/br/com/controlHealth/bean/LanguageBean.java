package br.com.controlHealth.bean;

import java.io.Serializable;
import java.util.Locale;

import javax.enterprise.context.SessionScoped;
import javax.faces.context.FacesContext;
import javax.inject.Named;

@Named
@SessionScoped
public class LanguageBean implements Serializable {
	
	private static final long serialVersionUID = 7920935662764644913L;
	
	public void changeLanguage(String language) {
		FacesContext.getCurrentInstance().getApplication().setDefaultLocale(new Locale(language));
	}
	
}
