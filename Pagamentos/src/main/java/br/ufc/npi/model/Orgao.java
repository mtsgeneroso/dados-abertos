package br.ufc.npi.model;

public class Orgao {

	private String nome;
	private String tipo;
	private Long id;
	
	public Orgao(String nome, String tipo, Long id) {
		super();
		this.nome = nome;
		this.tipo = tipo;
		this.id = id;
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
	
	
	
}
