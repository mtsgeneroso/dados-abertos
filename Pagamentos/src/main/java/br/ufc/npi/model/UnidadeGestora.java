package br.ufc.npi.model;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;

@Entity(name="unidade_gestora")
public class UnidadeGestora {
	
	@Id
	@Column(nullable=false, name="cod_unidade_gestora")	
	private int codUnidadeGestora; 
	@Column(name="nome_unidade_gestora")
	private String nomeUnidadeGestora;
	@Column(name="cod_gestao_pagamento")
	private int codGestaoPagamento;
	
	@Column(insertable=false, 
			updatable=false,
			nullable=false, 
			name="cod_orgao_subordinado")
	private int codOrgaoSubordinado;
	
	@ManyToOne(optional=false)
	@JoinColumn(name="cod_orgao_subordinado", 
	referencedColumnName="cod_orgao_subordinado")
	private OrgaoSubordinado orgaoSubordinado;
	
	@ManyToMany(mappedBy="unidades")
	private List<Acao> acoes;
	
	
	public OrgaoSubordinado getOrgaoSubordinado() {
		return orgaoSubordinado;
	}
	public void setOrgaoSubordinado(OrgaoSubordinado orgaoSubordinado) {
		this.orgaoSubordinado = orgaoSubordinado;
	}
	public List<Acao> getAcoes() {
		return acoes;
	}
	
	public int getCodUnidadeGestora() {
		return codUnidadeGestora;
	}
	public void setCodUnidadeGestora(int codUnidadeGestora) {
		this.codUnidadeGestora = codUnidadeGestora;
	}
	public String getNomeUnidadeGestora() {
		return nomeUnidadeGestora;
	}
	public void setNomeUnidadeGestora(String nomeUnidadeGestora) {
		this.nomeUnidadeGestora = nomeUnidadeGestora;
	}
	public int getCodGestaoPagamento() {
		return codGestaoPagamento;
	}
	public void setCodGestaoPagamento(int codGestaoPagamento) {
		this.codGestaoPagamento = codGestaoPagamento;
	}
	public int getCodOrgaoSubordinado() {
		return codOrgaoSubordinado;
	}
	public void setCodOrgaoSubordinado(int codOrgaoSubordinado) {
		this.codOrgaoSubordinado = codOrgaoSubordinado;
	}
	public void setAcoes(List<Acao> acoes) {
		this.acoes = acoes;
	}

}
