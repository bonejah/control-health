package br.com.controlHealth.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "tbDadosConsulta")
public class DadosDaConsulta implements Serializable {
	
	private static final long serialVersionUID = -899441947530014156L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	@OneToOne(mappedBy = "dadosDaConsulta")
	private Consulta consulta;
	
	private Double peso;
	
	private Double estatura;
	
	@Column(name = "percentual_craniano")
	private Double percentualCraniano;

	@Lob
	@Column(name = "desc_consulta", nullable = false)
	private String descricaoDaConsulta;
	
	public DadosDaConsulta(){}
	
	public DadosDaConsulta(Integer id, Consulta consulta, Double peso,
			Double estatura, Double percentualCraniano,
			String descricaoDaConsulta) {
		super();
		this.consulta = consulta;
		this.peso = peso;
		this.estatura = estatura;
		this.percentualCraniano = percentualCraniano;
		this.descricaoDaConsulta = descricaoDaConsulta;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Consulta getConsulta() {
		return consulta;
	}

	public void setConsulta(Consulta consulta) {
		this.consulta = consulta;
	}

	public Double getPeso() {
		return peso;
	}

	public void setPeso(Double peso) {
		this.peso = peso;
	}

	public Double getEstatura() {
		return estatura;
	}

	public void setEstatura(Double estatura) {
		this.estatura = estatura;
	}

	public Double getPercentualCraniano() {
		return percentualCraniano;
	}

	public void setPercentualCraniano(Double percentualCraniano) {
		this.percentualCraniano = percentualCraniano;
	}

	public String getDescricaoDaConsulta() {
		return descricaoDaConsulta;
	}

	public void setDescricaoDaConsulta(String descricaoDaConsulta) {
		this.descricaoDaConsulta = descricaoDaConsulta;
	}
	
	public String montaDescricaoDaConsulta() {
		StringBuilder sb = new StringBuilder();
		sb.append("Uso Oral: \n");
		sb.append("1-) \n\n");
		sb.append("2-) \n\n");
		sb.append("3-) \n\n");
		sb.append("Uso Nasal: \n");
		sb.append("4-) \n\n");
		sb.append("5-) \n\n");
		return sb.toString(); 
	}

	@Override
	public String toString() {
		return "DadosDaConsulta [id=" + id + ", consulta=" + consulta
				+ ", peso=" + peso + ", estatura=" + estatura
				+ ", percentualCraniano=" + percentualCraniano
				+ ", descricaoDaConsulta=" + descricaoDaConsulta + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((consulta == null) ? 0 : consulta.hashCode());
		result = prime
				* result
				+ ((descricaoDaConsulta == null) ? 0 : descricaoDaConsulta
						.hashCode());
		result = prime * result
				+ ((estatura == null) ? 0 : estatura.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime
				* result
				+ ((percentualCraniano == null) ? 0 : percentualCraniano
						.hashCode());
		result = prime * result + ((peso == null) ? 0 : peso.hashCode());
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
		DadosDaConsulta other = (DadosDaConsulta) obj;
		if (consulta == null) {
			if (other.consulta != null)
				return false;
		} else if (!consulta.equals(other.consulta))
			return false;
		if (descricaoDaConsulta == null) {
			if (other.descricaoDaConsulta != null)
				return false;
		} else if (!descricaoDaConsulta.equals(other.descricaoDaConsulta))
			return false;
		if (estatura == null) {
			if (other.estatura != null)
				return false;
		} else if (!estatura.equals(other.estatura))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (percentualCraniano == null) {
			if (other.percentualCraniano != null)
				return false;
		} else if (!percentualCraniano.equals(other.percentualCraniano))
			return false;
		if (peso == null) {
			if (other.peso != null)
				return false;
		} else if (!peso.equals(other.peso))
			return false;
		return true;
	}
	
}