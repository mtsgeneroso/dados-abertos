package br.ufc.npi.model;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToMany;

@Entity(name="programa")
public class Programa {
	
	@Id
	@Column(nullable=false)
	private int cod_programa; 
	private String nome_programa;
	
	@ManyToMany(mappedBy="programas")
	private List<Acao> acoes;
	
	public int getCod_programa() {
		return cod_programa;
	}
	public void setCod_programa(int cod_programa) {
		this.cod_programa = cod_programa;
	}
	public String getNome_programa() {
		return nome_programa;
	}
	public void setNome_programa(String nome_programa) {
		this.nome_programa = nome_programa;
	}
	
	
}
