package br.ufc.npi.controller.api;

import java.util.List;

import br.ufc.npi.model.api.Chart;
import br.ufc.npi.model.api.OrgaoGovernamental;

public interface IOrgaoApiControler<T> {

	public List<OrgaoGovernamental> list();
	public OrgaoGovernamental get(T codigo);
	public List<OrgaoGovernamental> find(OrgaoGovernamental orgaoConsulta);
	
}
