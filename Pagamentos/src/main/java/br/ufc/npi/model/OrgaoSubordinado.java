package br.ufc.npi.model;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

@Entity(name="orgao_subordinado")
public class OrgaoSubordinado {
	
	@Id
	@Column(nullable=false, name="cod_orgao_subordinado")
	private Long codOrgaoSubordinado;
	
	@Column(name="nome_orgao_subordinado")
	private String nomeOrgaoSubordinado;

	@Column(insertable=false, 
			updatable=false,
			nullable=false, 
			name="cod_orgao_superior")
	private Long codOrgaoSuperior;
	
	@ManyToOne(optional=false)
	@JoinColumn(name="cod_orgao_superior", 
	referencedColumnName="cod_orgao_Superior")
	private OrgaoSuperior orgaoSuperior;
	
	@OneToMany(mappedBy="unidadesGestoras",
			   targetEntity=OrgaoSubordinado.class)
	private List<UnidadeGestora> unidadesGestoras;
	
	@OneToMany(mappedBy="pagamentos",
			targetEntity=OrgaoSubordinado.class)
	private List<Pagamento> pagamentos;

	public Long getCodOrgaoSubordinado() {
		return codOrgaoSubordinado;
	}

	public void setCodOrgaoSubordinado(Long codOrgaoSubordinado) {
		this.codOrgaoSubordinado = codOrgaoSubordinado;
	}

	public String getNomeOrgaoSubordinado() {
		return nomeOrgaoSubordinado;
	}

	public void setNomeOrgaoSubordinado(String nomeOrgaoSubordinado) {
		this.nomeOrgaoSubordinado = nomeOrgaoSubordinado;
	}

	public Long getCodOrgaoSuperior() {
		return codOrgaoSuperior;
	}

	public void setCodOrgaoSuperior(Long codOrgaoSuperior) {
		this.codOrgaoSuperior = codOrgaoSuperior;
	}

	public OrgaoSuperior getOrgaoSuperior() {
		return orgaoSuperior;
	}

	public void setOrgaoSuperior(OrgaoSuperior orgaoSuperior) {
		this.orgaoSuperior = orgaoSuperior;
	}

	public List<UnidadeGestora> getUnidadesGestoras() {
		return unidadesGestoras;
	}

	public void setUnidadesGestoras(List<UnidadeGestora> unidadesGestoras) {
		this.unidadesGestoras = unidadesGestoras;
	}

	public List<Pagamento> getPagamentos() {
		return pagamentos;
	}

	public void setPagamentos(List<Pagamento> pagamentos) {
		this.pagamentos = pagamentos;
	}
	
}
