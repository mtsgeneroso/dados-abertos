package br.ufc.npi.model;

import java.util.List;
import javax.persistence.JoinColumn;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;

@Entity(name="acao")
public class Acao {
	
	@Id
	@Column(name="cod_acao")
	private String codAcao;
	@Column(name="nome_acao")
	private String nomeAcao;
	@Column(name = "linguagem_cidada")
	private String liguagemCidada; 
	
	@ManyToMany
	@JoinTable(name="acao_unidade_gestora",
		joinColumns=@JoinColumn(name="cod_acao",
								referencedColumnName="cod_acao"),
		inverseJoinColumns=@JoinColumn(name="cod_unidade_gestora",
									   referencedColumnName="cod_unidade_gestora")
			  )
	private List<UnidadeGestora> unidades;
	
	@ManyToMany
	@JoinTable(name="acao_programa",
		joinColumns=@JoinColumn(name="cod_acao",
								referencedColumnName="cod_acao"),
		inverseJoinColumns=@JoinColumn(name="cod_programa",
									   referencedColumnName="cod_programa")
			  )
	private List<Programa> programas;

	
	@OneToMany(mappedBy="pagamentos",
			targetEntity=Acao.class)
	private List<Pagamento> pagamentos;


	public String getCodAcao() {
		return codAcao;
	}


	public void setCodAcao(String codAcao) {
		this.codAcao = codAcao;
	}


	public String getNomeAcao() {
		return nomeAcao;
	}


	public void setNomeAcao(String nomeAcao) {
		this.nomeAcao = nomeAcao;
	}


	public String getLiguagemCidada() {
		return liguagemCidada;
	}


	public void setLiguagemCidada(String liguagemCidada) {
		this.liguagemCidada = liguagemCidada;
	}


	public List<UnidadeGestora> getUnidades() {
		return unidades;
	}


	public void setUnidades(List<UnidadeGestora> unidades) {
		this.unidades = unidades;
	}


	public List<Programa> getProgramas() {
		return programas;
	}


	public void setProgramas(List<Programa> programas) {
		this.programas = programas;
	}


	public List<Pagamento> getPagamentos() {
		return pagamentos;
	}


	public void setPagamentos(List<Pagamento> pagamentos) {
		this.pagamentos = pagamentos;
	}
	
	

}
