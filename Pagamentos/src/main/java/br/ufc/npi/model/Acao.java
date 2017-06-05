package br.ufc.npi.model;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;

@Entity(name="acao")
public class Acao {
	
	@Id
	@Column(nullable=false)
	private int id_acao;
	
	private int cod_acao; 
	private String nome_acao;
	private String liguagem_cidada; 
	
	@OneToMany(mappedBy="pagamentos",
			   targetEntity=Acao.class)
	List<Pagamento> pagamentos; 

	@ManyToMany
	@JoinTable(name="acao_unidade_gestora",
		joinColumns=@JoinColumn(name="id_acao",
								referencedColumnName="id_acao"),
		inverseJoinColumns=@JoinColumn(name="cod_unidade_gestora",
									   referencedColumnName="cod_unidade_gestora")
			  )
	private List<UnidadeGestora> unidades;
	
	@ManyToMany
	@JoinTable(name="acao_programa",
		joinColumns=@JoinColumn(name="id_acao",
								referencedColumnName="id_acao"),
		inverseJoinColumns=@JoinColumn(name="cod_programa",
									   referencedColumnName="cod_programa")
			  )
	private List<Programa> programas;

	
	public int getId_acao() {
		return id_acao;
	}
	public void setId_acao(int id_acao) {
		this.id_acao = id_acao;
	}
	public int getCod_acao() {
		return cod_acao;
	}
	public void setCod_acao(int cod_acao) {
		this.cod_acao = cod_acao;
	}
	public String getNome_acao() {
		return nome_acao;
	}
	public void setNome_acao(String nome_acao) {
		this.nome_acao = nome_acao;
	}
	public String getLiguagem_cidada() {
		return liguagem_cidada;
	}
	public void setLiguagem_cidada(String liguagem_cidada) {
		this.liguagem_cidada = liguagem_cidada;
	} 

}
