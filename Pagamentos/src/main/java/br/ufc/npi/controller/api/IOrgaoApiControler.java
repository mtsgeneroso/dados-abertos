package br.ufc.npi.controller.api;

import java.util.List;

import br.ufc.npi.model.api.OrgaoGovernamental;

public interface IOrgaoApiControler {

	public List<OrgaoGovernamental> list();
	public Object get(Long codigo);
	public List<OrgaoGovernamental> find(OrgaoGovernamental orgaoConsulta);
	
}
