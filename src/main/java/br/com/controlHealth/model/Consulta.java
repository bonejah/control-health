package br.com.controlHealth.model;

import java.io.Serializable;
import java.util.Calendar;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name = "tbConsulta")
public class Consulta implements Serializable, Comparable<Consulta> {

	private static final long serialVersionUID = 5142593201254834690L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "data_cadastro", nullable = false)
	private Calendar dataCadastro;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "data_atualizacao", nullable = true)
	private Calendar dataAtualizacao;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "data_consulta", nullable = false)
	private Date dataConsulta;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "data_retorno", nullable = true)
	private Date dataRetorno;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "medico_id", nullable =  false)
	private Medico medico;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "paciente_id", nullable =  false)
	private Paciente paciente;

	@OneToOne(optional = false, fetch = FetchType.LAZY)
	@JoinColumn(name = "dadosDaConsulta_id", nullable =  false)
	private DadosDaConsulta dadosDaConsulta;

	public Consulta() {
		this.medico = new Medico();
		this.paciente = new Paciente();
		this.dadosDaConsulta = new DadosDaConsulta();
	}

	public Consulta(Calendar dataAtualizacao, Date dataCadastro, Date dataRetorno, Medico medico_consulta,
			Paciente paciente_consulta, DadosDaConsulta dadosDaConsulta) {
		super();
		this.dataAtualizacao = dataAtualizacao;
		this.dataConsulta = dataCadastro;
		this.dataRetorno = dataRetorno;
		this.medico = medico_consulta;
		this.paciente = paciente_consulta;
		this.dadosDaConsulta = dadosDaConsulta;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}
	
	public Calendar getDataCadastro() {
		return dataCadastro;
	}

	public void setDataCadastro(Calendar dataCadastro) {
		this.dataCadastro = dataCadastro;
	}

	public Calendar getDataAtualizacao() {
		return dataAtualizacao;
	}

	public void setDataAtualizacao(Calendar dataAtualizacao) {
		this.dataAtualizacao = dataAtualizacao;
	}

	public Date getDataConsulta() {
		return dataConsulta;
	}

	public void setDataConsulta(Date dataConsulta) {
		this.dataConsulta = dataConsulta;
	}

	public Date getDataRetorno() {
		return dataRetorno;
	}

	public void setDataRetorno(Date dataRetorno) {
		this.dataRetorno = dataRetorno;
	}

	public Medico getMedico() {
		return medico;
	}

	public void setMedico(Medico medico) {
		this.medico = medico;
	}

	public Paciente getPaciente() {
		return paciente;
	}

	public void setPaciente(Paciente paciente) {
		this.paciente = paciente;
	}

	public DadosDaConsulta getDadosDaConsulta() {
		return dadosDaConsulta;
	}

	public void setDadosDaConsulta(DadosDaConsulta dadosDaConsulta) {
		this.dadosDaConsulta = dadosDaConsulta;
	}

	@Override
	public String toString() {
		return "Consulta [id=" + id + ", dataCadastro=" + dataCadastro + ", dataAtualizacao=" + dataAtualizacao
				+ ", dataConsulta=" + dataConsulta + ", dataRetorno=" + dataRetorno + ", medico=" + medico
				+ ", paciente=" + paciente + ", dadosDaConsulta=" + dadosDaConsulta + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((dadosDaConsulta == null) ? 0 : dadosDaConsulta.hashCode());
		result = prime * result + ((dataAtualizacao == null) ? 0 : dataAtualizacao.hashCode());
		result = prime * result + ((dataConsulta == null) ? 0 : dataConsulta.hashCode());
		result = prime * result + ((dataRetorno == null) ? 0 : dataRetorno.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((medico == null) ? 0 : medico.hashCode());
		result = prime * result + ((paciente == null) ? 0 : paciente.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Consulta other = (Consulta) obj;
		if (dadosDaConsulta == null) {
			if (other.dadosDaConsulta != null)
				return false;
		} else if (!dadosDaConsulta.equals(other.dadosDaConsulta))
			return false;
		if (dataAtualizacao == null) {
			if (other.dataAtualizacao != null)
				return false;
		} else if (!dataAtualizacao.equals(other.dataAtualizacao))
			return false;
		if (dataConsulta == null) {
			if (other.dataConsulta != null)
				return false;
		} else if (!dataConsulta.equals(other.dataConsulta))
			return false;
		if (dataRetorno == null) {
			if (other.dataRetorno != null)
				return false;
		} else if (!dataRetorno.equals(other.dataRetorno))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (medico == null) {
			if (other.medico != null)
				return false;
		} else if (!medico.equals(other.medico))
			return false;
		if (paciente == null) {
			if (other.paciente != null)
				return false;
		} else if (!paciente.equals(other.paciente))
			return false;
		return true;
	}

	@Override
	public int compareTo(Consulta consulta) {
		return this.compareTo(consulta);
	}

}
