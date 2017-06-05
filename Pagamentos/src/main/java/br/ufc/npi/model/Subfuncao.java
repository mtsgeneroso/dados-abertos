package br.ufc.npi.model;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;

@Entity(name="subfuncao")
public class Subfuncao {
	
	private String nome_subfuncao; 
	@Id
	@Column(nullable=false)
	private int cod_subfuncao;
	
	@OneToMany(mappedBy="pagamentos",
			   targetEntity=Subfuncao.class)
	List<Pagamento> pagamentos;
	
	@ManyToMany(mappedBy="subfuncoes")
	private List<Funcao> funcoes;
	
	public String getNome_subfuncao() {
		return nome_subfuncao;
	}
	public void setNome_subfuncao(String nome_subfuncao) {
		this.nome_subfuncao = nome_subfuncao;
	}
	public int getCod_subfuncao() {
		return cod_subfuncao;
	}
	public void setCod_subfuncao(int cod_subfuncao) {
		this.cod_subfuncao = cod_subfuncao;
	}

}
