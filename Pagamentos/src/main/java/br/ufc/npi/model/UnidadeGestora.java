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
	@Column(nullable=false)	
	private int cod_unidade_gestora; 
	private String nome_unidade_gestora; 
	private int cod_gestao_pagamento;
	
	@Column(insertable=false, 
			updatable=false,
			nullable=false)
	private int cod_orgao_subordinado;
	
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
	public int getCod_unidade_gestora() {
		return cod_unidade_gestora;
	}
	public void setCod_unidade_gestora(int cod_unidade_gestora) {
		this.cod_unidade_gestora = cod_unidade_gestora;
	}
	public String getNome_unidade_gestora() {
		return nome_unidade_gestora;
	}
	public void setNome_unidade_gestora(String nome_unidade_gestora) {
		this.nome_unidade_gestora = nome_unidade_gestora;
	}
	public int getCod_gestao_pagamento() {
		return cod_gestao_pagamento;
	}
	public void setCod_gestao_pagamento(int cod_gestao_pagamento) {
		this.cod_gestao_pagamento = cod_gestao_pagamento;
	}
	public int getCod_orgao_subordinado() {
		return cod_orgao_subordinado;
	}
	public void setCod_orgao_subordinado(int cod_orgao_subordinado) {
		this.cod_orgao_subordinado = cod_orgao_subordinado;
	}

	
}
