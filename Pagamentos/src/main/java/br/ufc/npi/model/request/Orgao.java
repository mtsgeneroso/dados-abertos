package br.ufc.npi.model.request;

public class Orgao {
	
	public static final String UNIDADE_GESTORA = "unidadeGestora";
	public static final String ORGAO_SUPERIOR = "orgaoSuperior";
	public static final String ORGAO_SUBORDINADO = "orgaoSubordinado";
	
	private String nome;
	private String tipo;
	private Long id;
	
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
