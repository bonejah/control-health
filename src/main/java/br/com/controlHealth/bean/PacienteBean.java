package br.com.controlHealth.bean;

import java.io.Serializable;
import java.util.Calendar;
import java.util.List;

import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;

import org.primefaces.model.LazyDataModel;

import br.com.controlHealth.dao.impl.EnderecoDAOImpl;
import br.com.controlHealth.dao.impl.PacienteDAOImpl;
import br.com.controlHealth.dao.impl.TipoEnderecoDAOImpl;
import br.com.controlHealth.dao.impl.TipoEspecialidadeDAOImpl;
import br.com.controlHealth.datamodel.PacienteLazyDataModel;
import br.com.controlHealth.model.Endereco;
import br.com.controlHealth.model.Paciente;
import br.com.controlHealth.model.TipoEndereco;
import br.com.controlHealth.model.TipoEspecialidade;

@Named
@SessionScoped
public class PacienteBean implements Serializable {

	private static final long serialVersionUID = 9002544314630677396L;

	private static final String PACIENTE_LIST = "/paciente/list?faces-redirect=true";
	private static final String PACIENTE_EDIT = "/paciente/edit?faces-redirect=true";
	private static final String ENDERECO_EDIT = "/endereco/edit?faces-redirect=true";
	private static final String TIPO_ESPECIALIDADE_EDIT = "/tipoEspecialidade/edit?faces-redirect=true";

	@Inject
	private Paciente paciente;

	@Inject
	private MenuBean menuBean;

	@Inject
	private PacienteDAOImpl daoPaciente;

	@Inject
	private EnderecoDAOImpl daoEndereco;
	
	@Inject
	private TipoEnderecoDAOImpl daoTipoEndereco;
	
	@Inject
	private TipoEspecialidadeDAOImpl daoTipoEspecialidade;

	@Inject
	private PacienteLazyDataModel model;

	private Integer enderecoId;

	private Integer tipoEnderecoId;

	private List<Paciente> listaPacientes;

	private List<Endereco> listaEndereco;

	private List<TipoEndereco> listaTipoEnderecos;

	private List<TipoEspecialidade> listaTipoEspecialidades;

	public PacienteBean() {}

	public String saveOrUpdate() {
		if (this.paciente.getId() == null) {
			this.paciente.setDataCadastro(Calendar.getInstance());
			this.daoPaciente.save(this.paciente);
			this.menuBean.save();
			return PACIENTE_LIST;
		} else {
			this.paciente.setDataAtualizacao(Calendar.getInstance());
			this.daoPaciente.update(this.paciente);
			this.menuBean.update();
			return PACIENTE_LIST;
		}
	}

	public void deletePaciente(Paciente paciente) {
		this.daoPaciente.delete(paciente);
		this.menuBean.delete();
	}

	public void removeEnderecoPaciente(Endereco endereco) {
		this.paciente.removeEndereco(endereco);
	}

	public void addEnderecoPaciente() {
		if (this.enderecoId == null) {
			return;
		} else {
			this.paciente.adicionaEndereco(this.daoEndereco.getById(this.enderecoId));
		}
	}

	public void selecionaEnderecoPorTipoEndereco() {
		if (this.tipoEnderecoId != null) {
			setListaEndereco(this.daoEndereco.selectAddressByTypeAddress(this.tipoEnderecoId));
		}
	}

	public List<TipoEndereco> getListaTipoEnderecos() {
		this.listaTipoEnderecos =  this.daoTipoEndereco.getAll();
		return this.listaTipoEnderecos;
	}

	public List<TipoEspecialidade> getTipoEspecialidades() {
		this.listaTipoEspecialidades = this.daoTipoEspecialidade.getAll();
		return this.listaTipoEspecialidades;
	}

	public List<Paciente> getListaPacientes() {
		this.listaPacientes = this.daoPaciente.getAll();
		return this.listaPacientes;
	}
	
	public void viewPaciente(Paciente paciente) throws Exception {
		this.paciente = this.daoPaciente.getById(paciente.getId());
	}

	public String listPacientes() {
		this.paciente = new Paciente();
		return PACIENTE_LIST;
	}

	public String newPaciente() {
		this.tipoEnderecoId = null;
		this.enderecoId = null;
		this.paciente = new Paciente();
		return PACIENTE_EDIT;
	}

	public String editPaciente(Paciente paciente) {
		this.tipoEnderecoId = null;
		this.enderecoId = null;
		this.paciente = paciente;
		return PACIENTE_EDIT;
	}

	public String newEndereco() {
		return ENDERECO_EDIT;
	}

	public String newTipoEspecialidade() {
		return TIPO_ESPECIALIDADE_EDIT;
	}

	public List<Endereco> getEnderecosDoPaciente() {
		return this.paciente.getListaEnderecoPaciente();
	}

	public List<Endereco> getListaEndereco() {
		return this.listaEndereco;
	}
	
	public void setListaEndereco(List<Endereco> listaEndereco) {
		this.listaEndereco = listaEndereco;
	}

	public Paciente getPaciente() {
		return paciente;
	}

	public void setPaciente(Paciente paciente) {
		this.paciente = paciente;
	}

	public Integer getEnderecoId() {
		return enderecoId;
	}

	public void setEnderecoId(Integer enderecoId) {
		this.enderecoId = enderecoId;
	}

	public Integer getTipoEnderecoId() {
		return tipoEnderecoId;
	}

	public void setTipoEnderecoId(Integer tipoEnderecoId) {
		this.tipoEnderecoId = tipoEnderecoId;
	}
	
	public LazyDataModel<Paciente> getModel() {
		return this.model;
	}
	
}
