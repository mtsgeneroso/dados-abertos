package br.ufc.npi.model;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

@Entity(name="unidade_gestora")
public class UnidadeGestora {
	
	@Id
	@Column(nullable=false, name="cod_unidade_gestora")	
	private Long codUnidadeGestora; 
	@Column(name="nome_unidade_gestora")
	private String nomeUnidadeGestora;
	@Column(name="cod_gestao_pagamento")
	private int codGestaoPagamento;
	
	@Column(insertable=false, 
			updatable=false,
			nullable=false, 
			name="cod_orgao_subordinado")
	private Long codOrgaoSubordinado;
	
	@ManyToOne(optional=false)
	@JoinColumn(name="cod_orgao_subordinado", 
	referencedColumnName="cod_orgao_subordinado")
	private OrgaoSubordinado orgaoSubordinado;
	
	@ManyToMany(mappedBy="unidades")
	private List<Acao> acoes;
	
	@OneToMany(mappedBy="pagamentos",
			targetEntity=UnidadeGestora.class)
	private List<Pagamento> pagamentos;

	public Long getCodUnidadeGestora() {
		return codUnidadeGestora;
	}

	public void setCodUnidadeGestora(Long codUnidadeGestora) {
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

	public Long getCodOrgaoSubordinado() {
		return codOrgaoSubordinado;
	}

	public void setCodOrgaoSubordinado(Long codOrgaoSubordinado) {
		this.codOrgaoSubordinado = codOrgaoSubordinado;
	}

	public OrgaoSubordinado getOrgaoSubordinado() {
		return orgaoSubordinado;
	}

	public void setOrgaoSubordinado(OrgaoSubordinado orgaoSubordinado) {
		this.orgaoSubordinado = orgaoSubordinado;
	}

	public List<Acao> getAcoes() {
		return acoes;
	}

	public void setAcoes(List<Acao> acoes) {
		this.acoes = acoes;
	}

	public List<Pagamento> getPagamentos() {
		return pagamentos;
	}

	public void setPagamentos(List<Pagamento> pagamentos) {
		this.pagamentos = pagamentos;
	}
	
}
