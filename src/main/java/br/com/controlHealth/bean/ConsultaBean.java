package br.com.controlHealth.bean;

import java.io.Serializable;
import java.util.Calendar;
import java.util.List;

import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;

import org.primefaces.model.LazyDataModel;

import br.com.controlHealth.dao.impl.ConsultaDAOImpl;
import br.com.controlHealth.dao.impl.DadosDaConsultaDAOImpl;
import br.com.controlHealth.dao.impl.MedicoDAOImpl;
import br.com.controlHealth.dao.impl.PacienteDAOImpl;
import br.com.controlHealth.datamodel.ConsultaLazyDataModel;
import br.com.controlHealth.model.Consulta;
import br.com.controlHealth.model.Medico;
import br.com.controlHealth.model.Paciente;

@Named
@SessionScoped
public class ConsultaBean implements Serializable {

	private static final long serialVersionUID = -7816509409372439751L;
	
	private static final String CONSULTA_LIST = "/consulta/list?faces-redirect=true";
	private static final String CONSULTA_EDIT = "/consulta/edit?faces-redirect=true";
	
	@Inject
	private ConsultaDAOImpl daoConsulta;

	@Inject
	private DadosDaConsultaDAOImpl daoDadosConsulta;

	@Inject
	private MedicoDAOImpl daoMedico;

	@Inject
	private PacienteDAOImpl daoPaciente;

	@Inject
	private ConsultaLazyDataModel model;

	@Inject
	private MenuBean menuBean;
	
	@Inject
	private Consulta consulta;

	private List<Medico> listaMedicos;
	
	private List<Paciente> listaPacientes;

	public ConsultaBean() {}
	
	public List<Medico> getListaMedicos() {
		this.listaMedicos = this.daoMedico.getAll();
		return this.listaMedicos;
	}

	public List<Paciente> getListaPacientes() {
		this.listaPacientes = this.daoPaciente.getAll();
		return this.listaPacientes;
	}

	public String saveOrUpdate() {
		if (this.consulta.getId() == null) {
			this.consulta.setDataCadastro(Calendar.getInstance());
			this.daoDadosConsulta.save(this.consulta.getDadosDaConsulta());
			this.daoConsulta.save(this.consulta);
			this.menuBean.save();
			return CONSULTA_LIST;
		} else {
			this.consulta.setDataAtualizacao(Calendar.getInstance());
			this.daoDadosConsulta.update(this.consulta.getDadosDaConsulta());
			this.daoConsulta.update(this.consulta);
			this.menuBean.update();
			return CONSULTA_LIST;
		}
	}

	public void deleteConsulta(Consulta consulta) {
		Consulta returnConsulta = daoConsulta.getDadosDaConsulta(consulta.getId());
		this.daoConsulta.delete(returnConsulta);
		this.daoDadosConsulta.delete(returnConsulta.getDadosDaConsulta());
		this.menuBean.delete();
	}

	public String listConsultas() {
		this.consulta = new Consulta();
		return CONSULTA_LIST;
	}

	public String newConsulta() {
		this.consulta = new Consulta();
		this.consulta.getDadosDaConsulta().setDescricaoDaConsulta(this.consulta.getDadosDaConsulta().montaDescricaoDaConsulta());
		return CONSULTA_EDIT;
	}
	
	public String editConsulta(Consulta consulta) {
		Consulta resultConsulta = daoConsulta.getDadosDaConsulta(consulta.getId());
		this.consulta = resultConsulta;
		return CONSULTA_EDIT;
	}

	public void viewConsulta(Consulta consulta) throws Exception {
		Consulta resultConsulta = daoConsulta.getDadosDaConsulta(consulta.getId());
		this.consulta = resultConsulta;
	}
	
	public Consulta getConsulta() {
		return this.consulta;
	}
	
	public void setConsulta(Consulta consulta) {
		this.consulta = consulta;
	}
	
	public LazyDataModel<Consulta> getModel() {
		return this.model;
	}

}
