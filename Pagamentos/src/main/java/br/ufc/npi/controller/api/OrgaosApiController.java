package br.ufc.npi.controller.api;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import br.ufc.npi.model.OrgaoSubordinado;
import br.ufc.npi.model.OrgaoSuperior;
import br.ufc.npi.model.UnidadeGestora;
import br.ufc.npi.model.api.MensagemJSON;
import br.ufc.npi.model.api.OrgaoGovernamental;
import br.ufc.npi.model.ui.TipoOrgaoGovernamental;
import br.ufc.npi.service.OrgaoSubordinadoService;
import br.ufc.npi.service.OrgaoSuperiorService;
import br.ufc.npi.service.UnidadeGestoraService;

@RestController
@RequestMapping(path="/api/orgaos")
public class OrgaosApiController implements IOrgaoApiControler{
	
	@Autowired
	private OrgaoSuperiorService orgaoSuperiorService;
	@Autowired
	private OrgaoSubordinadoService orgaoSubordinadoService;
	@Autowired
	private UnidadeGestoraService unidadeGestoraService;
	
	@RequestMapping(path="/", method=RequestMethod.GET)
	@Override
	public List<OrgaoGovernamental> list() {
		List<OrgaoSuperior> orgaosSuperiores = orgaoSuperiorService.findAll();
		List<OrgaoSubordinado> orgaosSubordinados = orgaoSubordinadoService.findAll();
		List<UnidadeGestora> unidadesGestoras = unidadeGestoraService.findAll();
		ArrayList<OrgaoGovernamental> orgaos = new ArrayList<OrgaoGovernamental>();
		for(OrgaoSuperior os : orgaosSuperiores){
			orgaos.add(new OrgaoGovernamental(os.getNomeOrgaoSuperior(), TipoOrgaoGovernamental.ORGAO_SUPERIOR.getNomeTabela(), String.valueOf(os.getCodOrgaoSuperior())));
		}
		for(OrgaoSubordinado os : orgaosSubordinados){
			orgaos.add(new OrgaoGovernamental(os.getNomeOrgaoSubordinado(), TipoOrgaoGovernamental.ORGAO_SUBORDINADO.getNomeTabela(), String.valueOf(os.getCodOrgaoSubordinado())));
		}
		for(UnidadeGestora ug : unidadesGestoras){
			orgaos.add(new OrgaoGovernamental(ug.getNomeUnidadeGestora(), TipoOrgaoGovernamental.UNIDADE_GESTORA.getNomeTabela(), String.valueOf(ug.getCodUnidadeGestora())));
		}
		return orgaos;
	}

	@RequestMapping(path="/{codigo}", method=RequestMethod.GET)
	@Override
	public Object get(@PathVariable("codigo") Long codigo) {
		OrgaoSuperiorApiController orgaoSuperiorApi = new OrgaoSuperiorApiController();
		Object response = orgaoSuperiorApi.get(codigo);
		if(response instanceof MensagemJSON){
			OrgaoSubordinadoApiController orgaoSubordinadoApi = new OrgaoSubordinadoApiController();
			response = orgaoSubordinadoApi.get(codigo);
			if(response instanceof MensagemJSON){
				UnidadeGestoraApiController unidadeGestoraApi = new UnidadeGestoraApiController();
				response = unidadeGestoraApi.get(codigo);
				return response;
			}else{
				return (OrgaoGovernamental)response;
			}
		}else{
			return (OrgaoGovernamental)response;
		}
	}

	@RequestMapping(path="/find", method=RequestMethod.POST)
	@Override
	public List<OrgaoGovernamental> find(OrgaoGovernamental orgaoConsulta) {
		return null;
	}
	
	
	
}
