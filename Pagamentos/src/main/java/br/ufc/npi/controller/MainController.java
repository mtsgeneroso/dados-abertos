package br.ufc.npi.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import br.ufc.npi.model.Acao;
import br.ufc.npi.model.Favorecido;
import br.ufc.npi.model.OrgaoSubordinado;
import br.ufc.npi.model.OrgaoSuperior;
import br.ufc.npi.model.Programa;
import br.ufc.npi.model.UnidadeGestora;
import br.ufc.npi.model.api.OrgaoGovernamental;
import br.ufc.npi.model.ui.Breadcumb;
import br.ufc.npi.model.ui.TipoOrgaoGovernamental;
import br.ufc.npi.service.AcaoService;
import br.ufc.npi.service.FavorecidoService;
import br.ufc.npi.service.OrgaoSubordinadoService;
import br.ufc.npi.service.OrgaoSuperiorService;
import br.ufc.npi.service.ProgramaService;
import br.ufc.npi.service.UnidadeGestoraService;

@Controller
@RequestMapping(path="/")
public class MainController {

	private Breadcumb inicio = new Breadcumb("0", "INÍCIO", "/");
	
	@RequestMapping(path="/")
	public String index(Model model){
		ArrayList<Breadcumb> caminho = new ArrayList<>();
		caminho.add(inicio);
		model.addAttribute("caminho", caminho);
		
		ArrayList<TipoOrgaoGovernamental> itensHierarquia = new ArrayList<>();
		itensHierarquia.add(TipoOrgaoGovernamental.ORGAO_SUPERIOR);
		itensHierarquia.add(TipoOrgaoGovernamental.ORGAO_SUBORDINADO);
		itensHierarquia.add(TipoOrgaoGovernamental.UNIDADE_GESTORA);
		itensHierarquia.add(TipoOrgaoGovernamental.ACAO);
		itensHierarquia.add(TipoOrgaoGovernamental.PROGRAMA);
		itensHierarquia.add(TipoOrgaoGovernamental.FAVORECIDO);
		model.addAttribute("itensHierarquia", itensHierarquia);
		
		return "index";
	}
	
	@RequestMapping(path="/consulta-hierarquica")
	public String consultaHierarquica(Model model){
		
		ArrayList<Breadcumb> caminho = new ArrayList<>();
		caminho.add(inicio);
		caminho.add(new Breadcumb("0", "CONSULTA HIERÁRQUICA", "/consulta-hierarquica"));
		model.addAttribute("caminho", caminho);
		
		ArrayList<TipoOrgaoGovernamental> itensHierarquia = new ArrayList<>();
		itensHierarquia.add(TipoOrgaoGovernamental.ORGAO_SUPERIOR);
		itensHierarquia.add(TipoOrgaoGovernamental.ORGAO_SUBORDINADO);
		itensHierarquia.add(TipoOrgaoGovernamental.UNIDADE_GESTORA);
		itensHierarquia.add(TipoOrgaoGovernamental.ACAO);
		itensHierarquia.add(TipoOrgaoGovernamental.PROGRAMA);
		itensHierarquia.add(TipoOrgaoGovernamental.FAVORECIDO);
		model.addAttribute("itensHierarquia", itensHierarquia);
		
		return "consulta-hierarquica";
	}
	
	@RequestMapping(path="/consulta-simples")
	public String consultaSimples(Model model){
		
		ArrayList<Breadcumb> caminho = new ArrayList<>();
		caminho.add(inicio);
		caminho.add(new Breadcumb("0", "CONSULTA SIMPLES", "/consulta-simples"));
		model.addAttribute("caminho", caminho);
		
		return "consulta-simples";
	}
	
}
