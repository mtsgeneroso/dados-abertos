package br.ufc.npi.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import br.ufc.npi.model.Programa;
import br.ufc.npi.model.ui.Breadcumb;
import br.ufc.npi.model.ui.TipoOrgaoGovernamental;
import br.ufc.npi.service.ProgramaService;

@Controller
public class PagesProgramaController implements IPagesOrgaoController<Long>{
	
	@Autowired
	private ProgramaService programaService;
	
	@RequestMapping(path="/programa")
	@Override
	public String pageAll(Model model){
		
		List<Programa> programas = programaService.findAll();
		model.addAttribute("programas", programas);
		
		ArrayList<Breadcumb> caminho = new ArrayList<>();
		caminho.add(Breadcumb.inicio);
		caminho.add(new Breadcumb("0", "PROGRAMAS", "/"+TipoOrgaoGovernamental.PROGRAMA.getNomeTabela()));
		model.addAttribute("caminho", caminho);
		
		return "programas";
		
	}
	
	@RequestMapping(path="/programa/{id}")
	@Override
	public String pageOne(@PathVariable("id")Long id, Model model){
		
		Programa programa = programaService.getById(id);
		
		model.addAttribute("currentId", id);
		
		ArrayList<Breadcumb> caminho = new ArrayList<>();
		caminho.add(Breadcumb.inicio);
		caminho.add(new Breadcumb("0", "PROGRAMAS", "/"+TipoOrgaoGovernamental.PROGRAMA.getNomeTabela()));
		caminho.add(new Breadcumb(String.valueOf(programa.getCodPrograma()), programa.getNomePrograma().toUpperCase(), "/"+TipoOrgaoGovernamental.PROGRAMA.getNomeTabela()+"/"+programa.getCodPrograma()));
		
		model.addAttribute("caminho", caminho);
		
		return "programa";
		
	}
	
}
