package br.com.controlHealth.bean;

import java.io.Serializable;
import java.util.Calendar;
import java.util.List;

import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;

import org.primefaces.model.LazyDataModel;

import br.com.controlHealth.dao.impl.EnderecoDAOImpl;
import br.com.controlHealth.dao.impl.TipoEnderecoDAOImpl;
import br.com.controlHealth.datamodel.EnderecoLazyDataModel;
import br.com.controlHealth.model.Endereco;
import br.com.controlHealth.model.TipoEndereco;

@Named
@SessionScoped
public class EnderecoBean implements Serializable {

	private static final long serialVersionUID = 5729844854174117962L;

	private static final String ENDERECO_LIST = "/endereco/list?faces-redirect=true";
	private static final String ENDERECO_EDIT = "/endereco/edit?faces-redirect=true";
	private static final String TIPO_ENDERECO_EDIT = "/tipoEndereco/edit?faces-redirect=true";
	
	@Inject
	private EnderecoDAOImpl daoEndereco;

	@Inject
	private TipoEnderecoDAOImpl daoTipoEndereco;

	@Inject
	private Endereco endereco;

	@Inject
	private MenuBean menuBean;

	@Inject
	private EnderecoLazyDataModel model;

	private List<Endereco> listaEnderecos;

	private List<TipoEndereco> listaTipoEnderecos;
	
	public EnderecoBean() {}

	public List<Endereco> getListaEnderecos() {
		this.listaEnderecos = this.daoEndereco.getAll();
		return this.listaEnderecos;
	}

	public List<TipoEndereco> getListaTipoEnderecos() {
		this.listaTipoEnderecos = this.daoTipoEndereco.getAll();
		return this.listaTipoEnderecos;
	}

	public String saveOrUpdate() {
		if (this.endereco.getId() == null) {
			this.endereco.setDataCadastro(Calendar.getInstance());
			this.daoEndereco.save(this.endereco);
			this.menuBean.save();
			return ENDERECO_LIST;	
		} else {
			this.endereco.setDataAtualizacao(Calendar.getInstance());
			this.daoEndereco.update(this.endereco);
			this.menuBean.update();
			return ENDERECO_LIST;
		}
	}

	public void deleteEndereco(Endereco endereco) {
		this.daoEndereco.delete(endereco);
		this.menuBean.delete();
	}

	public void viewEndereco(Endereco endereco) throws Exception {
		this.endereco = this.daoEndereco.getById(endereco.getId());
	}

	public String listEnderecos() {
		this.endereco = new Endereco();
		return ENDERECO_LIST;
	}

	public String newEndereco() {
		this.endereco = new Endereco();
		return ENDERECO_EDIT;
	}

	public String editEndereco(Endereco endereco) {
		this.endereco = endereco;
		return ENDERECO_EDIT;
	}

	public String newTipoEndereco() {
		return TIPO_ENDERECO_EDIT;
	}

	public Endereco getEndereco() {
		return endereco;
	}

	public void setEndereco(Endereco endereco) {
		this.endereco = endereco;
	}

	public LazyDataModel<Endereco> getModel() {
		return this.model;
	}

}
