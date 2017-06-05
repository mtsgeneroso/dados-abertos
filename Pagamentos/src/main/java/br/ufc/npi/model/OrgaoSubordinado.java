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
	@Column(nullable=false)
	private int cod_orgao_subordinado; 
	private String nome_orgao_subordinado;

	@Column(insertable=false, 
			updatable=false,
			nullable=false)
	private int cod_orgao_superior;
	
	@ManyToOne(optional=false)
	@JoinColumn(name="cod_orgao_superior", 
	referencedColumnName="cod_orgao_superior")
	OrgaoSuperior orgao_superior;
	
	@OneToMany(mappedBy="unidades_gestoras",
			   targetEntity=OrgaoSubordinado.class)
	List<UnidadeGestora> unidades_gestoras;
	
	public int getCod_orgao_superior() {
		return cod_orgao_superior;
	}
	public void setCod_orgao_superior(int cod_orgao_superior) {
		this.cod_orgao_superior = cod_orgao_superior;
	}
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
	public int getCod_orgao_subordinado() {
		return cod_orgao_subordinado;
	}
	public void setCod_orgao_subordinado(int cod_orgao_subordinado) {
		this.cod_orgao_subordinado = cod_orgao_subordinado;
	}
	public String getNome_orgao_subordinado() {
		return nome_orgao_subordinado;
	}
	public void setNome_orgao_subordinado(String nome_orgao_subordinado) {
		this.nome_orgao_subordinado = nome_orgao_subordinado;
	}
	

}
