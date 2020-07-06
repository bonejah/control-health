package br.com.controlHealth.bean;

import java.io.Serializable;
import java.util.Calendar;
import java.util.List;

import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;

import org.primefaces.model.LazyDataModel;

import br.com.controlHealth.dao.impl.TipoEnderecoDAOImpl;
import br.com.controlHealth.datamodel.TipoEnderecoLazyDataModel;
import br.com.controlHealth.model.TipoEndereco;

@Named
@SessionScoped
public class TipoEnderecoBean implements Serializable {

	private static final long serialVersionUID = -8201735575114505151L;

	private static final String TIPO_ENDERECO_EDIT = "/tipoEndereco/edit?faces-redirect=true";
	private static final String TIPO_ENDERECO_LIST = "/tipoEndereco/list?faces-redirect=true";

	@Inject
	private TipoEndereco tipoEndereco;

	@Inject
	private MenuBean menuBean;

	@Inject
	private TipoEnderecoDAOImpl daoTipoEndereco;
	
	@Inject
	private TipoEnderecoLazyDataModel model;

	private List<TipoEndereco> listaTipoEnderecos;

	public TipoEnderecoBean() {}

	public String saveOrUpdate() {
		if (this.tipoEndereco.getId() == null) {
			this.tipoEndereco.setDataCadastro(Calendar.getInstance());
			this.daoTipoEndereco.save(this.tipoEndereco);
			this.menuBean.save();
			return TIPO_ENDERECO_LIST;
		} else {
			this.tipoEndereco.setDataAtualizacao(Calendar.getInstance());
			this.daoTipoEndereco.update(this.tipoEndereco);
			this.menuBean.update();
			return TIPO_ENDERECO_LIST;
		}
	}

	public void deleteTipoEndereco(TipoEndereco tipoEndereco) {
		this.daoTipoEndereco.delete(tipoEndereco);
		this.menuBean.delete();
	}

	public List<TipoEndereco> getListaTipoEnderecos() {
		this.listaTipoEnderecos = this.daoTipoEndereco.getAll();
		return this.listaTipoEnderecos;
	}

	public void viewTipoEndereco(TipoEndereco tipoEndereco) throws Exception {
		this.tipoEndereco = this.daoTipoEndereco.getById(tipoEndereco.getId());
	}

	public String listTipoEndereco() {
		this.tipoEndereco = new TipoEndereco();
		return TIPO_ENDERECO_LIST;
	}

	public String newTipoEndereco() {
		this.tipoEndereco = new TipoEndereco();
		return TIPO_ENDERECO_EDIT;
	}

	public String editTipoEndereco(TipoEndereco tipoEndereco) {
		this.tipoEndereco = tipoEndereco;
		return TIPO_ENDERECO_EDIT;
	}

	public TipoEndereco getTipoEndereco() {
		return tipoEndereco;
	}

	public void setTipoEndereco(TipoEndereco tipoEndereco) {
		this.tipoEndereco = tipoEndereco;
	}

	public LazyDataModel<TipoEndereco> getModel() {
		return this.model;
	}
	
}
