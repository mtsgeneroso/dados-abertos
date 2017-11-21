package br.ufc.npi.model.ui;

public enum TipoOrgaoGovernamental {
	
	ORGAO_SUPERIOR("Órgão Superior", "orgao_superior"),
	ORGAO_SUBORDINADO("Órgão Subordinado", "orgao_subordinado"),
	UNIDADE_GESTORA("Unidade Gestora", "unidade_gestora"),
	ACAO("Ação", "acao"),
	PROGRAMA("Programa", "programa"),
	FAVORECIDO("Favorecido", "favorecido");
	
	private String nome;
	private String nomeTabela;
	
	TipoOrgaoGovernamental(String nome, String nomeTabela){
		this.nome = nome;
		this.nomeTabela = nomeTabela;
	}
	
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getNomeTabela() {
		return nomeTabela;
	}
	public void setNomeTabela(String nomeTabela) {
		this.nomeTabela = nomeTabela;
	}
	
}
