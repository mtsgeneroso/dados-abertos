package br.ufc.npi.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="funcao")
public class Funcao {

	@Id
	@Column(name="cod_atividade")
	private int codAtividade;
	
	@Column(name="sigla")
	private String sigla;
	
	@Column(name="nivel_funcao")
	private int nivelFuncao;
	
	@Column(name="funcao")
	private String funcao;
	
	@Column(name="opcao_parcial")
	private String opcaoParcial;
}
