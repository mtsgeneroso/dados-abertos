package br.ufc.npi.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import br.ufc.npi.model.OrgaoSuperior;
import br.ufc.npi.service.OrgaoSuperiorService;

@Controller
@RequestMapping(path="/")
public class MainController {

	@Autowired
	private OrgaoSuperiorService orgaoSuperiorDAO;
	
	@RequestMapping(path="/")
	public String index(Model model){
		
		List<OrgaoSuperior> orgaosSuperiores = orgaoSuperiorDAO.findAll();
		model.addAttribute("orgaosSuperiores", orgaosSuperiores);
		return "index";
	}
	
}
