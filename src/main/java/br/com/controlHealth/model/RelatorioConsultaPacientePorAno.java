package br.com.controlHealth.model;

import java.io.Serializable;

public class RelatorioConsultaPacientePorAno implements Serializable {
	private static final long serialVersionUID = -872857869890646627L;
	private int ano;
	private Long quantidade;

	public RelatorioConsultaPacientePorAno() {
	}

	public RelatorioConsultaPacientePorAno(int ano, Long quantidade) {
		super();
		this.ano = ano;
		this.quantidade = quantidade;
	}

	public int getAno() {
		return ano;
	}

	public void setAno(int ano) {
		this.ano = ano;
	}

	public Long getQuantidade() {
		return quantidade;
	}

	public void setQuantidade(Long quantidade) {
		this.quantidade = quantidade;
	}

	@Override
	public String toString() {
		return "RelatorioConsultaPacientePorAno [ano=" + ano + ", quantidade=" + quantidade + "]";
	}
	
}
