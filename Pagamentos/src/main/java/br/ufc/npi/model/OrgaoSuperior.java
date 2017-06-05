package br.ufc.npi.model;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity(name="orgao_superior")
public class OrgaoSuperior {
	
	private String nome_orgao_superior; 
	@Id
	@Column(nullable=false)
	private int cod_orgao_superior;
	
	@OneToMany(mappedBy="orgaos_subordinados",
			   targetEntity=OrgaoSuperior.class)
	List<OrgaoSubordinado> orgaos_subordinados;
	
	public List<OrgaoSubordinado> getOrgaos_subordinados() {
		return orgaos_subordinados;
	}
	public void setOrgaos_subordinados(List<OrgaoSubordinado> orgaos_subordinados) {
		this.orgaos_subordinados = orgaos_subordinados;
	}
	public String getNome_orgao_superior() {
		return nome_orgao_superior;
	}
	public void setNome_orgao_superior(String nome_orgao_superior) {
		this.nome_orgao_superior = nome_orgao_superior;
	}
	public int getCod_orgao_superior() {
		return cod_orgao_superior;
	}
	public void setCod_orgao_superior(int cod_orgao_superior) {
		this.cod_orgao_superior = cod_orgao_superior;
	}
	
	

}
