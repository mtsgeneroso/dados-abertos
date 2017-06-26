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
	private OrgaoSuperior orgao_superior;
	
	@OneToMany(mappedBy="unidades_gestoras",
			   targetEntity=OrgaoSubordinado.class)
	private List<UnidadeGestora> unidades_gestoras;
	
	
	public OrgaoSuperior getOrgao_superior() {
		return orgao_superior;
	}
	public void setOrgao_superior(OrgaoSuperior orgao_superior) {
		this.orgao_superior = orgao_superior;
	}
	public List<UnidadeGestora> getUnidades_gestoras() {
		return unidades_gestoras;
	}
	public void setUnidades_gestoras(List<UnidadeGestora> unidades_gestoras) {
		this.unidades_gestoras = unidades_gestoras;
	}
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
	

}
