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
import br.ufc.npi.model.OrgaoSubordinado;
import br.ufc.npi.model.OrgaoSuperior;
import br.ufc.npi.model.UnidadeGestora;
import br.ufc.npi.model.ui.Breadcumb;
import br.ufc.npi.service.OrgaoSubordinadoService;
import br.ufc.npi.service.OrgaoSuperiorService;
import br.ufc.npi.service.UnidadeGestoraService;

@Controller
@RequestMapping(path="/")
public class MainController {

	@Autowired
	private OrgaoSuperiorService orgaoSuperiorService;
	@Autowired
	private OrgaoSubordinadoService orgaoSubordinadoService;
	@Autowired
	private UnidadeGestoraService unidadeGestoraService;
	
	private Breadcumb inicio = new Breadcumb(0L, "INÍCIO", "/");
	
	
	@RequestMapping(path="/")
	public String index(Model model){
		
		ArrayList<Breadcumb> caminho = new ArrayList<>();
		caminho.add(inicio);
		
		model.addAttribute("caminho", caminho);
		
		return "index";
	}
	
	@RequestMapping(path="/consulta-hierarquica")
	public String consultaHierarquica(Model model){
		
		ArrayList<Breadcumb> caminho = new ArrayList<>();
		caminho.add(inicio);
		caminho.add(new Breadcumb(0L, "CONSULTA HIERÁRQUICA", "/consulta-hierarquica"));
		model.addAttribute("caminho", caminho);
		
		return "consulta-hierarquica";
	}
	
	@RequestMapping(path="/consulta-simples")
	public String consultaSimples(Model model){
		
		ArrayList<Breadcumb> caminho = new ArrayList<>();
		caminho.add(inicio);
		caminho.add(new Breadcumb(0L, "CONSULTA SIMPLES", "/consulta-simples"));
		model.addAttribute("caminho", caminho);
		
		return "consulta-simples";
	}
	
	@RequestMapping(path="/orgaos-superiores")
	public String paginaOrgaosSuperiores(Model model){
		
		List<OrgaoSuperior> orgaosSuperiores = orgaoSuperiorService.findAll();
		model.addAttribute("orgaosSuperiores", orgaosSuperiores);
		
		ArrayList<Breadcumb> caminho = new ArrayList<>();
		caminho.add(inicio);
		caminho.add(new Breadcumb(0L, "ÓRGÃOS SUPERIORES", "/orgaos-superiores"));
		model.addAttribute("caminho", caminho);
		
		return "orgaos-superiores";
		
	}
	
	@RequestMapping(path="/orgaos-subordinados")
	public String paginaOrgaosSubordinados(Model model){
		
		List<OrgaoSubordinado> orgaosSubordinados = orgaoSubordinadoService.findAll();
		model.addAttribute("orgaosSubordinados", orgaosSubordinados);
		
		ArrayList<Breadcumb> caminho = new ArrayList<>();
		caminho.add(inicio);
		caminho.add(new Breadcumb(0L, "ÓRGÃOS SUBORDINADOS", "/orgaos-subordinados"));
		model.addAttribute("caminho", caminho);
		
		return "orgaos-subordinados";
		
	}
	
	@RequestMapping(path="/unidades-gestoras")
	public String paginaUnidadesGestoras(Model model){
		
		List<UnidadeGestora> unidadesGestoras = unidadeGestoraService.findAll();
		model.addAttribute("unidadesGestoras", unidadesGestoras);
		
		ArrayList<Breadcumb> caminho = new ArrayList<>();
		caminho.add(inicio);
		caminho.add(new Breadcumb(0L, "UNIDADES GESTORAS", "/unidades-gestoras"));
		model.addAttribute("caminho", caminho);
		
		return "unidades-gestoras";
		
	}
	
	@RequestMapping(path="/orgaoSuperior/{id}")
	public String orgaoSuperior(@PathVariable("id")Long id, Model model){
		
		OrgaoSuperior orgaoSuperior = orgaoSuperiorService.getById(id);
		
		List<OrgaoSubordinado> orgaosSubordinados = orgaoSubordinadoService.listByCodOrgaoSuperior(id);
		model.addAttribute("orgaosSubordinados", orgaosSubordinados);
		model.addAttribute("currentId", id);
		
		ArrayList<Breadcumb> caminho = new ArrayList<>();
		caminho.add(inicio);
		caminho.add(new Breadcumb(orgaoSuperior.getCodOrgaoSuperior(), orgaoSuperior.getNomeOrgaoSuperior(),"/orgaoSuperior/"+id));
		
		model.addAttribute("caminho", caminho);
		
		return "orgao-superior";
	}
	
	@RequestMapping(path="/orgaoSubordinado/{id}")
	public String orgaoSubordinado(@PathVariable("id")Long id, Model model){
		
		OrgaoSubordinado orgaoSubordinado = orgaoSubordinadoService.getById(id);
		
		List<UnidadeGestora> unidadesGestoras = unidadeGestoraService.findByCodOrgaoSubordinado(id);
		model.addAttribute("unidadesGestoras", unidadesGestoras);
		model.addAttribute("currentId", id);
		
		OrgaoSuperior orgaoSuperior = orgaoSubordinado.getOrgaoSuperior();
		
		ArrayList<Breadcumb> caminho = new ArrayList<>();
		caminho.add(inicio);
		caminho.add(new Breadcumb(orgaoSuperior.getCodOrgaoSuperior(), orgaoSuperior.getNomeOrgaoSuperior(), "/orgaoSuperior/" + orgaoSuperior.getCodOrgaoSuperior()));
		caminho.add(new Breadcumb(orgaoSubordinado.getCodOrgaoSubordinado(), orgaoSubordinado.getNomeOrgaoSubordinado(),"/orgaoSubordinado/"+id));
		
		model.addAttribute("caminho", caminho);
		
		return "orgao-subordinado";
		
	}
	
	@RequestMapping(path="/unidadeGestora/{id}")
	public String unidadeGestora(@PathVariable("id")Long id, Model model){
		
		UnidadeGestora unidadeGestora = unidadeGestoraService.getById(id);
		
		List<Acao> acoes = unidadeGestora.getAcoes();
		
		model.addAttribute("acoes", acoes);
		model.addAttribute("currentId", id);
		
		OrgaoSubordinado orgaoSubordinado = unidadeGestora.getOrgaoSubordinado();
		OrgaoSuperior orgaoSuperior = orgaoSubordinado.getOrgaoSuperior();
		
		ArrayList<Breadcumb> caminho = new ArrayList<>();
		caminho.add(inicio);
		caminho.add(new Breadcumb(orgaoSuperior.getCodOrgaoSuperior(), orgaoSuperior.getNomeOrgaoSuperior(), "/orgaoSuperior/" + orgaoSuperior.getCodOrgaoSuperior()));
		caminho.add(new Breadcumb(orgaoSubordinado.getCodOrgaoSubordinado(), orgaoSubordinado.getNomeOrgaoSubordinado(),"/orgaoSubordinado/"+orgaoSubordinado.getCodOrgaoSubordinado()));
		caminho.add(new Breadcumb(unidadeGestora.getCodUnidadeGestora(), unidadeGestora.getNomeUnidadeGestora(), "/unidadeGestora/" + id));
		
		model.addAttribute("caminho", caminho);
		
		return "unidade-gestora";
		
	}
	
	
}
