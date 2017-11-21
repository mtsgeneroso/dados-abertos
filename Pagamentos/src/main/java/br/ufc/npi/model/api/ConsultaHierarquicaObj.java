package br.ufc.npi.model.api;

import java.util.List;

public class ConsultaHierarquicaObj {

	private List<String> hierarquia;
	private List<OrgaoGovernamental> orgaosConsulta;
	
	public List<String> getHierarquia() {
		return hierarquia;
	}
	public void setHierarquia(List<String> hierarquia) {
		this.hierarquia = hierarquia;
	}
	public List<OrgaoGovernamental> getOrgaosConsulta() {
		return orgaosConsulta;
	}
	public void setOrgaosConsulta(List<OrgaoGovernamental> orgaosConsulta) {
		this.orgaosConsulta = orgaosConsulta;
	}
	
}
