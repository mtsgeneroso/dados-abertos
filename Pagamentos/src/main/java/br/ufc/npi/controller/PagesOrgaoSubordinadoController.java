package br.ufc.npi.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import br.ufc.npi.model.OrgaoSubordinado;
import br.ufc.npi.model.OrgaoSuperior;
import br.ufc.npi.model.UnidadeGestora;
import br.ufc.npi.model.ui.Breadcumb;
import br.ufc.npi.model.ui.TipoOrgaoGovernamental;
import br.ufc.npi.service.OrgaoSubordinadoService;
import br.ufc.npi.service.UnidadeGestoraService;

@Controller
public class PagesOrgaoSubordinadoController implements IPagesOrgaoController<Long>{

	@Autowired
	private OrgaoSubordinadoService orgaoSubordinadoService;
	@Autowired
	private UnidadeGestoraService unidadeGestoraService;
	
	@RequestMapping(path="/orgao_subordinado")
	@Override
	public String pageAll(Model model){
		
		List<OrgaoSubordinado> orgaosSubordinados = orgaoSubordinadoService.findAll();
		model.addAttribute("orgaosSubordinados", orgaosSubordinados);
		
		ArrayList<Breadcumb> caminho = new ArrayList<>();
		caminho.add(Breadcumb.inicio);
		caminho.add(new Breadcumb("0", "ÓRGÃOS SUBORDINADOS", "/"+TipoOrgaoGovernamental.ORGAO_SUBORDINADO.getNomeTabela()));
		model.addAttribute("caminho", caminho);
		
		return "orgaos-subordinados";
		
	}
	
	@RequestMapping(path="/orgao_subordinado/{id}")
	@Override
	public String pageOne(@PathVariable("id")Long id, Model model){
		
		OrgaoSubordinado orgaoSubordinado = orgaoSubordinadoService.getById(id);
		
		List<UnidadeGestora> unidadesGestoras = unidadeGestoraService.findByCodOrgaoSubordinado(id);
		model.addAttribute("unidadesGestoras", unidadesGestoras);
		model.addAttribute("currentId", id);
		
		OrgaoSuperior orgaoSuperior = orgaoSubordinado.getOrgaoSuperior();
		
		ArrayList<Breadcumb> caminho = new ArrayList<>();
		caminho.add(Breadcumb.inicio);
		caminho.add(new Breadcumb(String.valueOf(orgaoSuperior.getCodOrgaoSuperior()), orgaoSuperior.getNomeOrgaoSuperior(), "/"+TipoOrgaoGovernamental.ORGAO_SUPERIOR.getNomeTabela()+"/" + orgaoSuperior.getCodOrgaoSuperior()));
		caminho.add(new Breadcumb(String.valueOf(orgaoSubordinado.getCodOrgaoSubordinado()), orgaoSubordinado.getNomeOrgaoSubordinado(),"/"+TipoOrgaoGovernamental.ORGAO_SUBORDINADO.getNomeTabela()+"/"+id));
		
		model.addAttribute("caminho", caminho);
		
		return "orgao-subordinado";
		
	}
	
}
