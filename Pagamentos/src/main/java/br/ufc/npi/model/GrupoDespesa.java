package br.ufc.npi.model;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;

@Entity(name="grupo_despesa")
public class GrupoDespesa {
	
	@Id
	@Column(nullable=false, name="cod_grupo_despesa")
	private int codGrupoDespesa;
	@Column(name="nome_grupo_despesa")
	private String nomeGrupoDespesa;
	
	@ManyToMany(mappedBy="grupos", fetch=FetchType.LAZY)
	private List<ElementoDespesa> despesas;

	public int getCodGrupoDespesa() {
		return codGrupoDespesa;
	}

	public void setCodGrupoDespesa(int codGrupoDespesa) {
		this.codGrupoDespesa = codGrupoDespesa;
	}

	public String getNomeGrupoDespesa() {
		return nomeGrupoDespesa;
	}

	public void setNomeGrupoDespesa(String nomeGrupoDespesa) {
		this.nomeGrupoDespesa = nomeGrupoDespesa;
	}

	public List<ElementoDespesa> getDespesas() {
		return despesas;
	}

	public void setDespesas(List<ElementoDespesa> despesas) {
		this.despesas = despesas;
	}
	
	
}
