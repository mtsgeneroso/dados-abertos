package br.ufc.npi.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import br.ufc.npi.model.Favorecido;
import br.ufc.npi.model.ui.Breadcumb;
import br.ufc.npi.model.ui.TipoOrgaoGovernamental;
import br.ufc.npi.service.FavorecidoService;

@Controller
public class PagesFavorecidoController implements IPagesOrgaoController<String>{

	@Autowired
	private FavorecidoService favorecidoService;
	
	@RequestMapping(path="/favorecido")
	@Override
	public String pageAll(Model model){
		
		List<Favorecido> favorecidos = favorecidoService.findAll();
		model.addAttribute("favorecidos", favorecidos);
		
		ArrayList<Breadcumb> caminho = new ArrayList<>();
		caminho.add(Breadcumb.inicio);
		caminho.add(new Breadcumb("0", "FAVORECIDOS", "/"+TipoOrgaoGovernamental.FAVORECIDO.getNomeTabela()));
		model.addAttribute("caminho", caminho);
		
		return "favorecidos";
		
	}
	
	@RequestMapping(path="/favorecido/{id}")
	@Override
	public String pageOne(@PathVariable("id")String id, Model model){
		
		Favorecido favorecido = favorecidoService.getById(id);
		
		model.addAttribute("currentId", id);
		
		ArrayList<Breadcumb> caminho = new ArrayList<>();
		caminho.add(Breadcumb.inicio);
		caminho.add(new Breadcumb("0", "FAVORECIDO", "/"+TipoOrgaoGovernamental.FAVORECIDO.getNomeTabela()));
		caminho.add(new Breadcumb(favorecido.getCodFavorecido(), favorecido.getNomeFavorecido().toUpperCase(), "/"+TipoOrgaoGovernamental.FAVORECIDO.getNomeTabela()+"/"+favorecido.getCodFavorecido()));
		
		model.addAttribute("caminho", caminho);
		
		return "programa";
		
	}
	
}
