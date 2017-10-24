package br.ufc.npi.model.request;

import java.util.List;

public class ConsultaHierarquicaObj {

	private List<String> hierarquia;
	private List<Dado> orgaosConsulta;
	
	public List<String> getHierarquia() {
		return hierarquia;
	}
	public void setHierarquia(List<String> hierarquia) {
		this.hierarquia = hierarquia;
	}
	public List<Dado> getOrgaosConsulta() {
		return orgaosConsulta;
	}
	public void setOrgaosConsulta(List<Dado> orgaosConsulta) {
		this.orgaosConsulta = orgaosConsulta;
	}
	
}
