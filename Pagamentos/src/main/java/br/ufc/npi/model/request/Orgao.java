package br.ufc.npi.model.request;

import java.util.List;

public class Orgao {
	
	public static final String UNIDADE_GESTORA = "unidadeGestora";
	public static final String ORGAO_SUPERIOR = "orgaoSuperior";
	public static final String ORGAO_SUBORDINADO = "orgaoSubordinado";
	
	private String nome;
	private String tipo;
	private Long id;
	private List<Orgao> subordinados;
	private double valorPagamentos;
	
	public Orgao(){
		this.id = 0L;
		this.nome = "";
		this.tipo = "";
	}
	
	public Orgao(String nome, String tipo, Long id) {
		super();
		this.nome = nome;
		this.tipo = tipo;
		this.id = id;
	}
	
	
	
	public Orgao(String nome, String tipo, Long id, List<Orgao> subordinados, double valorPagamentos) {
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
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}

	public List<Orgao> getSubordinados() {
		return subordinados;
	}

	public void setSubordinados(List<Orgao> subordinados) {
		this.subordinados = subordinados;
	}

	public double getValorPagamentos() {
		if(subordinados == null || subordinados.size() == 0){
			return valorPagamentos;
		}
		else{
			double soma = 0;
			for(Orgao orgSub : subordinados){
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
