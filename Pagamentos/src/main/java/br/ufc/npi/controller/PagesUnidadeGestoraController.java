package br.ufc.npi.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import br.ufc.npi.model.Acao;
import br.ufc.npi.model.OrgaoSubordinado;
import br.ufc.npi.model.OrgaoSuperior;
import br.ufc.npi.model.UnidadeGestora;
import br.ufc.npi.model.ui.Breadcumb;
import br.ufc.npi.model.ui.TipoOrgaoGovernamental;
import br.ufc.npi.service.UnidadeGestoraService;

@Controller
public class PagesUnidadeGestoraController implements IPagesOrgaoController<Long>{

	@Autowired
	private UnidadeGestoraService unidadeGestoraService;
	
	@RequestMapping(path="/unidade_gestora")
	@Override
	public String pageAll(Model model){
		
		List<UnidadeGestora> unidadesGestoras = unidadeGestoraService.findAll();
		model.addAttribute("unidadesGestoras", unidadesGestoras);
		
		ArrayList<Breadcumb> caminho = new ArrayList<>();
		caminho.add(Breadcumb.inicio);
		caminho.add(new Breadcumb("0", "UNIDADES GESTORAS", "/"+TipoOrgaoGovernamental.UNIDADE_GESTORA.getNomeTabela()));
		model.addAttribute("caminho", caminho);
		
		return "unidades-gestoras";
		
	}
	
	@RequestMapping(path="/unidade_gestora/{id}")
	@Override
	public String pageOne(@PathVariable("id")Long id, Model model){
		
		UnidadeGestora unidadeGestora = unidadeGestoraService.getById(id);
		
		List<Acao> acoes = unidadeGestora.getAcoes();
		
		model.addAttribute("acoes", acoes);
		model.addAttribute("currentId", id);
		
		OrgaoSubordinado orgaoSubordinado = unidadeGestora.getOrgaoSubordinado();
		OrgaoSuperior orgaoSuperior = orgaoSubordinado.getOrgaoSuperior();
		
		ArrayList<Breadcumb> caminho = new ArrayList<>();
		caminho.add(Breadcumb.inicio);
		caminho.add(new Breadcumb(String.valueOf(orgaoSuperior.getCodOrgaoSuperior()), orgaoSuperior.getNomeOrgaoSuperior(), "/"+TipoOrgaoGovernamental.ORGAO_SUPERIOR.getNomeTabela()+"/" + orgaoSuperior.getCodOrgaoSuperior()));
		caminho.add(new Breadcumb(String.valueOf(orgaoSubordinado.getCodOrgaoSubordinado()), orgaoSubordinado.getNomeOrgaoSubordinado(),"/"+TipoOrgaoGovernamental.ORGAO_SUBORDINADO.getNomeTabela()+"/"+orgaoSubordinado.getCodOrgaoSubordinado()));
		caminho.add(new Breadcumb(String.valueOf(unidadeGestora.getCodUnidadeGestora()), unidadeGestora.getNomeUnidadeGestora(), "/"+TipoOrgaoGovernamental.UNIDADE_GESTORA.getNomeTabela()+"/" + id));
		
		model.addAttribute("caminho", caminho);
		
		return "unidade-gestora";
		
	}
	
}
