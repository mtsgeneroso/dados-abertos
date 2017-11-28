package br.ufc.npi.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import br.ufc.npi.model.Acao;
import br.ufc.npi.model.ui.Breadcumb;
import br.ufc.npi.model.ui.TipoOrgaoGovernamental;
import br.ufc.npi.service.AcaoService;

@Controller
public class PagesAcaoController implements IPagesOrgaoController<String>{

	@Autowired
	private AcaoService acaoService;
	
	@RequestMapping(path="/acao")
	@Override
	public String pageAll(Model model){
		
		List<Acao> acoes = acaoService.findAll();
		model.addAttribute("acoes", acoes);
		
		ArrayList<Breadcumb> caminho = new ArrayList<>();
		caminho.add(Breadcumb.inicio);
		caminho.add(new Breadcumb("0", "AÇÕES", "/"+TipoOrgaoGovernamental.ACAO.getNomeTabela()));
		model.addAttribute("caminho", caminho);
		
		return "acoes";
		
	}
	
	@RequestMapping(path="/acao/{id}")
	@Override
	public String pageOne(@PathVariable("id")String codigo, Model model){
		
		Acao acao = acaoService.getById(codigo);
		
		model.addAttribute("currentId", codigo);
		
		ArrayList<Breadcumb> caminho = new ArrayList<>();
		caminho.add(Breadcumb.inicio);
		caminho.add(new Breadcumb("0", "AÇÕES", "/"+TipoOrgaoGovernamental.ACAO.getNomeTabela()));
		caminho.add(new Breadcumb(acao.getCodAcao(), acao.getNomeAcao().toUpperCase(), "/"+TipoOrgaoGovernamental.ACAO.getNomeTabela()+"/"+acao.getCodAcao()));
		
		model.addAttribute("caminho", caminho);
		
		return "acao";
		
	}
	
}
