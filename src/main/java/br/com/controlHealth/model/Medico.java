package br.com.controlHealth.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

@Entity
@Table(name = "tbMedico")
public class Medico implements Serializable {

	private static final long serialVersionUID = -4204512736575187377L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	@Column(nullable = false, length = 50)
	private String nome;

	@Column(nullable = true, length = 10)
	private String crm;

	@Column(nullable = false, length = 15)
	private String telefone;

	@Column(nullable = false, length = 15)
	private String celular;

	@Column(nullable = false, length = 30)
	private String email;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "data_cadastro", nullable = false)
	private Calendar dataCadastro;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "data_atualizacao", nullable = true)
	private Calendar dataAtualizacao;

	private Boolean ativo;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "tipoEspecialidade")
	private TipoEspecialidade tipoEspecialidade;

	@ManyToMany(fetch = FetchType.EAGER)
	@Fetch(value = FetchMode.SUBSELECT)
	private List<Endereco> listaEnderecoMedico = new ArrayList<Endereco>();
	
	public Medico() {
		tipoEspecialidade = new TipoEspecialidade();
	}

	public Medico(String nome, String crm, String telefone, String celular, String email, Calendar dataCadastro,
			Calendar dataAtualizacao, Boolean ativo, TipoEspecialidade tipoEspecialidade,
			List<Endereco> listaEnderecoMedico) {
		super();
		this.nome = nome;
		this.crm = crm;
		this.telefone = telefone;
		this.celular = celular;
		this.email = email;
		this.dataCadastro = dataCadastro;
		this.dataAtualizacao = dataAtualizacao;
		this.ativo = ativo;
		this.tipoEspecialidade = tipoEspecialidade;
		this.listaEnderecoMedico = listaEnderecoMedico;
	}

	public void addEndereco(Endereco endereco) {
		this.listaEnderecoMedico.add(endereco);
	}

	public void removeEndereco(Endereco endereco) {
		this.listaEnderecoMedico.remove(endereco);
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getCrm() {
		return crm;
	}

	public void setCrm(String crm) {
		this.crm = crm;
	}

	public String getTelefone() {
		return telefone;
	}

	public void setTelefone(String telefone) {
		this.telefone = telefone;
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

	public Boolean getAtivo() {
		return ativo;
	}

	public void setAtivo(Boolean ativo) {
		this.ativo = ativo;
	}

	public TipoEspecialidade getEspecialidade() {
		return tipoEspecialidade;
	}

	public void setEspecialidade(TipoEspecialidade especialidade) {
		this.tipoEspecialidade = especialidade;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public List<Endereco> getListaEnderecoMedico() {
		return listaEnderecoMedico;
	}

	public void setListaEnderecoMedico(List<Endereco> listaEnderecoMedico) {
		this.listaEnderecoMedico = listaEnderecoMedico;
	}

	public TipoEspecialidade getTipoEspecialidade() {
		return tipoEspecialidade;
	}

	public void setTipoEspecialidade(TipoEspecialidade tipoEspecialidade) {
		this.tipoEspecialidade = tipoEspecialidade;
	}

	public String getCelular() {
		return celular;
	}

	public void setCelular(String celular) {
		this.celular = celular;
	}

	@Override
	public String toString() {
		return "Medico [id=" + id + ", nome=" + nome + ", crm=" + crm + ", telefone=" + telefone + ", celular="
				+ celular + ", email=" + email + ", dataCadastro=" + dataCadastro + ", dataAtualizacao="
				+ dataAtualizacao + ", ativo=" + ativo + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((ativo == null) ? 0 : ativo.hashCode());
		result = prime * result + ((celular == null) ? 0 : celular.hashCode());
		result = prime * result + ((crm == null) ? 0 : crm.hashCode());
		result = prime * result + ((email == null) ? 0 : email.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((nome == null) ? 0 : nome.hashCode());
		result = prime * result + ((telefone == null) ? 0 : telefone.hashCode());
		result = prime * result + ((tipoEspecialidade == null) ? 0 : tipoEspecialidade.hashCode());
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
		Medico other = (Medico) obj;
		if (ativo == null) {
			if (other.ativo != null)
				return false;
		} else if (!ativo.equals(other.ativo))
			return false;
		if (celular == null) {
			if (other.celular != null)
				return false;
		} else if (!celular.equals(other.celular))
			return false;
		if (crm == null) {
			if (other.crm != null)
				return false;
		} else if (!crm.equals(other.crm))
			return false;
		if (email == null) {
			if (other.email != null)
				return false;
		} else if (!email.equals(other.email))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (nome == null) {
			if (other.nome != null)
				return false;
		} else if (!nome.equals(other.nome))
			return false;
		if (telefone == null) {
			if (other.telefone != null)
				return false;
		} else if (!telefone.equals(other.telefone))
			return false;
		if (tipoEspecialidade == null) {
			if (other.tipoEspecialidade != null)
				return false;
		} else if (!tipoEspecialidade.equals(other.tipoEspecialidade))
			return false;
		return true;
	}

}
