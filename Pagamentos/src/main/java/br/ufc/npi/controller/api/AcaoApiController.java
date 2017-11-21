package br.ufc.npi.controller.api;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import br.ufc.npi.model.Acao;
import br.ufc.npi.model.api.OrgaoGovernamental;
import br.ufc.npi.model.ui.TipoOrgaoGovernamental;
import br.ufc.npi.service.AcaoService;

@RestController
@RequestMapping(path="/api/acao")
public class AcaoApiController implements IOrgaoApiControler{
	
	@Autowired
	private AcaoService acaoService;
	
	@RequestMapping(path="/", method=RequestMethod.GET)
	@Override
	public List<OrgaoGovernamental> list() {
		ArrayList<OrgaoGovernamental> orgaos = new ArrayList<OrgaoGovernamental>();
		List<Acao> acoes = acaoService.findAll();
		for(Acao a : acoes){
			orgaos.add(new OrgaoGovernamental(a.getNomeAcao(), TipoOrgaoGovernamental.ACAO.getNomeTabela(), a.getCodAcao()));
		}
		return orgaos;
	}

	@RequestMapping(path="/{codigo}", method=RequestMethod.GET)
	@Override
	public Object get(Long codigo) {
		// TODO Auto-generated method stub
		return null;
	}
	
	@RequestMapping(path="/find", method=RequestMethod.POST)
	@Override
	public List<OrgaoGovernamental> find(OrgaoGovernamental orgaoConsulta) {
		// TODO Auto-generated method stub
		return null;
	}

	
	
}
