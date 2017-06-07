package br.ufc.npi.model;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity(name="orgao_superior")
public class OrgaoSuperior {

	@Column(name="nome_orgao_superior")
	private String nomeOrgaoSuperior; 
	@Id
	@Column(nullable=false, name="cod_orgao_superior")
	private int codOrgaoSuperior;
	
	@OneToMany(mappedBy="orgaos_subordinados",
			   targetEntity=OrgaoSuperior.class)
	private List<OrgaoSubordinado> orgaos_subordinados;
	
	public List<OrgaoSubordinado> getOrgaos_subordinados() {
		return orgaos_subordinados;
	}
	public void setOrgaos_subordinados(List<OrgaoSubordinado> orgaos_subordinados) {
		this.orgaos_subordinados = orgaos_subordinados;
	}
	public String getNomeOrgaoSuperior() {
		return nomeOrgaoSuperior;
	}
	public void setNomeOrgaoSuperior(String nomeOrgaoSuperior) {
		this.nomeOrgaoSuperior = nomeOrgaoSuperior;
	}
	public int getCodOrgaoSuperior() {
		return codOrgaoSuperior;
	}
	public void setCodOrgaoSuperior(int codOrgaoSuperior) {
		this.codOrgaoSuperior = codOrgaoSuperior;
	}
	
	
}
