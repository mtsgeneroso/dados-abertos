package br.ufc.npi.model;

import java.util.List;

import javax.persistence.JoinColumn;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;

@Entity(name="funcao")
public class Funcao {

	@Column(name="nome_funcao")
	private String nomeFuncao; 
	@Id
	@Column(nullable=false, name="cod_funcao")
	private int codFuncao;
	
	@ManyToMany
	@JoinTable(name="funcao_subfuncao",
		joinColumns=@JoinColumn(name="cod_funcao",
								referencedColumnName="cod_funcao"),
		inverseJoinColumns=@JoinColumn(name="cod_subfuncao",
									   referencedColumnName="cod_subfuncao")
			  )
	private List<Subfuncao> subfuncoes;

	
	public List<Subfuncao> getSubfuncoes() {
		return subfuncoes;
	}
	public void setSubfuncoes(List<Subfuncao> subfuncoes) {
		this.subfuncoes = subfuncoes;
	}
	
	public String getNomeFuncao() {
		return nomeFuncao;
	}
	public void setNomeFuncao(String nomeFuncao) {
		this.nomeFuncao = nomeFuncao;
	}
	public int getCodFuncao() {
		return codFuncao;
	}
	public void setCodFuncao(int codFuncao) {
		this.codFuncao = codFuncao;
	}
	
}
