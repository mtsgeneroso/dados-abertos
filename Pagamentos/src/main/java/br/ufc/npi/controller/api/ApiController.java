package br.ufc.npi.controller.api;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import br.ufc.npi.model.Orgao;
import br.ufc.npi.model.OrgaoSubordinado;
import br.ufc.npi.model.OrgaoSuperior;
import br.ufc.npi.model.UnidadeGestora;
import br.ufc.npi.model.request.RequestWrapper;
import br.ufc.npi.service.OrgaoSubordinadoService;
import br.ufc.npi.service.OrgaoSuperiorService;
import br.ufc.npi.service.UnidadeGestoraService;

@RestController
@RequestMapping(path="/api")
public class ApiController {
	
	@Autowired
	private OrgaoSuperiorService orgaoSuperiorService;
	@Autowired
	private OrgaoSubordinadoService orgaoSubordinadoService;
	@Autowired
	private UnidadeGestoraService unidadeGestoraService;
	
	@RequestMapping(path="/orgaos")
	public List<Orgao> listarTodosOrgaos(){
		
		List<OrgaoSuperior> orgaosSuperiores = orgaoSuperiorService.findAll();
		List<OrgaoSubordinado> orgaosSubordinados = orgaoSubordinadoService.findAll();
		List<UnidadeGestora> unidadesGestoras = unidadeGestoraService.findAll();
		
		ArrayList<Orgao> orgaos = new ArrayList<Orgao>();
		
		for(OrgaoSuperior os : orgaosSuperiores){
			orgaos.add(new Orgao(os.getNomeOrgaoSuperior(), "orgaoSuperior", os.getCodOrgaoSuperior()));
		}
		
		for(OrgaoSubordinado os : orgaosSubordinados){
			orgaos.add(new Orgao(os.getNomeOrgaoSubordinado(), "orgaoSubordinado", os.getCodOrgaoSubordinado()));
		}
		
		for(UnidadeGestora ug : unidadesGestoras){
			orgaos.add(new Orgao(ug.getNomeUnidadeGestora(), "unidadeGestora", ug.getCodUnidadeGestora()));
		}
		
		return orgaos;
		
	}
	
}
