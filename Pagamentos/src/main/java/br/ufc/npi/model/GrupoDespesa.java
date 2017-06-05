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
	@Column(nullable=false)
	private int cod_grupo_despesa;
	private String nome_grupo_despesa;
	
	@ManyToMany(mappedBy="grupos", fetch=FetchType.LAZY)
	private List<ElementoDespesa> despesas;
	
	public int getCod_grupo_despesa() {
		return cod_grupo_despesa;
	}
	public void setCod_grupo_despesa(int cod_grupo_despesa) {
		this.cod_grupo_despesa = cod_grupo_despesa;
	}
	public String getNome_grupo_despesa() {
		return nome_grupo_despesa;
	}
	public void setNome_grupo_despesa(String nome_grupo_despesa) {
		this.nome_grupo_despesa = nome_grupo_despesa;
	}

}
