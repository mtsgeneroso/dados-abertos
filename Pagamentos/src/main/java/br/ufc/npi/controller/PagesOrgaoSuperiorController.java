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
import br.ufc.npi.model.ui.Breadcumb;
import br.ufc.npi.model.ui.TipoOrgaoGovernamental;
import br.ufc.npi.service.OrgaoSubordinadoService;
import br.ufc.npi.service.OrgaoSuperiorService;

@Controller
public class PagesOrgaoSuperiorController implements IPagesOrgaoController<Long>{

	@Autowired
	private OrgaoSuperiorService orgaoSuperiorService;
	@Autowired
	private OrgaoSubordinadoService orgaoSubordinadoService;
	
	@RequestMapping(path="/orgao_superior")
	@Override
	public String pageAll(Model model){
		
		List<OrgaoSuperior> orgaosSuperiores = orgaoSuperiorService.findAll();
		model.addAttribute("orgaosSuperiores", orgaosSuperiores);
		
		ArrayList<Breadcumb> caminho = new ArrayList<>();
		caminho.add(Breadcumb.inicio);
		caminho.add(new Breadcumb("0", "ÓRGÃOS SUPERIORES", "/"+TipoOrgaoGovernamental.ORGAO_SUPERIOR.getNomeTabela()));
		model.addAttribute("caminho", caminho);
		
		return "orgaos-superiores";
		
	}
	
	@RequestMapping(path="/orgao_superior/{id}")
	@Override
	public String pageOne(@PathVariable("id")Long id, Model model){
		
		OrgaoSuperior orgaoSuperior = orgaoSuperiorService.getById(id);
		
		List<OrgaoSubordinado> orgaosSubordinados = orgaoSubordinadoService.listByCodOrgaoSuperior(id);
		model.addAttribute("orgaosSubordinados", orgaosSubordinados);
		model.addAttribute("currentId", id);
		
		ArrayList<Breadcumb> caminho = new ArrayList<>();
		caminho.add(Breadcumb.inicio);
		caminho.add(new Breadcumb(String.valueOf(orgaoSuperior.getCodOrgaoSuperior()), orgaoSuperior.getNomeOrgaoSuperior(),"/"+TipoOrgaoGovernamental.ORGAO_SUPERIOR.getNomeTabela()+"/"+id));
		
		model.addAttribute("caminho", caminho);
		
		return "orgao-superior";
	}
	
}
