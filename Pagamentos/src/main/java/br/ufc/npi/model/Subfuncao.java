package br.ufc.npi.model;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;

@Entity(name="subfuncao")
public class Subfuncao {

	@Column(name="nome_subfuncao")
	private String nomeSubfuncao; 
	@Id
	@Column(nullable=false, name="cod_subfuncao")
	private int codSufuncao;
	
	@OneToMany(mappedBy="pagamentos",
			   targetEntity=Subfuncao.class)
	private List<Pagamento> pagamentos;
	
	public List<Pagamento> getPagamentos() {
		return pagamentos;
	}
	public void setPagamentos(List<Pagamento> pagamentos) {
		this.pagamentos = pagamentos;
	}
	public List<Funcao> getFuncoes() {
		return funcoes;
	}
	public void setFuncoes(List<Funcao> funcoes) {
		this.funcoes = funcoes;
	}
	@ManyToMany(mappedBy="subfuncoes")
	private List<Funcao> funcoes;

	public String getNomeSubfuncao() {
		return nomeSubfuncao;
	}
	public void setNomeSubfuncao(String nomeSubfuncao) {
		this.nomeSubfuncao = nomeSubfuncao;
	}
	public int getCodSufuncao() {
		return codSufuncao;
	}
	public void setCodSufuncao(int codSufuncao) {
		this.codSufuncao = codSufuncao;
	}
	
	
	
}
