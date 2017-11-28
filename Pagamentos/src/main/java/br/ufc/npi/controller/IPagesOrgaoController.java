package br.ufc.npi.controller;

import org.springframework.ui.Model;

public interface IPagesOrgaoController<T> {

	public String pageOne(T codigo, Model model);
	public String pageAll(Model model);
	
}
