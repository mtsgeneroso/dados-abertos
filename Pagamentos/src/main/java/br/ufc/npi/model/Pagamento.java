package br.ufc.npi.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity(name="pagamento")
public class Pagamento {

	@Id
	@Column(nullable=false, name="cod_pagamento")
	private int codPagamento; 
	private float valor; 
	
	@Column(insertable=false, 
			updatable=false,
			nullable=false, 
			name="id_favorecido")
	private int idFavorecido; 	
		
	@ManyToOne(optional=false)
	@JoinColumn(name="id_favorecido", 
	referencedColumnName="id_favorecido")
	private Favorecido favorecido;

	@Column(insertable=false, 
			updatable=false,
			nullable=false, 
			name = "cod_subfuncao")
	private int codSubfuncao;
	
	@ManyToOne(optional=false)
	@JoinColumn(name="cod_subfuncao", 
	referencedColumnName="cod_subfuncao")
	Subfuncao subfuncao;
	
	@Column(insertable=false, 
			updatable=false,
			nullable=false, 
			name="id_acao")
	private int idAcao;
	
	@ManyToOne(optional=false)
	@JoinColumn(name="id_acao", 
	referencedColumnName="id_acao")
	private Acao acao;	

	@Column(insertable=false, 
			updatable=false,
			nullable=false, 
			name="id_data")
	private int idData;
	
	@ManyToOne(optional=false)
	@JoinColumn(name="id_data", 
	referencedColumnName="id_data")
	private Data data;
	
	@Column(insertable=false, 
			updatable=false,
			nullable=false, 
			name = "cod_elemento_despesa")
	private int codElementoDespesa; 
	
	@ManyToOne(optional=false)
	@JoinColumn(name="cod_elemento_despesa", 
	referencedColumnName="cod_elemento_despesa")
	private ElementoDespesa despesa;

	
	public Favorecido getFavorecido() {
		return favorecido;
	}
	public void setFavorecido(Favorecido favorecido) {
		this.favorecido = favorecido;
	}
	public Subfuncao getSubfuncao() {
		return subfuncao;
	}
	public void setSubfuncao(Subfuncao subfuncao) {
		this.subfuncao = subfuncao;
	}
	public Acao getAcao() {
		return acao;
	}
	public void setAcao(Acao acao) {
		this.acao = acao;
	}
	public Data getData() {
		return data;
	}
	public void setData(Data data) {
		this.data = data;
	}
	
	public float getValor() {
		return valor;
	}
	public void setValor(float valor) {
		this.valor = valor;
	}
	public int getCodPagamento() {
		return codPagamento;
	}
	public void setCodPagamento(int codPagamento) {
		this.codPagamento = codPagamento;
	}
	public int getIdFavorecido() {
		return idFavorecido;
	}
	public void setIdFavorecido(int idFavorecido) {
		this.idFavorecido = idFavorecido;
	}
	public int getCodSubfuncao() {
		return codSubfuncao;
	}
	public void setCodSubfuncao(int codSubfuncao) {
		this.codSubfuncao = codSubfuncao;
	}
	public int getIdAcao() {
		return idAcao;
	}
	public void setIdAcao(int idAcao) {
		this.idAcao = idAcao;
	}
	public int getIdData() {
		return idData;
	}
	public void setIdData(int idData) {
		this.idData = idData;
	}
	public int getCodElementoDespesa() {
		return codElementoDespesa;
	}
	public void setCodElementoDespesa(int codElementoDespesa) {
		this.codElementoDespesa = codElementoDespesa;
	}
	public ElementoDespesa getDespesa() {
		return despesa;
	}
	public void setDespesa(ElementoDespesa despesa) {
		this.despesa = despesa;
	}
		
}
