package br.ufc.npi.model.request;

import java.util.List;

public class Dado {
	
	public static final String UNIDADE_GESTORA = "unidade_gestora";
	public static final String ORGAO_SUPERIOR = "orgao_superior";
	public static final String ORGAO_SUBORDINADO = "orgao_subordinado";
	public static final String PROGRAMA = "programa";
	public static final String ACAO = "acao";
	public static final String FAVORECIDO = "favorecido";
	
	private String nome;
	private String tipo;
	private String id;
	private List<Dado> subordinados;
	private double valorPagamentos;
	
	public Dado(){
		this.id = "0";
		this.nome = "";
		this.tipo = "";
	}
	
	public Dado(String nome, String tipo, String id) {
		super();
		this.nome = nome;
		this.tipo = tipo;
		this.id = id;
	}
	
	public Dado(String nome, String tipo, String id, List<Dado> subordinados, double valorPagamentos) {
		super();
		this.nome = nome;
		this.tipo = tipo;
		this.id = id;
		this.subordinados = subordinados;
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

	public List<Dado> getSubordinados() {
		return subordinados;
	}

	public void setSubordinados(List<Dado> subordinados) {
		this.subordinados = subordinados;
	}

	public double getValorPagamentos() {
		if(subordinados == null || subordinados.size() == 0){
			return valorPagamentos;
		}
		else{
			double soma = 0;
			for(Dado orgSub : subordinados){
				soma += orgSub.valorPagamentos;
			}
			this.valorPagamentos = soma;
			return soma;
		}
	}

	public void setValorPagamentos(double valorPagamentos) {
		this.valorPagamentos = valorPagamentos;
	}
	
}
