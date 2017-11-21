package br.ufc.npi.model.api;

import java.util.List;

import br.ufc.npi.model.ui.TipoOrgaoGovernamental;

public class OrgaoGovernamental {
	
	private String nome;
	private String tipo;
	private String id;
	private double valorPagamentos;
	
	public OrgaoGovernamental(){
		this.id = "0";
		this.nome = "";
		this.tipo = "";
	}
	
	public OrgaoGovernamental(String nome, String tipo, String id) {
		super();
		this.nome = nome;
		this.tipo = tipo;
		this.id = id;
	}
	
	public OrgaoGovernamental(String nome, String tipo, String id, List<OrgaoGovernamental> subordinados, double valorPagamentos) {
		super();
		this.nome = nome;
		this.tipo = tipo;
		this.id = id;
		this.valorPagamentos = valorPagamentos;
	}

	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getTipo() {
		return tipo;
	}
	public void setTipo(String tipo) {
		this.tipo = tipo;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}

	public double getValorPagamentos() {
		return valorPagamentos;
	}

	public void setValorPagamentos(double valorPagamentos) {
		this.valorPagamentos = valorPagamentos;
	}
	
}
