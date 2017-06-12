package br.ufc.npi.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(path="/")
public class MainController {

	@RequestMapping(path="/")
	public String index(){
		return "index";
	}
	
}
