package br.ufc.npi.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity(name="pagamento")
public class Pagamento {

	@Id
	@Column(nullable=false, name="cod_pagamento")
	private Long codPagamento; 
	
	private float valor; 
	
	@Column(name="numero_documento")
	private String numeroDocumento;
	
	@ManyToOne(optional=false, fetch=FetchType.EAGER)
	@JoinColumn(name="cod_orgao_superior",
	referencedColumnName="cod_orgao_superior")
	private OrgaoSuperior orgaoSuperior;
	
	@Column(insertable=false, 
			updatable=false,
			nullable=false, 
			name = "cod_orgao_superior")
	private Long codOrgaoSuperior;
	
	@ManyToOne(optional=false, fetch=FetchType.EAGER)
	@JoinColumn(name="cod_orgao_subordinado",
	referencedColumnName="cod_orgao_subordinado")
	private OrgaoSubordinado orgaoSubordinado;
	
	@Column(insertable=false, 
			updatable=false,
			nullable=false, 
			name = "cod_orgao_subordinado")
	private Long codOrgaoSubordinado;
	
	@ManyToOne(optional=false, fetch=FetchType.EAGER)
	@JoinColumn(name="cod_unidade_gestora",
	referencedColumnName="cod_unidade_gestora")
	private UnidadeGestora unidadeGestora;
	
	@Column(insertable=false, 
			updatable=false,
			nullable=false, 
			name = "cod_unidade_gestora")
	private Long codUnidadeGestora;
	
	@ManyToOne(optional=false, fetch=FetchType.EAGER)
	@JoinColumn(name="cod_acao",
	referencedColumnName="cod_acao")
	private Acao acao;
	
	@Column(insertable=false, 
			updatable=false,
			nullable=false, 
			name="cod_acao")
	private String codAcao;
	
	@ManyToOne(optional=false, fetch=FetchType.EAGER)
	@JoinColumn(name="cod_programa",
	referencedColumnName="cod_programa")
	private Programa programa;
	
	@Column(insertable=false, 
			updatable=false,
			nullable=false, 
			name = "cod_programa")
	private Long codPrograma;
	
	@ManyToOne(optional=false, fetch=FetchType.EAGER)
	@JoinColumn(name="cod_funcao",
	referencedColumnName="cod_funcao")
	private Funcao funcao;
	
	@Column(insertable=false, 
			updatable=false,
			nullable=false, 
			name = "cod_funcao")
	private Long codFuncao;
	
	@ManyToOne(optional=false, fetch=FetchType.EAGER)
	@JoinColumn(name="cod_subfuncao", 
	referencedColumnName="cod_subfuncao")
	private Subfuncao subfuncao;
	
	@Column(insertable=false, 
			updatable=false,
			nullable=false, 
			name = "cod_subfuncao")
	private Long codSubfuncao;
	
	@ManyToOne(optional=false, fetch=FetchType.EAGER)
	@JoinColumn(name="cod_favorecido", 
	referencedColumnName="cod_favorecido")
	private Favorecido favorecido;
	
	@Column(insertable=false, 
			updatable=false,
			nullable=false, 
			name="cod_favorecido")
	private String codFavorecido; 
	
	@ManyToOne(optional=false, fetch=FetchType.EAGER)
	@JoinColumn(name="cod_data", 
	referencedColumnName="cod_data")
	private Data data;
	
	@Column(insertable=false, 
			updatable=false,
			nullable=false, 
			name="cod_data")
	private Long codData;
	
	@ManyToOne(optional=false, fetch=FetchType.EAGER)
	@JoinColumn(name="cod_elemento_despesa", 
	referencedColumnName="cod_elemento_despesa")
	private ElementoDespesa elementoDespesa;
	
	@Column(insertable=false, 
			updatable=false,
			nullable=false, 
			name = "cod_elemento_despesa")
	private Long codElementoDespesa; 
	
	@ManyToOne(optional=false, fetch=FetchType.EAGER)
	@JoinColumn(name="cod_grupo_despesa",
	referencedColumnName="cod_grupo_despesa")
	private GrupoDespesa grupoDespesa;
	
	@Column(insertable=false, 
			updatable=false,
			nullable=false, 
			name = "cod_grupo_despesa")
	private Long codGrupoDespesa;

	public Long getCodPagamento() {
		return codPagamento;
	}

	public void setCodPagamento(Long codPagamento) {
		this.codPagamento = codPagamento;
	}

	public float getValor() {
		return valor;
	}

