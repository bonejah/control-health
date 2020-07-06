package br.com.controlHealth.bean;

import java.io.Serializable;
import java.util.Calendar;
import java.util.List;

import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;

import org.primefaces.model.LazyDataModel;

import br.com.controlHealth.dao.impl.TipoEspecialidadeDAOImpl;
import br.com.controlHealth.datamodel.TipoEspecialidadeLazyDataModel;
import br.com.controlHealth.model.TipoEspecialidade;

@Named
@SessionScoped
public class TipoEspecialidadeBean implements Serializable {

	private static final String TIPO_ESPECIALIDADE_EDIT = "/tipoEspecialidade/edit?faces-redirect=true";

	private static final String TIPO_ESPECIALIDADE_LIST = "/tipoEspecialidade/list?faces-redirect=true";

	private static final long serialVersionUID = -7362410930483248984L;

	@Inject
	private TipoEspecialidade tipoEspecialidade;

	@Inject
	private MenuBean menuBean;

	@Inject
	private TipoEspecialidadeDAOImpl daoTipoEspecialidade;
	
	@Inject
	private TipoEspecialidadeLazyDataModel model;

	private List<TipoEspecialidade> listaTipoEspecialidade;

	public TipoEspecialidadeBean() {}

	public String saveOrUpdate() {
		if (this.tipoEspecialidade.getId() == null) {
			this.tipoEspecialidade.setDataCadastro(Calendar.getInstance());
			this.daoTipoEspecialidade.save(this.tipoEspecialidade);
			this.menuBean.save();
			return TIPO_ESPECIALIDADE_LIST;
		} else {
			this.tipoEspecialidade.setDataAtualizacao(Calendar.getInstance());
			this.daoTipoEspecialidade.update(this.tipoEspecialidade);
			this.menuBean.update();
			return TIPO_ESPECIALIDADE_LIST;
		}
	}

	public void deleteTipoEspecialidade(TipoEspecialidade tipoEspecialidade) {
		this.daoTipoEspecialidade.delete(tipoEspecialidade);
		this.menuBean.delete();
	}

	public List<TipoEspecialidade> getTipoEspecialidades() {
		this.listaTipoEspecialidade = this.daoTipoEspecialidade.getAll();
		return this.listaTipoEspecialidade;
	}

	public void viewTipoEspecialidade(TipoEspecialidade tipoEspecialidade) throws Exception {
		this.tipoEspecialidade = this.daoTipoEspecialidade.getById(tipoEspecialidade.getId());
	}

	public String listTipoEspecialidade() {
		this.tipoEspecialidade = new TipoEspecialidade();
		return TIPO_ESPECIALIDADE_LIST;
	}

	public String newTipoEspecialidade() {
		this.tipoEspecialidade = new TipoEspecialidade();
		return TIPO_ESPECIALIDADE_EDIT;
	}

	public String editTipoEspecialidade(TipoEspecialidade tipoEspecialidade) {
		this.tipoEspecialidade = tipoEspecialidade;
		return TIPO_ESPECIALIDADE_EDIT;
	}

	public TipoEspecialidade getTipoEspecialidade() {
		return tipoEspecialidade;
	}

	public void setTipoEspecialidade(TipoEspecialidade tipoEspecialidade) {
		this.tipoEspecialidade = tipoEspecialidade;
	}
	
	public LazyDataModel<TipoEspecialidade> getModel() {
		return this.model;
	}

}
