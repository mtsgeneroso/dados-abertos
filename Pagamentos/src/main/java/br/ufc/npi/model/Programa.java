package br.ufc.npi.model;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToMany;

@Entity(name="programa")
public class Programa {
	
	@Id
	@Column(nullable=false, name="cod_programa")
	private Long codPrograma;
	@Column(name="nome_programa")
	private String nomePrograma;
	
	@ManyToMany(mappedBy="programas")
	private List<Acao> acoes;

	public Long getCodPrograma() {
		return codPrograma;
	}

	public void setCodPrograma(Long codPrograma) {
		this.codPrograma = codPrograma;
	}

	public String getNomePrograma() {
		return nomePrograma;
	}

	public void setNomePrograma(String nomePrograma) {
		this.nomePrograma = nomePrograma;
	}

	public List<Acao> getAcoes() {
		return acoes;
	}

	public void setAcoes(List<Acao> acoes) {
		this.acoes = acoes;
	}
	
	

}
