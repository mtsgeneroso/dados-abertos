package br.ufc.npi.controller.api;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import br.ufc.npi.model.OrgaoSuperior;
import br.ufc.npi.model.UnidadeGestora;
import br.ufc.npi.model.api.MensagemJSON;
import br.ufc.npi.model.api.OrgaoGovernamental;
import br.ufc.npi.model.ui.TipoOrgaoGovernamental;
import br.ufc.npi.service.UnidadeGestoraService;

@RestController
@RequestMapping(path="/api/unidade_gestora")
public class UnidadeGestoraApiController implements IOrgaoApiControler{
	
	@Autowired
	private UnidadeGestoraService unidadeGestoraService;
	
	@RequestMapping(path="/", method=RequestMethod.GET)
	@Override
	public List<OrgaoGovernamental> list() {
		ArrayList<OrgaoGovernamental> orgaos = new ArrayList<OrgaoGovernamental>();
		List<UnidadeGestora> unidadeGestoras = unidadeGestoraService.findAll();
		for(UnidadeGestora u : unidadeGestoras){
			orgaos.add(new OrgaoGovernamental(u.getNomeUnidadeGestora(), TipoOrgaoGovernamental.UNIDADE_GESTORA.getNomeTabela(), String.valueOf(u.getCodUnidadeGestora())));
		}
		return orgaos;
	}

	@RequestMapping(path="/{codigo}", method=RequestMethod.GET)
	@Override
	public Object get(@PathVariable("codigo")Long codigo) {
		UnidadeGestora unidadeGestoa = unidadeGestoraService.getById(codigo);
		if(unidadeGestoa != null){
			OrgaoGovernamental orgao = new OrgaoGovernamental();
			orgao.setId(String.valueOf(codigo));
			orgao.setNome(unidadeGestoa.getNomeUnidadeGestora());
			orgao.setTipo(TipoOrgaoGovernamental.UNIDADE_GESTORA.getNomeTabela());
			return orgao;
		}else{
			return new MensagemJSON("Órgão não encontrado.");
		}
	}

	@RequestMapping(path="/find", method=RequestMethod.POST)
	@Override
	public List<OrgaoGovernamental> find(OrgaoGovernamental orgaoConsulta) {
		return null;
	}

}
