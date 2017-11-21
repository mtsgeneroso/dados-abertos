package br.ufc.npi.model.api;

public class MensagemJSON {

	private String mensagem;

	public MensagemJSON(String mensagem) {
		super();
		this.mensagem = mensagem;
	}
	
	public String getMensagem() {
		return mensagem;
	}

	public void setMensagem(String mensagem) {
		this.mensagem = mensagem;
	}	
	
}
