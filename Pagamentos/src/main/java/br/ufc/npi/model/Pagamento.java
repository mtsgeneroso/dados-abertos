package br.ufc.npi.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity(name="pagamento")
public class Pagamento {
	
	@Id
	@Column(nullable=false)
	private int cod_pagamento; 
	private float valor; 
	
	@Column(insertable=false, 
			updatable=false,
			nullable=false)
	private int id_favorecido; 	
		
	@ManyToOne(optional=false)
	@JoinColumn(name="id_favorecido", 
	referencedColumnName="id_favorecido")
	Favorecido favorecido;

	@Column(insertable=false, 
			updatable=false,
			nullable=false)
	private int cod_subfuncao;
	
	@ManyToOne(optional=false)
	@JoinColumn(name="cod_subfuncao", 
	referencedColumnName="cod_subfuncao")
	Subfuncao subfuncao;
	
	@Column(insertable=false, 
			updatable=false,
			nullable=false)
	private int id_acao;
	
	@ManyToOne(optional=false)
	@JoinColumn(name="id_acao", 
	referencedColumnName="id_acao")
	private Acao acao;	

	@Column(insertable=false, 
			updatable=false,
			nullable=false)
	private int id_data;
	
	@ManyToOne(optional=false)
	@JoinColumn(name="id_data", 
	referencedColumnName="id_data")
	private Data data;
	
	@Column(insertable=false, 
			updatable=false,
			nullable=false)
	private int cod_elemento_despesa; 
	
	@ManyToOne(optional=false)
	@JoinColumn(name="cod_elemento_despesa", 
	referencedColumnName="cod_elemento_despesa")
	private ElementoDespesa despesa;

	
	public int getCod_pagamento() {
		return cod_pagamento;
	}
	public void setCod_pagamento(int cod_pagamento) {
		this.cod_pagamento = cod_pagamento;
	}
	public float getValor() {
		return valor;
	}
	public void setValor(float valor) {
		this.valor = valor;
	}
	public int getId_favorecido() {
		return id_favorecido;
	}
	public void setId_favorecido(int cod_favorecido) {
		this.id_favorecido = cod_favorecido;
	}	
	
	public int getCod_subfuncao() {
		return cod_subfuncao;
	}
	public void setCod_subfuncao(int cod_subfuncao) {
		this.cod_subfuncao = cod_subfuncao;
	}
	public int getId_acao() {
		return id_acao;
	}
	public void setId_acao(int id_acao) {
		this.id_acao = id_acao;
	}
	/*public int getCod_elemento_despesa() {
		return cod_elemento_despesa;
	}
	public void setCod_elemento_despesa(int cod_elemento_despesa) {
		this.cod_elemento_despesa = cod_elemento_despesa;
	}*/
	public int getId_data() {
		return id_data;
	}
	public void setId_data(int id_data) {
		this.id_data = id_data;
	} 
	
	
	
}