	public void setValor(float valor) {
		this.valor = valor;
	}

	public String getNumeroDocumento() {
		return numeroDocumento;
	}

	public void setNumeroDocumento(String numeroDocumento) {
		this.numeroDocumento = numeroDocumento;
	}

	public OrgaoSuperior getOrgaoSuperior() {
		return orgaoSuperior;
	}

	public void setOrgaoSuperior(OrgaoSuperior orgaoSuperior) {
		this.orgaoSuperior = orgaoSuperior;
	}

	public Long getCodOrgaoSuperior() {
		return codOrgaoSuperior;
	}

	public void setCodOrgaoSuperior(Long codOrgaoSuperior) {
		this.codOrgaoSuperior = codOrgaoSuperior;
	}

	public OrgaoSubordinado getOrgaoSubordinado() {
		return orgaoSubordinado;
	}

	public void setOrgaoSubordinado(OrgaoSubordinado orgaoSubordinado) {
		this.orgaoSubordinado = orgaoSubordinado;
	}

	public Long getCodOrgaoSubordinado() {
		return codOrgaoSubordinado;
	}

	public void setCodOrgaoSubordinado(Long codOrgaoSubordinado) {
		this.codOrgaoSubordinado = codOrgaoSubordinado;
	}

	public UnidadeGestora getUnidadeGestora() {
		return unidadeGestora;
	}

	public void setUnidadeGestora(UnidadeGestora unidadeGestora) {
		this.unidadeGestora = unidadeGestora;
	}

	public Long getCodUnidadeGestora() {
		return codUnidadeGestora;
	}

	public void setCodUnidadeGestora(Long codUnidadeGestora) {
		this.codUnidadeGestora = codUnidadeGestora;
	}

	public Acao getAcao() {
		return acao;
	}

	public void setAcao(Acao acao) {
		this.acao = acao;
	}

	public String getCodAcao() {
		return codAcao;
	}

	public void setCodAcao(String codAcao) {
		this.codAcao = codAcao;
	}

	public Programa getPrograma() {
		return programa;
	}

	public void setPrograma(Programa programa) {
		this.programa = programa;
	}

	public Long getCodPrograma() {
		return codPrograma;
	}

	public void setCodPrograma(Long codPrograma) {
		this.codPrograma = codPrograma;
	}

	public Funcao getFuncao() {
		return funcao;
	}

	public void setFuncao(Funcao funcao) {
		this.funcao = funcao;
	}

	public Long getCodFuncao() {
		return codFuncao;
	}

	public void setCodFuncao(Long codFuncao) {
		this.codFuncao = codFuncao;
	}

	public Subfuncao getSubfuncao() {
		return subfuncao;
	}

	public void setSubfuncao(Subfuncao subfuncao) {
		this.subfuncao = subfuncao;
	}

	public Long getCodSubfuncao() {
		return codSubfuncao;
	}

	public void setCodSubfuncao(Long codSubfuncao) {
		this.codSubfuncao = codSubfuncao;
	}

	public Favorecido getFavorecido() {
		return favorecido;
	}

	public void setFavorecido(Favorecido favorecido) {
		this.favorecido = favorecido;
	}

	public String getCodFavorecido() {
		return codFavorecido;
	}

	public void setCodFavorecido(String codFavorecido) {
		this.codFavorecido = codFavorecido;
	}

	public Data getData() {
		return data;
	}

	public void setData(Data data) {
		this.data = data;
	}

	public Long getCodData() {
		return codData;
	}

	public void setCodData(Long codData) {
		this.codData = codData;
	}

	public ElementoDespesa getElementoDespesa() {
		return elementoDespesa;
	}

	public void setElementoDespesa(ElementoDespesa elementoDespesa) {
		this.elementoDespesa = elementoDespesa;
	}

	public Long getCodElementoDespesa() {
		return codElementoDespesa;
	}

	public void setCodElementoDespesa(Long codElementoDespesa) {
		this.codElementoDespesa = codElementoDespesa;
	}

	public GrupoDespesa getGrupoDespesa() {
		return grupoDespesa;
	}

	public void setGrupoDespesa(GrupoDespesa grupoDespesa) {
		this.grupoDespesa = grupoDespesa;
	}

	public Long getCodGrupoDespesa() {
		return codGrupoDespesa;
	}

	public void setCodGrupoDespesa(Long codGrupoDespesa) {
		this.codGrupoDespesa = codGrupoDespesa;
	}
	
}
