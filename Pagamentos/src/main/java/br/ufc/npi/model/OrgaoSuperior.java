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
	private Long codOrgaoSuperior;
	
	@OneToMany(mappedBy="orgaosSubordinados",
			   targetEntity=OrgaoSuperior.class)
	private List<OrgaoSubordinado> orgaosSubordinados;
	
	@OneToMany(mappedBy="pagamentos", 
			targetEntity=OrgaoSuperior.class)
	private List<Pagamento> pagamentos;

	public String getNomeOrgaoSuperior() {
		return nomeOrgaoSuperior;
	}

	public void setNomeOrgaoSuperior(String nomeOrgaoSuperior) {
		this.nomeOrgaoSuperior = nomeOrgaoSuperior;
	}

	public Long getCodOrgaoSuperior() {
		return codOrgaoSuperior;
	}

	public void setCodOrgaoSuperior(Long codOrgaoSuperior) {
		this.codOrgaoSuperior = codOrgaoSuperior;
	}

	public List<OrgaoSubordinado> getOrgaosSubordinados() {
		return orgaosSubordinados;
	}

	public void setOrgaosSubordinados(List<OrgaoSubordinado> orgaosSubordinados) {
		this.orgaosSubordinados = orgaosSubordinados;
	}

	public List<Pagamento> getPagamentos() {
		return pagamentos;
	}

	public void setPagamentos(List<Pagamento> pagamentos) {
		this.pagamentos = pagamentos;
	}
		
}
