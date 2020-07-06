package br.com.controlHealth.bean;

import java.io.Serializable;
import java.util.Calendar;
import java.util.List;

import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;

import org.primefaces.model.LazyDataModel;

import br.com.controlHealth.dao.impl.EnderecoDAOImpl;
import br.com.controlHealth.dao.impl.MedicoDAOImpl;
import br.com.controlHealth.dao.impl.TipoEnderecoDAOImpl;
import br.com.controlHealth.dao.impl.TipoEspecialidadeDAOImpl;
import br.com.controlHealth.datamodel.MedicoLazyDataModel;
import br.com.controlHealth.model.Endereco;
import br.com.controlHealth.model.Medico;
import br.com.controlHealth.model.TipoEndereco;
import br.com.controlHealth.model.TipoEspecialidade;

@Named
@SessionScoped
public class MedicoBean implements Serializable {

	private static final long serialVersionUID = -786987583546847541L;

	private static final String MEDICO_LIST = "/medico/list?faces-redirect=true";
	private static final String MEDICO_EDIT = "/medico/edit?faces-redirect=true";
	private static final String TIPO_ESPECIALIDADE_EDIT = "/tipoEspecialidade/edit?faces-redirect=true";
	private static final String ENDERECO_EDIT = "/endereco/edit?faces-redirect=true";
	
	@Inject
	private MedicoDAOImpl daoMedico;

	@Inject
	private EnderecoDAOImpl daoEndereco;

	@Inject
	private TipoEnderecoDAOImpl daoTipoEndereco;

	@Inject
	private TipoEspecialidadeDAOImpl daoTipoEspecialidade;

	@Inject
	private MenuBean menuBean;

	@Inject
	private MedicoLazyDataModel model;

	@Inject
	private Medico medico;

	private Integer enderecoId;

	private Integer tipoEnderecoId;

	private List<Medico> listaMedicos;

	private List<Endereco> listaEndereco;

	private List<TipoEndereco> listaTipoEndereco;

	private List<TipoEspecialidade> listaTipoEspecialidade;

	public MedicoBean() {
		this.listaEndereco = null;
	}

	public String saveOrUpdate() {
		if (this.medico.getId() == null) {
			this.medico.setDataCadastro(Calendar.getInstance());
			this.daoMedico.save(this.medico);
			this.menuBean.save();
			return MEDICO_LIST;
		} else {
			this.medico.setDataAtualizacao(Calendar.getInstance());
			this.daoMedico.update(this.medico);
			this.menuBean.update();
			return MEDICO_LIST;
		}
	}

	public void deleteMedico(Medico medico) {
		this.daoMedico.delete(medico);
		this.menuBean.delete();
	}

	public void removeEnderecoMedico(Endereco endereco) {
		this.medico.removeEndereco(endereco);
	}

	public void addEnderecoMedico() {
		if (this.enderecoId == null) {
			return;
		} else {
			this.medico.addEndereco(this.daoEndereco.getById(this.enderecoId));
		}
	}

	public void selecionaEnderecoPorTipoEndereco() {
		if (this.tipoEnderecoId != null) {
			setListaEndereco(this.daoEndereco.selectAddressByTypeAddress(this.tipoEnderecoId));
		} 
	}

	public List<Medico> getListaMedicos() {
		this.listaMedicos = this.daoMedico.getAll();
		return this.listaMedicos;
	}

	public List<TipoEndereco> getListaTipoEnderecos() {
		this.listaTipoEndereco = this.daoTipoEndereco.getAll();
		return this.listaTipoEndereco;
	}

	public List<TipoEspecialidade> getListaTipoEspecialidade() {
		this.listaTipoEspecialidade = this.daoTipoEspecialidade.getAll();
		return this.listaTipoEspecialidade;
	}

	public void viewMedico(Medico medico) throws Exception {
		this.medico = this.daoMedico.getById(medico.getId());
	}

	public String listMedicos() {
		this.medico = new Medico();
		return MEDICO_LIST;
	}

	public String newMedico() {
		this.tipoEnderecoId = null;
		this.enderecoId = null;
		this.medico = new Medico();
		return MEDICO_EDIT;
	}

	public String editMedico(Medico medico) {
		this.tipoEnderecoId = null;
		this.enderecoId = null;
		this.medico = medico;
		return MEDICO_EDIT;
	}

	public String newTipoEspecialidade() {
		return TIPO_ESPECIALIDADE_EDIT;
	}

	public String newEndereco() {
		return ENDERECO_EDIT;
	}

	public Integer getTipoEnderecoId() {
		return tipoEnderecoId;
	}

	public void setTipoEnderecoId(Integer tipoEnderecoId) {
		this.tipoEnderecoId = tipoEnderecoId;
	}

	public Integer getEnderecoId() {
		return enderecoId;
	}

	public void setEnderecoId(Integer enderecoId) {
		this.enderecoId = enderecoId;
	}

	public Medico getMedico() {
		return medico;
	}

	public void setMedico(Medico medico) {
		this.medico = medico;
	}

	public List<Endereco> getEnderecosDoMedico() {
		return this.medico.getListaEnderecoMedico();
	}

	public void setListaEndereco(List<Endereco> listaEndereco) {
		this.listaEndereco = listaEndereco;
	}

	public List<Endereco> getListaEndereco() {
		return this.listaEndereco;
	}

	public LazyDataModel<Medico> getModel() {
		return this.model;
	}

}
