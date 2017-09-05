package br.ufc.npi.model.request;

import java.util.List;

import br.ufc.npi.model.Orgao;

public class RequestWrapper {

	private List<Orgao> orgaos;

	public List<Orgao> getOrgaos() {
		return orgaos;
	}

	public void setOrgaos(List<Orgao> orgaos) {
		this.orgaos = orgaos;
	}
	
}
